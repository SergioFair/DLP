package ast.type;

import java.util.List;

import ast.ASTNode;

public interface Type extends ASTNode {

    /**
     * Checks whether a Type is logical or not
     * @return true if logical, false otherwise.
     */
    public boolean isLogical();

    /**
     * Checks if it is possible to execute an arithmetic operation between
     * the calling type and the one passed as a parameter.
     * @param type
     * @return 
     */
    public Type arithmetic(Type type);

    /**
     * Checks if it is possible to execute a unary arithmetic operation
     * with the type passed as a parameter.
     * @return
     */
    public Type arithmetic();

    /**
     * Checks if it is possible to do a comparison operation between
     * the calling type and the one passed as a parameter.
     * @param type
     * @return
     */
    public Type comparison(Type type);

    /**
     * Checks if it is possible to do a logical operation between
     * the calling type and the one passed as a parameter.
     * @param type
     * @return
     */
    public Type logical(Type type);

    /**
     * Checks if it is possible to promote a type to the one
     * passed as a parameter.
     * @param type
     * @return
     */
    public Type promotesTo(Type type);

    /**
     * Checks if the type of the parameters passed in a function
     * invocation match the required ones.
     * @param list
     * @return
     */
    public Type parenthesis(List<Type> list);

    /**
     * Checks whether the access to an array is valid or not.
     * @param type
     * @return
     */
    public Type squareBrackets(Type type);

    /**
     * Checks whether a field access operation is valid or not.
     * @param field
     * @return
     */
    public Type dot(String field);
    
    /**
     * Returns the size of the current type in bytes.
     * @return size.
     */
    public int numberOfBytes();
    
    /**
     * Returns a suffix depending on the type.
     * 'i' if int, 'f' if double, 'b' if char.
     * @return suffix for type.
     */
    public String suffix();

    /**
     * Checks whether a type is higher than the one
     * passed as a parameter, if so we return the first
     * type, otherwise we return the second type.
     * @param type
     * @return the higher type.
     */
    public Type higherThan(Type type);
    
    /**
     * Returns the RecordField associated to the given
     * id when a field is accessed or null if the id is
     * not recognized.
     * @param id
     * @return RecordField or null.
     */
    public RecordField getField(String id);

    /**
     * Returns a String representing the type.
     * @return
     */
    public String toInstruction();

}