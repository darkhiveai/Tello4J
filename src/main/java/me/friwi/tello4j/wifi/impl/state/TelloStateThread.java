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

package me.friwi.tello4j.wifi.impl.state;

import me.friwi.tello4j.api.exception.TelloException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.state.StateListener;
import me.friwi.tello4j.api.state.TelloDroneState;
import me.friwi.tello4j.wifi.impl.binary.TelloPacket;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinaryConnectRequest;
import me.friwi.tello4j.wifi.impl.network.TelloBinaryCommandConnection;
import me.friwi.tello4j.wifi.impl.network.TelloTextCommandConnection;
import me.friwi.tello4j.wifi.model.TelloSDKValues;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class TelloStateThread extends Thread {
    private boolean running = true;
    private TelloTextCommandConnection connection;
    private DatagramSocket ds;
    private static final Codec<TelloPacket> codec = Codecs.create(TelloPacket.class);

    public TelloStateThread(TelloTextCommandConnection connection) {
        this.connection = connection;
    }


    public void connect() throws TelloNetworkException {

        try {

            DatagramSocket socket = new DatagramSocket(TelloSDKValues.STATE_PORT, InetAddress.getByName(TelloSDKValues.COMMANDER_IP_DST));
            socket.setSoTimeout(TelloSDKValues.STATE_SOCKET_TIMEOUT);
            connect(socket);
        } catch (Exception e) {
            throw new TelloNetworkException("Error while creating state receive socket", e);
        }
    }
    public void connect(DatagramSocket socket) throws TelloNetworkException {
            ds = socket;
    }

    public void run() {
        setName("State-Thread");
        while (running) {
            try {
                byte[] buf = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                ds.receive(packet);
                handleInput(Arrays.copyOf(buf, packet.getLength()));
            } catch (Exception e) {
                //Ignore missing updates - no way to error them
                //Disconnect at end of program is also intended to end here
            }
        }
    }

    private void handleInput(byte[] bytes) throws TelloException {
        try {
            if(connection instanceof TelloBinaryCommandConnection)
            {
                TelloPacket packet = Codecs.decode(codec, bytes);
                this.handleInput(packet);
            } else {
                this.handleInput(new String(bytes, "UTF-8"));
            }

        } catch (UnsupportedEncodingException e) {
            throw new TelloNetworkException("Your system does not support utf-8", e);
        } catch (DecodingException e) {
            throw new TelloNetworkException("Error parsing binary encoding", e);
        }
    }

    private void handleInput(TelloPacket packet) {



    }

    private void handleInput(String s) throws TelloException {
        if (TelloSDKValues.DEBUG) System.out.println("[STE] " + s.trim());
        TelloDroneState state = TelloStateDeserializer.deserialize(s);
        TelloDroneState old = this.connection.getDrone().getCachedState();
        this.connection.getDrone().setCachedState(state);
        for (StateListener listener : this.connection.getDrone().getStateListeners()) {
            listener.onStateChanged(old, state);
        }
    }

    public void kill() {
        running = false;
        ds.close();
    }
}
