package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import com.joseph.chip8.chip8.Settings;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MostSignificantMisc {

    public void evaluateOpcode(Chip8 chip8, int opcode) {
        Settings settings = chip8.getSettings();
        char[] registers = settings.getRegisters();
        int x;
        int y;

        switch (opcode & 0xF000) {
            case 0x1000: //	TODO: Jumps to address NNN.
                settings.setPc(opcode & 0x0FFF);
                break;

            case 0x2000: // Calls subroutine at NNN.
                settings.getStack()[settings.getSp()] = settings.getPc();
                Helper.incrementStackPointer(chip8);
                settings.setPc((short) (opcode & 0x0FFF));
                break;

            case 0x3000: // 0x3XNN: Skips the next instruction if VX equals NN
                if (registers[(opcode & 0x0F00) >> 8] == (opcode & 0x00FF)) {
                    Helper.incrementProgramCounter(chip8); //skips next instruction -- double increment
                }

                Helper.incrementProgramCounter(chip8);
                break;

            case 0xA000: // ANNN: Sets I to the address NNN
                settings.setIndex(Helper.lowest12bits((short) opcode));
                Helper.incrementProgramCounter(chip8);
                break;

            case 0xD000:
                x = settings.getRegisters()[(opcode & 0x0F00) >> 8];
                y = settings.getRegisters()[(opcode & 0x00F0) >> 4];
                int height = opcode & 0x000F;
                int pixel;

                settings.getRegisters()[0xF] = 0;
                for (int yline = 0; yline < height; yline++) {
                    pixel = settings.getMemory()[settings.getIndex() + yline];
                    for (int xline = 0; xline < 8; xline++) {
                        if ((pixel & (0x80 >> xline)) != 0) {
                            if (settings.getChip8Graphics().getScreen()[(x + xline + ((y + yline) * 64))] == 1)
                                settings.getRegisters()[0xF] = 1;
                            settings.getChip8Graphics().getScreen()[x + xline + ((y + yline) * 64)] ^= 1;
                        }
                    }
                }

                chip8.getSettings().setDraw(true);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x6000: // 0x6XNN: Sets VX to NN.
                registers[(opcode & 0x0F00) >> 8] = (char) (opcode & 0x00FF);
                Helper.incrementProgramCounter(chip8);
                break;

            case 0x7000: // 0x7XNN: Adds NN to VX.
                registers[(opcode & 0x0F00) >> 8] += opcode & 0x00FF;
                Helper.incrementProgramCounter(chip8);
                break;

            case 0xC000: // CXNN: Sets VX to a random number and NN
                x = Helper.getX(opcode);

                Random rand = new Random();
                int n = rand.nextInt(255);
                registers[x] = (char) ((n % 0xFF) & (opcode & 0x00FF));

                Helper.incrementProgramCounter(chip8);
                break;

            case 0x4000: // Skips the next instruction if VX doesn't equal NN.
                x = Helper.getX(opcode);
                int NN = Helper.lowestByte(opcode);

                if (registers[x] != NN) {
                    Helper.incrementProgramCounter(chip8);
                }

                Helper.incrementProgramCounter(chip8);
                break;

            default:
                System.out.println(String.format("unknown opcode: 0x%08X", opcode));
        }
    }
}
