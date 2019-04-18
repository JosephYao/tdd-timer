package com.odde.timer;

import org.junit.jupiter.api.Test;

import java.time.Clock;

import static java.time.Instant.parse;
import static java.time.ZoneOffset.UTC;

public class TimerTest {

    @Test
    public void should_not_call_callback_when_count_down_is_0() {
        Clock stubClock = Clock.fixed(parse("2018-11-01T18:30:00Z"), UTC);
        Timer runner = new Timer(0, stubClock);

        MockRunnable mockRunnable = new MockRunnable();
        runner.start(mockRunnable);

        mockRunnable.verifyRunWithCount(0);
    }
}
