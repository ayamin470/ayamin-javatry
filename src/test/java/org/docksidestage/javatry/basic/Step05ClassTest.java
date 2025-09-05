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
package org.docksidestage.javatry.basic;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.docksidestage.bizfw.basic.buyticket.SystemClockProvider;
import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.TwoDayPassport;
import org.docksidestage.unit.PlainTestCase;
import org.docksidestage.unit.TestClockProvider;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 *
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 *
 * @author jflute
 * @author ayamin
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        Integer sea = booth.getSalesProceeds();
        log(sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea);
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea);
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea);
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {

        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        int money = 14000;
        int change = booth.buyTwoDayPassport(money).getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea);

    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds());
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */

    public void test_class_moreFix_usePluralDays() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        TicketBuyResult buyResult = booth.buyTwoDayPassport(14000);
        Ticket twoDayPassport = buyResult.getTicket();

        log("--- TwoDayPassport Usage ---");
        log("定価：" + twoDayPassport.getDisplayPrice() + ",残り日数：" + twoDayPassport.getValidDays() + ",入園カウント："
                + twoDayPassport.getEntryCount() + ",入園したか？：" + twoDayPassport.isAlreadyIn());

        // 1回目入園
        twoDayPassport.doInPark();
        log(",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？："
                + twoDayPassport.isAlreadyIn());

        // 2回目入園
        twoDayPassport.doInPark();
        log(",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？："
                + twoDayPassport.isAlreadyIn());

        // 3回目入園 (例外が発生するはず)
        try {
            twoDayPassport.doInPark();
            fail("例外処理");
        } catch (IllegalStateException e) {
            log("2日パスポートなので入園できないよ" + e.getMessage());
        }
    }

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        TicketBuyResult buyResult = booth.buyOneDayPassport(10000);
        Ticket oneDayPassport = buyResult.getTicket();
        showTicketIfNeeds(oneDayPassport);
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
    }

    // done ayamin { を下ろすなら下ろすのに徹底したほうが良いかも。今だと独立ifに見えちゃう... by jflute (2025/08/22)
    private void showTicketIfNeeds(Ticket ticket) {
        if (ticket.isNightOnly()) {
            log("それは夜間専用2日パスポートだよ");
        } else if (ticket.getValidDays() == 2) {
            log("それは2日パスポートだよ");
        } else {
            log("2日パスポートじゃないよ");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        int handedMoney = 25000;
        TicketBuyResult buyResult = booth.buyFourDayPassport(handedMoney);
        Ticket fourDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();

        log("--- FourDayPassport Purchase & Usage ---");
        log("チケットの値段: " + fourDayPassport.getDisplayPrice());
        log("お釣り: " + change);
        log("売り上げ: " + booth.getSalesProceeds());
        log("残りのチケット数: " + booth.getQuantity());

        // 4回入園
        log("残り入園可能日数：" + fourDayPassport.getValidDays() + ", EntryCount=" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark();
        log("入園数：" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark();
        log("入園数：" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark();
        log("入園数：" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark();
        log("入園数：" + fourDayPassport.getEntryCount());

        // 5回目
        try {
            fourDayPassport.doInPark();
            fail("4日チケットなので入れません");
        } catch (IllegalStateException e) {
            log("4日チケットなので入れません " + e.getMessage());
        }
        log("4日チケットなので入れません" + fourDayPassport.getEntryCount());

    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        int handedMoney = 10000;

        // --- シミュレーション時刻の定義 ---
        LocalTime nightTime = LocalTime.of(19, 0, 0);
        LocalTime dayTime = LocalTime.of(10, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // --- 夜間チケットを「夜間」としてシミュレートし使用するテスト ---
        log("--- 夜間チケットをシミュレートされた夜間に使用するテスト ---");
        log("シミュレート時刻: " + nightTime.format(formatter) + " (夜間)");

        TicketBooth nightBooth = new TicketBooth(new TestClockProvider(nightTime));
        TicketBuyResult nightBuyResult = nightBooth.buyNightOnlyTwoDayPassport(handedMoney);
        Ticket nightTicket = nightBuyResult.getTicket();
        int nightChange = nightBuyResult.getChange();

        log("夜間専用TwoDayPassportの価格: " + nightTicket.getDisplayPrice());
        log("お釣り: " + nightChange);
        log("夜間専用か: " + nightTicket.isNightOnly());
        log("残りのチケット枚数: " + nightBooth.getQuantity());
        log("初期状態: 有効日数=" + nightTicket.getValidDays() + ", 入園回数=" + nightTicket.getEntryCount());

        // 1回目入園 (夜間)
        log("1回目の入園試行 (シミュレート時刻: " + nightTime.format(formatter) + ")");
        nightTicket.doInPark();
        log("1回目の入園後 (シミュレート夜間): 入園回数=" + nightTicket.getEntryCount());

        // 2回目入園　(夜間)
        log("2回目の入園試行 (シミュレート時刻: " + nightTime.format(formatter) + ")");
        nightTicket.doInPark();
        log("2回目の入園後 (シミュレート夜間): 入園回数=" + nightTicket.getEntryCount());

        // 3回目入園 (有効回数超過)
        log("3回目の入園試行 (シミュレート時刻: " + nightTime.format(formatter) + ", 有効回数超過)");
        try {
            nightTicket.doInPark();
            fail("有効日数を超えているのに例外が発生しませんでした。");
        } catch (IllegalStateException e) {
            log("有効日数を超えています: " + e.getMessage());
            assertTrue(e.getMessage().contains("有効日数: " + nightTicket.getValidDays()) || e.getMessage().contains("validDays=" + nightTicket.getValidDays()));
        }
        log("3回目の入園試行後: 入園回数=" + nightTicket.getEntryCount());

        // --- 夜間チケットを「昼間」としてシミュレートし使用するテスト ---
        log("--- 夜間チケットをシミュレートされた昼間に使用するテスト ---");
        log("シミュレート時刻: " + dayTime.format(formatter) + " (昼間)");

        // 別の夜間チケットを購入
        TicketBooth dayBooth = new TicketBooth(new TestClockProvider(dayTime));
        TicketBuyResult anotherNightBuyResult = dayBooth.buyNightOnlyTwoDayPassport(handedMoney);
        Ticket anotherNightTicket = anotherNightBuyResult.getTicket();

        log("別の夜間専用TwoDayPassportの価格: " + anotherNightTicket.getDisplayPrice());
        log("夜間専用か: " + anotherNightTicket.isNightOnly());

        // 夜間チケットを昼間に使用する
        log("入園試行 (シミュレート時刻: " + dayTime.format(formatter) + ")");
        try {
            anotherNightTicket.doInPark();
            fail("夜間専用チケットなのに昼間に使えました");
        } catch (IllegalStateException e) {
            log("夜間専用チケットなので使えません): " + e.getMessage());
            assertTrue(e.getMessage().contains("夜間専用"));
            assertTrue(e.getMessage().contains(dayTime.format(formatter)));
        }
        log("入園回数=" + anotherNightTicket.getEntryCount());

    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
    }

    /**
     * Write intelligent JavaDoc comments seriously on the public classes/constructors/methods of the Ticket class. <br>
     * (Ticketクラスのpublicなクラス/コンストラクター/メソッドに、気の利いたJavaDocコメントを本気で書いてみましょう)
     */
    public void test_class_moreFix_yourSuperJavaDoc() {
    }
    // ===================================================================================
    //                                             ayamin original test for abstract class
    //                                                                           =========
    // done ayamin 修行#: (続き) チケット種別のユニーク性って、日数と夜間かどうかだけ？とは限らないかもという前提... by jflute (2025/08/22)
            // 日数と夜間だけとは限らない前提で、チケット種別をピンポイントで判別できるようにしてみましょう。
            // (ユニーク性の要素で言うと無限にできるので、そこを気にせず判定できるように)
    // #1on1: まず、継承を使って、チケット種別をサブクラスとして表現しているのはGood。 (2025/09/05)
    // これ自分もよくやります。うまくstep6の知識と融合できてて素晴らしい！
    // 一方で、step5なので継承を使わないやり方も別途存在しています。どっちが良いというわけでもないくらい。
    // 「チャレンジしたい！」by あやみんさん => 素敵です。
    // TODO ayamin 修行#++: 継承を使わないやり方もどこかでやってみましょう by jflute (2025/09/05)
    // 既存の今のコードは残しつつ、なんかクラス名にprefix付けるなどして、独立して作った方がいいかも。
    public void test_class_moreFix_whetherTicketType_withPolymorphism() {
        // チケットブースをインスタンス化
        TicketBooth booth = new TicketBooth(new SystemClockProvider());

        // 1日パスポートを購入し、チケット種別を表示
        Ticket oneDayPassport = booth.buyOneDayPassport(30000).getTicket();
        log("パスポートの種別: " + oneDayPassport.getTicketTypeDisplayName());

        // 2日パスポートを購入し、チケット種別を表示
        Ticket twoDayPassport = booth.buyTwoDayPassport(30000).getTicket();
        log("パスポートの種別: " + twoDayPassport.getTicketTypeDisplayName());
        // TODO ayamin 一応、if文で分岐(確認)できるプログラムも書いておきましょう(instanceof) by jflute (2025/09/05)

        // 4日パスポートを購入し、チケット種別を表示
        Ticket fourDayPassport = booth.buyFourDayPassport(30000).getTicket();
        log("パスポートの種別: " + fourDayPassport.getTicketTypeDisplayName());

        // 夜間専用2日パスポートを購入し、チケット種別を表示
        Ticket nightOnlyPassport = booth.buyNightOnlyTwoDayPassport(30000).getTicket();
        log("パスポートの種別: " + nightOnlyPassport.getTicketTypeDisplayName());

        // もし新しいチケット（例：子供用）を追加しても、ここに追加するだけでテストできる
        // Ticket childPassport = booth.buyChildPassport(5000).getTicket();
        // log("子供用パスポートの種別: " + childPassport.getTicketTypeDisplayName());
    }
}
