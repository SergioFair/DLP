package visitor.codegeneration;

import ast.expression.ArithmeticAssignment;
import ast.expression.Decrement;
import ast.expression.Expression;
import ast.expression.Increment;
import ast.program.Definition;
import ast.program.FunctionDefinition;
import ast.program.Program;
import ast.program.VariableDefinition;
import ast.statement.Assignment;
import ast.statement.DoWhileStatement;
import ast.statement.ForStatement;
import ast.statement.IfStatement;
import ast.statement.Invocation;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.Statement;
import ast.statement.WhileStatement;
import ast.statement.Write;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.VoidType;

public class ExecuteCGVisitor extends AbstractCGVisitor {

	private ValueCGVisitor valVisitor;
	private AddressCGVisitor addrVisitor;
	String input;

	public ExecuteCGVisitor(String input) {
		this.input = input;
		this.valVisitor = new ValueCGVisitor();
		this.addrVisitor = new AddressCGVisitor();
		this.valVisitor.setAddressVisitor(this.addrVisitor);
		this.valVisitor.setExecuteVisitor(this);
		this.addrVisitor.setVisitor(this.valVisitor);
	}

	@Override
	public Object visit(Program program, Object params) {
		CodeGenerator.getInstance().source(input);
		for (Definition d : program.definitions)
			if (d instanceof VariableDefinition)
				d.accept(this, params);
		CodeGenerator.getInstance().callMain();
		CodeGenerator.getInstance().halt();
		for (Definition d : program.definitions)
			if (!(d instanceof VariableDefinition)) {
				d.accept(this, params);
				CodeGenerator.getInstance().newLine();
			}
		return null;
	}

	@Override
	public Object visit(Assignment as, Object params) {
		CodeGenerator.getInstance().comment("assignment");
		as.getLeft().accept(addrVisitor, params);
		as.getRight().accept(valVisitor, params);
		CodeGenerator.getInstance().convertTo(as.getRight().getType(),
				as.getLeft().getType());
		CodeGenerator.getInstance().store(as.getLeft().getType());
		return null;
	}

	@Override
	public Object visit(ArithmeticAssignment ar, Object params) {
		ar.getLeft().accept(addrVisitor, params);
		ar.getLeft().accept(valVisitor, params);
		ar.getRight().accept(valVisitor, params);
		CodeGenerator.getInstance().arithmeticAssignment(ar.getOperator(),
				ar.getType());
		CodeGenerator.getInstance().store(ar.getType());
		return null;
	}

	@Override
	public Object visit(Decrement dec, Object params) {
		dec.getExpression().accept(addrVisitor, params);
		dec.getExpression().accept(valVisitor, params);
		CodeGenerator.getInstance().push(IntType.getInstance(), 1);
		CodeGenerator.getInstance().convertTo(IntType.getInstance(),
				dec.getType());
		CodeGenerator.getInstance().sub(dec.getType());
		CodeGenerator.getInstance().store(dec.getType());
		return null;
	}
	
	@Override
	public Object visit(DoWhileStatement dow, Object params){
		int labels = CodeGenerator.getInstance().getLabels(1);
		CodeGenerator.getInstance().label(labels);
		for(Statement st : dow.getBody())
			st.accept(this, params);
		dow.getCondition().accept(valVisitor, params);
		CodeGenerator.getInstance().jnz(labels);
		return null;
	}

	@Override
	public Object visit(ForStatement forStatement, Object params) {
		int labelNumber = CodeGenerator.getInstance().getLabels(2);
		forStatement.getInitilization().accept(this, params);
		CodeGenerator.getInstance().label(labelNumber);
		forStatement.getCondition().accept(valVisitor, params);
		CodeGenerator.getInstance().jz(labelNumber + 1);
		for (Statement st : forStatement.getBody())
			st.accept(this, params);
		forStatement.getIncrement().accept(this, params);
		CodeGenerator.getInstance().jmp(labelNumber);
		CodeGenerator.getInstance().label(labelNumber + 1);
		return null;
	}

	@Override
	public Object visit(IfStatement ifst, Object params) {

		// If part
		CodeGenerator.getInstance().comment("if");
		int labelNumber = CodeGenerator.getInstance().getLabels(2);
		ifst.getCondition().accept(valVisitor, params);
		CodeGenerator.getInstance().jz(labelNumber);
		for (Statement st : ifst.getIfBody()) {
			CodeGenerator.getInstance().writeLine(st.getLine());
			st.accept(this, params);
			CodeGenerator.getInstance().newLine();
		}

		// Else part
		if (!ifst.getElseBody().isEmpty()) {
			CodeGenerator.getInstance().jmp(labelNumber + 1);
			CodeGenerator.getInstance().label(labelNumber);
			CodeGenerator.getInstance().comment("else");
			for (Statement st : ifst.getElseBody()) {
				CodeGenerator.getInstance().writeLine(st.getLine());
				st.accept(this, params);
				CodeGenerator.getInstance().newLine();
			}
			CodeGenerator.getInstance().label(labelNumber + 1);
		}
		CodeGenerator.getInstance().newLine();
		return null;
	}

	@Override
	public Object visit(Increment inc, Object params) {
		inc.getExpression().accept(addrVisitor, params);
		inc.getExpression().accept(valVisitor, params);
		CodeGenerator.getInstance().push(IntType.getInstance(), 1);
		CodeGenerator.getInstance().convertTo(IntType.getInstance(),
				inc.getType());
		CodeGenerator.getInstance().add(inc.getType());
		CodeGenerator.getInstance().store(inc.getType());
		return null;
	}

	@Override
	public Object visit(Invocation inv, Object params) {
		CodeGenerator.getInstance().comment("invocation");
		CodeGenerator.getInstance().writeLine(inv.getLine());
		for (Expression exp : inv.getParams())
			exp.accept(valVisitor, params);
		CodeGenerator.getInstance().call(inv.getVar().getName());
		if (!(inv.getType() instanceof VoidType))
			CodeGenerator.getInstance().pop(inv.getType());
		return null;
	}

	@Override
	public Object visit(Read read, Object params) {
		CodeGenerator.getInstance().writeLine(read.getLine());
		CodeGenerator.getInstance().comment("read");
		for (Expression exp : read.expressions) {
			exp.accept(addrVisitor, params);
			CodeGenerator.getInstance().in(exp.getType());
			CodeGenerator.getInstance().store(exp.getType());
		}
		CodeGenerator.getInstance().newLine();
		return null;
	}

	@Override
	public Object visit(WhileStatement whst, Object params) {
		CodeGenerator.getInstance().comment("while");
		int labelNumber = CodeGenerator.getInstance().getLabels(2);
		CodeGenerator.getInstance().label(labelNumber);
		whst.getCondition().accept(valVisitor, params);
		CodeGenerator.getInstance().convertTo(whst.getCondition().getType(),
				IntType.getInstance());
		CodeGenerator.getInstance().jz(labelNumber + 1);
		for (Statement st : whst.getWhileBody()) {
			st.accept(this, params);
			CodeGenerator.getInstance().newLine();
		}
		CodeGenerator.getInstance().jmp(labelNumber);
		CodeGenerator.getInstance().label(labelNumber + 1);
		return null;
	}

	@Override
	public Object visit(Write wr, Object params) {
		CodeGenerator.getInstance().comment("Write");
		for (Expression exp : wr.expressions) {
			exp.accept(valVisitor, params);
			CodeGenerator.getInstance().out(exp.getType());
		}
		CodeGenerator.getInstance().newLine();
		return null;
	}

	@Override
	public Object visit(VariableDefinition var, Object params) {
		if (var.getScope() == 0)
			CodeGenerator.getInstance().globalVar(var);
		else if (var.getOffset() > 4)
			CodeGenerator.getInstance().parameter(var);
		else if (var.getOffset() < 0)
			CodeGenerator.getInstance().localVar(var);
		return null;
	}

	@Override
	public Object visit(FunctionDefinition funcDef, Object params) {

		// Information
		CodeGenerator.getInstance().functionDefinition(funcDef.getName());

		// Execution
		CodeGenerator.getInstance().label(funcDef.getName());
		CodeGenerator.getInstance().enter(funcDef.calculateLocalsBytes());
		params = funcDef;
		for (Statement st : funcDef.getBody()) {
			CodeGenerator.getInstance().newLine();
			st.accept(this, params);
			CodeGenerator.getInstance().newLine();
		}
		if (((FunctionType) funcDef.getType()).getReturnType() instanceof VoidType) {
			CodeGenerator.getInstance().ret(0, funcDef.calculateLocalsBytes(),
					funcDef.calculateParamsBytes());
		}
		CodeGenerator.getInstance().newLine();
		return null;
	}

	@Override
	public Object visit(Return ret, Object params) {
		CodeGenerator.getInstance().writeLine(ret.getLine());
		ret.getExpression().accept(valVisitor, params);
		FunctionDefinition funcDef = (FunctionDefinition) params;
		CodeGenerator.getInstance().ret(funcDef.calculateReturnBytes(),
				funcDef.calculateLocalsBytes(), funcDef.calculateParamsBytes());
		CodeGenerator.getInstance().newLine();
		return null;
	}

}
