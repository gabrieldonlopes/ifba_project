from time import time
from typing import List
from pydantic import BaseModel

from database import Livro

# Linear Search com tempo e iterações
def linear_search(key: str, arr: List[Livro], field: str): # type hint garante o tipo dos parâmetros
    start_time = time()
    iterations = 0
    encontrados = []

    for livro in arr:
        iterations += 1
        valor = getattr(livro, field, "").lower()  # Garantindo busca case-insensitive
        if key.lower() in valor:  # Busca parcial
            encontrados.append(livro)

    end_time = time()
    tempo_execucao = (end_time - start_time) * 1000  # Convertendo para milissegundos
    
    return encontrados, iterations, tempo_execucao

# Binary Search com tempo e iterações
def binary_search(key: str, arr: List[Livro], field: str): # type hint garante o tipo dos parâmetros
    key = key.lower()
    sorted_arr = sorted(arr, key=lambda livro: getattr(livro, field, "").lower())  # Ordenação antes da busca realizada  com quicksort nativo python
    
    start_time = time()
    iterations = 0
    
    
    start = 0
    end = len(sorted_arr) - 1
    encontrados = []

    while start <= end:
        iterations += 1
        mid = (start + end) // 2
        mid_valor = getattr(sorted_arr[mid], field, "").lower()

        if mid_valor == key:
            encontrados.append(sorted_arr[mid])
            break  # Retorna o primeiro encontrado (ou adicione busca de vizinhos)
        elif key < mid_valor:
            end = mid - 1
        else:
            start = mid + 1

    end_time = time()
    tempo_execucao = (end_time - start_time) * 1000  # Convertendo para milissegundos
    
    return encontrados, iterations, tempo_execucao
