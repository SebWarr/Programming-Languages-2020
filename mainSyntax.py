from mainLexer import Lexer

flagSintaxis = False

token = ""
i = 0
j = 0

recursive_calls = []

initial_symbol_grammar = "program"
not_terminals = ["program", "tipo", "declaracion",
                 "literal", "expr", "expr_aux", "expr_p2", "expr_p2_aux", "expr_p3", "expr_p3_aux", "expr_p4",
                 "cexpr", "cexpr_aux", "bin_op_log", "cexpr_p6", "cexpr_p6_aux", "bin_op_p6",
                 "cexpr_p7", "cexpr_p7_aux", "bin_op_p7", "cexpr_p8", "cexpr_p9", "cexpr_p9_aux", "cexpr_p10",
                 "cexpr_p10_aux", "expr_list_no_req", "expr_list_0_more", 'asignacion', 'asignacion_fc_nta',
                 'salida_aux', 'id_fc_aux', 'no_terminal_aux', 'operators', 'tk_corch_izq_fc',
                 'global_declaracion', 'nonlocal_declaracion', 'declaracion_asignacion_fc', 'print_statement',
                 'conditional_statement_1', 'conditional_statement_2', 'conditional_statement_3',
                 'statement', 'conditional_block', 'newline1_fc', 'newline2_fc', 'newline2star_fc',
                 'loop_block', 'block_sin_epsilon', 'block_con_epsilon', 'identaciones_bse',
                 'function_block', 'coma_fc', 'block_0o1', 'argumentos_star', 'argumentos_0o1',
                 'return_aux',"function_fc","return_0o1","ejecucion","return_statement","fc_return","class_block",
                 "id_object","dedent_0o1"
                 ]

grammar = {
    "tipo": [["int"], ["str"], ["bool"], ["object"], ["tk_corch_izq", "tipo", "tk_corch_der"],["tk_cadena"]],
    "declaracion": [['tk_dos_puntos', 'tipo', 'tk_asig', 'expr']],
    'asignacion': [
        ['asignacion_fc_nta']
    ],
    'asignacion_fc_nta': [
        ['tk_asig', 'salida_aux'],
        ['tk_corch_izq', 'expr', 'tk_corch_der', 'tk_corch_izq_fc'],
        ['tk_punto', 'id', 'tk_asig', 'salida_aux'],
        ['']
    ],
    'tk_corch_izq_fc': [['tk_asig', 'salida_aux'],
                        ['']
                        ],
    'salida_aux': [
        ['id', 'id_fc_aux'],
        ['expr']
    ],
    'id_fc_aux': [
        ['asignacion'],
        ['operators', 'expr'],
    ],
    'no_terminal_aux': [
        ['tk_asig'],
        ['tk_corch_izq'],
        ['tk_punto'],
        ['']
    ],
    'operators': [
        ['tk_suma'],
        ['tk_menos'],
        ['tk_multiplicacion'],
        ['tk_division'],
        ['tk_modulo'],
        ['tk_igual'],
        ['tk_diferente'],
        ['tk_menor_igual'],
        ['tk_mayor_igual'],
        ['tk_menor'],
        ['tk_mayor'],
        ['is'],
        ['or'],
        ['and'],
        ['if', 'expr', 'else', 'expr']
    ],
    'global_declaracion': [
        ['global', 'id']
    ],
    'nonlocal_declaracion': [
        ['nonlocal', 'id']
    ],
    'declaracion_asignacion_fc': [
        ['declaracion'],
        ['asignacion'],
        ["ejecucion"]
    ],
    "ejecucion":[
        ["tk_par_izq","expr_list_no_req","tk_par_der"]
    ],
    'print_statement': [
        ['print', 'tk_par_izq', 'expr', 'tk_par_der']
    ],
    'return_statement': [
        ["return","fc_return"]
    ],
    "fc_return":[
        ["expr"],
        [""]
    ],
    'conditional_statement_1': [
        ['if', 'expr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon', 'newline1_fc']
    ],
    'newline1_fc': [
        ['DEDENT'],
        ['']
    ],
    'conditional_statement_2': [
        ['elif', 'expr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon', 'newline2_fc'],
        ["conditional_statement_3"],
        ["loop_block"]
    ],
    'newline2_fc': [
        ['NEWLINE', 'DEDENT', 'newline2star_fc'],
        ['DEDENT']
    ],
    'newline2star_fc': [
       # ['conditional_statement_2'],
        ['']
    ],
    'conditional_statement_3': [
        ['else', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon', "newline1_fc"],
        ['']
    ],
    'statement': [
        ['print_statement'],
        ["return_statement"],
        ['id', 'declaracion_asignacion_fc'],
        ["self", "tk_punto", "id",'declaracion_asignacion_fc'],
        ['global_declaracion'],
        ['nonlocal_declaracion'],
        ['pass']
    ],
    'conditional_block': [
        ['conditional_statement_1', 'conditional_statement_2']
    ],
    'loop_block': [
        ['while', 'expr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon'],
        ['for', 'id', 'in', 'cexpr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon']
    ],
    'function_block': [
        ["def", "id", "tk_par_izq", "argumentos_0o1", "tk_par_der", "tk_ejecuta", "tipo", "tk_dos_puntos",
         "NEWLINE", "INDENT", "function_fc"],
    ],
    "function_fc":[
        ["return", "return_aux", "newline1_fc"],
        ["block_sin_epsilon","return_0o1"]
    ],
    "class_block":[
        ["class","id","tk_par_izq","id_object","tk_par_der" ,"tk_dos_puntos", "NEWLINE", "INDENT","block_sin_epsilon"]
    ],
    "id_object":[
        ["id"],
        ["object"]
    ],
    "return_0o1":[
        ["return", "return_aux"],
        [""]
    ],
    'argumentos_0o1': [
        ["coma_fc"],
        [""]
    ],
    'argumentos_star': [
        ["tk_coma", "coma_fc"],
        [""]
    ],
    'coma_fc': [
        ["id", "tk_dos_puntos", "tipo", "argumentos_star"],
        ["self", "tk_dos_puntos", "tipo", "argumentos_star"],
    ],
    'block_0o1': [
        ["block_sin_epsilon", "NEWLINE"],
        [""]
    ],
    'return_aux': [
        ["expr"],
        [""]
    ],
    'identaciones_bse': [
        ['NEWLINE',"dedent_0o1"],
        ['DEDENT'],
        ['']
    ],
    "dedent_0o1":[
       ["DEDENT"],
        [""]
    ],
    'block_sin_epsilon': [
        ['conditional_block', 'identaciones_bse', 'block_con_epsilon'],
        ['statement', 'identaciones_bse', 'block_con_epsilon'],
        ['loop_block', 'identaciones_bse', 'block_con_epsilon'],
        ["function_block", 'identaciones_bse', 'block_con_epsilon'],
        ["class_block", 'identaciones_bse', 'block_con_epsilon']
    ]
    , 'block_con_epsilon': [
        ['conditional_block', 'identaciones_bse', 'block_con_epsilon'],
        ['statement', 'identaciones_bse', 'block_con_epsilon'],
        ['loop_block', 'identaciones_bse', 'block_con_epsilon'],
        ["function_block", 'identaciones_bse', 'block_con_epsilon'],
        ["class_block", 'identaciones_bse', 'block_con_epsilon'],
        [""]
    ],
    'program': [
        ['block_con_epsilon']
    ],
    "literal": [["None"], ["True"], ["False"], ["tk_numero"], ["tk_cadena"]],
    "expr": [["expr_p2", "expr_aux"]],
    "expr_aux": [["if", "expr", "else", "expr_p2", "expr_aux"], [""]],
    "expr_p2": [["expr_p3", "expr_p2_aux"]],
    "expr_p2_aux": [["or", "expr_p3", "expr_p2_aux"], [""]],
    "expr_p3": [["expr_p4", "expr_p3_aux"]],
    "expr_p3_aux": [["and", "expr_p4", "expr_p3_aux"], [""]],
    "expr_p4": [["not", "expr_p4"], ["cexpr"]],
    "cexpr": [["cexpr_p6", "cexpr_aux"]],
    "cexpr_aux": [["bin_op_log", "cexpr_p6", "cexpr_aux"], [""]],
    "bin_op_log": [["tk_igual"], ["tk_diferente"], ["tk_mayor"], ["tk_menor"], ["tk_mayor_igual"], ["tk_menor_igual"],
                   ["is"]],
    "cexpr_p6": [["cexpr_p7", "cexpr_p6_aux"]],
    "cexpr_p6_aux": [["bin_op_p6", "cexpr_p7", "cexpr_p6_aux"], [""]],
    "bin_op_p6": [["tk_suma"], ["tk_menos"]],
    "cexpr_p7": [["cexpr_p8", "cexpr_p7_aux"]],
    "cexpr_p7_aux": [["bin_op_p7", "cexpr_p8", "cexpr_p7_aux"], [""]],
    "bin_op_p7": [["tk_multiplicacion"], ["tk_division"], ["tk_modulo"]],
    "cexpr_p8": [["tk_menos", "cexpr_p8"], ["cexpr_p9"]],
    "cexpr_p9": [["cexpr_p10", "cexpr_p9_aux"]],
    "cexpr_p9_aux": [["tk_punto", "id", "cexpr_p10_aux", "cexpr_p9_aux"],
                     ["tk_corch_izq", "expr", "tk_corch_der", "cexpr_p9_aux"], [""]],
    "cexpr_p10": [["id", "cexpr_p10_aux"], ["literal"], ["tk_corch_izq", "expr_list_no_req", "tk_corch_der"],
                  ["tk_par_izq", "expr", "tk_par_der"], ["len", "tk_par_izq", "cexpr", "tk_par_der"],["self"]],
    "cexpr_p10_aux": [["tk_par_izq", "expr_list_no_req", "tk_par_der"], [""]],
    "expr_list_no_req": [["expr", "expr_list_0_more"], [""]],
    "expr_list_0_more": [["tk_coma", "expr", "expr_list_0_more"], [""]],

}

pred_rules = {}

for k in grammar.keys():
    pred_rules[k] = []


def log(s, debug=0):
    if debug:
        print(s)


def PRIMEROS(alpha, debug=0):
    alpha = [alpha] if type(alpha) is str else alpha

    set_primeros = set()
    if alpha[0] == "":  # 1. Si alpha == epsilon
        set_primeros = set_primeros.union([""])
        return set_primeros

    if alpha[0] not in not_terminals:  # 2a. a_1 es un terminal

        set_primeros = set_primeros.union([alpha[0]])
        return set_primeros

    else:
        if alpha[0] != alpha[0]:
            set_primeros = set_primeros.union(PRIMEROS(alpha[0], debug))

            if "" in set_primeros:
                if len(alpha) == 1:
                    pass
                else:
                    try:
                        set_primeros.remove("")
                    except KeyError:
                        pass

                    set_primeros = set_primeros.union(PRIMEROS(alpha[1:], debug))

            return set_primeros

        else:
            # log("alpha[0] != alpha", debug)
            for regla in grammar[alpha[0]]:
                set_primeros = set_primeros.union(PRIMEROS(regla, debug))
            log("Después de Revisar las Reglas de " + alpha[0] + " Se encontró que sus PRIMEROS son: " + str(
                set_primeros), debug)
            pass

    return set_primeros


def SIGUIENTES(no_terminal):
    global recursive_calls
    set_siguientes = set()
    if no_terminal == initial_symbol_grammar:
        set_siguientes = set_siguientes.union(set("$"))

    for nt, rules in grammar.items():
        for rule in rules:
            try:
                index = rule.index(no_terminal)
                if index == len(rule) - 1:
                    beta = ""
                else:
                    beta = rule[index + 1:]

                if type(beta) == str:
                    beta = [beta]

                primeros_beta = PRIMEROS(beta)
                set_siguientes = set_siguientes.union(primeros_beta)
                set_siguientes.remove("")
                if "" in primeros_beta or beta == "":
                    if nt not in recursive_calls:
                        recursive_calls.append(no_terminal)
                        set_siguientes = set_siguientes.union(SIGUIENTES(nt))
            except ValueError:
                pass
            except KeyError:
                pass

    return set_siguientes


def PRED(no_terminal):
    for rule in grammar[no_terminal]:
        set_prediccion = set()
        primeros_alpha = PRIMEROS(rule)

        if "" in primeros_alpha:
            set_prediccion = set_prediccion.union(primeros_alpha)
            set_prediccion.remove("")
            followings = SIGUIENTES(no_terminal)
            set_prediccion = set_prediccion.union(followings)

        else:
            set_prediccion = set_prediccion.union(primeros_alpha)

        lst_tmp = []
        for i in set_prediccion:
            lst_tmp.append(i)

        pred_rules[no_terminal].append(lst_tmp)


def emparejar(token, token_esperado, lexer):
    global i, j
    # Emparejar No Terminales
    if token == token_esperado:
        token, i, j = lexer.getNextToken(i, j)
        # print("af" , token)
    else:
        errorSintaxis([token_esperado])
    return token, i, j


def errorSintaxis(lista_tokens_Esperados):
    global token, i, j, flagSintaxis
    flagSintaxis = True
    str_tmp = ""
    for token_esperado in lista_tokens_Esperados:
        str_tmp += "'" + token_esperado + "', "
    print(
        "<" + str(i+1) + "," + str(j) + ">" + "Error sintactico: se encontro: '" + str(token) + "' y se esperaba " + str(
            str_tmp[:-2]) + ".")


def nonTerminal(N, lexer):
    global token, i, j
    for idx, pd in enumerate(pred_rules[N]):
        if flagSintaxis:
            return
        if token[0] in pd:
            for symbol in grammar[N][idx]:
                if flagSintaxis:
                    return
                if symbol in not_terminals:
                    nonTerminal(symbol, lexer)
                    if flagSintaxis:
                        return
                elif symbol == "":
                    if flagSintaxis:
                        return
                else:
                    token, i, j = emparejar(token[0], symbol, lexer)
                    if i == -1 and j == -1:
                        token = ("$", i, j)
                    if flagSintaxis:
                        return
            return
    errorSintaxis(pd)
    return

def main():
    global token, i, j, recursive_calls

    for nt in not_terminals:
        recursive_calls = []
        PRED(nt)

    lexer = Lexer("test.py")
    with open("output.txt", "w") as file:
        token, i, j = lexer.getNextToken(i, j)
        nonTerminal(initial_symbol_grammar, lexer)
        if not flagSintaxis:
            if token[0] == '$':
                print("El analisis sintactico ha finalizado exitosamente.")
            else:
                errorSintaxis(["No se esperaba este token"])
                print(token)

if __name__ == '__main__':
    main()