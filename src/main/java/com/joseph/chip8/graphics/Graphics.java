package com.joseph.chip8.graphics;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Graphics {
    private char[] screen;

    public void setup() {
        screen = new char[64 * 32];
        System.out.println("setup done for com.joseph.chip8.graphics");
    }

    public void draw() {

    }

    public void clear() {
        for (int i = 0; i < screen.length; i++) {
            screen[i] = 0;
        }
    }
}
