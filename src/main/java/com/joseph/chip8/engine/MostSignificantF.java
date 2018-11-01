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

        switch (opcode & 0x00FF) {
            case 0x0033:
                memory[index] = (char) (registers[(opcode & 0x0F00) >> 8] / 100);
                memory[index + 1] = (char) ((registers[(opcode & 0x0F00) >> 8] / 10) % 10);
                memory[index + 2] = (char) ((registers[(opcode & 0x0F00) >> 8] % 100) % 10);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x001E: // FX1E: Adds VX to I
                if (index + registers[(opcode & 0x0F00) >> 8] > 0xFFF)    // registersF is set to 1 when range overflow (index+registersX>0xFFF), and 0 when there isn't.
                    registers[0xF] = 1;
                else
                    registers[0xF] = 0;
                chip8.getSettings().setIndex(chip8.getSettings().getIndex() + registers[(opcode & 0x0F00) >> 8]);
                Helper.incrementProgramCounter(chip8);

            default:
                System.out.println(String.format("unknown opcode: 0x%08X", opcode));
        }
    }
}
