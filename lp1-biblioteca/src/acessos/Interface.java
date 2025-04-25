package acessos;
import dados.Emprestimos;
import dados.Regras;
import usuarios.*;
import java.util.*;

public class Interface {
    private CaixaDoacao caixaDoacao;
    private boolean funcionando = true;
    private final Scanner ler = new Scanner(System.in);
    private final ArrayList <User> usuarios = new ArrayList<>();
    public Interface(){

        // usuarios padrão:
        usuarios.add(new ADM("Gabriel",123));
        usuarios.add(new Aluno("Jamilly",321));
        usuarios.add(new Aluno("Liz",213));

    }
    public void menuInicial(Estante estante,Regras regras){
        System.out.println("""
                                                   
                ─▄▀─▄▀
                ──▀──▀
                █▀▀▀▀▀█▄        Seja Bem-vindo a Biblioteca!
                █░░░░░█─█       Escolha as opções disponíveis:
                ▀▄▄▄▄▄▀▀
                """);
        System.out.println(" [1] login / [2] registrar");
        int opcao = ler.nextInt();
        if (opcao ==1){
            telaLogin(estante,regras);
        } else if (opcao == 2) {
            registrar();
            menuInicial(estante,regras);
        }
    }
    public void exibirOpcoes(Aluno aluno, Estante estanteUtilizada, Regras regrasUtilizadas){
        int resposta;
        while (funcionando) {
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        MENU PRINCIPAL - Aluno                                                                                                               ║");
            System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║  [0] Sair / [1] Pegar Livro / [2] Devolver Livro / [3] Livro em Posse / [4] Exibir Regras / [5] Configurar Perfil / [6] Pesquisar Livro     ║");
            System.out.println("║  [7] Voltar ao Menu / [8] Doar Livro                                                                                                                       ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            resposta = ler .nextInt();
            switch (resposta) {
                case 0:
                    setFuncionando(false);
                    break;
                case 1:
                    aluno.pedirLivro(estanteUtilizada);
                    break;
                case 2:
                    if(!aluno.livrosEmPosse().isEmpty()) {
                        ArrayList<Emprestimos> emprestimosDoUsuario = aluno.livrosEmPosse();
                        System.out.println("Qual Livro você quer devolver? [indice]");
                        int indice = ler.nextInt() - 1;
                        aluno.devolverLivro(indice, emprestimosDoUsuario);
                        break;
                    } else {
                        System.out.println("Você não tem livros em posse!");
                        menuInicial(estanteUtilizada,regrasUtilizadas);
                    }
                case 3:
                    aluno.livrosEmPosse();
                case 4:
                    aluno.exibirRegras(regrasUtilizadas);
                    break;
                case 5:
                    System.out.println("Opções: [1] Mudar nome / [2] Mudar senha:");
                    int opcao = ler.nextInt();
                    if (opcao == 1) {
                        aluno.mudarNome();
                        menuInicial(estanteUtilizada, regrasUtilizadas);
                    } else if (opcao == 2) {
                        menuInicial(estanteUtilizada,regrasUtilizadas);
                        aluno.mudarSenha();
                    } else {
                        throw new RuntimeException("Escolha indisponível");
                    }
                    break;
                case 6:
                    estanteUtilizada.pesquisaLivro(aluno);
                    break;
                default:
                    throw new RuntimeException("Indice Incorreto");
                case 7:
                    menuInicial(estanteUtilizada,regrasUtilizadas);
                case 8:
                    caixaDoacao = new CaixaDoacao(aluno);

                    aluno.doarLivros(caixaDoacao.getEstanteParaVerificar());
                    caixaDoacao.getEstante().clear();
            }
        }
    }
    public void exibirOpcoes(ADM administrador,Estante estanteUtilizada,Regras regrasUtilizadas){
        int resposta;
        while (funcionando) {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        MENU PRINCIPAL - ADM                                                                                              ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
            System.out.println("║ [0] Sair / [1] Pegar Livro / [2] Devolver Livro / [3] Livro em Posse / [4] Exibir Regras / [5] Configurar Perfil         ║");
            System.out.println("║ [6] Pesquisar Livro / [7] Voltar ao Menu / [8] Alterar Regras / [9] Registrar Livro / [10] Consultar Emprestimos         ║");
            System.out.println("║ [11] Consultar Estantes / [12] Senha institucional / [13] Verificar Livros  / [14] Verificar Caixa Doacao                                                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            resposta = ler .nextInt();
            switch (resposta) {
                case 0:
                    setFuncionando(false);
                    break;
                case 1:
                    administrador.pedirLivro(estanteUtilizada);
                    break;
                case 2:
                    if(!administrador.livrosEmPosse().isEmpty()) {
                        ArrayList<Emprestimos> emprestimosDoUsuario = administrador.livrosEmPosse();
                        System.out.println("Qual Livro você quer devolver? [indice]");
                        int indice = ler.nextInt() - 1;
                        administrador.devolverLivro(indice, emprestimosDoUsuario);
                        break;
                    } else {
                        System.out.println("Você não tem livros em posse!");
                        menuInicial(estanteUtilizada,regrasUtilizadas);
                    }
                case 3:
                    administrador.livrosEmPosse();
                    break;
                case 4:
                    administrador.exibirRegras(regrasUtilizadas);
                    break;
                case 5:
                    System.out.println("Opções: [1] Mudar nome / [2] Mudar senha:");
                    int opcao = ler.nextInt();
                    if (opcao == 1) {
                        administrador.mudarNome();
                        menuInicial(estanteUtilizada, regrasUtilizadas);
                    } else if (opcao == 2) {
                        administrador.mudarSenha();
                        menuInicial(estanteUtilizada,regrasUtilizadas);
                    } else {
                        throw new RuntimeException("Escolha indisponível");
                    }
                    break;
                case 6:
                    estanteUtilizada.pesquisaLivro(administrador);
                    break;
                case 7:
                    menuInicial(estanteUtilizada,regrasUtilizadas);
                case 8:
                    administrador.alterarRegra(regrasUtilizadas);
                    break;
                case 9:
                    System.out.println("adicione sua senha institucional: ");
                    String senhaUtilizada = ler.nextLine();
                    administrador.registrarLivro(estanteUtilizada, senhaUtilizada);
                    break;
                case 10:
                    administrador.consultarEmprestimos();
                    break;
                case 11:
                    administrador.consultarEstantes(estanteUtilizada);
                    break;
                case 12:
                    administrador.consultarSenha();
                    break;
                case 13:
                    CaixaDoacao verificarLivros = new CaixaDoacao(estanteUtilizada.getEstante());
                    verificarLivros.verificarLivros();
                case 14:
                    if (caixaDoacao == null) throw new RuntimeException("Caixa de Doação Vazia");
                    caixaDoacao.verificarLivros();
                    administrador.adicionarDoacoes(estanteUtilizada.getEstante(),caixaDoacao.getEstante());
                    caixaDoacao.getEstante().clear();
                    default:
                    throw new RuntimeException("Indice Incorreto");
            }
        }
        }

    public void registrar(){
        String senhaInstitucional;
        int resposta;
        System.out.println("Você quer se registrar como Administrador ou Aluno? [adm=0/aluno=1]");
        resposta = ler.nextInt();
        if (resposta == 0){ // melhorar segurança desse processo
            System.out.println("Digite a senha institucional:");
            senhaInstitucional = ler.next();
            if (!senhaInstitucional.equals("adorocafe"))throw new RuntimeException("Erro. Por favor reinicie o sistema");
            telaRegistro(0);
        } else if (resposta == 1){
            telaRegistro(1);
        } else {
            throw new RuntimeException("Error! Reinicie o sistema");
        }
    }

    public void telaLogin(Estante estante,Regras regras){
        int contador = 1;
        for(User todosUsuarios : usuarios){
            System.out.println(contador+"-------------------------------------");
            System.out.println(todosUsuarios.getNome());
            contador ++;
        }
        System.out.println("Selecione o usuario [indice]");
        int indiceSelecionado = ler.nextInt();
        if (usuarios.size()<indiceSelecionado) throw new RuntimeException("Erro! Por favor tente Novamente!");
        User usuario = usuarios.get(indiceSelecionado-1);
        System.out.println("Digite sua Senha!");
        System.out.print("Senha: ");
        int senha = ler.nextInt();
        if (usuario.getSenha() == senha){
            if (usuario instanceof ADM) exibirOpcoes((ADM) usuario,estante,regras);
            if (usuario instanceof Aluno) exibirOpcoes((Aluno) usuario,estante,regras);
        } else {
            throw new RuntimeException("Senha incorreta");
        }
    }
    public void telaRegistro(int tipoConta){
        System.out.println("-------------------------------------");
        System.out.print("Nome: ");
        String nome = ler.next();
        System.out.print("Senha: ");
        int senha = ler.nextInt();
        System.out.println("-------------------------------------");
        if (!verificacaoRegistro(nome)){
            System.out.println("Esse usuário já existe!");
            telaRegistro(tipoConta);
        }
        if (tipoConta == 0) {
            boolean verificar = true;
            User usuarioRegistro = new ADM(nome, senha,verificar);
            usuarios.add(usuarioRegistro);
        } else {
            usuarios.add(new Aluno(nome, senha));
        }
    }
    public boolean verificacaoRegistro(String nome){
        for (User usuariosParaVerificar : usuarios){
            if (nome.equals(usuariosParaVerificar.getNome())){
                return false;
            }
        }
        return true;
    }
    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }

}
