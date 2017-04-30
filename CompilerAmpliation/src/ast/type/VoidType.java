package ast.type;

import visitor.Visitor;

public class VoidType extends AbstractType {

    private static VoidType instance = new VoidType();
    
    private VoidType() {}
    
    public static VoidType getInstance(){
	return instance;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("void");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}