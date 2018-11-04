package com.joseph.chip8.input;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Input {

    private char[] keyPressed;

    private boolean runGame = true;

    public void setup() {
        keyPressed = new char[16];
        System.out.println("setup done");
    }

    public void pressKeys() {

    }

}
