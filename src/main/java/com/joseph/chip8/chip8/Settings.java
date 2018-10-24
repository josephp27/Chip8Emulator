package com.joseph.chip8.chip8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Settings {
    private boolean draw;
    private char[] memory;
    private short[] stack;
    private int sp;
    private int pc;
    private int index;
    private short opcode;
    private char delayTimer;
    private char soundTimer;
}
