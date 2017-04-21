package ast.statement;

import java.util.List;

import ast.expression.Expression;
import visitor.Visitor;

public class ForStatement implements Statement {

    public int line, column;
    public Expression initialize, condition, increment;
    public List<Statement> body;
    
    public ForStatement(int line, int column, Expression first, Expression second, Expression third,
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

    public Expression getInitilization() {
	return this.initialize;
    }
    
    public Expression getCondition() {
	return this.condition;
    }
    
    public Expression getIncrement() {
	return this.increment;
    }
    
    public List<Statement> getBody() {
	return this.body;
    }

}
