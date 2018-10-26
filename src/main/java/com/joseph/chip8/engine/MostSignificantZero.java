package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantZero {
    public void evaluateOpcode(Chip8 chip8, short opcode) {
        switch (opcode & 0x000F) {
            case 0x0000: // 0x00E0: Clears the screen
                // Execute opcode
                chip8.getSettings().getChip8Graphics().clear();

                break;

            case 0x000E: // 0x00EE: Returns from subroutine
                return;
            default:
                // Calls RCA 1802 program at address NNN. Not necessary for most ROMs.
        }
    }
}
