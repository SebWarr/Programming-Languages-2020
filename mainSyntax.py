from mainLexer import Lexer, Token

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
                 "cexpr_p10_aux",
                 "expr_list_no_req", "expr_list_0_more",
                 'asignacion', 'asignacion_fc_nta', 'salida_aux', 'id_fc_aux', 'no_terminal_aux', 'operators',
                 'tk_corch_izq_fc',
                 'global_declaracion', 'nonlocal_declaracion', 'declaracion_asignacion_fc', 'print_statement',
                 'conditional_statement_1', 'conditional_statement_2', 'conditional_statement_3',
                 'ternary_statement', 'statement', 'conditional_block', 'block', 'newline1_fc', 'newline2_fc',
                 'newline2star_fc',
                 'loop_block', 'block_sin_epsilon', 'block_con_epsilon', 'identaciones_bse'
                 ]

grammar = {

    "tipo": [["int"], ["str"], ["bool"], ["object"], ["tk_corch_izq", "tipo", "tk_corch_der"]],
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
        ['asignacion']
    ],
    'print_statement': [
        ['print', 'tk_par_izq', 'expr', 'tk_par_der']
    ],
    'conditional_statement_1': [
        ['if', 'expr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'statement', 'newline1_fc']
    ],
    'newline1_fc': [
        ['NEWLINE', 'DEDENT'],
        ['DEDENT']
    ],
    'conditional_statement_2': [
        ['elif', 'expr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'statement', 'newline2_fc'],
        ['']
    ],
    'newline2_fc': [
        ['NEWLINE', 'DEDENT', 'newline2star_fc'],
        ['DEDENT']
    ],
    'newline2star_fc': [
        ['conditional_statement_2'],
        ['']
    ],
    'conditional_statement_3': [
        ['else', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'statement',"newline1_fc" ],
        ['']
    ],
    'ternary_statement': [
        ['expr']
    ],
    'statement': [
        ['print_statement'],
        ['id', 'declaracion_asignacion_fc'],
        ['global_declaracion'],
        ['nonlocal_declaracion'],
        ['pass']
    ],
    'conditional_block': [
        ['conditional_statement_1', 'conditional_statement_2', 'conditional_statement_3'],
        ['ternary_statement']
    ],
    'block': [
        ['conditional_block', 'block'],
        ['statement', 'block'],
        ['']
    ],
    'loop_block': [
        ['while', 'expr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon'],
        ['for', 'id', 'in', 'cexpr', 'tk_dos_puntos', 'NEWLINE', 'INDENT', 'block_sin_epsilon']
    ],
    'identaciones_bse': [
        ['NEWLINE'],
        ['DEDENT']
        , ['']
    ],
    'block_sin_epsilon': [
        ['conditional_block', 'identaciones_bse', 'block_con_epsilon'],
        ['statement', 'identaciones_bse', 'block_con_epsilon'],
        ['loop_block', 'identaciones_bse', 'block_con_epsilon']
    ]
    , 'block_con_epsilon': [
        ['conditional_block', 'identaciones_bse', 'block_con_epsilon'],
        ['statement', 'identaciones_bse', 'block_con_epsilon'],
        ['loop_block', 'identaciones_bse', 'block_con_epsilon'],
        ['']
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
                  ["tk_par_izq", "expr", "tk_par_der"]],
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
        # log("Se agregó " + "epsilon" + " al conjunto de primeros\n", debug)
        return set_primeros

    if alpha[0] not in not_terminals:  # 2a. a_1 es un terminal

        set_primeros = set_primeros.union([alpha[0]])
        # log("Se agregó " + alpha[0] + " al conjunto de primeros\n", debug)
        return set_primeros

    else:  # 2b. a_1 es un no terminal

        if alpha[0] != alpha[0]:

            # log("Hacemos unión con: PRIMEROS(" + alpha[0] + ")", debug)
            set_primeros = set_primeros.union(PRIMEROS(alpha[0], debug))

            if "" in set_primeros:
                #   log(str(alpha) + str(set_primeros), debug)
                if len(alpha) == 1:
                    pass
                else:
                    #     log("Hacemos unión con: PRIMEROS(" + str(alpha[1:]) + ")", debug)

                    # ??????
                    try:
                        set_primeros.remove("")
                    except KeyError:
                        pass

                    set_primeros = set_primeros.union(PRIMEROS(alpha[1:], debug))

            return set_primeros

        else:
            # log("alpha[0] != alpha", debug)
            # log("\nSe encontró que " + alpha[0] + " es un no terminal, asi que revisaremos sus reglas", debug)
            for regla in grammar[alpha[0]]:
                #   log("Regla a desglosar: " + str(regla), debug)
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
                # print(index, nt, rule)
                else:
                    beta = rule[index + 1:]

                if type(beta) == str:
                    beta = [beta]

                primeros_beta = PRIMEROS(beta)
                # print("Se pide Primeros(" + str(beta) + ") = ", primeros_beta)
                set_siguientes = set_siguientes.union(primeros_beta)
                set_siguientes.remove("")
                # print(set_siguientes)
                # len(set_siguientes) no ha cambiado break
                if "" in primeros_beta or beta == "":
                    #   print("o bien epsilon esta en primeros_beta o beta es epsilon")
                    #   print(nt, no_terminal)
                    # tmp_len = len(set_siguientes)
                    if nt not in recursive_calls:  # Ni idea si sirve # Se agrega union de conjuntos
                        # print("Como son distintos, pedimos Siguientes(" + nt + ")")
                        recursive_calls.append(no_terminal)
                        set_siguientes = set_siguientes.union(SIGUIENTES(nt))
                    # if len(set_siguientes) == tmp_len:
                    #     break

            except ValueError:
                pass
            except KeyError:
                pass

    return set_siguientes


def PRED(no_terminal):
    # print(no_terminal)
    for rule in grammar[no_terminal]:
        set_prediccion = set()
        # print("Se pide Primeros(" + str(rule) + ")")
        primeros_alpha = PRIMEROS(rule)
        # with open("firsts.txt", "a") as file:
        #    file.write('Primeros de regla {}\n{}\n'.format(no_terminal,primeros_alpha))
        # print("primeros alpha = ", str(primeros_alpha))

        if "" in primeros_alpha:
            set_prediccion = set_prediccion.union(primeros_alpha)
            set_prediccion.remove("")
            # print("set Prediccion = ", str(set_prediccion))
            # print("Se pide siguientes(" + str(no_terminal) + ")")
            followings = SIGUIENTES(no_terminal)
            # with open("Followings.txt", "a") as file:
            #   file.write('Siguientes de regla {}\n{}\n'.format(no_terminal,followings))
            set_prediccion = set_prediccion.union(followings)

        else:
            set_prediccion = set_prediccion.union(primeros_alpha)

        lst_tmp = []
        for i in set_prediccion:
            lst_tmp.append(i)

        pred_rules[no_terminal].append(lst_tmp)


def emparejar(token, token_esperado, lexer):
    global i, j

    # print("\n\n\nEMPAREJARRRR!!!")
    # print(token,token_esperado)
    # Emparejar No Terminales
    if token == token_esperado:
        token, i, j = lexer.getNextToken(i, j)
        # print("af" , token)
    else:
        errorSintaxis([token_esperado])
        # token = -1  # Hubo un error
    # print(token,"\n\n\n")
    return token, i, j


def errorSintaxis(lista_tokens_Esperados):
    global token, i, j, flagSintaxis
    flagSintaxis = True
    str_tmp = ""
    for token_esperado in lista_tokens_Esperados:
        str_tmp += "'" + token_esperado + "', "
    print(
        "<" + str(i) + "," + str(j) + ">" + "Error sintactico: se encontro: '" + str(token) + "' y se esperaba " + str(
            str_tmp[:-2]) + ".")


def nonTerminal(N, lexer):
    global token, i, j
    for idx, pd in enumerate(pred_rules[N]):
        if flagSintaxis:
            return
        # print(token, "Capa 1")
        if token[0] in pd:
            #   print(pd, "\n")
            #   print(N)
            #   print("idx: ", idx, grammar[N], "puta",pred_rules[N])
            for symbol in grammar[N][idx]:
                if flagSintaxis:
                    return
                print(grammar[N][idx], symbol)
                if symbol in not_terminals:
                    # print("Bajo al terminal: ", symbol, "\n")
                    nonTerminal(symbol, lexer)
                    if flagSintaxis:
                        return
                elif symbol == "":
                    # print("cadena vacía")
                    if flagSintaxis:
                        return
                else:
                    token, i, j = emparejar(token[0], symbol, lexer)
                    # print(token, "Capa 2", i, j)
                    if i == -1 and j == -1:
                        token = ("$", i, j)
                    if flagSintaxis:
                        return
            return
    errorSintaxis(pd)
    print("No se encontró regla que se adapte a ese token", token, pd)
    return


def main():
    global token, i, j, recursive_calls

    for nt in not_terminals:
        recursive_calls = []
        PRED(nt)
    ## Checking LL1 propiety of disjoints sets in PRED sets
    ## predictions.txt helps visualize the interjection problems and PRED sets for each rule

    """ with open("predictions.txt", "w") as file:
        for key,value in pred_rules.items():
            file.write('Prediction set for {}\n\n{}\n\n'.format(key, value))    
            for i in range(len(value)):
                for j in range(i+1,len(value)):
                    inter = set(value[i]).intersection(set(value[j]))
                    if len(inter)!=0:
                        file.write('\n\n\n######## NOT DISJOINT PREDICTION SETS OF RULE {} ###########\n\n SETS\n {}\n and\n {}\n WITH COMMON ELEMENT(S) {}'.format(key,value[i],value[j],inter))
                        file.write('\n\n##################################################################\n\n ')    """
    lexer = Lexer("test.py")
    with open("output.txt", "w") as file:
        # while i != -1 and j != -1:
        token, i, j = lexer.getNextToken(i, j)
        # print(token, i, j)
        nonTerminal(initial_symbol_grammar, lexer)
        # lexer.escrituraToken(file, token)
        if not flagSintaxis:
            if token[0] == '$':
                print("FIN DE ARCHIVO")
            else:
                errorSintaxis(["No se esperaba este token"])
                print(token)


if __name__ == '__main__':
    main()