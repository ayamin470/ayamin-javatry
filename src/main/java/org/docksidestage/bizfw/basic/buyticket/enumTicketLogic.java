package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * チケットのインスタンス的な
 * チケットの状態や振る舞いをこのクラスに書いている(ロジック)
 * @author ayamin
 */

public class enumTicketLogic {
    private final Util_ClockProvider clockProvider;
    private final int validDays;
    private int entryCount;
    private final boolean nightonly;

    public enumTicketLogic(enumTicket type, Util_ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
        this.validDays = type.getValidDays();
        this.nightonly = type.isNightOnly();
    }

    public void doInPark() {
        if (entryCount >= validDays) {
            throw new IllegalStateException("有効日数を超えています。現在の入園カウント: " + entryCount + ", 有効日数: " + validDays);
        }

        LocalTime currentTime = clockProvider.getCurrentTime();
        if (nightonly && Util_DayNightChecker.isDay(currentTime)) {
            String currentTimeStr = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String errorMessage = "このチケットは夜間専用です。現在時刻(" + currentTimeStr + ")は昼間のため使用できません。";
            throw new IllegalStateException(errorMessage);
        }
        entryCount++;
    }


}
