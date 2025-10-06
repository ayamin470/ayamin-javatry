package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * 現在のローカル環境の時刻を取得して返す
 * @return 現在時刻
 * @author ayamin
 */
public interface Util_ClockProvider {
    LocalTime getCurrentTime();
}
