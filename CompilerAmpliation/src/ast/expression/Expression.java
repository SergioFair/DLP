package ast.expression;

import ast.ASTNode;
import ast.type.Type;

public interface Expression extends ASTNode {
    
    /* The LValue of an expression is a boolean value used to indicate if an
     * expression can be at the left side of an assignmente or not. And it is
     * also used to indicate if an expression can be used by a Read statement. */

    /**
     * Returns the lvalue of the expression.
     * @return lvalue
     */
    public boolean getLValue();

    /**
     * Sets a new value for the lvalue of the expresion.
     * @param lval
     */
    public void setLValue(boolean lval);

    /**
     * Sets a new value for the type of the expresion.
     * @param type
     */
    public void setType(Type type);

    /**
     * Returns the type of the expression.
     * @return type
     */
    public Type getType();

}