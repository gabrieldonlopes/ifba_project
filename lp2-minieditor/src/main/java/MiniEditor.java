import javax.swing.SwingUtilities; //Importa a classe SwingUtilities, do pacote javax.swing. Essa classe é responsável por gerenciar eventos da interface gráfica

/**
 *
 * @author Integrante 1
 * @author Integrante 2 
 */
public class MiniEditor {

    public static void main(String[] args) { //Método Principal. Aqui a aplicação java inicia
        SwingUtilities.invokeLater(new Runnable() { //Chama o método invokeLater da classe SwingUtilities
            @Override //Anotação de Sobrescrita de método herdado
            public void run() { //Sobrescrita do método run, que não recebe parâmetro e não retorna nada (void)
                MiniEditorView view = new MiniEditorView();
                MiniEditorModel model = new MiniEditorModel(view.getDocumentoFormatado());
                new MiniEditorController(model, view);
                view.setVisible(true);
            }
        });
    }
}
