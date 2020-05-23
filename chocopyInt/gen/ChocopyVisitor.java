// Generated from /home/brayan/Documentos/UNAL/Semestre 9/Lenguajes/chocopyInt/grammar/Chocopy.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ChocopyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ChocopyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ChocopyParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#class_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_def(ChocopyParser.Class_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body(ChocopyParser.Class_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#func_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_def(ChocopyParser.Func_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#func_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_body(ChocopyParser.Func_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyped_var(ChocopyParser.Typed_varContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ChocopyParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#global_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_decl(ChocopyParser.Global_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#nonlocal_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonlocal_decl(ChocopyParser.Nonlocal_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_def(ChocopyParser.Var_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(ChocopyParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(ChocopyParser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#stmtprint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtprint(ChocopyParser.StmtprintContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ChocopyParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ChocopyParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ChocopyParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_aux(ChocopyParser.Expr_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p2(ChocopyParser.Expr_p2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p2_aux(ChocopyParser.Expr_p2_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p3(ChocopyParser.Expr_p3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p3_aux(ChocopyParser.Expr_p3_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p4(ChocopyParser.Expr_p4Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr(ChocopyParser.CexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_aux(ChocopyParser.Cexpr_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log(ChocopyParser.Bin_op_logContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p6(ChocopyParser.Cexpr_p6Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p6_aux(ChocopyParser.Cexpr_p6_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p6(ChocopyParser.Bin_op_p6Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p7(ChocopyParser.Cexpr_p7Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p7_aux(ChocopyParser.Cexpr_p7_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p7(ChocopyParser.Bin_op_p7Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p8(ChocopyParser.Cexpr_p8Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p9(ChocopyParser.Cexpr_p9Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p9_aux(ChocopyParser.Cexpr_p9_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_no_req_cor(ChocopyParser.Expr_list_no_req_corContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_0_more_cor(ChocopyParser.Expr_list_0_more_corContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10(ChocopyParser.Cexpr_p10Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_aux(ChocopyParser.Cexpr_p10_auxContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_no_req(ChocopyParser.Expr_list_no_reqContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_0_more(ChocopyParser.Expr_list_0_moreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expr(ChocopyParser.Member_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_expr(ChocopyParser.Index_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget(ChocopyParser.TargetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN(ChocopyParser.NContext ctx);
}