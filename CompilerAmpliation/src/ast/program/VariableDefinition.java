package ast.program;

import ast.statement.Statement;
import ast.type.Type;
import visitor.Visitor;

public class VariableDefinition implements Definition, Statement {

    public String name;
    public Type type;
    public int line, column, offset, scope;

    public VariableDefinition(int line, int column, String name, Type type) {
	this.line = line;
	this.column = column;
	this.name = name;
	this.type = type;
    }

    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public Type getType() {
	return this.type;
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
    public String toString() {
	StringBuilder sb = new StringBuilder().append("Variable definition:").append(type.toString()).append(" ")
		.append(getName()).append(";\n");
	return sb.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	return this.getName().equals(((VariableDefinition) obj).getName());
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    public void setOffset(int offset) {
	this.offset = offset;
    }
    
    public int getOffset(){
	return offset;
    }

    @Override
    public int getScope() {
	return this.scope;
    }

    @Override
    public void setScope(int scope) {
	this.scope = scope;
    }

}
