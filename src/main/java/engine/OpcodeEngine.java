package engine;

import chip8.Chip8;

public class OpcodeEngine {

    private MostSignificantNonZerosHelper msNzHelper;
    private MostSignificantZerosHelper mszHelper;

    public OpcodeEngine(){
        msNzHelper = new MostSignificantNonZerosHelper();
        mszHelper = new MostSignificantZerosHelper();
    }

    public void decode(Chip8 chip8) {
        short opcode = chip8.getOpcode();

        short operation = (short) (opcode & 0xF000);

        if (operation == 0x0000) {
            mszHelper.mostSignificantZeros(chip8, opcode);
        } else {
            msNzHelper.mostSignificantNonZeros(chip8, opcode);
        }
    }

    public short calculateOpcode(char firstAddress, char secondAddress) {
        return (short) (((firstAddress & 0xFF) << 8) | (secondAddress & 0xFF));
    }
}
