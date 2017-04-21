package ast.expression;

import ast.program.Definition;
import ast.type.Type;
import visitor.Visitor;

public class Variable implements Expression {

    public int line, column;
    public String name;
    public boolean lval;
    public Definition def;
    public Type type;

    public Variable(int line, int column, String name) {
	this.line = line;
	this.column = column;
	this.name = name;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    public String getName() {
	return this.name;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Variable: ").append(getName());
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
	return this.getName().equals(((Variable) obj).getName());
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    @Override
    public boolean getLValue() {
	return this.lval;
    }

    @Override
    public void setLValue(boolean lval) {
	this.lval = lval;
    }

    public void setDefinition(Definition def) {
	this.def = def;
    }

    public Definition getDefinition() {
	return this.def;
    }

    @Override
    public void setType(Type type) {
	this.type = type;
    }

    @Override
    public Type getType() {
	return this.type;
    }

}