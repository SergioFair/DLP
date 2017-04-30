package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Power implements Expression {
    
    public int line, column;
    public boolean lval;
    public Type type;
    public Expression right, left;
    
    public Power(int line, int column, Expression left, Expression right){
	this.line = line;
	this.column = column;
	this.left = left;
	this.right = right;
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
    
    public Expression getLeft(){
	return this.left;
    }
    
    public Expression getRight(){
	return this.right;
    }

}
