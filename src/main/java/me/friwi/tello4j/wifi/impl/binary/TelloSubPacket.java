package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundObject;
import org.codehaus.preon.annotation.Choices;
import org.codehaus.preon.el.ImportStatic;

@ImportStatic(TelloMessageID.class)
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
                    @Choices.Choice(condition = "messageID == TelloMessageID.FlightStatus", type = FlightData.class),
//                    @Choices.Choice(condition = "messageID == TelloMessageID.WifiStrength", type = WifiStrength.class),
//                    @Choices.Choice(condition = "messageID == TelloMessageId.LogData", type = LogData.class)
            }
    ))
    public TelloMessage payload;
}
