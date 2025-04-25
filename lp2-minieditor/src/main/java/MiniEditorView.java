
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionListener;

//A View é responsável por desenhar a interface | GUI | tela para apresentação dos dados.
public class MiniEditorView extends JFrame{ //Herança da classe JFrame. JFrame traz a modelagem de uma janela com bordas e botôes de controle padrão (Fechar, Maximizar e Minimizar)
    
    private JTextPane areaDoTexto;
    private JScrollPane rolagem;
    private JMenuBar barraDoMenu;
    private JMenu menuArquivo,menuAjuda;
    private JMenuItem itemAbrir, itemSalvar, itemSair, itemSobre, itemAtualizar;
    private JToolBar barraDeFerramentas;
    private JButton botaoNegrito, botaoItalico,botaoMaiscMinus,botaoAumentarFonte,botaoMudarFonte,botaoDesenho;

    public MiniEditorView() { // Configurações da janela
                
        setTitle("Editor de Texto Simples");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Área de texto
        areaDoTexto = new JTextPane();
        rolagem = new JScrollPane(areaDoTexto);
        add(rolagem, BorderLayout.CENTER);

        // Menu
        barraDoMenu = new JMenuBar();
        menuArquivo = new JMenu("Arquivo");
        itemAbrir = new JMenuItem("Abrir");
        itemSalvar = new JMenuItem("Salvar");
        itemSair = new JMenuItem("Sair");

        menuAjuda = new JMenu("Ajuda");
        itemSobre = new JMenuItem("Sobre");
        itemAtualizar = new JMenuItem("Atualizar");

        menuArquivo.add(itemAbrir);
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);

        menuAjuda.add(itemSobre);
        menuAjuda.add(itemAtualizar);

        barraDoMenu.add(menuArquivo);
        barraDoMenu.add(menuAjuda);

        setJMenuBar(barraDoMenu);

        // Barra de ferramentas
        barraDeFerramentas = new JToolBar();
        botaoNegrito = new JButton("B");
        botaoItalico = new JButton("I");
        botaoMaiscMinus = new JButton("aA");
        botaoAumentarFonte = new JButton("A+");
        botaoMudarFonte = new JButton("Fonte");
        botaoDesenho = new JButton("Desenhar");

        botaoNegrito.setFont(new Font("Serif", Font.BOLD, 16));
        botaoItalico.setFont(new Font("Serif", Font.ITALIC, 16));


        barraDeFerramentas.add(botaoNegrito);
        barraDeFerramentas.add(botaoItalico);
        barraDeFerramentas.add(botaoMaiscMinus);
        barraDeFerramentas.add(botaoAumentarFonte);
        barraDeFerramentas.add(botaoMudarFonte);
        barraDeFerramentas.add(botaoDesenho);
        add(barraDeFerramentas, BorderLayout.NORTH);
    }
    
    public void setOuvintesDeAcoes(ActionListener listener) {
        itemAbrir.addActionListener(listener);
        itemSalvar.addActionListener(listener);
        itemSair.addActionListener(listener);
        botaoNegrito.addActionListener(listener);
        botaoItalico.addActionListener(listener);
        botaoMaiscMinus.addActionListener(listener);
        botaoAumentarFonte.addActionListener(listener);
        botaoMudarFonte.addActionListener(listener);
        botaoDesenho.addActionListener(listener);
    }

    public StyledDocument getDocumentoFormatado() {
        return areaDoTexto.getStyledDocument();
    }

    public JTextPane getAreaDoTexto() {
        return areaDoTexto;
    }

    public JMenuItem getItemAbrir() {
        return itemAbrir;
    }

    public JMenuItem getItemSalvar() {
        return itemSalvar;
    }

    public JMenuItem getItemSair() {
        return itemSair;
    }

    public JButton getBotaoNegrito() {
        return botaoNegrito;
    }

    public JButton getBotaoItalico() {
        return botaoItalico;
    }
    public JButton getBotaoMaiscMinus() {
        return botaoMaiscMinus;
    }
    public JButton getBotaoAumentarFonte() {return botaoAumentarFonte;}
    public JButton getBotaoMudarFonte(){return botaoMudarFonte;}
    public JButton getBotaoDesenho() {return  botaoDesenho;}

    public JTextPane getTextPane(){
        return areaDoTexto;
    }

}
