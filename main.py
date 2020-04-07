import re

global_keywords = ["False", "None", "True", " and", " as", "assert ", "async ", "await ", "break", "class", "continue",
                   "object", "def", "del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import",
                   " in", " is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return", "try", "while", "with",
                   "yield", "bool", "str", "int", "len", "print", "input", "__init__", "self"]

dict_conversor = {
    "\/\/": "tk_division",
    "\=\=": "tk_igual",
    "\:": "tk_dos_puntos",
    "\-\>": "tk_ejecuta",
    "\!\=": "tk_distinto",
    "\<\=": "tk_menor_igual",
    "\>\=": "tk_mayor_igual",
    "\+": "tk_suma",
    "\*": "tk_multiplicacion",
    "\%": "tk_modulo",
    "\(": "tk_par_izq",
    "\)": "tk_par_der",
    "\=": "tk_asig",
    "\.": "tk_punto",
    "\,": "tk_coma",
    "\<": "tk_menor",
    "\>": "tk_mayor",
    "\[": "tk_corch_izq",
    "\]": "tk_corch_der",
    "\{": "tk_llave_izq",
    "\}": "tk_llave_der",
}

class Token():

    def __init__(self, fila, columna, lexema, tipo):
        self.fila = fila
        self.columna = columna
        self.lexema = lexema
        self.tipo = tipo



def lexical_analysis(input_file):

    input_string = open(input_file, "r").read()
    global_keywords_s = sorted(global_keywords, key=len, reverse=True)
    global_keywords_appear = []
    final_str = ""

    for idx, line in enumerate(input_string.splitlines()):

        exist_comments = line.find('#')

        if exist_comments != -1:
            line = line[:-exist_comments]

        for kw in global_keywords_s:
            found = [m.start() for m in re.finditer(kw, line)]
            for x in found:
                global_keywords_appear.append((kw, idx+1, x+1))
            if len(found) > 0:
                line = line.replace(kw, " " * len(kw))

        for symbol in dict_conversor.keys():
            found = [m.start() for m in re.finditer(symbol, line)]
            for x in found:
                global_keywords_appear.append((dict_conversor[symbol], idx+1, x+1))
            if len(found) > 0:
                tam = len(symbol) - 1 if len(symbol) == 2 else len(symbol)-2
                line = re.sub(symbol, " " * tam, line)

        final_str += line + "\n"

    str_to_find_ids_and_tk_cadenas = final_str
    global_keywords_appear = sorted(global_keywords_appear, key=lambda x: (x[1], x[2]))
    tokens = global_keywords_appear
    print(str_to_find_ids_and_tk_cadenas)
    return tokens

# Si empieza con Mayuscula el identificador, entonces es un tk_cadena
# Como manejar variables con palabras reservadas adentro, ejem: "str_pollo"
# Primero detecta <= y luego <, por lo tanto al encontrar < revisa si ya existe algo allí, de no ser el caso lo agrega
# Los tipos de datos solo pueden estar precedidos de : o (


def test():
    string = 'c.make_noise()   Prints "moo"'
    print(string.find('#'))
    string = string[:-string.find('#')]
    print(string)

def main():
    list_tokens = lexical_analysis("test.py")
    print(list_tokens)
    with open("output.txt", "w") as file:
        for token in list_tokens:
            file.write("<" + str(token[0]) + "," + str(token[1]) + "," + str(token[2]) + ">\n")
        file.close()
    test()


if __name__ == '__main__':
    main()