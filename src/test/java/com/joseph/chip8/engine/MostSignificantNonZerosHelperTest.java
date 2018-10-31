package com.joseph.chip8.engine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MostSignificantNonZerosHelperTest {

    @Test
    public void itTestsLowestByteWorksCorrectly() {
        short bytes = (short) 0xABCD;

        char expected = Helper.lowestByte(bytes);

        assertThat(0xCD).isEqualTo(expected);
    }

    @Test
    public void itTestsLowest12ByteWorksCorrectly() {
        short bytes = (short) 0xABCD;

        short expected = Helper.lowest12bits(bytes);

        assertThat(0xBCD).isEqualTo(expected);
    }
}