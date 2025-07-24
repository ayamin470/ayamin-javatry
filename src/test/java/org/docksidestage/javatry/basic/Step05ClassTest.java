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

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.unit.PlainTestCase;
import org.docksidestage.unit.TestClockProvider; // TestClockProvider をインポート
import org.docksidestage.bizfw.basic.buyticket.SystemClockProvider; // SystemClockProvider をインポート

// done ayamin いったん、既存のtodoでdoneにできるものはdoneを付けるようにお願いします by jflute (2025/07/07)
// (doneの付いてないtodoだらけになってきたのですが、どれが直したもので、どれがまだ未対応のものか判断が大変なのでm(_ m)
// done jflute すみません！Step05まではすべてdoneしました。課題を進めることばかりに目が眩んでしまいました by ayamin (2025/07/08)
// done ayamin [へんじ] 頑張ってるの素晴らしいことですー（＾＾。 by jflute (2025/07/08)
// ただ、早く進めることが目的ではないので、仕事のトレーニングとしては「ちゃんとしたものに仕上げて提出する」ってのもありますので、
// 「通り過ぎたものも綺麗に整えてから置いておく」ってのをぜひ意識してもらえればと思います。
// (プログラマーにはそれが大事だと思っているので)

// done ayamin メモtodoの識別よろしくお願いします by jflute (2025/07/15)

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
        // ★修正: TicketBooth に SystemClockProvider を渡す★
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? =>　9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        // ★修正: TicketBooth に SystemClockProvider を渡す★
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>10000
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        // ★修正: TicketBooth に SystemClockProvider を渡す★
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? =>null
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? =>9
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        // ★修正: TicketBooth に SystemClockProvider を渡す★
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
        log(sea); // should be max quantity, visual check here
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
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
        // done ayamin 変数が使われてないので、unused の警告がIDE上で出ています by jflute (2025/07/07)
        // (コメントアウトされた)元のコードで合わないところがあったら、合うように修正しちゃってください。

        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        int money = 14000;
        int change = booth.buyTwoDayPassport(money).getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money

        // and show two-day passport quantity here
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */

    // TODO[memo] ayamin 以下の点が重複しているはず
    //    チケット在庫のチェック
    //    支払われた金額が不足していないかのチェック
    //    チケット在庫を減らす
    //    売上金の更新
    //    どうやるか？カプセル化してメソッドを呼び出す
    // done ayamin [ふぉろー] このように小さな業務のまとまりを言語化して思考するの大切です。とても上手ですね。 by jflute (2025/07/07)
    public void test_class_letsFix_refactor_recycle() {
        // ★修正: TicketBooth に SystemClockProvider を渡す★
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
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
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */

    //TODO[memo] ayamin そもそもなぜTicketBuyResult.javaを作る必要があったのか？
    // Javaは、原則として一つの値しか直接返すことができない。そのままだとチケットとお釣りの両方を返せない
    // TicketBuyResultとして一つのクラスにお釣りとチケットを内包することで、クリアできる
    // done ayamin [ふぉろー] いいですね、そういうところに視点を置くというのは。 by jflute (2025/07/07)
    // TicketBuyResult は、物理の世界のチケットブースの、紙のチケットとお釣りを入れて戻す「青いトレー」みたいなイメージです。
    // 将来、レシートも戻すとか戻す項目が増えたとき、TicketBuyResultがあれば戻り値は変更せずにResultに追加するだけで済みます。
    // こういう風に、戻り値の「入れ物クラス」というのはよく使われます。
    public void test_class_moreFix_return_whole() {
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */

    public void test_class_moreFix_usePluralDays() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
        // TwoDayPassport を購入 (14000円支払い)
        TicketBuyResult buyResult = booth.buyTwoDayPassport(14000);
        Ticket twoDayPassport = buyResult.getTicket();

        log("--- TwoDayPassport Usage ---");
        log("定価：" + twoDayPassport.getDisplayPrice() + ",残り日数：" + twoDayPassport.getValidDays() + ",入園カウント："
                + twoDayPassport.getEntryCount() + ",入園したか？：" + twoDayPassport.isAlreadyIn());

        // 1回目入園
        twoDayPassport.doInPark();
        log(",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？："
                + twoDayPassport.isAlreadyIn()); // EntryCount=1, AlreadyIn=true

        // 2回目入園
        twoDayPassport.doInPark();
        log(",入園カウント：" + twoDayPassport.getEntryCount() + ",入園したか？："
                + twoDayPassport.isAlreadyIn()); // EntryCount=2, AlreadyIn=true

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
    }

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        // done ayamin NightOnlyは別なので、純粋に TwoDayPassport だけがヒットするようにしましょう by jflute (2025/07/07)
        // done jflute よくわからなかったので、1on1で一緒に確認させていただきたいです！ by ayamin (2025/07/08)
        // done ayamin りょうかいですー。説明の準備だけしておきます by jflute (2025/07/08)
        // チケットの種別は4種類。
        //  o OneDayPassport
        //  o TwoDayPassport
        //  o FourDayPassport
        //  o NightOnlyTwoDayPassport
        // TwoDayPassport と NightOnlyTwoDayPassport は日数が似てるけど、チケットとして別物。
        // なので、TwoDayPassport を判定するというときに、NightOnlyTwoDayPassport はヒットしてはいけない。
        // TODO ayamin 修行++: NightOnlyが紛れないようにしましょう by jflute (2025/07/15)
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
        // ★修正: TicketBooth に SystemClockProvider を渡す★
        TicketBooth booth = new TicketBooth(new SystemClockProvider());
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
        fourDayPassport.doInPark(); // ★修正: 引数を削除★
        log("入園数：" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark(); // ★修正: 引数を削除★
        log("入園数：" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark(); // ★修正: 引数を削除★
        log("入園数：" + fourDayPassport.getEntryCount());
        fourDayPassport.doInPark(); // ★修正: 引数を削除★
        log("入園数：" + fourDayPassport.getEntryCount());

        // 5回目
        try {
            fourDayPassport.doInPark(); // ★修正: 引数を削除★
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
    // done jflute ParkContextの変更に伴い、元のテストコードをコメントアウトしました by ayamin (2025/07/08)
    // done ayamin ありがとうございます！思考努力の結晶なので、コメントアウトで残すのGoodです。 by jflute (2025/07/08)
    // 業務だと消すでしょうけど、javatryではこういうのぜひコード上で残していきたいです。
    // ただ、やりかけなのか？みたいなのが読み手に判断がつかないと迷うので...
    // 「おもいでコメントアウト」お願いします(^^。「おもいで」って一言添えるだけ。
    // e.g.
    //  // おもいで
    //  //public void test_class_moreFix_wonder_night() {
    //  // ...
    //
    // こちらコンセプトです。「おもいで」の一言も十分読み手に情報を与えます(^^。
    // https://x.com/jflute/status/1322832458543325184

    // おもいで
    //    public void test_class_moreFix_wonder_night() {
    //        TicketBooth booth = new TicketBooth();
    //        int handedMoney = 10000;
    //
    //        log("--- 夜間 ---");
    //
    //        // 夜間チケットを夜間に使用するテスト
    //        ParkContext.setNight(true);
    //        log("現在の時間: 夜間");
    //        TicketBuyResult nightBuyResult = booth.buyNightOnlyTwoDayPassport(handedMoney);
    //        Ticket nightTicket = nightBuyResult.getTicket();
    //        int nightChange = nightBuyResult.getChange();
    //
    //        log("夜間専用TwoDayPassportの価格: " + nightTicket.getDisplayPrice());
    //        log("お釣り: " + nightChange);
    //        log("夜間専用か: " + nightTicket.isNightOnly());
    //        log("残りのチケット枚数: " + booth.getQuantity());
    //
    //        log("初期状態: 有効日数=" + nightTicket.getValidDays() + ", 入園回数=" + nightTicket.getEntryCount());
    //
    //        // 1回目入園 (夜間なので成功)
    //        nightTicket.doInPark();
    //        log("1回目の入園後 (夜間): 入園回数=" + nightTicket.getEntryCount());
    //
    //        // 2回目入園 (夜間なので成功)
    //        nightTicket.doInPark();
    //        log("2回目の入園後 (夜間): 入園回数=" + nightTicket.getEntryCount());
    //
    //        // 3回目入園 (有効回数超過なので失敗)
    //        try {
    //            nightTicket.doInPark();
    //            fail("有効日数を超えています");
    //        } catch (IllegalStateException e) {
    //            log("有効に数を超えています " + e.getMessage());
    //        }
    //        log("3回目の入園試行後: 入園回数=" + nightTicket.getEntryCount());
    //
    //        // 2. 夜間チケットを昼間に使用するテスト
    //        ParkContext.setNight(false);
    //        log("現在の時間: 昼間");
    //        TicketBuyResult anotherNightBuyResult = booth.buyNightOnlyTwoDayPassport(handedMoney);
    //        Ticket anotherNightTicket = anotherNightBuyResult.getTicket();
    //
    //        log("別の夜間専用TwoDayPassportの価格: " + anotherNightTicket.getDisplayPrice());
    //        log("夜間専用か: " + anotherNightTicket.isNightOnly());
    //
    //        try {
    //            anotherNightTicket.doInPark(); // 昼間なので例外が発生するはず
    //            fail("夜間専用チケットなので使えません。");
    //        } catch (IllegalStateException e) {
    //            log("夜間専用チケットなので使えません。: " + e.getMessage());
    //        }
    //        log("入園回数=" + anotherNightTicket.getEntryCount());
    //    }
    public void test_class_moreFix_wonder_night() {
        int handedMoney = 10000;

        // --- シミュレーション時刻の定義 ---
        LocalTime nightTime = LocalTime.of(19, 0, 0); // 夜間としてテストする時刻 (午後7時)
        LocalTime dayTime = LocalTime.of(10, 0, 0);  // 昼間としてテストする時刻 (午前10時)
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

    //done jflute さん
    //ここは解いていません。1on1時にご解説いただけますと幸いです
    // done ayamin [へんじ] だいぶ全体的に綺麗にできています。気になるところはt.odo入れていきますね。 by jflute (2025/07/07)
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
