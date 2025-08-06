///*
// * Copyright 2019-2022 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
// * either express or implied. See the License for the specific language
// * governing permissions and limitations under the License.
// */
//package org.docksidestage.bizfw.basic.buyticket;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//
///**
// * @author jflute
// * @author ayamin
// */
//public class Ticket {
//
//    // ===================================================================================
//    //                                                                           Attribute
//    //                                                                           =========
//    // done ayamin [いいね] インスタンス変数の定義順序、Constructorの引数や設定と合っててとても綺麗で直感的で良いです！ by jflute (2025/07/07)
//    private final int displayPrice; // written on ticket, park guest can watch this
//    private final int validDays;
//    private final boolean nightOnly;// チケットが有効な日数 (例: 1日パスポートなら1, 2日パスポートなら2)
//    private int entryCount;// 既に入園した回数
//    private final ClockProvider clockProvider;
//    // ===================================================================================
//    //                                                                         Constructor
//    //                                                                         ===========
//    public Ticket(int displayPrice, int validDays, boolean nightOnly, ClockProvider clockProvider) { // ← ここに clockProvider を追加
//        this.displayPrice = displayPrice;
//        this.validDays = validDays;
//        this.nightOnly = nightOnly;
//        this.entryCount = 0;
//        this.clockProvider = clockProvider;
//    }
//    // ===================================================================================
//    //                                                                             In Park
//    //                                                                             =======
//    //    public void doInPark() {
//    //        // 有効な入園回数を超えているかチェック
//    //        if (entryCount >= validDays) {
//    //            // done ayamin [いいね] 例外メッセージ、とってもわかりやすくていいですね。変数情報が入っててデバッグしやすいです by jflute (2025/07/07)
//    //            // done ayamin [読み物課題] 例外メッセージ、敬語で満足でもロスロスパターン by jflute (2025/07/07)
//    //            // https://jflute.hatenadiary.jp/entry/20170804/explossloss
//    //            // (ブログを読むのもjavatry。じっくり読んでくださいませ)
//    //            throw new IllegalStateException("値段" + displayPrice + ", validDays=" + validDays + ", entryCount=" + entryCount);
//    //        }
//    //        // done ayamin [いいね] すごい、ParkContextというシングルトンの概念で制御するようにしているの良いですね by jflute (2025/07/07)
//    //        // done ayamin 修行++: せっかくなので、単純なtrue/falseではなく、現在日時で夜かどうか？を判定するようにしてみてください by jflute (2025/07/07)
//    //        // ParkContext を書きかえても良いですし、また別の実装にしても良いですし。
//    //        // done jflute LocalTime.now(); で現在日時を取得できそうなことがわかったので、これで実装し直してみました by ayamin (2025/07/08)
//    //
//    //        if (nightOnly && ParkContext.isDay()) { // ParkContext を使用
//    //            throw new IllegalStateException("夜ではないので使えません");
//    //        }
//    //        // 入園回数をインクリメント
//    //        entryCount++;
//    //    }
//
//    // done ayamin 修行++: LocalTimeを指定できるメソッドをpublicにはしない方良いと思います。 by jflute (2025/07/08)
//    // ちになりますので。
//    // (検索とかだと、呼び出し側に自由に日時を渡せるようにするとかはありますが、今回は業務の振る舞いないので)
//    // (でも、テストの都合上、時間を指定して動作確認したいってのはありますよね...さあそこが課題です)
//    // (このto.doは最後でもOKです)
//    //
//    // 業務のコードでも、現在日時限定の場合は、外から日時を指定できないように作りました。
//    // done ayamin [いいね] おおぉ、ClockProviderインターフェース方式、いいですね！ by jflute (2025/07/24)
//    // TicketBoothから受け取って現在日付を裏から細工できるようになってるの素晴らしいです。
//    // done jflute 1on1にて、ClockProvider一緒に見ていきましょう (2025/07/24)
//    public void doInPark() {
//        if (entryCount >= validDays) {
//            throw new IllegalStateException("有効日数を超えています。現在の入園カウント: " + entryCount + ", 有効日数: " + validDays);
//        }
//
//        LocalTime currentTime = clockProvider.getCurrentTime();
//        if (nightOnly && DayNightChecker.isDay(currentTime)) {
//            // done ayamin [いいね] 例外メッセージ、とても詳しくて素晴らしい by jflute (2025/07/24)
//            // done ayamin 横長すぎるので、現在時刻のところ、変数に抽出しましょう by jflute (2025/07/25)
//            // done ayamin 細かいですが、変数名の先頭は小文字で (CurrentTime -> currentTime) by jflute (2025/07/31)
//            // スコープ短いですが、IntelliJのrenameのショートカット機能を使ってrenameしてみましょう。(指トレーニング)
//            // done ayamin 細かいですが、java.time.format.DateTimeFormatter のところ、FQCN じゃなくて良いと思います by jflute (2025/07/31)
//            String CurrentTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
//            String errorMessage = "このチケットは夜間専用です。現在時刻(" + CurrentTime + ")は昼間のため使用できません。";
//            throw new IllegalStateException(errorMessage);
//        }
//        entryCount++;
//    }
//    // ===================================================================================
//    //                                                                            Accessor
//    //                                                                            ========
//    public int getDisplayPrice() {
//        return displayPrice;
//    }
//    public boolean isAlreadyIn() {
//        return entryCount > 0;
//    }
//    public boolean canEnterPark() {
//        return entryCount < validDays;
//    }
//    public int getValidDays() {
//        return validDays;
//    }
//    public int getEntryCount() {
//        return entryCount;
//    }
//    public boolean isNightOnly() {
//        return nightOnly;
//    }
//}
//
