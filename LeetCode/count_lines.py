from os import walk
import os
import operator


def process_file(file):
    path = os.path.join(r, file)
    # print(path)
    if file.count(".") == 0:  # Don't do binary files
        return
    file_ext = file.split(".")[-1].lower()
    if file_ext in dont_do_exts:
        return
    try:
        num_lines = len(open(path).readlines())
    except:
        print("Error on file: ", str(file))
        return
    if file_ext in exts:
        f[file_ext].append(file)
        counts[file_ext] += num_lines
    else:
        other_counts[file_ext] = num_lines if file_ext not in other_counts else other_counts[file_ext] + num_lines


# Extensions to count
exts = {"java", "py", "rs"}
# Binary files don't want to count
dont_do_exts = {"idx", "pack", "class", "lock", "jar", "exe", "pdb", "o", "bin"}
cwd = os.getcwd()
f = {n:[] for n in exts}
counts = {n:0 for n in exts}
other_counts = {}
for r, d, files in walk(cwd):
    for file in files:
        process_file(file)

# Sort results
counts = reversed(sorted(counts.items(), key=operator.itemgetter(1)))
other_counts = reversed(sorted(other_counts.items(), key=operator.itemgetter(1)))

for e,c in counts:
    print(".", e, " : ", c, sep="")

print("\nOthers: ")

# Sort print results
for e,c in other_counts:
    print("\t.", e, " : ", c, sep="")
