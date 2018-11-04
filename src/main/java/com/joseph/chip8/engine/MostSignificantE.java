package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantE {
    public void evaluateOpcode(Chip8 chip8, int opcode) {
        int x;
        char[] keyPressed = chip8.getSettings().getInput().getKeyPressed();
        char[] registers = chip8.getSettings().getRegisters();

        switch (opcode & 0x00FF) {
            case 0x009E: // EX9E: Skips the next instruction if the key stored in VX is pressed
                x = Helper.getX(opcode);

                if (keyPressed[registers[x]] != 0)
                    Helper.incrementProgramCounter(chip8);

                Helper.incrementProgramCounter(chip8);
                break;

            case 0x00A1: // EXA1: Skips the next instruction if the key stored in VX isn't pressed
                x = Helper.getX(opcode);
                if (keyPressed[registers[x]] == 0)
                    Helper.incrementProgramCounter(chip8);

                Helper.incrementProgramCounter(chip8);
                break;

            default:
                System.out.println(String.format("unknown opcode: 0x%08X", opcode));
        }

    }
}
