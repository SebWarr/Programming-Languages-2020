import org.antlr.v4.runtime.RuleContext;
import java.net.SocketImpl;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyVisitor<T> extends ChocopyBaseVisitor<T> {
    //🐍 Epicness

    Stack<HashMap<String, String>> stackTables = new Stack<>(); //stack de contextos para variables
    Stack<HashMap<String, Object>> stackContext = new Stack<>(); //stack de contextos
    HashMap<String, ArrayList<String>> funArguments =  new HashMap<>();
    HashMap<String, Object> lengthsTable = new HashMap<>();
    Boolean flag = true;

    public String[] getTypeValue(String key) {
        //USAR SABIAMENTE: *NOOOO* USAR SI NO SE HA VERIFICADO QUE LA LLAVE ESTÁ EN EL MAPA
        String tp = stackTables.peek().get(key);
        String[] typeValue = tp.split("¿");
        /*Idea: guardar variables de este tipo en el mapa : llave : "x", valor :"int¿6"*/
        /*Este método retorna un string[] que en su primera posición (0) tiene el tipo y en la segunda (1) tiene el valor*/
        return typeValue;
    }

    public void Sout(String str) {
        if (flag) {
            System.out.println(str);
        }
    }

    //FUNCIÓN AUXILIAR PARA TYPE, PARA CONOCER EL TIPO DE LOS ARREGLOS
    public String type_arr(String[] data_arr){
        String first_type = type(data_arr[0].replace(" ",""));
        System.out.println("primer tipo "+ first_type);
        for(int i = 1; i<data_arr.length;i++){
            String ith_type = type(data_arr[i].replace(" ",""));
            System.out.println("tipo "+i+" "+ ith_type);
            if(!ith_type.equals(first_type))
                return "[object]";
        }
        return "["+first_type+"]";
    }
    public String type(String data) {
        String result = "";
        try {
            System.out.println("Llegue a evaluar int");
            int number = Integer.parseInt(data);
            result = "int";
        } catch (Exception notInteger) {
            System.out.println("Llegue a evaluar bool");
            if (data.toLowerCase().equals("true") || data.toLowerCase().equals("false")) {
                result = "bool";
            } else {
                System.out.println("Llegue a evaluar str");
                // TODO: VER SI ES CLASE LO TRATAMOS LUEGO CUANDO HAYA TABLAS DE CLASES
                if (data.charAt(0) == '"' && data.charAt(data.length() - 1) == '"') {
                    result = "str";
                } else if (data.charAt(0) == '[' && data.charAt(data.length() - 1) == ']') {
                    System.out.println("Llegue a evaluar arr");
                    String[] data_arr = data.substring(1,data.length()-1).split(",");
                    result = type_arr(data_arr);
                } else {
                    System.out.println("Llegue a evaluar None");
                    if (data.equals("None")) {
                        result = "None";
                    }else{
                        System.out.println("Llegue a evaluar OBJ COSA");

                        if (data.equals("retorno de la funcion visitFunc_body")){
                            result = "Retorno Vacío de Funcion";
                        }else{
                            result = "ERROR";
                        }

                        //mirar en talbas (clases, ) <- VER CASOS DE PRUEBA
                        // x:= a
                        // Ver tabla de varialbes y mirar el tipo y comparar Sino, error
                        // retrun 'posible variable o error'

                    }
                }
            }
        }
        Sout("Llegue al return result");
        return result;
    }

    public boolean isArray(String possibleArr){
        if(possibleArr.charAt(0)=='[' && possibleArr.charAt(possibleArr.length()-1)==']')
            return true;
        return false;
    }

    public void imprimirTable(){
        for(Map.Entry<String, String> entry :  stackTables.peek().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Llave "+key+" Valor "+value);
        }
    }

    //FUNCIONES VISITOR
    @Override
    public T visitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx) {
        HashMap<String,String> localVariables = stackTables.peek();
        // TODO VERIFICAR TIPOS
        // REGLA visitCexpr_p10_id: ID (PAR_IZQ (expr (COMA expr)*)? PAR_DER)?
        String name = ctx.ID().getText();
        Sout("Entré a visitCexpr_p10_id "+ name);
        if (ctx.PAR_IZQ() != null) {
            HashMap<String,Object> localHash = stackContext.peek(); //para funciones clases
//            HashMap<String,String> localTable = stackTables.pop(); //para variables
            HashMap<String,String> localTable = stackTables.peek(); //para variables
            Sout("Tengo un Parentesis Izquierdo");
            if (!localHash.containsKey(name)) {
                int line = ctx.ID().getSymbol().getLine();
                int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, la funcion con nombre: \"" + name + "\" no ha sido declarada", line, col);
                System.exit(-1);
            } else {
                ArrayList<String> local_vars = new ArrayList<>();
                ChocopyParser.Func_defContext context = (ChocopyParser.Func_defContext) localHash.get(name);
                Sout("TypedVar"+context.typed_var(0).getText());
                if (context.typed_var().size() == ctx.expr().size()) {
                    ArrayList<String> argumentos = new ArrayList<>();

                    for (int i = 0; i < context.typed_var().size(); i++) {
                        Sout("Estoy llegando a acá" + i);
                        String[] idTipo = context.typed_var(i).getText().split(":");
                        Sout("Estoy llegando a acá"+ visit(ctx.expr(i)).toString());
//                        local_vars.add(""+idTipo[0]+ "¿"+idTipo[1] + "¿" + visit(ctx.expr(i)).toString());
                        //localTable.put(idTipo[0], idTipo[1] + "¿" + visit(ctx.expr(i)).toString());
                        argumentos.add(idTipo[0]+"¿"+ idTipo[1] + "¿" + visit(ctx.expr(i)).toString());

                        // Esto no esta funcionando?
                    }
                    funArguments.put(context.ID().getText(), argumentos);
                    // Agregamos los Parámetros
                   // stackTables.push(localTable);

                    Sout("Revisando la Tabla");
                    Sout("Size actual de la tabla: " + stackTables.size());
                    imprimirTable();//Sout de HM table
                } else {
                    int line = ctx.ID().getSymbol().getLine();
                    int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, el número de parametros no es correcto", line, col);
                    System.exit(-1);
                }
                String vis = visit(context.func_body()).toString(); // Valor de vis
                String vis_type = type(vis); //Tipo de vis
                Sout("Mi Vis_type is: " + vis_type);
                //Borrar Variables Locales
                for(int i= 0; i < local_vars.size();i++){
                    local_vars.remove(local_vars.get(i));
                }

                if (vis.equals("$") && context.type() != null) {
                    int line = ctx.ID().getSymbol().getLine();
                    int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, el número de parametros no es correcto", line, col);
                    System.exit(-1);
                } else {
                    Sout("Soy Context Type: " + context.type());
                    if (context.type() == null && vis_type.equals("Retorno Vacío de Funcion")){
                        Sout("Es un Retorno Válido");
                    }else if (context.type() == null && !vis_type.equals("Retorno Vacío de Funcion")){
                        int line = ctx.ID().getSymbol().getLine();
                        int col = ctx.ID().getSymbol().getCharPositionInLine();
                        System.err.printf("<%d, %d> Error Semantico, el tipo de retorno y la expresion no son iguales para la funcion " + ctx.ID(), line, col);
                        System.exit(-1);
                    }
                    Sout("Mi Vis es: " + vis);
                    String type = context.type().getText(); // Acá se muere
                    Sout("Mi Vis_type is: " + vis_type);
                    Sout("Soy Type: " + type);
                    if(!vis_type.equals(type)){
                        int line = ctx.ID().getSymbol().getLine();
                        int col = ctx.ID().getSymbol().getCharPositionInLine();
                        System.err.printf("<%d, %d> Error Semantico, el tipo de retorno y la expresion no son iguales para la funcion " + ctx.ID(), line, col);
                        System.exit(-1);
                    }else if(vis_type.equals("ERROR")){
                        int line = ctx.ID().getSymbol().getLine();
                        int col = ctx.ID().getSymbol().getCharPositionInLine();
                        System.err.printf("<%d, %d> Error Semantico, expresion  " +vis+" no tiene un tipo de dato valido" + ctx.ID(), line, col);
                        System.exit(-1);
                    }
                    Sout("Fin de Verificación de Tipos de Variables");
                    return (T) vis;
                }
                Sout("No fuí una funcion");
            }
            Sout("Ya termino mi periodo de ser un parentesis");
        } else {
            Sout("Prueba: ");
//            if (funArguments.containsKey(ctx.ID().getText())){
//                ArrayList<String> funArgumTmp = funArguments.get(ctx.ID().getText());
//                for (int i = 0; i < funArgumTmp.size(); i++) {
//                    String[] tmp = funArgumTmp.get(i).split("¿");
//                    Sout("Aleluya "+tmp[0]+tmp[1] + "¿"+ tmp[2]);
//                    localVariables.put(tmp[0], tmp[1] + "¿"+ tmp[2]);
//                }
//            }
            Sout("Estoy buscando a: " + name);
            imprimirTable();
            Sout("El tamaño de la pila es de: " + stackContext.size());
            // 25 minutos en un !

            if (!localVariables.containsKey(name)) {
                Sout("No encontre a:" + name);
                int line = ctx.ID().getSymbol().getLine();
                int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + name + "\" no ha sido declarada", line, col);
                System.exit(-1);
            } else {
                Sout("Encontre a:" + name);
                String id = getTypeValue(ctx.ID().getText())[1];
                return (T) id;
            }
        }
        return (T) "He terminado visitCexpr_p10_id";
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
        Sout("Soy una Lista");
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
        Sout("Soy una Funcion Ejecutada");
        return (T) visit(ctx.expr());
    }

    @Override
    public T visitCexpr_p10_len(ChocopyParser.Cexpr_p10_lenContext ctx) {
        Sout("Estoy pidiendo el LEN");
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
        Sout("La longitud final es: " + ans);
        return (T) ((Integer) ans).toString();
    }

    @Override
    public T visitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx) {
        Sout("soy self");
        return (T) ctx.SELF();
    }

    @Override
    public T visitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx) {
        Sout("Voy a cambiar el signo");
        String a = visit(ctx.cexpr_p8()).toString();
        String type_expr = type(a);
        if(!type_expr.equals("int")){
            int line = ctx.MENOS().getSymbol().getLine();
            int col =  ctx.MENOS().getSymbol().getCharPositionInLine()+1;
            System.err.printf("<%d, %d> Error Semantico, no se puede cambiar de signo a un objeto no entero, tipo de dato dado: " + type_expr + " ", line, col);
            System.exit(-1);
        }
        int number = Integer.parseInt(a);
        number *= -1;
        Sout("Menos: " +number);
        return (T) ((Integer) number).toString();
    }

    @Override
    public T visitCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx) {
        Sout("Bajo de P8 a P9 " + ctx.cexpr_p9().getText());
        return (T) visit(ctx.cexpr_p9());
    }

    //OPERACIONES BASICAS
    //Máxima prioridad math
    @Override
    public T visitP7_mult(ChocopyParser.P7_multContext ctx) {
        String a = visit(ctx.cexpr_p7()).toString();
        String b = visit(ctx.cexpr_p8()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MULTIPLICACION().getSymbol().getCharPositionInLine() - 2;
            } else {
                col = ctx.MULTIPLICACION().getSymbol().getCharPositionInLine() + 2;
            }
            int line = ctx.MULTIPLICACION().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se pueden multiplicar dos objetos de tipo " + type_a + " y " + type_b, line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a)) * (Integer.parseInt(b));
        Sout("Multiplicacion: " + result);
        return (T) ((Integer) (result)).toString();
    }

    @Override
    public T visitP7_div(ChocopyParser.P7_divContext ctx) {
        String a = visit(ctx.cexpr_p7()).toString();
        String b = visit(ctx.cexpr_p8()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int") ){
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.DIVISION().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.DIVISION().getSymbol().getCharPositionInLine()+3;
            }
            int line = ctx.DIVISION().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se pueden dividir dos objetos de tipo " + type_a + " y "+type_b, line, col);
            System.exit(-1);
        }
        if(Integer.parseInt(b) == 0){
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.DIVISION().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.DIVISION().getSymbol().getCharPositionInLine()+3;
            }
            int line = ctx.DIVISION().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, Math error, no se puede dividir por 0", line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a))/(Integer.parseInt(b));
        Sout("Division: " + result);
        return (T) ((Integer) result).toString();
    }


    @Override
    public T visitP7_mod(ChocopyParser.P7_modContext ctx) {
        String a = visit(ctx.cexpr_p7()).toString();
        String b = visit(ctx.cexpr_p8()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int") ){
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.MODULO().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.MODULO().getSymbol().getCharPositionInLine()+2;
            }
            int line = ctx.MODULO().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se puede obtener el modulo de dos objetos de tipo " + type_a + " y "+type_b, line, col);
            System.exit(-1);
        }
        //TODO: Propiedades del módulo
        int result = (Integer.parseInt(a))%(Integer.parseInt(b));
        Sout("Modulo: " + result);
        return (T) ((Integer) result).toString();
    }


    //Segunda prioridad: suma/concatenacion de arreglos/concatenacion de Strings/resta
    @Override
    public T visitP6_suma(ChocopyParser.P6_sumaContext ctx) {
        // Vamos a ver el tipo de dato que son ctx.cexpr_p6() y ctx.cexpr_p7()
        // Si ambos son listas/str, suma se comporta como concatenación de listas/str
        // Si ambos son enteros, vamos a hacer una suma
        // Si son diferentes vamos a declarar error
        String a = visit(ctx.cexpr_p6()).toString();
        String b = visit(ctx.cexpr_p7()).toString();
        String type_a = type(a);
        String type_b = type(b);
        Sout("Tipo de "+a+ " es "+type_a+"Tipo de "+b+ " es "+type_b);
        //esto va a ser mi else
        Sout("Llegue a una suma con tipo a "+type_a+" Tipo b "+type_b);
        if(type_a.equals("str") && type_b.equals("str")){
            //Concatenación de strs
            String result = a+b;
            result = result.replace("\"","");
            result = "\""+result+"\"";
            Sout(" Resultado de la suma de strs "+result);
            return (T) (result);
        }else if(isArray(a) && isArray(b)){
            //concatenación de arrays
            String part_a = a.substring(0,a.length()-1);
            String part_b = b.substring(1);
            String result = part_a+", "+part_b;
            Sout(" Resultado de la suma de arrays "+result+ " y el tipo es "+type(result));
            return (T) (result);
        }else if(type_a.equals("int") && type_b.equals("int")){
            int result = Integer.parseInt(a) + Integer.parseInt(b);
            return (T) (Integer)result;
        }else{
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.SUMA().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.SUMA().getSymbol().getCharPositionInLine()+2;
            }
            int line = ctx.SUMA().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se pueden sumar dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        return (T) ("Ya se sumó we");
    }

    @Override
    public T visitP6_resta(ChocopyParser.P6_restaContext ctx) {
        String a = visit(ctx.cexpr_p6()).toString();
        String b = visit(ctx.cexpr_p7()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int")){
            int line = ctx.MENOS().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.MENOS().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.MENOS().getSymbol().getCharPositionInLine()+2;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden restar dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a))-(Integer.parseInt(b));
        Sout("Resta: " + result);
        return (T) ((Integer) result).toString();
    }


    //Comparacion de enteros
    @Override
    public T visitCexpr_mayor(ChocopyParser.Cexpr_mayorContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int")){
            int line = ctx.MAYOR().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.MAYOR().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.MAYOR().getSymbol().getCharPositionInLine()+2;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (>) dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a))>(Integer.parseInt(b));
        Sout("Mayor: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_menor(ChocopyParser.Cexpr_menorContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int")){
            int line = ctx.MENOR().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.MENOR().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.MENOR().getSymbol().getCharPositionInLine()+2;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (<) dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a))<(Integer.parseInt(b));
        Sout("Menor: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_mayor_igual(ChocopyParser.Cexpr_mayor_igualContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int")){
            int line = ctx.MAYOR_IGUAL().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.MAYOR_IGUAL().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.MAYOR_IGUAL().getSymbol().getCharPositionInLine()+3;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (>=) dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a))>=(Integer.parseInt(b));
        Sout("Mayor o igual: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_menor_igual(ChocopyParser.Cexpr_menor_igualContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!type_a.equals("int") || !type_b.equals("int")){
            int line = ctx.MENOR_IGUAL().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.MENOR_IGUAL().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.MENOR_IGUAL().getSymbol().getCharPositionInLine()+3;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (<=) dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a))<=(Integer.parseInt(b));
        Sout("Menor o igual: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_igual(ChocopyParser.Cexpr_igualContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        Sout("Voy a comparar dos int");
        if((!type_a.equals("int") || !type_b.equals("int")) && ((!type_a.equals("bool") || !type_b.equals("bool"))) && (!type_a.equals("str") || !type_b.equals("str"))){
            int line = ctx.IGUAL().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.IGUAL().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.IGUAL().getSymbol().getCharPositionInLine()+3;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (==) dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        boolean result = a.equals(b);
        Sout("igual: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_diferente(ChocopyParser.Cexpr_diferenteContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        Sout("Voy a comparar dos int");
        if((!type_a.equals("int") || !type_b.equals("int")) && ((!type_a.equals("bool") || !type_b.equals("bool"))) && (!type_a.equals("str") || !type_b.equals("str"))){
            int line = ctx.DIFERENTE().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("int")) {
                col = ctx.DIFERENTE().getSymbol().getCharPositionInLine()-2;
            }else{
                col = ctx.DIFERENTE().getSymbol().getCharPositionInLine()+3;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (!=) dos objetos de tipo " + type_a + " y  "+type_b, line, col);
            System.exit(-1);
        }
        boolean result = !a.equals(b);
        Sout("diferente: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx) {
        String a = visit(ctx.expr_p4()).toString();
        Sout("Voy a sacarle  el tipo  a "+a);
        //NOTA TENER CUIDADO CON TRUE/FALSE DE JAVA: true/false y TRUE/FALSE de chocapick True/False
        String type_a = type(a);
        Sout("Tipo de dato"+type_a);
        Sout("Valor de dato "+a);
        if(!(type_a).equals("bool")){
            int line = ctx.NOT().getSymbol().getLine();
            int col = ctx.NOT().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, no se cambiar valor booleano (not) de objeto de tipo " + type_a , line, col);
            System.exit(-1);
        }
        boolean result = !Boolean.parseBoolean(a);
        Sout("Not: " + result);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitP3_and(ChocopyParser.P3_andContext ctx) {
        String a = visit(ctx.expr_p3()).toString();
        String b = visit(ctx.expr_p4()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!(type_a).equals("bool") || !(type_b).equals("bool")){
            int line = ctx.AND().getSymbol().getLine();
            line = ctx.AND().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("bool")) {
                col = ctx.AND().getSymbol().getCharPositionInLine() - ctx.expr_p3().getText().length();
            }else{
                col = ctx.AND().getSymbol().getCharPositionInLine() + 5;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (and) objetos de tipo " + type_a+" y "+type_b , line, col);
            System.exit(-1);
        }
        boolean result = ((Boolean.parseBoolean(a)) && ((Boolean.parseBoolean(b))));
        Sout("And: " + result);
        return (T) ((Boolean) result).toString();
    }

    @Override
    public T visitP2_or(ChocopyParser.P2_orContext ctx) {
        String a = visit(ctx.expr_p2()).toString();
        String b = visit(ctx.expr_p3()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if(!(type_a).equals("bool") || !(type_b).equals("bool")){
            int line = ctx.OR().getSymbol().getLine();
            line = ctx.OR().getSymbol().getLine();
            int col = -1;
            if(!type_a.equals("bool")) {
                col = ctx.OR().getSymbol().getCharPositionInLine() - ctx.expr_p2().getText().length();
            }else{
                col = ctx.OR().getSymbol().getCharPositionInLine() + 4;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (or) objetos de tipo " + type_a+" y "+type_b , line, col);
            System.exit(-1);
        }
        boolean result = ((Boolean.parseBoolean(a)) || ((Boolean.parseBoolean(b))));
        Sout("or: " + result);
        return (T) ((Boolean) result).toString();
    }

    @Override
    public T visitExpr_if(ChocopyParser.Expr_ifContext ctx) {
        String b = visit(ctx.expr(0)).toString();
        String type_b = type(b);
        if(!type_b.equals("bool")){
            int line = ctx.IF().getSymbol().getLine();
            int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, evaluar if con expresion " +b+" ya que es de tipo "+type_b+ " y no bool" , line, col);
            System.exit(-1);
        }
        Sout("if: " + (b));
        if (Boolean.parseBoolean(b)) {
            return (T) visit(ctx.expr_p2()).toString();
        } else {
            return (T) visit(ctx.expr(1)).toString();
        }
    }


    @Override
    public T visitStmtprint(ChocopyParser.StmtprintContext ctx) {
        Sout("Voy a imprimir" + ctx.expr().getText());
        String ex = visit(ctx.expr()).toString();
        String type_ex = type(ex);
        if (type_ex.equals("str")) {
            System.out.println("Esto es una sentencia Print: " + ex.substring(1, ex.length() - 1));
        } else if (isArray(ex)){
            int line = ctx.PRINT().getSymbol().getLine();
            int col = ctx.PRINT().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, No se puede imprimir un arreglo ", line, col);
            System.exit(-1);
        }
        else {
            System.out.println("Esto es una sentencia Print: " + ex);
        }
        return (T) ("Termine visitStmtprint");
//        return super.visitStmtprint(ctx);
    }

    @Override
    public T visitSimple_stmt_expr(ChocopyParser.Simple_stmt_exprContext ctx) {
        Sout("Soy un Simple Stmt a revisar la expr: " + ctx.expr().getText());
        String visit = visit(ctx.expr()).toString();
        //Sout("Soy un Simple Stmt: " + visit);
        return (T) ("Ya termine visitSimple_stmt_expr");
    }

    @Override
    public T visitSimple_stmt_return(ChocopyParser.Simple_stmt_returnContext ctx) {
        if (ctx.expr() != null) {
            Sout("Llegue a Sentencia Return: " + "return¿" + visit(ctx.expr()).toString());
            return (T) ("return¿" + visit(ctx.expr()).toString());
        } else {
            Sout("Soy un Return Vacío");
            return (T) "return¿null"; //Si return tiene de valor null romper contexto
        }
    }

    //TODO: ERROR RARO SIEMPRE BOTA ERROR SEMÁNTICO EN 1,ULTIMO
    //TODO: Agregar tipos de listas
    @Override
    public T visitSimple_stmt_asig(ChocopyParser.Simple_stmt_asigContext ctx) {
        HashMap<String, String> localVariables = stackTables.peek();
        Sout("Entre a una asignación");
        // Falta cambiar de aqui para abajo los sout
        int size = ctx.target().size();
        ArrayList<String> targetList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String target = visit(ctx.target(i)).toString();
            if (target.contains("¡")) {
                Sout("ACÁ ESTOY MODIFICANDO EL COMPORTAMIENTO PARA ACCEDER AL ARREGLO¡indice" + target);
//                targetList.add(target);
                String[] target_varindex = target.split("¡");
                String target_name = target_varindex[0];
                Sout("Target name " + target_name);
                if (localVariables.containsKey(target_name)) {
                    Sout("La llave " + target_name + " del arreglo que quiero modiicar " + target + " esta en table");
                    Sout("HEYYY " + localVariables.get(target_name));
                    targetList.add(target);
                } else {
                    int line = ctx.ASIG(i).getSymbol().getLine();
                    int col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                    System.err.printf("<%d, %d> Error Semantico, no se ha declarado la variable " + target_name + " ", line, col);
                    System.exit(-1);
                }
            } else if (localVariables.containsKey(target)) {
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
            Sout("Valor antes de asignacion de la variable: " + target + "  " + localVariables.get(target));
            /*pos 0 tipo, pos 1 valor*/

            if (target.contains("¡")) {
                String[] target_varindex = target.split("¡");
                String target_name = target_varindex[0];
                String array = target_varindex[1];
                int target_index = Integer.parseInt(target_varindex[2]);

                String[] targetArr = array.substring(1, array.length() - 1).replace(" ", "").split(",");
                Sout("ESTE ES MI ARREGLO ");
                Sout("Voy a tratar de modificar la variable " + localVariables.get(target_name) + " en el indice " + target_index);
                Sout("SOY TU TARGET " + targetArr[target_index]);
                targetArr[target_index] = visit(ctx.expr()).toString();
                Sout("SOY TU TARGET ASIGNADO" + targetArr[target_index]);

                String ans = "[";
                for (int j = 0; j < targetArr.length - 1; j++) {
                    ans = ans + targetArr[j] + ",";
                }
                ans = ans + targetArr[targetArr.length - 1] + "]";
                Sout("Arreglo modificado es " + ans);
                localVariables.put(target_name, type(array) + "¿" + ans);
            } else {

                String[] typeValue = getTypeValue(target);
                String type = typeValue[0];
                String visit = visit(ctx.expr()).toString();
                String visit_type = type(visit);
                Sout("VISITÉ LA EXPRE Y SALIO " + visit);
                if (visit.equals("ERROR")) {
                    int line = ctx.ASIG(i).getSymbol().getLine();
                    int col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                    System.err.printf("<%d, %d> Error Semantico, el tipo de la variable " + visit + " no es valido", line, col);
                    System.exit(-1);
                }
                if (!visit_type.equals(type)) {
                    int line = ctx.ASIG(i).getSymbol().getLine();
                    int col = ctx.ASIG(i).getSymbol().getCharPositionInLine();
                    System.err.printf("<%d, %d> Error Semantico, tipos diferentes de datos\n la variable: " + target + " es de tipo " + type + " y la expresion " + visit + " a asignar es de tipo " + visit_type, line, col);
                    System.exit(-1);
                }
                Sout("Visit es " + visit + " su tipo es " + visit_type);
                Sout("Target es " + target + " su tipo es " + localVariables.get(target));
                if (visit_type.equals("int")) {
                    int number = Integer.parseInt(visit);
                    localVariables.put(target, "int¿" + number);
                } else if (visit_type.equals("bool")) {
                    localVariables.put(target, "bool¿" + visit);
                } else if (visit_type.equals("str")) {
                    localVariables.put(target, "str¿" + visit);
                } else if (isArray(visit_type)) {
                    Sout("VOY A ASIGNAR UN ARREGLO");
                    if (visit_type.equals(type)) {
                        Sout("Soy un arreglo chinngon " + visit);
                        localVariables.put(target, type + "¿" + visit);
                    } else {
                        //TODO: QUE SE PUEDA RECORRER EL FOR DE OBJECT
                    }
                }
                Sout("Valor después de asignacion de la variable: " + target + "  " + localVariables.get(target));
            }

        }
        return (T) ("Ya visité visitSimple_stmt_asig");
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
        return (T)("Termine visitBlock");
    }

    @Override
    public T visitStmt_simple_stmt(ChocopyParser.Stmt_simple_stmtContext ctx) {
        Sout("Entre a un simple stmt");
        return (T) super.visit(ctx.simple_stmt());
    }

    @Override
    public T visitStmt_if(ChocopyParser.Stmt_ifContext ctx) {
        String con1 = visit(ctx.expr(0)).toString();
        String type_con1 = type(con1);
        if(!type_con1.equals("bool")){
            int line = ctx.IF().getSymbol().getLine();
            int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, el tipo de la variable a evaluar en el if "+con1+" no es bool, tipo de variable "+type_con1 , line, col);
            System.exit(-1);
        }
        if (Boolean.parseBoolean(con1)) {
            return (T) visit(ctx.block(0));
        } else {
            int size = ctx.expr().size();
            for (int i = 1; i < size; i++) {
                String coni = visit(ctx.expr(i)).toString();
                String type_coni = type(coni);
                if(!type_coni.equals("bool")){
                    int line = ctx.IF().getSymbol().getLine();
                    int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
                    System.err.printf("<%d, %d> Error Semantico, el tipo de la variable a evaluar en el if "+coni+" no es bool, tipo de variable "+type_coni , line, col);
                    System.exit(-1);
                }
                if (Boolean.parseBoolean(coni))
                    return (T) visit(ctx.block(i));
            }
            if (ctx.ELSE() != null) {
                return (T) visit(ctx.block(ctx.block().size() - 1));
            }
        }
        return (T)("Termine visitStmt_if");
    }

    @Override
    public T visitStmt_while(ChocopyParser.Stmt_whileContext ctx) {
        String con = visit(ctx.expr()).toString();
        String type_con = type(con);
        if(!type_con.equals("bool")){
            int line = ctx.WHILE().getSymbol().getLine();
            int col = ctx.WHILE().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, el tipo de la variable a evaluar en el condicional while "+con+" no es bool, tipo de variable "+type_con , line, col);
            System.exit(-1);
        }
        while (Boolean.parseBoolean(con)) {
            visit(ctx.block());
            con = (visit(ctx.expr()).toString());
            Sout("Condicion " + con);
        }
        return (T)("Termine un visitStmt_while");
    }
    @Override
    public T visitStmt_for(ChocopyParser.Stmt_forContext ctx) {
        HashMap<String,String> localVariables = stackTables.peek();

        // | FOR ID IN expr DOS_PUNTOS block
        // table.put("t","int¿0");
        String visit = visit(ctx.expr()).toString();
        String tipo_tmp = "";
        String type_visit = type(visit);
        String id = ctx.ID().toString();
        if(!localVariables.containsKey(id)){
            int line = ctx.ID().getSymbol().getCharPositionInLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, "+id+ " no ha sido declarado", line, col);
            System.exit(-1);
        }
        Sout(visit+" es arreglo "+isArray(visit));
        if(!type_visit.equals(("str")) && !isArray(visit) ){
            int line = ctx.IN().getSymbol().getCharPositionInLine();
            int col = ctx.IN().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, Solo se permiten listas con int o bool o object o un String y la expresion "+visit+" tiene tipo "+type_visit, line, col);
            System.exit(-1);
        }
        Sout("SOY ID"+localVariables.get(id)); //str¿"HOLA"
        String type_id = localVariables.get(id).split("¿")[0];
        if(!type_visit.equals(type_id)){ //[str] str
            if(!type_visit.equals("["+type_id+"]")) { //[str] [str]
                int line = ctx.ID().getSymbol().getCharPositionInLine();
                int col = ctx.ID().getSymbol().getCharPositionInLine() + 4;
                System.err.printf("<%d, %d> Error Semantico, " + id + " no es del mismo tipo que " + visit + " tipo de id: " + type_id + " tipo de expresion " + visit, line, col);
                System.exit(-1);
            }
        }
        if(type_visit.equals("str")){
            visit = visit.replace("\"","");
            for (int i = 0; i<visit.length();i++){
                localVariables.put(id,"str¿"+visit.charAt(i));
                visit(ctx.block());
            }
        }else if (isArray(visit)){
            String tmp = visit.substring(1,visit.length()-1);
            String [] arr = tmp.split(",");
            String ith_type = type_visit.substring(1,type_visit.length()-1); //[sdf] -> sdf
            for (int i = 0; i<arr.length;i++){
                localVariables.put(id,ith_type+"¿"+arr[i].replace(" ",""));
                visit(ctx.block());
            }
        }
        return (T)("Termine visitStmt_for");
    }

    @Override
    public T visitProgram(ChocopyParser.ProgramContext ctx) {
        HashMap<String, Object> globalContext = new HashMap<>();
        HashMap<String, String> globalVariables = new HashMap<>();
        stackContext.push(globalContext);
        stackTables.push(globalVariables);
        return super.visitProgram(ctx);
    }

    @Override
    public T visitVar_def(ChocopyParser.Var_defContext ctx) {
        HashMap<String,String> localVariables= stackTables.peek();
        String var_def = visit(ctx.typed_var()).toString();
        Sout("var def 2 "+var_def);
        String lit = ctx.literal().getText();
        Sout("lit "+lit);
        String[] tmp = var_def.split(":");
        String var_type = tmp[1];
        String var_name = tmp[0];
        String lit_type = type(lit);
        if(!lit_type.equals(var_type)) {
            if(isArray(var_type) && (var_type.equals(lit_type) || lit_type.equals("ERROR"))){
                int line = ctx.ASIG().getSymbol().getLine();
                int col = ctx.ASIG().getSymbol().getCharPositionInLine() + 3;
                System.err.printf("<%d, %d>  Error Semantico, variable "+var_name+" de tipo "+var_type+" y solo se puede inicializar con None", line, col);
                System.exit(-1);
            }
            if (!isArray(var_type) || !lit_type.equals("None")) {
                int line = ctx.ASIG().getSymbol().getLine();
                int col = ctx.ASIG().getSymbol().getCharPositionInLine() + 3;
                System.err.printf("<%d, %d> Error Semantico,Tipos diferentes de variables: el tipo de la variable "+var_name+" es de tipo "+var_type+" y el del literal "+lit+" es "+lit_type, line, col);
                System.exit(-1);
            }
        }
        localVariables.put(var_name, var_type + "¿" + lit);
        Sout(localVariables.get(var_name));
        imprimirTable();
        return (T) ("Termine visitVar_def");
    }

    @Override
    public T visitTyped_var_id(ChocopyParser.Typed_var_idContext ctx) {
        HashMap<String,String> localVariables = stackTables.peek();
        String tipo = ctx.type().getText();
        if (localVariables.containsKey(ctx.ID().getText())) {
            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + ctx.ID().getText() + "\" ya ha sido declarada", line, col);
            System.exit(-1);
        } else {
            Sout("Se agregó la variable: " + ctx.ID().getText() + " con valor: "+ tipo);
            localVariables.put(ctx.ID().getText(), tipo + "¿");
        }
        return (T) (ctx.ID().getText() + ":" + tipo);
    }

    @Override
    public T visitTyped_var_self(ChocopyParser.Typed_var_selfContext ctx) {
        Stack<HashMap<String,String>> tmp = new Stack<>();
        for (int i = 0; i < stackTables.size(); i++) {
            tmp.push(stackTables.pop());
        }

        String tipo = ctx.type().getText();
        if (!tmp.peek().containsKey(tipo)) {
            int line = ctx.DOS_PUNTOS().getSymbol().getLine();
            int col = ctx.DOS_PUNTOS().getSymbol().getCharPositionInLine() + 3;

            System.err.printf("<%d, %d> Error Semantico, la clase nombrada: " + tipo + " no ha sido declarada", line, col);
            System.exit(-1);

        }
        for (int i = 0; i < tmp.size(); i++) {
            stackTables.push(tmp.pop());
        }
        return (T)"retorno de visitTyped_var_self";
    }

    @Override
    public T visitFunc_body(ChocopyParser.Func_bodyContext ctx) {
        HashMap<String,Object> localHash = new HashMap<>(); //crear un nuevo contexto local
        HashMap<String,String> localVaribles = new HashMap<>(); //crear un nuevo contexto local

        for(Map.Entry<String, Object> entry :  stackContext.peek().entrySet()) {
            String key = entry.getKey();
            if (funArguments.containsKey(key)){
                ArrayList<String> funArgumTmp = funArguments.get(key);
                for (int i = 0; i < funArgumTmp.size(); i++) {
                    String[] tmp = funArgumTmp.get(i).split("¿");
                    localVaribles.put(tmp[0], tmp[1] + "¿"+ tmp[2]);
                }
            }
        }

        stackContext.push(localHash);
        stackTables.push(localVaribles);

        int size = ctx.var_def().size();
        Sout("Soy un Func Body" + size);
        for (int i = 0; i < size; i++) {
            Sout("Soy un Var def: "+ ctx.var_def(i).getText());
            visit(ctx.var_def(i));
        }

        //TODO FALTAN LAS DEMAS DECLARACIONES global nonlocal

        // visitar las declaraciones de funciones
        int nFunciones = ctx.func_def().size();

        for (int i = 0; i < nFunciones; i++) {
            visit(ctx.func_def(i));
        }

        size = ctx.stmt().size();
        //Sout(ctx.func_def(0).getText());
        Sout("Encontrado Bug " + size + " "+ ctx.stmt().get(0).getText());
        for (int i = 0; i < size; i++) {
            try {
                Object tmp = visit(ctx.stmt(i));
                Sout("El stmt me arrojó como return: " + tmp.toString());
                String ans = tmp.toString();
                String[] retorno = ans.split("¿");
                if (retorno[0].equals("return")) {
                    try {
                        String x = retorno[1];
                        stackTables.pop();
                        stackContext.pop();
                        return (T) x;
                    } catch (Exception e) {
                        stackTables.pop();
                        stackContext.pop();
                        return (T) "$";
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        stackTables.pop();
        stackContext.pop();
        return (T)"retorno de la funcion visitFunc_body";
        //return super.visitFunc_body(ctx);
    }

    @Override
    public T visitFunc_def(ChocopyParser.Func_defContext ctx) {
        Sout("ESTOY DEFININEDO UNA FUNCION");
        //Agrega el id de la funcion a la tabla local
        String idFun = ctx.ID().getText();
        HashMap<String,Object> localHash = stackContext.peek(); //traer el contexto local

        if(localHash.containsKey(idFun)){    //Si la tabla ya contiene el id es que la funcion ya fue declarada
            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;

            System.err.printf("<%d, %d> Error Semantico, la funcion con nombre: \"" + idFun + "\" ya fue declarada", line, col);
            System.exit(-1);
        }else{      //No ha sido declarada por lo tanto se va a agregar
            localHash.put(idFun, ctx); // se guarda el contexto para futuros usos de la funcion
            HashMap<String,Object> localcontext = new HashMap<>(); //crear un nuevo contexto local
            HashMap<String,String> localvariables = new HashMap<>(); //crear un nuevo contexto local
            stackContext.push(localcontext);
            stackTables.push(localvariables);
            Sout("Se Aumentó el tamaño de los stacks, size: "+ stackContext.size());

//            for (int i = 0; i < ctx.typed_var().size(); i++) {
//                visit(ctx.typed_var(i));
//            }
            imprimirTable();

            Sout("Se esta perdiendo estos valores");


            for (int i = 0; i < ctx.func_body().func_def().size(); i++) {
                visit(ctx.func_body().func_def(i));
            }
            for (int i = 0; i < ctx.func_body().var_def().size(); i++) {
                visit(ctx.func_body().var_def(i));
            }
            stackContext.pop();
            stackTables.pop();
            Sout("Se Redujo el tamaño de los stacks, size: "+ stackContext.size());
            //TODO VERIFICAR TODAS LAS DECLARACIONES

        }
        Sout("TERMINE DE DEFINIR UNA FUNCION");
        return (T)"retorno de visitFunc_def";
    }

    @Override
    public T visitClass_body_var_func(ChocopyParser.Class_body_var_funcContext ctx) {
        HashMap<String,Object> localHash = new HashMap<>(); //crear un nuevo contexto local
        HashMap<String,String> localVariables = new HashMap<>(); //crear un nuevo contexto local
        stackContext.push(localHash);
        stackTables.push(localVariables);
        super.visitClass_body_var_func(ctx);
        stackContext.pop();
        stackTables.pop();
        return (T) "retorno de la funcion visitClass_body_var_func";
    }

    @Override
    public T visitClass_def(ChocopyParser.Class_defContext ctx) {
        //Agrega el id de la clase a la tabla local
        String idClass = ctx.ID(0).getText();
        HashMap<String,Object> localHash = stackContext.peek(); //traer el contexto local

        if(localHash.containsKey(idClass)){    //Si la tabla ya contiene el id es que la clase ya fue declarada
            int line = ctx.ID(0).getSymbol().getLine();
            int col = ctx.ID(0).getSymbol().getCharPositionInLine() + 1;

            System.err.printf("<%d, %d> Error Semantico, el id: \"" + idClass + "\" ya fue declarado en este scope", line, col);
            System.exit(-1);
        }else{      //No ha sido declarada por lo tanto se va a agregar
            String herencia = ctx.ID(1).getText();
            if (herencia.equals("object") || localHash.containsKey(herencia)){
                //verificar que la clase de la que hereda exista
                //Si existe agregar la nueva clase a la tabla
                localHash.put(idClass, ctx); // se guarda el contexto para futuros usos
                return visit(ctx.class_body());
            }else{
                //si no, existe error
                int line = ctx.ID(1).getSymbol().getLine();
                int col = ctx.ID(1).getSymbol().getCharPositionInLine() + 1;

                System.err.printf("<%d, %d> Error Semantico, No se puede heredar de la clase con nombre: \"" + herencia + "\", porque no existe" , line, col);
                System.exit(-1);
            }
        }

        return super.visitClass_def(ctx);
    }

    @Override
    public T visitIndex_expr_cexpr(ChocopyParser.Index_expr_cexprContext ctx) {
        String cexpr = visit(ctx.cexpr()).toString(); // va y busca que es cexxpr y toma su valor de arreglo
        //si quiere que le salga el nombre de la variable que esta antes de [ simplemente ctx.cexpr().toString()
        //pero no lo haga acá todo tiene la logica del valor de cexpr
        Sout("CEXPR DE DONDE VIENE "+cexpr);
        String index = visit(ctx.expr()).toString();
        String index_type = type(index);
        if(!index_type.equals("int")){
            int line = ctx.COR_DER().getSymbol().getLine();
            int col = ctx.COR_DER().getSymbol().getCharPositionInLine() -1;
            System.err.printf("<%d, %d> Error semantico: entre [] se encontro "+index+" de tipo "+index_type+" y solo se puede poner algo de tipo int ", line, col);
            System.exit(-1);
        }
        int i = Integer.parseInt(index);
        if(i>=cexpr.length()){ //CUIDADO: INDEXOUTOFBOUNDS
            // LO QUE LLEGA A ESTE VISITOR EN CEXPR SON COSAS DE TIPO [ 1, 2, 3] O "STRINGS" O None
            int line = ctx.COR_DER().getSymbol().getLine();
            int col = ctx.COR_DER().getSymbol().getCharPositionInLine() -1;
            System.err.printf("<%d, %d> Error semantico: Indice  "+index+" fuera de rango "+cexpr.length(), line, col);
            System.exit(-1);
        }

        Sout(type(cexpr));
        if(!isArray(cexpr)){
            int line = ctx.COR_IZQ().getSymbol().getLine();
            int col = ctx.COR_IZQ().getSymbol().getCharPositionInLine() -1;
            System.err.printf("<%d, %d> Error semantico: no se pude asignar a un subindice de algo que no es una lista"+cexpr.length(), line, col);
            System.exit(-1);
        }else{
            String id = ctx.cexpr().getText();
            if (id.charAt(0)!='[') { // si es igual es algode tipo [2,3][0] = 2 y eso se puede simplemente ignorar
                Sout(id+"¡"+cexpr+"¡"+i);
                return (T)(id+"¡"+cexpr+"¡"+i);
            }
        }

        //TODO: LÉEME MUY IMPORTANTE, VOY A DEJAR UN ERROR ABAJO DE ESTO QUE NO TE DEJE COMPILAR PARA QUE ME LEAS
        //CHOCO-WHAT
        // 1. VERIFICAR QUE ES LO QUE ESTA DEVOLVIENDO CEXPR
        // 2. VERIFICAR COMO HACER ASIGNACIÓN EN INDICES DE ARREGLOS Y VER SI SE PUEDE HACER CON STRINGS EN CHOCOPY (esto es en stmt_asign)
        //formato voy a devolver nombrevariable¡valotvariable¡indice a modificar/evaluar
        // -> IDEA: SI SE LLEGA A NECESITAR EL VALOR DE LA VARIABLE EN LA POSICIÓN I: RETORNEN nombrevariable¡valorvariable¡indice a modificar/evaluar¡VALOR EVALUADO
        // PERO DEBEN TENER CUIDADO: SI ES UN STRING DEBEN VER QUE PASA CON LAS COMILLAS y usar charAt()
        // SI ES UN ARREGLO, DEBEN TENER EN CUENTA LOS ESPACIOS ENTRE ELEMENTOS
        //(LOS ARREGLOS SE ESTÁN GUARDANDO DE LA SIGUIENTE MANERA  ARR [ELEMENTO1 ,ELEMENTO2 ,ELEMENTO3 ,...])<-NOTEN EL ESPACIO ENTRE ELEMENTOS.
        //POR LO QUE HAY UNA SOLUCIÓN QUE CASI SIRVE: String arr [] = ARR.substring(1,ARR.length()-1).Replace(" ", "").split(",")<- esto devolveria un arreglo
        //en el que podamos acceder directamente al valor del indice i sin tener un espacio de más [1 ,2 ,3] -> [["1!],["2"],["3"]]
        // el problema es si tengo un arreglo de Strings tipo arr = ["Hola mundo"  ,"Como vamos" ]
        //si se le hace la solución estaría quedando  [["Holamundo"]  ,["Comovamos"] ], es decir lo que queremos,
        // pero nuestros elementos strings pierden los espacios, es el único caso especial
        //    return (T) (ctx.cexpr().toString()+"¡"cexpr+"¡"+i); // Si le llego x[0] x->[1 ,2 ,3] está devolviendo [1 ,2 ,3] y el indice que está en expr; digamos 0
        return (T)"retorno algo que nunca deberia pasar";
        // x[0]-> 1
    }

}































