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
         this.emit(commonToken(ChocopyParser.NEWLINE, "\n"));

         // Now emit as much DEDENT tokens as needed.
         while (!indents.isEmpty()) {
           this.emit(createDedent());
           indents.pop();
         }

         // Put the EOF back on the token stream.
         this.emit(commonToken(ChocopyParser.EOF, "<EOF>"));
       }

       Token next = super.nextToken();

       if (next.getChannel() == Token.DEFAULT_CHANNEL) {
         // Keep track of the last token on the default channel.
         this.lastToken = next;
       }

       return tokens.isEmpty() ? next : tokens.poll();
     }

     private Token createDedent() {
       CommonToken dedent = commonToken(ChocopyParser.DEDENT, "");
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

class_body : PASS NEWLINE #class_body_pass
            | (var_def | func_def)+ #class_body_var_func
            ;

func_def : DEF ID PAR_IZQ ((typed_var) (COMA typed_var)*)? PAR_DER (EJECUTA type)? DOS_PUNTOS NEWLINE INDENT func_body DEDENT;

func_body : (global_decl | nonlocal_decl | var_def | func_def)* stmt+;

typed_var : ID DOS_PUNTOS type  #typed_var_id
            | SELF DOS_PUNTOS type #typed_var_self
            ;

type : ID #type_id
        | STRING #type_string
        | COR_IZQ type COR_DER #type_cor_izq
        ;   // TODO PENDIENTE EL IDSTRING

global_decl : GLOBAL ID NEWLINE;

nonlocal_decl : NONLOCAL ID NEWLINE;

var_def : typed_var ASIG literal NEWLINE;

stmt : simple_stmt (NEWLINE)? #stmt_simple_stmt
        | IF expr DOS_PUNTOS block (ELIF expr DOS_PUNTOS block)* (ELSE DOS_PUNTOS block)? #stmt_if
        | WHILE expr DOS_PUNTOS block #stmt_while
        | FOR ID IN expr DOS_PUNTOS block #stmt_for
        ;

simple_stmt : PASS #simple_stmt_pass
            | expr #simple_stmt_expr
            | RETURN (expr)? #simple_stmt_return
            | (target ASIG)+ expr #simple_stmt_asig
            | stmtprint #simple_stmt_print
            ;

stmtprint : PRINT PAR_IZQ expr PAR_DER;

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

expr_aux: IF expr ELSE expr_p2 expr_aux #expr_aux_if
        |/*epsilon*/ #expr_aux_eps;

expr_p2: expr_p3 expr_p2_aux;

expr_p2_aux: OR expr_p3 expr_p2_aux #expr_p2_aux_or
            |/*epsilon*/ #expr_p2_aux_eps
            ;

expr_p3: expr_p4 expr_p3_aux;

expr_p3_aux: AND expr_p4 expr_p3_aux #expr_p3_aux_and
            |/*epsilon*/ #expr_p3_aux_eps
            ;

expr_p4: NOT expr_p4 #expr_p4_not
        |cexpr #expr_p4_cexpr
        ;

cexpr: cexpr_p6 cexpr_aux;

cexpr_aux: bin_op_log cexpr_p6 cexpr_aux #cexpr_aux_bin
            |/*epsilon*/ #cexpr_aux_eps
            ;

bin_op_log: IGUAL #bin_op_log_igual
            |DIFERENTE #bin_op_log_diferente
            |MAYOR #bin_op_log_mayor
            |MENOR #bin_op_log_menor
            |MAYOR_IGUAL #bin_op_log_mayor_igual
            |MENOR_IGUAL #bin_op_log_menor_igual
            |IS #bin_op_log_is
            ;

cexpr_p6: cexpr_p7 cexpr_p6_aux;

cexpr_p6_aux: bin_op_p6 cexpr_p7 cexpr_p6_aux #cexpr_p6_aux_bin
              |/*epsilon*/ #cexpr_p6_aux_eps
              ;

bin_op_p6: SUMA #bin_op_p6_suma
            |MENOS #bin_op_p6_menos
            ;

cexpr_p7: cexpr_p8 cexpr_p7_aux;

cexpr_p7_aux: bin_op_p7 cexpr_p8 cexpr_p7_aux #cexpr_p7_aux_bin
            |/*epsilon*/ #cexpr_p7_aux_eps
            ;

bin_op_p7: MULTIPLICACION #bin_op_p7_mult
            |DIVISION #bin_op_p7_div
            |MODULO #bin_op_p7_mod
            ;

cexpr_p8: MENOS cexpr_p8 #cexpr_p8_menos
            |cexpr_p9 #cexpr_p8_cexpr_p9
            ;

cexpr_p9: cexpr_p10 cexpr_p9_aux;

cexpr_p9_aux: PUNTO ID cexpr_p10_aux cexpr_p9_aux #cexpr_p9_aux_punto
            | COR_IZQ expr COR_DER cexpr_p9_aux #cexpr_p9_aux_cor_izq
            |/*epsilon*/ #cexpr_p9_aux_eps
            ;

expr_list_no_req_cor:expr expr_list_0_more_cor #expr_list_no_req_cor_expr
                    |/*epsilon*/ #expr_list_no_req_cor_eps;

expr_list_0_more_cor: COMA expr expr_list_0_more_cor #expr_list_0_more_cor_coma
                    |/*epsilon*/ #expr_list_0_more_cor_eps;

cexpr_p10: ID cexpr_p10_aux #cexpr_p10_id
        |literal #cexpr_p10_literal
        |COR_IZQ expr_list_no_req_cor COR_DER #cexpr_p10_cor
        |PAR_IZQ expr PAR_DER #cexpr_p10_par
        |LEN PAR_IZQ cexpr PAR_DER #cexpr_p10_len
        |SELF #cexpr_p10_self
        ;// TODO NO ESTOY SEGURO DE SELF

cexpr_p10_aux: PAR_IZQ expr_list_no_req PAR_DER #cexpr_p10_aux_par
            |/*epsilon*/ #cexpr_p10_aux_eps
            ;

expr_list_no_req: expr expr_list_0_more #expr_list_no_req_expr
                |/*epsilon*/ #expr_list_no_req_eps
                ;

expr_list_0_more: COMA expr expr_list_0_more #expr_list_0_more_coma
                |/*epsilon*/ #expr_list_0_more_eps
                ;

member_expr : cexpr PUNTO ID #member_expr_cexpr
              |SELF PUNTO ID #member_expr_self
              ;


index_expr : cexpr COR_IZQ expr COR_DER #index_expr_cexpr
            |SELF COR_IZQ expr COR_DER #index_expr_self
            ;

target : ID #target_id
        |SELF #target_self
        | member_expr #target_member
        | index_expr #target_index_expr
        ;

n: ASIG #n_asig
    |IGUAL #n_igual
    ;
// TOKEN

SKIP_
 : ( SPACES | COMMENT | LINE_JOINING) -> skip
 ;

COMMENT
 : '#' ~[\r\n\f]*
 ;

SPACES
 : [ \t]+
 ;

LINE_JOINING
 : '\\' SPACES? ( '\r'? '\n' | '\r' )
 ;

CLASS: 'class';

PRINT: 'print';

PAR_IZQ: '(';

PAR_DER: ')';

DOS_PUNTOS: ':';

NEWLINE
    : ( {atStartOfInput()}?   SPACES
      | ( '\r'? '\n' | '\r' ) SPACES?
      )
      {
        String newLine = getText().replaceAll("[^\r\n]+", "");
        String spaces = getText().replaceAll("[\r\n]+", "");
        int next = _input.LA(1);
        if (opened > 0 || next == '\r' || next == '\n' || next == '#') {
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
            emit(commonToken(ChocopyParser.INDENT, spaces));
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

INTEGER: [1-9][0-9]*|'0' ; //TODO FALTA EL LIMITE SUPERIOR

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

//TODO FALTA PRINT