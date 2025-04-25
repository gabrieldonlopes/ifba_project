import time

# Bubble Sort com contador de iterações e tempo
def bubble_sort(unordered_list):
    start_time = time.time()
    iterations = 0
    arr = unordered_list.copy()

    for i in range(len(arr) - 1, 0, -1):
        for j in range(i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
            iterations += 1

    end_time = time.time()
    return arr, iterations, end_time - start_time

# Insertion Sort com contador de iterações e tempo
def insertion_sort(unsorted_list):
    start_time = time.time()
    iterations = 0
    arr = unsorted_list.copy()

    for index in range(1, len(arr)):
        search_index = index
        insert_value = arr[index]
        while search_index > 0 and arr[search_index - 1] > insert_value:
            arr[search_index] = arr[search_index - 1]
            search_index -= 1
            iterations += 1
        arr[search_index] = insert_value
        iterations += 1

    end_time = time.time()
    return arr, iterations, end_time - start_time

# Selection Sort com contador de iterações e tempo
def selection_sort(unsorted_list):
    start_time = time.time()
    iterations = 0
    arr = unsorted_list.copy()
    
    for i in range(len(arr)):
        min_index = i
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min_index]:
                min_index = j
            iterations += 1
        arr[i], arr[min_index] = arr[min_index], arr[i]

    end_time = time.time()
    return arr, iterations, end_time - start_time

# Merge Sort com contador global
merge_iterations = 0

def merge_sort(unsorted_list):
    global merge_iterations
    merge_iterations = 0
    start_time = time.time()

    def merge_sort_rec(arr):
        global merge_iterations
        if len(arr) <= 1:
            return arr

        mid = len(arr) // 2
        left = merge_sort_rec(arr[:mid])
        right = merge_sort_rec(arr[mid:])

        return merge(left, right)

    def merge(left, right):
        global merge_iterations
        result = []
        i = j = 0
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                result.append(left[i])
                i += 1
            else:
                result.append(right[j])
                j += 1
            merge_iterations += 1
        result.extend(left[i:])
        result.extend(right[j:])
        return result

    sorted_list = merge_sort_rec(unsorted_list.copy())
    end_time = time.time()
    return sorted_list, merge_iterations, end_time - start_time

# Quick Sort com contador de iterações
quick_iterations = 0

def quick_sort(unsorted_list):
    global quick_iterations
    quick_iterations = 0
    start_time = time.time()

    def quick_sort_rec(arr, start, end):
        global quick_iterations
        if start < end:
            p = partition(arr, start, end)
            quick_sort_rec(arr, start, p - 1)
            quick_sort_rec(arr, p + 1, end)

    def partition(arr, start, end):
        global quick_iterations
        pivot = arr[start]
        left = start + 1
        right = end

        while True:
            while left <= right and arr[left] <= pivot:
                left += 1
                quick_iterations += 1
            while left <= right and arr[right] >= pivot:
                right -= 1
                quick_iterations += 1
            if left > right:
                break
            else:
                arr[left], arr[right] = arr[right], arr[left]

        arr[start], arr[right] = arr[right], arr[start]
        return right

    arr = unsorted_list.copy()
    quick_sort_rec(arr, 0, len(arr) - 1)
    end_time = time.time()
    return arr, quick_iterations, end_time - start_time
