package ast.type;

import visitor.Visitor;

public class VoidType extends AbstractType {

    public int line, column;

    public VoidType(int line, int column) {
	super(line, column);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("VoidType: void");
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

}