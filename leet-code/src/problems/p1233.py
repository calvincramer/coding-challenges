class Node:
    def __init__(self):
        self.children = set()


class Solution:
    def removeSubfolders(self, folder: list[str]) -> list[str]:
        root = Node()
        # Build tree
        for f in folder:
            parts = f.split("/")
