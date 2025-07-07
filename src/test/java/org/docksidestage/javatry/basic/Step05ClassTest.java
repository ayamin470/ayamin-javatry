
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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.ParkContext;

import org.docksidestage.unit.PlainTestCase;

// TODO ayamin いったん、既存のtodoでdoneにできるものはdoneを付けるようにお願いします by jflute (2025/07/07)
// (doneの付いてないtodoだらけになってきたのですが、どれが直したもので、どれがまだ未対応のものか判断が大変なのでm(_ _)m)
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
        log(sea); // your answer? =>10000
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>null
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? =>9
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
        // uncomment after making the method
        // TODO ayamin 変数が使われてないので、unused の警告がIDE上で出ています by jflute (2025/07/07)
        // (コメントアウトされた)元のコードで合わないところがあったら、合うように修正しちゃってください。
        TicketBooth booth = new TicketBooth();
        int money = 14000;
//        int change = booth.buyTwoDayPassport(money);
//        Integer sea = booth.getSalesProceeds() + change;
//        log(sea); // should be same as money

        // and show two-day passport quantity here
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */

    // TODO ayamin 以下の点が重複しているはず
    //    チケット在庫のチェック
    //    支払われた金額が不足していないかのチェック
    //    チケット在庫を減らす
    //    売上金の更新
    //    どうやるか？カプセル化してメソッドを呼び出す
    // TODO ayamin [ふぉろー] このように小さな業務のまとまりを言語化して思考するの大切です。とても上手ですね。 by jflute (2025/07/07)
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */

    //TODO ayamin そもそもなぜTicketBuyResult.javaを作る必要があったのか？
    // Javaは、原則として一つの値しか直接返すことができない。そのままだとチケットとお釣りの両方を返せない
    // TicketBuyResultとして一つのクラスにお釣りとチケットを内包することで、クリアできる
    // TODO ayamin [ふぉろー] いいですね、そういうところに視点を置くというのは。 by jflute (2025/07/07)
    // TicketBuyResult は、物理の世界のチケットブースの、紙のチケットとお釣りを入れて戻す「青いトレー」みたいなイメージです。
    // 将来、レシートも戻すとか戻す項目が増えたとき、TicketBuyResultがあれば戻り値は変更せずにResultに追加するだけで済みます。
    // こういう風に、戻り値の「入れ物クラス」というのはよく使われます。

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
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        // TwoDayPassport を購入 (14000円支払い)
        TicketBuyResult buyResult = booth.buyTwoDayPassport(14000);
        Ticket twoDayPassport = buyResult.getTicket();

        log("--- TwoDayPassport Usage ---");
        log("定価：" + twoDayPassport.getDisplayPrice() + ",残り日数：" + twoDayPassport.getValidDays() + ",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？：" + twoDayPassport.isAlreadyIn());

        // 1回目入園
        twoDayPassport.doInPark();
        log(",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？：" + twoDayPassport.isAlreadyIn()); // EntryCount=1, AlreadyIn=true

        // 2回目入園
        twoDayPassport.doInPark();
        log(",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？：" + twoDayPassport.isAlreadyIn()); // EntryCount=2, AlreadyIn=true

        // 3回目入園 (例外が発生するはず)
        try {
            twoDayPassport.doInPark();
            fail("例外処理"); // 例外が発生しなかったらテスト失敗
        } catch (IllegalStateException e) {
            log("2日パスポートなので入園できないよ" + e.getMessage()); // 例外メッセージをログに出力
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
        showTicketIfNeeds(oneDayPassport); // "other" とログされるはず
        TicketBuyResult buyResult = booth.buyTwoDayPassport(10000);
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport); // "two-day passport" とログされるはず
    }

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        // TODO ayamin NightOnlyは別なので、純粋に TwoDayPassport だけがヒットするようにしましょう by jflute (2025/07/07)
        if (ticket.getValidDays() == 2) { // write determination for two-day passport
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
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        int handedMoney = 25000;
        TicketBuyResult buyResult = booth.buyFourDayPassport(handedMoney);
        Ticket fourDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();

        log("--- FourDayPassport Purchase & Usage ---");
        log("チケットの値段: " + fourDayPassport.getDisplayPrice()); // 22400
        log("お釣り: " + change); // 25000 - 22400 = 2600
        log("売り上げ: " + booth.getSalesProceeds()); // 22400
        log("残りのチケット数: " + booth.getQuantity()); // 9

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
        TicketBooth booth = new TicketBooth();
        int handedMoney = 10000;

        log("--- 夜間 ---");

        // 1. 夜間チケットを夜間に使用するテスト
        ParkContext.setNight(true);
        log("現在の時間: 夜間");
        TicketBuyResult nightBuyResult = booth.buyNightOnlyTwoDayPassport(handedMoney);
        Ticket nightTicket = nightBuyResult.getTicket();
        int nightChange = nightBuyResult.getChange();

        log("夜間専用TwoDayPassportの価格: " + nightTicket.getDisplayPrice());
        log("お釣り: " + nightChange);
        log("夜間専用か: " + nightTicket.isNightOnly());
        log("残りのチケット枚数: " + booth.getQuantity());

        log("初期状態: 有効日数=" + nightTicket.getValidDays() + ", 入園回数=" + nightTicket.getEntryCount());

        // 1回目入園 (夜間なので成功)
        nightTicket.doInPark();
        log("1回目の入園後 (夜間): 入園回数=" + nightTicket.getEntryCount());

        // 2回目入園 (夜間なので成功)
        nightTicket.doInPark();
        log("2回目の入園後 (夜間): 入園回数=" + nightTicket.getEntryCount());

        // 3回目入園 (有効回数超過なので失敗)
        try {
            nightTicket.doInPark();
            fail("有効日数 (2日間) を超えて入園できるべきではありません");
        } catch (IllegalStateException e) {
            log("3回目の入園で期待される例外を捕捉しました (有効日数超過): " + e.getMessage());
        }
        log("3回目の入園試行後: 入園回数=" + nightTicket.getEntryCount());

        // 2. 夜間チケットを昼間に使用するテスト
        ParkContext.setNight(false);
        log("現在の時間: 昼間");
        TicketBuyResult anotherNightBuyResult = booth.buyNightOnlyTwoDayPassport(handedMoney);
        Ticket anotherNightTicket = anotherNightBuyResult.getTicket();

        log("別の夜間専用TwoDayPassportの価格: " + anotherNightTicket.getDisplayPrice());
        log("夜間専用か: " + anotherNightTicket.isNightOnly());

        try {
            anotherNightTicket.doInPark(); // 昼間なので例外が発生するはず
            fail("夜間専用チケットなので使えません。");
        } catch (IllegalStateException e) {
            log("夜間専用チケットなので使えません。: " + e.getMessage());
        }
        log("入園回数=" + anotherNightTicket.getEntryCount());
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */

    //done jflute さん
    //ここは解いていません。1on1時にご解説いただけますと幸いです
    // TODO ayamin [へんじ] だいぶ全体的に綺麗にできています。気になるところはtodo入れていきますね。 by jflute (2025/07/07)
    // このエクササイズは、コードを綺麗に整えることを意識させるために入れてあるという感じです。

    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    /**
     * Write intelligent comments on source code to the main code in buyticket package. <br>
     * (buyticketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        // your confirmation code here
    }
}
