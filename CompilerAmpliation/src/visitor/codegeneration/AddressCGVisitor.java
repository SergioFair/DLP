package visitor.codegeneration;

import visitor.Visitor;
import ast.expression.FieldAccess;
import ast.expression.Indexing;
import ast.expression.Variable;
import ast.program.VariableDefinition;
import ast.type.ArrayType;
import ast.type.IntType;

public class AddressCGVisitor extends AbstractCGVisitor {

    private Visitor valueVisitor;

    @Override
    public Object visit(Variable var, Object params) {
	VariableDefinition def = (VariableDefinition) var.getDefinition();
	if (def.getScope() == 0)
	    CodeGenerator.getInstance().pusha(def.getOffset());
	else {
	    CodeGenerator.getInstance().pushabp();
	    CodeGenerator.getInstance().push(IntType.getInstance(), def.getOffset());
	    CodeGenerator.getInstance().add(IntType.getInstance());
	}
	return null;
    }

    @Override
    public Object visit(FieldAccess field, Object params) {
	field.getLeft().accept(this, params);
	CodeGenerator.getInstance().push(IntType.getInstance(),
		field.getLeft().getType().getField(field.getName()).getOffset());
	CodeGenerator.getInstance().add(IntType.getInstance());
	return null;
    }

    @Override
    public Object visit(Indexing ind, Object params) {
	ind.getLeft().accept(this, params);
	ind.getRight().accept(valueVisitor, params);
	CodeGenerator.getInstance().convertTo(ind.getRight().getType(), IntType.getInstance());
	CodeGenerator.getInstance().push(IntType.getInstance(),
		((ArrayType) ind.getLeft().getType()).getOf().numberOfBytes());
	CodeGenerator.getInstance().mul(IntType.getInstance());
	CodeGenerator.getInstance().add(IntType.getInstance());
	return null;
    }

    void setVisitor(Visitor visitor) {
	this.valueVisitor = visitor;
    }

}
