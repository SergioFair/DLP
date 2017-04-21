package ast.expression;

import ast.type.Type;
import visitor.Visitor;

public class Indexing implements Expression {

	public int line, column;
	public Expression left, right;
	public boolean lval;
	public Type type;

	public Indexing(int line, int column, Expression left, Expression right) {
		this.line = line;
		this.column = column;
		this.right = right;
		this.left = left;
	}

	@Override
	public int getLine() {
		return this.line;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	public Expression getRight() {
		return this.right;
	}

	public Expression getLeft() {
		return this.left;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Indexing: ")
				.append(left.toString()).append("[").append(right.toString())
				.append("]");
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