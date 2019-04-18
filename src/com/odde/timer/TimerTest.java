package com.odde.timer;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;

import static java.time.Instant.parse;
import static java.time.ZoneOffset.UTC;

public class TimerTest {

    Clock stubClock = Clock.fixed(parse("2018-11-01T18:30:00Z"), UTC);
    MockRunnable mockRunnable = new MockRunnable();

    @Test
    public void should_not_call_callback_when_count_down_is_0() {
        Timer runner = new Timer(0, stubClock);

        runner.start(mockRunnable);

        mockRunnable.verifyRunWithCount(0);
    }

    @Test
    public void should_call_callback_after_one_second() {
        Timer runner = new Timer(1, stubClock);

        runner.start(mockRunnable);
        Clock.offset(stubClock, Duration.ofSeconds(1));

        mockRunnable.verifyRunWithCount(1);
    }

}
