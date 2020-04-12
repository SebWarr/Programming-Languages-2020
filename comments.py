#     //////
# exist_comments = line.find('#')

#     if exist_comments != -1:
#         line = line[:-exist_comments]
#
#     for kw in global_keywords_s:
#         found = [m.start() for m in re.finditer(kw, line)]
#         for x in found:
#             global_keywords_appear.append((kw, idx+1, x+1))
#         if len(found) > 0:
#             line = line.replace(kw, " " * len(kw))
#
#     for symbol in dict_conversor.keys():
#         found = [m.start() for m in re.finditer(symbol, line)]
#         for x in found:
#             global_keywords_appear.append((dict_conversor[symbol], idx+1, x+1))
#         if len(found) > 0:
#             tam = len(symbol) - 1 if len(symbol) == 2 else len(symbol)-2
#             line = re.sub(symbol, " " * tam, line)
#
#     final_str += line + "\n"
#
# str_to_find_ids_and_tk_cadenas = final_str
# id_token = re.compile("[A-Z]{0,1}[a-z]+[0-9]*")
# str_token = re.compile('"[A-Z]{0,1}[a-z]*[0-9]*"')
#
# for idx, line in enumerate(str_to_find_ids_and_tk_cadenas.splitlines()):
#     tmp_lst = []
#     for s in str_token.finditer(line):
#         global_keywords_appear.append((line[s.span()[0]:s.span()[1]], idx+1, s.span()[0]+1))
#         tmp_lst.append((s.span()[0], s.span()[1]))
#
#
# for idx, line in enumerate(str_to_find_ids_and_tk_cadenas.splitlines()):
#     for s in id_token.finditer(line):
#         # print(s)
#         global_keywords_appear.append((line[s.span()[0]:s.span()[1]], idx + 1, s.span()[0] + 1, "id"))
# global_keywords_appear = sorted(global_keywords_appear, key=lambda x: (x[1], x[2]))


# # print(str_to_find_ids_and_tk_cadenas)
# return tokens
# tokens = []