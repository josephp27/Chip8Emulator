package com.joseph.chip8.graphics;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

@Data
@Component
@EqualsAndHashCode(callSuper = true)
public class Chip8Graphics extends JFrame {

    private char[] screen;
    public AffineTransform at;
    public final double ZOOM_FACTOR = 10;
    private DrawPane drawPane;

    public Chip8Graphics() throws HeadlessException {
        super("Chip8Graphics Frame");

        drawPane = new DrawPane();
        setContentPane(drawPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 340);
        setVisible(true);
    }

    class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            at = g2.getTransform();
            at.scale(ZOOM_FACTOR, ZOOM_FACTOR);

            g2.transform(at);

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, 640, 340);

            for (int y = 0; y < 32; ++y) {
                for (int x = 0; x < 64; ++x) {
                    if (screen[(y * 64) + x] == 1) {
                        g2.setColor(Color.WHITE);
                        g2.drawRect(x, y, 0, 0);
                    }
                }
            }
        }
    }

    public void setup() {
        screen = new char[64 * 32];
        System.out.println("setup done for graphics");
    }

    public void draw() {
        drawPane.repaint();
    }

    public void clear() {
        for (int i = 0; i < screen.length; i++) {
            screen[i] = 0;
        }
    }
}

