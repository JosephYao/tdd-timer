package com.odde.timer;

import java.time.Clock;
import java.time.Instant;

public class Timer {
    private final Clock clock;
    private Instant startTime;
    private int countDownSecond;

    public Timer(int countDownSecond, Clock clock) {
        this.countDownSecond = countDownSecond;
        this.clock = clock;
        startTime = clock.instant();
    }

    public void start(Runnable runnable) {
        while (countDownSecond > 0 && clock.instant().minusSeconds(1).equals(startTime)) {
            runnable.run();
            countDownSecond--;
            startTime = startTime.plusSeconds(1);
        }
    }
}
