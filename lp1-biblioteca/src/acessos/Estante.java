package acessos;
import java.util.*;

import dados.Emprestimos;
import dados.Livros;
import usuarios.*;

public class Estante {
    private int LivrosTotais;
    public final ArrayList <Livros> estante = new ArrayList <> ();

    Scanner ler = new Scanner (System.in);
    public Estante () {
        this.estante.add(new Livros("Cem anos de Solidão", "Gabriel Garcia Marquez", "Realismo Magico", "Romance", 368,2));
        this.estante.add(new Livros("1984", "George Orwel", "Ficção", "Politica", 328,4));
        this.estante.add(new Livros("Orgulho e Preconceito", "Jane Austen", "Romance", "Drama", 418,2));
        this.estante.add(new Livros("O Senhor dos Aneis", "J.R.R Tolkien", "Fantasia", "Drama", 416,3));
        this.estante.add(new Livros("Crime e Castigo", "Fiodor Dostoievski", "Psicologico", "Drama", 576,1));
        this.estante.add(new Livros("O Grande Gatsby", "F.Scott Fitz Gerald", "Romance", "Drama", 200,0));
        this.estante.add(new Livros("Dom quixote", "Miguel Cervantes", "Romance", "Sátira", 1072,3));
        this.estante.add(new Livros("A Revolução dos Bichos", "George Orwel", "Fabula", "Politica", 152,1));
        this.estante.add(new Livros("O Sol é para Todos", "Harper Lee", "Romance", "Drama", 376,2));
        this.estante.add(new Livros("O pequeno Principe", "Antoine de Saint-Exupery", "Infantil", "Filosofia", 96,1));
        calcularLivros();
    }
    public Estante(Estante estanteVerificar){
        for (Livros livro : estanteVerificar.getEstante()){
            this.estante.add(livro);
        }
    }

    public void exibirEstante() {
        int contador = 1;
        for (Livros livro : estante) {
            System.out.println(contador+"---------------------------------");
            System.out.println(exibirDados(livro));
            contador ++;
        }
    }
    public void exibirEstante(ArrayList<Livros> estante) {
        int contador = 1;
        for (Livros livro : estante) {
            System.out.println(contador+"---------------------------------");
            System.out.println(exibirDados(livro));
            contador ++;
        }
    }

    public void pegarLivro(User usuario) { // permite receber dados de qualquer usuario

        System.out.println("TESTE: "+usuario.getNome()+"#");

        // testagem de erros e entrada do usuario
        int resposta = 0;
        if (usuario.tamanhoPosse() >= 2) throw new RuntimeException("Você alcançou o número máximo de livros emprestados!");
        exibirEstante();
        try { // teste para prevenção de burrice
            System.out.println("Qual Livro você quer pedir Emprestado?[adicione o indíce]");
            resposta = ler.nextInt()-1;
            if (resposta > estante.size()) throw new RuntimeException("Indíce Indisponível");
            if (estante.get(resposta).getCopias() == 0) throw new RuntimeException("Esse livro não possui mais cópias");
            System.out.println("O livro "+estante.get(resposta).getTitulo()+" está disponível,"
                    + " para confirmar coloque sua senha: ");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage()+": Adicione um indíce válido, tente novamente");
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage()+": Adicione um *indíce*, tente novamente");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        confirmarEmprestimo(usuario,resposta,estante);
        exibirRecomendacoes(estante.get(resposta));
    }
    public void pegarLivro(User usuario,ArrayList<Livros> livrosSelecionados){ // sobrecarga de métodos - diferenciação por parâmetro

        System.out.println("Usuario Selecionado: "+usuario.getNome());

        // testagem de erros e entrada do usuario
        int resposta = 0;
        if (usuario.tamanhoPosse() >= 2) throw new RuntimeException("Você alcançou o número máximo de livros emprestados!");
        try { // teste para prevenção de burrice
            System.out.println("Qual Livro você quer pedir Emprestado?[adicione o indíce]");
            resposta = ler.nextInt()-1;
            if (resposta > livrosSelecionados.size()) throw new RuntimeException("Indíce Indisponível");
            if (livrosSelecionados.get(resposta).getCopias() == 0) throw new RuntimeException("Esse livro não possui mais cópias");
            System.out.println("O livro "+livrosSelecionados.get(resposta).getTitulo()+" está disponível,"
                    + " para confirmar coloque sua senha: ");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage()+": Adicione um indíce válido, tente novamente");
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage()+": Adicione um *indíce*, tente novamente");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        confirmarEmprestimo(usuario,resposta,livrosSelecionados);
        exibirRecomendacoes(livrosSelecionados.get(resposta));
    } // filtragem de livros

    public void confirmarEmprestimo(User usuario, int indiceLivro,ArrayList<Livros> estanteSelecionada){
        // confirmação de emprestimo (incompleto - adicionar teste de senha)
        int senhaConfirmacao;
        int tentativas = 0;

        while (tentativas<4) {
            senhaConfirmacao = ler.nextInt();
            if (senhaConfirmacao != usuario.getSenha()) {
                System.out.println("Senha incorreta, tente novamente: ");
                tentativas ++;
            } else {
                System.out.println("Empréstimo concluído!");
                usuario.adicionarLivro(estanteSelecionada.get(indiceLivro));
                estanteSelecionada.get(indiceLivro).setCopias(estanteSelecionada.get(indiceLivro).getCopias()-1);
                new Emprestimos(estanteSelecionada.get(indiceLivro),usuario);
                calcularLivros();
                break;
            }
        }
    }
    public void calcularLivros() {
        for (Livros livro : estante) {
            setLivrosTotais(getLivrosTotais()+livro.getCopias());
        }
    }
    public void calcularLivros(ArrayList <Livros> estanteFiltrada){
        for (Livros livro : estante){
            for (Livros livrosFiltrados : estanteFiltrada){
                if (livro.getTitulo().equals(livrosFiltrados.getTitulo())){
                    livro.setCopias(livrosFiltrados.getCopias());
                }
            }
        }
    }
    public void adicionarLivro(String nome, String autor,String genero1, String genero2, int paginas, int copias) {
        estante.add(new Livros(nome,autor,genero1,genero2,paginas,copias));
        calcularLivros();
    }
    public void removerLivro(int indice){
        try { // teste para prevenção de burrice
            if (indice > estante.size()) throw new RuntimeException("Indíce Indisponível");
            estante.remove(indice-1);
            System.out.println("O "+estante.get(indice-1).getTitulo()+"foi excluido com sucesso");
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage()+": Adicione um indíce válido, tente novamente");
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage()+": Adicione um *indíce*, tente novamente");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void pesquisaLivro(User usuario){
        // o método .contains() pesquisa se o titulo possui a string passada dentro do parâmetro
        // o método .toLowerCase() transforma todas Strings em minusculas (previnir erros do usuário)
        ArrayList<Livros> livrosFiltrados = new ArrayList<>(); // guarda os livros do mesmo titulo
        System.out.println("Qual o tipo de pesquisa? [1] autor / [2] titulo / [3] genero");
        int tipoPesquisa = ler.nextInt();
        ler.nextLine();
        System.out.println("Pesquise: ");
        String pesquisa = ler.nextLine();
        String oqueVouPesquisar;
        for (Livros livro : estante){
            if (tipoPesquisa == 1){
                oqueVouPesquisar = livro.getTitulo().toLowerCase();
            } else if (tipoPesquisa == 2) {
                oqueVouPesquisar = livro.getAutor().toLowerCase();
            } else if (tipoPesquisa == 3) {
                oqueVouPesquisar = livro.getGeneros().toLowerCase();
            } else throw new RuntimeException("Error! Reinicie o programa");
            if (oqueVouPesquisar.contains(pesquisa.toLowerCase())&&livro.getCopias()!=0) {
                livrosFiltrados.add(livro);
            }
            // o livro só é adicionado se corresponder ao titulo inserido e também tiver cópías disponíveis
        }
        if (!livrosFiltrados.isEmpty()) {
            int contador = 1;
            System.out.println("Livros encontrados: ");
            for (Livros livro : livrosFiltrados) {
                System.out.println(contador+"---------------------------------");
                System.out.println(exibirDados(livro));
                contador ++;
            }
            // recomendação
            try {
                String resposta;
                System.out.println("Você quer pedir algum desses livros?[y/n]");
                resposta = ler.next();
                if (!resposta.equals("y"))
                    throw new RuntimeException("Escreva uma Resposta válida"); // tratar esse erro
                pegarLivro(usuario, livrosFiltrados);
                calcularLivros(livrosFiltrados);
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Nenhum livro encontrado");
        }
    }
    public String exibirDados(Livros livro){
        return "Livro: " +livro.getTitulo() +"\n"
                + "Autor: "+livro.getAutor() +"\n"
                + "Páginas: "+ livro.getPaginas() +"\n"
                + "Gêneros: "+ livro.getGeneros() +"\n"
                + "Cópias Disponíveis:"+ livro.getCopias();
    }
    public void exibirRecomendacoes(Livros livroEscolhido){
        ArrayList<Livros> livrosRecomendados = new ArrayList<>();
        ArrayList<Livros> livrosRecomendadosF = new ArrayList<>();
        String []generosEscolhidos = livroEscolhido.getGeneros().split(",");
        for (Livros livro : estante){
            String []generosDisponiveis = livro.getGeneros().split(",");
            for (String genero : generosDisponiveis){
                    if (genero.equals(generosEscolhidos[0]) || (genero.equals(generosEscolhidos[1]))) livrosRecomendados.add(livro);
            }
        }
        for(Livros livro : livrosRecomendados) {
            if(!livrosRecomendadosF.contains(livro)&&livro.getCopias()>1&&!livro.getTitulo().equals(livroEscolhido.getTitulo())){
                livrosRecomendadosF.add(livro);
            }
        } // formatação da lista
        if (!livrosRecomendados.isEmpty()){
            for (Livros livro : livrosRecomendadosF) {
                System.out.println("-------- Livros Recomendados --------");
                System.out.println(exibirDados(livro));
                System.out.println("-------------------------------------");
            }
        }
    } // buscar outra abordagem para esse método
    public int getLivrosTotais() {
        return LivrosTotais;
    }

    public void setLivrosTotais(int livrosTotais) {
        LivrosTotais = livrosTotais;
    }

    public ArrayList<Livros> getEstante() {
        return estante;
    }

}
