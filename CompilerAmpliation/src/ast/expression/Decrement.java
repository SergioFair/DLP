package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Decrement implements Incrementable {

    public int line, column;
    public Expression expression;
    public Type type;
    public boolean lvalue;

    public Decrement(int line, int column, Expression exp) {
	this.line = line;
	this.column = column;
	this.expression = exp;
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

    public Expression getExpression() {
	return this.expression;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Decrement: ").append(expression.toString()).append("--");
	return sb.toString();
    }

}
