package ast.expression;

import ast.statement.Statement;
import ast.type.Type;
import visitor.Visitor;

public class ArithmeticAssignment implements Expression, Statement {
    
    public int line, column;
    public Type type;
    public String operator;
    public Expression left, right;
    public boolean lvalue;
    
    public ArithmeticAssignment(int line, int column, Expression left, String operator, Expression right) {
	this.line = line;
	this.column = column;
	this.operator = operator;
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

    public Expression getLeft() {
	return this.left;
    }
    
    public Expression getRight() {
	return this.right;
    }
    
    @Override
    public String toString(){
	StringBuilder sb = new StringBuilder("ArithmeticAssignment: ");
	sb.append(left.toString()).append(operator).append(right.toString());
	return sb.toString();
    }

    public String getOperator() {
	return this.operator;
    }

}
