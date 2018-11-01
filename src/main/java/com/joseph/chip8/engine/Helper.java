package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;

public class Helper {

    public static void incrementProgramCounter(Chip8 chip8) {
        chip8.getSettings().setPc(chip8.getSettings().getPc() + 2);
    }
    public static int decrementStackPointer(Chip8 chip8) {
        int sp = chip8.getSettings().getSp();
        chip8.getSettings().setSp(--sp);
        return sp;
    }

    public static void incrementStackPointer(Chip8 chip8) {
        int sp = chip8.getSettings().getSp();
        chip8.getSettings().setSp(++sp);
    }

    public static short lowest12bits(int opcode) {
        return (short) (opcode & 0xFFF);
    }

    public static char lowestByte(int opcode) {
        return (char) (opcode & 0xFF);
    }

    public static int getX(int opcode){
        return (opcode & 0x0F00) >> 8;
    }

    public static int getY(int opcode){
        return (opcode & 0x00F0) >> 4;
    }
}
