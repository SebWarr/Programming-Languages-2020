import re

global_keywords = ["False", "None", "True", "and", "as", "assert", "async", "await", "break", "class", "continue",
                   "object", "def", "del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import",
                   "in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return", "try", "while", "with",
                   "yield", "bool", "str", "int", "len", "print", "input", "__init__", "self"]

dict_conversor2 = {
    "//": "tk_division",
    "==": "tk_igual",
    ":": "tk_dos_puntos",
    "->": "tk_ejecuta",
    "!=": "tk_distinto",
    "<=": "tk_menor_igual",
    ">=": "tk_mayor_igual",
    "+": "tk_suma",
    "*": "tk_multiplicacion",
    "%": "tk_modulo",
    "(": "tk_par_izq",
    ")": "tk_par_der",
    "=": "tk_asig",
    ".": "tk_punto",
    ",": "tk_coma",
    "<": "tk_menor",
    ">": "tk_mayor",
    "[": "tk_corch_izq",
    "]": "tk_corch_der",
    "{": "tk_llave_izq",
    "}": "tk_llave_der",
}


class Token:

    def __init__(self, fila, columna, lexema, tipo):
        self.fila = fila
        self.columna = columna
        self.lexema = lexema
        self.tipo = tipo


def lexical_analysis(input_file):

    input_string = open(input_file, "r").read()
    global_keywords_appear = []
    str_re = re.compile('"[A-Z]{0,1}[a-z]*[0-9]*"')
    id_re = re.compile("[A-Z]{0,1}[a-z]+[0-9]*")
    for idx, line in enumerate(input_string.splitlines()):
        line += " "
        possible_substr = ""
        for jdx, char in enumerate(line):
            if char != " " and char not in list(x for x in dict_conversor2.keys()):
                possible_substr += char
            else:
                if possible_substr in global_keywords:
                    global_keywords_appear.append((possible_substr, idx+1, jdx+1 - len(possible_substr)))
                    possible_substr = ""
                elif len(list(str_re.finditer(possible_substr))) > 0:
                    global_keywords_appear.append(("tk_cadena", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                    possible_substr = ""
                elif len(list(id_re.finditer(possible_substr))) > 0:
                    global_keywords_appear.append(
                        ("id", possible_substr, idx + 1, jdx + 1 - len(possible_substr)))
                    possible_substr = ""
                else:
                    if char == "#":
                        break
                if possible_substr + char in list(x for x in dict_conversor2.keys()) and len(
                        possible_substr + char) == 2:
                    global_keywords_appear.append(
                        (dict_conversor2[possible_substr + char], idx + 1, jdx))
                    possible_substr = ""
                elif char in list(x for x in dict_conversor2.keys()):
                    global_keywords_appear.append(
                        (dict_conversor2[char], idx + 1, jdx + 1))
    tokens = global_keywords_appear
    return tokens

def main():
    list_tokens = lexical_analysis("test.py")
    with open("output.txt", "w") as file:
        for token in list_tokens:
            if len(token) > 3:
                file.write("<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + "," + str(token[3]) + ">\n")
            else:
                file.write("<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + ">\n")
        file.close()

if __name__ == '__main__':
    main()