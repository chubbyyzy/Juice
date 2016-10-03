package com.tribeofspirit.external.sightcorp.entity;

/**
 * Created by tihong on 10/30/15.
 */
public class Head {

    private RotateType yaw;
    private RotateType roll;
    private RotateType pitch;

    public class RotateType{
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public RotateType getYaw() {
        return yaw;
    }

    public void setYaw(RotateType yaw) {
        this.yaw = yaw;
    }

    public RotateType getRoll() {
        return roll;
    }

    public void setRoll(RotateType roll) {
        this.roll = roll;
    }

    public RotateType getPitch() {
        return pitch;
    }

    public void setPitch(RotateType pitch) {
        this.pitch = pitch;
    }
}
