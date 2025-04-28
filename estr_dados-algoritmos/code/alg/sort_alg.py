from time import time
from typing import List
from database import Livro

# Bubble Sort adaptado para Livro e campo específico
def bubble_sort(arr: List[Livro], field: str):
    start_time = time()
    iterations = 0
    sorted_arr = arr.copy()

    for i in range(len(sorted_arr)-1, 0, -1):
        for j in range(i):
            val_j = getattr(sorted_arr[j], field, "").lower()
            val_j1 = getattr(sorted_arr[j+1], field, "").lower()
            if val_j > val_j1:
                sorted_arr[j], sorted_arr[j+1] = sorted_arr[j+1], sorted_arr[j]
            iterations += 1

    tempo_execucao = (time() - start_time) * 1000
    return sorted_arr, iterations, tempo_execucao

# Insertion Sort adaptado para Livro e campo específico
def insertion_sort(arr: List[Livro], field: str):
    start_time = time()
    iterations = 0
    sorted_arr = arr.copy()

    for i in range(1, len(sorted_arr)):
        current = sorted_arr[i]
        current_val = getattr(current, field, "").lower()
        j = i-1
        
        while j >= 0 and getattr(sorted_arr[j], field, "").lower() > current_val:
            sorted_arr[j+1] = sorted_arr[j]
            j -= 1
            iterations += 1
            
        sorted_arr[j+1] = current
        iterations += 1

    tempo_execucao = (time() - start_time) * 1000
    return sorted_arr, iterations, tempo_execucao

# Selection Sort adaptado para Livro e campo específico
def selection_sort(arr: List[Livro], field: str):
    start_time = time()
    iterations = 0
    sorted_arr = arr.copy()

    for i in range(len(sorted_arr)):
        min_idx = i
        for j in range(i+1, len(sorted_arr)):
            val_j = getattr(sorted_arr[j], field, "").lower()
            val_min = getattr(sorted_arr[min_idx], field, "").lower()
            if val_j < val_min:
                min_idx = j
            iterations += 1
        sorted_arr[i], sorted_arr[min_idx] = sorted_arr[min_idx], sorted_arr[i]

    tempo_execucao = (time() - start_time) * 1000
    return sorted_arr, iterations, tempo_execucao

def merge_sort(arr: List[Livro], field: str):
    def merge_sort_rec(sub_arr):
        if len(sub_arr) <= 1:
            return sub_arr, 0

        mid = len(sub_arr) // 2
        left, left_iter = merge_sort_rec(sub_arr[:mid])
        right, right_iter = merge_sort_rec(sub_arr[mid:])
        merged, merge_iter = merge(left, right)
        return merged, left_iter + right_iter + merge_iter

    def merge(left, right):
        merged = []
        i = j = 0
        iterations = 0
        
        while i < len(left) and j < len(right):
            left_val = getattr(left[i], field, "").lower()
            right_val = getattr(right[j], field, "").lower()
            
            if left_val < right_val:
                merged.append(left[i])
                i += 1
            else:
                merged.append(right[j])
                j += 1
            iterations += 1

        merged.extend(left[i:])
        merged.extend(right[j:])
        return merged, iterations

    start_time = time()
    sorted_arr, total_iterations = merge_sort_rec(arr.copy())
    tempo_execucao = (time() - start_time) * 1000
    
    return sorted_arr, total_iterations, tempo_execucao

def quick_sort(arr: List[Livro], field: str):
    def quick_sort_rec(sub_arr, start, end):
        if start >= end:
            return 0
            
        pivot_idx, iter_part = partition(sub_arr, start, end)
        iter_left = quick_sort_rec(sub_arr, start, pivot_idx - 1)
        iter_right = quick_sort_rec(sub_arr, pivot_idx + 1, end)
        return iter_part + iter_left + iter_right

    def partition(sub_arr, start, end):
        pivot_val = getattr(sub_arr[start], field, "").lower()
        left = start + 1
        right = end
        iterations = 0

        while True:
            while left <= right and getattr(sub_arr[left], field, "").lower() <= pivot_val:
                left += 1
                iterations += 1
            while left <= right and getattr(sub_arr[right], field, "").lower() >= pivot_val:
                right -= 1
                iterations += 1
            if left <= right:
                sub_arr[left], sub_arr[right] = sub_arr[right], sub_arr[left]
            else:
                break

        sub_arr[start], sub_arr[right] = sub_arr[right], sub_arr[start]
        return right, iterations

    start_time = time()
    sorted_arr = arr.copy()
    total_iterations = quick_sort_rec(sorted_arr, 0, len(sorted_arr) - 1)
    tempo_execucao = (time() - start_time) * 1000
    
    return sorted_arr, total_iterations, tempo_execucao