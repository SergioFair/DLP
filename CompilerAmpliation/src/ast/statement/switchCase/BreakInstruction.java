package ast.statement.switchCase;

import ast.statement.Statement;
import visitor.Visitor;

public class BreakInstruction implements Statement {
    
    public int line, column;
    
    public BreakInstruction(int line, int column) {
	this.line = line;
	this.column = column;
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
    public String toString(){
	StringBuilder sb = new StringBuilder("break");
	return sb.toString();
    }

}
