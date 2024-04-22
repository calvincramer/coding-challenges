#[COMPLETED]

class Solution:
    def removeComments(self, source):
        source = "\n".join(source)
        dest = ""
        i = 0
        while i < len(source):
            if i == len(source) - 1:
                dest += source[i]
                break
            if source[i:i+2] == "//":
                i += 2
                while i < len(source) and source[i] != "\n":
                    i += 1
                #i += 1
            elif source[i:i+2] == "/*":
                i += 2
                while i < len(source) - 1 and source[i:i+2] != "*/":
                    i += 1
                i += 2
            else:
                dest += source[i]
                i += 1

        return [line for line in dest.split("\n") if line != ""]




sol = Solution()
src = ["/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"]
ret = sol.removeComments(src)
for line in ret:
    print("'", line, "'", sep="")
