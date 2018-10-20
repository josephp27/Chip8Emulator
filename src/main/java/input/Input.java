package input;

import lombok.Data;

@Data
public class Input {

    private boolean runGame = true;

    public void setup() {
        System.out.println("setup done");
    }

    public void pressKeys() {

    }
}
