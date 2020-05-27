import org.antlr.v4.runtime.RuleContext;

import java.net.SocketImpl;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyVisitor<T> extends ChocopyBaseVisitor<T> {
    //🐍 Chococripsy Epicness Interpreter 🐍🐍
    Stack<HashMap<String, String>> stackTables = new Stack<>(); //stack de contextos para variables
    Stack<HashMap<String, Object>> stackContext = new Stack<>(); //stack de contextos
    HashMap<String, ArrayList<String>> funArguments = new HashMap<>();
    HashMap<String, Object> classTypes = new HashMap<>();
    HashMap<String, Object> variablesClass = new HashMap<>();

    Boolean flag = false;

    public String[] getTypeValue(String key) {
        String tp = stackTables.peek().get(key);
        String[] typeValue = tp.split("¿");
        return typeValue;
    }

    public void Sout(String str) {
        if (flag) {
            System.out.println(str);
        }
    }

    //FUNCIÓN AUXILIAR PARA TYPE, PARA CONOCER EL TIPO DE LOS ARREGLOS
    public String type_arr(String[] data_arr) {
        String first_type = type(data_arr[0].replace(" ", ""));
        for (int i = 1; i < data_arr.length; i++) {
            String ith_type = type(data_arr[i].replace(" ", ""));
            if (!ith_type.equals(first_type))
                return "[object]";
        }
        return "[" + first_type + "]";
    }

    public String type(String data) {
        String result = "";
        try {
            int number = Integer.parseInt(data);
            result = "int";
        } catch (Exception notInteger) {
            if (data.toLowerCase().equals("true") || data.toLowerCase().equals("false")) {
                result = "bool";
            } else {
                if (data.charAt(0) == '"' && data.charAt(data.length() - 1) == '"') {
                    result = "str";
                } else if (data.charAt(0) == '[' && data.charAt(data.length() - 1) == ']') {
                    String[] data_arr = data.substring(1, data.length() - 1).split(",");
                    result = type_arr(data_arr);
                } else {
                    if (data.equals("None")) {
                        result = "None";
                    } else {

                        if (data.equals("retorno de la funcion visitFunc_body") || data.equals("null")) {
                            result = "Retorno Vacío de Funcion";
                        } else {
                            result = "ERROR";
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean isArray(String possibleArr) {
        if (possibleArr.charAt(0) == '[' && possibleArr.charAt(possibleArr.length() - 1) == ']')
            return true;
        return false;
    }

    public void imprimirTable() {
        for (Map.Entry<String, String> entry : stackTables.peek().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

        }
    }

    //FUNCIONES VISITOR
    @Override
    public T visitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx) {
        HashMap<String, String> localVariables = stackTables.peek();
        String name = ctx.ID().getText();
        String all = ctx.getText();

        if (ctx.PAR_IZQ() != null) {
            HashMap<String, Object> localHash = stackContext.peek(); //para funciones clases
            HashMap<String, String> localTable = stackTables.peek(); //para variables
            if (!localHash.containsKey(name)) {
                int line = ctx.ID().getSymbol().getLine();
                int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, la funcion con nombre: \"" + name + "\" no ha sido declarada", line, col);
                System.exit(-1);
            } else {
                ArrayList<String> local_vars = new ArrayList<>();

                ChocopyParser.Func_defContext context = (ChocopyParser.Func_defContext) localHash.get(name);
                if (context.typed_var().size() == ctx.expr().size()) {
                    ArrayList<String> argumentos = new ArrayList<>();

                    for (int i = 0; i < context.typed_var().size(); i++) {
                        String[] idTipo = context.typed_var(i).getText().split(":");
                        argumentos.add(idTipo[0] + "¿" + idTipo[1] + "¿" + visit(ctx.expr(i)).toString());
                    }
                    funArguments.put(context.ID().getText(), argumentos);
                } else {
                    int line = ctx.PAR_IZQ().getSymbol().getLine();
                    int col = ctx.PAR_IZQ().getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, el número de parametros no es correcto", line, col);
                    System.exit(-1);
                }
                String vis = visit(context.func_body()).toString(); // Valor de vis
                String vis_type = type(vis); //Tipo de vis
                //Borrar Variables Locales
                for (int i = 0; i < local_vars.size(); i++) {
                    local_vars.remove(local_vars.get(i));
                }

                if (vis.equals("$") && context.type() != null) {
                    int line = ctx.ID().getSymbol().getLine();
                    int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, el número de parametros no es correcto", line, col);
                    System.exit(-1);
                } else {
                    if (context.type() == null && vis_type.equals("Retorno Vacío de Funcion")) {

                    } else if (context.type() == null && !vis_type.equals("Retorno Vacío de Funcion")) {
                        int line = ctx.PAR_IZQ().getSymbol().getLine();
                        int col = ctx.PAR_IZQ().getSymbol().getCharPositionInLine();
                        System.err.printf("<%d, %d> Error Semantico, el tipo de retorno y la expresion no son iguales para la funcion " + ctx.ID(), line, col);
                        System.exit(-1);
                    } else {

                        String type = context.type().getText();
                        if (!vis_type.equals(type)) {
                            int line = ctx.ID().getSymbol().getLine();
                            int col = ctx.ID().getSymbol().getCharPositionInLine();
                            System.err.printf("<%d, %d> Error Semantico, el tipo de retorno y la expresion no son iguales para la funcion " + ctx.ID(), line, col);
                            System.exit(-1);
                        } else if (vis_type.equals("ERROR")) {
                            int line = ctx.ID().getSymbol().getLine();
                            int col = ctx.ID().getSymbol().getCharPositionInLine();
                            System.err.printf("<%d, %d> Error Semantico, expresion  " + vis + " no tiene un tipo de dato valido" + ctx.ID(), line, col);
                            System.exit(-1);
                        }
                    }
                    return (T) vis;
                }
            }
        } else {
            if (funArguments.containsKey(ctx.ID().getText())) {
                ArrayList<String> funArgumTmp = funArguments.get(ctx.ID().getText());
                for (int i = 0; i < funArgumTmp.size(); i++) {
                    String[] tmp = funArgumTmp.get(i).split("¿");
                    localVariables.put(tmp[0], tmp[1] + "¿" + tmp[2]);
                }
            }
            if (!localVariables.containsKey(name)) {
                int line = ctx.ID().getSymbol().getLine();
                int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + name + "\" no ha sido declarada", line, col);
                System.exit(-1);
            } else {
                String id = getTypeValue(ctx.ID().getText())[1];
                return (T) id;
            }
        }
        return (T) "He terminado visitCexpr_p10_id";
    }

    @Override
    public T visitCexpr_p10_literal(ChocopyParser.Cexpr_p10_literalContext ctx) {
        String aux = ctx.literal().getText();
        return (T) aux;
    }

    @Override
    public T visitCexpr_p10_cor(ChocopyParser.Cexpr_p10_corContext ctx) {
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
        if (st.charAt(0) != '"' && st.charAt(0) != '[') {
            int line = ctx.LEN().getSymbol().getLine();
            int col = ctx.PAR_IZQ().getSymbol().getCharPositionInLine() + 2;
            System.err.printf("<%d, %d> Error Semantico, no se puede sacar len al objeto dado: " + st + " ", line, col);
            System.exit(-1);
        }
        if (st.charAt(0) == '"')
            ans = st.length() - 2;
        if (st.charAt(0) == '[') {
            ans = st.substring(1, st.length() - 1).split(",").length;
        }
        return (T) ((Integer) ans).toString();
    }

    @Override
    public T visitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx) {
        return (T) ctx.SELF();
    }

    @Override
    public T visitP10_cor(ChocopyParser.P10_corContext ctx) {
        String expr = visit(ctx.cexpr_p10()).toString();
        int size = ctx.expr().size();
        int[] returnIndex = new int[size];
        String expr_type = type(expr);
        if (!expr_type.equals("str") && !isArray(expr)) {
            if (expr_type.equals("None"))
                return (T) "None";
            int line = ctx.COR_IZQ(0).getSymbol().getLine();
            int col = ctx.COR_IZQ(0).getSymbol().getCharPositionInLine() - 1;
            System.err.printf("<%d, %d> Error Semantico, la variable no es de tipo str o arreglo, es de tipo " + expr_type, line, col);
            System.exit(-1);
        } else {
            for (int i = 0; i < size; i++) {
                String possibleIndex = visit(ctx.expr(i)).toString();
                if (!type(possibleIndex).equals("int")) {
                    int line = ctx.COR_IZQ(i).getSymbol().getLine();
                    int col = ctx.COR_IZQ(i).getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, no se puede acceder a una posicion que no sea entera\n" +
                            "   expresión dada  " + possibleIndex + " de tipo " + type(possibleIndex), line, col);
                    System.exit(-1);
                }
                returnIndex[i] = Integer.parseInt(visit(ctx.expr(i)).toString());
            }
            if (expr_type.equals("str")) {
                if (returnIndex[0] >= expr.length()) {
                    int line = ctx.COR_IZQ(0).getSymbol().getLine();
                    int col = ctx.COR_IZQ(0).getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, indice " + returnIndex[0] + " fuera de tamaño del arreglo " + expr.length(), line, col);
                    System.exit(-1);
                }
                return (T) ("" + expr.charAt(returnIndex[0] + 1));
            } else {
                String[] elements = expr.substring(1, expr.length() - 1).replace(" ", "").split(",");
                if (returnIndex[0] >= elements.length) {
                    int line = ctx.COR_IZQ(0).getSymbol().getLine();
                    int col = ctx.COR_IZQ(0).getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, indice " + returnIndex[0] + " fuera de tamaño del arreglo " + elements.length, line, col);
                    System.exit(-1);
                }

                return (T) elements[returnIndex[0]];
            }
        }
        return (T) ("He Terminado p10cor");
    }

    @Override
    public T visitP10_punto(ChocopyParser.P10_puntoContext ctx) {
        return super.visitP10_punto(ctx);
    }

    @Override
    public T visitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx) {
        String a = visit(ctx.cexpr_p8()).toString();
        String type_expr = type(a);
        if (!type_expr.equals("int")) {
            int line = ctx.MENOS().getSymbol().getLine();
            int col = ctx.MENOS().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, no se puede cambiar de signo a un objeto no entero, tipo de dato dado: " + type_expr + " ", line, col);
            System.exit(-1);
        }
        int number = Integer.parseInt(a);
        number *= -1;
        return (T) ((Integer) number).toString();
    }

    @Override
    public T visitCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx) {
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
                col = ctx.MULTIPLICACION().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.MULTIPLICACION().getSymbol().getCharPositionInLine() + 1;
            }
            int line = ctx.MULTIPLICACION().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se pueden multiplicar dos objetos de tipo " + type_a + " y " + type_b, line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a)) * (Integer.parseInt(b));
        return (T) ((Integer) (result)).toString();
    }

    @Override
    public T visitP7_div(ChocopyParser.P7_divContext ctx) {
        String a = visit(ctx.cexpr_p7()).toString();
        String b = visit(ctx.cexpr_p8()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.DIVISION().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.DIVISION().getSymbol().getCharPositionInLine() + 1;
            }
            int line = ctx.DIVISION().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se pueden dividir dos objetos de tipo " + type_a + " y " + type_b, line, col);
            System.exit(-1);
        }
        if (Integer.parseInt(b) == 0) {
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.DIVISION().getSymbol().getCharPositionInLine() - 2;
            } else {
                col = ctx.DIVISION().getSymbol().getCharPositionInLine() + 4;
            }
            int line = ctx.DIVISION().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, Math error, no se puede dividir por 0", line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a)) / (Integer.parseInt(b));
        return (T) ((Integer) result).toString();
    }


    @Override
    public T visitP7_mod(ChocopyParser.P7_modContext ctx) {
        String a = visit(ctx.cexpr_p7()).toString();
        String b = visit(ctx.cexpr_p8()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MODULO().getSymbol().getLine() - 1;
            } else {
                col = ctx.MODULO().getSymbol().getCharPositionInLine() + 3;
            }
            int line = ctx.MODULO().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se puede obtener el modulo de dos objetos de tipo " + type_a + " y " + type_b, line, col);
            System.exit(-1);
        } else if (b.equals("0")) {
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MODULO().getSymbol().getCharPositionInLine() - 1;
            } else {
                col = ctx.MODULO().getSymbol().getCharPositionInLine() + 3;
            }
            int line = ctx.MODULO().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se puede obtener el modulo 0 de un número", line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a)) % (Integer.parseInt(b));
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
        if (type_a.equals("str") && type_b.equals("str")) {
            //Concatenación de strs
            String result = a + b;
            result = result.replace("\"", "");
            result = "\"" + result + "\"";
            return (T) (result);
        } else if (isArray(a) && isArray(b)) {
            //concatenación de arrays
            String part_a = a.substring(0, a.length() - 1);
            String part_b = b.substring(1);
            String result = part_a + ", " + part_b;
            return (T) (result);
        } else if (type_a.equals("int") && type_b.equals("int")) {
            int result = Integer.parseInt(a) + Integer.parseInt(b);
            return (T) (Integer) result;
        } else {
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.SUMA().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.SUMA().getSymbol().getCharPositionInLine() + 1;
            }
            int line = ctx.SUMA().getSymbol().getLine();
            System.err.printf("<%d, %d> Error Semantico, no se pueden sumar dos objetos de tipo " + type_a + " y  " + type_b, line, col);
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
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int line = ctx.MENOS().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MENOS().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.MENOS().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden restar dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        int result = (Integer.parseInt(a)) - (Integer.parseInt(b));
        return (T) ((Integer) result).toString();
    }


    //Comparacion de enteros
    @Override
    public T visitCexpr_mayor(ChocopyParser.Cexpr_mayorContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int line = ctx.MAYOR().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MAYOR().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.MAYOR().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (>) dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a)) > (Integer.parseInt(b));
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_menor(ChocopyParser.Cexpr_menorContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int line = ctx.MENOR().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MENOR().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.MENOR().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (<) dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a)) < (Integer.parseInt(b));
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_mayor_igual(ChocopyParser.Cexpr_mayor_igualContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int line = ctx.MAYOR_IGUAL().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MAYOR_IGUAL().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.MAYOR_IGUAL().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (>=) dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a)) >= (Integer.parseInt(b));
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_menor_igual(ChocopyParser.Cexpr_menor_igualContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!type_a.equals("int") || !type_b.equals("int")) {
            int line = ctx.MENOR_IGUAL().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.MENOR_IGUAL().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.MENOR_IGUAL().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (<=) dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = (Integer.parseInt(a)) <= (Integer.parseInt(b));
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_igual(ChocopyParser.Cexpr_igualContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if ((!type_a.equals("int") || !type_b.equals("int")) && ((!type_a.equals("bool") || !type_b.equals("bool"))) && (!type_a.equals("str") || !type_b.equals("str"))) {
            int line = ctx.IGUAL().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.IGUAL().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.IGUAL().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (==) dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = a.equals(b);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitCexpr_diferente(ChocopyParser.Cexpr_diferenteContext ctx) {
        String a = visit(ctx.cexpr_p6(0)).toString();
        String b = visit(ctx.cexpr_p6(1)).toString();
        String type_a = type(a);
        String type_b = type(b);
        if ((!type_a.equals("int") || !type_b.equals("int")) && ((!type_a.equals("bool") || !type_b.equals("bool"))) && (!type_a.equals("str") || !type_b.equals("str"))) {
            int line = ctx.DIFERENTE().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("int")) {
                col = ctx.DIFERENTE().getSymbol().getCharPositionInLine() + 1;
            } else {
                col = ctx.DIFERENTE().getSymbol().getCharPositionInLine() + 1;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (!=) dos objetos de tipo " + type_a + " y  " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = !a.equals(b);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx) {
        String a = visit(ctx.expr_p4()).toString();
        String type_a = type(a);
        if (!(type_a).equals("bool")) {
            int line = ctx.NOT().getSymbol().getLine();
            int col = ctx.NOT().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, no se cambiar valor booleano (not) de objeto de tipo " + type_a, line, col);
            System.exit(-1);
        }
        boolean result = !Boolean.parseBoolean(a);
        return (T) ((Boolean) (result)).toString();
    }

    @Override
    public T visitP3_and(ChocopyParser.P3_andContext ctx) {
        String a = visit(ctx.expr_p3()).toString();
        String b = visit(ctx.expr_p4()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!(type_a).equals("bool") || !(type_b).equals("bool")) {
            int line = ctx.AND().getSymbol().getLine();
            line = ctx.AND().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("bool")) {
                col = ctx.AND().getSymbol().getCharPositionInLine() - ctx.expr_p3().getText().length();
            } else {
                col = ctx.AND().getSymbol().getCharPositionInLine() + 5;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (and) objetos de tipo " + type_a + " y " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = ((Boolean.parseBoolean(a)) && ((Boolean.parseBoolean(b))));
        return (T) ((Boolean) result).toString();
    }

    @Override
    public T visitP2_or(ChocopyParser.P2_orContext ctx) {
        String a = visit(ctx.expr_p2()).toString();
        String b = visit(ctx.expr_p3()).toString();
        String type_a = type(a);
        String type_b = type(b);
        if (!(type_a).equals("bool") || !(type_b).equals("bool")) {
            int line = ctx.OR().getSymbol().getLine();
            line = ctx.OR().getSymbol().getLine();
            int col = -1;
            if (!type_a.equals("bool")) {
                col = ctx.OR().getSymbol().getCharPositionInLine() - ctx.expr_p2().getText().length();
            } else {
                col = ctx.OR().getSymbol().getCharPositionInLine() + 4;
            }
            System.err.printf("<%d, %d> Error Semantico, no se pueden comparar (or) objetos de tipo " + type_a + " y " + type_b, line, col);
            System.exit(-1);
        }
        boolean result = ((Boolean.parseBoolean(a)) || ((Boolean.parseBoolean(b))));
        return (T) ((Boolean) result).toString();
    }

    @Override
    public T visitExpr_if(ChocopyParser.Expr_ifContext ctx) {
        String b = visit(ctx.expr(0)).toString();
        String type_b = type(b);
        if (!type_b.equals("bool")) {
            int line = ctx.IF().getSymbol().getLine();
            int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, evaluar if con expresion " + b + " ya que es de tipo " + type_b + " y no bool", line, col);
            System.exit(-1);
        }
        if (Boolean.parseBoolean(b)) {
            return (T) visit(ctx.expr_p2()).toString();
        } else {
            return (T) visit(ctx.expr(1)).toString();
        }
    }


    @Override
    public T visitStmtprint(ChocopyParser.StmtprintContext ctx) {
        if (!ctx.expr().getText().contains("\"") && ctx.expr().getText().contains(".")) {
            if (stackTables.peek().containsKey(ctx.expr().getText())) {
                if (stackTables.peek().get(ctx.expr().getText()).split("¿")[0].equals("bool")) {
                    if (stackTables.peek().get(ctx.expr().getText()).split("¿")[1].toLowerCase().equals("true")) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    return (T) "he salido de stmtprint";
                }
                String valor = stackTables.peek().get(ctx.expr().getText()).split("¿")[1];
                System.out.println(valor);
                return (T) "he salido de stmtprint";
            } else {
                int line = ctx.PAR_IZQ().getSymbol().getLine();
                int col = ctx.PAR_IZQ().getSymbol().getCharPositionInLine() + 2;
                System.err.printf("<%d, %d> Error Semantico, no se pudo obtener " + ctx.expr().getText(), line, col);
                System.exit(-1);
            }
        }
        String ex = visit(ctx.expr()).toString();
        String type_ex = type(ex);
        if (type_ex.equals("str")) {
            System.out.println(ex.substring(1, ex.length() - 1));
        } else if (isArray(ex)) {
            int line = ctx.PAR_IZQ().getSymbol().getLine();
            int col = ctx.PAR_IZQ().getSymbol().getCharPositionInLine() + 2;
            System.err.printf("<%d, %d> Error Semantico, No se puede imprimir un arreglo ", line, col);
            System.exit(-1);
        } else if (ex.equals("None")) {
            int line = ctx.PAR_IZQ().getSymbol().getLine();
            int col = ctx.PAR_IZQ().getSymbol().getCharPositionInLine() + 2;
            System.err.printf("<%d, %d> Error Semantico, Argumento invalido: None ", line, col);
            System.exit(-1);
        } else if (type_ex.equals("bool")) {
            if (ex.toLowerCase().equals("true")) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } else {
            System.out.println(ex);
        }
        return (T) ("Termine visitStmtprint");
    }

    @Override
    public T visitSimple_stmt_expr(ChocopyParser.Simple_stmt_exprContext ctx) {
        String visit = visit(ctx.expr()).toString();
        return (T) ("Ya termine visitSimple_stmt_expr");
    }

    @Override
    public T visitSimple_stmt_return(ChocopyParser.Simple_stmt_returnContext ctx) {
        if (ctx.expr() != null) {
            return (T) ("return¿" + visit(ctx.expr()).toString());
        } else {
            return (T) "return¿null"; //Si return tiene de valor null romper contexto
        }
    }

    @Override
    public T visitSimple_stmt_asig(ChocopyParser.Simple_stmt_asigContext ctx) {
        HashMap<String, String> localVariables = stackTables.peek();
        int size = ctx.target().size();
        ArrayList<String> targetList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String target = visit(ctx.target(i)).toString();
            if (target.contains("¡")) {
                String[] target_varindex = target.split("¡");
                String target_name = target_varindex[0];
                if (localVariables.containsKey(target_name)) {
                    targetList.add(target);
                } else {
                    int line = ctx.ASIG(i).getSymbol().getLine();
                    int col = ctx.ASIG(i).getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, no se ha declarado la variable " + target_name + " ", line, col);
                    System.exit(-1);
                }
            } else if (target.contains("ñ")) {
                String[] arrtmp = target.split("ñ");
                ChocopyParser.Class_defContext contexto = (ChocopyParser.Class_defContext) variablesClass.get(arrtmp[0]);
                String atributo = arrtmp[1];
                HashMap<String, String> classVariables = (HashMap<String, String>) visit(contexto.class_body());
                if (!classVariables.containsKey(atributo)) {
                    int line = ctx.ASIG(i).getSymbol().getLine();
                    int col = ctx.ASIG(i).getSymbol().getCharPositionInLine() + 1;
                    System.err.printf("<%d, %d> Error Semantico, \"" + atributo + "\" No es un atributo válido", line, col);
                    System.exit(-1);
                } else {
                    String newTarget = arrtmp[0] + "." + atributo;
                    String valor = classVariables.get(atributo);
                    localVariables.put(newTarget, valor);
                    targetList.add(newTarget);
                }
            } else if (localVariables.containsKey(target)) {
                targetList.add(target);
            } else {
                int line = ctx.ASIG(i).getSymbol().getLine();
                int col = ctx.ASIG(i).getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, no se ha declarado la variable " + target + " ", line, col);
                System.exit(-1);
            }
        }

        for (int i = targetList.size() - 1; i >= 0; i--) {
            String target = targetList.get(i);
            if (target.contains("¡")) {
                String[] target_varindex = target.split("¡");
                String target_name = target_varindex[0];
                String array = target_varindex[1];
                int target_index = Integer.parseInt(target_varindex[2]);

                String[] targetArr = array.substring(1, array.length() - 1).replace(" ", "").split(",");
                targetArr[target_index] = visit(ctx.expr()).toString();
                String ans = "[";
                for (int j = 0; j < targetArr.length - 1; j++) {
                    ans = ans + targetArr[j] + ",";
                }
                ans = ans + targetArr[targetArr.length - 1] + "]";
                localVariables.put(target_name, type(array) + "¿" + ans);
            } else {
                String texto = ctx.expr().getText();
                if (texto.contains("()") && classTypes.containsKey(texto.substring(0, texto.length() - 2))) {
                    variablesClass.put(target, classTypes.get(texto.substring(0, texto.length() - 2)));
                } else {
                    String[] typeValue = getTypeValue(target);
                    String type = typeValue[0];
                    String visit = visit(ctx.expr()).toString();
                    String visit_type = type(visit);
                    if (visit.equals("ERROR")) {
                        int line = ctx.ASIG(i).getSymbol().getLine();
                        int col = ctx.ASIG(i).getSymbol().getCharPositionInLine() + 1;
                        System.err.printf("<%d, %d> Error Semantico, el tipo de la variable " + visit + " no es valido", line, col);
                        System.exit(-1);
                    }
                    if (!visit_type.equals(type)) {
                        if (type.equals("object") || type.equals("[object]")) {
                            localVariables.put(target, type + "¿" + visit);
                        } else {
                            int line = ctx.ASIG(i).getSymbol().getLine();
                            int col = ctx.ASIG(i).getSymbol().getCharPositionInLine() + 1;
                            System.err.printf("<%d, %d> Error Semantico, tipos diferentes de datos\n la variable: " + target + " es de tipo " + type + " y la expresion " + visit + " a asignar es de tipo " + visit_type, line, col);
                            System.exit(-1);
                        }
                    }
                    if (visit_type.equals("int")) {
                        int number = Integer.parseInt(visit);
                        localVariables.put(target, "int¿" + number);
                    } else if (visit_type.equals("bool")) {
                        localVariables.put(target, "bool¿" + visit);
                    } else if (visit_type.equals("str")) {
                        localVariables.put(target, "str¿" + visit);
                    } else if (isArray(visit_type)) {
                        if (visit_type.equals(type)) {
                            localVariables.put(target, type + "¿" + visit);
                        } else {

                        }
                    }
                }
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
        int size = ctx.stmt().size();
        for (int i = 0; i < size; i++) {
            visit(ctx.stmt(i));
        }
        return (T) ("Termine visitBlock");
    }

    @Override
    public T visitStmt_simple_stmt(ChocopyParser.Stmt_simple_stmtContext ctx) {
        return (T) super.visit(ctx.simple_stmt());
    }

    @Override
    public T visitStmt_if(ChocopyParser.Stmt_ifContext ctx) {
        String con1 = visit(ctx.expr(0)).toString();
        String type_con1 = type(con1);
        if (!type_con1.equals("bool")) {
            int line = ctx.IF().getSymbol().getLine();
            int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, el tipo de la variable a evaluar en el if " + con1 + " no es bool, tipo de variable " + type_con1, line, col);
            System.exit(-1);
        }
        if (Boolean.parseBoolean(con1)) {
            return (T) visit(ctx.block(0));
        } else {
            int size = ctx.expr().size();
            for (int i = 1; i < size; i++) {
                String coni = visit(ctx.expr(i)).toString();
                String type_coni = type(coni);
                if (!type_coni.equals("bool")) {
                    int line = ctx.IF().getSymbol().getLine();
                    int col = ctx.IF().getSymbol().getCharPositionInLine() + 4;
                    System.err.printf("<%d, %d> Error Semantico, el tipo de la variable a evaluar en el if " + coni + " no es bool, tipo de variable " + type_coni, line, col);
                    System.exit(-1);
                }
                if (Boolean.parseBoolean(coni))
                    return (T) visit(ctx.block(i));
            }
            if (ctx.ELSE() != null) {
                return (T) visit(ctx.block(ctx.block().size() - 1));
            }
        }
        return (T) ("Termine visitStmt_if");
    }

    @Override
    public T visitStmt_while(ChocopyParser.Stmt_whileContext ctx) {
        String con = visit(ctx.expr()).toString();
        String type_con = type(con);
        if (!type_con.equals("bool")) {
            int line = ctx.WHILE().getSymbol().getLine();
            int col = ctx.WHILE().getSymbol().getCharPositionInLine() + 6;
            System.err.printf("<%d, %d> Error Semantico, el tipo de la variable a evaluar en el condicional while " + con + " no es bool, tipo de variable " + type_con, line, col);
            System.exit(-1);
        }
        while (Boolean.parseBoolean(con)) {
            visit(ctx.block());
            con = (visit(ctx.expr()).toString());
        }
        return (T) ("Termine un visitStmt_while");
    }

    @Override
    public T visitStmt_for(ChocopyParser.Stmt_forContext ctx) {
        HashMap<String, String> localVariables = stackTables.peek();
        String visit = visit(ctx.expr()).toString();
        String tipo_tmp = "";
        String type_visit = type(visit);
        String id = ctx.ID().toString();
        if (!localVariables.containsKey(id)) {
            int line = ctx.ID().getSymbol().getCharPositionInLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, " + id + " no ha sido declarado", line, col);
            System.exit(-1);
        }
        if (!type_visit.equals(("str")) && !isArray(visit)) {
            int line = ctx.IN().getSymbol().getLine();
            int col = ctx.IN().getSymbol().getCharPositionInLine() + 4;
            System.err.printf("<%d, %d> Error Semantico, Solo se permiten listas con int o bool o object o un String y la expresion " + visit + " tiene tipo " + type_visit, line, col);
            System.exit(-1);
        }

        String type_id = localVariables.get(id).split("¿")[0];
        if (!type_visit.equals(type_id)) {
            if (!type_visit.equals("[" + type_id + "]")) {
                int line = ctx.ID().getSymbol().getLine();
                int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, " + id + " no es del mismo tipo que " + visit + " tipo de id: " + type_id + " tipo de expresion " + visit, line, col);
                System.exit(-1);
            }
        }
        if (type_visit.equals("str")) {
            visit = visit.replace("\"", "");
            for (int i = 0; i < visit.length(); i++) {
                localVariables.put(id, "str¿" + visit.charAt(i));
                visit(ctx.block());
            }
        } else if (isArray(visit)) {
            String tmp = visit.substring(1, visit.length() - 1);
            String[] arr = tmp.split(",");
            String ith_type = type_visit.substring(1, type_visit.length() - 1);
            for (int i = 0; i < arr.length; i++) {
                localVariables.put(id, ith_type + "¿" + arr[i].replace(" ", ""));
                visit(ctx.block());
            }
        }
        return (T) ("Termine visitStmt_for");
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
        HashMap<String, String> localVariables = stackTables.peek();
        String var_def = visit(ctx.typed_var()).toString();
        String lit = ctx.literal().getText();
        String[] tmp = var_def.split(":");
        String var_type = tmp[1];
        String var_name = tmp[0];
        if (!var_type.equals("object") && !var_type.equals("[object]")) {
            String lit_type = type(lit);
            if (!lit_type.equals(var_type)) {
                if (classTypes.containsKey(var_type) && lit_type == "None") {

                } else {
                    if (isArray(var_type) && (var_type.equals(lit_type) || lit_type.equals("ERROR"))) {
                        int line = ctx.ASIG().getSymbol().getLine();
                        int col = ctx.ASIG().getSymbol().getCharPositionInLine() + 1;
                        System.err.printf("<%d, %d>  Error Semantico, variable " + var_name + " de tipo " + var_type + " y solo se puede inicializar con None", line, col);
                        System.exit(-1);
                    }
                    if (!isArray(var_type) || !lit_type.equals("None")) {
                        int line = ctx.ASIG().getSymbol().getLine();
                        int col = ctx.ASIG().getSymbol().getCharPositionInLine() + 1;
                        System.err.printf("<%d, %d> Error Semantico,Tipos diferentes de variables: el tipo de la variable " + var_name + " es de tipo " + var_type + " y el del literal " + lit + " es " + lit_type, line, col);
                        System.exit(-1);
                    }
                }
            }
        }
        localVariables.put(var_name, var_type + "¿" + lit);
        return (T) ("Termine visitVar_def");
    }

    @Override
    public T visitTyped_var_id(ChocopyParser.Typed_var_idContext ctx) {
        HashMap<String, String> localVariables = stackTables.peek();
        String tipo = ctx.type().getText();

        if (localVariables.containsKey(ctx.ID().getText())) {
            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + ctx.ID().getText() + "\" ya ha sido declarada", line, col);
            System.exit(-1);
        }
        if (!tipo.equals("int") && !tipo.equals("str") && !tipo.equals("bool") && !tipo.equals("object") &&
                !tipo.equals("[int]") && !tipo.equals("[str]") && !tipo.equals("[bool]") && !tipo.equals("[object]") && !classTypes.containsKey(tipo)) {
            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, tipo no válido", line, col);
            System.exit(-1);
        }
        if (classTypes.containsKey(tipo)) {
            variablesClass.put(ctx.ID().getText(), classTypes.get(tipo));
        } else {
            localVariables.put(ctx.ID().getText(), tipo + "¿");
        }


        return (T) (ctx.ID().getText() + ":" + tipo);
    }

    @Override
    public T visitTyped_var_self(ChocopyParser.Typed_var_selfContext ctx) {
        Stack<HashMap<String, String>> tmp = new Stack<>();
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
        return (T) "retorno de visitTyped_var_self";
    }

    @Override
    public T visitFunc_body(ChocopyParser.Func_bodyContext ctx) {
        HashMap<String, Object> localHash = new HashMap<>(); //crear un nuevo contexto local que tiene que ser copia del anterior
        HashMap<String, String> localVaribles = new HashMap<>(); //crear un nuevo contexto local
        imprimirTable();
        for (Map.Entry<String, Object> entry : stackContext.peek().entrySet()) {
            String key = entry.getKey();
            if (funArguments.containsKey(key)) {
                ArrayList<String> funArgumTmp = funArguments.get(key);
                for (int i = 0; i < funArgumTmp.size(); i++) {
                    String[] tmp = funArgumTmp.get(i).split("¿");
                    localVaribles.put(tmp[0], tmp[1] + "¿" + tmp[2]);
                }
            }
        }

        stackContext.push(localHash);
        stackTables.push(localVaribles);

        int size = ctx.var_def().size();
        for (int i = 0; i < size; i++) {
            visit(ctx.var_def(i));
        }

        // visitar las declaraciones de funciones
        int nFunciones = ctx.func_def().size();

        for (int i = 0; i < nFunciones; i++) {
            visit(ctx.func_def(i));
        }

        size = ctx.stmt().size();
        for (int i = 0; i < size; i++) {
            try {
                Object tmp = visit(ctx.stmt(i));
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
        return (T) "retorno de la funcion visitFunc_body";
    }

    @Override
    public T visitFunc_def(ChocopyParser.Func_defContext ctx) {
        String idFun = ctx.ID().getText();
        HashMap<String, Object> localHash = stackContext.peek();
        if (localHash.containsKey(idFun)) {
            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, la funcion con nombre: \"" + idFun + "\" ya fue declarada", line, col);
            System.exit(-1);
        } else {      //No ha sido declarada por lo tanto se va a agregar
            localHash.put(idFun, ctx); // se guarda el contexto para futuros usos de la funcion
            HashMap<String, Object> localcontext = new HashMap<>(); //crear un nuevo contexto local que tiene que ser copia del anterior
            HashMap<String, String> localvariables = new HashMap<>(); //crear un nuevo contexto local
            stackContext.push(localcontext);
            stackTables.push(localvariables);

            for (int i = 0; i < ctx.func_body().func_def().size(); i++) {
                visit(ctx.func_body().func_def(i));
            }
            for (int i = 0; i < ctx.func_body().var_def().size(); i++) {
                visit(ctx.func_body().var_def(i));
            }
            stackContext.pop();
            stackTables.pop();
        }

        return (T) "retorno de visitFunc_def";
    }

    @Override
    public T visitClass_body_var_func(ChocopyParser.Class_body_var_funcContext ctx) {
        HashMap<String, Object> localHash = new HashMap<>(); //crear un nuevo contexto local que tiene que ser copia del anterior
        HashMap<String, String> localVariables = new HashMap<>(); //crear un nuevo contexto local
        stackContext.push(localHash);
        stackTables.push(localVariables);
        super.visitClass_body_var_func(ctx);
        HashMap<String, String> returnVariables = stackTables.peek();
        stackContext.pop();
        stackTables.pop();
        return (T) returnVariables;
    }

    @Override
    public T visitClass_def(ChocopyParser.Class_defContext ctx) {
        //Agrega el id de la clase a la tabla local
        String idClass = ctx.ID(0).getText();
        HashMap<String, Object> localHash = stackContext.peek(); //traer el contexto local
        if (localHash.containsKey(idClass)) {    //Si la tabla ya contiene el id es que la clase ya fue declarada
            int line = ctx.ID(0).getSymbol().getLine();
            int col = ctx.ID(0).getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error Semantico, el id: \"" + idClass + "\" ya fue declarado en este scope", line, col);
            System.exit(-1);
        } else {      //No ha sido declarada por lo tanto se va a agregar
            String herencia = ctx.ID(1).getText();
            if (herencia.equals("object") || classTypes.containsKey(herencia)) {
                //verificar que la clase de la que hereda exista
                //Si existe agregar la nueva clase a la tabla
                classTypes.put(idClass, ctx);
                localHash.put(idClass, ctx); // se guarda el contexto para futuros usos
                visit(ctx.class_body());
                return (T) "Estoy saliendo de classdef";
            } else {
                //si no, existe error
                int line = ctx.ID(1).getSymbol().getLine();
                int col = ctx.ID(1).getSymbol().getCharPositionInLine() + 1;
                System.err.printf("<%d, %d> Error Semantico, No se puede heredar de: \"" + herencia + "\", tipo inválido", line, col);
                System.exit(-1);
            }
        }

        return super.visitClass_def(ctx);
    }

    @Override
    public T visitIndex_expr_cexpr(ChocopyParser.Index_expr_cexprContext ctx) {
        String cexpr = visit(ctx.cexpr()).toString(); // va y busca que es cexpr y toma su valor de arreglo
        String index = visit(ctx.expr()).toString();
        String index_type = type(index);
        if (!index_type.equals("int")) {
            int line = ctx.COR_DER().getSymbol().getLine();
            int col = ctx.COR_DER().getSymbol().getCharPositionInLine() - 1;
            System.err.printf("<%d, %d> Error semantico: entre [] se encontro " + index + " de tipo " + index_type + " y solo se puede poner algo de tipo int ", line, col);
            System.exit(-1);
        }
        int i = Integer.parseInt(index);
        if (i >= cexpr.length()) {
            int line = ctx.COR_DER().getSymbol().getLine();
            int col = ctx.COR_DER().getSymbol().getCharPositionInLine() - 1;
            System.err.printf("<%d, %d> Error semantico: Indice  " + index + " fuera de rango " + cexpr.length(), line, col);
            System.exit(-1);
        }

        if (!isArray(cexpr)) {
            int line = ctx.COR_IZQ().getSymbol().getLine();
            int col = ctx.COR_IZQ().getSymbol().getCharPositionInLine() + 2;
            System.err.printf("<%d, %d> Error semantico: no se pude asignar a un subindice de algo que no es una lista ", line, col);
            System.exit(-1);
        } else {
            String id = ctx.cexpr().getText();
            if (id.charAt(0) != '[') {
                return (T) (id + "¡" + cexpr + "¡" + i);
            }
        }
        return (T) "retorno algo que nunca deberia pasar";
    }

    @Override
    public T visitMember_expr_cexpr(ChocopyParser.Member_expr_cexprContext ctx) {
        String cexpr = visit(ctx.cexpr()).toString();

        if (cexpr.equals("None") && variablesClass.containsKey(ctx.cexpr().getText())) {
            return (T) (ctx.cexpr().getText() + "ñ" + ctx.ID().getText());
        } else {
            int line = ctx.PUNTO().getSymbol().getLine();
            int col = ctx.PUNTO().getSymbol().getCharPositionInLine() + 1;
            System.err.printf("<%d, %d> Error semantico: No se puede acceder a " + ctx.cexpr().getText() + "." + ctx.ID().getText(), line, col);
            System.exit(-1);
        }


        return (T) "Estoy saliendo de visitMember_expr_cexpr";
    }
}