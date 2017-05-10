package visitor.semantic;

import ast.expression.Expression;
import ast.expression.Variable;
import ast.program.FunctionDefinition;
import ast.program.VariableDefinition;
import ast.statement.Invocation;
import ast.statement.Statement;
import ast.type.ErrorType;
import visitor.symboltable.SymbolTable;

/**
 * Looks for definition and calling conflicts.
 * 
 * @author Sergio
 *
 */
public class IdentificationVisitor extends AbstractVisitor {

    private SymbolTable st = new SymbolTable();

    /* Definitions */

    @Override
    public Object visit(FunctionDefinition func, Object params) {
	if (!st.insert(func))
	    new ErrorType(func, "Duplicated function '" + func.getName() + "'");
	st.set();
	func.getType().accept(this, params);
	for (Statement st : func.getBody())
	    st.accept(this, params);
	st.reset();
	return null;
    }

    @Override
    public Object visit(VariableDefinition var, Object params) {
	var.getType().accept(this, params);
	if (!st.insert(var))
	    new ErrorType(var, "Duplicated variable '" + var.getName() + "'");
	return null;
    }

    /* Expressions */

    @Override
    public Object visit(Variable var, Object params) {
	var.setDefinition(st.findInCurrentScope(var.getName()));
	if (var.getDefinition() == null) {
	    var.setDefinition(st.find(var.getName()));
	    if(var.getDefinition() == null){
		var.setDefinition(new VariableDefinition(var.getLine(), var.getColumn(), var.getName(),
		    new ErrorType(var, "Variable '" + var.getName() + "' not defined")));
	    }
	}
	return null;
    }

    /* Statements */

    @Override
    public Object visit(Invocation inv, Object params) {
	inv.getVar().accept(this, params);
	if (st.find(inv.getVar().getName()) == null)
	    new ErrorType(inv, "Function '" + inv.getVar().getName() + "' not defined");
	for (Expression exp : inv.getParams())
	    exp.accept(this, params);
	return null;
    }

}
