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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author ayamin
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で　出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */

    // TODO[memo]ayaminコールバック処理とは?
    //  ある処理が完了した後に、別の特定の処理（関数やメソッド）を呼び出す仕組みのこと
    //  時間のかかる処理を待っている間にプログラム全体が停止してしまうとユーザ体験が悪くなりがちなので使用する

    // #1on1: step6 の Animal の BarkingProcess のところと一緒にコールバックの補足した (2025/07/18)
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title));  //新しくインスタンスを作った上でtitleを渡す

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() {
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        });

        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        });

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));

        // your answer? => yes

        // cannot reassign because it is used at callback process
        //title = "wave";
    }

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor, broadway, dockside, hangar, lost river
    }

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    /**
     * 後で呼び出す処理
     */
    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? => number: 7
    }

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        helpCallbackSupplier(new Supplier<String>() { // sea
            public String get() {
                return "broadway";
            }
        });

        helpCallbackSupplier(() -> { // land
            return "dockside";
        });

        helpCallbackSupplier(() -> "hangar"); // piari
    }

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */

    /**
     * Optionalクラスは「値が存在しない可能性がある」を明示的に表現する
     * そもそも：メソッドの戻り値が null である場合、それが「エラー」なのか、「単に値が見つからなかった」のか、あるいは「まだ値が設定されていない」のか、コードを読んだだけでは分かりにくい
     * 明示的に表現して、少なくとも値が存在するかどうかを表現し、nullチェックの代わりにOptionalが提供するメソッドを使って処理を記述できる
     */

    /**
     * ここでは、selectMember(1)というメソッドでIDが1の会員情報を取ってきています。このメソッドは、会員が見つからなくても**nullを返しません**。代わりに、Optionalという「箱」に会員情報を入れて返してくれます。
     * 会員が見つかった場合：箱の中に会員情報が入っています。
     * 会員が見つからなかった場合：箱は空っぽです。
     * だから、「もしoptMemberの箱が空っぽじゃなかったら（つまり、isPresent()がtrueだったら）」という条件で、箱の中からget()を使って会員情報を取り出し、そのIDと名前を表示しています。
     */

    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName());
        }
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        // your answer? => Yes(上も下もやっていることは同じ、oldmemberという箱を用意してNPEを出さないようにしているかどうかの違い)
    }

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        optMember.ifPresent(member -> {
            log(member.getMemberId(), member.getMemberName());
        });
        // your answer? => Yes(理由は前回の問題と同じ)
    }

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */

    /**
     * map()とflatMap()に関する問題
     * map()とflatMap()はどちらも、Optionalという箱の中に値が入っていた場合に、その値を加工（変換）して、新しいOptionalの箱に入れて返すためのメソッド
     * map(): 値を普通の変換（Optionalを返さないメソッド）をして、結果を新しいOptionalに包み直したいとき。
     * flatMap(): 値を変換した結果がすでにOptionalになっている場合で、それを平らにして（一つにまとめて）次のOptionalとして扱いたいとき。
     * map()使用例： Optional<ユーザー>から、ユーザーの名前（String）や年齢（Integer）を取り出す場合。
     * flatMap()使用例： Optional<ユーザー>から、そのユーザーが持つOptional<住所>を取り出す場合。もしgetAdress()がOptional<Address>を返すならflatMap()を使う
     */

    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // traditional style
        St8Member oldmemberFirst = facade.oldselectMember(1);
        String sea;
        if (oldmemberFirst != null) { //true
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal(); // St8Withdrawal(11, "music")
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason(); //"music"
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        Optional<St8Member> optMemberFirst = facade.selectMember(1);

        /**
         * String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())の処理について
         * St8Memberをmbという名前にして矢印の左側に渡す
         * 渡されたmbを使ってoldgetWithdrawal()関数を処理
         * 処理の結果が.mapされ、landに代入される
         * 代入されたものが下の行に渡され.mapへ
         *
         * orElse()は、「もし箱が空っぽだったら、ここに書かれているデフォルトの値を使ってね。でも、もし箱に値が入っていたら、その中身の値をそのまま使ってね」という指示
         */

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())//optMemberFirstにはSt8Withdrawal(11, "music")が入っている
                .map(wdl -> wdl.oldgetPrimaryReason())//"music"
                .orElse("*no reason: someone was not present"); //"music"

        // flatMap style
        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String dstore = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        /**
         * 途中の Optional が空っぽになった場合、その後の処理（ラムダ式の実行）は一切行われず、空っぽの Optional がそのまま次のメソッドに引き継がれます
         * String ambaの.flatMapの時点で箱は空っぽなので、(wdl -> wdl.getPrimaryReason()のラムダ式は実行されない
         * 空っぽということでorElse() が実行される
         */

        String amba = facade.selectMember(3)
                .flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.getWithdrawalId()) // ID here
                .orElse(defaultWithdrawalId);

        log(sea); // your answer? => music
        log(land); // your answer? => music
        log(piari); // your answer? => music
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => *no reason: someone was not present
        log(amba); // your answer? => *no reason: someone was not present
        log(miraco); // your answer? => 12
    }

    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */

    /**
     *　orElseThrow() メソッド
     * もしOptionalの箱が空っぽだったら、指定したエラー（例外）を発生させて処理を中断する
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2);
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over"));
        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });
            sea = reason;
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => wave
    }

    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {
                oldfilteredNameList.add(member.getMemberName());
            }
        }
        String sea = oldfilteredNameList.toString();
        log(sea); // your answer? => [broadway, dockside]

        List<String> filteredNameList = memberList.stream() //
                .filter(mb -> mb.getWithdrawal().isPresent()) //
                .map(mb -> mb.getMemberName()) //
                .collect(Collectors.toList());
        String land = filteredNameList.toString();
        log(land); // your answer? => [broadway, dockside]
    }

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */

    /**
     * Stream API
     * 詳しくはGeminiの回答を参照
     */
    // done ayamin flatmapした後にfilterしてるのがよくわからない...なんでそんなことするんだろう
    // done ayamin [ふぉろー] フラットな購入の一覧状態にしないと、purchaseIdを使ったfilterをすることができないから、という感じでしょうか by jflute (2025/07/24)
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        int sea = memberList.stream()
                .filter(mb -> mb.getWithdrawal().isPresent())//mbという会員が退会しているかどうか
                .flatMap(mb -> mb.getPurchaseList().stream()) //ここがわからん。「mbという会員からその購入履歴のリスト（getPurchaseList()）を取り出し、さらにその購入リストをストリームに変換」するらしいけど、なぜそんなことする？
                .filter(pur -> pur.getPurchaseId() > 100)
                .mapToInt(pur -> pur.getPurchasePrice())
                .distinct()
                .sum();
        log(sea); // your answer? =>
        // done jflute 1on1にて、mapとflatMap()の違いについてフォロー予定 (2025/07/24)
        // #1on1: がっつりmapとflatMap()の話しして、ayamin様に楽しんで頂けて光栄ございます (2025/08/22)
        // https://dbflute.seasar.org/ja/manual/topic/programming/java/java8/mapandflat.html
    }

    // *Stream API will return at Step12 again, it's worth the wait!
}
