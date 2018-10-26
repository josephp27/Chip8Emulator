package com.joseph.chip8.chip8;

import com.joseph.chip8.engine.OpcodeEngine;
import com.joseph.chip8.graphics.Chip8Graphics;
import com.joseph.chip8.graphics.Fonts;
import com.joseph.chip8.input.Input;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Service
public class Chip8 {

    private Fonts fonts;
    private Input input;
    private Chip8Graphics chip8Graphics;
    private OpcodeEngine opcodeEngine;
    private Settings settings;

    public Chip8(Fonts fonts, Input input, Chip8Graphics chip8Graphics, OpcodeEngine opcodeEngine) {
        this.fonts = fonts;
        this.input = input;
        this.chip8Graphics = chip8Graphics;
        this.opcodeEngine = opcodeEngine;
    }

    public void initialize() {

        settings = Settings.builder()
                .draw(true)
                .memory(new char[4096])
                .pc((short) 0x200)
                .registers(new char[16])
                .index((short) 0)
                .opcode((short) 0)
                .sp((short) 0)
                .stack(new short[16])
                .delayTimer((char) 0)
                .soundTimer((char) 0)
                .chip8Graphics(chip8Graphics)
                .fonts(fonts)
                .input(input)
                .opcodeEngine(opcodeEngine)
                .build();

        settings.getChip8Graphics().clear();
        Arrays.fill(settings.getRegisters(), (char) 0);
        Arrays.fill(settings.getStack(), (short) 0);
        Arrays.fill(settings.getMemory(), (char) 0);

        List<Character> fontList = fonts.getFontSet();
        for (int i = 0; i < 80; i++) {
            settings.getMemory()[i] = fontList.get(i);
        }
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