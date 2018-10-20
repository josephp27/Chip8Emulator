package engine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpcodeEngineTest {

    @InjectMocks
    private OpcodeEngine opcodeEngine;

    @Test
    public void testOpcode() {
        short expectedValue = (short) 0xA250;
        short actualValue = opcodeEngine.calculateOpcode((char) 0xA2, (char) 0x50);

        System.out.println(expectedValue + " " + actualValue);
        assert actualValue == expectedValue;
    }
}