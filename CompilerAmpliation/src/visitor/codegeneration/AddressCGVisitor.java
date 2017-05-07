package visitor.codegeneration;

import ast.expression.FieldAccess;
import ast.expression.Indexing;
import ast.expression.Variable;
import ast.program.VariableDefinition;
import ast.statement.Assignment;
import ast.type.ArrayType;
import ast.type.IntType;
import visitor.Visitor;

public class AddressCGVisitor extends AbstractCGVisitor {

    private Visitor valueVisitor;

    @Override
    public Object visit(Assignment as, Object params) {
	as.getLeft().accept(this, params);
	CodeGenerator.getInstance().dup(as.getLeft().getType());
	as.getRight().accept(valueVisitor, params);
	CodeGenerator.getInstance().convertTo(as.getRight().getType(), as.getLeft().getType());
	CodeGenerator.getInstance().store(as.getLeft().getType());
	return null;
    }

    @Override
    public Object visit(FieldAccess field, Object params) {
	field.getLeft().accept(this, params);
	CodeGenerator.getInstance().push(IntType.getInstance(),
		field.getLeft().getType().getField(field.getName()).getOffset());
	CodeGenerator.getInstance().add(IntType.getInstance());
	return null;
    }

    @Override
    public Object visit(Indexing ind, Object params) {
	ind.getLeft().accept(this, params);
	ind.getRight().accept(valueVisitor, params);
	CodeGenerator.getInstance().convertTo(ind.getRight().getType(), IntType.getInstance());
	CodeGenerator.getInstance().push(IntType.getInstance(),
		((ArrayType) ind.getLeft().getType()).getOf().numberOfBytes());
	CodeGenerator.getInstance().mul(IntType.getInstance());
	CodeGenerator.getInstance().add(IntType.getInstance());
	return null;
    }

    @Override
    public Object visit(Variable var, Object params) {
	VariableDefinition def = (VariableDefinition) var.getDefinition();
	if (def.getScope() == 0)
	    CodeGenerator.getInstance().pusha(def.getOffset());
	else {
	    CodeGenerator.getInstance().pushabp();
	    CodeGenerator.getInstance().push(IntType.getInstance(), def.getOffset());
	    CodeGenerator.getInstance().add(IntType.getInstance());
	}
	return null;
    }

    void setValueVisitor(Visitor visitor) {
	this.valueVisitor = visitor;
    }

}
