#!/usr/bin/python3

import os
from os import walk
from os.path import join
import shutil
import datetime

# Generates the README.md in the base directory of the project
# Steps
# 1.   Get text from AUTO_INCLUDE_README.md
# 2.   Get list of all problems completed from `problems_completed.txt`
# 3.   Get list of all problems in `problems` folder
#           Get total lines of code (per problem)
#           What languages written in (and lines of code)
# 4.   Calculate total of all for each language
# 5.   Overwrite README.txt
# 6.   Write auto include readme text
# 7.   Write separator
# 7.5. Write total number completed / total number problems, and percentage
# 8.   Write table for total number of lines per language, in descending order of total
# 9.   Make table of problems, complete / in/progress (if problem not in completed problems list)
# 10.  Print current date and time


# These are all the languages recognized:
# If writing in another language, add the extension for it here
code_exts_to_name = {"java": "Java", "py": "Python", "rs": "Rust"}
code_exts_to_folder_name = {"java": "java", "py": "python", "rs": "rust"}
code_exts = code_exts_to_name.keys()

# Update this number as new problems come out
total_num_problems_avail = 1719  # As of Jan 9, 2021

# State lines
# These are the lines that should be at the top of every code file
state_lines_map = {
    "//[IN PROGRESS]": "IN PROGRESS",
    "//[COMPLETED]": "COMPLETED",
    "//[SOURCE CODE NOT FOUND]": "NOT FOUND",
    "#[IN PROGRESS]": "IN PROGRESS",
    "#[COMPLETED]": "COMPLETED",
    "#[SOURCE CODE NOT FOUND]": "NOT FOUND"
}
state_lines = state_lines_map.keys()


def num_lines_of_code_in_file(path):
    # Calculates the number of lines of code in a file
    # Blank lines and lines starting with a comment are not counted
    # Does not support block comments
    try:
        f = open(path, 'r')
        if f is None:
            return 0
        lines = f.readlines()
        lines = [line.strip() for line in lines if line.strip()]
        comments = ("#", "//")
        return len([line for line in lines if not line.startswith(comments)])
    except FileNotFoundError:
        print("Couldn't open file:", path)
    return 0


def num_lines_in_file(path):
    # Get the number of lines in a file
    try:
        f = open(path, 'r')
        if f is not None:
            return len(f.readlines())
    except FileNotFoundError:
        print("Couldn't open file:", path)
    return 0


def process_problem_folder_states(path):
    # Walk this folder and read the first line of each code file, determine if problem finished or not
    # return dictionary of language -> status
    states = {}
    for r, d, files in walk(path):
        for file in files:
            ext = file.split('.')[-1]
            if ext not in code_exts:
                continue
            whole_file_path = join(r, file)
            try:
                file = open(whole_file_path, "r")
                line = file.readline().strip()
                if line not in state_lines:
                    print("FILE: ", whole_file_path, " DOES NOT HAVE A STATE LINE AS THE FIRST LINE, add one of these as the first line of the file: \n", list(set(state_lines_map.values())))
                else:
                    states[ext] = state_lines_map[line]
            except:
                print("error opening file or io error: ", whole_file_path)
    return states


def process_problem_folder(path):
    # Walk this folder and sum up all lines of code for each language
    # return dictionary of language -> lines of code
    loc = {}
    for r, d, files in walk(path):
        for file in files:
            ext = file.split('.')[-1]
            if ext not in code_exts:
                continue
            whole_file_path = join(r, file)
            num_lines = num_lines_of_code_in_file(whole_file_path)
            if ext not in loc:
                loc[ext] = num_lines
            else:
                loc[ext] += num_lines
    return loc


class PathsCollection:
    """ Assumed locations for root, problems directory based on where this script is located """
    def __init__(self):
        cwd = os.getcwd()
        self.root = os.sep.join(cwd.split(os.sep)[:-1])
        self.problems_folder = join(self.root, "src", "problems")
        self.readme          = join(self.root, "README.md")


paths = PathsCollection()

# 1. GET HEADER TEXT
auto_inc_text = "# A bunch of LeetCode problems\n## Use if you want, but you won't learn anything by copying.\n"
# print(auto_inc_text)

# 2. Get list of all problems completed from `problems_completed.txt`
# OLD WAY TO DO IT WITH files_complete.txt
# problems_completed_file = open(paths.problems_cmplet)
# problems_complete = problems_completed_file.readlines()
# # Take off newline with strip
# problems_complete = [int(line.strip()) for line in problems_complete if len(line.strip()) > 0]
# problems_complete = set(problems_complete)
# print(problems_complete)
# NEW WAY IS TO READ THE FIRST LINE OF EACH FILE


# 3. Get list of all problems in `problems` folder, and process files
problems_seen = set()
problem_totals = {}
problem_states = {}
for r, d, files in walk(paths.problems_folder):
    current_dir = r.split(os.sep)[-1]
    if current_dir not in problems_seen and current_dir[0] == 'p' and current_dir[1:].isdigit():
        problems_seen.add(current_dir)
        problem_num = int(current_dir[1:])
        problem_totals[problem_num] = process_problem_folder(r)
        problem_states[problem_num] = process_problem_folder_states(r)
        if len(files) != 0:
            # Try to move files into their own directory
            # Don't move if directory already exists
            for file in files:
                os.chdir(r)
                file_ext = file.split(".")[-1].strip()
                if file_ext in code_exts_to_folder_name.keys():
                    folder_name = code_exts_to_folder_name[file_ext]
                    if os.path.exists(folder_name):
                        print("DO SOMETHING WITH: ", file, " SINCE IT SHOULD BELONG IN A FOLDER")
                        continue
                    else:
                        # Make directory and move file into it
                        os.makedirs(folder_name)
                        shutil.move(file, folder_name)
                else:
                    print("YOU SHOULD MOVE ", files, " TO A DIRECTOR/IES FOR EACH LANGUAGE IN", r)
        # print(r, "\n\t", d, "\n\t", files, "\n\t", problem_totals[problem_num])

# 4. Calculate total of all for each language
total_total = {ext:0 for ext in code_exts}  # Dictionary with all extensions, 0 for value
for problem_total in problem_totals.values():
    for l,c in problem_total.items():
        total_total[l] += c

# 5. Overwrite README.md
readme_file = open(paths.readme, 'w')   # replaces old readme with blank file

# 6. Write auto include readme text
for l in auto_inc_text:
    readme_file.write(l)

# 7. Write separator
readme_file.write("\n\n")
readme_file.write("########################################################  \n")
readme_file.write("################ AUTO GENERATED SUMMARY ################  \n")
readme_file.write("########################################################  \n")
readme_file.write("\n\n")

# 7.5. Write total number completed / total number problems, and percentage
# num_problems_complete = len(problems_complete)
num_problems_complete = 0
for problem_num, states in problem_states.items():
    for ext, status in states.items():
        if status == "COMPLETED":
            num_problems_complete += 1
            break
percent_complete = num_problems_complete * 100.0 / total_num_problems_avail
readme_file.write("Number of problems completed (in at least one language): `" + str(num_problems_complete) + "`\n\n")
readme_file.write("Total problems available: `" + str(total_num_problems_avail) + "`\n\n")
readme_file.write("Percent complete: `" + ("{:.2f}".format(percent_complete)) + "%`\n\n")
readme_file.write("\n")

# 8. Write table for total number of lines per language
readme_file.write("| Language | Total LoC |\n")
readme_file.write("|   ---:   |  :---     |\n")
for l in sorted(total_total, reverse=True):
    readme_file.write("| " + code_exts_to_name[l] + " | " + "`" + str(total_total[l]) + "`" + " |\n")
readme_file.write("\n\n")

# 9. Make table of problems, complete / in/progress (if problem not in completed problems list)
# Table header
# readme_file.write("|   #  | Language |  LoC | Status |\n")
# readme_file.write("| ---: |   ---:   | :--- | :---:  |\n")
# for prob_num in sorted(problem_totals.keys()):
#     prob_totals = problem_totals[prob_num]
#     first_ext, first_total = prob_totals.popitem()
#     status = "Complete" if prob_num in problems_complete else "**In progress**"
#
#     readme_file.write(" | " + str(prob_num))
#     readme_file.write(" | " + code_exts_to_name[first_ext])
#     readme_file.write(" | " + "`" + str(first_total) + "`")
#     readme_file.write(" | " + status + " | " + "\n")
#
#     for ext, total in prob_totals.items():  # Rest of languages
#         readme_file.write(" | " + "^")
#         readme_file.write(" | " + code_exts_to_name[ext])
#         readme_file.write(" | " + "`" + str(total) + "`")
#         readme_file.write(" | " + ("^" if status == "Complete" else "**^**") + " | " + "\n")

# 9.5 Print new format for table
# Format for table: # Language1 Language2 ...
# Each cell will be a number for completed, number dash in progress, or blank
extension_column_order = sorted(code_exts)   # Sort the language order

readme_file.write("\n\n")
header_string = "| # | "
for ext in extension_column_order:
    header_string += code_exts_to_name[ext] + " | "
# readme_file.write("|   #  | Language |  LoC | Status |\n")
readme_file.write(header_string + "\n")
readme_file.write("| ---: | " + (" :---: | " * len(code_exts)) + "\n")
for prob_num in sorted(problem_totals.keys()):
    prob_totals = problem_totals[prob_num]
    readme_file.write(" | " + str(prob_num) + " | ")
    # Loop through all languages and see if they exist
    for ext in extension_column_order:
        if ext in prob_totals:
            # Check if problem is done, in progress
            try:
                if problem_states[prob_num][ext] == "COMPLETED":
                    readme_file.write("`" + str(prob_totals[ext]) + "`" + " | ")
                elif problem_states[prob_num][ext] == "IN PROGRESS":
                    readme_file.write("*IN PROGRESS*" + " | ")
                elif problem_states[prob_num][ext] == "NOT FOUND":
                    readme_file.write("NULL" + " | ")
                else:
                    readme_file.write("`?`" + " | ")
            except Exception as e:
                print("Problem ", prob_num, "is in problem totals but doesn't exist in problem states for ", ext)
                print(e)
        else:
            readme_file.write(" - | ")
    readme_file.write("\n")
    # print(prob_totals)

# 10. Print current date and time
readme_file.write("\n\n")
readme_file.write("A NULL value means the source code was lost, but the problem is still complete on leet code\n\n")

readme_file.write("Generated on: " + str(datetime.datetime.now()))
readme_file.write("\n")

readme_file.close()

print("Generated new readme to: ", paths.readme)
