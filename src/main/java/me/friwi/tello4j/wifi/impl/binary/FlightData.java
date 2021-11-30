package me.friwi.tello4j.wifi.impl.binary;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;

public class FlightData implements TelloMessage{

    //byte 0 - 9
    @BoundNumber
    public short height;
    @BoundNumber
    public short north_speed;
    @BoundNumber
    public short east_speed;
    @BoundNumber
    public short ground_speed;
    @BoundNumber
    public short fly_time;

    //byte 10
    @Bound
    public boolean imu_state;
    @Bound
    public boolean pressure_state;
    @Bound
    public boolean down_visual_state;
    @Bound
    public boolean power_state;
    @Bound
    public boolean battery_state;
    @Bound
    public boolean gravity_state;
    @Bound
    public boolean unused_bit1;
    @Bound
    public boolean wind_state;

    //byte 11-16
    @BoundNumber
    public byte imu_calibration_state;
    @BoundNumber(size="8")
    public short battery_percentage;
    @BoundNumber
    public short drone_battery_left;
    @BoundNumber
    public short drone_fly_time_left;

    //Byte 17
    @Bound
    public boolean em_sky;
    @Bound
    public boolean em_ground;
    @Bound
    public boolean em_open;
    @Bound
    public boolean drone_hover;
    @Bound
    public boolean outage_recording;
    @Bound
    public boolean battery_low;
    @Bound
    public boolean battery_lower;
    @Bound
    public boolean factory_mode;

    //byte 18 - 21
    @BoundNumber
    public byte fly_mode;
    @BoundNumber
    public byte throw_fly_timer;
    @BoundNumber
    public byte camera_state;
    @BoundNumber
    public byte electrical_machinery_state;


    //byte 22
    @Bound
    public boolean front_in;
    @Bound
    public boolean front_out;
    @Bound
    public boolean front_lsc;
    @BoundNumber(size="5")
    public byte unused1;

    //Byte 23
    @Bound
    public boolean temperature_height_error_state;
    @BoundNumber(size="7")
    public byte unused2;

}
