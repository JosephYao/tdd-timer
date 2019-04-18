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
    public void should_call_callback_after_more_than_seconds() {
        givenClockWithTicks(millisFromNow(0), millisFromNow(1000), millisFromNow(2000));

        timerWithCountDownSecond(2).start(mockRunnable);

        mockRunnable.verifyRunWithCount(2);
    }

    @Test
    public void should_call_callback_until_one_second_arrives() {
        givenClockWithTicks(millisFromNow(0), millisFromNow(500), millisFromNow(1000));

        timerWithCountDownSecond(1).start(mockRunnable);

        mockRunnable.verifyRunWithCount(1);
    }

    @Test
    public void should_be_able_to_start_more_than_once() {
        givenClockWithTicks(millisFromNow(0), millisFromNow(1000), millisFromNow(2000), millisFromNow(3000));

        Timer timer = timerWithCountDownSecond(1);
        timer.start(mockRunnable);
        MockRunnable anotherMockRunnable = new MockRunnable();
        timer.start(anotherMockRunnable);

        anotherMockRunnable.verifyRunWithCount(1);
    }

    @Test
    public void acceptance_test() {
        new Timer(5).start(mockRunnable);

        mockRunnable.verifyRunWithCount(5);
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
