/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author ayamin
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    private final int validDays;
    private final boolean nightOnly;// チケットが有効な日数 (例: 1日パスポートなら1, 2日パスポートなら2)
    private int entryCount;// 既に入園した回数


    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int validDays,boolean nightOnly) { // コンストラクタに validDays を追加
        this.displayPrice = displayPrice;
        this.validDays = validDays;
        this.nightOnly = nightOnly;
        this.entryCount = 0; // 初期入園回数は0

    }
    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        // 有効な入園回数を超えているかチェック
        if (entryCount >= validDays) {
            throw new IllegalStateException("値段" + displayPrice + ", validDays=" + validDays + ", entryCount=" + entryCount);
        }
        if (nightOnly && ParkContext.isDay()) { // ParkContext を使用
            throw new IllegalStateException("夜ではないので使えません");
        }
        // 入園回数をインクリメント
        entryCount++;
    }
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return entryCount > 0;
    }
    public boolean canEnterPark() {
        return entryCount < validDays;
    }
    public int getValidDays() {
        return validDays;
    }
    public int getEntryCount() {
        return entryCount;
    }
    public boolean isNightOnly() {
        return nightOnly;
    }
}
