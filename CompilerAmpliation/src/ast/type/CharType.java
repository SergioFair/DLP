package ast.type;

import visitor.Visitor;

public class CharType extends AbstractType {

    private static CharType instance = new CharType();

    private CharType() {
    }

    public static CharType getInstance() {
	return instance;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("char");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    @Override
    public boolean isLogical() {
	return true;
    }

    @Override
    public Type promotesTo(Type type) {
	if (type instanceof RealType || type instanceof IntType || type instanceof CharType)
	    return type;
	else if(type instanceof ArrayType)
	    return promotesTo(((ArrayType) type).getOf());
	return null;
    }
    
    @Override
    public int numberOfBytes(){
	return 1;
    }

    @Override
    public String suffix() {
	return "b";
    }
    
    @Override
    public Type higherThan(Type type){
	return promotesTo(type)==null?this:promotesTo(type);
    }
    
    @Override
    public String toInstruction(){
	return "byte";
    }
    
    @Override
    public Type comparison(Type type){
	return higherThan(type)==null?null:IntType.getInstance();
    }

}