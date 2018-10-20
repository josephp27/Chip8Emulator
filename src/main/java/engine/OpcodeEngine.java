package engine;

import chip8.Chip8;

public class OpcodeEngine {

    public void decode(Chip8 chip8) {
        short opcode = chip8.getOpcode();
        switch (opcode & 0xF000) {
            case 0xA000: // ANNN: Sets I to the address NNN
                // Execute opcode
                chip8.setIndex(opcode & 0x0FFF);
                chip8.setPc(chip8.getPc() + 2);
                break;

            case 0x0000:
                switch (opcode & 0x000F) {
                    case 0x0000: // 0x00E0: Clears the screen
                        // Execute opcode
                        break;

                    case 0x000E: // 0x00EE: Returns from subroutine
                        // Execute opcode
                        break;

                    default:
                        System.out.println("Unknown opcode [0x0000]: " + opcode);
                }
                break;

            default:
                System.out.println("unknown opcode " + chip8.getOpcode());
        }

        // Update timers
//        if(delay_timer > 0)
//            --delay_timer;
//
//        if(sound_timer > 0)
//        {
//            if(sound_timer == 1)
//                printf("BEEP!\n");
//            --sound_timer;
//        }
    }

    public short calculateOpcode(char firstAddress, char secondAddress) {
        return (short) (((firstAddress & 0xFF) << 8) | (secondAddress & 0xFF));
    }
}
