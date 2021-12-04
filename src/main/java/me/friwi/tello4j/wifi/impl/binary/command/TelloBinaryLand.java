package me.friwi.tello4j.wifi.impl.binary.command;

import java.io.UnsupportedEncodingException;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.PacketTypeValues;
import me.friwi.tello4j.wifi.impl.binary.TelloHeader;
import me.friwi.tello4j.wifi.impl.binary.TelloMessageID;
import me.friwi.tello4j.wifi.impl.binary.TelloPacket;
import me.friwi.tello4j.wifi.impl.binary.TelloSubPacket;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

public class TelloBinaryLand extends TelloBinaryCommand{

    //TODO hack to do throw and go
    private static final byte[] bytes =  new byte[] {(byte) 0xcc, 0x60, 0x00, 0x27, 0x68, 0x55, 0x00, (byte) 0xe5, 0x01, 0x00, (byte) 0xba, (byte) 0xc7};
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
