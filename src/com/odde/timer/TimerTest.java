package com.odde.timer;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class TimerTest {

    StubClock stubClock = new StubClock();
    MockRunnable mockRunnable = new MockRunnable();

    @Test
    public void should_not_call_callback_when_count_down_is_0() {
        givenClockWithTicks(millisFromNow(0), millisFromNow(1000));

        timerWithCountDownSecond(0).start(mockRunnable);

        mockRunnable.verifyRunWithCount(0);
    }

    @Test
    public void should_call_callback_after_one_second() {
        givenClockWithTicks(millisFromNow(0), millisFromNow(1000));

        timerWithCountDownSecond(1).start(mockRunnable);

        mockRunnable.verifyRunWithCount(1);
    }

    @Test
    public void should_not_call_callback_only_after_half_second() {
        givenClockWithTicks(millisFromNow(0), millisFromNow(500));

        timerWithCountDownSecond(1).start(mockRunnable);

        mockRunnable.verifyRunWithCount(0);
    }

    private Timer timerWithCountDownSecond(int second) {
        return new Timer(second, stubClock);
    }

    private void givenClockWithTicks(Instant... ticks) {
        stubClock.setTicks(ticks);
    }

    private Instant millisFromNow(long millis) {
        return Instant.parse("2018-11-01T18:30:00Z").plusMillis(millis);
    }

}
