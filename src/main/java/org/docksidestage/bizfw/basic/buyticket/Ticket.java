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

/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    // private boolean alreadyIn; // このフィールドは remainingCount で代替されるため、厳密には不要だが、isAlreadyIn() のために残す
    private final int ticketDays;
    private int remainingCount;
    private final boolean nightOnly; // nightOnly フィールドは final にする
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // コンストラクタを displayPrice, ticketDays, nightOnly の3つの引数を持つように修正
    public Ticket(int displayPrice, int ticketDays, boolean nightOnly) {
        this.displayPrice = displayPrice;
        this.ticketDays = ticketDays; // 引数で渡された ticketDays で初期化
        this.remainingCount = ticketDays; // 初期状態では、日数分だけ利用可能
        this.nightOnly = nightOnly; // 引数で渡された nightOnly で初期化
    }
    /**
     * なぜコンストラクタでthis.をするのか？
     * そもそもコンストラクタはインスタンス変数を初期化するためにある
     *this を付けることで、「このオブジェクトの ticketDays というインスタンス変数に、引数で渡された ticketDays の値を代入して初期化するよ」の意味になる
     */

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // doInPark メソッドに isNightTime 引数を追加
    public void doInPark(boolean isNightTime) {
        // 残り利用可能回数が0以下の場合、エラーをスロー
        if (remainingCount <= 0) {
            throw new IllegalStateException("このチケットは使い切りました" + displayPrice + "円" + ticketDays + "日");
        }
        // 夜間専用チケットで、かつ現在が夜間ではない場合、エラーをスロー
        if (nightOnly && !isNightTime) {
            throw new IllegalStateException("このチケットは夜間のみ利用可能です。");
        }

        remainingCount--; // 利用可能回数を1減らす
        System.out.println("残り利用可能回数: " + remainingCount);
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    // isAlreadyIn() は remainingCount <= 0 と等価なので、そのまま残す
    public boolean isAlreadyIn() {
        return remainingCount <= 0; // remainingCount をチェック
    }

    public int getTicketDays() {
        return ticketDays;
    }
    public int getRemainingUseCount() {
        return remainingCount;
    }
    public boolean isNightOnly() {
        return nightOnly;
    }
}
