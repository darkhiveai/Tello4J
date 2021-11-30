package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

import java.util.List;

public class PacketType {

    //Workaround as Preon encoding/decoding failing on this due to LE/BE.
    @BoundNumber(size="8", byteOrder = ByteOrder.LittleEndian)
    public PacketTypeValues getPacketType;

//    @BoundNumber(size= "3")
//    public byte packetType;
//
//    @BoundNumber(size= "3")
//    public byte packetSubType;

}

