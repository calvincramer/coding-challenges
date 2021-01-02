#[COMPLETED]

class Solution:
    def subdomainVisits(self, cpdomains):
        d = {}
        for domstr in cpdomains:
            count,dom = domstr.split(" ")
            count = int(count)
            subdoms = dom.split(".")
            curdom = subdoms[-1]
            i = len(subdoms) - 2
            while True:
                if curdom not in d:
                    d[curdom] = count
                else:
                    d[curdom] += count
                if i < 0:
                    break
                curdom = subdoms[i] + "." + curdom
                i -= 1
        # Assemble return string
        s_list = []
        for k,v in d.items():
            s_list.append(str(v) + " " + k)
        return s_list

sol = Solution()
print(sol.subdomainVisits(["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]),
      "expected [\"901 mail.com\",\"50 yahoo.com\",\"900 google.mail.com\",\"5 wiki.org\",\"5 org\",\"1 intel.mail.com\",\"951 com\"]")