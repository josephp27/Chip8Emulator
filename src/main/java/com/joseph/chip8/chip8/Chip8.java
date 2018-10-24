package com.joseph.chip8.chip8;

import com.joseph.chip8.engine.OpcodeEngine;
import com.joseph.chip8.graphics.Fonts;
import com.joseph.chip8.graphics.Graphics;
import com.joseph.chip8.input.Input;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@Service
@AllArgsConstructor
public class Chip8 {

    private Fonts fonts;
    private Input input;
    private Graphics graphics;
    private OpcodeEngine opcodeEngine;
    private Settings settings;

    public void initialize() {

        settings = Settings.builder()
                .draw(true)
                .memory(new char[4096])
                .pc(0x200)
                .index(0)
                .opcode((short) 0)
                .sp(0)
                .stack(new short[16])
                .delayTimer((char) 0)
                .soundTimer((char) 0)
                .build();

        // TODO: Clear display
        // TODO: Clear registers V0-VF

        List<Character> fontList = fonts.getFontSet();
        for (int i = 0; i < 80; i++) {
            settings.getMemory()[i] = fontList.get(i);
        }

        System.out.println(settings.getMemory());
    }

    public void loadGame(String game) throws IOException {
        InputStream reader = new FileInputStream("src/main/resources/c8games/" + game);

        int i = 0;
        int data = (char) reader.read();

        while (data != -1) {
            settings.getMemory()[i + 512] = (char) data;

            i++;
            data = reader.read();
        }

        System.out.println(settings.getMemory());

    }

    public void updateTimer() {

        int delayTimer = settings.getDelayTimer();
        int soundTimer = settings.getSoundTimer();

        if (delayTimer > 0)
            settings.setDelayTimer((char) --delayTimer);

        if (soundTimer > 0) {
            if (soundTimer == 1)
                System.out.println("BEEP!\n");
            settings.setSoundTimer((char) --soundTimer);
        }
    }

    public void emulateCycle() {
        char[] memory = settings.getMemory();
        int pc = settings.getPc();

        short opcode = opcodeEngine.calculateOpcode(memory[pc], memory[pc + 1]);
        settings.setOpcode(opcode);

        opcodeEngine.decode(this);
        updateTimer();
    }

}