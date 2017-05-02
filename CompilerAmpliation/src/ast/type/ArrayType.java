package ast.type;

import visitor.Visitor;

public class ArrayType extends AbstractType {

    public int size;
    public Type of;
    public int line, column;

    public ArrayType(int line, int column, Type type, int size) {
	this.line = line;
	this.column = column;
	this.of = type;
	this.size = size;
    }

    public Type getOf() {
	return this.of;
    }

    public int getSize() {
	return this.size;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(getOf().toString()).append("[").append(getSize()).append("]");
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

    public static ArrayType orderArray(int line, int column, Type type, int size) {
	if (!(type instanceof ArrayType)) {
	    return new ArrayType(line, column, type, size);
	} else {
	    ArrayType oType = (ArrayType) type;
	    ArrayType apuntador = oType;
	    Type apuntado = apuntador.of;
	    while (apuntado instanceof ArrayType) {
		apuntador = (ArrayType) apuntado;
		apuntado = apuntador.of;
	    }
	    apuntador.of = new ArrayType(line, column, apuntador.of, size);
	    return oType;
	}
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    @Override
    public Type squareBrackets(Type type) {
	if (!(type instanceof IntType))
	    return null;
	return this.of;
    }

    @Override
    public int numberOfBytes() {
	return getOf().numberOfBytes() * getSize();
    }

    @Override
    public String toInstruction() {
	return "["+getSize() + "," + getOf().toInstruction()+"]";
    }

    @Override
    public String suffix() {
	return of.suffix();
    }

    @Override
    public Type promotesTo(Type type) {
	return of.promotesTo(type);
    }
}