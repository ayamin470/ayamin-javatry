package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * ローカル環境の時刻を返す、{@link Util_ClockProvider} の実装クラス。
 * @author ayamin
 */
public class Util_SystemClockProvider implements Util_ClockProvider {
    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}
