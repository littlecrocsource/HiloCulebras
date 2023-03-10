import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;
import javax.swing.*;

public class Lienzo extends JComponent {
  Graphics gBuffer = null;
  Image imag;
  Vector vectorCulebras;
  int ancho, alto;

  Lienzo() {
    vectorCulebras = new Vector();
    setSize(new Dimension(1000, 700));
    ancho = getSize().width;
    alto = getSize().height;
    setBackground(new Color(150, 150, 255, 220));
  }

  public void paint(Graphics g) {
    if (gBuffer == null) {
      imag = createImage(ancho, alto);
      gBuffer = imag.getGraphics();
    }
    gBuffer.setColor(getBackground());
    gBuffer.fillRect(0, 0, ancho, alto);
    for (int j = 0; j < vectorCulebras.size(); j++) {
      Culebra culebra = (Culebra) vectorCulebras.get(j);
      if ((culebra.vectorPuntos).size() > 0) {
        for (int i = 0; i < (culebra.vectorPuntos).size(); i++) {
          Point2D punto = (Point2D) ((culebra.vectorPuntos).get(i));
          culebra.dibujar(gBuffer, punto.getX(), punto.getY());
        }
      } else {
        vectorCulebras.remove(j);
      }
    }
    g.drawImage(imag, 0, 0, null);
  }

  public void update(Graphics g) {
    paint(g);
  }
}
