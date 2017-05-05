package ast.statement.switchCase;

import java.util.List;

import ast.expression.Expression;
import ast.statement.Statement;
import visitor.Visitor;

public class SwitchCase implements Statement {
    
    public int line, column;
    public List<Case> cases;
    public Expression expression;
    
    public SwitchCase(int line, int column, Expression expression, List<Case> cases){
	this.line = line;
	this.column = column;
	this.expression = expression;
	this.cases = cases;
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
    
    public List<Case> getCases() {
	return this.cases;
    }

    @Override
    public Object accept(Visitor v, Object params) {
	return v.visit(this, params);
    }
    
    public String toString() {
	StringBuilder sb = new StringBuilder("switch(").append(getExpression().toString()).append(") {\n");
	for(Case c : getCases())
	    sb.append(c.toString()).append("\n");
	sb.append("}");
	return sb.toString();
    }

}
