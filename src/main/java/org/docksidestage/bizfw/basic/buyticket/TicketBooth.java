/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 20.0 (the "License");
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

// TODO ayamin こちらにも、ぜひご自身の@authorを追加お願いします。(既存コードもさわったらauthor追加で) by jflute (2025/07/02)
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 29600; // FourDayPassportの価格
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400; // NightOnlyTwoDayPassportの価格
    private static final int ONE_DAY_TICKET = 1; // 1日券の日数
    private static final int TWO_DAY_TICKET = 2; // 2日券の日数
    private static final int FOUR_DAY_TICKET = 4; // 4日券の日数
    private static final int NIGHT_ONLY_TWO_DAY_TICKET = 2; // 夜間専用2日券の日数
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // TODO ayamin [いいね] 全体的にすごく丁寧に綺麗にコードが整ってますね、Good! by jflute (2025/07/02)
    // JavaDocも、@returnの追加や、(NotNull) の表現などありがとうございます。呼び出し側が助かる情報です。
    /**
     * Buy one-day passport, method for park guest.
     *
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return The purchased one-day ticket. (NotNull)
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // doBuyPassportに価格、日数、夜間専用フラグ(false)を渡す
        return doBuyPassport(handedMoney, ONE_DAY_PRICE, ONE_DAY_TICKET, false);
    }

    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return The purchase result (ticket and change). (NotNull)
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        // TODO ayamin 恐らく OneDay だけ要件的に Result を必要としないので... by jflute (2025/07/02)
        // doBuyPassport()はチケットだけを戻して、後はpublicメソッド次第という風になっているのかと思いますが...
        // 大半は必ず TicketBuyResult まで作って戻すので、doBuyPassport()で TicketBuyResult 戻しちゃって、
        // OneDay は TicketBuyResult からチケットだけ取り出して戻す形にしちゃっても良いかなとは思います。
        // 若干 OneDay で不要なデータを作ることになりますが(OneDayはお釣りが要らない)、微々たるものなので、
        // 大きなパフォーマンス劣化にはならないので、ここはコードのスッキリさを優先しちゃってもいいかなと。

        // doBuyPassportでチケットを購入 (夜間専用ではないのでfalse)
        Ticket ticket = doBuyPassport(handedMoney, TWO_DAY_PRICE, TWO_DAY_TICKET, false);

        // お釣りを計算
        int change = handedMoney - TWO_DAY_PRICE;

        // TicketBuyResultオブジェクトを生成して返す
        return new TicketBuyResult(ticket, change);
    }

    /**
     * Buy four-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return The purchase result (ticket and change). (NotNull)
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        // doBuyPassportでチケットを購入 (夜間専用ではないのでfalse)
        Ticket ticket = doBuyPassport(handedMoney, FOUR_DAY_PRICE, FOUR_DAY_TICKET, false);

        // お釣りを計算
        int change = handedMoney - FOUR_DAY_PRICE;

        // TicketBuyResultオブジェクトを生成して返す
        return new TicketBuyResult(ticket, change);
    }

    /**
     * Buy night-only two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return The purchase result (ticket and change). (NotNull)
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        // doBuyPassportでチケットを購入 (夜間専用なのでtrue)
        Ticket ticket = doBuyPassport(handedMoney, NIGHT_ONLY_TWO_DAY_PRICE, NIGHT_ONLY_TWO_DAY_TICKET, true);

        // お釣りを計算
        int change = handedMoney - NIGHT_ONLY_TWO_DAY_PRICE;

        // TicketBuyResultオブジェクトを生成して返す
        return new TicketBuyResult(ticket, change);
    }

    /**
     * Common logic for buying a passport.
     * @param handedMoney The money (amount) handed over. (NotNull, NotMinus)
     * @param price The price of the passport.
     * @param ticketDays The number of days (uses) for the ticket.
     * @param isNightOnly Whether the ticket is night-only.
     * @return The purchased ticket. (NotNull)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    // 引数を handedMoney, price, ticketDays, isNightOnly に統一
    private Ticket doBuyPassport(Integer handedMoney, int price, int ticketDays, boolean isNightOnly) {
        // お金が足りているかを確認
        if (handedMoney < price) {
            // TODO ayamin [いいね] 日本語の例外メッセージGoodです。全然OKです。 by jflute (2025/07/02)
            // javatryは国籍問わずできるようにデフォルト英語にしているだけで、javadocとかも日本語でも全然OKですので。
            throw new TicketShortMoneyException("お金が足りません: 手持ち=" + handedMoney + "円, 必要=" + price + "円");
        }
        // チケットの在庫を確認
        if (quantity <= 0) {
            throw new TicketSoldOutException("チケットは売り切れです");
        }
        // TODO jflute 1on1にて、良いコメント、不要なコメントのお話をする予定 (2025/07/02)
        // でも良ければ、先にこのブログを読んでおいてもらえたらと。(ブログを読むのもjavatry!)
        // // オートマティックおうむ返しコメントより背景や理由を
        // https://jflute.hatenadiary.jp/entry/20180625/repeatablecomment
        --quantity; // チケットの数量を減らす
        if (salesProceeds != null) { // 2回目以降の購入
            salesProceeds = salesProceeds + price; // 売上をチケット価格分加算
        } else { // 初回購入
            salesProceeds = price; // 初回購入時の売上を設定
        }
        // Ticketコンストラクタに price, ticketDays, isNightOnly の全てを渡す
        return new Ticket(price, ticketDays, isNightOnly);
    }

    // TODO ayamin [いいね] publicの調整処理と、privateの実処理でうまく分かれててとてもわかりやすいです！ by jflute (2025/07/02)
    // TODO ayamin メモが↓JavaDoc形式になってしまっているので、今これ TicketSoldOutException に紐づくコメントになってしまっています by jflute (2025/07/02)
    // 最初の /** を /* だけにすると良いです。/** はJavaDoc, /* はただのコメント。
    /**
     * クラスをまとめる時に一つのメソッドでまとめてしまいそうになったけど、それぞれ違う処理はそれぞれで記述しつつ、
     * 同じ処理を別のメソッドとして書き込み、それを呼び出すという形にするとスマート
     */

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
