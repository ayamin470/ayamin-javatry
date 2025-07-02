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

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 *
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 *
 * @author jflute
 * @author your_name_here
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
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? =>　9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>7400 // 修正: 売上はチケット価格になる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>0
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? =>10 // 修正: お金不足でチケットは減らない
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
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
        log(sea); // should be max quantity, visual check here
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        // コメントアウトを解除
        TicketBuyResult buyResult = booth.buyTwoDayPassport(money);
        int change = buyResult.getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challengef
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark(false); // 修正: isNightTime 引数を追加 (昼間を想定)
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        TicketBooth booth = new TicketBooth();
        int twoDayMoney = 20000;
        TicketBuyResult twoDayResult = booth.buyTwoDayPassport(twoDayMoney); // (1) ここでTwoDayPassportを購入
        Ticket twoDayPassport = twoDayResult.getTicket();

        System.out.println("--- TwoDay Passport Test ---");
        System.out.println("購入したチケットの価格: " + twoDayPassport.getDisplayPrice());
        System.out.println("有効日数: " + twoDayPassport.getTicketDays());
        System.out.println("初期残り利用可能回数: " + twoDayPassport.getRemainingUseCount()); // (2) 初期値 (2) が表示される

        twoDayPassport.doInPark(true); // (3) 1回目の入園処理 (remainingUseCount が 2 -> 1 になる) // 修正: isNightTime 引数を追加
        System.out.println("1回目入園後、残り利用可能回数: " + twoDayPassport.getRemainingUseCount()); // (4) 1 が表示される
        System.out.println("1回目入園後、isAlreadyIn(): " + twoDayPassport.isAlreadyIn());

        twoDayPassport.doInPark(true); // (5) 2回目の入園処理 (remainingUseCount が 1 -> 0 になる) // 修正: isNightTime 引数を追加
        System.out.println("2回目入園後、残り利用可能回数: " + twoDayPassport.getRemainingUseCount()); // (6) あなたが質問している行。ここで 0 が表示される
        System.out.println("2回目入園後、isAlreadyIn(): " + twoDayPassport.isAlreadyIn());

        // 3回目入園を試みる (エラーになることを確認)
        try {
            twoDayPassport.doInPark(true); // 修正: isNightTime 引数を追加
            fail("3回目入園はIllegalStateExceptionが発生するはず");
        } catch (IllegalStateException e) {
            log("期待通り、3回目入園でエラー: " + e.getMessage()); // エラーメッセージが出力される
        }

        // OneDayPassportの動作も確認
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log("OneDayPassport 初期残り利用可能回数: " + oneDayPassport.getRemainingUseCount()); // 1
        oneDayPassport.doInPark(false); // 修正: isNightTime 引数を追加
        log("OneDayPassport 1回目入園後、残り利用可能回数: " + oneDayPassport.getRemainingUseCount()); // 0
        log("OneDayPassport 1回目入園後、isAlreadyIn(): " + oneDayPassport.isAlreadyIn());

        // 2回目の入園を試みて、IllegalStateExceptionが発生することを確認
        try {
            oneDayPassport.doInPark(false); // 修正: isNightTime 引数を追加
            fail("OneDayPassportは1回で使い切れるので、2回目の入園で例外が発生するはずです。");
        } catch (IllegalStateException e) {
            log("期待通り、2回目入園でエラー: " + e.getMessage());
        }
    }

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        showTicketIfNeeds(oneDayPassport);
        TicketBuyResult buyResult = booth.buyTwoDayPassport(10000); // TwoDayPassportは13200円なので、この金額では買えない（ShortMoneyException）
        // テストをパスさせるなら、ここを20000などに変更する必要がある
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
    }

    private void showTicketIfNeeds(Ticket ticket)
    {
        if (ticket.getTicketDays() == 2) { // チケットの日数が2日ならTwoDayパスポート
            log("２日用パスポートだよ");
        } else {
            log("２日用ではないよ"); // 1日パスポートなど
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
        TicketBooth booth = new TicketBooth();
        int handedMoney = 50000;
        TicketBuyResult buyResult = booth.buyFourDayPassport(handedMoney);
        Ticket fourDayPassport = buyResult.getTicket(); // 変数名を修正: FourDayPassport -> fourDayPassport
        int change = buyResult.getChange();
        log("購入したFourDayPassportの価格: " + fourDayPassport.getDisplayPrice());
        log("お釣り: " + change);
        log("有効日数: " + fourDayPassport.getTicketDays());
        log("初期残り利用可能回数: " + fourDayPassport.getRemainingUseCount());

        // 4回利用できることを確認
        for (int i = 0; i < 4; i++) {
            fourDayPassport.doInPark(false); // 昼間利用を想定
            log((i + 1) + "回目入園後、残り利用可能回数: " + fourDayPassport.getRemainingUseCount());
        }

        // 5回目利用を試みる (エラーになることを確認)
        try {
            fourDayPassport.doInPark(false);
            fail("FourDayPassportは4回で使い切れるので、5回目の入園で例外が発生するはずです。");
        } catch (IllegalStateException e) {
            log("期待通り、5回目入園でエラー: " + e.getMessage());
        }
    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        TicketBooth booth = new TicketBooth();

        System.out.println("--- NightOnly Two-Day Passport Test ---");
        int nightOnlyMoney = 10000; // 7400円なのでお釣りが出る金額
        TicketBuyResult nightOnlyResult = booth.buyNightOnlyTwoDayPassport(nightOnlyMoney);
        Ticket nightOnlyPassport = nightOnlyResult.getTicket();
        int change = nightOnlyResult.getChange();

        log("購入した夜間専用チケットの価格: " + nightOnlyPassport.getDisplayPrice()); // 7400
        log("お釣り: " + change); // 10000 - 7400 = 2600
        log("有効日数: " + nightOnlyPassport.getTicketDays()); // 2
        log("初期残り利用可能回数: " + nightOnlyPassport.getRemainingUseCount()); // 2
        log("夜間専用チケットか: " + nightOnlyPassport.isNightOnly()); // true

        // 昼間に利用を試みる (エラーになることを確認)
        System.out.println("\n--- 昼間の利用試行 ---");
        try {
            nightOnlyPassport.doInPark(false); // isNightTime = false (昼間)
            fail("夜間専用チケットなので、昼間は利用できないはずです。");
        } catch (IllegalStateException e) {
            log("期待通り、昼間の利用でエラー: " + e.getMessage()); // エラーメッセージが出力される
        }
        log("昼間利用試行後、残り利用可能回数: " + nightOnlyPassport.getRemainingUseCount()); // 2 (減っていない)

        // 夜間に1回目利用
        System.out.println("\n--- 夜間の1回目利用 ---");
        nightOnlyPassport.doInPark(true); // isNightTime = true (夜間)
        log("夜間1回目利用後、残り利用可能回数: " + nightOnlyPassport.getRemainingUseCount()); // 1

        // 夜間に2回目利用
        System.out.println("\n--- 夜間の2回目利用 ---");
        nightOnlyPassport.doInPark(true); // isNightTime = true (夜間)
        log("夜間2回目利用後、残り利用可能回数: " + nightOnlyPassport.getRemainingUseCount()); // 0
        log("2回利用後、isAlreadyIn(): " + nightOnlyPassport.isAlreadyIn()); // true

        // 3回目利用を試みる (エラーになることを確認)
        System.out.println("\n--- 3回目利用試行 ---");
        try {
            nightOnlyPassport.doInPark(true); // isNightTime = true (夜間)
            fail("TwoDayチケットなので、3回目は利用できないはずです。");
        } catch (IllegalStateException e) {
            log("期待通り、3回目利用でエラー: " + e.getMessage()); // エラーメッセージが出力される
        }

        log("\nテスト完了。ログを確認してください。");
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }
    // TODO jflute さん
    // 自分のコードを見ても改善箇所が思い浮かびませんでした
    // 何かするべきリファクタリングがありましたら、教えていただけますと幸いです

    /**
     * Write intelligent comments on source code to the main code in buyticket package. <br>
     * (buyticketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        // your confirmation code here
    }


}
