package engine;

import chip8.Chip8;

public class MostSignificantNonZerosHelper {

    public void mostSignificantNonZeros(Chip8 chip8, short opcode) {
        switch (opcode & 0xF000) {
            case 0xA000: // ANNN: Sets I to the address NNN
                chip8.setIndex(opcode & 0xFFF);
                chip8.setPc(chip8.getPc() + 2);
            case 0x0000:


            default:
                System.out.println("unknown opcode " + chip8.getOpcode());
        }
    }

}
