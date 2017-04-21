package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class UnaryNot implements Expression {

	public int line, column;
	public Expression expression;
	public boolean lval;
	public Type type;

	public UnaryNot(int line, int column, Expression expression) {
		this.line = line;
		this.column = column;
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
		StringBuilder sb = new StringBuilder("Unary not: ").append("!").append(
				expression);
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