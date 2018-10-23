package chip8;

import engine.OpcodeEngine;
import graphics.Fonts;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Data
public class Chip8 {

    private boolean draw;
    private char[] memory;

    private short[] stack;
    private int sp;

    private int pc;
    private int index;
    private short opcode;

    private char delayTimer;
    private char soundTimer;

    private Fonts fonts;

    private OpcodeEngine opcodeEngine;

    public void initialize() {
        draw = true;
        memory = new char[4096];

        pc = 0x200;  // Program counter starts at 0x200
        index = 0;   // Reset index register
        opcode = 0;  // Reset current opcode

        sp = 0;      // Reset stack pointer
        stack = new short[16];

        opcodeEngine = new OpcodeEngine();

        fonts = new Fonts();


        // TODO: Clear display
        // TODO: Clear registers V0-VF


        delayTimer = 0;
        soundTimer = 0;


        List<Character> fontList = fonts.getFontSet();
        for (int i = 0; i < 80; i++) {
            memory[i] = fontList.get(i);
        }

    }

    public void loadGame(String game) throws IOException {
        InputStream reader = new FileInputStream("src/main/resources/c8games/" + game);

        int i = 0;
        int data = (char) reader.read();

        while (data != -1) {
            memory[i + 512] = (char) data;

            i++;
            data = reader.read();
        }

        reader.close();
    }

    public void emulateCycle() {
        opcode = opcodeEngine.calculateOpcode(memory[pc], memory[pc + 1]);
        opcodeEngine.decode(this);
    }


}

