import java.util.ArrayList;
import java.util.HashMap;

public class MyVisitor<T> extends ChocopyBaseVisitor<T> {

    HashMap<String, Object> table = new HashMap<>();

    @Override
    public T visitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx) {
        String name = ctx.ID().getText();
        if (table.get(name) == null){
            int line = ctx.ID().getSymbol().getLine();
            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;

            System.err.printf("<%d, %d> Error Semantico, la variable con nombre: \"" + name + "\" no ha sido declarada", line, col);
            System.exit(-1);
        }else{
            return (T) visitChildren(ctx);
        }
        return super.visitCexpr_p10_id(ctx);
    }

    @Override
    public T visitCexpr_p10_literal(ChocopyParser.Cexpr_p10_literalContext ctx) {
        System.out.println("Soy un literal" + ctx.literal().getText());
        Integer aux = null;
        aux = Integer.parseInt(ctx.literal().getText());
        return (T) aux;
        //OJO
        //return super.visitCexpr_p10_literal(ctx);
    }

//    @Override
//    public Object visitCexpr_p10_cor(ChocopyParser.Cexpr_p10_corContext ctx) {
//        ArrayList<Integer> lista = new ArrayList<>();
//        System.out.println("Soy una Lista");
//        System.out.println(ctx.expr_list_no_req_cor());
//        return super.visitCexpr_p10_cor(ctx);
//    }

    @Override
    public T visitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx) {
        return (T) ctx.SELF();
    }

//    @Override
//    public Object visitGlobal_decl(ChocopyParser.Global_declContext ctx) {
//        String name = ctx.ID().getText();
//        if (table.get(name) == null) {
//            int line = ctx.ID().getSymbol().getLine();
//            int col = ctx.ID().getSymbol().getCharPositionInLine() + 1;
//
//            System.err.printf("<%d, %d> Error Semantico, la variable global con nombre: \"" + name + "\" no ha sido declarada", line, col);
//            System.exit(-1);
//        }
//        return super.visitGlobal_decl(ctx);
//    }


//    @Override
//    public T visitCexpr_p9_aux_eps(ChocopyParser.Cexpr_p9_aux_epsContext ctx) {
//        System.out.println("Soy un Epsilom");
//       // System.out.println(super.visitCexpr_p9_aux_eps(ctx));
//        System.out.println(ctx);
//        return (T) ctx.getText() ;
//    }

    @Override
    public T visitCexpr_p9(ChocopyParser.Cexpr_p9Context ctx) {
        //System.out.println(ctx.start);
        //System.out.println(ctx.cexpr_p10());
       // System.out.println(ctx.getText());
        //Object prueba = visitChildren(ctx.cexpr_p10());
        //prueba = ctx.children.toString()
        System.out.println(ctx.cexpr_p10().getText());
        System.out.println("Buenas");
        System.out.println(visitChildren(ctx));
        Integer prueba1 =  Integer.parseInt((String)visitChildren(ctx));
        System.out.println("Debería llegar" + prueba1);
        return (T) prueba1;
    }

    @Override
    public T visitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx) {
        Object resExpr = visitChildren(ctx);
        System.out.println("sdfbvgsdfb" + resExpr);
        int expr = Integer.parseInt((String) resExpr);
        System.out.println(expr);
        expr *= -1;
        return (T) (Object) expr;
    }
}
