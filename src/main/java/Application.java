import chip8.Chip8;
import graphics.Graphics;
import input.Input;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Application {

    public static void main(String[] args) throws Exception {
        new LoopController(new Chip8(), new Graphics(), new Input()).run();
    }
}
