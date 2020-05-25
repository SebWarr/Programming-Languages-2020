// import de librerias de runtime de ANTLR

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            // crear un analizador léxico que se alimenta a partir de la entrada (archivo  o consola)
            ChocopyLexer lexer;
            if (args.length>0)
                lexer = new ChocopyLexer(CharStreams.fromFileName(args[0]));
            else
                lexer = new ChocopyLexer(CharStreams.fromStream(System.in));
            // Identificar al analizador léxico como fuente de tokens para el sintactico
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // Crear el analizador sintáctico que se alimenta a partir del buffer de tokens
            ChocopyParser parser = new ChocopyParser(tokens);
            ParseTree tree = parser.program(); // comienza el análisis en la regla inicial
            System.out.println(tree.toStringTree(parser)); // imprime el árbol en forma textual
            MyVisitor<Object> loader = new MyVisitor<Object>();
            loader.visit(tree);
        } catch (Exception e){
            System.err.println("Error (Test): " + e);
        }
    }
}


