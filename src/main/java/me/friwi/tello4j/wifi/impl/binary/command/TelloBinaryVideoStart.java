/*
 * Copyright 2021 Darkhive, Inc. All Rights Reserved.
 */

package me.friwi.tello4j.wifi.impl.binary.command;

import java.io.UnsupportedEncodingException;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

public class TelloBinaryVideoStart extends TelloBinaryCommand{

    private static final byte[] bytes = new byte[] {(byte) 0xcc, 0x58, 0x00, 0x7c, 0x60, 0x25, 0x00, 0x09, 0x00, 0x00, 0x5b, (byte) 0xc5};
//    private static final byte[] bytes = new byte[] {(byte) 0xcc, 0x58, 0x00, 0x7c, 0x48, 0x5d, 0x00, (byte) 0xe4, 0x01, (byte) 0x40, 0x10 };

    @Override
    public byte[] serializeCommand() {

        return fixupPacket(bytes);
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
