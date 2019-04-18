package com.odde.timer;

import java.time.Clock;

public class Timer {
    private int countDownSecond;

    public Timer(int countDownSecond, Clock clock) {
        this.countDownSecond = countDownSecond;
    }

    public void start(Runnable runnable) {
        while (countDownSecond-- > 0) {
            runnable.run();
        }
    }
}
