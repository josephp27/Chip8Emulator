package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpcodeEngine {

    private MostSignificantNonZerosHelper msNzHelper;
    private MostSignificantZerosHelper mszHelper;

    public void decode(Chip8 chip8) {
        short opcode = chip8.getSettings().getOpcode();

        short operation = (short) (opcode & 0xF000);

        if (operation == 0x0000) {
            mszHelper.mostSignificantZeros(chip8, opcode);
        } else {
            msNzHelper.mostSignificantNonZeros(chip8, opcode);
        }
    }

    public short calculateOpcode(char firstAddress, char secondAddress) {
        return (short) (((firstAddress & 0xFF) << 8) | (secondAddress & 0xFF));
    }
}
