package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import com.joseph.chip8.chip8.Settings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class OpcodeEngineTest {

    @InjectMocks
    private OpcodeEngine opcodeEngine;

    @Mock
    private MostSignificantZero mostSignificantZero;

    @Mock
    private MostSignificantE mostSignificantE;

    @Mock
    private MostSignificantF mostSignificantF;

    @Mock
    private MostSignificantMisc mostSignificantMisc;

    @Mock
    private MostSignificantEight mostSignificantEight;

    private Chip8 chip8;

    @Before
    public void setup() {
        chip8 = new Chip8(null, null, null, null);
    }

    @Test
    public void testOpcode() {
        int expectedValue = 0xA250;
        int overflow = 0xFFFF;
        int actualValue = opcodeEngine.calculateOpcode((char) 0xA2, (char) 0x50);
        int actualValueOverflow = opcodeEngine.calculateOpcode((char) 0xFF, (char) 0xFF);

        assertThat(actualValue, is(expectedValue));
        assertThat(overflow, is(actualValueOverflow));
    }

    @Test
    public void itVerifiesEntersCorrectClass_MSZ() {

        chip8.setSettings(Settings.builder().opcode((short) 0x0000).build());
        opcodeEngine.decode(chip8);

        verify(mostSignificantZero).evaluateOpcode(chip8, chip8.getSettings().getOpcode());
        verifyZeroInteractions(mostSignificantE);
        verifyZeroInteractions(mostSignificantEight);
        verifyZeroInteractions(mostSignificantF);
        verifyZeroInteractions(mostSignificantMisc);
    }

    @Test
    public void itVerifiesEntersCorrectClass_MSEight() {

        chip8.setSettings(Settings.builder().opcode((short) 0x8000).build());
        opcodeEngine.decode(chip8);

        verifyZeroInteractions(mostSignificantZero);
        verify(mostSignificantEight).evaluateOpcode(chip8, chip8.getSettings().getOpcode());
        verifyZeroInteractions(mostSignificantF);
        verifyZeroInteractions(mostSignificantE);
        verifyZeroInteractions(mostSignificantMisc);
    }

    @Test
    public void itVerifiesEntersCorrectClass_MSE() {

        chip8.setSettings(Settings.builder().opcode((short) 0xE000).build());
        opcodeEngine.decode(chip8);

        verifyZeroInteractions(mostSignificantZero);
        verify(mostSignificantE).evaluateOpcode(chip8, chip8.getSettings().getOpcode());
        verifyZeroInteractions(mostSignificantF);
        verifyZeroInteractions(mostSignificantEight);
        verifyZeroInteractions(mostSignificantMisc);
    }

    @Test
    public void itVerifiesEntersCorrectClass_MSF() {

        chip8.setSettings(Settings.builder().opcode((short) 0xF000).build());
        opcodeEngine.decode(chip8);

        verifyZeroInteractions(mostSignificantZero);
        verify(mostSignificantF).evaluateOpcode(chip8, chip8.getSettings().getOpcode());
        verifyZeroInteractions(mostSignificantE);
        verifyZeroInteractions(mostSignificantEight);
        verifyZeroInteractions(mostSignificantMisc);
    }

    @Test
    public void itVerifiesEntersCorrectClass_MSMISC() {

        chip8.setSettings(Settings.builder().opcode((short) 0x2000).build());
        opcodeEngine.decode(chip8);

        verifyZeroInteractions(mostSignificantZero);
        verify(mostSignificantMisc).evaluateOpcode(chip8, chip8.getSettings().getOpcode());
        verifyZeroInteractions(mostSignificantE);
        verifyZeroInteractions(mostSignificantEight);
        verifyZeroInteractions(mostSignificantF);
    }
}