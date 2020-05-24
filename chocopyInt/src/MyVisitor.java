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
        System.out.println("Soy un literal " + ctx.literal().getText());
        String aux = ctx.literal().getText();
        return (T) aux;
        //OJO
        //return super.visitCexpr_p10_literal(ctx);
    }

    @Override
    public T visitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx) {
        System.out.println("soy self");
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

    @Override
    public T visitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx) {
        System.out.println("soyp8menos");
        try {
//            Integer number = Integer.parseInt(ctx.cexpr_p8().getText());
            int number = (int)Double.parseDouble((String)visitChildren(ctx));
            number *= -1;
            System.out.println(number);
            return (T)((Double)(double)number).toString();
        } catch (NumberFormatException nfe){
            return super.visitCexpr_p8_menos(ctx);
        }
    }

    @Override
    public T visitCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx) {
        System.out.println("soyp8p9");
        return visitChildren(ctx);
    }

}
