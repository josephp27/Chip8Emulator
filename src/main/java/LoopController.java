import chip8.Chip8;
import graphics.Graphics;
import input.Input;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class LoopController {

    private Chip8 chip8;
    private Graphics graphics;
    private Input input;

    void run() throws Exception {

        graphics.setup();
        input.setup();

        chip8.initialize();
        chip8.loadGame("pong");

        while (input.isRunGame()) {
            chip8.emulateCycle();

            if (chip8.isDraw()) {
                graphics.draw();
            }

            input.pressKeys();
        }
    }
}
