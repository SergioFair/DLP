package ast.statement;

import java.util.List;

import visitor.Visitor;
import ast.expression.Expression;

public class DoWhileStatement implements Statement {

	public int line, column;
	public List<Statement> body;
	public Expression condition;

	public DoWhileStatement(int line, int column, List<Statement> body,
			Expression condition) {
		this.line = line;
		this.column = column;
		this.body = body;
		this.condition = condition;
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
		v.visit(this, params);
		return null;
	}

	public List<Statement> getBody() {
		return this.body;
	}

	public Expression getCondition() {
		return this.condition;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Do while: do ");
		sb.append(" {\n");
		for (Statement st : getBody())
			sb.append("\t").append(st.toString()).append("\n");
		sb.append("}");

		return sb.toString();
	}

}
