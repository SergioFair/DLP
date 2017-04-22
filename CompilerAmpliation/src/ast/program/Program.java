package ast.program;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import visitor.Visitor;

public class Program implements ASTNode {

    public int line, column;
    public List<Definition> definitions;

    public Program(int line, int column, List<Definition> definition) {
	this.line = line;
	this.column = column;
	this.definitions = definition;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    public List<Definition> getDefinitions() {
	return new ArrayList<Definition>(definitions);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("Program: \n");
	for (Definition def : getDefinitions()) {
	    sb.append(def.toString()).append("\n\n");
	}
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}
