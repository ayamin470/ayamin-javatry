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
 * @author ayamin
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    // private boolean alreadyIn; // true means this ticket is unavailable (Old: single use)
    private final int ticketDays; // 修正: final を追加し、コンストラクタで初期化されるように
    private int remainingCount;
    private boolean nightOnly;

    // TODO ayamin せっかくこういった Accessor を定義する場所がクラスの一番下にあるので、定義位置も統一した方がいいかなと by jflute (2025/07/02)
    public boolean isNightOnly() { // このメソッドが正しく定義されているはず
        return nightOnly;
    }

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // 修正: コンストラクタが displayPrice と ticketDays の両方を受け取るように変更
    public Ticket(int displayPrice, int ticketDays,boolean nightOnly) {
        this.displayPrice = displayPrice;
        this.ticketDays = ticketDays; // 引数で渡された ticketDays で初期化
        this.remainingCount = ticketDays; // 初期状態では、日数分だけ利用可能
        this.nightOnly = nightOnly;
    }
    // TODO ayamin [いいね] そういうことです。まあ同時に displayPrice という同じ名前の変数が二つ存在することになるので... by jflute (2025/07/02)
    // 「this(このインスタンス)のdisplayPrice」と「引数のdisplayPrice」を区別するために、thisを付けています。
    // 何も付けなかったときは、スコープが短い方 (宣言が近い方) が優先されるので、引数の方は何も付けなくてOKと。
    // そういう意味では、this.remainingCount = ticketDays; は this. なくても良いということにはなります。
    // でも、みんな付けてて縦に揃ってるのに、そこだけ this. 外すってのも変だから全然今のでOKです。
    /**
     * なぜコンストラクタでthis.をするのか？
     * そもそもコンストラクタはインスタンス変数を初期化するためにある
     *this を付けることで、「このオブジェクトの ticketDays というインスタンス変数に、引数で渡された ticketDays の値を代入して初期化するよ」の意味になる
     */

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // TODO ayamin ここちょっと isNightTime の説明が欲しいところですね。(入った瞬間)今夜時間か？ってことだと思いますが... by jflute (2025/07/02)
    // nightOnly変数もあって混同しやすいところですから。publicメソッドですし重要メソッドなのでJavaDocで@paramで説明あるといいなと。
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
        
        // TODO ayamin これはわからなくて当然ですが、ログはロギングフレームワークをぜひ使ってください by jflute (2025/07/02)
        // javatryの中に、Bicycle.java がいるので、そこで logger の宣言を参考に。
        
        System.out.println("残り利用可能回数: " + remainingCount);
        // TODO jflute 1on1にて、なぜSystem.out.println()は使わないのか？の説明をする予定 (2025/07/02)
    }
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    // isAlreadyIn() は remainingCount <= 0 と等価なので、そのまま残す
    public boolean isAlreadyIn() {
        return remainingCount <= 0; // 修正: remainingCount をチェック
    }

    public int getTicketDays() {
        return ticketDays;
    }
    public int getRemainingUseCount() {
        return remainingCount;
    }
}
