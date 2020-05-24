// Generated from /home/brayan/Documentos/UNAL/Semestre 9/Lenguajes/compilador/Programming-Languages-2020/chocopyInt/grammar/Chocopy.g4 by ANTLR 4.8
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
	 * Visit a parse tree produced by the {@code expr_if}
	 * labeled alternative in {@link ChocopyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_if(ChocopyParser.Expr_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code p2}
	 * labeled alternative in {@link ChocopyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP2(ChocopyParser.P2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code p2_or}
	 * labeled alternative in {@link ChocopyParser#expr_p2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP2_or(ChocopyParser.P2_orContext ctx);
	/**
	 * Visit a parse tree produced by the {@code p3}
	 * labeled alternative in {@link ChocopyParser#expr_p2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP3(ChocopyParser.P3Context ctx);
	/**
	 * Visit a parse tree produced by the {@code p3_and}
	 * labeled alternative in {@link ChocopyParser#expr_p3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP3_and(ChocopyParser.P3_andContext ctx);
	/**
	 * Visit a parse tree produced by the {@code p4}
	 * labeled alternative in {@link ChocopyParser#expr_p3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP4(ChocopyParser.P4Context ctx);
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
	 * Visit a parse tree produced by the {@code cexpr_op_log}
	 * labeled alternative in {@link ChocopyParser#cexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCexpr_op_log(ChocopyParser.Cexpr_op_logContext ctx);
	/**
	 * Visit a parse tree produced by the {@code p6}
	 * labeled alternative in {@link ChocopyParser#cexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP6(ChocopyParser.P6Context ctx);
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
	 * Visit a parse tree produced by the {@code p7}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP7(ChocopyParser.P7Context ctx);
	/**
	 * Visit a parse tree produced by the {@code p6_op_p6}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP6_op_p6(ChocopyParser.P6_op_p6Context ctx);
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
	 * Visit a parse tree produced by the {@code p8}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP8(ChocopyParser.P8Context ctx);
	/**
	 * Visit a parse tree produced by the {@code p7_op_p8}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP7_op_p8(ChocopyParser.P7_op_p8Context ctx);
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
	 * Visit a parse tree produced by the {@code p10_punto}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP10_punto(ChocopyParser.P10_puntoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code p10_cor}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP10_cor(ChocopyParser.P10_corContext ctx);
	/**
	 * Visit a parse tree produced by the {@code p10}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitP10(ChocopyParser.P10Context ctx);
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
}