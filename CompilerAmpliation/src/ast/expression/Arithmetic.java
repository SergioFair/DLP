package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Arithmetic implements Expression {

    public String operator;
    public Expression left, right;
    public int line, column;
    public boolean lval;
    public Type type;

    public Arithmetic(int line, int column, Expression left, String operator, Expression right) {
	this.line = line;
	this.column = column;
	this.left = left;
	this.right = right;
	this.operator = operator;
    }

    public String getOperator() {
	return this.operator;
    }

    public Expression getLeft() {
	return this.left;
    }

    public Expression getRight() {
	return this.right;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Arithmetic: ");
	sb.append(left.toString()).append(operator).append(right.toString());
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    @Override
    public boolean getLValue() {
	return this.lval;
    }

    @Override
    public void setLValue(boolean lval) {
	this.lval = lval;
    }

    @Override
    public void setType(Type type) {
	this.type = type;
    }

    @Override
    public Type getType() {
	return this.type;
    }

}
