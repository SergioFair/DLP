package visitor.symboltable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.program.Definition;

/**
 * Class used to store variable and function definitions and also
 * their scope.
 * @author Sergio
 *
 */
public class SymbolTable {

    private int scope = 0;
    private List<Map<String, Definition>> table;

    public SymbolTable() {
	table = new ArrayList<Map<String, Definition>>();
	table.add(new HashMap<String, Definition>());
    }

    /**
     * Increments the scope and creates a new table for the new scope.
     */
    public void set() {
	table.add(new HashMap<String, Definition>());
	scope++;
    }

    /**
     * Removes the last scope table and decrements it.
     */
    public void reset() {
	table.remove(scope);
	scope--;
    }

    /**
     * Inserts the definition inside the current table if it wasn't added.
     * @param definition
     * @return true if added, false otherwise
     */
    public boolean insert(Definition definition) {
	Map<String, Definition> map = table.get(scope);
	boolean result = false;
	if (!map.containsKey(definition.getName())) {
	    map.put(definition.getName(), definition);
	    definition.setScope(scope);
	    result = true;
	}
	return result;
    }

    /**
     * Looks for the Definition with the corresponding name
     * in every scope.
     * @param name
     * @return definition if found, null otherwise
     */
    public Definition find(String name) {
	Definition def = null;
	for (int i = scope; i >= 0; i--) {
	    if (table.get(i).containsKey(name))
		def = table.get(i).get(name);
	}
	return def;
    }

    /**
     * Looks for the definition with the corresponding name
     * in the current scope.
     * @param name
     * @return definition if found, null otherwise
     */
    public Definition findInCurrentScope(String name) {
	Map<String, Definition> map = table.get(scope);
	return map.get(name);
    }
    
    /**
     * Getter for the scope attribute.
     * @return scope
     */
    public int getScope(){
	return this.scope;
    }
}
