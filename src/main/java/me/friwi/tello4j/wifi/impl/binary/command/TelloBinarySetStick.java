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

public class TelloBinarySetStick extends TelloBinaryCommand{
    @Override
    public byte[] serializeCommand() {
        return new byte[] {(byte) 0xcc, (byte) 0xd8, 0x00, 0x53, 0x60, 0x50, 0x00, 0x00, 0x00, 0x00, 0x42, 0x00, 0x00,0x10, (byte) 0x80, (byte) 0xb0, 0x039, 0x00, 0x18, 0x00, (byte) 0xb5, 0x00, 0x01, 0x00, 0x47, (byte) 0xd1};
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
