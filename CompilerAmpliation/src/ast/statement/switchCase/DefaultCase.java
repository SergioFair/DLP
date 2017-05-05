package ast.statement.switchCase;

import java.util.List;

import ast.statement.Statement;
import visitor.Visitor;

public class DefaultCase implements Case {
    
    public int line, column;
    public List<Statement> body;

    public DefaultCase(int line, int column, List<Statement> body) {
	this.line = line;
	this.column = column;
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

    @Override
    public Object accept(Visitor v, Object params) {
	return v.visit(this, params);
    }

    @Override
    public List<Statement> getBody() {
	return this.body;
    }
    
    public String toString() {
	StringBuilder sb = new StringBuilder("default:\n");
	for(Statement st : getBody())
	    sb.append("\t").append(st.toString()).append(";\n");
	return sb.toString();
    }

}
