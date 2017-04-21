package visitor.semantic;

import ast.expression.Arithmetic;
import ast.expression.ArithmeticAssignment;
import ast.expression.Cast;
import ast.expression.CharLiteral;
import ast.expression.Comparison;
import ast.expression.Decrement;
import ast.expression.Expression;
import ast.expression.FieldAccess;
import ast.expression.Increment;
import ast.expression.Indexing;
import ast.expression.IntLiteral;
import ast.expression.Logical;
import ast.expression.RealLiteral;
import ast.expression.UnaryMinus;
import ast.expression.UnaryNot;
import ast.expression.Variable;
import ast.program.Definition;
import ast.program.FunctionDefinition;
import ast.program.Program;
import ast.program.VariableDefinition;
import ast.statement.Assignment;
import ast.statement.IfStatement;
import ast.statement.Invocation;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.Statement;
import ast.statement.WhileStatement;
import ast.statement.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.RecordField;
import ast.type.Struct;
import ast.type.VoidType;
import visitor.Visitor;

/**
 * Avoids implementing all the methods in each Visitor implementing
 * a default behaviour for each one
 * @author Sergio
 *
 */
public abstract class AbstractVisitor implements Visitor {

    /* Program package */

    @Override
    public Object visit(FunctionDefinition func, Object params) {
	func.getType().accept(this, params);
	for (Statement st : func.getBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(VariableDefinition var, Object params) {
	var.getType().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Program program, Object params) {
	for (Definition d : program.getDefinitions()) {
	    d.accept(this, params);
	}
	return null;
    }

    /* Expressions package */

    @Override
    public Object visit(Arithmetic ar, Object params) {
	ar.getLeft().accept(this, params);
	ar.getRight().accept(this, params);
	return null;
    }
    
    @Override
    public Object visit(ArithmeticAssignment ar, Object params) {
	ar.getLeft().accept(this, params);
	ar.getRight().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Cast cast, Object params) {
	cast.getType().accept(this, params);
	cast.getExpression().accept(this, params);
	return null;
    }

    @Override
    public Object visit(CharLiteral ch, Object params) {
	ch.getType().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Comparison compar, Object params) {
	compar.getLeft().accept(this, params);
	compar.getRight().accept(this, params);
	return null;
    }
    
    @Override
    public Object visit(Decrement dec, Object params){
	dec.getExpression().accept(this, params);
	return null;
    }

    @Override
    public Object visit(FieldAccess field, Object params) {
	field.getLeft().accept(this, params);
	return null;
    }
    
    @Override
    public Object visit(Increment inc, Object params){
	inc.getExpression().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Indexing ind, Object params) {
	ind.getLeft().accept(this, params);
	ind.getRight().accept(this, params);
	return null;
    }

    @Override
    public Object visit(IntLiteral lit, Object params) {
	lit.getType().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Logical log, Object params) {
	log.getLeft().accept(this, params);
	log.getRight().accept(this, params);
	return null;
    }

    @Override
    public Object visit(RealLiteral real, Object params) {
	real.getType().accept(this, params);
	return null;
    }

    @Override
    public Object visit(UnaryMinus minus, Object params) {
	minus.getExpression().accept(this, params);
	return null;
    }

    @Override
    public Object visit(UnaryNot not, Object params) {
	not.getExpression().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Variable var, Object params) {
	var.getType().accept(this, params);
	var.getDefinition().accept(this, params);
	return null;
    }

    /* Statement package */

    @Override
    public Object visit(Assignment as, Object params) {
	as.getLeft().accept(this, params);
	as.getRight().accept(this, params);
	return null;
    }

    @Override
    public Object visit(IfStatement ifst, Object params) {
	ifst.getCondition().accept(this, params);
	for (Statement st : ifst.getIfBody())
	    st.accept(this, params);
	for (Statement st : ifst.getElseBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(Invocation inv, Object params) {
	inv.getVar().accept(this, params);
	for (Expression param : inv.getParams())
	    param.accept(this, params);
	return null;
    }

    @Override
    public Object visit(Read read, Object params) {
	for (Expression exp : read.getExpressions())
	    exp.accept(this, params);
	return null;
    }

    @Override
    public Object visit(Return ret, Object params) {
	ret.getExpression().accept(this, params);
	return null;
    }

    @Override
    public Object visit(WhileStatement whst, Object params) {
	whst.getCondition().accept(this, params);
	for (Statement st : whst.getWhileBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(Write wr, Object params) {
	for (Expression exp : wr.getExpressions()) {
	    exp.accept(this, params);
	}
	return null;
    }

    /* Type package */

    @Override
    public Object visit(ArrayType at, Object params) {
	at.getOf().accept(this, params);
	return null;
    }

    @Override
    public Object visit(CharType ct, Object params) {
	return null;
    }

    @Override
    public Object visit(ErrorType et, Object params) {
	return null;
    }

    @Override
    public Object visit(FunctionType ft, Object params) {
	for (VariableDefinition varDef : ft.getParams())
	    varDef.accept(this, params);
	ft.getReturnType().accept(this, params);
	return null;
    }

    @Override
    public Object visit(IntType it, Object params) {
	return null;
    }

    @Override
    public Object visit(RealType rt, Object params) {
	return null;
    }

    @Override
    public Object visit(RecordField rf, Object params) {
	rf.getType().accept(this, params);
	return null;
    }

    @Override
    public Object visit(Struct st, Object params) {
	for (RecordField rf : st.getRecords())
	    rf.accept(this, params);
	return null;
    }

    @Override
    public Object visit(VoidType vt, Object params) {
	return null;
    }

}
