package ast.type;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;

public class Struct extends AbstractType {

    public List<RecordField> records;
    public int line, column;

    public Struct(int line, int column, List<RecordField> records) {
	super(line, column);
	this.records = records;
    }

    public List<RecordField> getRecords() {
	return new ArrayList<>(this.records);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Struct: ");
	sb.append("{\n");
	for (RecordField record : records) {
	    sb.append("\t").append(record.toString()).append(";\n");
	}
	sb.append("}");
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
    public Type dot(String value){
	for(RecordField rf : records){
	    if(rf.getName().equals(value))
		return rf.getType();
	}
	return null;
    }
    
    @Override
    public int numberOfBytes(){
	int total = 0;
	for(RecordField rf : records){
	    total+=rf.getType().numberOfBytes();
	}
	return total;
    }
    
    @Override
    public RecordField getField(String id){
	for(RecordField rf : records)
	    if(rf.getName().equals(id))
		return rf;
	throw new IllegalStateException("Code generation was not possible");
    }
    
    public String toInstruction(){
	StringBuilder sb = new StringBuilder();
	for(RecordField rf : getRecords()){
	    sb.append("\t");
	    sb.append(rf.toInstruction());
	}
	return sb.toString();
    }

}