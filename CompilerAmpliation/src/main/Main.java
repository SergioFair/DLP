package main;

import java.io.FileReader;
import java.io.IOException;

import ast.program.Program;
import error_handler.ErrorHandler;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import parser.Parser;
import scanner.Scanner;
import visitor.Visitor;
import visitor.semantic.IdentificationVisitor;
import visitor.semantic.OffsetVisitor;
import visitor.semantic.TypeCheckingVisitor;

public class Main {

    public static void main(String args[]) throws IOException {
	if (args.length < 1) {
	    System.err.println("Pass me the name of the input file.");
	    return;
	}

	FileReader fr = null;
	try {
	    fr = new FileReader(args[0]);
	} catch (IOException io) {
	    System.err.println("The file " + args[0] + " could not be opened.");
	    return;
	}

	Scanner scanner = new Scanner(fr);
	Parser parser = new Parser(scanner);
	parser.run();
	
	Visitor visitor = new IdentificationVisitor();
	visitor.visit((Program) parser.getAST(), null);
	ErrorHandler.getInstance().showError(System.err);
	visitor = new TypeCheckingVisitor();
	visitor.visit((Program) parser.getAST(), null);
	ErrorHandler.getInstance().showError(System.err);
	visitor = new OffsetVisitor();
	visitor.visit((Program) parser.getAST(), null);
	ErrorHandler.getInstance().showError(System.err);

	IntrospectorModel modelo = new IntrospectorModel("Program", parser.getAST());
	new IntrospectorTree("Introspector", modelo);
    }
}