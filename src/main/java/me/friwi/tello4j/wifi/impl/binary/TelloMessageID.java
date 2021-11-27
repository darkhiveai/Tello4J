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
    Connect             , // 1
    @BoundEnumOption(0x0002)
    Connected           , // 2
    @BoundEnumOption(0x0011)
    QuerySSID           , // 17
    @BoundEnumOption(0x0012)
    SetSSID             , // 18
    @BoundEnumOption(0x0013)
    QuerySSIDPass       , // 19
    @BoundEnumOption(0x0014)
    SetSSIDPass         , // 20
    @BoundEnumOption(0x0015)
    QueryWifiRegion     , // 21
    @BoundEnumOption(0x0016)
    SetWifiRegion       , // 22
    @BoundEnumOption(0x001a)
    WifiStrength        , // 26
    @BoundEnumOption(0x0020)
    SetVideoBitrate     , // 32
    @BoundEnumOption(0x0021)
    SetDynAdjRate       , // 33
    @BoundEnumOption(0x0024)
    EisSetting          , // 36
    @BoundEnumOption(0x0025)
    QueryVideoSPSPPS    , // 37
    @BoundEnumOption(0x0028)
    QueryVideoBitrate   , // 40
    @BoundEnumOption(0x0030)
    DoTakePic           , // 48
    @BoundEnumOption(0x0031)
    SwitchPicVideo      , // 49
    @BoundEnumOption(0x0032)
    DoStartRec          , // 50
    @BoundEnumOption(0x0034)
    ExposureVals        , // 52 (Get or set?)
    @BoundEnumOption(0x0035)
    LightStrength       , // 53
    @BoundEnumOption(0x0037)
    QueryJPEGQuality    , // 55
    @BoundEnumOption(0x0043)
    Error1              , // 67
    @BoundEnumOption(0x0044)
    Error2              , // 68
    @BoundEnumOption(0x0045)
    QueryVersion        , // 69
    @BoundEnumOption(0x0046)
    SetDateTime         , // 70
    @BoundEnumOption(0x0047)
    QueryActivationTime , // 71
    @BoundEnumOption(0x0049)
    QueryLoaderVersion  , // 73
    @BoundEnumOption(0x0050)
    SetStick            , // 80
    @BoundEnumOption(0x0054)
    DoTakeoff           , // 84
    @BoundEnumOption(0x0055)
    DoLand              , // 85
    @BoundEnumOption(0x0056)
    FlightStatus        , // 86
    @BoundEnumOption(0x0058)
    SetHeightLimit      , // 88
    @BoundEnumOption(0x005c)
    DoFlip              , // 92
    @BoundEnumOption(0x005d)
    DoThrowTakeoff      , // 93
    @BoundEnumOption(0x005e)
    DoPalmLand          , // 94
    @BoundEnumOption(0x0062)
    FileSize            , // 98
    @BoundEnumOption(0x0063)
    FileData            , // 99
    @BoundEnumOption(0x0064)
    FileDone            , // 100
    @BoundEnumOption(0x0080)
    DoSmartVideo        , // 128
    @BoundEnumOption(0x0081)
    SmartVideoStatus    , // 129
    @BoundEnumOption(0x1050)
    LogHeader           , // 4176
    @BoundEnumOption(0x1051)
    LogData             , // 4177
    @BoundEnumOption(0x1052)
    LogConfig           , // 4178
    @BoundEnumOption(0x1053)
    DoBounce            , // 4179
    @BoundEnumOption(0x1054)
    DoCalibration       , // 4180
    @BoundEnumOption(0x1055)
    SetLowBattThresh    , // 4181
    @BoundEnumOption(0x1056)
    QueryHeightLimit    , // 4182
    @BoundEnumOption(0x1057)
    QueryLowBattThresh  , // 4183
    @BoundEnumOption(0x1058)
    SetAttitude         , // 4184
    @BoundEnumOption(0x1059)
    QueryAttitude        // 4185)

//    private static final Map<Byte, TelloMessageID> typesByValue = new HashMap<Byte, TelloMessageID>();
//
//
//
//    static {
//        for (TelloMessageID type : TelloMessageID.values()) {
//            typesByValue.put(type.value, type);
//        }
//    }
//
//    private final byte value;
//    TelloMessageID(byte val) {
//        this.value = val;
//    }
//
//    byte value() { return value; }
//    public static TelloMessageID forValue(byte value) {
//        return TelloMessageID.typesByValue.get(value);
//    }

}
