#[IN PROGRESS]

# Read from the file file.txt and print its transposed content to stdout.
# File always has same number of things per row?
# Oh this is a bash problem...
filename = "text.txt"
f = open(filename, "r")
if f.mode != "r":
    print("Could not open file to read from it, exiting")
    exit(-1)
data = []
max_in_row = 0
for line in f:
    spl = line.split(" ")
    data.append(spl)
    if len(spl) > max_in_row:
        max_in_row = len(spl)

# Take out new lines
for i in range(0, len(data)-1):
    data[i][-1] = data[i][-1][:-1]

# Clear file
f.close()
f = open(filename, "w")
f.truncate(0)

# Write file
for i in range(0, max_in_row):
    for l in range(0, len(data)):
        f.write(data[l][i] + " ")
    f.write("\n")

f.close()