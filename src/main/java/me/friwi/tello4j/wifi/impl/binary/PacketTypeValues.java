package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundEnumOption;

public enum PacketTypeValues {
    @BoundEnumOption(0)
    EXTENDED,
    @BoundEnumOption(1)
    GETINFO,
    @BoundEnumOption(2)
    DATA1,
    @BoundEnumOption(4)
    DATA2,
    @BoundEnumOption(5)
    SETINFO,
    @BoundEnumOption(6)
    FLIP,
}
