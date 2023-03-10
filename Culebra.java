import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Vector;

public class Culebra extends Thread {
  static double radio = 6;
  int ancho, alto;
  boolean tiene7Aros;
  double Xo, Yo;
  double Xf, Yf;
  double dx, dy;
  Thread animaCulebra;
  Vector vectorPuntos;
  Lienzo lienzo;

  Culebra(double X, double Y) {
    tiene7Aros = false;
    animaCulebra = new Thread();
    vectorPuntos = new Vector();
    Xo = X - radio;
    Yo = Y - radio;
  }

  public void calcularDestino() {
    double angulo = Math.toRadians(Math.random() * 360);
    Xf = Xo + (12 * radio) * (Math.cos(angulo));
    Yf = Yo + (12 * radio) * (Math.sin(angulo));
    dx = (Xf - Xo) / 6;
    dy = (Yf - Yo) / 6;
  }

  public void desplazar() {
    Xo += dx;
    Yo += dy;
  }

  public void run() {
    for (int i = 0; i < 15; i++) {
      calcularDestino();
      for (int j = 0; j < 7; j++) {
        try {
          vectorPuntos.add(new Point2D.Double(Xo, Yo));
          desplazar();
          if (tiene7Aros) {
            vectorPuntos.remove(0);
          }
          lienzo.repaint();
          animaCulebra.sleep(100);
        } catch (InterruptedException ie) {
        }
      }
      tiene7Aros = true;
    }
    // Despues de cambiar 15 veces de trayectoria
    // se desaparece poco a poco la culebra.
    for (int i = 0; i < 7; i++) {
      vectorPuntos.remove(0);
      try {
        lienzo.repaint();
        animaCulebra.sleep(100);
      } catch (InterruptedException ie) {
      }
    }
  }

  public void dibujar(Graphics g, double X, double Y) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setPaint(
        new GradientPaint(
            (float) X,
            (float) Y,
            Color.white,
            (float) (X + (radio * 3 / 2) * Math.cos(Math.PI / 4)),
            (float) (Y + (radio * 3 / 2) * Math.sin(Math.PI / 4)),
            Color.black));
    g2.fill(new Ellipse2D.Double(X, Y, radio * 2, radio * 2));
  }
}
