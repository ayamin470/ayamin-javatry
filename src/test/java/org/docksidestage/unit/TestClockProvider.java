// src/test/java/org/docksidestage/unit/TestClockProvider.java
package org.docksidestage.unit;

import java.time.LocalTime;
import org.docksidestage.bizfw.basic.buyticket.ClockProvider;

/**
 * テスト用に固定の時刻を返すClockProvider
 */
public class TestClockProvider implements ClockProvider {
    private final LocalTime fixedTime;

    public TestClockProvider(LocalTime fixedTime) {
        this.fixedTime = fixedTime;
    }

    @Override
    public LocalTime getCurrentTime() {
        return fixedTime;
    }
}
