package visitor.codegeneration;

import ast.expression.Arithmetic;
import ast.expression.Cast;
import ast.expression.CharLiteral;
import ast.expression.Comparison;
import ast.expression.Expression;
import ast.expression.FieldAccess;
import ast.expression.Indexing;
import ast.expression.IntLiteral;
import ast.expression.Logical;
import ast.expression.RealLiteral;
import ast.expression.Ternary;
import ast.expression.UnaryMinus;
import ast.expression.UnaryNot;
import ast.expression.Variable;
import ast.program.VariableDefinition;
import ast.statement.Assignment;
import ast.statement.Invocation;
import ast.type.IntType;
import visitor.Visitor;

public class ValueCGVisitor extends AbstractCGVisitor {

    private Visitor addrVisitor;

    @Override
    public Object visit(Arithmetic ar, Object params) {
	ar.getLeft().accept(this, params);
	CodeGenerator.getInstance().convertTo(ar.getLeft().getType(), ar.getType());
	ar.getRight().accept(this, params);
	CodeGenerator.getInstance().convertTo(ar.getRight().getType(), ar.getType());
	CodeGenerator.getInstance().arithmetic(ar);
	return null;
    }

    @Override
    public Object visit(Assignment as, Object params) {
	as.accept(addrVisitor, params);
	CodeGenerator.getInstance().load(as.getLeft().getType());
	return null;
    }

    @Override
    public Object visit(Cast cast, Object params) {
	cast.getExpression().accept(this, params);
	CodeGenerator.getInstance().convertTo(cast.getExpression().getType(), cast.getType());
	return null;
    }

    @Override
    public Object visit(CharLiteral ch, Object params) {
	CodeGenerator.getInstance().push(ch.getType(), ch.getValue());
	return null;
    }

    @Override
    public Object visit(Comparison compar, Object params) {
	compar.getLeft().accept(this, params);
	CodeGenerator.getInstance().convertTo(compar.getLeft().getType(),
		compar.getLeft().getType().higherThan(compar.getRight().getType()));
	compar.getRight().accept(this, params);
	CodeGenerator.getInstance().convertTo(compar.getRight().getType(),
		compar.getLeft().getType().higherThan(compar.getRight().getType()));
	CodeGenerator.getInstance().comparison(compar.getLeft().getType().higherThan(compar.getRight().getType()),
		compar.getOperator());
	return null;
    }

    @Override
    public Object visit(FieldAccess field, Object params) {
	field.accept(addrVisitor, params);
	CodeGenerator.getInstance().load(field.getType());
	return null;
    }

    @Override
    public Object visit(Indexing ind, Object params) {
	ind.accept(addrVisitor, params);
	CodeGenerator.getInstance().load(ind.getType());
	return null;
    }

    @Override
    public Object visit(IntLiteral lit, Object params) {
	CodeGenerator.getInstance().push(lit.getType(), lit.getValue());
	return null;
    }

    @Override
    public Object visit(Invocation inv, Object params) {
	for (Expression exp : inv.getParams())
	    exp.accept(this, params);
	CodeGenerator.getInstance().call(inv.getVar().getName());
	return null;
    }

    @Override
    public Object visit(Logical log, Object params) {
	log.getLeft().accept(this, params);
	CodeGenerator.getInstance().convertTo(log.getLeft().getType(), IntType.getInstance());
	log.getRight().accept(this, params);
	CodeGenerator.getInstance().convertTo(log.getRight().getType(), IntType.getInstance());
	CodeGenerator.getInstance().logical(log.getOperator());
	return null;
    }

    // @Override
    // public Object visit(Power pow, Object params) {
    // pow.getLeft().accept(this, params);
    // return null;
    // }

    @Override
    public Object visit(RealLiteral real, Object params) {
	CodeGenerator.getInstance().push(real.getType(), real.getValue());
	return null;
    }

    @Override
    public Object visit(Ternary ter, Object params) {
	int labelNumber = CodeGenerator.getInstance().getLabels(2);
	ter.getCondition().accept(this, params);
	CodeGenerator.getInstance().jz(labelNumber);
	ter.getFirst().accept(this, params);
	CodeGenerator.getInstance().convertTo(ter.getFirst().getType(),ter.getType());
	CodeGenerator.getInstance().jmp(labelNumber + 1);
	CodeGenerator.getInstance().label(labelNumber);
	ter.getSecond().accept(this, params);
	CodeGenerator.getInstance().convertTo(ter.getSecond().getType(),ter.getType());
	CodeGenerator.getInstance().label(labelNumber + 1);
	return null;
    }

    @Override
    public Object visit(UnaryMinus minus, Object params) {
	CodeGenerator.getInstance().push(IntType.getInstance(), 0);
	CodeGenerator.getInstance().convertTo(IntType.getInstance(), minus.getExpression().getType());
	minus.getExpression().accept(this, params);
	CodeGenerator.getInstance().sub(minus.getType());
	return null;
    }

    @Override
    public Object visit(UnaryNot not, Object params) {
	not.getExpression().accept(this, params);
	CodeGenerator.getInstance().not();
	return null;
    }

    @Override
    public Object visit(Variable var, Object params) {
	var.accept(addrVisitor, params);
	if(!((VariableDefinition) var.getDefinition()).isReference())
	    CodeGenerator.getInstance().load(var.getType());
	return null;
    }

    void setAddressVisitor(AddressCGVisitor visitor) {
	this.addrVisitor = visitor;
    }
}
