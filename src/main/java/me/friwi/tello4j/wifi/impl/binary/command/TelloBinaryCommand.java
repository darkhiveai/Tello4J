package me.friwi.tello4j.wifi.impl.binary.command;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.TelloPacket;
import me.friwi.tello4j.wifi.model.command.TelloCommand;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public abstract class TelloBinaryCommand extends TelloCommand {

    private static short seqNum = 0;
    protected byte[] fixupPacket(byte[] bytes)
    {
        bytes[3] = (byte) TelloPacket.crc8(Arrays.copyOf(bytes, 4));
        bytes[7] = (byte)(seqNum & 0xff);
        bytes[8] = (byte)((seqNum >> 8) & 0xff);

        int crc16 = TelloPacket.crc16(Arrays.copyOf(bytes, bytes.length-2));
        bytes[bytes.length - 2] = (byte)(crc16 & 0xff);
        bytes[bytes.length - 1] = (byte)((crc16 >> 8) & 0xff);


        return bytes;
    }
    @Override
    public abstract byte[] serializeCommand();

    @Override
    public abstract TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException;
}
