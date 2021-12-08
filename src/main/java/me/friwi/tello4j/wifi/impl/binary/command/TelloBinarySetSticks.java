package me.friwi.tello4j.wifi.impl.binary.command;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.impl.binary.TelloVideoBitRate;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

public class TelloBinarySetSticks extends TelloBinaryCommand{



    //                                                                       crc    typ  cmdL  cmdH  seqL  seqH  rateL  crc
    private static final byte[] bytes = new byte[] {(byte) 0xcc, (byte) 0xb0, 0x00, 0x7f, 0x60, 0x50, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x12, 0x16, 0x01, 0x0e, 0x00, 0x25, 0x54 };

    public TelloBinarySetSticks() {
        
    }
    @Override
    public byte[] serializeCommand() {
        
        short fRx = 0, fRy = 0, fLy = 0, fLx = 0, speed = 0;

        short axis1 = (short)(660.0F * fRx + 1024.0F);//RightX center=1024 left =364 right =-364
        short axis2 = (short)(660.0F * fRy + 1024.0F);//RightY down =364 up =-364
        short axis3 = (short)(660.0F * fLy + 1024.0F);//LeftY down =364 up =-364
        short axis4 = (short)(660.0F * fLx + 1024.0F);//LeftX left =364 right =-364
        short axis5 = (short)(660.0F * speed + 1024.0F);//Speed. 

        if (speed > 0.1f)
            axis5 = 0x7fff;

        long packedAxis = ((long)axis1 & 0x7FF) | (((long)axis2 & 0x7FF) << 11) | ((0x7FF & (long)axis3) << 22) | ((0x7FF & (long)axis4) << 33) | ((long)axis5 << 44);
        bytes[9] = ((byte)(int)(0xFF & packedAxis));
        bytes[10] = ((byte)(int)(packedAxis >> 8 & 0xFF));
        bytes[11] = ((byte)(int)(packedAxis >> 16 & 0xFF));
        bytes[12] = ((byte)(int)(packedAxis >> 24 & 0xFF));
        bytes[13] = ((byte)(int)(packedAxis >> 32 & 0xFF));
        bytes[14] = ((byte)(int)(packedAxis >> 40 & 0xFF));

        //Add time info.		
        Date now = new Date();
        bytes[15] = (byte)now.getHours();
        bytes[16] = (byte)now.getMinutes();
        bytes[17] = (byte)now.getSeconds();
        bytes[18] = (byte)(now.getTime() & 0xff);
        bytes[19] = (byte)(now.getTime() >> 8);
        
        return fixupPacket(bytes);
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
