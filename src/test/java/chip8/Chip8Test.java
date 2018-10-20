package chip8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Chip8Test {

    @InjectMocks
    private Chip8 chip8;

    @Test
    public void testLoad() throws Exception {
        chip8.initialize();
        chip8.loadGame("BLITZ");
    }
}