package ast.statement;

import ast.expression.Expression;
import ast.type.Type;
import visitor.Visitor;

public class Assignment implements Statement, Expression {

    public Expression left, right;
    public int line, column;
    public Type type;
    public boolean lvalue;

    public Assignment(int line, int column, Expression left, Expression right) {
	this.line = line;
	this.column = column;
	this.left = left;
	this.right = right;
    }

    public Expression getLeft() {
	return left;
    }

    public Expression getRight() {
	return right;
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
	StringBuilder sb = new StringBuilder(getLeft().toString()).append("=").append(getRight().toString()).append(";");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    @Override
    public boolean getLValue() {
	return this.lvalue;
    }

    @Override
    public void setLValue(boolean lval) {
	this.lvalue = lval;
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
