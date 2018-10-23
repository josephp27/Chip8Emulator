package input;

import lombok.Data;

@Data
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
