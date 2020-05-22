grammar Chocopy;

tokens { INDENT, DEDENT }

@lexer::members {
  // A queue where extra tokens are pushed on (see the NEWLINE lexer rule).
  private java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();
  // The stack that keeps track of the indentation level.
  private java.util.Stack<Integer> indents = new java.util.Stack<>();
  // The amount of opened braces, brackets and parenthesis.
  private int opened = 0;
  // The most recently produced token.
  private Token lastToken = null;
  @Override
  public void emit(Token t) {
    super.setToken(t);
    tokens.offer(t);
  }

  @Override
  public Token nextToken() {
    // Check if the end-of-file is ahead and there are still some DEDENTS expected.
    if (_input.LA(1) == EOF && !this.indents.isEmpty()) {
      // Remove any trailing EOF tokens from our buffer.
      for (int i = tokens.size() - 1; i >= 0; i--) {
        if (tokens.get(i).getType() == EOF) {
          tokens.remove(i);
        }
      }

      // First emit an extra line break that serves as the end of the statement.
      this.emit(commonToken(Python3Parser.NEWLINE, "\n"));

      // Now emit as much DEDENT tokens as needed.
      while (!indents.isEmpty()) {
        this.emit(createDedent());
        indents.pop();
      }

      // Put the EOF back on the token stream.
      this.emit(commonToken(Python3Parser.EOF, "<EOF>"));
    }

    Token next = super.nextToken();

    if (next.getChannel() == Token.DEFAULT_CHANNEL) {
      // Keep track of the last token on the default channel.
      this.lastToken = next;
    }

    return tokens.isEmpty() ? next : tokens.poll();
  }

  private Token createDedent() {
    CommonToken dedent = commonToken(Python3Parser.DEDENT, "");
    dedent.setLine(this.lastToken.getLine());
    return dedent;
  }

  private CommonToken commonToken(int type, String text) {
    int stop = this.getCharIndex() - 1;
    int start = text.isEmpty() ? stop : stop - text.length() + 1;
    return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
  }

  // Calculates the indentation of the provided spaces, taking the
  // following rules into account:
  //
  // "Tabs are replaced (from left to right) by one to eight spaces
  //  such that the total number of characters up to and including
  //  the replacement is a multiple of eight [...]"
  //
  //  -- https://docs.python.org/3.1/reference/lexical_analysis.html#indentation
  static int getIndentationCount(String spaces) {
    int count = 0;
    for (char ch : spaces.toCharArray()) {
      switch (ch) {
        case '\t':
          count += 8 - (count % 8);
          break;
        default:
          // A normal space char.
          count++;
      }
    }

    return count;
  }

  boolean atStartOfInput() {
    return super.getCharPositionInLine() == 0 && super.getLine() == 1;
  }
}


//Sintaxis
program : (var_def | func_def | class_def)* stmt*;

class_def : CLASS ID PAR_IZQ ID PAR_DER DOS_PUNTOS NEWLINE INDENT class_body DEDENT;

class_body : PASS NEWLINE
            | (var_def | func_def)+;

func_def : DEF ID PAR_IZQ (typed_var (COMA typed_var)*)? PAR_DER (EJECUTA type)? DOS_PUNTOS NEWLINE INDENT func_body DEDENT;

func_body : (global_decl | nonlocal_decl | var_def | func_def)* stmt+;

typed_var : ID DOS_PUNTOS type;

type : ID | STRING | COR_IZQ type COR_DER;   // TODO PENDIENTE EL IDSTRING

global_decl : GLOBAL ID NEWLINE;

nonlocal_decl : NONLOCAL ID NEWLINE;

var_def : typed_var ASIG literal NEWLINE;

stmt : simple_stmt NEWLINE
        | IF expr DOS_PUNTOS block (ELIF expr DOS_PUNTOS block)* (ELSE DOS_PUNTOS block)?
        | WHILE expr DOS_PUNTOS block
        | FOR ID IN expr DOS_PUNTOS block;

simple_stmt : PASS
            | expr
            | RETURN (expr)?
            | (target ASIG)+ expr;

block : NEWLINE INDENT stmt+ DEDENT;

literal : (NONE
        | TRUE
        | FALSE
        | INTEGER
        | STRING);

//expr : cexpr
//        | NOT expr
//        | expr (AND | OR) expr
//        | expr IF expr ELSE expr;
//
//cexpr : ID
//        | literal
//        | COR_IZQ (expr (COMA expr )* )? COR_DER
//        | PAR_IZQ expr PAR_DER
//        | cexpr PUNTO ID
//        | cexpr COR_IZQ expr COR_DER
//        | cexpr PUNTO ID PAR_IZQ (expr (COMA expr )* )? PAR_DER
//        | ID PAR_IZQ (expr (COMA expr )* )? PAR_DER
//        | cexpr bin_op cexpr
//        | MENOS cexpr;
//
//bin_op : SUMA
//        | MENOS
//        | MULTIPLICACION
//        | DIVISION
//        | MODULO
//        | IGUAL
//        | DIFERENTE
//        | MENOR_IGUAL
//        | MAYOR_IGUAL
//        | MENOR
//        | MAYOR
//        | IS;
expr: expr_p2 expr_aux;

expr_aux: IF expr ELSE expr_p2 expr_aux
        |/*epsilon*/;

expr_p2: expr_p3 expr_p2_aux;

expr_p2_aux: OR expr_p3 expr_p2_aux
            |/*epsilon*/;

expr_p3: expr_p4 expr_p3_aux;

expr_p3_aux: AND expr_p4 expr_p3_aux
            |/*epsilon*/;

expr_p4: NOT expr_p4
        |cexpr;

cexpr: cexpr_p6 cexpr_aux;

cexpr_aux: bin_op_log cexpr_p6 cexpr_aux
            |/*epsilon*/;

bin_op_log: IGUAL
            |DIFERENTE
            |MAYOR
            |MENOR
            |MAYOR_IGUAL
            |MENOR_IGUAL
            |IS;

cexpr_p6: cexpr_p7 cexpr_p6_aux;

cexpr_p6_aux: bin_op_p6 cexpr_p7 cexpr_p6_aux
              |/*epsilon*/;

bin_op_p6: SUMA
            |MENOS;

cexpr_p7: cexpr_p8 cexpr_p7_aux;

cexpr_p7_aux: bin_op_p7 cexpr_p8 cexpr_p7_aux
            |/*epsilon*/;

bin_op_p7: MULTIPLICACION
            |DIVISION
            |MODULO;

cexpr_p8: MENOS cexpr_p8
            |cexpr_p9;

cexpr_p9: cexpr_p10 cexpr_p9_aux;

cexpr_p9_aux: PUNTO ID cexpr_p10_aux cexpr_p9_aux
            | COR_IZQ expr COR_DER cexpr_p9_aux
            |/*epsilon*/;

expr_list_no_req_cor:expr expr_list_0_more_cor
                    |/*epsilon*/;

expr_list_0_more_cor: COMA expr expr_list_0_more_cor
                    |/*epsilon*/;

cexpr_p10: ID cexpr_p10_aux
        |literal
        |COR_IZQ expr_list_no_req_cor COR_DER
        |PAR_IZQ expr PAR_DER
        |LEN PAR_IZQ cexpr PAR_DER
        |SELF;// TODO NO ESTOY SEGURO DE SELF

cexpr_p10_aux: PAR_IZQ expr_list_no_req PAR_DER
            |/*epsilon*/;

expr_list_no_req: expr expr_list_0_more
                |/*epsilon*/;

expr_list_0_more: COMA expr expr_list_0_more
                |/*epsilon*/;

member_expr : cexpr PUNTO ID;

index_expr : cexpr COR_IZQ expr COR_DER;

target : ID
        | member_expr
        | index_expr;


n:ASIG|IGUAL;
// TOKEN

CLASS: 'class';



PAR_IZQ: '(';

PAR_DER: ')';

DOS_PUNTOS: ':';

NEWLINE
 : ( {atStartOfInput()}?   SPACES
   | ( '\r'? '\n' | '\r' | '\f' ) SPACES?
   )
   {
     String newLine = getText().replaceAll("[^\r\n\f]+", "");
     String spaces = getText().replaceAll("[\r\n\f]+", "");

     // Strip newlines inside open clauses except if we are near EOF. We keep NEWLINEs near EOF to
     // satisfy the final newline needed by the single_put rule used by the REPL.
     int next = _input.LA(1);
     int nextnext = _input.LA(2);
     if (opened > 0 || (nextnext != -1 && (next == '\r' || next == '\n' || next == '\f' || next == '#'))) {
       // If we're inside a list or on a blank line, ignore all indents,
       // dedents and line breaks.
       skip();
     }
     else {
       emit(commonToken(NEWLINE, newLine));
       int indent = getIndentationCount(spaces);
       int previous = indents.isEmpty() ? 0 : indents.peek();
       if (indent == previous) {
         // skip indents of the same size as the present indent-size
         skip();
       }
       else if (indent > previous) {
         indents.push(indent);
         emit(commonToken(Python3Parser.INDENT, spaces));
       }
       else {
         // Possibly emit more than 1 DEDENT token.
         while(!indents.isEmpty() && indents.peek() > indent) {
           this.emit(createDedent());
           indents.pop();
         }
       }
     }
   }
 ;

//NEWLINE: '\n'; //TODO ESTA UNA MIERDA x3
//
//INDENT: ' '+; //TODO ESTA UNA MIERDA
//
//DEDENT: '  '+; //TODO ESTA UNA MIERDA x2

PASS:'pass';

DEF:'def';

COMA: ',';

EJECUTA: '->';

STRING: ('"' ([ -!#-[\]-~] | '\\"' | '\\n' | '\\t' | '\\\\')* '"');

COR_IZQ: '[';

COR_DER: ']';

GLOBAL: 'global';

NONLOCAL: 'nonlocal';

ASIG:'=';

IF:'if';

ELIF:'elif';

ELSE:'else';

WHILE:'while';

FOR:'for';

IN:'in';

RETURN:'return';

NONE : 'None';

TRUE: 'True';

FALSE:'False';

INTEGER:[1-9][0-9]*; //TODO FALTA EL LIMITE SUPERIOR

NOT:'not';

AND:'and';

OR:'or';

PUNTO:'.';

MENOS:'-';

SUMA:'+';

MULTIPLICACION:'*';

DIVISION:'//';

MODULO:'%';

IGUAL:'==';






DIFERENTE:'!=';

MENOR_IGUAL:'<=';

MAYOR_IGUAL:'>=';

MENOR:'<';

MAYOR:'>';

IS:'is';

LEN:'len';

SELF:'self';

ID: ([A-Z]|[a-z]|'_')([0-9]|[a-z]|[A-Z]|'_')*;

SKIP_
 : ( SPACES | COMMENT | LINE_JOINING ) -> skip
 ;

fragment SPACES
 : [ \t]+
 ;

//TODO FALTA PRINT