package acessos;

import dados.Livros;
import usuarios.Aluno;
import usuarios.User;

import java.util.ArrayList;
import java.util.Scanner;

public class CaixaDoacao  extends Estante {
    private final ArrayList<Livros> estanteParaVerificar = new ArrayList <> ();
    Scanner ler = new Scanner (System.in);
    private Aluno quemDoou;
    public CaixaDoacao(Aluno aluno){ // constrututor para verificar toda caixa de doacao
        this.quemDoou = aluno;
    }
    public CaixaDoacao(ArrayList<Livros> estanteParaVerificar){ // contrutor para verificar toda biblioteca
        for (Livros livro : estanteParaVerificar){
            this.estanteParaVerificar.add(livro);
        }
    }
    // Shallow Copy: ap
    public void verificarLivros(){
        if (estanteParaVerificar == null){
            System.out.println("A caixa de doacoes não possui livros!");
        } else {
            if(getQuemDoou()!= null){
                System.out.println("As doações foram realizadas por "+getQuemDoou().getNome());
            }
            exibirEstante(estanteParaVerificar);
            int resposta;
            boolean verificacao = false;
            while (true) {
                System.out.println("Todos os livro estão em bom estado?[0] = sim, [1] = não");
                resposta = ler.nextInt();
                if (resposta == 0) {
                    break;
                }
                exibirEstante();
                System.out.println("Qual livro esta com problemas? [indice]");
                resposta = ler.nextInt()-1;
                if(resposta > estanteParaVerificar.size()) throw new RuntimeException("Esse livro não existe");
                Livros livroDanificado = estanteParaVerificar.get(resposta);
                System.out.println("Quantas copias estão danificadas?");
                int copiasDanificadas = ler.nextInt();
                System.out.println("O problema pode ser resolvido?[0] = sim, [1] = não");
                resposta = ler.nextInt();
                if (resposta == 0) {
                    System.out.println("As copias foram removidas! Resolva os problemas e devolva-as a estanteParaVerificar");
                    livroDanificado.setCopias(livroDanificado.getCopias() - copiasDanificadas);

                } else {
                    System.out.println("As copias foram descartadas!");
                    livroDanificado.setCopias(livroDanificado.getCopias() - copiasDanificadas);
                }

            }
        }
    }

    public ArrayList<Livros> getEstanteParaVerificar() {
        return estanteParaVerificar;
    }

    public User getQuemDoou() {
        return quemDoou;
    }


}
