package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundEnumOption;

public enum PacketTypeValues {
    @BoundEnumOption(0x88)
    FROM_TELLO,
    @BoundEnumOption(0x60)
    TO_TELLO_SET_STICK,
    @BoundEnumOption(0x68)
    TO_TELLO_COMMAND,
    @BoundEnumOption(0x48)
    TO_TELLO_THROW_AND_GO,
    @BoundEnumOption(0x50)
    TO_TELLO_LOG_HEADER,

    //These are not decoding properly, reverting to overall byte representation.
//    @BoundEnumOption(0)
//    EXTENDED,
//    @BoundEnumOption(1)
//    GETINFO,
//    @BoundEnumOption(2)
//    DATA1,
//    @BoundEnumOption(4)
//    DATA2,
//    @BoundEnumOption(5)
//    SETINFO,
//    @BoundEnumOption(6)
//    FLIP,



}
