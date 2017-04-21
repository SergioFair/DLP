package ast.type;

import visitor.Visitor;

public class RealType extends AbstractType {

    private static RealType instance = new RealType();
    
    private RealType(){}
    
    public static RealType getInstance(){
	return instance;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("RealType: ").append("double");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }
    
    @Override
    public Type promotesTo(Type type){
	if(type instanceof RealType)
	    return type;
	else if(type instanceof ArrayType)
	    return promotesTo(((ArrayType) type).getOf());
	return null;
    }
    
    @Override
    public int numberOfBytes(){
	return 4;
    }

    @Override
    public String suffix() {
	return "f";
    }
    
    @Override
    public Type higherThan(Type type){
	return promotesTo(type)==null?this:promotesTo(type);
    }
    
    @Override
    public String toInstruction(){
	return "float";
    }
    
    @Override
    public Type comparison(Type type){
	return higherThan(type);
    }
    
    @Override
    public Type arithmetic(Type type) {
	if(type instanceof CharType || type instanceof IntType || type instanceof RealType)
	    return this;
	else
	    return null;
    }
    
    @Override
    public Type increment() {
	return this;
    }

}