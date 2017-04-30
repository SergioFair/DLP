package ast.type;

import visitor.Visitor;

public class RecordField extends AbstractType {

    public String name;
    public int line, column, offset;
    public Type type;

    public RecordField(int line, int column, Type type, String name) {
	this.line = line;
	this.column = column;
	this.name = name;
	this.type = type;
    }

    public String getName() {
	return this.name;
    }

    public Type getType() {
	return this.type;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(type.toString()).append(" ").append(getName());
	return sb.toString();
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

    @Override
    public int numberOfBytes() {
	return getType().numberOfBytes();
    }

    public int getOffset() {
	return this.offset;
    }

    public void setOffset(int offset) {
	this.offset = offset;
    }

    @Override
    public String toInstruction() {
	return getName() + ":" + getType().toInstruction() + "\n";
    }

}
