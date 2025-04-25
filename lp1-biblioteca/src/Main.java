
import dados.Regras;
import acessos.*;
import usuarios.ADM;
import usuarios.Aluno;
import usuarios.User;

public class Main {
    public static void main(String[] args) {
        /*
            -----------------------
            nome: Gabriel (ADM)
            senha: 123
            -----------------------
            nome: Jamilly
            senha: 321
            --------------------
            ,0---
            nome: Liz
            senha: 213
         */

        Regras regras = new Regras();
        Estante estante = new Estante();
        Interface menu = new Interface();

       // User a = new Aluno("Ariel",321);


        try {
            menu.menuInicial(estante,regras);
        } catch (Exception e){
            System.err.println(e.getMessage());
            menu.menuInicial(estante,regras);
        }
        // nota: ainda podem existir alguns bugs
    }}
