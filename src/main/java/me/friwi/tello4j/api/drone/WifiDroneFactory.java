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

package me.friwi.tello4j.api.drone;

import me.friwi.tello4j.wifi.impl.WifiBinaryDrone;
import me.friwi.tello4j.wifi.impl.WifiTextDrone;
import me.friwi.tello4j.wifi.model.PacketMode;

import static me.friwi.tello4j.wifi.model.PacketMode.*;
import static me.friwi.tello4j.wifi.model.PacketMode.BINARY;

/**
 * A factory to create a new {@link TelloDrone}, which is connected by wifi
 *
 * @author Fritz Windisch
 */
public class WifiDroneFactory {
    /**
     * Builds a new {@link TelloDrone}, which is connected by wifi
     *
     * @return new {@link TelloDrone}
     */
    public TelloDrone build(PacketMode packetMode) {
        switch (packetMode) {
           case BINARY:
               return new WifiBinaryDrone();
           case TEXT:
            default:
                return new WifiTextDrone();
        }

    }
}
