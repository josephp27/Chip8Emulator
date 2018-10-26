package com.joseph.chip8.engine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MostSignificantNonZerosHelperTest {

    @InjectMocks
    private MostSignificantMisc mostSignificantNonZerosHelper;

    @Test
    public void itTestsLowestByteWorksCorrectly() {
        short bytes = (short) 0xABCD;

        char expected = mostSignificantNonZerosHelper.lowestByte(bytes);

        assertThat(0xCD).isEqualTo(expected);
    }

    @Test
    public void itTestsLowest12ByteWorksCorrectly() {
        short bytes = (short) 0xABCD;

        short expected = mostSignificantNonZerosHelper.lowest12bits(bytes);

        assertThat(0xBCD).isEqualTo(expected);
    }

    @Test
    public void name() {
        short operation = (short) (0xE000 & 0xF000);
        System.out.println(operation == 0xE000);
    }
}