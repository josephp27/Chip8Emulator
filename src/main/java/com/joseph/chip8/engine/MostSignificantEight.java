package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantEight {

    public void evaluateOpcode(Chip8 chip8, short opcode) {
        switch (opcode & 0x000F) {
            case 0x0004:
                char[] registers = chip8.getSettings().getRegisters();
                if (registers[(opcode & 0x00F0) >> 4] > (0xFF - registers[(opcode & 0x0F00) >> 8])) {
                    registers[0xF] = 1; //carry
                } else {
                    registers[0xF] = 0;
                }
                registers[(opcode & 0x0F00) >> 8] += registers[(opcode & 0x00F0) >> 4];
                Helper.incrementProgramCounter(chip8);
                break;
        }
    }
}
