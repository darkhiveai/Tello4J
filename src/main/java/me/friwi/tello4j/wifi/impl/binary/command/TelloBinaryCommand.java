package me.friwi.tello4j.wifi.impl.binary.command;

import me.friwi.tello4j.api.exception.TelloCustomCommandException;
import me.friwi.tello4j.api.exception.TelloGeneralCommandException;
import me.friwi.tello4j.api.exception.TelloNetworkException;
import me.friwi.tello4j.api.exception.TelloNoValidIMUException;
import me.friwi.tello4j.wifi.model.command.TelloCommand;
import me.friwi.tello4j.wifi.model.response.TelloResponse;

import java.io.UnsupportedEncodingException;

public class TelloBinaryCommand extends TelloCommand {

    private byte[] command;

    public TelloBinaryCommand(byte[] command) {
        this.command = command;
    }


    @Override
    public byte[] serializeCommand() {
        return command;
    }

    @Override
    public TelloResponse buildResponse(String data) throws TelloGeneralCommandException, TelloNoValidIMUException, TelloCustomCommandException, TelloNetworkException, UnsupportedEncodingException {
        return null;
    }
}
