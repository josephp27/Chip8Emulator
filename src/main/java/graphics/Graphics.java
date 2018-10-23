package graphics;

import lombok.Data;

@Data
public class Graphics {
    private char[] screen;

    public void setup() {
        screen = new char[64 * 32];
        System.out.println("setup done for graphics");
    }

    public void draw() {

    }
}
