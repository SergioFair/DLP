package ast.statement;

import ast.expression.Expression;
import visitor.Visitor;

public class Assignment implements Statement {

    public Expression left, right;
    public int line, column;

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
	StringBuilder sb = new StringBuilder().append("Assignment: ").append(getLeft().toString())
		.append("=").append(getRight().toString());
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}
