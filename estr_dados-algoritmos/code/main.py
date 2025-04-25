from flask import (
    Flask,render_template,request
)
from typing import List

from database import load_db,Livro
from alg.search_alg import linear_search,binary_search

app = Flask(__name__)

acervo_principal: List[Livro] = load_db()

@app.route("/", methods=["GET","POST"])
def home():
    resultado = []

    if request.method == 'POST':
        termo = request.form["termo"]
        metodo = request.form["metodo"]
        campo = request.form.get("campo", "titulo") # valor padrão para o campo=titulo

        if metodo == "linear":
            resultado, iteracoes, tempo = linear_search(termo,acervo_principal,campo)
        
        elif metodo == "binaria":
            resultado, iteracoes, tempo = binary_search(termo,acervo_principal,campo)


    return render_template("index.html", resultado=resultado)


if __name__ == "__main__":
    app.run()

