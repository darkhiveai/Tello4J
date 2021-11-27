package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;

public class TelloSubPacket {

    //From header to Packet Size
    @BoundNumber(size="8")
    public short crc8;

    @BoundObject
    public PacketType packetType;

    @BoundNumber(size="16")
    public TelloMessageID messageID;

    @BoundNumber(size="16")
    public int sequenceNumber;


    @BoundObject(selectFrom = @Choices(
            alternatives = {
                    @Choices.Choice(condition = "messageID.value == 0x56", type = FlightData.class)
//                    @Choices.Choice(condition = "messageID == TelloMessageId.LogHeader", type = LogHeader.class),
//                    @Choices.Choice(condition = "messageID == TelloMessageId.LogData", type = LogData.class)
            }
    ))
    public TelloMessage payload;
}
