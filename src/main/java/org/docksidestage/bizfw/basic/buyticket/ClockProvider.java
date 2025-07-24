package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * @author ayamin
 */
public interface ClockProvider {
    /**
     * 現在の時刻を取得します。
     * @return 現在時刻 (LocalTime形式で)
     */
    LocalTime getCurrentTime();
}
