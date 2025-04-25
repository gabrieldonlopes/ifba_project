package dados;
import java.util.*;

import usuarios.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Emprestimos {
    // toda vez que Emprestimos é instanciado é adicionado a lista
    private final static ArrayList<Emprestimos> emprestimosRealiazados = new ArrayList<>();
    private static int codigo = 0;
    private boolean devolvido;
    private final Livros livroEmprestado;
    private final int codigoDoEmprestimo;
    private final User usuario;
    private final int prazo = 5; // prazo de devolução dos livros
    // configuração da data:
    LocalDate dataAtual = LocalDate.now();
    LocalDate dataPrazo = dataAtual.plusDays(prazo);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String dataDoEmprestimo = dataAtual.format(formatter);
    private final String dataPrazoF = dataPrazo.format(formatter);
    public Emprestimos(Livros livroEmprestado, User usuario) {
        this.livroEmprestado = livroEmprestado;
        this.usuario = usuario;
        setCodigo(getCodigo()+1);
        this.codigoDoEmprestimo = codigo;
        setDevolvido(false);
        emprestimosRealiazados.add(this);
    }

    @Override
    public String toString() {
        String status;
        if (!getDevolvido()) status = "Em Posse";
        else status = "Devolvido";
        return "Emprestimo "+getCodigoDoEmprestimo()+"# \n"+
                "Livro: "+getLivroEmprestado().getTitulo()+"\n"+
                "Usuario: "+getUsuario().getNome()+"\n"+
                "Data: "+getDataDoEmprestimo()+"\n"+
                "Prazo: "+getDataPrazoF()+"\n"+
                "Status: "+status;
    }

    public static void exibirEmprestimosRealizados() {
        System.out.println("-------------------------------------------");
        System.out.println("Relatório de Empréstimos de Livros: ");
        System.out.println("Total: "+emprestimosRealiazados.size());
        for (Emprestimos emprestimo : emprestimosRealiazados) {
            System.out.println("-------------------------------------------\n");
            System.out.println(emprestimo);
        }
    }
    public static ArrayList<Emprestimos> getEmprestimosRealiazados (){
        return emprestimosRealiazados;
    }
    public static int getCodigo() {
        return codigo;
    }
    @SuppressWarnings("static-access")
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Livros getLivroEmprestado() {
        return livroEmprestado;
    }
    public User getUsuario() {
        return usuario;
    }

    public String getDataDoEmprestimo() {
        return dataDoEmprestimo;
    }
    public String getDataPrazoF() {
        return dataPrazoF;
    }

    public int getCodigoDoEmprestimo() {
        return codigoDoEmprestimo;
    }

    public boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}

