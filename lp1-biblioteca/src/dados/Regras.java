package dados;

import java.text.SimpleDateFormat;
import java.util.*;

public class Regras {
    private int horarioAbertura;
    private int horarioFechamento;
    private ArrayList <String> regras = new ArrayList<>(); // array que guarda regra
    Scanner ler = new Scanner (System.in);
    Date data = new Date(); // obter acesso a data e hora
    Calendar calendario = Calendar.getInstance(); // obter acesso ao calendario
    // determinação da data atual para registrar regras
    SimpleDateFormat formatoData =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private String dataRegistro = formatoData.format(data);

    public Regras () { // regras padronizadas
        try {
            determinarHorario();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        regras.add("1 - Registre-se antes de utilizar os serviços.");
        regras.add("2 - Devolva os materiais dentro do prazo de 5 dias.");
        regras.add("3 - Mantenha o silêncio e o respeito pelo ambiente.");
        regras.add("4 - Cuide dos materiais emprestados.");
        regras.add("5 - Usuários podem manter até dois empréstimos ao mesmo tempo");
        regras.add("6 - Use dispositivos eletrônicos com discrição.");
        regras.add("7 - Consuma alimentos e bebidas apenas em áreas permitidas.");
        regras.add("8 - Siga as leis de direitos autorais ao utilizar materiais.");
        regras.add("9 - Cada pessoa pode ter dois livros em sua posse.");
        regras.add("10 - Colabore com os funcionários e outros usuários.");
    }

    void determinarHorario() {
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        if (diaSemana == Calendar.SATURDAY) {
            setHorarioAbertura(9);
            setHorarioFechamento(15);
        } else if (diaSemana == Calendar.SUNDAY) {
            throw new RuntimeException("Infelizmente a biblioteca não funciona no Domingo");
        } else {
            setHorarioAbertura(8);
            setHorarioFechamento(17);
        }
    }
    public void removerRegra() {
        exibirRegras();
        System.out.println("Exclua a regra (coloque o seu indice) ");
        int indiceDaRegra = ler.nextInt()-1;
        if (indiceDaRegra > 0 && indiceDaRegra <= regras.size()) {
            regras.remove(indiceDaRegra);
        } else throw new RuntimeException("Indice inserido incorretamente");
        // atualização da data de alteração
        Date novaData = new Date();
        setDataRegistro(formatoData.format(novaData));
    }
    public void adicionarRegra() {
        exibirRegras();
        System.out.println("Adicione a nova regra: ");
        String resposta = ler.nextLine();
        if (resposta == null) new RuntimeException("Nenhuma atualização realizada");
        regras.add( (regras.size()+1) + " - "+resposta);
        // atualização da data de alteração
        Date novaData = new Date ();
        setDataRegistro(formatoData.format(novaData));
    }

    public void exibirRegras() {
        System.out.println("Horário de Funcionamento: "+getHorarioAbertura()+"h as "+getHorarioFechamento()+"h");
        System.out.println("---------------------- Regras Atuais ----------------------");
        for (String regra : regras) {
            System.out.println(regra);
        }
        System.out.println("Ultima alteração: "+getDataRegistro()+"-------------------------");
    }

    public int getHorarioAbertura() {
        return horarioAbertura;
    }
    public void setHorarioAbertura(int horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }
    public int getHorarioFechamento() {
        return horarioFechamento;
    }
    public void setHorarioFechamento(int horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }
    public String getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

}