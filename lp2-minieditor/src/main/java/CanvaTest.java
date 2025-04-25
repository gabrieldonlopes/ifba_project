import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CanvaTest extends JPanel {
    private Image imagem;
    private Graphics2D g2d;

    public CanvaTest() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 300));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                desenharPonto(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                desenharPonto(e.getX(), e.getY());
            }
        });
    }

    private void desenharPonto(int x, int y) {
        if (g2d == null) {
            criarImagem();
        }
        g2d.fillOval(x, y, 5, 5);
        repaint();
    }

    private void criarImagem() {
        imagem = createImage(getWidth(), getHeight());
        g2d = (Graphics2D) imagem.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagem == null) {
            criarImagem();
        }
        g.drawImage(imagem, 0, 0, null);
    }
}