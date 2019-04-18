package com.odde.timer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockRunnable implements Runnable {
    private int runCount;

    @Override
    public void run() {
        runCount++;
    }

    public void verifyRunWithCount(int expected) {
        assertEquals(expected, runCount);
    }
}
