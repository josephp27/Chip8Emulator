package com.joseph.chip8.controller;

import com.joseph.chip8.chip8.Chip8;
import com.joseph.chip8.graphics.Chip8Graphics;
import com.joseph.chip8.input.Input;
import org.springframework.stereotype.Service;

@Service
public class LoopController {

    private Chip8 chip8;
    private Chip8Graphics chip8Graphics;
    private Input input;
    private long firstTick;

    public LoopController(Chip8 chip8, Chip8Graphics chip8Graphics, Input input) throws Exception {
        this.chip8 = chip8;
        this.chip8Graphics = chip8Graphics;
        this.input = input;

        firstTick = 0;
        run();
    }

    public void run() throws Exception {

        //TODO implement this so frame isnt capped at 60
        firstTick = System.currentTimeMillis();

        final int FRAMES_PER_SECOND = 800;
        final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

        long sleepTime;

        chip8Graphics.setup();
        input.setup();

        chip8.initialize();
        chip8.loadGame("BRIX");
//        chip8.loadGame("BRIX");

        long nextTick = getTickCount();

        while (input.isRunGame()) {
            chip8.emulateCycle();

            if (chip8.getSettings().isDraw()) {
                chip8Graphics.draw();
            }

            input.pressKeys();

            nextTick += SKIP_TICKS;
            sleepTime = nextTick - getTickCount();

            if (sleepTime >= 0) {
                Thread.sleep(sleepTime);
            }

            chip8.getSettings().setDraw(false);
        }

    }

    private long getTickCount() {
        return System.currentTimeMillis() - firstTick;
    }
}
