package visitor.codegeneration;

import ast.expression.Arithmetic;
import ast.expression.Cast;
import ast.expression.CharLiteral;
import ast.expression.Comparison;
import ast.expression.FieldAccess;
import ast.expression.Indexing;
import ast.expression.IntLiteral;
import ast.expression.Logical;
import ast.expression.Power;
import ast.expression.RealLiteral;
import ast.expression.UnaryMinus;
import ast.expression.UnaryNot;
import ast.expression.Variable;
import ast.program.FunctionDefinition;
import ast.program.Program;
import ast.program.VariableDefinition;
import ast.statement.Assignment;
import ast.statement.DoWhileStatement;
import ast.statement.ForStatement;
import ast.statement.IfStatement;
import ast.statement.Invocation;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.WhileStatement;
import ast.statement.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.RecordField;
import ast.type.Struct;
import ast.type.VoidType;
import visitor.Visitor;

public abstract class AbstractCGVisitor implements Visitor {

    // Program

    @Override
    public Object visit(FunctionDefinition func, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Program program, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }
    
    @Override
    public Object visit(VariableDefinition var, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    // Expressions

    @Override
    public Object visit(Arithmetic ar, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Cast cast, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(CharLiteral ch, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Comparison compar, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(FieldAccess field, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Indexing ind, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(IntLiteral lit, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Logical log, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }
    
    @Override
    public Object visit(Power pow, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(RealLiteral real, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(UnaryMinus minus, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(UnaryNot not, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Variable var, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    // Statements

    @Override
    public Object visit(Assignment as, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(DoWhileStatement dowhile, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(ForStatement forStatement, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(IfStatement ifst, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Invocation inv, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Read read, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Return ret, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(WhileStatement whst, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Write wr, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    // Types

    @Override
    public Object visit(ArrayType at, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(CharType ct, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(ErrorType et, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(FunctionType ft, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(IntType it, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(RealType rt, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(RecordField rf, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(Struct st, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

    @Override
    public Object visit(VoidType vt, Object params) {
	throw new IllegalArgumentException("Code generation was not possible");
    }

}
