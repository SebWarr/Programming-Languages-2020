// Generated from /home/minorin/Documents/Programming-Languages-2020/chocopyInt/grammar/Chocopy.g4 by ANTLR 4.8
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
	 * Visit a parse tree produced by the {@code class_body_pass}
	 * labeled alternative in {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body_pass(ChocopyParser.Class_body_passContext ctx);
	/**
	 * Visit a parse tree produced by the {@code class_body_var_func}
	 * labeled alternative in {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body_var_func(ChocopyParser.Class_body_var_funcContext ctx);
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
	 * Visit a parse tree produced by the {@code typed_var_id}
	 * labeled alternative in {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyped_var_id(ChocopyParser.Typed_var_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typed_var_self}
	 * labeled alternative in {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyped_var_self(ChocopyParser.Typed_var_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code type_id}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_id(ChocopyParser.Type_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code type_string}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_string(ChocopyParser.Type_stringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code type_cor_izq}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_cor_izq(ChocopyParser.Type_cor_izqContext ctx);
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
	 * Visit a parse tree produced by the {@code stmt_simple_stmt}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_simple_stmt(ChocopyParser.Stmt_simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmt_if}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_if(ChocopyParser.Stmt_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmt_while}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_while(ChocopyParser.Stmt_whileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmt_for}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_for(ChocopyParser.Stmt_forContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple_stmt_pass}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt_pass(ChocopyParser.Simple_stmt_passContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple_stmt_expr}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt_expr(ChocopyParser.Simple_stmt_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple_stmt_return}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt_return(ChocopyParser.Simple_stmt_returnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple_stmt_asig}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt_asig(ChocopyParser.Simple_stmt_asigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple_stmt_print}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt_print(ChocopyParser.Simple_stmt_printContext ctx);
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
	 * Visit a parse tree produced by the {@code expr_aux_if}
	 * labeled alternative in {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_aux_if(ChocopyParser.Expr_aux_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_aux_eps(ChocopyParser.Expr_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p2(ChocopyParser.Expr_p2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_p2_aux_or}
	 * labeled alternative in {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p2_aux_or(ChocopyParser.Expr_p2_aux_orContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_p2_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p2_aux_eps(ChocopyParser.Expr_p2_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#expr_p3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p3(ChocopyParser.Expr_p3Context ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_p3_aux_and}
	 * labeled alternative in {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p3_aux_and(ChocopyParser.Expr_p3_aux_andContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_p3_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p3_aux_eps(ChocopyParser.Expr_p3_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_p4_not}
	 * labeled alternative in {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_p4_cexpr}
	 * labeled alternative in {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_p4_cexpr(ChocopyParser.Expr_p4_cexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr(ChocopyParser.CexprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_aux_bin(ChocopyParser.Cexpr_aux_binContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_aux_eps(ChocopyParser.Cexpr_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_igual(ChocopyParser.Bin_op_log_igualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_diferente}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_diferente(ChocopyParser.Bin_op_log_diferenteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_mayor}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_mayor(ChocopyParser.Bin_op_log_mayorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_menor}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_menor(ChocopyParser.Bin_op_log_menorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_mayor_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_mayor_igual(ChocopyParser.Bin_op_log_mayor_igualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_menor_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_menor_igual(ChocopyParser.Bin_op_log_menor_igualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_log_is}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_log_is(ChocopyParser.Bin_op_log_isContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p6(ChocopyParser.Cexpr_p6Context ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p6_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p6_aux_bin(ChocopyParser.Cexpr_p6_aux_binContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p6_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p6_aux_eps(ChocopyParser.Cexpr_p6_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_p6_suma}
	 * labeled alternative in {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p6_suma(ChocopyParser.Bin_op_p6_sumaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_p6_menos}
	 * labeled alternative in {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p6_menos(ChocopyParser.Bin_op_p6_menosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p7(ChocopyParser.Cexpr_p7Context ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p7_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p7_aux_bin(ChocopyParser.Cexpr_p7_aux_binContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p7_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p7_aux_eps(ChocopyParser.Cexpr_p7_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_p7_mult}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p7_mult(ChocopyParser.Bin_op_p7_multContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_p7_div}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p7_div(ChocopyParser.Bin_op_p7_divContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bin_op_p7_mod}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_op_p7_mod(ChocopyParser.Bin_op_p7_modContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p8_menos}
	 * labeled alternative in {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p8_cexpr_p9}
	 * labeled alternative in {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx);
	/**
	 * Visit a parse tree produced by {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p9(ChocopyParser.Cexpr_p9Context ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p9_aux_punto}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p9_aux_punto(ChocopyParser.Cexpr_p9_aux_puntoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p9_aux_cor_izq}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p9_aux_cor_izq(ChocopyParser.Cexpr_p9_aux_cor_izqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p9_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p9_aux_eps(ChocopyParser.Cexpr_p9_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_no_req_cor_expr}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_no_req_cor_expr(ChocopyParser.Expr_list_no_req_cor_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_no_req_cor_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_no_req_cor_eps(ChocopyParser.Expr_list_no_req_cor_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_0_more_cor_coma}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_0_more_cor_coma(ChocopyParser.Expr_list_0_more_cor_comaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_0_more_cor_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_0_more_cor_eps(ChocopyParser.Expr_list_0_more_cor_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_id}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_literal}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_literal(ChocopyParser.Cexpr_p10_literalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_cor}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_cor(ChocopyParser.Cexpr_p10_corContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_par}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_par(ChocopyParser.Cexpr_p10_parContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_len}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_len(ChocopyParser.Cexpr_p10_lenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_self}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_aux_par}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_aux_par(ChocopyParser.Cexpr_p10_aux_parContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cexpr_p10_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_p10_aux_eps(ChocopyParser.Cexpr_p10_aux_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_no_req_expr}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_no_req_expr(ChocopyParser.Expr_list_no_req_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_no_req_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_no_req_eps(ChocopyParser.Expr_list_no_req_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_0_more_coma}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_0_more_coma(ChocopyParser.Expr_list_0_more_comaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_list_0_more_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_0_more_eps(ChocopyParser.Expr_list_0_more_epsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code member_expr_cexpr}
	 * labeled alternative in {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expr_cexpr(ChocopyParser.Member_expr_cexprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code member_expr_self}
	 * labeled alternative in {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expr_self(ChocopyParser.Member_expr_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code index_expr_cexpr}
	 * labeled alternative in {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_expr_cexpr(ChocopyParser.Index_expr_cexprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code index_expr_self}
	 * labeled alternative in {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_expr_self(ChocopyParser.Index_expr_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code target_id}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget_id(ChocopyParser.Target_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code target_self}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget_self(ChocopyParser.Target_selfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code target_member}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget_member(ChocopyParser.Target_memberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code target_index_expr}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget_index_expr(ChocopyParser.Target_index_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code n_asig}
	 * labeled alternative in {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN_asig(ChocopyParser.N_asigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code n_igual}
	 * labeled alternative in {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN_igual(ChocopyParser.N_igualContext ctx);
}