package visitor.semantic;

import ast.program.Definition;
import ast.program.FunctionDefinition;
import ast.program.Program;
import ast.program.VariableDefinition;
import ast.statement.Statement;
import ast.type.FunctionType;
import ast.type.RecordField;
import ast.type.Struct;

public class OffsetVisitor extends AbstractVisitor {

    @Override
    public Object visit(Program p, Object o) {
	int offset = 0;
	for (Definition d : p.getDefinitions()) {
	    d.accept(this, o);
	    if (d instanceof VariableDefinition) {
		((VariableDefinition) d).setOffset(offset);
		offset += d.getType().numberOfBytes();
	    }
	}
	return null;
    }

    @Override
    public Object visit(FunctionDefinition f, Object o) {
	f.getType().accept(this, o);

	int offset = 0;
	for (Statement s : f.getBody()) {
	    s.accept(this, o);
	    if (s instanceof VariableDefinition) {
		offset -= ((VariableDefinition) s).getType().numberOfBytes();
		((VariableDefinition) s).setOffset(offset);
	    }
	}

	return null;
    }

    @Override
    public Object visit(FunctionType f, Object o) {
	f.getReturnType().accept(this, o);

	int offset = 4;
	for (int i = f.getParams().size() - 1; i >= 0; i--) {
	    VariableDefinition parameter = f.getParams().get(i);
	    parameter.accept(this, o);
	    parameter.setOffset(offset);
	    offset += parameter.getType().numberOfBytes();
	}
	return null;
    }

    @Override
    public Object visit(Struct s, Object o) {
	int offset = 0;
	for (RecordField r : s.getRecords()) {
	    r.accept(this, o);
	    r.setOffset(offset);
	    offset += r.getType().numberOfBytes();
	}
	return null;
    }

}