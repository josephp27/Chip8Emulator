import chip8.Chip8;
import controller.LoopController;
import graphics.Graphics;
import input.Input;

public class Application {
    public static void main(String[] args) throws Exception {
        new LoopController(new Chip8(), new Graphics(), new Input()).run();
    }
}
