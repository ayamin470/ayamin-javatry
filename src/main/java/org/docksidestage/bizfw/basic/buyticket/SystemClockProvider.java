package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * ローカル環境の時刻を返す、{@link ClockProvider} の実装クラス。
 * @author ayamin
 */
public class SystemClockProvider implements ClockProvider {
    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}
