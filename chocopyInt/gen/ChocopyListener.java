// Generated from /home/minorin/Documents/Programming-Languages-2020/chocopyInt/grammar/Chocopy.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ChocopyParser}.
 */
public interface ChocopyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ChocopyParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ChocopyParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#class_def}.
	 * @param ctx the parse tree
	 */
	void enterClass_def(ChocopyParser.Class_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#class_def}.
	 * @param ctx the parse tree
	 */
	void exitClass_def(ChocopyParser.Class_defContext ctx);
	/**
	 * Enter a parse tree produced by the {@code class_body_pass}
	 * labeled alternative in {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body_pass(ChocopyParser.Class_body_passContext ctx);
	/**
	 * Exit a parse tree produced by the {@code class_body_pass}
	 * labeled alternative in {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body_pass(ChocopyParser.Class_body_passContext ctx);
	/**
	 * Enter a parse tree produced by the {@code class_body_var_func}
	 * labeled alternative in {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body_var_func(ChocopyParser.Class_body_var_funcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code class_body_var_func}
	 * labeled alternative in {@link ChocopyParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body_var_func(ChocopyParser.Class_body_var_funcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#func_def}.
	 * @param ctx the parse tree
	 */
	void enterFunc_def(ChocopyParser.Func_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#func_def}.
	 * @param ctx the parse tree
	 */
	void exitFunc_def(ChocopyParser.Func_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(ChocopyParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(ChocopyParser.Func_bodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typed_var_id}
	 * labeled alternative in {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 */
	void enterTyped_var_id(ChocopyParser.Typed_var_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typed_var_id}
	 * labeled alternative in {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 */
	void exitTyped_var_id(ChocopyParser.Typed_var_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typed_var_self}
	 * labeled alternative in {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 */
	void enterTyped_var_self(ChocopyParser.Typed_var_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typed_var_self}
	 * labeled alternative in {@link ChocopyParser#typed_var}.
	 * @param ctx the parse tree
	 */
	void exitTyped_var_self(ChocopyParser.Typed_var_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_id}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_id(ChocopyParser.Type_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_id}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_id(ChocopyParser.Type_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_string}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_string(ChocopyParser.Type_stringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_string}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_string(ChocopyParser.Type_stringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_cor_izq}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_cor_izq(ChocopyParser.Type_cor_izqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_cor_izq}
	 * labeled alternative in {@link ChocopyParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_cor_izq(ChocopyParser.Type_cor_izqContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#global_decl}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_decl(ChocopyParser.Global_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#global_decl}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_decl(ChocopyParser.Global_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#nonlocal_decl}.
	 * @param ctx the parse tree
	 */
	void enterNonlocal_decl(ChocopyParser.Nonlocal_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#nonlocal_decl}.
	 * @param ctx the parse tree
	 */
	void exitNonlocal_decl(ChocopyParser.Nonlocal_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#var_def}.
	 * @param ctx the parse tree
	 */
	void enterVar_def(ChocopyParser.Var_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#var_def}.
	 * @param ctx the parse tree
	 */
	void exitVar_def(ChocopyParser.Var_defContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmt_simple_stmt}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt_simple_stmt(ChocopyParser.Stmt_simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmt_simple_stmt}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt_simple_stmt(ChocopyParser.Stmt_simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmt_if}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt_if(ChocopyParser.Stmt_ifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmt_if}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt_if(ChocopyParser.Stmt_ifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmt_while}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt_while(ChocopyParser.Stmt_whileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmt_while}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt_while(ChocopyParser.Stmt_whileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmt_for}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt_for(ChocopyParser.Stmt_forContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmt_for}
	 * labeled alternative in {@link ChocopyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt_for(ChocopyParser.Stmt_forContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple_stmt_pass}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt_pass(ChocopyParser.Simple_stmt_passContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple_stmt_pass}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt_pass(ChocopyParser.Simple_stmt_passContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple_stmt_expr}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt_expr(ChocopyParser.Simple_stmt_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple_stmt_expr}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt_expr(ChocopyParser.Simple_stmt_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple_stmt_return}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt_return(ChocopyParser.Simple_stmt_returnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple_stmt_return}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt_return(ChocopyParser.Simple_stmt_returnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple_stmt_asig}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt_asig(ChocopyParser.Simple_stmt_asigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple_stmt_asig}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt_asig(ChocopyParser.Simple_stmt_asigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple_stmt_print}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt_print(ChocopyParser.Simple_stmt_printContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple_stmt_print}
	 * labeled alternative in {@link ChocopyParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt_print(ChocopyParser.Simple_stmt_printContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#stmtprint}.
	 * @param ctx the parse tree
	 */
	void enterStmtprint(ChocopyParser.StmtprintContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#stmtprint}.
	 * @param ctx the parse tree
	 */
	void exitStmtprint(ChocopyParser.StmtprintContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ChocopyParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ChocopyParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ChocopyParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ChocopyParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ChocopyParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ChocopyParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_aux_if}
	 * labeled alternative in {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpr_aux_if(ChocopyParser.Expr_aux_ifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_aux_if}
	 * labeled alternative in {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpr_aux_if(ChocopyParser.Expr_aux_ifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpr_aux_eps(ChocopyParser.Expr_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpr_aux_eps(ChocopyParser.Expr_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#expr_p2}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p2(ChocopyParser.Expr_p2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#expr_p2}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p2(ChocopyParser.Expr_p2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_p2_aux_or}
	 * labeled alternative in {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p2_aux_or(ChocopyParser.Expr_p2_aux_orContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_p2_aux_or}
	 * labeled alternative in {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p2_aux_or(ChocopyParser.Expr_p2_aux_orContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_p2_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p2_aux_eps(ChocopyParser.Expr_p2_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_p2_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_p2_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p2_aux_eps(ChocopyParser.Expr_p2_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#expr_p3}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p3(ChocopyParser.Expr_p3Context ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#expr_p3}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p3(ChocopyParser.Expr_p3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_p3_aux_and}
	 * labeled alternative in {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p3_aux_and(ChocopyParser.Expr_p3_aux_andContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_p3_aux_and}
	 * labeled alternative in {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p3_aux_and(ChocopyParser.Expr_p3_aux_andContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_p3_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p3_aux_eps(ChocopyParser.Expr_p3_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_p3_aux_eps}
	 * labeled alternative in {@link ChocopyParser#expr_p3_aux}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p3_aux_eps(ChocopyParser.Expr_p3_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_p4_not}
	 * labeled alternative in {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_p4_not}
	 * labeled alternative in {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p4_not(ChocopyParser.Expr_p4_notContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_p4_cexpr}
	 * labeled alternative in {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 */
	void enterExpr_p4_cexpr(ChocopyParser.Expr_p4_cexprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_p4_cexpr}
	 * labeled alternative in {@link ChocopyParser#expr_p4}.
	 * @param ctx the parse tree
	 */
	void exitExpr_p4_cexpr(ChocopyParser.Expr_p4_cexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#cexpr}.
	 * @param ctx the parse tree
	 */
	void enterCexpr(ChocopyParser.CexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#cexpr}.
	 * @param ctx the parse tree
	 */
	void exitCexpr(ChocopyParser.CexprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_aux_bin(ChocopyParser.Cexpr_aux_binContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_aux_bin(ChocopyParser.Cexpr_aux_binContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_aux_eps(ChocopyParser.Cexpr_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_aux_eps(ChocopyParser.Cexpr_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_igual(ChocopyParser.Bin_op_log_igualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_igual(ChocopyParser.Bin_op_log_igualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_diferente}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_diferente(ChocopyParser.Bin_op_log_diferenteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_diferente}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_diferente(ChocopyParser.Bin_op_log_diferenteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_mayor}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_mayor(ChocopyParser.Bin_op_log_mayorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_mayor}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_mayor(ChocopyParser.Bin_op_log_mayorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_menor}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_menor(ChocopyParser.Bin_op_log_menorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_menor}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_menor(ChocopyParser.Bin_op_log_menorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_mayor_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_mayor_igual(ChocopyParser.Bin_op_log_mayor_igualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_mayor_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_mayor_igual(ChocopyParser.Bin_op_log_mayor_igualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_menor_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_menor_igual(ChocopyParser.Bin_op_log_menor_igualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_menor_igual}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_menor_igual(ChocopyParser.Bin_op_log_menor_igualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_log_is}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_log_is(ChocopyParser.Bin_op_log_isContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_log_is}
	 * labeled alternative in {@link ChocopyParser#bin_op_log}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_log_is(ChocopyParser.Bin_op_log_isContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#cexpr_p6}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p6(ChocopyParser.Cexpr_p6Context ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#cexpr_p6}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p6(ChocopyParser.Cexpr_p6Context ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p6_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p6_aux_bin(ChocopyParser.Cexpr_p6_aux_binContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p6_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p6_aux_bin(ChocopyParser.Cexpr_p6_aux_binContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p6_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p6_aux_eps(ChocopyParser.Cexpr_p6_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p6_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p6_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p6_aux_eps(ChocopyParser.Cexpr_p6_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_p6_suma}
	 * labeled alternative in {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_p6_suma(ChocopyParser.Bin_op_p6_sumaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_p6_suma}
	 * labeled alternative in {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_p6_suma(ChocopyParser.Bin_op_p6_sumaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_p6_menos}
	 * labeled alternative in {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_p6_menos(ChocopyParser.Bin_op_p6_menosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_p6_menos}
	 * labeled alternative in {@link ChocopyParser#bin_op_p6}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_p6_menos(ChocopyParser.Bin_op_p6_menosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#cexpr_p7}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p7(ChocopyParser.Cexpr_p7Context ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#cexpr_p7}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p7(ChocopyParser.Cexpr_p7Context ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p7_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p7_aux_bin(ChocopyParser.Cexpr_p7_aux_binContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p7_aux_bin}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p7_aux_bin(ChocopyParser.Cexpr_p7_aux_binContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p7_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p7_aux_eps(ChocopyParser.Cexpr_p7_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p7_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p7_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p7_aux_eps(ChocopyParser.Cexpr_p7_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_p7_mult}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_p7_mult(ChocopyParser.Bin_op_p7_multContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_p7_mult}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_p7_mult(ChocopyParser.Bin_op_p7_multContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_p7_div}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_p7_div(ChocopyParser.Bin_op_p7_divContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_p7_div}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_p7_div(ChocopyParser.Bin_op_p7_divContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bin_op_p7_mod}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 */
	void enterBin_op_p7_mod(ChocopyParser.Bin_op_p7_modContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bin_op_p7_mod}
	 * labeled alternative in {@link ChocopyParser#bin_op_p7}.
	 * @param ctx the parse tree
	 */
	void exitBin_op_p7_mod(ChocopyParser.Bin_op_p7_modContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p8_menos}
	 * labeled alternative in {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p8_menos}
	 * labeled alternative in {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p8_menos(ChocopyParser.Cexpr_p8_menosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p8_cexpr_p9}
	 * labeled alternative in {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p8_cexpr_p9}
	 * labeled alternative in {@link ChocopyParser#cexpr_p8}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p8_cexpr_p9(ChocopyParser.Cexpr_p8_cexpr_p9Context ctx);
	/**
	 * Enter a parse tree produced by {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p9(ChocopyParser.Cexpr_p9Context ctx);
	/**
	 * Exit a parse tree produced by {@link ChocopyParser#cexpr_p9}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p9(ChocopyParser.Cexpr_p9Context ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p9_aux_punto}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p9_aux_punto(ChocopyParser.Cexpr_p9_aux_puntoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p9_aux_punto}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p9_aux_punto(ChocopyParser.Cexpr_p9_aux_puntoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p9_aux_cor_izq}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p9_aux_cor_izq(ChocopyParser.Cexpr_p9_aux_cor_izqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p9_aux_cor_izq}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p9_aux_cor_izq(ChocopyParser.Cexpr_p9_aux_cor_izqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p9_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p9_aux_eps(ChocopyParser.Cexpr_p9_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p9_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p9_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p9_aux_eps(ChocopyParser.Cexpr_p9_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_no_req_cor_expr}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_no_req_cor_expr(ChocopyParser.Expr_list_no_req_cor_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_no_req_cor_expr}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_no_req_cor_expr(ChocopyParser.Expr_list_no_req_cor_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_no_req_cor_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_no_req_cor_eps(ChocopyParser.Expr_list_no_req_cor_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_no_req_cor_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req_cor}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_no_req_cor_eps(ChocopyParser.Expr_list_no_req_cor_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_0_more_cor_coma}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_0_more_cor_coma(ChocopyParser.Expr_list_0_more_cor_comaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_0_more_cor_coma}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_0_more_cor_coma(ChocopyParser.Expr_list_0_more_cor_comaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_0_more_cor_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_0_more_cor_eps(ChocopyParser.Expr_list_0_more_cor_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_0_more_cor_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more_cor}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_0_more_cor_eps(ChocopyParser.Expr_list_0_more_cor_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_id}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_id}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_id(ChocopyParser.Cexpr_p10_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_literal}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_literal(ChocopyParser.Cexpr_p10_literalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_literal}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_literal(ChocopyParser.Cexpr_p10_literalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_cor}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_cor(ChocopyParser.Cexpr_p10_corContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_cor}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_cor(ChocopyParser.Cexpr_p10_corContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_par}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_par(ChocopyParser.Cexpr_p10_parContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_par}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_par(ChocopyParser.Cexpr_p10_parContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_len}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_len(ChocopyParser.Cexpr_p10_lenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_len}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_len(ChocopyParser.Cexpr_p10_lenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_self}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_self}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_self(ChocopyParser.Cexpr_p10_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_aux_par}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_aux_par(ChocopyParser.Cexpr_p10_aux_parContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_aux_par}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_aux_par(ChocopyParser.Cexpr_p10_aux_parContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cexpr_p10_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 */
	void enterCexpr_p10_aux_eps(ChocopyParser.Cexpr_p10_aux_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cexpr_p10_aux_eps}
	 * labeled alternative in {@link ChocopyParser#cexpr_p10_aux}.
	 * @param ctx the parse tree
	 */
	void exitCexpr_p10_aux_eps(ChocopyParser.Cexpr_p10_aux_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_no_req_expr}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_no_req_expr(ChocopyParser.Expr_list_no_req_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_no_req_expr}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_no_req_expr(ChocopyParser.Expr_list_no_req_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_no_req_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_no_req_eps(ChocopyParser.Expr_list_no_req_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_no_req_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_no_req}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_no_req_eps(ChocopyParser.Expr_list_no_req_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_0_more_coma}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_0_more_coma(ChocopyParser.Expr_list_0_more_comaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_0_more_coma}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_0_more_coma(ChocopyParser.Expr_list_0_more_comaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_list_0_more_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_0_more_eps(ChocopyParser.Expr_list_0_more_epsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_list_0_more_eps}
	 * labeled alternative in {@link ChocopyParser#expr_list_0_more}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_0_more_eps(ChocopyParser.Expr_list_0_more_epsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code member_expr_cexpr}
	 * labeled alternative in {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 */
	void enterMember_expr_cexpr(ChocopyParser.Member_expr_cexprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code member_expr_cexpr}
	 * labeled alternative in {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 */
	void exitMember_expr_cexpr(ChocopyParser.Member_expr_cexprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code member_expr_self}
	 * labeled alternative in {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 */
	void enterMember_expr_self(ChocopyParser.Member_expr_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code member_expr_self}
	 * labeled alternative in {@link ChocopyParser#member_expr}.
	 * @param ctx the parse tree
	 */
	void exitMember_expr_self(ChocopyParser.Member_expr_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code index_expr_cexpr}
	 * labeled alternative in {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 */
	void enterIndex_expr_cexpr(ChocopyParser.Index_expr_cexprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code index_expr_cexpr}
	 * labeled alternative in {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 */
	void exitIndex_expr_cexpr(ChocopyParser.Index_expr_cexprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code index_expr_self}
	 * labeled alternative in {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 */
	void enterIndex_expr_self(ChocopyParser.Index_expr_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code index_expr_self}
	 * labeled alternative in {@link ChocopyParser#index_expr}.
	 * @param ctx the parse tree
	 */
	void exitIndex_expr_self(ChocopyParser.Index_expr_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code target_id}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget_id(ChocopyParser.Target_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code target_id}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget_id(ChocopyParser.Target_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code target_self}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget_self(ChocopyParser.Target_selfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code target_self}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget_self(ChocopyParser.Target_selfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code target_member}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget_member(ChocopyParser.Target_memberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code target_member}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget_member(ChocopyParser.Target_memberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code target_index_expr}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget_index_expr(ChocopyParser.Target_index_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code target_index_expr}
	 * labeled alternative in {@link ChocopyParser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget_index_expr(ChocopyParser.Target_index_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code n_asig}
	 * labeled alternative in {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 */
	void enterN_asig(ChocopyParser.N_asigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code n_asig}
	 * labeled alternative in {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 */
	void exitN_asig(ChocopyParser.N_asigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code n_igual}
	 * labeled alternative in {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 */
	void enterN_igual(ChocopyParser.N_igualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code n_igual}
	 * labeled alternative in {@link ChocopyParser#n}.
	 * @param ctx the parse tree
	 */
	void exitN_igual(ChocopyParser.N_igualContext ctx);
}