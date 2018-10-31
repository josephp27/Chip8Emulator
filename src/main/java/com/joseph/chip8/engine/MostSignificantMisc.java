package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import com.joseph.chip8.chip8.Settings;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantMisc {

    public void evaluateOpcode(Chip8 chip8, int opcode) {
        Settings settings = chip8.getSettings();
        switch (opcode & 0xF000) {
            case 0x1000: //	TODO: Jumps to address NNN.
                System.out.println("jump!");
                break;

            case 0x2000: // Calls subroutine at NNN.
                settings.getStack()[settings.getSp()] = settings.getPc();
                short sp = settings.getSp();
                settings.setSp(++sp);
                settings.setPc((short) (opcode & 0x0FFF));
                break;

            case 0xA000: // ANNN: Sets I to the address NNN
                settings.setIndex(Helper.lowest12bits((short) opcode));
                Helper.incrementProgramCounter(chip8);
                break;

            default:
                System.out.println("unknown opcode " + opcode);
        }
    }
}
