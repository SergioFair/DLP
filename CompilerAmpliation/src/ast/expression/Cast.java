package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Cast implements Expression {

    public int line, column;
    public Type type;
    public Expression expression;
    public boolean lval;

    public Cast(int line, int column, Type type, Expression expression) {
	this.line = line;
	this.column = column;
	this.type = type;
	this.expression = expression;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    public Expression getExpression() {
	return this.expression;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("(").append(type.toString()).append(")")
		.append(expression.toString());
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
