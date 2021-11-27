package me.friwi.tello4j.wifi.impl.binary;

import me.friwi.tello4j.wifi.model.TelloSDKValues;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;
import org.codehaus.preon.buffer.ByteOrder;

public class ConnectRequest {
    @BoundString(match = "conn_req:", size = "9")
    public String connectionRequestString = "conn_req:";

    @BoundNumber(size="16", byteOrder = ByteOrder.LittleEndian)
    public short streamVideoPort = TelloSDKValues.STREAM_PORT;
}
