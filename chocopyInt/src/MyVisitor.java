import java.util.ArrayList;
import java.util.HashMap;

public class MyVisitor<T> extends ChocopyBaseVisitor<T> {

    HashMap<String, Object> table = new HashMap<>();

    Boolean flag = true;

    public void agregar(HashMap<String, Object> table){
        table.put("a", new Integer(5));
    }

    public void Sout(String str){
        if(flag){

            System.out.println(str);
        }
    }


    @Override
    public T visitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx) {

        String name = ctx.ID().getText();

        agregar(table);

        if (table.get(name) == null){

            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;

            System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + name + "\" no ha sido declarada", line, col);
            System.exit(-1);

        }else if (ctx.PAR_IZQ()!=null){
            Sout("Por acá Estuve");

//            if(ctx.expr()!=null){
//                String ex = ctx.expr().get(0).getText();
//                int len_comas = ctx.COMA().size();
//                for(int i = 0; i<len_comas; i++){
//                    def a(2,3):
//                        return 2+3
//                }
//            }

        }else{

            return (T) table.get(ctx.ID().getText());
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
        ArrayList<String> arreglo = new ArrayList<>();
        if (ctx.expr() != null){
            String ex = ctx.expr(0).getText();
            arreglo.add(ex);
            int times = ctx.expr().size();
            for (int i = 1; i < times; i++) {
                ex = visit(ctx.expr(i)).toString();
                arreglo.add(ex);
            }
        }
        Sout(arreglo.toString());
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
        if (st.charAt(0) == '['){
            String[] tmp = st.substring(1, st.length()-1).split(",");
            ans = tmp.length;
        }else{
            if (st.charAt(0) != '"'){
                //TODO Poner indices
                System.err.print("Error Semantico No se puede sacar LEN a algo que no es una lista o un string");
                System.exit(-1);
            }else{
                ans = st.substring(1, st.length()-1).length();
            }

        }
        Sout("La longitud de mi polla en km es: " + (ans));
        return (T) ((Integer)ans).toString();
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
            int number = Integer.parseInt((String)visitChildren(ctx));
            number *= -1;
            Sout("Menos: "+((Integer)number).toString());
            return (T)((Integer)number).toString();
        } catch (NumberFormatException nfe){
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
        int a = Integer.parseInt((String)visit(ctx.cexpr_p7()));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p8()));
        Sout("Multiplicacion: " + (a*b));
        return  (T) ((Integer)(a * b)).toString();
    }

    @Override
    public T visitP7_div(ChocopyParser.P7_divContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p7()));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p8()));
        Sout("Division: " + (a/b));
        return  (T) ((Integer)(a / b)).toString();
    }

    @Override
    public T visitP7_mod(ChocopyParser.P7_modContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p7()));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p8()));
        //TODO: Propiedades del módulo
        Sout("Modulo: " + (a%b));
        return  (T) ((Integer)(a % b)).toString();
    }

    @Override
    public T visitP6_suma(ChocopyParser.P6_sumaContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p6()));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p7()));
        Sout("Suma: " + (a+b));
        return  (T) ((Integer)(a + b)).toString();
    }

    @Override
    public T visitP6_resta(ChocopyParser.P6_restaContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p6()));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p7()));
        Sout("Resta: " + (a-b));
        return  (T) ((Integer)(a - b)).toString();
    }

    @Override
    public T visitCexpr_mayor(ChocopyParser.Cexpr_mayorContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p6(1)));
        Sout("Mayor: " + (a>b));
        return  (T) ((Boolean)(a > b)).toString();
    }

    @Override
    public T visitCexpr_menor(ChocopyParser.Cexpr_menorContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p6(1)));
        Sout("Menor: " + (a<b));
        return  (T) ((Boolean)(a < b)).toString();
    }

    @Override
    public T visitCexpr_mayor_igual(ChocopyParser.Cexpr_mayor_igualContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p6(1)));
        Sout("Mayor Igual: " + (a>=b));
        return  (T) ((Boolean)(a >= b)).toString();
    }

    @Override
    public T visitCexpr_menor_igual(ChocopyParser.Cexpr_menor_igualContext ctx) {
        int a = Integer.parseInt((String)visit(ctx.cexpr_p6(0)));
        int b = Integer.parseInt((String)visit(ctx.cexpr_p6(1)));
        Sout("Menor Igual: " + (a<=b));
        return  (T) ((Boolean)(a <= b)).toString();
    }

    @Override
    public T visitCexpr_igual(ChocopyParser.Cexpr_igualContext ctx) {
        String a = (String)visit(ctx.cexpr_p6(0));
        String b = (String)visit(ctx.cexpr_p6(1));
        Sout("Igual: " + (a.equals(b))); //"4"== "2+2" Caso Toteo
        return  (T) ((Boolean)(a.equals(b))).toString();
    }

    @Override
    public T visitCexpr_diferente(ChocopyParser.Cexpr_diferenteContext ctx) {
        String a = (String)visit(ctx.cexpr_p6(0));
        String b = (String)visit(ctx.cexpr_p6(1));
        Sout("Diferente: " + (!a.equals(b)));
        return  (T) ((Boolean)(!a.equals(b))).toString();
    }

    //TODO IS


    @Override
    public T visitExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx) {
        Boolean a = Boolean.parseBoolean((String)visit(ctx.expr_p4()));
        //System.out.println("Booleano: " + !a);
        Sout("Not: " + (!a));
        return  (T) ((Boolean)(!a)).toString();
    }

    @Override
    public T visitP3_and(ChocopyParser.P3_andContext ctx) {
        Boolean a = Boolean.parseBoolean((String)visit(ctx.expr_p3()));
        Boolean b = Boolean.parseBoolean((String)visit(ctx.expr_p4()));
        //System.out.println("Booleano: " + !a);
        Sout("And: " + (a && b));
        return  (T) ((Boolean)(a && b)).toString();
    }

    @Override
    public T visitP2_or(ChocopyParser.P2_orContext ctx) {
        Boolean a = Boolean.parseBoolean((String)visit(ctx.expr_p2()));
        Boolean b = Boolean.parseBoolean((String)visit(ctx.expr_p3()));
        //System.out.println("Booleano: " + !a);
        Sout("Or: " + (a || b));
        return  (T) ((Boolean)(a || b)).toString();
    }

    @Override
    public T visitExpr_if(ChocopyParser.Expr_ifContext ctx) {
        Boolean a = Boolean.parseBoolean((String)visit(ctx.expr_p2()));
        Boolean b = Boolean.parseBoolean((String)visit(ctx.expr(0)));
        Boolean c = Boolean.parseBoolean((String)visit(ctx.expr(1)));
        //System.out.println("Booleano: " + !a);
        Sout("if: " + (b));
        if(b){
            return (T) a.toString();
        }else {
            return (T) c.toString();
        }
    }

    @Override
    public T visitStmtprint(ChocopyParser.StmtprintContext ctx) {
        String ex = ctx.expr().getText();
        if (ex.charAt(0) == '"'){
            System.out.println("Esto es una sentencia Print: " + ex.substring(1, ex.length()-1));
        }else{
            System.out.println("Esto es una sentencia Print: " + ex);
        }

        return super.visitStmtprint(ctx);
    }




}
