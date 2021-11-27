package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.BoundEnumOption;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * Based on https://tellopilots.com/wiki/protocol/#PacketTypeInfo
 */
public enum TelloMessageID {
    @BoundEnumOption(0x0001)
    Connect             ((byte) 0x0001), // 1
    @BoundEnumOption(0x0002)
    Connected           ((byte) 0x0002), // 2
    @BoundEnumOption(0x0011)
    QuerySSID           ((byte) 0x0011), // 17
    @BoundEnumOption(0x0012)
    SetSSID             ((byte) 0x0012), // 18
    @BoundEnumOption(0x0013)
    QuerySSIDPass       ((byte) 0x0013), // 19
    @BoundEnumOption(0x0014)
    SetSSIDPass         ((byte) 0x0014), // 20
    @BoundEnumOption(0x0015)
    QueryWifiRegion     ((byte) 0x0015), // 21
    @BoundEnumOption(0x0016)
    SetWifiRegion       ((byte) 0x0016), // 22
    @BoundEnumOption(0x001a)
    WifiStrength        ((byte) 0x001a), // 26
    @BoundEnumOption(0x0020)
    SetVideoBitrate     ((byte) 0x0020), // 32
    @BoundEnumOption(0x0021)
    SetDynAdjRate       ((byte) 0x0021), // 33
    @BoundEnumOption(0x0024)
    EisSetting          ((byte) 0x0024), // 36
    @BoundEnumOption(0x0025)
    QueryVideoSPSPPS    ((byte) 0x0025), // 37
    @BoundEnumOption(0x0028)
    QueryVideoBitrate   ((byte) 0x0028), // 40
    @BoundEnumOption(0x0030)
    DoTakePic           ((byte) 0x0030), // 48
    @BoundEnumOption(0x0031)
    SwitchPicVideo      ((byte) 0x0031), // 49
    @BoundEnumOption(0x0032)
    DoStartRec          ((byte) 0x0032), // 50
    @BoundEnumOption(0x0034)
    ExposureVals        ((byte) 0x0034), // 52 (Get or set?)
    @BoundEnumOption(0x0035)
    LightStrength       ((byte) 0x0035), // 53
    @BoundEnumOption(0x0037)
    QueryJPEGQuality    ((byte) 0x0037), // 55
    @BoundEnumOption(0x0043)
    Error1              ((byte) 0x0043), // 67
    @BoundEnumOption(0x0044)
    Error2              ((byte) 0x0044), // 68
    @BoundEnumOption(0x0045)
    QueryVersion        ((byte) 0x0045), // 69
    @BoundEnumOption(0x0046)
    SetDateTime         ((byte) 0x0046), // 70
    @BoundEnumOption(0x0047)
    QueryActivationTime ((byte) 0x0047), // 71
    @BoundEnumOption(0x0049)
    QueryLoaderVersion  ((byte) 0x0049), // 73
    @BoundEnumOption(0x0050)
    SetStick            ((byte) 0x0050), // 80
    @BoundEnumOption(0x0054)
    DoTakeoff           ((byte) 0x0054), // 84
    @BoundEnumOption(0x0055)
    DoLand              ((byte) 0x0055), // 85
    @BoundEnumOption(0x0056)
    FlightStatus        ((byte) 0x0056), // 86
    @BoundEnumOption(0x0058)
    SetHeightLimit      ((byte) 0x0058), // 88
    @BoundEnumOption(0x005c)
    DoFlip              ((byte) 0x005c), // 92
    @BoundEnumOption(0x005d)
    DoThrowTakeoff      ((byte) 0x005d), // 93
    @BoundEnumOption(0x005e)
    DoPalmLand          ((byte) 0x005e), // 94
    @BoundEnumOption(0x0062)
    FileSize            ((byte) 0x0062), // 98
    @BoundEnumOption(0x0063)
    FileData            ((byte) 0x0063), // 99
    @BoundEnumOption(0x0064)
    FileDone            ((byte) 0x0064), // 100
    @BoundEnumOption(0x0080)
    DoSmartVideo        ((byte) 0x0080), // 128
    @BoundEnumOption(0x0081)
    SmartVideoStatus    ((byte) 0x0081), // 129
    @BoundEnumOption(0x1050)
    LogHeader           ((byte) 0x1050), // 4176
    @BoundEnumOption(0x1051)
    LogData             ((byte) 0x1051), // 4177
    @BoundEnumOption(0x1052)
    LogConfig           ((byte) 0x1052), // 4178
    @BoundEnumOption(0x1053)
    DoBounce            ((byte) 0x1053), // 4179
    @BoundEnumOption(0x1054)
    DoCalibration       ((byte) 0x1054), // 4180
    @BoundEnumOption(0x1055)
    SetLowBattThresh    ((byte) 0x1055), // 4181
    @BoundEnumOption(0x1056)
    QueryHeightLimit    ((byte) 0x1056), // 4182
    @BoundEnumOption(0x1057)
    QueryLowBattThresh  ((byte) 0x1057), // 4183
    @BoundEnumOption(0x1058)
    SetAttitude         ((byte) 0x1058), // 4184
    @BoundEnumOption(0x1059)
    QueryAttitude       ((byte) 0x1059); // 4185)

    private static final Map<Byte, TelloMessageID> typesByValue = new HashMap<Byte, TelloMessageID>();



    static {
        for (TelloMessageID type : TelloMessageID.values()) {
            typesByValue.put(type.value, type);
        }
    }

    private final byte value;
    TelloMessageID(byte val) {
        this.value = val;
    }

    byte value() { return value; }
    public static TelloMessageID forValue(byte value) {
        return TelloMessageID.typesByValue.get(value);
    }

}
