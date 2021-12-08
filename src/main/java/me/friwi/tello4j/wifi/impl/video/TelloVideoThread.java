/*
 * Copyright 2020 Fritz Windisch
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package me.friwi.tello4j.wifi.impl.video;

import me.friwi.tello4j.api.exception.TelloException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.wifi.impl.network.TelloTextCommandConnection;
import me.friwi.tello4j.wifi.model.TelloSDKValues;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static me.friwi.tello4j.wifi.model.TelloSDKValues.STREAM_DEFAULT_PACKET_SIZE;

public class TelloVideoThread extends Thread {
    public boolean running = true;
    TelloVideoQueue queue;
    public PipedInputStream pis = new PipedInputStream();
    private TelloTextCommandConnection connection;
    private DatagramSocket ds;
    private List<byte[]> currentFrame = new LinkedList<>();
    private PipedOutputStream pos;

    private boolean streamAligned = false;
    private byte[] buf = new byte[2048];
    private final TelloFrameGrabberThread frameGrabberThread;

    private boolean started = false;
    private int videoOffset = 0;

    public TelloVideoThread(TelloTextCommandConnection connection, TelloFrameGrabberThread frameGrabberThread) throws TelloNetworkException {
        this.connection = connection;
        this.frameGrabberThread = frameGrabberThread;
        this.queue = new TelloVideoQueue(this);
        try {
            this.pos = new PipedOutputStream(pis);
        } catch (IOException e) {
            throw new TelloNetworkException("Error on constructing video stream", e);
        }
    }

    public void connect() throws TelloNetworkException {
        try {
            ds = new DatagramSocket(TelloSDKValues.STREAM_PORT, InetAddress.getByName(TelloSDKValues.COMMANDER_IP_DST));
            ds.setSoTimeout(TelloSDKValues.VIDEO_SOCKET_TIMEOUT);
        } catch (Exception e) {
            throw new TelloNetworkException("Error while creating stream receive socket", e);
        }
    }

    public void run() {
        //TODO
//        queue.start();
        frameGrabberThread.start();
        setName("Stream-Thread");
        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                ds.receive(packet);
//                packet.getOffset()
                handleInput(buf, packet.getLength());
            } catch (Exception e) {
                //Ignore missing updates - no way to error them
                //Disconnect at end of program is also intended to end here
            }
        }
        try {
            pos.close(); //This will shut down the frame grabber as well
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    byte[] videoFrame = new byte[100 * 1024];
    private void handleInput(byte[] data, int length) throws TelloException {
        if (!streamAligned) {
            if (length != STREAM_DEFAULT_PACKET_SIZE) streamAligned = true;
            return;
        }


//        frameGrabberThread.decode(data);
        if (data[2] == 0 && data[3] == 0 && data[4] == 0 && data[5] == 1)//if nal
        {
            int nalType = data[6] & 0x1f;
            if (nalType == 7 || nalType == 8)
            {

            }
            if (videoOffset > 0)
            {
                if (true)//surfaces are lost when paused.
                {
                    //aTello.Video.Decoder.decode(videoFrame.Take(videoOffset).ToArray());


                    frameGrabberThread.decode(Arrays.copyOf(videoFrame, videoOffset));
                }
                videoOffset = 0;
            }
            //var nal = (received.bytes[6] & 0x1f);
            //if (nal != 0x01 && nal != 0x07 && nal != 0x08 && nal != 0x05)
            //    Console.WriteLine("NAL type:" + nal);
        }
        //todo. resquence frames.
        System.arraycopy(data, 2, videoFrame, videoOffset, length - 2);
        videoOffset += (length - 2);

    }

    private void handleInput2(byte[] bytes, int length) throws TelloException {
//        if (!streamAligned) {
//            if (length != STREAM_DEFAULT_PACKET_SIZE) streamAligned = true;
//            return;
//        }



        if (bytes[2] == 0 && bytes[3] == 0 && bytes[4] == 0 && bytes[5] == 1)//Wait for first NAL
        {
            int nal = (bytes[6] & 0x1f);
            //if (nal != 0x01 && nal!=0x07 && nal != 0x08 && nal != 0x05)
            //    Console.WriteLine("NAL type:" +nal);
            started = true;
        }
        if (started) {
            try {
                pos.write(bytes, 0, length);
                pos.flush();
            } catch (IOException e) {
                throw new TelloNetworkException("Error while pushing data", e);
            }
        }
        videoOffset = 0;
//            }

        //todo. resquence frames.
//        Arrays.copyOf(bytes, 2, videoFrame, videoOffset, data.Length - 2);
//        videoOffset += (data.Length - 2);

    }

    public void kill() {
        running = false;
        //TODO
//        queue.kill();
        ds.close();
    }

    public TelloTextCommandConnection getConnection() {
        return connection;
    }
}
