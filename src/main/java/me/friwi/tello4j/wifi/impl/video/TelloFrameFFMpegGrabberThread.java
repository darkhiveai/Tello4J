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

package me.friwi.tello4j.wifi.impl.video;

import static org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_RGBA;

import me.friwi.tello4j.api.video.VideoListener;
import me.friwi.tello4j.wifi.model.TelloSDKValues;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import static me.friwi.tello4j.wifi.model.TelloSDKValues.VIDEO_HEIGHT;
import static me.friwi.tello4j.wifi.model.TelloSDKValues.VIDEO_WIDTH;

public class TelloFrameFFMpegGrabberThread extends TelloFrameGrabberThread {


    public TelloFrameFFMpegGrabberThread() {
        super();
        setName("Frame-Grabber");

    }

    public void run() {
        if (TelloSDKValues.DEBUG) avutil.av_log_set_level(avutil.AV_LOG_ERROR);
        else avutil.av_log_set_level(avutil.AV_LOG_FATAL);
        Java2DFrameConverter conv = new Java2DFrameConverter();
        CustomFFmpegFrameGrabber fg = new CustomFFmpegFrameGrabber(videoThread.pis);
        fg.setImageMode(FrameGrabber.ImageMode.COLOR);
        fg.setFormat("h264");
        fg.setFrameRate(30);
        fg.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        fg.setImageWidth(VIDEO_WIDTH);
        fg.setImageHeight(VIDEO_HEIGHT);
        fg.setPixelFormat(AV_PIX_FMT_RGBA);
        try {
            fg.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
            return;
        }
        Frame f;
        while (videoThread.running) {
            try {
                f = fg.grabImage();
                if (f != null) {
//                    TelloVideoFrame frame;
//                    switch (videoThread.getConnection().getDrone().getVideoExportType()) {
//                        case BUFFERED_IMAGE:
//                            frame = new TelloVideoFrame(conv.convert(f));
//                            break;
//                        case JAVACV_FRAME:
//                            frame = new TelloVideoFrame(f.clone());
//                            break;
//                        case BOTH:
//                        default:
//                            Frame cloned = f.clone();
//                            frame = new TelloVideoFrame(conv.convert(cloned), cloned);
//                    }
                    if (rateLimiter.tryAcquire(1)) {

                    for (VideoListener listener : this.videoThread.getConnection().getDrone().getVideoListeners()) {

                        listener.onFrameReceived(f.clone());
                        //TODO profile this to determine if more effective to queue
                        //videoThread.queue.queueFrame(f);
                    }
                    }
                }
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            fg.release();
            fg.close();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}
