package ast;

import visitor.Visitor;

public interface ASTNode {

    /**
     * Returns the line of the current node
     * @return line
     */
    public int getLine();

    /**
     * Returns the column of the current node
     * @return column
     */
    public int getColumn();
    
    /**
     * Needed to traverse all the tree nodes
     * @param v
     * @param params
     * @return
     */
    public Object accept(Visitor v, Object params);


}