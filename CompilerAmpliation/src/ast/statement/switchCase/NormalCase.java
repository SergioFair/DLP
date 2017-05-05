package ast.statement.switchCase;

import java.util.List;

import ast.expression.Expression;
import ast.statement.Statement;
import visitor.Visitor;

public class NormalCase implements Case {

    public int line, column;
    public List<Statement> body;
    public Expression expression;

    public NormalCase(int line, int column, Expression expression, List<Statement> body) {
	this.line = line;
	this.column = column;
	this.expression = expression;
	this.body = body;
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
    public Object accept(Visitor v, Object params) {
	return v.visit(this, params);
    }

    @Override
    public List<Statement> getBody() {
	return this.body;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder("case ").append(expression.toString()).append(":\n");
	for(Statement st : getBody())
	    sb.append("\t").append(st.toString()).append(";\n");
	return sb.toString();
    }

}
