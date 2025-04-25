package dados;

public class Livros {
    private String titulo;
    private String autor;
    private String [] generos = new String [2];
    private int paginas;
    private int copias;
    // construtor
    public Livros(String titulo,String autor, String genero1, String genero2, int paginas, int copias) {
        this.titulo = titulo;
        this.autor = autor;
        this.generos[0] = genero1;
        this.generos[1] = genero2;
        this.paginas = paginas;
        this.copias = copias;
    }

    // getters e setters
    public String getTitulo() {
        return this.titulo;
    }
    public String getAutor() {
        return this.autor;
    }
    public String getGeneros() {
        return (this.generos[0]+","+this.generos[1]);
    }
    // cocatenar array de Strings para facilitar exibição
    public int getPaginas() {
        return this.paginas;
    }
    public int getCopias() {
        return copias;
    }
    public void setCopias(int copias) {
        this.copias = copias;
    }
}
