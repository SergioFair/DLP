package ast.type;

import java.util.List;

public abstract class AbstractType implements Type {
    
    private int line, column;
    
    public AbstractType(){}
    
    public AbstractType(int line, int column){
	this.line = line;
	this.column = column;
    }

    @Override
    public Type arithmetic(Type type) {
	return null;
    }

    @Override
    public Type arithmetic() {
	return null;
    }
    
    @Override
    public Type comparison(Type type) {
	return null;
    }
    
    @Override
    public boolean isLogical() {
	return false;
    }

    @Override
    public Type logical(Type type) {
	return null;
    }

    @Override
    public Type promotesTo(Type type) {
	return null;
    }

    @Override
    public Type parenthesis(List<Type> list) {
	return null;
    }

    @Override
    public Type squareBrackets(Type type) {
	return null;
    }

    @Override
    public Type dot(String field) {
	return null;
    }
    
    @Override
    public int getLine(){
	return this.line;
    }
    
    @Override
    public int getColumn(){
	return this.column;
    }
    
    @Override
    public int numberOfBytes(){
	return 0;
    }
    
    @Override
    public String suffix(){
	throw new IllegalStateException("Code generation was not possible");
    }
    
    @Override
    public Type higherThan(Type type){
	throw new IllegalStateException("Code generation was not possible");
    }
    
    @Override
    public RecordField getField(String id){
	throw new IllegalStateException("Code generation was not possible");
    }
    
    @Override
    public String toInstruction(){
	throw new IllegalStateException("Code generation was not possible");
    }
}