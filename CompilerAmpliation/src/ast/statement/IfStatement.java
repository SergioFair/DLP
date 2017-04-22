package ast.statement;

import java.util.List;

import ast.expression.Expression;
import visitor.Visitor;

public class IfStatement implements Statement {

    public int line, column;
    public List<Statement> ifBody, elseBody;
    public Expression condition;

    public IfStatement(int line, int column, List<Statement> ifBody, List<Statement> elseBody, Expression condition) {
	this.line = line;
	this.column = column;
	this.ifBody = ifBody;
	this.elseBody = elseBody;
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

    public List<Statement> getIfBody() {
	return this.ifBody;
    }

    public List<Statement> getElseBody() {
	return this.elseBody;
    }

    public Expression getCondition() {
	return condition;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("IfStatement: if(").append(condition.toString()).append(") {\n");
	for (int i = 0; i < ifBody.size(); i++) {
	    sb.append("\t").append(ifBody.get(i));
	}
	sb.append("}\n");
	if (!elseBody.isEmpty()) {
	    sb.append("else {\n");
	    for (int i = 0; i < elseBody.size(); i++)
		sb.append("\t").append(elseBody.get(i)).append("\n");
	    sb.append("}");
	}
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}