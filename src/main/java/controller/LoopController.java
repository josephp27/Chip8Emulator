package controller;

import chip8.Chip8;
import engine.MostSignificantNonZerosHelper;
import graphics.Graphics;
import input.Input;

public class LoopController {

    private Chip8 chip8;
    private Graphics graphics;
    private Input input;

    private long firstTick;

    public LoopController(Chip8 chip8, Graphics graphics, Input input) {
        this.chip8 = chip8;
        this.graphics = graphics;
        this.input = input;
    }

    public void run() throws Exception {

        //TODO implement this so frame isnt capped at 60
        firstTick = System.currentTimeMillis();

        final int FRAMES_PER_SECOND = 60;
        final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

        long sleepTime;

        graphics.setup();
        input.setup();

        chip8.initialize();
        chip8.loadGame("BLINKY");

        long nextTick = getTickCount();

        while (input.isRunGame()) {
//            chip8.emulateCycle();
//
//            if (chip8.isDraw()) {
//                graphics.draw();
//            }
//
//            input.pressKeys();

            nextTick += SKIP_TICKS;
            sleepTime = nextTick - getTickCount();

            if (sleepTime >= 0) {
                Thread.sleep(sleepTime);
            }
        }

    }

    private long getTickCount() {
        return System.currentTimeMillis() - firstTick;
    }
}
