package ast.type;

import visitor.Visitor;

public class IntType extends AbstractType {

    private static IntType instance = new IntType();
    
    private IntType() {}
    
    public static IntType getInstance(){
	return instance;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append("IntType: ").append("int");
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
    public Type logical(Type type){
	if(type instanceof CharType || type instanceof IntType)
	    return this;
	return null;
    }
    
    @Override
    public Type promotesTo(Type type){
	if(type instanceof RealType || type instanceof IntType)
	    return type;
	else if(type instanceof ArrayType)
	    return promotesTo(((ArrayType) type).getOf());
	return null;
    }
    
    @Override
    public Type arithmetic(Type type) {
	if(type instanceof CharType)
	    return this;
	else if(type instanceof IntType || type instanceof RealType)
	    return type;
	else
	    return null;
    }
    
    @Override
    public Type arithmetic(){
	return this;
    }
    
    @Override
    public int numberOfBytes(){
	return 2;
    }

    @Override
    public String suffix() {
	return "i";
    }
    
    @Override
    public Type higherThan(Type type){
	return promotesTo(type)==null?this:promotesTo(type);
    }
    
    @Override
    public String toInstruction(){
	return "int";
    }
    
    @Override
    public Type comparison(Type type){
	return higherThan(type);
    }
    
    @Override
    public Type increment() {
	return this;
    }

}