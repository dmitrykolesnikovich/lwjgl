package org.lwjgl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputListener extends MouseAdapter implements KeyListener {

  private double x = -1;
  private double y = -1;

  @Override
  public final void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
  }

  @Override
  public final void mousePressed(MouseEvent e) {
    x = e.getX();
    y = e.getY();
    down(e, x, y);
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    if (!isReset()) {
      up(e, e.getX(), e.getY());
      reset();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (x != -1 && y != -1) {
      if (e.getX() >= 0 && e.getY() >= 0) {
        x = e.getX();
        y = e.getY();
        move(e, x, y);
      } else {
        up(e, x, y);
        reset();
      }
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  private void reset() {
    x = -1;
    y = -1;
  }

  private boolean isReset() {
    return x == -1 && y == -1;
  }

  public void down(MouseEvent e, double x, double y) {
  }

  public void move(MouseEvent e, double x, double y) {
  }

  public void up(MouseEvent e, double x, double y) {
  }
}
