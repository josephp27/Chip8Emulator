package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantEight {

    public void evaluateOpcode(Chip8 chip8, int opcode) {
        char[] registers = chip8.getSettings().getRegisters();
        int x, y;

        switch (opcode & 0x000F) {
            case 0x0004:
                if (registers[(opcode & 0x00F0) >> 4] > (0xFF - registers[(opcode & 0x0F00) >> 8])) {
                    registers[0xF] = 1; //carry
                } else {
                    registers[0xF] = 0;
                }
                registers[(opcode & 0x0F00) >> 8] += registers[(opcode & 0x00F0) >> 4];
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0003: //8XY3 Sets VX to VX xor VY.
                x = Helper.getX(opcode);
                y = Helper.getY(opcode);

                registers[x] = (char) (x ^ y);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0000: // 0x8XY0: Sets VX to the value of VY
                x = Helper.getX(opcode);
                y = Helper.getY(opcode);

                registers[x] = registers[y];
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0002: //	Sets VX to VX and VY. (Bitwise AND operation)
                x = Helper.getX(opcode);
                y = Helper.getY(opcode);

                registers[x] = (char) (registers[x] & registers[y]);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0005: // 0x8XY5: VY is subtracted from VX. VF is set to 0 when there's a borrow, and 1 when there isn't
                x = Helper.getX(opcode);
                y = Helper.getY(opcode);

                if (registers[y] > registers[x]) {
                    registers[0xF] = 0;
                } else {
                    registers[0xF] = 1;
                }

                registers[x] -= registers[y];
                Helper.incrementProgramCounter(chip8);
                break;

            default:
                System.out.println(String.format("unknown opcode: 0x%08X", opcode));

        }
    }
}
