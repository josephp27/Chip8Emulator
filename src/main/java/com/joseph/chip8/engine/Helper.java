package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;

public class Helper {

    public static void incrementProgramCounter(Chip8 chip8) {
        chip8.getSettings().setPc((short) (chip8.getSettings().getPc() + 2));
    }

    public static short lowest12bits(short opcode) {
        return (short) (opcode & 0xFFF);
    }

    public static char lowestByte(short opcode) {
        return (char) (opcode & 0xFF);
    }
}
