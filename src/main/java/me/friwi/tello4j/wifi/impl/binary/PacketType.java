package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;

public class PacketType {

    @Bound
    public boolean fromDrone;

    @Bound
    public boolean toDrone;

    @BoundNumber(size= "3")
    public PacketTypeValues packetType;


    @BoundNumber(size= "3")
    public byte packetSubType;

}
