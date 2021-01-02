#[COMPLETED]

class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows <= 1:
            return s
        points = []
        x, y = 0, 0
        new_s = ""
        descending = True
        for c in s:
            points.append( (x, y, c) )
            if descending:
                y += 1
                if y >= numRows - 1:
                    descending = False
            else:
                y -= 1
                x += 1
                if y <= 0:
                    descending = True
        y = 0
        while True:
            row = [el for el in points if el[1] == y]
            if len(row) == 0:
                break
            for el in row:
                new_s = new_s + el[2]
            y += 1
        return new_s



if __name__ == "__main__":
    sol = Solution()
    s = "PAYPALISHIRING"
    print("ABC", " -> ", sol.convert("ABC", 1), "\t expected ABC")
    print(s, " -> ", sol.convert(s, 3), "\t expected PAHNAPLSIIGYIR")
    print(s, " -> ", sol.convert(s, 4), "\t expected PINALSIGYAHRPI")
