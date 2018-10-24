package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantNonZerosHelper {

    public void mostSignificantNonZeros(Chip8 chip8, short opcode) {
        switch (opcode & 0xF000) {
            case 0xA000: // ANNN: Sets I to the address NNN
                chip8.getSettings().setIndex(opcode & 0xFFF);
                chip8.getSettings().setPc(chip8.getSettings().getPc() + 2);
                break;
            case 0x0000:
                break;
            case 0x1000:

                //TODO make sure this is correct
                System.out.println("jump!");
                chip8.getSettings().setPc(opcode & 0xFFF);
                break;

            default:
                System.out.println("unknown opcode " + opcode);
        }
    }

}
