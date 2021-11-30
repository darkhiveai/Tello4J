package me.friwi.tello4j.wifi.impl.binary.command;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.*;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

import java.io.UnsupportedEncodingException;

public class TelloBinaryThrowAndGo extends TelloBinaryCommand{
    @Override
    public byte[] serializeCommand() {
        TelloPacket packet = new TelloPacket();
        packet.header = new TelloHeader();
        packet.header.packetLengthBits = 88;
        packet.body = new TelloSubPacket();
        packet.body.packetType = PacketTypeValues.TO_TELLO_THROW_AND_GO;
        packet.body.messageID = TelloMessageID.DoThrowTakeoff;
        return packet.serializeCommand();
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
