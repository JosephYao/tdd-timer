package com.odde.timer;

import java.time.Clock;
import java.time.Instant;

public class Timer {
    private static final int SECOND_TO_TICK = 1;
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
            if (isNextTick(startTime)) {
                runnable.run();
                currentCountDown--;
                startTime = nextTick(startTime);
            }
        }
    }

    private Instant nextTick(Instant startTime) {
        return startTime.plusSeconds(SECOND_TO_TICK);
    }

    private boolean isNextTick(Instant startTime) {
        return clock.instant().minusSeconds(SECOND_TO_TICK).equals(startTime);
    }
}
