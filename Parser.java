package parser;
import java.io.*;

class Parser extends Lexer {
        
        Parser(){}
    
	Parser(Lexer lexer) throws IOException, ParserException {
		this.lexer = lexer;
	}

	void parse()  throws IOException, ParserException {
		program();
		match(Token.EOI);
	}
		
	void program() throws IOException, ParserException {
                while(optMatch(Token.COMMENT)){}
                
                match(Token.PROGRAM);
		while (optMatch(Token.INT)) 
			declaration();
		while (!optMatch(Token.END)) 
			statement();
	}

        void declaration() throws IOException, ParserException {
            match(Token.ID);
            while(optMatch(Token.COMMA))
                match(Token.ID);
            match(Token.SEMICOLON);
            while (optMatch(Token.COMMENT)) {}
        }
        
	void statement() throws IOException, ParserException {
		if (optMatch(Token.ID)) 
			assignmentStatement();
		else if (optMatch(Token.IF))
			ifStatement();
                else if (optMatch(Token.WHILE))
                        whileStatement();
                else if (optMatch(Token.FOR))
                        forStatement();
                else if (optMatch(Token.PRINT))
                        printStatement();
                else if (optMatch(Token.READ))
                        readStatement();
                else if (optMatch(Token.LBRACE))
                        compoundStatement();
                else if (lexer.token().type == Token.COMMENT)
                        lexer.next();
		else
			throw new ParserException(lexer, "Expecting statement, found " + lexer.token());
	}

	void assignmentStatement() throws IOException, ParserException {
		match(Token.ASSIGN_OP);
		expression();
		match(Token.SEMICOLON);
	}

	void ifStatement() throws IOException, ParserException {
                match(Token.LPAREN);
                    expression();
		if(!lexer.token().isRelop())
                    throw new ParserException(lexer, "Expecting relational, found " + lexer.token());
                
                    lexer.next();
                    expression();
                    match(Token.RPAREN);
                    statement();
                    
                    if(optMatch(Token.ELSE)){
                        if(optMatch(Token.LBRACE)){
                            compoundStatement();
                        }
                        else
                            statement();                            
                    }
	}

        void whileStatement() throws IOException, ParserException {  
            match(Token.LPAREN);
                expression();
            if(!lexer.token().isRelop())
                throw new ParserException(lexer, "Expecting relational, found " + lexer.token());
            
                lexer.next();
                expression();
                match(Token.RPAREN);            
                statement();
            
        }
        
        void forStatement() throws IOException, ParserException {
            match(Token.LPAREN);
            match(Token.ID);
            match(Token.ASSIGN_OP);
            expression();
            match(Token.COMMA);
            expression();
            match(Token.RPAREN);
            statement();
        }
  
        void printStatement() throws IOException, ParserException {
            expression();
            match(Token.SEMICOLON);
        }
        
        void readStatement() throws IOException, ParserException {
            match(Token.ID);
            match(Token.SEMICOLON);
        }
        
        void compoundStatement() throws IOException, ParserException {
            statement();
            while(!optMatch(Token.RBRACE))
                statement();
        }
        
	void expression() throws IOException, ParserException {
		term();
		while (lexer.token().isAddOp()){
                    lexer.next();
                    term();
                }
	}
	
        void term() throws IOException, ParserException {
                factor();
                while(lexer.token().isMulOp()){
                    lexer.next();
                    factor();
                }
        }
        
	void factor() throws IOException, ParserException {
		if (optMatch(Token.ID)) 
                    return;
                else if(optMatch(Token.LPAREN))
                    expression();
                else if(optMatch(Token.RPAREN))
                    return;
                else if(optMatch(Token.INT_LITERAL))
                        return;
		else
                    throw new ParserException(lexer, "Expecting factor, found " + lexer.token());
	}

	boolean optMatch(int tokenType) throws IOException {
		if (lexer.token().type == tokenType) {
			lexer.next();
			return true;
		}
		return false;
	}

	void match(int tokenType) throws IOException, ParserException {
		if (!optMatch(tokenType)) throw new ParserException(lexer, "Expecting '" + new Token(tokenType) + "', found " + lexer.token());
	}

	Lexer lexer;
}
