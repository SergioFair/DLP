package visitor.semantic;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Arithmetic;
import ast.expression.Cast;
import ast.expression.CharLiteral;
import ast.expression.Comparison;
import ast.expression.Expression;
import ast.expression.FieldAccess;
import ast.expression.Indexing;
import ast.expression.IntLiteral;
import ast.expression.Logical;
import ast.expression.Power;
import ast.expression.RealLiteral;
import ast.expression.Ternary;
import ast.expression.UnaryMinus;
import ast.expression.UnaryNot;
import ast.expression.Variable;
import ast.program.FunctionDefinition;
import ast.statement.Assignment;
import ast.statement.DoWhileStatement;
import ast.statement.ForStatement;
import ast.statement.IfStatement;
import ast.statement.Invocation;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.Statement;
import ast.statement.WhileStatement;
import ast.statement.switchCase.BreakInstruction;
import ast.statement.switchCase.Case;
import ast.statement.switchCase.DefaultCase;
import ast.statement.switchCase.NormalCase;
import ast.statement.switchCase.SwitchCase;
import ast.type.ArrayType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.Type;

/**
 * Looks for lvalue and type conflicts in the program.
 * 
 * @author Sergio
 *
 */
public class TypeCheckingVisitor extends AbstractVisitor {

    /* Definitions package */

    public Object visit(FunctionDefinition func, Object params) {
	func.getType().accept(this, params);
	for (Statement st : func.getBody()) {
	    st.accept(this, func.getType());
	}
	return null;
    }

    /* Expressions package */

    @Override
    public Object visit(Arithmetic ar, Object params) {
	ar.getLeft().accept(this, params);
	ar.getRight().accept(this, params);
	ar.setLValue(false);
	ar.setType(ar.getLeft().getType().arithmetic(ar.getRight().getType()));
	if (ar.getType() == null)
	    new ErrorType(ar, "Not valid Arithmetic operation");
	return null;
    }

    @Override
    public Object visit(Cast cast, Object params) {
	cast.getType().accept(this, params);
	cast.getExpression().accept(this, params);
	cast.setLValue(false);
	return null;
    }

    @Override
    public Object visit(CharLiteral ch, Object params) {
	ch.setLValue(false);
	return null;
    }

    @Override
    public Object visit(Comparison compar, Object params) {
	compar.getLeft().accept(this, params);
	compar.getRight().accept(this, params);
	compar.setLValue(false);
	compar.setType(compar.getLeft().getType().comparison(compar.getRight().getType()));
	if (compar.getType() == null)
	    new ErrorType(compar, "Not valid Comparison operation");
	return null;
    }

    @Override
    public Object visit(FieldAccess field, Object params) {
	field.getLeft().accept(this, params);
	field.setLValue(true);
	field.setType(field.getLeft().getType().dot(field.getName()));
	if (field.getType() == null)
	    new ErrorType(field, "Not valid Field Access operation");
	return null;
    }

    @Override
    public Object visit(Indexing ind, Object params) {
	ind.getLeft().accept(this, params);
	ind.getRight().accept(this, params);
	ind.setLValue(true);
	ind.setType(ind.getLeft().getType().squareBrackets(ind.getRight().getType()));
	if (ind.getType() == null)
	    new ErrorType(ind, "Not valid Indexing operation");
	return null;
    }

    @Override
    public Object visit(IntLiteral lit, Object params) {
	lit.setLValue(false);
	return null;
    }

    @Override
    public Object visit(Logical log, Object params) {
	log.getLeft().accept(this, params);
	log.getRight().accept(this, params);
	log.setLValue(false);
	log.setType(log.getLeft().getType().logical(log.getRight().getType()));
	if (log.getType() == null)
	    new ErrorType(log, "Not valid Logical construction");
	return null;
    }

    @Override
    public Object visit(Power pow, Object params) {
	pow.getLeft().accept(this, params);
	pow.getRight().accept(this, params);
	pow.setLValue(false);
	pow.setType(pow.getLeft().getType().arithmetic(pow.getRight().getType().promotesTo(IntType.getInstance())));
	if (pow.getType() == null)
	    new ErrorType(pow, "Power operation not valid");
	return null;
    }

    @Override
    public Object visit(RealLiteral real, Object params) {
	real.setLValue(false);
	return null;
    }

    @Override
    public Object visit(Ternary ter, Object params) {
	ter.getCondition().accept(this, params);
	if (!ter.getCondition().getType().isLogical())
	    new ErrorType(ter.getCondition(), "Not valid condition for Ternary operator");
	ter.getFirst().accept(this, params);
	ter.getSecond().accept(this, params);
	ter.setType(ter.getFirst().getType().higherThan(ter.getSecond().getType()));
	ter.setLValue(false);
	return null;
    }

    @Override
    public Object visit(UnaryMinus minus, Object params) {
	minus.getExpression().accept(this, params);
	minus.setLValue(false);
	minus.setType(minus.getExpression().getType().arithmetic());
	if (minus.getType() == null)
	    new ErrorType(minus, "Not valid Unary Minus construction");
	return null;
    }

    @Override
    public Object visit(UnaryNot not, Object params) {
	not.getExpression().accept(this, params);
	not.setLValue(false);
	not.setType(not.getExpression().getType().arithmetic());
	if (not.getType() == null)
	    new ErrorType(not, "Not valid Unary Not construction");
	return null;
    }

    @Override
    public Object visit(Variable var, Object params) {
	var.setLValue(true);
	var.setType(var.getDefinition().getType());
	return null;
    }

    /* Statement package */

    @Override
    public Object visit(Assignment as, Object params) {
	as.getLeft().accept(this, params);
	as.getRight().accept(this, params);
	if (!as.getLeft().getLValue())
	    new ErrorType(as, "LValue expected for Assignment");
	as.setLValue(as.getLeft().getLValue());
	if (as.getLeft().getType() instanceof ArrayType)
	    as.getLeft().setType(as.getRight().getType().promotesTo(((ArrayType) as.getLeft().getType()).getOf()));
	else
	    as.getLeft().setType(as.getRight().getType().promotesTo(as.getLeft().getType()));
	if (as.getLeft().getType() == null)
	    new ErrorType(as.getLeft(), "Not valid Assignment");
	as.setType(as.getLeft().getType());
	return null;
    }

    @Override
    public Object visit(BreakInstruction br, Object params) {
	return null;
    }

    @Override
    public Object visit(DefaultCase def, Object params) {
	for (Statement st : def.getBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(DoWhileStatement dow, Object params) {
	dow.getCondition().accept(this, params);
	if (!dow.getCondition().getType().isLogical())
	    new ErrorType(dow.getCondition(), "Do While condition is not logical");
	for (Statement st : dow.getBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(ForStatement forStatement, Object params) {
	if (!(forStatement.getInitilization() instanceof Assignment))
	    new ErrorType(forStatement.getInitilization(), "Initialization in For Statement is not correct");
	forStatement.getInitilization().accept(this, params);
	forStatement.getCondition().accept(this, params);
	forStatement.getIncrement().accept(this, params);
	for (Statement st : forStatement.getBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(IfStatement ifstm, Object params) {
	ifstm.getCondition().accept(this, params);
	if (!ifstm.getCondition().getType().isLogical())
	    new ErrorType(ifstm.getCondition(), "If condition is not logical");
	for (Statement st : ifstm.getIfBody())
	    st.accept(this, params);
	for (Statement st : ifstm.getElseBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(Invocation inv, Object params) {
	inv.getVar().accept(this, params);
	List<Type> list = new ArrayList<>();
	for (Expression param : inv.getParams()) {
	    param.accept(this, params);
	    list.add(param.getType());
	}
	inv.setLValue(false);
	inv.setType(inv.getVar().getType().parenthesis(list));
	if (inv.getType() == null)
	    new ErrorType(inv, "Not valid Parameters");
	return null;
    }

    @Override
    public Object visit(NormalCase normalCase, Object params) {
	normalCase.getExpression().accept(this, params);
	for (Statement st : normalCase.getBody())
	    st.accept(this, params);
	return null;
    }

    @Override
    public Object visit(Read read, Object params) {
	for (Expression exp : read.getExpressions()) {
	    exp.accept(this, params);
	    if (!exp.getLValue())
		new ErrorType(read, "LValue expected for Read");
	}
	return null;
    }

    @Override
    public Object visit(Return ret, Object params) {
	ret.getExpression().accept(this, params);
	ret.getExpression().setType(ret.getExpression().getType().promotesTo(((FunctionType) params).getReturnType()));
	if (ret.getExpression().getType() == null)
	    new ErrorType(ret, "Not valid return type");
	return null;
    }

    @Override
    public Object visit(SwitchCase switchCase, Object params) {
	int cases = 0;
	switchCase.getExpression().accept(this, params);
	for (Case c : switchCase.getCases()){
	    if(c instanceof DefaultCase)
		cases++;
	    c.accept(this, params);
	}
	if(cases>1)
	    new ErrorType(switchCase, "Switch cannot have more than one default case");
	return null;
    }

    @Override
    public Object visit(WhileStatement wstm, Object params) {
	wstm.getCondition().accept(this, params);
	if (!wstm.getCondition().getType().isLogical())
	    new ErrorType(wstm.getCondition(), "While condition is not logical");
	for (Statement st : wstm.getWhileBody())
	    st.accept(this, params);
	return null;
    }

}