package ast.type;

import java.util.List;

import ast.program.VariableDefinition;
import visitor.Visitor;

public class FunctionType extends AbstractType {

    public String name;
    public List<VariableDefinition> params;
    public int line, column;
    public Type returnType;

    public FunctionType(int line, int column, String name, List<VariableDefinition> params, Type returnType) {
	this.line = line;
	this.column = column;
	this.name = name;
	this.params = params;
	this.returnType = returnType;
    }

    public String getName() {
	return this.name;
    }

    public List<VariableDefinition> getParams() {
	return params;
    }

    public Type getReturnType() {
	return returnType;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(getReturnType().toString()).append(" ")
		.append(getName()).append(" (");
	for (int i = 0; i < params.size(); i++) {
	    sb.append(params.get(i).toString());
	    if (i != params.size() - 1)
		sb.append(",");
	}
	sb.append(")");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }
    
    @Override
    public Type parenthesis(List<Type> list) {
	if(list.size()!=this.params.size()){
	    new ErrorType(params.get(0), "Incorrect number of parameters");
	    return null;
	}
	else{
	    for(int i=0;i<list.size();i++)
		if(list.get(i).promotesTo(this.params.get(i).type)==null)
		    return null;
	    return getReturnType();
	}
    }
    
    @Override
    public int numberOfBytes(){
	int total=0;
	for(VariableDefinition varDef : params)
	    total+=varDef.getType().numberOfBytes();
	return total;
    }
    
    @Override
    public int getLine(){
	return this.line;
    }
    
    @Override
    public int getColumn(){
	return this.column;
    }
}