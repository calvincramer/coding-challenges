#[IN PROGRESS]

class Solution:
    def nextGreaterElement(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nextGreatest = []
        for i in range(0, len(nums2)):
            index = i+1
            while index < len(nums2):
                if nums2[index] > nums2[i]:
                    nextGreatest.append()
                index = index + 1


def binarySearch(arr, key, low = None, high = None):
    if low is None or high is None:
        return binarySearch(arr, key, 0, len(arr) - 1)
    if high < low or low > high:
        return False

    middle = int((high - low) / 2) + low

    if arr[middle] == key:
        return middle
    elif key > arr[middle]:
        return binarySearch(arr, key, middle + 1, high)
    else:
        return binarySearch(arr, key, low, middle - 1)

arr = [1,3,5,7,9,12,14,15,16,20,23]
for i in range(0, 25):
    print(str(i) + ": " + str(binarySearch(arr, i)))


