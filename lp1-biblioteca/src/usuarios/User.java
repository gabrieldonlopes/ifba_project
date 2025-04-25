package usuarios;
import java.util.*;
import acessos.*;
import dados.*;

public abstract class User { // classe pai abstrata
    private String nome;
    private int senha;
    private final ArrayList <Livros> posse = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    // método construtor
    public User (String nome,int senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public ArrayList<Emprestimos> livrosEmPosse(){
        ArrayList<Emprestimos> emprestimosDoUsuario = new ArrayList<>();
        for (Emprestimos emprestimos : Emprestimos.getEmprestimosRealiazados()){
            if(emprestimos.getUsuario().getNome().equals(getNome())){
                emprestimosDoUsuario.add(emprestimos);
            }
        }
        int contador = 1;
        for (Livros livro : posse){
            for (Emprestimos emprestimos : emprestimosDoUsuario){
                if (livro.getTitulo().equals(emprestimos.getLivroEmprestado().getTitulo())){
                    System.out.println(contador+"-------------------------------------");
                    System.out.println(emprestimos);
                }
            }
        }
        return emprestimosDoUsuario;
    }
    public void devolverLivro(int indice, ArrayList<Emprestimos> emprestimosUsuario){
        posse.remove(indice);
        System.out.println("Livro devolvido com sucesso!");
        Emprestimos aSerDevolvido = emprestimosUsuario.get(indice);
        // atualizar tabela de emprestimos
        for (Emprestimos emprestimos : Emprestimos.getEmprestimosRealiazados()){
            if (emprestimos.equals(aSerDevolvido)){
                emprestimos.setDevolvido(true);
            }

        }
    }
    public void registrarLivro(Estante e) {
            System.out.print("Titulo: ");
            String titulo = sc.nextLine();
            System.out.print("Autor");
            String autor = sc.nextLine();
            System.out.print("Genero 1: ");
            String genero1 = sc.nextLine();
            System.out.print("Genero 2: ");
            String genero2 = sc.nextLine();
            System.out.print("Páginas: ");
            int paginas = sc.nextInt();
            System.out.println("Copias: ");
            int copias = sc.nextInt();
            e.adicionarLivro(titulo,autor,genero1,genero2,paginas,copias);
    }
    public void registrarLivro(ArrayList<Livros> estante){
        Scanner ler = new Scanner(System.in);
        System.out.print("Titulo: ");
        String titulo = ler.nextLine();
        System.out.print("Autor: ");
        String autor = ler.nextLine();
        System.out.print("Genero 1: ");
        String genero1 = ler.nextLine();
        System.out.print("Genero 2: ");
        String genero2 = ler.nextLine();
        System.out.print("Páginas: ");
        int paginas = ler.nextInt();
        System.out.print("Copias: ");
        int copias = ler.nextInt();
        Livros livro = new Livros(titulo,autor,genero1,genero2,paginas,copias);
        estante.add(livro);

    }
    public void doarLivros(ArrayList<Livros> estante){
        System.out.println("Página de Doação de Livros");
        while(true) {
            registrarLivro(estante);
            System.out.println("Quer adicionar mais Livros? [0] = sim, [1] = nao");
            int resposta = sc.nextInt();
            if (resposta == 1) break;
        }
    }
    // métodos
    public void mudarSenha() {
        System.out.println("Qual será sua nova senha?(apenas numeros)");
        int novaSenha = sc.nextInt();
        setSenha(novaSenha);
    }
    public void mudarNome() {
        System.out.println("Qual será sua nova nome?(apenas letras)");
        String novoNome = sc.nextLine();
        setNome(novoNome);
    }

    public void pedirLivro(Estante estante) {
        try {
            estante.pegarLivro(this);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public void exibirRegras(Regras regra) { // todos usuarios podem ver as regras
        regra.exibirRegras();
    }
    // getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    // métodos para lidar com os livros que estão em posse do usuário
    public int tamanhoPosse () {
        return posse.size();
    }
    public void adicionarLivro(Livros novoLivro) {
        posse.add(novoLivro);
    }
}
