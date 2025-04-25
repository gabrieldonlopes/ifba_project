
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;

public class MiniEditorModel { //O Model é responsável pela manipulação dos dados e pela lógica
    private Document meuDocumento;

    public MiniEditorModel(Document documentoParam) {
        this.meuDocumento = documentoParam;
    }

    public Document getDocumento() {
        return meuDocumento;
    }

    public void carregarArquivo(File file) throws IOException, BadLocationException {
        RTFEditorKit rtfEditorKit = new RTFEditorKit();
        try (FileInputStream fis = new FileInputStream(file)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                System.out.print((char)byteData + " "); // Imprime os bytes como números
            }
            rtfEditorKit.read(fis, meuDocumento, 0);
        }
    }

    public void salvaArquivo(File file) throws IOException, BadLocationException {
        RTFEditorKit rtfEditorKit = new RTFEditorKit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            rtfEditorKit.write(fos, meuDocumento, 0, meuDocumento.getLength());
        }
    }
}
