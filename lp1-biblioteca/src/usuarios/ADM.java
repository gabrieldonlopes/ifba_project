package usuarios;

import java.util.*;
import acessos.*;
import dados.Emprestimos;
import dados.Livros;
import dados.Regras;
public class ADM extends User{
    Date data = new Date ();
    Scanner ler = new Scanner(System.in);
    private final String senhaInstitucional = "adorocafe";

    public String getSenhaInstitucional() {
        return senhaInstitucional;
    }

    // método construtor
    public ADM(String nome, int senha,boolean novo) {
        super(nome, senha);

        System.out.println("Digite a senha institucional!");
        String senhaInserida = ler.nextLine();
        if (!senhaInserida.equals(senhaInstitucional)) throw new RuntimeException("Senha institucional Incorreta!");
    }
    public ADM(String nome, int senha) {
        super(nome, senha);
    }

    // métodos:
    public void alterarRegra(Regras regra) { // apenas o ADM pode adicionar novas regras
        String resposta;
            System.out.println("Você quer modificar as Regras? [y/n]");
            resposta = ler.nextLine();
            if (!resposta.equals("y")) throw new RuntimeException("Nenhuma mudança Realizada");
            System.out.println("Digite sua senha instituicional: ");
            resposta = ler.nextLine();
            if (!resposta.equals(getSenhaInstitucional())) throw new RuntimeException("Nenhuma mudança Realizada");
            System.out.println("Você quer adicionar ou remover regras? [ADD/REMOVE]");
            resposta = ler.nextLine();
            if (resposta.equals("ADD")) {
                regra.adicionarRegra();
            } else if (resposta.equals("REMOVE")) {
                regra.removerRegra();
            } else throw new RuntimeException("Nenhuma mudança Realizada");
    }
    @Override
    public void exibirRegras(Regras regra) {
        super.exibirRegras(regra);
        System.out.println("Você pode adicionar novas regras!");
}
    public void registrarLivro(Estante e,String senhaInserida) {
        if(!senhaInstitucional.equals(senhaInserida)) throw new RuntimeException("Senha institucional Errada");
        String resposta;
        System.out.println("Você quer adicionar um novo livro? [y/n]");
        resposta = ler.nextLine();
        if (!resposta.equals("y")) throw new RuntimeException("Nenhuma mudança Realizada");
        System.out.println("Você quer adicionar, remover ou adicionar novas copias algum livro? [ADD/REMOVE/NEW]");
        resposta = ler.nextLine();
        if (resposta.equals("ADD")) {
            System.out.print("Titulo: ");
            String titulo = ler.nextLine();
            System.out.print("Autor");
            String autor = ler.nextLine();
            System.out.print("Genero 1: ");
            String genero1 = ler.nextLine();
            System.out.print("Genero 2: ");
            String genero2 = ler.nextLine();
            System.out.print("Páginas: ");
            int paginas = ler.nextInt();
            System.out.println("Copias: ");
            int copias = ler.nextInt();
            e.adicionarLivro(titulo,autor,genero1,genero2,paginas,copias);
        } else if (resposta.equals("REMOVE")) {
            System.out.println("Qual livro você quer remover?(digite o indice)");
            e.exibirEstante();
            int livroRemovido = ler.nextInt();
            e.removerLivro(livroRemovido);
        } else throw new RuntimeException("Nenhuma mudança Realizada");
    }
    public void consultarEstantes(Estante estante) {
        estante.exibirEstante();
        System.out.println("-------------------------------------");
        System.out.println("Livros totais: ");
        System.out.println(estante.getLivrosTotais());
    }
    public void consultarEmprestimos() {
        Emprestimos.exibirEmprestimosRealizados();
    }
    public String consultarSenha(){
        System.out.println("Sua senha Institucional é: ");
        System.out.println(getSenhaInstitucional());
        System.out.println("Você não pode alterar essa senha");
        return senhaInstitucional;
    }
    public void adicionarDoacoes(ArrayList<Livros> estanteOriginal,ArrayList<Livros> caixaDoacoes){
        for (Livros livroDoados : caixaDoacoes){
            for (Livros livroOriginais : estanteOriginal){
                if (livroDoados.getTitulo()!=livroOriginais.getTitulo()){
                    estanteOriginal.add(livroDoados);
                }else{
                    livroOriginais.setCopias(livroOriginais.getCopias() + livroDoados.getCopias());
                }
            }
        }
    }
}


