#[COMPLETED]

class Solution(object):
    """
    :type list1: List[str]
    :type list2: List[str]
    :rtype: List[str]
    """
    def findRestaurant(self, list1, list2):
        h1 = {}
        h2 = {}
        for lst, d in [(list1, h1), (list2, h2)]:
            for i, rest in enumerate(lst):
                d[rest] = i
        min_sum_index = len(list1) + len(list2)
        min_sum_restaurants = []
        for rest, ind in h1.items():
            if rest in h2:
                if ind + h2[rest] == min_sum_index:
                    min_sum_restaurants.append(rest)
                elif ind + h2[rest] < min_sum_index:
                    min_sum_restaurants.clear()
                    min_sum_restaurants.append(rest)
                    min_sum_index = ind + h2[rest]
        return min_sum_restaurants


sol = Solution()
l1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]
l2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
print(sol.findRestaurant(l1, l2), "expected ['Shogun']")
