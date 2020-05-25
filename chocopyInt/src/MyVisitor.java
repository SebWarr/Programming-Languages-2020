import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyVisitor<T> extends ChocopyBaseVisitor<T> {
    //🐍 Epicness
    HashMap<String, String> table = new HashMap<>();

    public String[] getTypeValue(String key) {
        //USAR SABIAMENTE: *NOOOO* USAR SI NO SE HA VERIFICADO QUE LA LLAVE ESTÁ EN EL MAPA
        String tp = table.get(key);
        String[] typeValue = tp.split("¿");
        /*Idea: guardar variables de este tipo en el mapa : llave : "x", valor :"int¿6"*/
        /*Este método retorna un string[] que en su primera posición (0) tiene el tipo y en la segunda (1) tiene el valor*/
        return typeValue;
    }


    public boolean parseBoolean(String s, int line, int col) {
        boolean booleano1 = false;
        if (s.toLowerCase().equals("true") || s.toLowerCase().equals("false")) {
            booleano1 = Boolean.parseBoolean(s);
        } else {
            System.err.printf("<%d, %d> Error Semantico, expresión " + s + " no es booleano", line, col);
            System.exit(-1);
        }
        return booleano1;
    }

    /*
    * <String,String>
       Funcion que devuelve el valor de las variables
       "x","int:6"
        String st = table.get(x)
        -> "int¿6"
            str_tv [] = st.split("¿")
              ->str_tv["int","6"]
                    if str_tv[0]  == "int"
                        valor = Integer.PaserInt(str_tv[1])
    * */

    HashMap<String, Object> lengthsTable = new HashMap<>();

    Boolean flag = true;

    public void agregar(HashMap<String, String> table) {
        table.put("a", "int¿1");
        table.put("b", "bool¿False");
        table.put("c", "bool¿True");
        table.put("i", "int¿0");
    }

    public void Sout(String str) {
        if (flag) {
            System.out.println(str);
        }
    }


    @Override
    public T visitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx) {

        String name = ctx.ID().getText();



        if (table.get(name) == null) {

            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;

            System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + name + "\" no ha sido declarada", line, col);
            System.exit(-1);

        } else if (ctx.PAR_IZQ() != null) {
            Sout("Por acá Estuve");
            //TODO:functions
//            if(ctx.expr()!=null){
//                String ex = ctx.expr().get(0).getText();
//                int len_comas = ctx.COMA().size();
//                for(int i = 0; i<len_comas; i++){
//                    def a(2,3):
//                        return 2+3
//                }
//            }

        } else {
            String id = getTypeValue(ctx.ID().getText())[1];
            return (T) id;
        }
        return super.visitCexpr_p10_id(ctx);
    }

    @Override
    public T visitCexpr_p10_literal(ChocopyParser.Cexpr_p10_literalContext ctx) {
        Sout("Soy un literal " + ctx.literal().getText());
        String aux = ctx.literal().getText();
        return (T) aux;
        //return (T) visit(ctx.literal());
    }

    @Override
    public T visitCexpr_p10_cor(ChocopyParser.Cexpr_p10_corContext ctx) {
        //TODO: Lista con tipo object
        ArrayList<String> arreglo = new ArrayList<>();
        if (ctx.expr() != null) {
            String ex = ctx.expr(0).getText();
            arreglo.add(ex);
            int times = ctx.expr().size();
            for (int i = 1; i < times; i++) {
                ex = visit(ctx.expr(i)).toString();
                arreglo.add(ex);
            }
        }
        Sout(arreglo.toString());
        lengthsTable.put(arreglo.toString(), arreglo.size());
        return (T) arreglo;
    }

    @Override
    public T visitCexpr_p10_par(ChocopyParser.Cexpr_p10_parContext ctx) {
        return (T) visit(ctx.expr());
    }

    @Override
    public T visitCexpr_p10_len(ChocopyParser.Cexpr_p10_lenContext ctx) {
        String st = visit(ctx.cexpr()).toString();
        int ans = -1;
        if (lengthsTable.containsKey(st))
            ans = (Integer) lengthsTable.get(st);
        if (st.charAt(0) != '"' && st.charAt(0) != '[') {
            int line = ctx.LEN().getSymbol().getCharPositionInLine();
            int col = ctx.LEN().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, no se puede sacar len al objeto dado: " + st + " ", line, col);
            System.exit(-1);
        }
        if (st.charAt(0) == '"')
            ans = st.length() - 2;
        Sout("La longitud de mi polla en km es: " + (ans));
        return (T) ((Integer) ans).toString();
    }

    @Override
    public T visitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx) {
        Sout("soy self");
        return (T) ctx.SELF();
    }

    @Override
    public T visitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx) {
        Sout("soyp8menos");
        try {
            int number = Integer.parseInt((String) visitChildren(ctx));
            number *= -1;
            Sout("Menos: " + ((Integer) number).toString());
            return (T) ((Integer) number).toString();
        } catch (NumberFormatException nfe) {
            return super.visitCexpr_p8_menos(ctx);
        }
    }

    @Override
    public T visitCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx) {
        Sout("soyp8p9 " + ctx.cexpr_p9().getText());
        return (T) visit(ctx.cexpr_p9());
    }

    @Override
    public T visitP7_mult(ChocopyParser.P7_multContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p7()));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p8()));
        Sout("Multiplicacion: " + (a * b));
        return (T) ((Integer) (a * b)).toString();
    }

    @Override
    public T visitP7_div(ChocopyParser.P7_divContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p7()));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p8()));
        Sout("Division: " + (a / b));
        return (T) ((Integer) (a / b)).toString();
    }

    @Override
    public T visitP7_mod(ChocopyParser.P7_modContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p7()));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p8()));
        //TODO: Propiedades del módulo
        Sout("Modulo: " + (a % b));
        return (T) ((Integer) (a % b)).toString();
    }

    @Override
    public T visitP6_suma(ChocopyParser.P6_sumaContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p6()));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p7()));
        Sout("Suma: " + (a + b));
        return (T) ((Integer) (a + b)).toString();
    }

    @Override
    public T visitP6_resta(ChocopyParser.P6_restaContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p6()));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p7()));
        Sout("Resta: " + (a - b));
        return (T) ((Integer) (a - b)).toString();
    }

    @Override
    public T visitCexpr_mayor(ChocopyParser.Cexpr_mayorContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p6(1)));
        Sout("Mayor: " + (a > b));
        return (T) ((Boolean) (a > b)).toString();
    }

    @Override
    public T visitCexpr_menor(ChocopyParser.Cexpr_menorContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p6(1)));
        Sout("Menor: " + (a < b));
        return (T) ((Boolean) (a < b)).toString();
    }

    @Override
    public T visitCexpr_mayor_igual(ChocopyParser.Cexpr_mayor_igualContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p6(1)));
        Sout("Mayor Igual: " + (a >= b));
        return (T) ((Boolean) (a >= b)).toString();
    }

    @Override
    public T visitCexpr_menor_igual(ChocopyParser.Cexpr_menor_igualContext ctx) {
        int a = Integer.parseInt((String) visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String) visit(ctx.cexpr_p6(1)));
        Sout("Menor Igual: " + (a <= b));
        return (T) ((Boolean) (a <= b)).toString();
    }

    @Override
    public T visitCexpr_igual(ChocopyParser.Cexpr_igualContext ctx) {
        String a = (String) visit(ctx.cexpr_p6(0));
        String b = (String) visit(ctx.cexpr_p6(1));
        Sout("Igual: " + (a.equals(b))); //"4"== "2+2" Caso Toteo
        return (T) ((Boolean) (a.equals(b))).toString();
    }

    @Override
    public T visitCexpr_diferente(ChocopyParser.Cexpr_diferenteContext ctx) {
        String a = (String) visit(ctx.cexpr_p6(0));
        String b = (String) visit(ctx.cexpr_p6(1));
        Sout("Diferente: " + (!a.equals(b)));
        return (T) ((Boolean) (!a.equals(b))).toString();
    }

    //TODO IS

    @Override
    public T visitExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx) {
        int line = ctx.NOT().getSymbol().getLine();
        int col = ctx.NOT().getSymbol().getCharPositionInLine() + 4;
        Boolean a = parseBoolean((String) visit(ctx.expr_p4()), line, col);
        Sout("Not: " + (!a));
        return (T) ((Boolean) (!a)).toString();
    }

    @Override
    public T visitP3_and(ChocopyParser.P3_andContext ctx) {
        int line = ctx.AND().getSymbol().getLine();
        int col = ctx.AND().getSymbol().getCharPositionInLine() - ctx.expr_p3().getText().length();
        Boolean a = parseBoolean((String) visit(ctx.expr_p3()), line, col);
        line = ctx.AND().getSymbol().getLine();
        col = ctx.AND().getSymbol().getCharPositionInLine() + 5;
        Boolean b = parseBoolean((String) visit(ctx.expr_p4()), line, col);
        //System.out.println("Booleano: " + !a);
        Sout("And: " + (a && b));
        return (T) ((Boolean) (a && b)).toString();
    }

    @Override
    public T visitP2_or(ChocopyParser.P2_orContext ctx) {
        int line = ctx.OR().getSymbol().getLine();
        int col = ctx.OR().getSymbol().getCharPositionInLine() - ctx.expr_p2().getText().length();
        Boolean a = parseBoolean((String) visit(ctx.expr_p2()), line, col);
        line = ctx.OR().getSymbol().getLine();
        col = ctx.OR().getSymbol().getCharPositionInLine() + 4;
        Boolean b = parseBoolean(visit(ctx.expr_p3()).toString(), line, col);
        //System.out.println("Booleano: " + !a);
        Sout("Or: " + (a || b));
        return (T) ((Boolean) (a || b)).toString();
    }

    @Override
    public T visitExpr_if(ChocopyParser.Expr_ifContext ctx) {
        int line = ctx.IF().getSymbol().getLine();
        int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
        Boolean b = parseBoolean((String) visit(ctx.expr_p2()), line, col);

        Sout("if: " + (b));
        if (b) {
            return (T) visit(ctx.expr(0)).toString();
        } else {
            return (T) visit(ctx.expr(1)).toString();
        }
    }

    @Override
    public T visitStmtprint(ChocopyParser.StmtprintContext ctx) {
        String ex = visit(ctx.expr()).toString();
        if (ex.charAt(0) == '"') {
            System.out.println("Esto es una sentencia Print: " + ex.substring(1, ex.length() - 1));
        } else {
            System.out.println("Esto es una sentencia Print: " + ex);
        }

        return super.visitStmtprint(ctx);
    }

    @Override
    public T visitSimple_stmt_expr(ChocopyParser.Simple_stmt_exprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public T visitSimple_stmt_return(ChocopyParser.Simple_stmt_returnContext ctx) {
        if (ctx.expr() != null) {
            return (visit(ctx.expr()));
        }
        return super.visitSimple_stmt_return(ctx); //CUIDADO ROMPER FUNCIONES
    }

    //TODO: ERROR RARO SIEMPRE BOTA ERROR SEMÁNTICO EN 1,ULTIMO
    //TODO: Agregar tipos de listas
    @Override
    public T visitSimple_stmt_asig(ChocopyParser.Simple_stmt_asigContext ctx) {
        int size = ctx.target().size();
        ArrayList<String> targetList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String target = visit(ctx.target(i)).toString();
            if (table.containsKey(target)) {
                targetList.add(target);
            } else {
                int line = ctx.ASIG(i).getSymbol().getLine();
                int col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                System.err.printf("<%d, %d> Error Semantico, no se ha declarado la variable " + target + " ", line, col);
                System.exit(-1);
            }
        }
        /*Acá todas las variables existen en la tabla pero no sabemos su tipo*/
        for (int i = targetList.size() - 1; i >= 0; i--) {
            String target = targetList.get(i);
            Sout("Valor antes de asignacion de la variable: " + target + "  " + table.get(target));
            /*pos 0 tipo, pos 1 valor*/
            String[] typeValue = getTypeValue(target);
            String type = typeValue[0];
            String visit = visit(ctx.expr()).toString();
            Sout("VISITÉ LA EXPRE Y SALIO "+visit);
            try {
                int number = Integer.parseInt(visit);
                if (!type.equals("int")) {
                    int line = ctx.ASIG(i).getSymbol().getLine();
                    int col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                    System.err.printf("<%d, %d> Error Semantico, tipos diferentes de datos\n la variable: " + target + " es de tipo " + type + " y la expresion a asignar es de tipo int", line, col);
                    System.exit(-1);
                }
                Sout("Y number es "+number);
                table.put(target, "int¿" + number);
                Sout("Valor después de asignacion de la variable: " + target + "  " + table.get(target));
            } catch (Exception exprIsNotNumber) {
                int line = ctx.ASIG(i).getSymbol().getLine();
                int col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                boolean booleano = parseBoolean(visit, line, col);
                Sout("Tipo " + type + " Booleano " + booleano);
                if (!type.equals("bool")) {
                    line = ctx.ASIG(i).getSymbol().getLine();
                    col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                    System.err.printf("<%d, %d> Error Semantico, tipos diferentes de datos\n la variable: " + target + " es de tipo " + type + " y la expresion a asignar es de tipo bool", line, col);
                    System.exit(-1);
                } else {
                    table.put(target, "bool¿" + booleano);
                    Sout("Valor después de asignacion de la variable: " + target + "  " + table.get(target));
                    continue;
                }
                if (!type.equals("str")) {
                    line = ctx.ASIG(i).getSymbol().getLine();
                    col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                    System.err.printf("<%d, %d> Error Semantico, tipos diferentes de datos\n la variable: " + target + " es de tipo " + type + " y la expresion a asignar es de tipo String", line, col);
                    System.exit(-1);
                }
                table.put(target, "str¿" + visit);
                Sout("Valor después de asignacion de la variable: " + target + "  " + table.get(target));
            }
        }
        return super.visitSimple_stmt_asig(ctx);
    }

    @Override
    public T visitTarget_id(ChocopyParser.Target_idContext ctx) {
        return (T) ctx.ID().getText();
    }

    @Override
    public T visitBlock(ChocopyParser.BlockContext ctx) {
        //Por si acaso: returnar arreglo de visitas
        int size = ctx.stmt().size();
        Sout("Size de los bloques " + size);
        for (int i = 0; i < size; i++) {
            visit(ctx.stmt(i));
        }
        return null;
    }

    /*
        @Override
        public T visitStmt_simple_stmt(ChocopyParser.Stmt_simple_stmtContext ctx) {
            return (T) super.visit(ctx.simple_stmt());
        }
    */
    @Override
    public T visitStmt_if(ChocopyParser.Stmt_ifContext ctx) {
        String con1 = visit(ctx.expr(0)).toString();
        int line = ctx.IF().getSymbol().getLine();
        int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
        boolean booleano1 = parseBoolean(con1, line, col);
        if (booleano1) {
            return (T) visit(ctx.block(0));
        } else {
            int size = ctx.expr().size();
            for (int i = 1; i < size; i++) {
                String coni = visit(ctx.expr(i)).toString();
                line = ctx.ELIF(i - 1).getSymbol().getLine();
                col = ctx.ELIF(i - 1).getSymbol().getCharPositionInLine() + 6;
                boolean booleanoi = parseBoolean(coni, line, col);
                if (booleanoi)
                    return (T) visit(ctx.block(i));
            }
            if (ctx.ELSE() != null) {
                return (T) visit(ctx.block(ctx.block().size() - 1));
            }
        }
        Sout("AHHHHHHHHHHHH");
        return null;
    }

    @Override
    public T visitStmt_while(ChocopyParser.Stmt_whileContext ctx) {
        int line = ctx.WHILE().getSymbol().getLine();
        int col = ctx.WHILE().getSymbol().getCharPositionInLine()+7;
        boolean condition = parseBoolean(visit(ctx.expr()).toString(),line,col);
        while(condition){
            visit(ctx.block());
            condition = parseBoolean(visit(ctx.expr()).toString(),line,col);
            Sout("Condicion "+condition);

        }
        return null;
    }

    @Override
    public T visitStmt_for(ChocopyParser.Stmt_forContext ctx) {
        // | FOR ID IN expr DOS_PUNTOS block
        // table.put("t","int¿0");
        String visit = visit(ctx.expr()).toString();
        String tipo_tmp = "";
        //int size = Integer.parseInt(visit);
        int ans = -1;
        if (lengthsTable.containsKey(visit)){
            ans = (Integer) lengthsTable.get(visit);
            //String num = "[22,True,'Perro',None,66,77]";
            String str[] = visit.substring(1,visit.length()-1).split(",");
            List<String> al = new ArrayList<String>();
            al = Arrays.asList(str);

            boolean is_Integer = false;
            boolean is_String = false;
            boolean is_Boolean = false;
            boolean is_Object = false;

            for(Object i: al){
                try{
                    int prueba = Integer.parseInt(i.toString().replace(" ", ""));
                    is_Integer = true;
                    if (is_Boolean || is_String){
                        is_Object = true;
                        break;
                    }

                }catch(Exception e){
                    try{
                        String tmp = i.toString().replace(" ", "");
                        if (tmp.equals("True") || tmp.equals("False")){
                            is_Boolean = true;
                            if (is_Integer || is_String){
                                is_Object = true;
                                break;
                            }
                        }else{
                            throw new Exception("Excepcion para que pase al catch siguiente");
                        }

                    }catch(Exception e1){
                        Sout("El valor de Object es: " + i.toString().replace(" ", ""));
                        if(i.toString().replace(" ", "").charAt(0) != '"'){
                            int line = ctx.IN().getSymbol().getCharPositionInLine();
                            int col = ctx.IN().getSymbol().getCharPositionInLine() + 4;
                            System.err.printf("<%d, %d> Error Semantico, Solo se permiten listas con Strings, Enteros o Booleanos", line, col);
                            System.exit(-1);
                        }
                        is_String= true;
                    }
                }
            }

            if(is_Object){
                tipo_tmp = "Object";
            }else if (is_Integer){
                tipo_tmp = "int";
            }else if (is_Boolean){
                tipo_tmp = "bool";
            }else{
                tipo_tmp = "str";
            }
            Sout("Soy un null pointer " + tipo_tmp + " " + ans);
        }

        if (visit.charAt(0) != '"' && visit.charAt(0) != '[') {
            int line = ctx.IN().getSymbol().getCharPositionInLine();
            int col = ctx.IN().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, no se puede iterar sobre: " + visit + " ", line, col);
            System.exit(-1);
        }
        if (visit.charAt(0) == '"'){
            ans = visit.length() - 2;
            tipo_tmp = "str";
        }

        Sout("La longitud de mi polla en km es: " + (ans));
        Sout(ctx.ID().toString());
        String[] arreglo =  getTypeValue(ctx.ID().getText());

        if(!arreglo[0].equals(tipo_tmp)){
            int line = ctx.IN().getSymbol().getCharPositionInLine();
            int col = ctx.IN().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, El tipo del iterador: " + arreglo[0] + " ", line, col);
            System.exit(-1);
        }
        Sout("Buenas Tardes");
        String str[] = visit.substring(1,visit.length()-1).split(",");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);
        Sout("Buenas Tardes");

        for(Object i : al){
            if (i.toString().replace(" ", "").charAt(0) == '"'){
                //Sout("Yo soy i: " + i.toString().replace(" ", "") + ":");
                if (i.toString().charAt(0) == ' '){
                    table.put(ctx.ID().getText(), arreglo[0]+"¿"+i.toString().substring(2, i.toString().length()-1));
                }else{
                    table.put(ctx.ID().getText(), arreglo[0]+"¿"+i.toString().substring(1, i.toString().length()-1));

                }
            }else{
                Sout("Yo soy i: " + i.toString()+ ":");
                table.put(ctx.ID().getText(), arreglo[0]+"¿"+i.toString().replace(" ", ""));

            }

            visit(ctx.block());
        }
       // return super.visitStmt_for(ctx);
        return null;
    }

    @Override
    public T visitProgram(ChocopyParser.ProgramContext ctx) {
        agregar(table);
        return super.visitProgram(ctx);
    }
}