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

package me.friwi.tello4j.wifi.impl;

import me.friwi.tello4j.api.drone.TelloDrone;
import me.friwi.tello4j.api.exception.*;
import me.friwi.tello4j.api.world.FlipDirection;
import me.friwi.tello4j.api.world.MovementDirection;
import me.friwi.tello4j.api.world.TurnDirection;
import me.friwi.tello4j.wifi.impl.binary.TelloVideoBitRate;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinaryConnectRequest;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinaryLand;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinarySetVideoBitRate;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinaryTakeoff;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinaryThrowAndGo;
import me.friwi.tello4j.wifi.impl.binary.command.TelloBinaryVideoStart;
import me.friwi.tello4j.wifi.impl.command.control.*;
import me.friwi.tello4j.wifi.impl.command.read.*;
import me.friwi.tello4j.wifi.impl.command.set.RemoteControlCommand;
import me.friwi.tello4j.wifi.impl.command.set.SetSpeedCommand;
import me.friwi.tello4j.wifi.impl.command.set.SetWifiPasswordAndSSIDCommand;
import me.friwi.tello4j.wifi.impl.network.TelloBinaryCommandConnection;
import me.friwi.tello4j.wifi.impl.network.TelloTextCommandConnection;
import me.friwi.tello4j.wifi.impl.response.TelloReadCommandResponse;
import me.friwi.tello4j.wifi.model.TelloSDKValues;
import me.friwi.tello4j.wifi.model.command.ReadCommand;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

public class WifiBinaryDrone extends TelloDrone {
    private TelloTextCommandConnection commandConnection;

    private boolean streaming = false;

    public WifiBinaryDrone() {
        this.commandConnection = new TelloBinaryCommandConnection(this);
    }

    @Override
    public void connect() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {
        this.connect(TelloSDKValues.DRONE_IP_DST);
    }

    @Override
    public void connect(String remoteAddr) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {
        this.commandConnection.connect(remoteAddr);
        //Enter SDK mode
        try {


            //send connect_request
            //receive Con_ack

            this.commandConnection.sendCommand(new TelloBinaryConnectRequest());
        } catch (TelloNoValidIMUException e) {
            //Will (hopefully) never happen
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        this.commandConnection.disconnect();
    }

    @Override
    public boolean isConnected() {
        return this.commandConnection.isConnected();
    }

    public void takeoff() throws TelloCommandTimedOutException, TelloCustomCommandException, TelloNetworkException, TelloGeneralCommandException {
        try {
            this.commandConnection.sendCommand(new TelloBinaryTakeoff());
        } catch (TelloNoValidIMUException e) {
            //Will (hopefully) never happen
            e.printStackTrace();
        }
    }

    @Override
    public void throwAndGo() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {
        try {
            this.commandConnection.sendCommand(new TelloBinaryThrowAndGo());
        } catch (TelloNoValidIMUException e) {
            //Will (hopefully) never happen
            e.printStackTrace();
        }
    }

    @Override
    public void setVideoBitRate(TelloVideoBitRate videoBitRate) throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        try {
            this.commandConnection.sendCommand(new TelloBinarySetVideoBitRate(videoBitRate));
        } catch (TelloNoValidIMUException e) {
            //Will (hopefully) never happen
            e.printStackTrace();
        }
    }

    public void land() throws TelloCommandTimedOutException, TelloCustomCommandException, TelloNetworkException, TelloGeneralCommandException {
        try {
            this.commandConnection.sendCommand(new TelloBinaryLand());
        } catch (TelloNoValidIMUException e) {
            //Will (hopefully) never happen
            e.printStackTrace();
        }
    }

    public boolean isStreaming() {
        return this.streaming;
    }

    public void setStreaming(boolean stream) throws TelloCommandTimedOutException, TelloCustomCommandException, TelloNetworkException, TelloGeneralCommandException {
        //Only notify drone on state change
        try {
            if (stream && !streaming) {
                this.commandConnection.sendCommand(new TelloBinaryVideoStart());
            } else if (!stream && streaming) {
                this.commandConnection.sendCommand(new StreamOffCommand());
            }
        } catch (TelloNoValidIMUException e) {
            //Will (hopefully) never happen
            e.printStackTrace();
        }
        //If state change successful, update streaming parameter
        this.streaming = stream;
    }

    @Override
    public void emergency() throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {

    }

    @Override
    public void moveDirection(MovementDirection direction, int cm) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloNoValidIMUException, TelloGeneralCommandException {

    }

    @Override
    public void turn(TurnDirection direction, int degrees) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloNoValidIMUException, TelloGeneralCommandException {

    }

    @Override
    public void flip(FlipDirection direction) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloNoValidIMUException, TelloGeneralCommandException {

    }

    @Override
    public void move(int x, int y, int z, int speed) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloNoValidIMUException, TelloGeneralCommandException {

    }

    @Override
    public void curve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloNoValidIMUException, TelloGeneralCommandException {

    }

    @Override
    public void setSpeed(int speed) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {

    }

    @Override
    public void sendRemoteControlInputs(int lr, int fb, int ud, int yaw) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {

    }

    @Override
    public void setWifiSSIDAndPassword(String ssid, String password) throws TelloNetworkException, TelloCommandTimedOutException, TelloCustomCommandException, TelloGeneralCommandException {

    }

    @Override
    public double fetchSpeed() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public int fetchBattery() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public int fetchMotorTime() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public int fetchHeight() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public int fetchTemperature() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public int[] fetchAttitude() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return new int[0];
    }

    @Override
    public double fetchBarometer() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public double[] fetchAcceleration() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return new double[0];
    }

    @Override
    public int fetchTOFDistance() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }

    @Override
    public int fetchWifiSnr() throws TelloCommandTimedOutException, TelloNetworkException, TelloCustomCommandException, TelloGeneralCommandException {
        return 0;
    }



}
