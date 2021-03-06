package com.odde.timer;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class StubClock extends Clock {
    private Instant[] ticks = {};
    private int index = 0;

    @Override
    public ZoneId getZone() {
        return null;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        return ticks[index++];
    }

    public void setTicks(Instant... ticks) {
        this.ticks = ticks;
    }
}
