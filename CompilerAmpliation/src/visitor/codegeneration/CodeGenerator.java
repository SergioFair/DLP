package visitor.codegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ast.expression.Arithmetic;
import ast.program.VariableDefinition;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.IntType;
import ast.type.RealType;
import ast.type.Struct;
import ast.type.Type;

public class CodeGenerator {

    private static FileWriter output;
    private static CodeGenerator instance = null;
    private int labels = 1;

    private CodeGenerator() {
    }

    public static CodeGenerator getInstance() {
	if (instance == null) {
	    instance = new CodeGenerator();
	    try {
		output = new FileWriter(new File("prueba.txt"));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return instance;
    }

    public int getLabels(int howMany) {
	int temp = this.labels;
	this.labels += howMany;
	return temp;
    }

    public FileWriter getOutput() {
	return output;
    }

    /* Integer, float and byte */

    public void push(Type t, double value) {
	try {
	    output.write("push" + t.suffix() + " " + value + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void push(Type t, int value) {
	try {
	    output.write("push" + t.suffix() + " " + value + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void store(Type t) {
	try {
	    output.write("store" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void load(Type t) {
	try {
	    output.write("load" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void dup(Type t) {
	try {
	    output.write("dup" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void pop(Type t) {
	try {
	    output.write("pop" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void in(Type t) {
	try {
	    output.write("in" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void out(Type t) {
	try {
	    output.write("out" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /* Just integer and float */

    public void add(Type t) {
	try {
	    output.write("add" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void sub(Type t) {
	try {
	    output.write("sub" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void mul(Type t) {
	try {
	    output.write("mul" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void div(Type t) {
	try {
	    output.write("div" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void gt(Type t) {
	try {
	    output.write("gt" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void lt(Type t) {
	try {
	    output.write("lt" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void ge(Type t) {
	try {
	    output.write("ge" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void le(Type t) {
	try {
	    output.write("le" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void eq(Type t) {
	try {
	    output.write("eq" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void ne(Type t) {
	try {
	    output.write("ne" + t.suffix() + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /* Without suffix */

    public void pusha(int address) {
	try {
	    output.write("pusha " + address + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void pushabp() {
	try {
	    output.write("pusha bp\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void mod() {
	try {
	    output.write("mod\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void and() {
	try {
	    output.write("and\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void or() {
	try {
	    output.write("or\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void not() {
	try {
	    output.write("not\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void i2b() {
	try {
	    output.write("i2b\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void b2i() {
	try {
	    output.write("b2i\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void f2i() {
	try {
	    output.write("f2i\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void i2f() {
	try {
	    output.write("i2f\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void call(int label) {
	try {
	    output.write("call label" + label + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void call(String label) {
	try {
	    output.write("call " + label + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void callMain() {
	try {
	    output.write("call main\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void jmp(int label) {
	try {
	    output.write("jmp label" + label + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void jz(int label) {
	try {
	    output.write("jz label" + label + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void jnz(int label) {
	try {
	    output.write("jnz label" + label + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void halt() {
	try {
	    output.write("halt\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void ret(int retType, int localVars, int params) {
	try {
	    output.write("ret " + retType + "," + localVars + "," + params + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void enter(int value) {
	try {
	    output.write("enter " + value + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void convertTo(Type current, Type next) {
	if (!current.getClass().equals(next.getClass())) {
	    if (current instanceof CharType) {
		if (next instanceof IntType)
		    b2i();
		else if(next instanceof RealType){
		    b2i();
		    i2f();
		}
		else
		    throw new IllegalStateException("Code generation was not possible");
	    } else if (current instanceof IntType) {
		if (next instanceof CharType)
		    i2b();
		else if (next instanceof RealType)
		    i2f();
		else
		    throw new IllegalStateException("Code generation was not possible");
	    } else if (current instanceof RealType) {
		if (next instanceof IntType)
		    f2i();
		else if(next instanceof CharType) {
		    f2i();
		    i2b();
		}
		else
		    throw new IllegalStateException("Code generation was not possible");
	    } else if (current instanceof ArrayType) {
		convertTo(((ArrayType) current).getOf(), next);
	    } else
		throw new IllegalStateException("Code generation was not possible.");
	}
    }

    public void label(int label) {
	try {
	    output.write("label" + label + ":\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void label(String label) {
	try {
	    output.write(label + ":\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void arithmetic(Arithmetic ar) {
	switch (ar.getOperator()) {
	case "+":
	    add(ar.getType());
	    break;
	case "-":
	    sub(ar.getType());
	    break;
	case "*":
	    mul(ar.getType());
	    break;
	case "/":
	    div(ar.getType());
	    break;
	case "%":
	    mod();
	    break;
	default:
	    throw new IllegalStateException("Code generation was not possible");
	}
    }

    public void logical(String operator) {
	switch (operator) {
	case "&&":
	    and();
	    break;
	case "||":
	    or();
	    break;
	default:
	    throw new IllegalStateException("Code generation was not possible");
	}
    }

    public void comparison(Type type, String operator) {
	switch (operator) {
	case "==":
	    eq(type);
	    break;
	case "!=":
	    ne(type);
	    break;
	case ">=":
	    ge(type);
	    break;
	case ">":
	    gt(type);
	    break;
	case "<=":
	    le(type);
	    break;
	case "<":
	    lt(type);
	    break;
	default:
	    throw new IllegalStateException("Code generation was not possible");
	}
    }

    public void globalVar(VariableDefinition var) {
	try {
	    if (!(var.getType() instanceof Struct)) {
		output.write("#var " + var.getName() + ":" + var.getType().toInstruction() + "\n");
		output.flush();
	    } else {
		output.write("#type " + var.getName() + ": {\n" + ((Struct) var.getType()).toInstruction() + "}\n");
		output.flush();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void parameter(VariableDefinition var) {
	try {
	    if (!(var.getType() instanceof Struct)) {
		output.write("#param " + var.getName() + ":" + var.getType().toInstruction() + "\n");
		output.flush();
	    } else {
		output.write("#type " + var.getName() + ": {\n" + ((Struct) var.getType()).toInstruction() + "}\n");
		output.flush();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void localVar(VariableDefinition var) {
	try {
	    if (!(var.getType() instanceof Struct)) {
		output.write("#local " + var.getName() + ":" + var.getType().toInstruction() + "\n");
		output.flush();
	    } else {
		output.write("#type " + var.getName() + ": {\n" + ((Struct) var.getType()).toInstruction() + "}\n");
		output.flush();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void newLine() {
	try {
	    output.write("\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void writeLine(int line) {
	try {
	    output.write("\n#line " + line + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void comment(String string) {
	try {
	    output.write("' * " + string + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void functionDefinition(String string) {
	try {
	    output.write("#func " + string + "\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void source(String input) {
	String[] route = input.split("/");
	try {
	    output.write("#source \"" + route[route.length - 1] + "\"\n");
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void arithmeticAssignment(String operator, Type type) {
	switch (operator) {
	case "+=":
	    add(type);
	    break;
	case "-=":
	    sub(type);
	    break;
	case "*=":
	    mul(type);
	    break;
	case "/=":
	    div(type);
	    break;
	default:
	    throw new IllegalStateException("Code generation was not possible");
	}
    }
}