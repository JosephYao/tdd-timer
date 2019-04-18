package com.odde.timer;

import java.time.Clock;
import java.time.Instant;

public class Timer {
    private final Clock clock;
    private final int countDownSecond;

    public Timer(int countDownSecond, Clock clock) {
        this.countDownSecond = countDownSecond;
        this.clock = clock;
    }

    public Timer(int countDownSecond) {
        this(countDownSecond, Clock.systemUTC());
    }

    public void start(Runnable runnable) {
        Instant startTime = clock.instant();
        int currentCountDown = countDownSecond;
        while (currentCountDown > 0) {
            if (clock.instant().minusSeconds(1).equals(startTime)) {
                runnable.run();
                currentCountDown--;
                startTime = startTime.plusSeconds(1);
            }
        }
    }
}
