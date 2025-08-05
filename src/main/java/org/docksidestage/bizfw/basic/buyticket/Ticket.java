/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 遊園地のチケットを表すクラス
 * チケットの情報(有効日数、価格など)と、入園処理を定義
 * @author jflute
 * @author ayamin
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int validDays;
    private final boolean nightOnly;
    private int entryCount;
    private final ClockProvider clockProvider;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケットを構築する
     * @param displayPrice チケットに表示される価格
     * @param validDays チケットの有効日数
     * @param nightOnly 夜間専用チケットであるか
     * @param clockProvider 現在時刻を提供するプロバイダ
     */
    public Ticket(int displayPrice, int validDays, boolean nightOnly, ClockProvider clockProvider) {
        this.displayPrice = displayPrice;
        this.validDays = validDays;
        this.nightOnly = nightOnly;
        this.entryCount = 0;
        this.clockProvider = clockProvider;
    }
    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    /**
     * チケットを使って遊園地に入園する
     * @throws IllegalStateException 入園条件を満たさない場合。
     */
    public void doInPark() {
        if (entryCount >= validDays) {
            throw new IllegalStateException("有効日数を超えています。現在の入園カウント: " + entryCount + ", 有効日数: " + validDays);
        }

        LocalTime currentTime = clockProvider.getCurrentTime();
        if (nightOnly && DayNightChecker.isDay(currentTime)) {
            String CurrentTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String errorMessage = "このチケットは夜間専用です。現在時刻(" + CurrentTime + ")は昼間のため使用できません。";
            throw new IllegalStateException(errorMessage);
        }
        entryCount++;
    }
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * チケットに表示されている価格を取得
     * @return チケットの表示価格
     */
    public int getDisplayPrice() {
        return displayPrice;
    }
    /**
     * 一度でも入園したことがあるかを判定
     * @return 入園済みであればtrue
     */
    public boolean isAlreadyIn() {
        return entryCount > 0;
    }
    /**
     * まだ入園できるかを判定
     * @return 入園可能であればtrue
     */
    public boolean canEnterPark() {
        return entryCount < validDays;
    }
    /**
     * チケットの有効日数を取得
     * @return 有効日数
     */
    public int getValidDays() {
        return validDays;
    }
    /**
     * 現在の入園カウントを取得
     * @return 入園した回数
     */
    public int getEntryCount() {
        return entryCount;
    }
    /**
     * このチケットが夜間専用であるかを判定
     * @return 夜間専用であればtrue
     */
    public boolean isNightOnly() {
        return nightOnly;
    }
}
