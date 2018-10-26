package com.joseph.chip8.chip8;

import com.joseph.chip8.engine.OpcodeEngine;
import com.joseph.chip8.graphics.Chip8Graphics;
import com.joseph.chip8.graphics.Fonts;
import com.joseph.chip8.input.Input;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    private boolean draw;
    private char[] memory;
    private short[] stack;
    private char[] registers;
    private short sp;
    private short pc;
    private short index;
    private short opcode;
    private char delayTimer;
    private char soundTimer;
    private Chip8Graphics chip8Graphics;
    private Input input;
    private OpcodeEngine opcodeEngine;
    private Fonts fonts;
}
