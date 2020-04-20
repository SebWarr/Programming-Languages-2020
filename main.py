##REVISAR SI IMPRIME ID ANTES DE ENCONTRAR ERROR (duda)
## REVISAR STRINGS
## ID NO DEBE RECIBIR CARACTERES RAROS, ARREGLAR STRINGS X2 Y NUMEROS RANGO ASCII
## LEER NUMERO, SI PROXIMO CARACTER != NUMERO SI ES VACIO ENTONCES ES UN INT , SI ES OTRA COSA QUE NO SEA OPERACION O PARENTESIS ES ERROR
## MANEJO DE ERRORES EN EXPRESIONES REGULARES
##ver expresiones regulares
## ver que el buffer substring no tenga nada para seguir adicionado en caso de hallar un conversor2
### REVISAR ROMPER LA EJECUCION DE CODIGO CUANDO HAYA ERROR
import re

global_keywords = ["False", "None", "True", "and", "as", "assert", "async", "await", "break", "class", "continue",
                   "object", "def", "del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import",
                   "in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return", "try", "while", "with",
                   "yield", "bool", "str", "int", "len", "print", "input", "__init__", "self"]

dict_conversor2 = {
    "+": "tk_suma",
    "*": "tk_multiplicacion",
    "%": "tk_modulo",
    "(": "tk_par_izq",
    ")": "tk_par_der",
    ".": "tk_punto",
    ",": "tk_coma",
    ":": "tk_dos_puntos",
    "[": "tk_corch_izq",
    "]": "tk_corch_der",
    "{": "tk_llave_izq",
    "}": "tk_llave_der"
}

str_re = re.compile('"([ -~])*"') #no se usa
id_re = re.compile("([A-Z]|[a-z]|_)([0-9]|[a-z]|[A-Z]|_)*")
num_re = re.compile("\d+")
global_keywords_appear = []


class Token:

    def __init__(self, fila, columna, lexema, tipo):
        self.fila = fila
        self.columna = columna
        self.lexema = lexema
        self.tipo = tipo


def isTokenNumber(possible_substr, char, idx, jdx):
    if num_re.match(possible_substr) and not num_re.match(char):
        if len(list(num_re.finditer(possible_substr))) > 0:
            global_keywords_appear.append(
                ("tk_numero", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
            return ""
    return possible_substr


def isTokenId(possible_substr, idx, jdx):
    if len(list(id_re.finditer(possible_substr))) > 0 and possible_substr != "":
        global_keywords_appear.append(
            ("id", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
        return ""
    return possible_substr

def isTokenResWord(possible_substr, idx, jdx):
    if possible_substr in global_keywords:
        global_keywords_appear.append((possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
        return ""
    return possible_substr

def isTokenSymbol(char, idx, jdx):
    if char in list(x for x in dict_conversor2.keys()):
        global_keywords_appear.append((dict_conversor2[char], idx + 1, jdx + 1))

def lexical_analysis(input_file):
    input_string = open(input_file, "r").read()

    flag_str = False
    caso_especial = False
    for idx, line in enumerate(input_string.splitlines()):
        line += " "
        possible_substr = ""
        for jdx, char in enumerate(line):
            print(char)
            if not caso_especial:
                ##hay un salto en la lectura de caracteres por signos dobles o string
                ##POSIBLE MEJORA: no recorreer con enummerate sino con i in range len(line)
                ## y adelantar arbitrariamente i
                if char != " " and char not in list(x for x in dict_conversor2.keys()):
                    ##si no es espacio, ni token especiales y está en rango Ascii permitido
                    ##analizar tokens especiales trickies
                    # ver si es un comentario
                    if char == "#" and not flag_str:
                        break
                    elif flag_str and jdx + 1 == len(line):
                        global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                        print("Se ha encontrado un error léxico en:", idx + 1, jdx + 1, char)
                        return global_keywords_appear
                    if (ord(char) <= 31 or ord(char) >= 127):

                        print("Se ha encontrado un error léxico en:", idx + 1, jdx + 1, char)
                        caso_especial = True  ##BREAK
                        if flag_str:
                            global_keywords_appear.append(("tk_cadena", possible_substr, idx + 1, jdx + 1))
                        elif len(possible_substr) != 0:
                            if possible_substr in global_keywords:
                                # ver si lo que llevo en substring es palabra reservada
                                global_keywords_appear.append(
                                    (possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                                possible_substr = ""
                            elif len(list(num_re.finditer(possible_substr))) > 0 and len(
                                    list(id_re.finditer(possible_substr))) == 0:
                                global_keywords_appear.append(
                                    ("tk_numero", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                                possible_substr = ""
                            else:
                                global_keywords_appear.append(("id", possible_substr, idx + 1, jdx + 1))
                        global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                        return global_keywords_appear
                    if (not flag_str):
                        if char == "-":
                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)

                            if jdx + 2 < len(line):
                                if line[jdx + 1] == '>':
                                    global_keywords_appear.append(("tk_ejecuta", idx + 1, jdx + 1))
                                    caso_especial = True
                                else:
                                    global_keywords_appear.append(("tk_menos", idx + 1, jdx + 1))
                            else:
                                global_keywords_appear.append(("tk_menos", idx + 1, jdx + 1))

                        elif char == "!":
                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)

                            if jdx + 2 < len(line):
                                if line[jdx + 1] == '=':
                                    global_keywords_appear.append(("tk_diferente", idx + 1, jdx + 1))
                                    caso_especial = True
                                else:
                                    global_keywords_appear.append(
                                        f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                    return global_keywords_appear
                            else:
                                global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                return global_keywords_appear

                        elif char == "/":

                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)

                            if jdx + 2 < len(line):
                                if line[jdx + 1] == '/':
                                    global_keywords_appear.append(("tk_division", idx + 1, jdx + 1))
                                    caso_especial = True
                                else:
                                    global_keywords_appear.append(
                                        f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                    return global_keywords_appear
                            else:
                                global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                return global_keywords_appear

                        elif char == "<":
                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)

                            if jdx + 2 < len(line):
                                if line[jdx + 1] == '=':
                                    global_keywords_appear.append(("tk_menor_igual", idx + 1, jdx + 1))
                                    caso_especial = True
                                else:
                                    global_keywords_appear.append(("tk_menor", idx + 1, jdx + 1))
                            else:
                                global_keywords_appear.append(("tk_menor", idx + 1, jdx + 1))

                        elif char == ">":
                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)

                            if jdx + 2 < len(line):
                                if line[jdx + 1] == '=':
                                    global_keywords_appear.append(("tk_mayor_igual", idx + 1, jdx + 1))
                                    caso_especial = True
                                else:
                                    global_keywords_appear.append(("tk_mayor", idx + 1, jdx + 1))
                            else:
                                global_keywords_appear.append(("tk_mayor", idx + 1, jdx + 1))

                        elif char == "=":
                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)
                            if jdx + 2 < len(line):
                                if line[jdx + 1] == '=':
                                    global_keywords_appear.append(("tk_igual", idx + 1, jdx + 1))
                                    caso_especial = True
                                else:
                                    global_keywords_appear.append(("tk_asig", idx + 1, jdx + 1))
                            else:
                                global_keywords_appear.append(("tk_asig", idx + 1, jdx + 1))
                        # Darle trato especial a los strings
                        elif char == '"':
                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                            possible_substr = isTokenId(possible_substr, idx, jdx)
                            possible_substr += char
                            flag_str = True

                        elif (char == "\\"):
                            ##Se encontro una barra en algo en algo que no es String
                            ##REEEEVIIISAAAAAAR QUE PUEDE PASAR CON IDENTACIONES DE CODIGO
                            global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                            print("Se ha encontrado un error léxico en:", idx + 1, jdx + 1, char)
                            ##idea : iterar con iter_string (iter_string=jdx;jdx<len(line) )
                            ### possible_substr += char hasta encontrar otra ", si lo hace,
                            # procesar el possible_substring en la expresión regular
                            # ignorar las proximas iter_string iteraciones lo que esté en line
                            ###      y que el iter_string<len(line) si llega a
                        # elif ord(char) >= 48 and ord(char) <= 57:
                        #
                        else:
                            if (char == "\\"):
                                if (jdx + 2 == len(line)):
                                    global_keywords_appear.append(
                                        f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                    print("Se ha encontrado un error léxico en:", idx + 1, jdx + 1, char)
                                else:
                                    if (line[jdx + 1] != 'n' or line[jdx + 1] != 't' or line[jdx + 1] != '\\'):
                                        global_keywords_appear.append(
                                            f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                        print("Se ha encontrado un error léxico en:", idx + 1, jdx + 1, char)
                                    else:
                                        possible_substr += char
                                        print('tratar casos ')

                            possible_substr = isTokenNumber(possible_substr, char, idx, jdx)

                            if not char.isalnum() and char!="_":
                                possible_substr = isTokenId(possible_substr, idx, jdx)
                                global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                                return global_keywords_appear

                            possible_substr += char
                    else:
                        if (char == '"'): # encontro comillas de cierre
                            possible_substr = possible_substr + char
                            global_keywords_appear.append(
                                ("tk_cadena", possible_substr, idx + 1, jdx + 2 - len(possible_substr)))
                            possible_substr = ""
                            flag_str = False
                        else:
                            possible_substr = possible_substr + char
                else:
                    if flag_str:
                        if jdx + 1 == len(line):
                            global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                            return global_keywords_appear
                        elif char == " ":
                            possible_substr += char
                        else:
                            possible_substr += char
                    else: #encontró un caracter diferente;
                        possible_substr = isTokenResWord(possible_substr, idx, jdx)
                        possible_substr = isTokenNumber(possible_substr, char, idx, jdx)
                        possible_substr = isTokenId(possible_substr, idx, jdx)
                        isTokenSymbol(char, idx, jdx)

                        #### REPLANTEAR ESTO
                        # if possible_substr.__contains__("\""):
                        #     possible_substr += char
                        # elif char == "\n":
                        #     global_keywords_appear.append(
                        #         ("tk_newline", idx + 1, jdx + 1))
                        # elif char == "\t":
                        #     global_keywords_appear.append(
                        #         ("tk_ident", idx + 1, jdx + 1))
                        # elif char == "\\":
                        #     global_keywords_appear.append(
                        #         ("tk_continue_string", idx + 1, jdx + 1))
                        # else:
                        #     # ver si es error
                        #     if char != " ":
                        #         global_keywords_appear.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
                        #         print("Se ha encontrado un error léxico en:", idx + 1, jdx + 1, char)
                        #         #                 - len(possible_substr)
                        #         break
                        #     else:
                        #         if (flag_str):
                        #             possible_substr += char

            else:
                caso_especial = False

    tokens = global_keywords_appear
    return tokens


def main():
    list_tokens = lexical_analysis("test.py")
    with open("output.txt", "w") as file:
        for token in list_tokens:
            if len(token) == 4:
                file.write(
                    "<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + "," + str(token[3]) + ">\n")
            elif len(token) == 3:
                file.write("<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + ">\n")
            else:
                file.write(str(token))
        file.close()


if __name__ == '__main__':
    main()
