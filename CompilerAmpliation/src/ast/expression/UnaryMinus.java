package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class UnaryMinus implements Expression {

	public Expression expression;
	public int line, column;
	public boolean lval;
	public Type type;

	public UnaryMinus(int line, int column, Expression expression) {
		this.line = line;
		this.column = column;
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
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
		StringBuilder sb = new StringBuilder("Unary Minus: ");
		sb.append("-").append(expression.toString());
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