package ast.statement.switchCase;

import java.util.List;

import ast.statement.Statement;

public interface Case extends Statement {
    
    public List<Statement> getBody();

}
