#[COMPLETED]

class Employee:
    def __init__(self, id, importance, subordinates):
        # It's the unique id of each node.
        # unique id of this employee
        self.id = id
        # the importance value of this employee
        self.importance = importance
        # the id of direct subordinates
        self.subordinates = subordinates



class Solution:
    def getImportance(self, employees, id):
        findInd = 0
        for i in range(0, len(employees)):
            if employees[i].id == id:
                findInd = i
                break
        importance = employees[findInd].importance
        for subid in employees[findInd].subordinates:
            importance = importance + self.getImportance(employees, subid)
        return importance
        """
        :type employees: Employee
        :type id: int
        :rtype: int
        """


s = Solution()
