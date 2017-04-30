package ast.statement;

import java.util.List;

import ast.expression.Expression;
import visitor.Visitor;

public class ForStatement implements Statement {

    public int line, column;
    public Expression condition;
    public Statement initialize;
    public Statement increment;
    public List<Statement> body;
    
    public ForStatement(int line, int column, Statement first, Expression second, Statement third,
	    List<Statement> body) {
	this.line = line;
	this.column = column;
	this.initialize = first;
	this.condition = second;
	this.increment = third;
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
	v.visit(this, params);
	return null;
    }

    public Statement getInitilization() {
	return this.initialize;
    }
    
    public Expression getCondition() {
	return this.condition;
    }
    
    public Statement getIncrement() {
	return this.increment;
    }
    
    public List<Statement> getBody() {
	return this.body;
    }
    
    @Override
    public String toString(){
	StringBuilder sb = new StringBuilder("for ( ")
		.append(getInitilization().toString()).append(" ")
		.append(getCondition().toString()).append(" ; ")
		.append(getIncrement().toString()).append(" )");
	if(!body.isEmpty()){
	    if(body.size()>1){
		sb.append(" {\n");
		for(Statement st : getBody())
		    sb.append("  ").append(st.toString()).append(";\n");
		sb.append("}\n");
	    }
	    else{
		for(Statement st : getBody())
		    sb.append("\n").append("\t").append(st.toString()).append(";");
	    }
	}
	return sb.toString();
    }
}