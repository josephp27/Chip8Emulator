package com.joseph.chip8.graphics;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Data
@Component
public class Chip8Graphics extends JFrame {
    private char[] screen;
    private DrawPane drawPane = new DrawPane();

    public Chip8Graphics() throws HeadlessException {
        super("Chip8Graphics Frame");

        setContentPane(drawPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

    }

    class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < screen.length; i++) {
                int x = (int) Math.floor(i / 32);
                int y = i % 32;
                if (screen[i] == 1)
                    g.drawOval(x, y, x, y);
            }
        }
    }

    public void setup() {
        screen = new char[64 * 32];
        System.out.println("setup done for com.joseph.chip8.graphics");

        this.setPreferredSize(new Dimension(300, 300));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
