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
import java.time.LocalTime;

/**
 * @author jflute
 * @author ayamin
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done ayamin [いいね] インスタンス変数の定義順序、Constructorの引数や設定と合っててとても綺麗で直感的で良いです！ by jflute (2025/07/07)
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
        // done ayamin ここの空行は不要だと思うので削除しましょう by jflute (2025/07/07)
    }
    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
//    public void doInPark() {
//        // 有効な入園回数を超えているかチェック
//        if (entryCount >= validDays) {
//            // done ayamin [いいね] 例外メッセージ、とってもわかりやすくていいですね。変数情報が入っててデバッグしやすいです by jflute (2025/07/07)
//            // done ayamin [読み物課題] 例外メッセージ、敬語で満足でもロスロスパターン by jflute (2025/07/07)
//            // https://jflute.hatenadiary.jp/entry/20170804/explossloss
//            // (ブログを読むのもjavatry。じっくり読んでくださいませ)
//            throw new IllegalStateException("値段" + displayPrice + ", validDays=" + validDays + ", entryCount=" + entryCount);
//        }
//        // done ayamin [いいね] すごい、ParkContextというシングルトンの概念で制御するようにしているの良いですね by jflute (2025/07/07)
//        // done ayamin 修行++: せっかくなので、単純なtrue/falseではなく、現在日時で夜かどうか？を判定するようにしてみてください by jflute (2025/07/07)
//        // ParkContext を書きかえても良いですし、また別の実装にしても良いですし。
//        // done jflute LocalTime.now(); で現在日時を取得できそうなことがわかったので、これで実装し直してみました by ayamin (2025/07/08)
//
//        if (nightOnly && ParkContext.isDay()) { // ParkContext を使用
//            throw new IllegalStateException("夜ではないので使えません");
//        }
//        // 入園回数をインクリメント
//        entryCount++;
//    }

    public void doInPark() {
        doInPark(LocalTime.now());
    }

    // TODO ayamin 修行++: LocalTimeを指定できるメソッドをpublicにはしない方良いと思います。 by jflute (2025/07/08)
    // チケットのユーザーが時刻を細工して呼び出せちゃうということになりますので。
    // 業務のコードでも、現在日時限定の場合は、外から日時を指定できないように作りました。
    // (検索とかだと、呼び出し側に自由に日時を渡せるようにするとかはありますが、今回は業務の振る舞いないので)
    // (でも、テストの都合上、時間を指定して動作確認したいってのはありますよね...さあそこが課題です)
    public void doInPark(LocalTime checkTime) {

        if (entryCount >= validDays) {
            throw new IllegalStateException("値段" + displayPrice + ", validDays=" + validDays + ", entryCount=" + entryCount);
        }

        if (nightOnly && ParkContext.isDay(checkTime)) {
            throw new IllegalStateException("このチケットは夜間専用です。指定された時刻(" + checkTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) + ")は昼間のため使用できません。");
        }

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
