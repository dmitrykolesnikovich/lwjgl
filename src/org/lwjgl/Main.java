package org.lwjgl;

import org.lwjgl.opengl.AWTGLCanvas;

import javax.swing.*;
import java.awt.*;

public class Main {

  public static void main(String[] args) throws LWJGLException {
    LwjglNatives.register();
    AWTGLCanvas panel = new AWTGLCanvas();
    JFrame frame = new JFrame();
    frame.getContentPane().setPreferredSize(new Dimension(600, 600));
    frame.setTitle("Lwjgl");
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.requestFocus();
    frame.getContentPane().add(panel);
    frame.getContentPane().requestFocus();
    panel.requestFocus();
  }

}
