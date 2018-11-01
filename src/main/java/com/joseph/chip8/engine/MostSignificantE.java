package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import org.springframework.stereotype.Component;

@Component
public class MostSignificantE {
    public void evaluateOpcode(Chip8 chip8, int opcode) {
        System.out.println(String.format("unknown opcode: 0x%08X", opcode));
    }
}
