import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameCulebras extends JFrame implements MouseListener {
  Lienzo lienzo;
  Culebra culebra;

  FrameCulebras() {
    super(".: CULEBRAS DESPLAZANDOSE :.");
    lienzo = new Lienzo();
    lienzo.addMouseListener(this);
    getContentPane().add(lienzo);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(lienzo.getSize());
  }

  public void mouseClicked(MouseEvent e) {
    culebra = new Culebra(e.getX(), e.getY());
    (lienzo.vectorCulebras).add(culebra);
    culebra.lienzo = lienzo;
    culebra.start();
  }
  // Funciones dee la interfaz mouseListener adicionales e implementables.
  public void mouseEntered(MouseEvent e) {}

  public void mouseExited(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}

  public static void main(String[] args) {
    FrameCulebras frame = new FrameCulebras();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
