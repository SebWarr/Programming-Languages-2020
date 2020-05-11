import re

global_keywords = ["False", "None", "True", "and", "as", "assert", "async", "await", "break", "class", "continue",
                   "object", "def", "del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import",
                   "in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return", "try", "while", "with",
                   "yield", "bool", "str", "int", "len", "print", "input", "__init__", "self"]

dict_operators_len1 = {
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

dict_operators_len2 = {
    "!": ["!", "=", 0, "diferente"],
    "/": ["/", "/", 0, "division"],
    "-": ["-", ">", 1, "ejecuta", "menos"],
    "<": ["<", "=", 1, "menor_igual", "menor"],
    ">": [">", "=", 1, "mayor_igual", "mayor"],
    "=": ["=", "=", 1, "igual", "asig"]
}

# REGEX
id_re = re.compile("([A-Z]|[a-z]|_)([0-9]|[a-z]|[A-Z]|_)*")
num_re = re.compile("\d+")
tokens_list = []
next = True
newLine = True
tokenNewLine = False
indentationStack = [0]


class Token:

    def __init__(self, fila, columna, lexema, tipo):
        self.fila = fila
        self.columna = columna
        self.lexema = lexema
        self.tipo = tipo

class Lexer:

    fileName = ""

    def __init__(self, fileName):
        self.fileName = fileName
        self.str_code = open(self.fileName, "r").read()

    def isTokenNumber(self, possible_substr, char, idx, jdx):
        if num_re.match(possible_substr) and not num_re.match(char):
            if len(list(num_re.finditer(possible_substr))) > 0:
                tokens_list.append(
                    ("tk_numero", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                return "", False
        return possible_substr, True


    def isTokenId(self, possible_substr, idx, jdx):
        if len(list(id_re.finditer(possible_substr))) > 0 and possible_substr != "":
            tokens_list.append(
                ("id", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))

            return "", False
        return possible_substr, True


    def isTokenResWord(self, possible_substr, idx, jdx):
        if possible_substr in global_keywords:
            tokens_list.append((possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
            return "", False
        return possible_substr, True


    def isTokenSymbol(self, char, idx, jdx):
        if char in list(x for x in dict_operators_len1.keys()):
            tokens_list.append((dict_operators_len1[char], idx + 1, jdx + 1))
            return False
        return True


    def print_lexical_error(self, idx, jdx, char, token_buffer):
        tokens_list.append(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
        print(f">>>Error léxico(linea:{idx + 1},posicion:{jdx + 1})")
        return tokens_list[-1], [], -2, -2


    def print_indentation_error(self, idx, jdx):
        tokens_list.append(f">>>Error léxico de indentacion (linea:{idx + 1},posicion:{jdx + 1})")
        print(f">>>Error léxico de indentacion (linea:{idx + 1},posicion:{jdx + 1})")
        return tokens_list[-1], [], -2, -2


    def lexical_analysis(self, input_string, token_buffer, i=0, j=0):
        global newLine
        global indentationStack
        global tokenNewLine
        next = True
        flag_str = False
        caso_especial = False
        list_lines = input_string.splitlines()
        if len(list_lines[i]) == j:
            j = 0
            i += 1
            newLine = True

        for idx in range(i, len(list_lines)):
            list_lines[idx] += " "
            possible_substr = ""
            line = list_lines[idx]
            for jdx in range(j, len(line)):
                # print(char) # Solo para debug
                if next:
                    char = line[jdx]
                    # Indentaciones
                    if newLine:

                        if char != " " and char != "#":
                            if tokenNewLine:
                                tokenNewLine = False
                                tokens_list.append(("NEWLINE", idx + 1, jdx))
                                return tokens_list[-1], [], idx, jdx
                            newLine = False
                            tokenNewLine = True
                            if jdx > indentationStack[-1]:
                                indentationStack.append(jdx)
                                tokens_list.append(("INDENT", idx + 1, jdx))
                                return tokens_list[-1], [], idx, jdx
                            elif jdx < indentationStack[-1]:
                                if jdx in indentationStack:
                                    if jdx < indentationStack[-1]:
                                        indentationStack.pop()
                                        tokens_list.append(("DEDENT", idx + 1, jdx))
                                        if (jdx < indentationStack[-1]):
                                            newLine = True
                                            tokenNewLine = False
                                        return tokens_list[-1], [], idx, jdx
                                else:
                                    return self.print_indentation_error(idx, jdx)

                    if not caso_especial:
                        if char != " " and char not in list(x for x in dict_operators_len1.keys()):

                            # Se revisa si es un comentario
                            if char == "#" and not flag_str:
                                j = 0
                                # i += 1
                                newLine = True
                                break
                            elif flag_str and jdx + 1 == len(line):  # Era un string que nunca cerró
                                return self.print_lexical_error(idx, jdx, char, token_buffer)

                            # Si entra es caracter raro
                            if ord(char) <= 31 or ord(char) >= 127:
                                caso_especial = True  # Este caso especial salta el siguiente caracter
                                # Verificamos que quedó en el buffer antes de romper
                                # if flag_str:
                                #    tokens_list.append(("tk_cadena", possible_substr, idx + 1, jdx + 1))
                                #    return tokens_list[-1], [], idx, jdx
                                if len(possible_substr) != 0:
                                    if possible_substr in global_keywords:
                                        # ver si lo que llevo en substring es palabra reservada
                                        tokens_list.append(
                                            (possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                                        possible_substr = ""
                                        return tokens_list[-1], [], idx, jdx
                                    elif len(list(num_re.finditer(possible_substr))) > 0 and len(
                                            list(id_re.finditer(possible_substr))) == 0:
                                        tokens_list.append(
                                            ("tk_numero", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                                        possible_substr = ""
                                        return tokens_list[-1], [], idx, jdx
                                    elif not possible_substr.__contains__("\""):
                                        tokens_list.append(("id", possible_substr, idx + 1, jdx + 1))
                                        return tokens_list[-1], [], idx, jdx

                                return self.print_lexical_error(idx, jdx, char, token_buffer)

                            # Si entra no es un string asi que hay validar operadores len 2
                            if not flag_str:
                                # Verificacion de operadores longitud 2
                                if char in list(x for x in dict_operators_len2.keys()):
                                    possible_substr, next = self.isTokenNumber(possible_substr, char, idx, jdx)
                                    if not next:
                                        return tokens_list[-1], [], idx, jdx
                                    possible_substr, next = self.isTokenId(possible_substr, idx, jdx)

                                    if not next:
                                        return tokens_list[-1], [], idx, jdx

                                    if jdx + 2 < len(line):
                                        if line[jdx + 1] == dict_operators_len2[char][1]:
                                            tokens_list.append(("tk_" + dict_operators_len2[char][3], idx + 1, jdx + 1))
                                            return tokens_list[-1], [], idx, jdx + 2
                                        else:
                                            if dict_operators_len2[char][2]:
                                                tokens_list.append(
                                                    ("tk_" + dict_operators_len2[char][4], idx + 1, jdx + 1))
                                                return tokens_list[-1], [], idx, jdx + 1
                                            else:
                                                return self.print_lexical_error(idx, jdx, char, token_buffer)
                                    else:
                                        if dict_operators_len2[char][2]:
                                            tokens_list.append(("tk_" + dict_operators_len2[char][4], idx + 1, jdx + 1))
                                            return tokens_list[-1], [], idx, jdx + 1
                                        else:
                                            return self.print_lexical_error(idx, jdx, char, token_buffer)
                                    # else:


                                # Darle trato especial a los strings
                                elif char == '"':
                                    possible_substr, next = self.isTokenNumber(possible_substr, char, idx, jdx)
                                    if not next:
                                        return tokens_list[-1], [], idx, jdx
                                    possible_substr, next = self.isTokenId(possible_substr, idx, jdx)
                                    if not next:
                                        return tokens_list[-1], [], idx, jdx
                                    possible_substr += char
                                    flag_str = True

                                elif (char == "\\"):
                                    return self.print_lexical_error(idx, jdx, char, token_buffer)
                                else:
                                    if (char == "\\"):
                                        if (jdx + 2 == len(line)):
                                            return self.print_lexical_error(idx, jdx, char, token_buffer)
                                        else:
                                            if (line[jdx + 1] != 'n' or line[jdx + 1] != 't' or line[jdx + 1] != '\\'):
                                                return self.print_lexical_error(idx, jdx, char, token_buffer)
                                            else:
                                                possible_substr += char
                                                print('tratar casos ')

                                    possible_substr, next = self.isTokenNumber(possible_substr, char, idx, jdx)

                                    if not next:
                                        return tokens_list[-1], [], idx, jdx

                                    if not char.isalnum() and char != "_":
                                        possible_substr, next = self.isTokenId(possible_substr, idx, jdx)
                                        if not next:
                                            return tokens_list[-1], [], idx, jdx
                                        return self.print_lexical_error(idx, jdx, char, token_buffer)

                                    possible_substr += char

                            else:
                                # Poner condición posible error con "\"
                                if (char == '"'):  # encontro comillas de cierre
                                    possible_substr = possible_substr + char
                                    tokens_list.append(
                                        ("tk_cadena", possible_substr, idx + 1, jdx + 2 - len(possible_substr)))
                                    possible_substr = ""
                                    flag_str = False
                                    return tokens_list[-1], [], idx, jdx + 1

                                else:
                                    if char == "\\" and jdx + 2 < len(line):
                                        if line[jdx + 1] == "\\":
                                            caso_especial = True
                                            possible_substr += '\\'
                                        elif line[jdx + 1] == "\"":
                                            caso_especial = True
                                            possible_substr += '"'
                                        elif line[jdx + 1] == "n":
                                            caso_especial = True
                                            possible_substr += "\n"
                                        elif line[jdx + 1] == "t":
                                            caso_especial = True
                                            possible_substr += "\t"
                                        else:
                                            return self.print_lexical_error(idx, jdx, char, token_buffer)
                                    else:
                                        possible_substr = possible_substr + char
                        else:
                            # " " o no es un operador de longitud 1
                            if flag_str:
                                if jdx + 1 == len(line):
                                    return self.print_lexical_error(idx, jdx, char, token_buffer)
                                elif char == " ":
                                    possible_substr += char
                                else:
                                    possible_substr += char
                            else:  # encontró un caracter diferente;
                                possible_substr, next = self.isTokenResWord(possible_substr, idx, jdx)
                                if not next:
                                    return tokens_list[-1], [], idx, jdx
                                possible_substr, next = self.isTokenId(possible_substr, idx, jdx)
                                if not next:
                                    return tokens_list[-1], [], idx, jdx
                                possible_substr, next = self.isTokenNumber(possible_substr, char, idx, jdx)
                                if not next:
                                    return tokens_list[-1], [], idx, jdx

                                next = self.isTokenSymbol(char, idx, jdx)
                                if not next:
                                    return tokens_list[-1], [], idx, jdx + 1

                                if len(list_lines[i]) == jdx + 1:
                                    j = 0
                                    i += 1
                                    possible_substr = ""
                    else:
                        caso_especial = False
                else:

                    return tokens_list[-1], [], idx, jdx

            newLine = True
        if 0 < indentationStack[-1]:
            indentationStack.pop()
            tokens_list.append(("DEDENT", len(list_lines) + 1, 1))
            return tokens_list[-1], [], len(list_lines) - 1, len(list_lines[-1])
        return [], [], -1, -1

    def escrituraToken(self, file, token):
        if len(token) == 4:
            file.write(
                "<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + "," + str(token[3]) + ">\n")
        elif len(token) == 3:
            file.write("<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + ">\n")
        else:
            if token:
                file.write(str(token))


    def getNextToken(self, i , j):
        #i = j = 0
        token_buffer = []
        #while i != -1 and j != -1:
        token, token_buffer, i, j = self.lexical_analysis(self.str_code, token_buffer, i, j)
        if len(tokens_list) > 0:
            tokens_list.pop()
        #file.close()
        return token, i, j


def main():
    lexer = Lexer("test.py")
    i = j = 0
    with open("output.txt", "w") as file:
        while i != -1 and j != -1:
            token, i, j = lexer.getNextToken(i, j)
            lexer.escrituraToken(file, token)
    print("Fin del análisis Léxico")


if __name__ == '__main__':
    main()