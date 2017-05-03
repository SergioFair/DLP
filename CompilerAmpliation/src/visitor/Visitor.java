package visitor;

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
import ast.expression.Ternary;
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

public interface Visitor {

    /* Program package */

    public Object visit(FunctionDefinition func, Object params);

    public Object visit(VariableDefinition var, Object params);

    public Object visit(Program program, Object params);

    /* Expression package */

    public Object visit(Arithmetic ar, Object params);

    public Object visit(Cast cast, Object params);

    public Object visit(CharLiteral ch, Object params);

    public Object visit(Comparison compar, Object params);

    public Object visit(FieldAccess field, Object params);

    public Object visit(Indexing ind, Object params);

    public Object visit(IntLiteral lit, Object params);

    public Object visit(Logical log, Object params);

    public Object visit(RealLiteral real, Object params);

    public Object visit(UnaryMinus minus, Object params);

    public Object visit(UnaryNot not, Object params);

    public Object visit(Variable var, Object params);

    /* Statement package */

    public Object visit(Assignment as, Object params);

    public Object visit(IfStatement ifst, Object params);

    public Object visit(Invocation inv, Object params);

    public Object visit(Read read, Object params);

    public Object visit(Return ret, Object params);

    public Object visit(WhileStatement whst, Object params);

    public Object visit(Write wr, Object params);

    /* Type package */

    public Object visit(ArrayType at, Object params);

    public Object visit(CharType ct, Object params);

    public Object visit(ErrorType et, Object params);

    public Object visit(FunctionType ft, Object params);

    public Object visit(IntType it, Object params);

    public Object visit(RealType rt, Object params);

    public Object visit(RecordField rf, Object params);

    public Object visit(Struct st, Object params);

    public Object visit(VoidType vt, Object params);

    public Object visit(ForStatement forStatement, Object params);

    public Object visit(DoWhileStatement doWhileStatement, Object params);

    public Object visit(Power power, Object params);

    public Object visit(Ternary ternary, Object params);
}
