package ast.expression;

import ast.type.RealType;
import ast.type.Type;
import visitor.Visitor;

public class RealLiteral implements Expression {

    public double value;
    public int line, column;
    public boolean lval;
    public Type type;

    public RealLiteral(int line, int column, double value) {
	this.line = line;
	this.column = column;
	this.value = value;
	this.type = RealType.getInstance();
    }

    public double getValue() {
	return this.value;
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
	StringBuilder sb = new StringBuilder("Real Literal: ").append(getValue());
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