from pydantic import BaseModel
from typing import List

class Livro(BaseModel):
    ISBN: str
    titulo: str
    autor: str
    paginas: int 
    ano: int


def load_db() -> List[Livro]:
    acervo_principal: List[Livro] = []

    with open("livros.txt", "r", encoding="utf-8") as file:
        for line in file:
            values = line.strip().split(";")
            livro = Livro(
                ISBN=values[0],
                titulo=values[1],
                autor=values[2],
                paginas=int(values[3]),
                ano=int(values[4])
            )
            acervo_principal.append(livro)
    
    print("""
    --------------------------------------------------
            acervo carregado com sucesso
    --------------------------------------------------
    """)
    return acervo_principal