package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Logical implements Expression {

	public int line, column;
	public Expression left, right;
	public String operator;
	public boolean lval;
	public Type type;

	public Logical(int line, int column, Expression left, String operator,
			Expression right) {
		this.line = line;
		this.column = column;
		this.left = left;
		this.right = right;
		this.operator = operator;
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

	public Expression getRight() {
		return right;
	}

	public String getOperator() {
		return this.operator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Logical: ")
				.append(left.toString()).append(operator)
				.append(right.toString());
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