package parser;
import java.util.*;

class Token {
    Token(int type, String lexeme){
        this.type = type;
        this.lexeme = lexeme;
    }
    
    Token(int type){
        this(type, null);
    }
    
    Token(){}
    
    @Override
    public String toString(){
        return tokenMap.get(type) + (lexeme == null? "" : " (" + lexeme + ")");
    }
    
   public boolean isRelop() {
        return this.type == GT_OP || this.type == GE_OP || this.type == LT_OP || this.type == LE_OP
                || this.type == ASSIGN_OP || this.type == EQ_OP;
    }
    
    public boolean isAddOp(){
        return this.type == PLUS_OP || this.type == MIN_OP;
    }
    
    public boolean isMulOp(){
        return this.type == MUL_OP || this.type == DIV_OP || this.type == MOD_OP;
    }

    int type;
    String lexeme;

    static final int
        EOI = 0,
        ID = 1,
        COMMA = 2,
        SEMICOLON = 3,
        LBRACE = 4,
        RBRACE = 5,
        LPAREN = 6,
        RPAREN = 7,
        READ = 8,
        PRINT = 9,
        INT = 10,
        FOR = 11,
        WHILE = 12,
        IF = 13,
        ELSE = 14,
        INT_LITERAL = 15,
        PROGRAM = 16,
        END = 17,
        ASSIGN_OP = 18,
        EQ_OP = 19,
        LT_OP = 20,
        LE_OP = 21,
        GT_OP = 22,
        GE_OP = 23,
        PLUS_OP = 24,
        MIN_OP = 25,
        MUL_OP = 26,
        DIV_OP = 27,
        MOD_OP = 28,
        COMMENT = 29;

static final Map<Integer, String> tokenMap = new TreeMap<Integer, String>();
    static {
        tokenMap.put(EOI,"EOI");
        tokenMap.put(ID, "ID");
        tokenMap.put(COMMA, "COMMA");
        tokenMap.put(SEMICOLON, "SEMICOLON");
        tokenMap.put(LBRACE,"LBRACE");
        tokenMap.put(RBRACE, "RBRACE");
        tokenMap.put(LPAREN, "LPAREN");
        tokenMap.put(RPAREN, "RPAREN");
        tokenMap.put(READ, "read");
        tokenMap.put(PRINT, "print");
        tokenMap.put(INT, "int");
        tokenMap.put(FOR, "for");
        tokenMap.put(WHILE, "while");
        tokenMap.put(IF, "if");
        tokenMap.put(ELSE, "else");
        tokenMap.put(INT_LITERAL, "INT_LITERAL");
        tokenMap.put(PROGRAM, "program");
        tokenMap.put(END, "end");
        tokenMap.put(ASSIGN_OP, "ASSIGN_OP");
        tokenMap.put(EQ_OP, "EQ_OP");
        tokenMap.put(LT_OP, "LT_OP");
        tokenMap.put(LE_OP, "LE_OP");
        tokenMap.put(GT_OP, "GT_OP");
        tokenMap.put(GE_OP, "GE_OP");
        tokenMap.put(PLUS_OP, "PLUS_OP");
        tokenMap.put(MIN_OP, "MIN_OP");
        tokenMap.put(MUL_OP, "MUL_OP");
        tokenMap.put(DIV_OP, "DIV_OP");
        tokenMap.put(MOD_OP, "MOD_OP");
        tokenMap.put(COMMENT, "COMMENT");
    }
    
static final Map<String,Token> keywordMap = new TreeMap<String, Token>();
    static{
        keywordMap.put("program", new Token(PROGRAM));
        keywordMap.put("int", new Token(INT));
        keywordMap.put("for", new Token(FOR));
        keywordMap.put("while", new Token(WHILE));
        keywordMap.put("if", new Token(IF));
        keywordMap.put("else", new Token(ELSE));
        keywordMap.put("print", new Token(PRINT));
        keywordMap.put("read", new Token(READ));
        keywordMap.put("end", new Token(END));
    }

}
