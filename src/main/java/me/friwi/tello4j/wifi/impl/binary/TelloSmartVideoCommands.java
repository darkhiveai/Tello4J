package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundEnumOption;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * Based on https://tellopilots.com/wiki/protocol/#PacketTypeInfo
 */
public enum TelloSmartVideoCommands {
    @BoundEnumOption(4)
    Sv360Stop(4),
    @BoundEnumOption(8)
    SvCircleStop(8),
    @BoundEnumOption(12)
    SvUpOutStop(12),
    @BoundEnumOption(5)
    Sv360Start(5),
    @BoundEnumOption(9)
    SvCircleStart(9),
    @BoundEnumOption(13)
    SvUpOutStart(13);

    private static final Map<Integer, TelloSmartVideoCommands> typesByValue = new HashMap<Integer, TelloSmartVideoCommands>();



    static {
        for (TelloSmartVideoCommands type : TelloSmartVideoCommands.values()) {
            typesByValue.put(type.value, type);
        }
    }

    private final int value;
    TelloSmartVideoCommands(int val) {
        this.value = val;
    }

    public int value() { return value; }
    public static TelloSmartVideoCommands forValue(int value) {
        return TelloSmartVideoCommands.typesByValue.get(value);
    }

}
