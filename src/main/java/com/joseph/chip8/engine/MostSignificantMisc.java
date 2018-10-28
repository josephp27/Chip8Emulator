package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import com.joseph.chip8.chip8.Settings;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantMisc {

    public void evaluateOpcode(Chip8 chip8, int opcode) {
        Settings settings = chip8.getSettings();
        switch (opcode & 0xF000) {
            case 0xA000: // ANNN: Sets I to the address NNN
                settings.setIndex(lowest12bits((short) opcode));
                settings.setPc((short) (settings.getPc() + 2));
                break;

            case 0x1000:
                System.out.println("jump!");
                break;

            case 0x2000:
                settings.getStack()[settings.getSp()] = settings.getPc();
                short sp = settings.getSp();
                settings.setSp(++sp);
                settings.setPc((short) (opcode & 0x0FFF));
                break;

            case 0x3000: //Skips the next instruction if VX equals NN. (Usually the next instruction is a jump to skip a code block)
                break;

            default:
                System.out.println("unknown opcode " + opcode);
        }
    }

    public short lowest12bits(short opcode) {
        return (short) (opcode & 0xFFF);
    }

    public char lowestByte(short opcode) {
        return (char) (opcode & 0xFF);
    }

}
