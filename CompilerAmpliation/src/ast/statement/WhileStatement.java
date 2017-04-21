package ast.statement;

import java.util.List;

import ast.expression.Expression;
import visitor.Visitor;

public class WhileStatement implements Statement {

    public int line, column;
    public List<Statement> whileBody;
    public Expression condition;

    public WhileStatement(int line, int column, List<Statement> body, Expression condition) {
	this.line = line;
	this.column = column;
	this.whileBody = body;
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

    public List<Statement> getWhileBody() {
	return this.whileBody;
    }

    public Expression getCondition() {
	return condition;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("While: while (").append(condition.toString()).append(") {\n");
	for (int i = 0; i < whileBody.size(); i++) {
	    sb.append("\t").append(whileBody.get(i));
	}
	sb.append("}\n");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}