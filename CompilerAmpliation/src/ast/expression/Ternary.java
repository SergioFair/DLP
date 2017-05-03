package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Ternary implements Expression {

    public int line, column;
    public Expression condition, first, second;
    public boolean lvalue;
    public Type type;
    
    public Ternary(int line, int column, Expression condition, Expression first, Expression second){
	this.line = line;
	this.column = column;
	this.condition = condition;
	this.first = first;
	this.second = second;
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
	return v.visit(this, params);
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
    
    public Expression getCondition() {
	return this.condition;
    }
    
    public Expression getFirst() {
	return this.first;
    }
    
    public Expression getSecond() {
	return this.second;
    }
    
    @Override
    public String toString(){
	StringBuilder sb = new StringBuilder(getCondition().toString())
			.append(" ? ").append(getFirst().toString())
			.append(" : ").append(getSecond().toString())
			.append(";");
	return sb.toString();
    }
    
}