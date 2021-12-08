package me.friwi.tello4j.wifi.impl.binary.command;

import java.io.UnsupportedEncodingException;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.TelloVideoBitRate;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

public class TelloBinarySetVideoDyn extends TelloBinaryCommand{



    private final int rate;
    //                                                                       crc    typ  cmdL  cmdH  seqL  seqH  rateL  crc
    private static final byte[] bytes = new byte[] {(byte) 0xcc, 0x60, 0x00, 0x27, 0x68, 0x21, 0x00, 0x09, 0x00, 0x00, 0x5b, (byte) 0xc5};



    public TelloBinarySetVideoDyn(int rate) {
        this.rate = rate;
    }
    @Override
    public byte[] serializeCommand() {
        bytes[9] = (byte) rate;
        return fixupPacket(bytes);
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
