# import inspect
# import time
# import math
#
#
# def print_function(f):
#     print("Function name: ", f.__name__)
#     try:
#         ret = inspect.getfullargspec(f)
#         print(ret)
#         #ret = inspect.signature()
#         #print(ret)
#     except:
#         print("No signature")
#
#
# def calculate_time(func):
#     def inner1(*args, **kwargs):
#         begin = time.time()
#         func(*args, **kwargs)
#         end = time.time()
#         print("Time:", func.__name__, end - begin, sep=" ")
#     return inner1
#
#
# @calculate_time
# def factorial(num):
#     print(math.factorial(num))
#
#
# # calling the function.
# #factorial(500)
#
# s = "abc"
# l = [1,2,3,4]
# my_set = set()
# my_obj = my_set
# object_methods = [method_name for method_name in dir(my_obj) if callable(getattr(my_obj, method_name))]
# for om in object_methods:
#     print(om)
#     #print("\t", inspect.getfullargspec())
#
# print("\n\n######################\n\n")
#
# for method in dir(my_obj):
#     if callable(getattr(my_obj, method)):
#         print(method)
#         try:
#             print("\t", inspect.signature(getattr(my_obj, method)))
#         except:
#             pass
#         print()
#
# print("\n\n######################\n\n")
#
#
#
#
# #print(l.index(4))
# #l.reverse()
# #print(l)
#
# #f = lambda x,y,z: x * y - z
# #print(f(5,2,3))
#
# # s = "abc"
# # for i in range(0, 10):
# #     print(str(i), " :", s.zfill(i), sep="")
#
#
# s = set()
# s.add(5)
# s.add(3)
# s.add(1)
# s.add(9)
# print("s=", s)
#
#
# d = set()
# d.add(5)
# d.add(3)
# d.add(2)
# print("d=", d)
#
# print()
#
# print("s-d = ", s-d)
# print("s^d = ", s^d)
# print("s|d = ", s|d)
# print("s&d = ", s&d)






# Make default files for the java files that were lost at some point, but still show up as completed on leet code
import os
import sys
#
# java_problems = [
#     43, 57, 59, 61, 67, 74, 75, 92, 94, 100, 102, 103, 108, 109,
#     121, 129, 136, 143, 153, 162, 171, 190, 202, 203, 204, 206,
#     217, 226, 228, 231, 234, 237, 242, 257, 258, 260, 268, 279,
#     283, 284, 287, 289, 299, 341, 342, 344, 349, 367, 385, 389,
#     404, 409, 412, 434, 441, 448, 459, 461, 463, 485, 498, 501,
#     504, 520, 535, 537, 551, 557, 599, 606, 617, 637, 645, 654,
#     657, 671, 674, 682, 728, 733, 744, 747, 788]
# java_folder_names = ["p" + (str(n) if len(str(n)) == 3 else '0' + str(n)) for n in java_problems]
# print(java_folder_names)
# print(os.getcwd())
# os.chdir("problems")
# print(os.getcwd())
# problems_directory = os.getcwd()
# for folder in java_folder_names:
#     print("Making: ", folder)
#     # cd into base directory
#     os.chdir(problems_directory)
#     # Make base problem directory
#     if not os.path.exists(folder):
#         try:
#             os.makedirs(folder)
#         except:
#             print("folder already exists")
#     # cd into directory
#     os.chdir(folder)
#     if os.getcwd() == folder:
#         print("Couldn't cd into new folder: ", folder)
#         continue
#     try:
#         os.makedirs("java")
#     except:
#         print("java folder already exists")
#     os.chdir("java")
#     if os.getcwd().split("\\")[-1] != "java":
#         print("Couldn't cd into java folder")
#         continue
#     # Make file
#     try:
#         print("\t" + str(os.getcwd()))
#         file = open(folder + ".java", "w+")
#         file.write("//[SOURCE CODE NOT FOUND]\n")
#         file.write("\n")
#         file.write("/*\n")
#         file.write("The source code was lost to the sands of time.\n")
#         file.write("*/\n")
#         print("write good")
#     except:
#         print("Couldn't make file")
#     print()
#
# print("done")



# Rename all folders to be four digit numbers instead of three digit
def rename_problem_folders():
    import os
    print("CWD: ", os.getcwd())
    os.chdir("problems")
    print("CWD: ", os.getcwd())
    # print(os.listdir())
    dirs = os.listdir()

    def rename(dir_name):
        if type(dir_name) is not str:
            return dir_name
        if len(dir_name) == 0:
            return dir_name
        if dir_name[0] != 'p':
            return dir_name
        left = 'p'
        right = dir_name[1:]
        # Add zeroes
        while len(right) < 4:
            right = '0' + right
        return left + right

    for dir in dirs:
        rename_to = rename(dir)
        print(dir, " -> ", rename_to)
        os.rename(dir, rename_to)


rename_problem_folders()













print("Playground finished")
