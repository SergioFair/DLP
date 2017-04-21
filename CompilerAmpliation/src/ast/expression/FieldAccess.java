package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class FieldAccess implements Expression {

    public int line, column;
    public String name;
    public Expression left;
    public boolean lval;
    public Type type;

    public FieldAccess(int line, int column, Expression left, String name) {
	this.line = line;
	this.column = column;
	this.left = left;
	this.name = name;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    public Expression getLeft() {
	return left;
    }

    public String getName() {
	return name;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Field access: ").append(left.toString()).append(".").append(name);
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