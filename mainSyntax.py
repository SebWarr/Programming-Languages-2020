from mainLexer import Lexer, Token

flagSintaxis = False

token = ""
i = 0
j= 0

initial_symbol_grammar = "expr"
not_terminals = ["literal", "expr", "expr_aux", "expr_p2", "expr_p2_aux", "expr_p3", "expr_p3_aux", "expr_p4",
                 "cexpr", "cexpr_nrec", "cexpr_tmp", "cexpr_aux", "bin_op_log", "cexpr_p6", "cexpr_p6_aux", "bin_op_p6",
                 "cexpr_p7", "cexpr_p7_aux", "bin_op_p7", "cexpr_p8", "cexpr_p9", "cexpr_p9_aux", "expr_list_no_req",
                 "expr_list_0_more", "target"]

grammar = {
    "literal": [["None"], ["True"], ["False"], ["tk_numero"], ["tk_cadena"]],
    "expr": [["expr_p2", "expr_aux"]],
    "expr_aux": [["if", "expr", "else", "expr", "expr_aux"], [""]],
    "expr_p2": [["expr_p3", "expr_p2_aux"]],
    "expr_p2_aux": [["or", "expr", "expr_p2_aux"], [""]],
    "expr_p3": [["expr_p4", "expr_p3_aux"]],
    "expr_p3_aux": [["and", "expr", "expr_p3_aux"], [""]],
    "expr_p4": [["not", "expr"], ["cexpr"]],
    "cexpr": [["cexpr_tmp", "cexpr_nrec"]],
    "cexpr_nrec": [["tk_punto", "id", "cexpr_p9_aux", "cexpr_nrec"],
                   ["tk_corch_izq", "expr", "tk_corch_der", "cexpr_nrec"], [""]],
    "cexpr_tmp": [["cexpr_p6", "cexpr_aux"]],
    "cexpr_aux": [["bin_op_log", "cexpr"], [""]],
    "bin_op_log": [["tk_igual"], ["tk_diferente"], ["tk_mayor"], ["tk_menor"], ["tk_mayor_igual"], ["tk_menor_igual"], ["is"]],
    "cexpr_p6": [["cexpr_p7", "cexpr_p6_aux"]],
    "cexpr_p6_aux": [["bin_op_p6", "cexpr", "cexpr_p6_aux"], [""]],
    "bin_op_p6": [["tk_suma"], ["tk_menos"]],
    "cexpr_p7": [["cexpr_p8", "cexpr_p7_aux"]],
    "cexpr_p7_aux": [["bin_op_p7", "cexpr", "cexpr_p7_aux"], [""]],
    "bin_op_p7": [["tk_multiplicacion"], ["tk_division"], ["tk_modulo"]],
    "cexpr_p8": [["tk_menos", "cexpr"], ["cexpr_p9"]],
    "cexpr_p9": [["id", "cexpr_p9_aux"], ["literal"], ["tk_corch_izq", "expr_list_no_req", "tk_corch_der"],
                 ["tk_par_izq", "expr", "tk_par_der"]],
    "cexpr_p9_aux": [["tk_par_izq", "expr_list_no_req", "tk_par_der"], [""]],
    "expr_list_no_req": [["expr", "expr_list_0_more"], [""]],
    "expr_list_0_more": [["tk_coma", "expr", "expr_list_0_more"], [""]],
    "target": [["id"], ["member_expr"], ["index_expr"]]
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
        log("Se agregó " + "epsilon" + " al conjunto de primeros\n", debug)
        return set_primeros

    if alpha[0] not in not_terminals:  # 2a. a_1 es un terminal

        set_primeros = set_primeros.union([alpha[0]])
        log("Se agregó " + alpha[0] + " al conjunto de primeros\n", debug)
        return set_primeros

    else:  # 2b. a_1 es un no terminal

        if alpha[0] != alpha[0]:

            log("Hacemos unión con: PRIMEROS(" + alpha[0] + ")", debug)
            set_primeros = set_primeros.union(PRIMEROS(alpha[0], debug))

            if "" in set_primeros:
                log(str(alpha) + str(set_primeros), debug)
                if len(alpha) == 1:
                    pass
                else:
                    log("Hacemos unión con: PRIMEROS(" + str(alpha[1:]) + ")", debug)

                    # ??????
                    try:
                        set_primeros.remove("")
                    except KeyError:
                        pass

                    set_primeros = set_primeros.union(PRIMEROS(alpha[1:], debug))

            return set_primeros

        else:
            log("alpha[0] != alpha", debug)
            log("\nSe encontró que " + alpha[0] + " es un no terminal, asi que revisaremos sus reglas", debug)
            for regla in grammar[alpha[0]]:
                log("Regla a desglosar: " + str(regla), debug)
                set_primeros = set_primeros.union(PRIMEROS(regla, debug))
            log("Después de Revisar las Reglas de " + alpha[0] + " Se encontró que sus PRIMEROS son: " + str(
                set_primeros), debug)
            pass

    return set_primeros


def SIGUIENTES(no_terminal):
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
                set_siguientes = set_siguientes.union(primeros_beta)
                set_siguientes.remove("")
                # print(set_siguientes)
                # len(set_siguientes) no ha cambiado break
                if "" in primeros_beta or beta == "":
                    # tmp_len = len(set_siguientes)
                    if nt != no_terminal:  # Ni idea si sirve
                        set_siguientes = set_siguientes.union(SIGUIENTES(nt))
                    # if len(set_siguientes) == tmp_len:
                    #     break

            except ValueError:
                pass
            except KeyError:
                pass

    return set_siguientes


def PRED(no_terminal):
    print(no_terminal)
    for rule in grammar[no_terminal]:
        set_prediccion = set()
        primeros_alpha = PRIMEROS(rule)

        if "" in primeros_alpha:
            set_prediccion = set_prediccion.union(primeros_alpha)
            set_prediccion.remove("")
            set_prediccion = set_prediccion.union(SIGUIENTES(no_terminal))

        else:
            set_prediccion = set_prediccion.union(primeros_alpha)

        lst_tmp = []
        for i in set_prediccion:
            lst_tmp.append(i)

        pred_rules[no_terminal].append(lst_tmp)


def emparejar(token, token_esperado, lexer):
    global i,j

    print("\n\n\nEMPAREJARRRR!!!")
    print(token,token_esperado)
    # Emparejar No Terminales
    if token == token_esperado:
        token, i, j = lexer.getNextToken(i, j)
        print("af" , token)
    else:
        errorSintaxis([token_esperado])
        #token = -1  # Hubo un error
    print(token,"\n\n\n")
    return token, i, j


def errorSintaxis(lista_tokens_Esperados):
    global token,i,j, flagSintaxis
    flagSintaxis= True
    str_tmp = ""
    for token_esperado in lista_tokens_Esperados:
        str_tmp += "'" + token_esperado + "', "
    print(
        "<" + str(i) + "," + str(j) + ">" + "Error sintactico: se encontro: '" + str(token) + "' y se esperaba " + str(str_tmp[:-2]) + ".")


def nonTerminal(N, lexer):

    global token,i,j
    for idx, pd in enumerate(pred_rules[N]):
        if flagSintaxis:
            return
        print(token, "Capa 1")
        if token[0] in pd:
            print(pd, "\n")
            for symbol in grammar[N][idx]:
                if flagSintaxis:
                    return
                print(grammar[N][idx], symbol)
                if symbol in not_terminals:
                    print("Bajo al terminal: ", symbol, "\n")
                    nonTerminal(symbol, lexer)
                    if flagSintaxis:
                        return
                elif symbol == "":
                    print("cadena vacía")
                    if flagSintaxis:
                        return
                else:
                    token, i, j = emparejar(token[0], symbol, lexer)
                    print(token, "Capa 2", i, j)
                    if i == -1 and j == -1:
                        token = ("$", i, j)
                    if flagSintaxis:
                        return
            return
    errorSintaxis(pd)
    print("No se encontró regla que se adapte a ese token", token, pd)
    return



def main():
    print("aiuda")
    global token,i,j
    # for nt in not_terminals:
    #    PRED(nt)
    # print(pred_rules)
    PRED("expr_aux")

    lexer = Lexer("test.py")
    for nt in not_terminals:
        PRED(nt)

    with open("output.txt", "w") as file:
        #while i != -1 and j != -1:
        token, i, j = lexer.getNextToken(i, j)
        #print(token, i, j)
        nonTerminal("expr", lexer)
        # lexer.escrituraToken(file, token)
        print("FIN DE ARCHIVO")

if __name__ == '__main__':
    main()