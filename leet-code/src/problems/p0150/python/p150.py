#[COMPLETED]

class Solution:
    def evalRPN(self, tokens):
        """
        :type tokens: List[str]
        :rtype: int
        """
        ops = ["+", "-", "*", "/"]
        stack = []
        for token in tokens:
            print(str(stack) + " \t considering: " + str(token))
            if token not in ops:
                stack.append(int(token))
                continue
            elif token == "+":
                stack[-2] = stack[-2] + stack[-1]
            elif token == "-":
                stack[-2] = stack[-2] - stack[-1]
            elif token == "*":
                stack[-2] = stack[-2] * stack[-1]
            elif token == "/":
                resultNeg = (stack[-2] * stack[-1]) < 0
                stack[-2] = abs(stack[-2]) // abs(stack[-1])
                if resultNeg:
                    stack[-2] = -stack[-2]
            stack.pop()

        return stack[0]





l = ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
l2 = ["4","-2","/","2","-3","-","-"]
s = Solution()
print(s.evalRPN(l2))
