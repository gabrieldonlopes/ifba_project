from flask import (
    Flask,render_template,request
)
from typing import List

from database import load_db,Livro, MetodoRegistro
from alg.search_alg import linear_search,binary_search
from alg.sort_alg import (
    bubble_sort,insertion_sort,selection_sort,merge_sort,quick_sort,quick_sort_simple
)
app = Flask(__name__)

acervo_principal: List[Livro] = load_db()
registro_buscas: List[MetodoRegistro] = []
registro_sorts: List[MetodoRegistro] = []

@app.route("/", methods=["GET","POST"])
def home():
    resultado = []
    iteracoes = 0
    tempo = 0
    algoritmo = ""

    if request.method == 'POST':
        termo = request.form["termo"]
        algoritmo = request.form["algoritmo"]
        campo = request.form.get("campo", "titulo") # valor padrão para o campo=titulo

        if algoritmo == "linear":
            resultado, iteracoes, tempo = linear_search(termo,acervo_principal,campo)
        elif algoritmo == "binaria":
            resultado, iteracoes, tempo = binary_search(termo,acervo_principal,campo)
        
        registro_buscas.append(MetodoRegistro(algoritmo=algoritmo,iteracoes=iteracoes,tempo=tempo))

#        print(f"método{metodo}={iteracoes} e {tempo}ms")


    return render_template("index.html", livros=resultado,metodo=algoritmo, iteracoes=iteracoes, tempo=tempo)

@app.route("/livros", methods=["GET", "POST"])
def books():
    resultado = acervo_principal
    iteracoes = 0
    tempo = 0
    algoritmo = ""

    if request.method == 'POST':
        algoritmo = request.form.get("algoritmo", "bubble")
        campo = request.form.get("campo", "titulo")

        if algoritmo == "bubble":
            resultado, iteracoes, tempo = bubble_sort(acervo_principal, campo)
        elif algoritmo == "insertion":
            resultado, iteracoes, tempo = insertion_sort(acervo_principal, campo)
        elif algoritmo == "selection":
            resultado, iteracoes, tempo = selection_sort(acervo_principal, campo)
        elif algoritmo == "merge":
            resultado, iteracoes, tempo = merge_sort(acervo_principal, campo)
        elif algoritmo == "quick":
            resultado, iteracoes, tempo = quick_sort(acervo_principal, campo)
        elif algoritmo == "simple_quick":
            resultado, iteracoes, tempo = quick_sort_simple(acervo_principal, campo)
        
        registro_sorts.append(MetodoRegistro(algoritmo=algoritmo,iteracoes=iteracoes,tempo=tempo))
    

    return render_template("books.html", livros=resultado, algoritmo=algoritmo, iteracoes=iteracoes, tempo=tempo)

@app.route("/estatisticas")
def estatisticas_endpoint():
    buscas_json = [registro_buscas_obj.model_dump() for registro_buscas_obj in registro_buscas]
    sorts_json = [registro_sorts_obj.model_dump() for registro_sorts_obj in registro_sorts]

    return render_template(
        "estatisticas.html",
        registro_buscas=buscas_json,
        registro_sorts=sorts_json
    )


if __name__ == "__main__":
    app.run()

