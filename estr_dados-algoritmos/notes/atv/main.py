from pydantic import BaseModel
from typing import List

class Produtos(BaseModel):
    nome:str
    marca:str
    preco:float
    sku:int
    classificacao:float

catalogo: List[Produtos] = []

data = [
    {
        "nome": "B Camiseta Básica",
        "marca": "UrbanStyle",
        "preco": 49.90,
        "sku": 1001,
        "classificacao": 8.5
    },
        {
        "nome": "A Camisa Social Slim",
        "marca": "Executivo",
        "preco": 149.90,
        "sku": 1003,
        "classificacao": 7.8
    },
    {
        "nome": "C Calça Jeans Skinny",
        "marca": "DenimPro",
        "preco": 129.99,
        "sku": 1002,
        "classificacao": 9.0
    }
]

catalogo.extend([Produtos(**produto) for produto in data])

for i in catalogo:
    print(f"{i.nome} - {i.preco}")

# exemplo com ordenacao
def insertion_sort(unsorted_list:List[Produtos]):
    for index in range(1,len(unsorted_list)):
        search_index = index
        insert_value = unsorted_list[index]
        while search_index > 0 and unsorted_list[search_index-1].nome > insert_value.nome:
            unsorted_list[search_index] = unsorted_list[search_index-1]
            search_index -= 1
        unsorted_list[search_index] = insert_value

insertion_sort(catalogo)

print("--------------")
for i in catalogo:
    print(f"{i.nome} - {i.preco}")
