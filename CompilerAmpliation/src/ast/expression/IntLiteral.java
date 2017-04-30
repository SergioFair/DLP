package ast.expression;

import ast.type.IntType;
import ast.type.Type;
import visitor.Visitor;

public class IntLiteral implements Expression {

    public int value;
    public int line, column;
    public boolean lval;
    public Type type;

    public IntLiteral(int line, int column, int value) {
	this.line = line;
	this.column = column;
	this.value = value;
	this.type = IntType.getInstance();
    }

    public int getValue() {
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
	StringBuilder sb = new StringBuilder().append(getValue());
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