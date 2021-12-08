/*
 * Copyright 2021 Darkhive, Inc. All Rights Reserved.
 */

package me.friwi.tello4j.wifi.impl.video;

import com.google.common.util.concurrent.RateLimiter;

public abstract class TelloFrameGrabberThread extends Thread {
    protected final RateLimiter rateLimiter = RateLimiter.create(5); // rate = 5000 permits per second
    protected TelloVideoThread videoThread;

    public void setTelloVideoThread(TelloVideoThread videoThread){
        this.videoThread = videoThread;
    }

    public void decode(byte[] copyOf) {
    }
}
