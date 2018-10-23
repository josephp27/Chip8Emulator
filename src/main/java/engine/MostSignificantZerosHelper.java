package engine;

import chip8.Chip8;

public class MostSignificantZerosHelper {
    public void mostSignificantZeros(Chip8 chip8, short opcode) {
        switch (opcode & 0x000F) {
            case 0x0000: // 0x00E0: Clears the screen
                // Execute opcode
                break;

            case 0x000E: // 0x00EE: Returns from subroutine
                return;
            default:
                // Calls RCA 1802 program at address NNN. Not necessary for most ROMs.
        }
    }
}
