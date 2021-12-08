package me.friwi.tello4j.wifi.impl.binary.command;

import java.io.UnsupportedEncodingException;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.TelloVideoBitRate;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

public class TelloBinarySetExposure extends TelloBinaryCommand{



    private final byte exposeure;
    //                                                                       crc    typ  cmdL  cmdH  seqL  seqH  rateL  crc
    private static final byte[] bytes =
    //                                    crc    typ  cmdL  cmdH  seqL  seqH  evL  crc   crc
     new byte[] {(byte) 0xcc, 0x60, 0x00, 0x27, 0x68, 0x34, 0x00, 0x09, 0x00, 0x00, 0x5b, (byte) 0xc5};


    public TelloBinarySetExposure(byte exposeure) {
        this.exposeure = exposeure;
    }
    @Override
    public byte[] serializeCommand() {
        bytes[9] = exposeure;
        return fixupPacket(bytes);
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
