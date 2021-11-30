package me.friwi.tello4j.wifi.impl.binary.command;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.model.TelloSDKValues;
import me.friwi.tello4j.wifi.model.response.TelloResponse;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;
import org.codehaus.preon.buffer.ByteOrder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TelloBinaryConnectRequest extends TelloBinaryCommand {

    private static final Codec<TelloBinaryConnectRequest> codec = Codecs.create(TelloBinaryConnectRequest.class);
    @BoundString(match = "conn_req:", size = "9")
    public String connectionRequestString = "conn_req:";

    @BoundNumber(size="16", byteOrder = ByteOrder.LittleEndian)
    public short streamVideoPort = TelloSDKValues.STREAM_PORT;


    @Override
    public byte[] serializeCommand() {
        try {
            return Codecs.encode(this, codec);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
