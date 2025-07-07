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
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;

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
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */

    public Ticket buyOneDayPassport(Integer handedMoney) {
        doBuyPassport(handedMoney, ONE_DAY_PRICE);
        return new Ticket(ONE_DAY_PRICE, 1, false);
    }

    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        doBuyPassport(handedMoney, TWO_DAY_PRICE);
        Ticket ticket = new Ticket(TWO_DAY_PRICE, 2, false);
        int change = handedMoney - TWO_DAY_PRICE;
        return new TicketBuyResult(ticket, change);
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        doBuyPassport(handedMoney, FOUR_DAY_PRICE);

        Ticket ticket = new Ticket(FOUR_DAY_PRICE, 4,false);
        int change = handedMoney - FOUR_DAY_PRICE;

        return new TicketBuyResult(ticket, change);
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {

        doBuyPassport(handedMoney, NIGHT_ONLY_TWO_DAY_PRICE);

        Ticket ticket = new Ticket(NIGHT_ONLY_TWO_DAY_PRICE, 2, true); // ★ nightOnly を true に設定

        int change = handedMoney - NIGHT_ONLY_TWO_DAY_PRICE;

        return new TicketBuyResult(ticket, change);
    }

    // TODO ayamin 重複したものをまとめるメソッド
    private void doBuyPassport(Integer handedMoney, int price) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;

        if (salesProceeds != null) { // 2回目以降の購入
            salesProceeds = salesProceeds + price;
        } else { // 初回購入
            salesProceeds = price;
        }
    }

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
