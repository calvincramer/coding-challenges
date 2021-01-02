#[IN PROGRESS]

import copy

# Definition for a Node.
class Node(object):
    def __init__(self, val, neighbors):
        self.val = val
        self.neighbors = neighbors


class Solution(object):
    """
    :type node: Node
    :rtype: Node
    """
    def cloneGraph(self, node):
        vis = {}            # Visited in original array map from orig node to copied node
        copy_head = Node(node.val, [])
        to_visit = [node, copy_head]   # Traversing the original array

        while len(to_visit) != 0:
            cur_orig, cur_copy = to_visit.pop()
            for neighbor in cur_orig.neighbors:
                copy_neighbor = Node(neighbor.val, [])
                if neighbor not in vis:
                    vis[neighbor] = copy_neighbor #?
                cur_copy.neighbors.append(copy_neighbor)
            vis[cur_orig] = cur_copy


        return copy_head


# Start with initial node
# Copy value, start with empty neighbors
# For each neighbor, copy them, but without copying their neighbors
# Only copy the neighbor if it hasn't been copied yet (keep track of copied)
# Once copied all neighbors, add to visited spot
# Add each new neighbor to toVisit list
# Repeat until toVisit is empty
