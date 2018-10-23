package engine;

import chip8.Chip8;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OpcodeEngineTest {

    @InjectMocks
    private OpcodeEngine opcodeEngine;

    @Test
    public void testOpcode() {
        short expectedValue = (short) 0xA250;
        short actualValue = opcodeEngine.calculateOpcode((char) 0xA2, (char) 0x50);

        assertThat(actualValue, is(expectedValue));
    }

    @Test
    public void testTime() {

        Chip8 chip8 = new Chip8();
        chip8.initialize();
        chip8.setSoundTimer((char) 60);
        chip8.setDelayTimer((char) 60);

        for (int i = 60; i >= 0; i--) {
            assertThat((int) chip8.getDelayTimer(), is(i));
            assertThat((int) chip8.getSoundTimer(), is(i));
            opcodeEngine.updateTimer(chip8);
        }


    }
}