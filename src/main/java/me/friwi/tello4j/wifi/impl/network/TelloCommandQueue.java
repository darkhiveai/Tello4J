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

package me.friwi.tello4j.wifi.impl.network;

import me.friwi.tello4j.api.exception.*;
import me.friwi.tello4j.wifi.impl.command.set.RemoteControlCommand;
import me.friwi.tello4j.wifi.model.TelloSDKValues;
import me.friwi.tello4j.wifi.model.command.TelloCommand;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TelloCommandQueue extends Thread {
    private ConcurrentLinkedQueue<TelloCommand> queue = new ConcurrentLinkedQueue<>();
    private boolean running = true;
    private TelloTextCommandConnection connection;

    TelloCommandQueue(TelloTextCommandConnection connection) {
        this.connection = connection;
    }

    public synchronized void run() {
        setName("Command-Queue");
        while (running) {
            TelloCommand cmd = queue.poll();
            if (cmd != null) {
                try {
                    this.connection.send(cmd.serializeCommand());


                    if (connection instanceof TelloTextCommandConnection)
                        processTextMessages(cmd);
                    else
                        processBinaryMessages(cmd);

                    synchronized (cmd) {
                        cmd.notifyAll();
                    }
                } catch (TelloException e) {
                    cmd.setException(e);
                    synchronized (cmd) {
                        cmd.notifyAll();
                    }
                } catch (UnsupportedEncodingException e) {
                    //TODO
                    e.printStackTrace();
                }
            } else {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace(); //Unlikely
                }
            }
        }


    }



    private void processBinaryMessages(TelloCommand cmd) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, UnsupportedEncodingException, TelloGeneralCommandException, TelloNoValidIMUException {
        //Read response, or assume ok with the remote control command
        String data = cmd instanceof RemoteControlCommand ? "ok" : this.connection.readString().trim();
        int attempt = 0;
        boolean invalid;
        do {
            //first go round is "conn_ack"
                //then we are connected
                //if connected send if video enabled, encoder rate, and start video
            //then we need to process all packets from the socket based on deserializing them...

            //if == start of packet... begin parsing.


            invalid = !data.startsWith("conn_ack");
            if (!TelloSDKValues.COMMAND_REPLY_PATTERN.matcher(data).matches()) invalid = true;
            if (invalid && TelloSDKValues.DEBUG) {
                System.err.println("Dropping reply \"" + data + "\" as it might be binary");
            }
            attempt++;
            if (invalid && attempt >= TelloSDKValues.COMMAND_SOCKET_BINARY_ATTEMPTS) {
                throw new TelloNetworkException("Too many binary messages received after sending command. Broken connection?");
            }
            if (invalid) {
                data = this.connection.readString().trim();
            }
        } while (invalid);
        TelloResponse response = cmd.buildResponse(data);
        cmd.setResponse(response);

    }

    private void processTextMessages(TelloCommand cmd) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, UnsupportedEncodingException, TelloGeneralCommandException, TelloNoValidIMUException {
        //Read response, or assume ok with the remote control command
        String data = cmd instanceof RemoteControlCommand ? "ok" : this.connection.readString().trim();
        int attempt = 0;
        boolean invalid;
        do {
            invalid = data.startsWith("conn_ack");
            if (!TelloSDKValues.COMMAND_REPLY_PATTERN.matcher(data).matches()) invalid = true;
            if (invalid && TelloSDKValues.DEBUG) {
                System.err.println("Dropping reply \"" + data + "\" as it might be binary");
            }
            attempt++;
            if (invalid && attempt >= TelloSDKValues.COMMAND_SOCKET_BINARY_ATTEMPTS) {
                throw new TelloNetworkException("Too many binary messages received after sending command. Broken connection?");
            }
            if (invalid) {
                data = this.connection.readString().trim();
            }
        } while (invalid);
        TelloResponse response = cmd.buildResponse(data);
        cmd.setResponse(response);
    }

    synchronized void queueCommand(TelloCommand cmd) {
        queue.add(cmd);
        this.notifyAll();
    }

    synchronized void kill() {
        running = false;
        this.notifyAll();
    }
}
