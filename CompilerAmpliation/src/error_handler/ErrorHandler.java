package error_handler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.type.ErrorType;

/**
 * Class implementing the Singleton design pattern used to
 * store the errors than can be found in the inputs and to
 * show them at the end of the execution.
 * @author Sergio
 *
 */
public class ErrorHandler {

    private List<ErrorType> errors = new ArrayList<>();
    private static ErrorHandler instance = new ErrorHandler();

    private ErrorHandler() {
    }

    public static ErrorHandler getInstance() {
	return instance;
    }

    public boolean anyError() {
	return errors.size() > 0;
    }

    public void addError(ErrorType error) {
	this.errors.add(error);
    }

    public void showError(PrintStream out) {
	if (anyError()) {
	    for (ErrorType err : errors)
		out.println(err.toString());
	    System.exit(0);
	}
	errors = new ArrayList<>();
    }

}
