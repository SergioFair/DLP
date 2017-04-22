package ast.statement;

import ast.expression.Expression;
import visitor.Visitor;

public class Return implements Statement {

    public int line, column;
    public Expression expression;

    public Return(int line, int column, Expression exp) {
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

    public Expression getExpression() {
	return this.expression;
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("Return: return ").append(expression.toString()).append(";");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}
