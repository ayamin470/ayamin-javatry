package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * @author ayamin
 */
public class SystemClockProvider implements ClockProvider {
    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}
