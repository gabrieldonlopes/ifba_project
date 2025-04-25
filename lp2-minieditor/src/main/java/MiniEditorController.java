
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

//Controlador é responsável por receber as ações e eventos da tela e interagir com a Model e a View
public class MiniEditorController  implements ActionListener {

    private MiniEditorModel model;
    private MiniEditorView view;


    public MiniEditorController(MiniEditorModel model, MiniEditorView view) {
        this.model = model;
        this.view = view;
        this.view.setOuvintesDeAcoes(this);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getItemAbrir()) {
            abreArquivo();
        } else if (e.getSource() == view.getItemSalvar()) {
            salvaArquivo();
        } else if (e.getSource() == view.getItemSair()) {
            System.exit(0);
        } else if (e.getSource() == view.getBotaoNegrito()) {
            aplicaNegrito();
        } else if (e.getSource() == view.getBotaoItalico()) {
            aplicaItalico();
        } else if (e.getSource() == view.getBotaoMaiscMinus()) {
            aplicaMaiuscula();
        } else if (e.getSource() == view.getBotaoAumentarFonte()){
            mudarTamanhoFonte(2);
        } else if (e.getSource() == view.getBotaoMudarFonte()){
            mudarFonte();
        } else if (e.getSource() == view.getBotaoDesenho()){
            adicionarPainelDesenho();
        }
    }

    private void abreArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos txt", "txt");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                model.carregarArquivo(file);
            } catch (IOException | BadLocationException ex) {
                JOptionPane.showMessageDialog(view, "Erro ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salvaArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos TXT", "txt");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                model.salvaArquivo(file);
            } catch (IOException | BadLocationException ex) {
                JOptionPane.showMessageDialog(view, "Erro ao salvar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void aplicaNegrito() {
        StyledDocument doc = view.getDocumentoFormatado();
        Style style = view.getAreaDoTexto().addStyle("Bold", null);
        StyleConstants.setBold(style, !StyleConstants.isBold(doc.getCharacterElement(view.getAreaDoTexto().getCaretPosition()).getAttributes()));
        doc.setCharacterAttributes(view.getAreaDoTexto().getSelectionStart(), view.getAreaDoTexto().getSelectionEnd() - view.getAreaDoTexto().getSelectionStart(), style, false);
    }

    private void aplicaItalico() {
        StyledDocument doc = view.getDocumentoFormatado();
        Style style = view.getAreaDoTexto().addStyle("Italic", null);
        StyleConstants.setItalic(style, !StyleConstants.isItalic(doc.getCharacterElement(view.getAreaDoTexto().getCaretPosition()).getAttributes()));
        doc.setCharacterAttributes(view.getAreaDoTexto().getSelectionStart(), view.getAreaDoTexto().getSelectionEnd() - view.getAreaDoTexto().getSelectionStart(), style, false);
    }

    private void mudarFonte(){
          String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
          String font = (String) JOptionPane.showInputDialog(view, "Escolha uma fonte:", "Mudar Fonte", JOptionPane.PLAIN_MESSAGE, null, fonts, fonts[0]);
          if (font != null) {
              MutableAttributeSet attr = new SimpleAttributeSet();
              StyleConstants.setFontFamily(attr, font);
              view.getAreaDoTexto().setCharacterAttributes(attr, false);
          }
    }


    private void mudarTamanhoFonte(int valorMudanca) {
        StyledDocument doc = view.getDocumentoFormatado();
        int start = view.getAreaDoTexto().getSelectionStart();
        int end = view.getAreaDoTexto().getSelectionEnd();

        if (start == end){
            Element element = doc.getCharacterElement(start);
            AttributeSet as = element.getAttributes();
            int tamanhoAtual = StyleConstants.getFontSize(as);
            int novoTamanho = Math.max(2, tamanhoAtual+valorMudanca);

            MutableAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontSize(attr,novoTamanho);
            view.getAreaDoTexto().setCharacterAttributes(attr,false);
        } else {
            for (int i = start;i < end;i++){
                Element element = doc.getCharacterElement(i);
                AttributeSet as = element.getAttributes();
                int tamanhoAtual2 = StyleConstants.getFontSize(as);
                int novoTamanho2 = Math.max(2, tamanhoAtual2 + valorMudanca); // Menor tamanho>

                MutableAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setFontSize(attr, novoTamanho2);
                doc.setCharacterAttributes(i, 1, attr, false);
            }
        }

    }
    
    private void aplicaMaiuscula() {
        JTextPane textPane = view.getTextPane();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        try {
            String selectedText = textPane.getDocument().getText(start, end - start);
            textPane.getDocument().remove(start, end - start);
            textPane.getDocument().insertString(start, selectedText.toUpperCase(), null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void adicionarPainelDesenho() {
        CanvaTest painelDesenho = new CanvaTest();

        // Obtém o painel principal do JFrame
        Container contentPane = view.getContentPane();

        // Adiciona o painel de desenho abaixo do texto
        contentPane.add(painelDesenho, BorderLayout.SOUTH);

        // Atualiza a interface
        view.revalidate();
        view.repaint();
    }
    
}
