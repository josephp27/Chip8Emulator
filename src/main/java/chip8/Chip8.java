package chip8;

import engine.OpcodeEngine;
import lombok.Data;

import java.io.*;

@Data
public class Chip8 {

    private boolean draw;
    private char[] memory;
    private int index;
    private short opcode;
    private int pc;
    private int sp;

    private OpcodeEngine opcodeEngine;

    public void initialize() {
        draw = true;
        memory = new char[4096];
        pc = 0x200;  // Program counter starts at 0x200
        opcode = 0;  // Reset current opcode
        index = 0;   // Reset index register
        sp = 0;      // Reset stack pointer

        opcodeEngine = new OpcodeEngine();

        // TODO: Clear display
        // TODO: Clear stack
        // TODO: Clear registers V0-VF
        // TODO: Clear memory
        // TODO: Load fontset
        // TODO: Reset timers
    }

    public void loadGame(String game) throws IOException {
        InputStream reader = new FileInputStream("src/main/resources/c8games/" + game);

        int i = 0;
        int data = (char) reader.read();

        while (data != -1) {
            System.out.println(i);
            memory[i + 512] = (char) data;

            i++;
            data = reader.read();
        }

        reader.close();

        System.out.println(memory); //TODO REMOVE THIS LINE
    }

    public void emulateCycle() {
        opcode = opcodeEngine.calculateOpcode(memory[pc], memory[pc + 1]);
        opcodeEngine.decode(this);

        System.out.println(pc);
    }


}

