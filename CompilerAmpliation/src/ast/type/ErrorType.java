package ast.type;

import ast.ASTNode;
import error_handler.ErrorHandler;
import visitor.Visitor;

public class ErrorType extends AbstractType {

    public int line, column;
    public String message;

    public ErrorType(int line, int column, String message) {
	this.line = line;
	this.column = column;
	this.message = message;
	ErrorHandler.getInstance().addError(this);
    }

    public ErrorType(ASTNode node, String message) {
	this(node.getLine(), node.getColumn(), message);
    }

    public String getMessage() {
	return this.message;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Error: ").append("[").append(getMessage()).append("]")
		.append(" in line (").append(getLine()).append(") ").append("column (").append(getColumn()).append(")");
	return sb.toString();
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
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

}