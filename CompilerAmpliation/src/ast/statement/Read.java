package ast.statement;

import java.util.List;

import ast.expression.Expression;
import visitor.Visitor;

public class Read implements Statement {

    public List<Expression> expressions;
    public int line, column;

    public Read(int line, int column, List<Expression> expressions) {
	this.line = line;
	this.column = column;
	this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
	return this.expressions;
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
    public String toString() {
	StringBuilder sb = new StringBuilder("read ");
	for (int i = 0; i < expressions.size(); i++) {
	    sb.append(expressions.get(i).toString());
	    if (i != expressions.size() - 1)
		sb.append(", ");
	}
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

}