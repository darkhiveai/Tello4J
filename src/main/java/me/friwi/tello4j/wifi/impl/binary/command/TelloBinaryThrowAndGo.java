package me.friwi.tello4j.wifi.impl.binary.command;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.*;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

import java.io.UnsupportedEncodingException;

public class TelloBinaryThrowAndGo extends TelloBinaryCommand{

    //TODO hack to do throw and go
    private static final byte[] bytes =  new byte[] {(byte) 0xcc, 0x58, 0x00, 0x7c, 0x48, 0x5d, 0x00, (byte) 0xe4, 0x01, (byte) 0x40, 0x10 };
    @Override
    public byte[] serializeCommand() {
        TelloPacket packet = new TelloPacket();
        packet.header = new TelloHeader();
        packet.header.packetLengthBits = 88;
        packet.body = new TelloSubPacket();
        packet.body.packetType = PacketTypeValues.TO_TELLO_THROW_AND_GO;
        packet.body.messageID = TelloMessageID.DoThrowTakeoff;
//        packet.body.setSequenceNumber();


        return bytes;
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
