package me.friwi.tello4j.wifi.impl.binary;


import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class PacketTest {

    File PCAP_FILE = new File(getClass().getClassLoader().getResource("control_binary_received.pcap").getFile());
    String PACKET0 = "636f6e6e5f61636b3a672b"; //conn_ack
    String PACKET1 = "cc1801b988560065010000000000000000000000004f0000e70f0006000000000025af"; //?
    String PACKET2 = "cc680051881a0025005a00b5b4"; //?
    String PACKET3 = "cc1801b988560068010000000000000000000000004f0000e70f00060000000000e015"; //?
    String PACKET4 = "cc7008098850106b0193420027000000030000002a000000000000004255494c44204d617920203720323031392031323a30323a313100000000000046c3230007000000000365011b1b1d6c6c656517656705135555aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa5555555555555555555555555555555555555555555555555555555555555555559c0700007e01000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000444a495f4c4f475f56339ab9bccc972f";

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    @Before
    public void setUp() {
    }

    @Test
    public void parserTest() throws DecodingException, IOException {


        Codec<TelloPacket> codec = Codecs.create(TelloPacket.class);

        Codec<TelloHeader> headerCodec = Codecs.create(TelloHeader.class);

        TelloHeader headerSample = new TelloHeader();
        headerSample.packetLengthBits = 280;
        byte[] crcCheck = Codecs.encode(headerSample, headerCodec);
        short crc8 = TelloPacket.crc8(crcCheck);
        byte[] data = hexStringToByteArray(PACKET1);
        int crc16 = TelloPacket.crc16(Arrays.copyOf(data, data.length-2));
        TelloPacket packet = Codecs.decode(codec, data);
        packet = Codecs.decode(codec, hexStringToByteArray(PACKET2));
        packet = Codecs.decode(codec, hexStringToByteArray(PACKET4));
        System.out.println(packet);

    }


    @Test
    public void testPacketBuild() throws IOException {

        Codec<TelloPacket> codec = Codecs.create(TelloPacket.class);

        TelloPacket packet = new TelloPacket();

        packet.body.packetType = new PacketType();
        packet.body.packetType.packetType = PacketTypeValues.DATA1;
        packet.body.messageID = TelloMessageID.Connect;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Codecs.encode(packet, codec, out);

        System.out.println(out);


        Codec<ConnectRequest> codec2 = Codecs.create(ConnectRequest.class);
        ConnectRequest request = new ConnectRequest();
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        Codecs.encode(request, codec2, out2);

        System.out.println(out2);


    }
}