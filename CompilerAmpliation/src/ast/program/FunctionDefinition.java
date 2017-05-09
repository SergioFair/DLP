package ast.program;

import java.util.ArrayList;
import java.util.List;

import ast.statement.Statement;
import ast.type.FunctionType;
import ast.type.Type;
import visitor.Visitor;

public class FunctionDefinition implements Definition {

    public String name;
    public Type type;
    public List<Statement> body;
    public int line, column, scope;

    public FunctionDefinition(int line, int column, Type type, List<Statement> body) {
	this.line = line;
	this.column = column;
	this.type = type;
	this.name = ((FunctionType) this.type).getName();
	this.body = body;
    }

    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public Type getType() {
	return this.type;
    }

    public List<Statement> getBody() {
	return new ArrayList<Statement>(this.body);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder().append(getType().toString()).append(" {\n");
	for (Statement stm : getBody()) {
	    sb.append("  ").append(stm.toString()).append("\n");
	}
	sb.append("}\n");
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
    public int getScope() {
	return this.scope;
    }

    @Override
    public void setScope(int scope) {
	this.scope = scope;
    }

    public int calculateParamsBytes() {
	int bytesParams = 0;
	for (VariableDefinition varDef : ((FunctionType) getType()).getParams())
	    if (varDef.isReference())
		bytesParams += 2;
	    else
		bytesParams += varDef.getType().numberOfBytes();
	return bytesParams;
    }

    public int calculateLocalsBytes() {
	int bytesLocals = 0;
	for (Statement st : getBody())
	    if (st instanceof VariableDefinition)
		bytesLocals += ((VariableDefinition) st).getType().numberOfBytes();
	return bytesLocals;
    }

    public int calculateReturnBytes() {
	return ((FunctionType) getType()).getReturnType().numberOfBytes();
    }

}
