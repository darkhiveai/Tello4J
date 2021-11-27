package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

public class TelloHeader {
    @BoundNumber( match = "0xCC", size="8", byteOrder = ByteOrder.LittleEndian)
    public final short headerMagic = (byte) 0xCC;

    @BoundNumber(size="16")
    public int packetLengthBits;
}
