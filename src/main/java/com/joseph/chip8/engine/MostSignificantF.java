package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MostSignificantF {

    public void evaluateOpcode(Chip8 chip8, int opcode) {
        char[] memory = chip8.getSettings().getMemory();
        int index = chip8.getSettings().getIndex();
        char[] registers = chip8.getSettings().getRegisters();
        int x;

        switch (opcode & 0x00FF) {
            case 0x0033:
                memory[index] = (char) (registers[(opcode & 0x0F00) >> 8] / 100);
                memory[index + 1] = (char) ((registers[(opcode & 0x0F00) >> 8] / 10) % 10);
                memory[index + 2] = (char) ((registers[(opcode & 0x0F00) >> 8] % 100) % 10);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x001E: // FX1E: Adds VX to I
                x = Helper.getX(opcode);
                if (index + registers[x] > 0xFFF)    // registersF is set to 1 when range overflow (index+registersX>0xFFF), and 0 when there isn't.
                    registers[0xF] = 1;
                else
                    registers[0xF] = 0;
                chip8.getSettings().setIndex(chip8.getSettings().getIndex() + registers[(opcode & 0x0F00) >> 8]);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0065: // FX65: Fills V0 to VX with values from memory starting at address I
                x = Helper.getX(opcode);
                for (int i = 0; i <= x; i++) {
                    registers[i] = memory[index + i];
                }

                chip8.getSettings().setIndex(index + x + 1);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0055: // FX55: Stores V0 to VX in memory starting at address I
                x = Helper.getX(opcode);
                for (int i = 0; i <= x; i++) {
                    memory[index + i] = registers[i];
                }

                // On the original interpreter, when the operation is done, I = I + X + 1.
                chip8.getSettings().setIndex(index + x + 1);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0029: // FX29: Sets I to the location of the sprite for the character in VX. Characters 0-F (in hexadecimal) are represented by a 4x5 font
                x = Helper.getX(opcode);
                int sprite = registers[x] * 0x5;

                chip8.getSettings().setIndex(sprite);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0015:
                x = Helper.getX(opcode);
                chip8.getSettings().setDelayTimer((char) x);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x0007: //Sets VX to the value of the delay timer.
                x = Helper.getX(opcode);
                chip8.getSettings().getRegisters()[x] = chip8.getSettings().getDelayTimer();
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x018: // Sets the sound timer to VX.
                x = Helper.getX(opcode);
                chip8.getSettings().setSoundTimer(registers[x]);
                Helper.incrementProgramCounter(chip8);
                break;

            default:
                System.out.println(String.format("unknown opcode: 0x%08X", opcode));
        }
    }
}
