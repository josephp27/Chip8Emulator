package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantZero {
    public void evaluateOpcode(Chip8 chip8, int opcode) {
        switch (opcode & 0x000F) {
            case 0x0000: // 0x00E0: Clears the screen
                chip8.getSettings().setDraw(true);
                chip8.getSettings().getChip8Graphics().clear();
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x000E: // 0x00EE: Returns from subroutine
                int sp = Helper.decrementStackPointer(chip8);
                chip8.getSettings().setPc(chip8.getSettings().getStack()[sp]);
                Helper.incrementProgramCounter(chip8);
                break;

            default: // Calls RCA 1802 program at address NNN. Not necessary for most ROMs.
                //TODO: not done
                System.out.println(String.format("unknown opcode: 0x%08X", opcode));
        }
    }
}
