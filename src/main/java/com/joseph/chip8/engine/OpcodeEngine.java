package com.joseph.chip8.engine;

import com.joseph.chip8.chip8.Chip8;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpcodeEngine {

    private MostSignificantMisc mostSignificantMisc;
    private MostSignificantZero mostSignificantZero;
    private MostSignificantEight mostSignificantEight;
    private MostSignificantF mostSignificantF;
    private MostSignificantE mostSignificantE;

    public void decode(Chip8 chip8) {
        int opcode = chip8.getSettings().getOpcode();

        int operation = opcode & 0xF000;

        if (operation == 0x0000) {
            mostSignificantZero.evaluateOpcode(chip8, opcode);
        } else if (operation == 0x8000) {
            mostSignificantEight.evaluateOpcode(chip8, opcode);
        } else if (operation == 0xE000) {
            mostSignificantE.evaluateOpcode(chip8, opcode);
        } else if (operation == 0xF000) {
            mostSignificantF.evaluateOpcode(chip8, opcode);
        } else {
            mostSignificantMisc.evaluateOpcode(chip8, opcode);
        }
    }
}
