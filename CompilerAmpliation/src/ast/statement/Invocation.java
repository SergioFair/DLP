package ast.statement;

import java.util.List;

import ast.expression.Expression;
import ast.expression.Variable;
import ast.type.Type;
import visitor.Visitor;

public class Invocation implements Statement, Expression {

    public int line, column;
    public List<Expression> params;
    public Variable var;
    public boolean lval;
    public Type type;

    public Invocation(int line, int column, List<Expression> params, Variable var) {
	this.line = line;
	this.column = column;
	this.params = params;
	this.var = var;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    public List<Expression> getParams() {
	return params;
    }

    public Variable getVar() {
	return var;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Invocation: ").append(var.getName()).append("(");
	for (int i = 0; i < params.size(); i++) {
	    sb.append(params.get(i).toString());
	    if (i != params.size() - 1)
		sb.append(",");
	}
	sb.append(");");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    @Override
    public boolean getLValue() {
	return this.lval;
    }

    @Override
    public void setLValue(boolean lval) {
	this.lval = lval;
    }

	@Override
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public Type getType() {
		return this.type;
	}

}