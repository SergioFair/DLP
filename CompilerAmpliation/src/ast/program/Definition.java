package ast.program;

import ast.ASTNode;
import ast.type.Type;

public interface Definition extends ASTNode {

    /**
     * Returns the name of the definition.
     * @return name
     */
    public String getName();

    /**
     * Returns the type of the definition.
     * @return type
     */
    public Type getType();
    
    /**
     * Returns the scope of the definition.
     * @return scope
     */
    public int getScope();
    
    /**
     * Sets a new value for the scope of the definition.
     * @param scope
     */
    public void setScope(int scope);
}