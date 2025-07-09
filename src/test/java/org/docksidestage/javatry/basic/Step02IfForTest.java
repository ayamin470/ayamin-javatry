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

import org.docksidestage.unit.PlainTestCase;

// done ayamin javadocのauthorお願いしますー by jflute (2025/07/02)
/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author ayamin
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904)
        {
            sea = 2001;
        } else
        {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904)
        {
            sea = 2001;
        } else if (sea >= 904)
        {
            sea = 7;
        } else if (sea >= 903)
        {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904)
        {
            sea = 2001;
        } else if (land && sea >= 904)
        {
            sea = 7;
        } else if (sea >= 903 || land)
        {
            sea = 8;
            if (!land)
            {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
    }
    // done jflute 1on1にて、ソースコードリーディングの話の補足をする予定 (2025/07/02)
    // (これはぼくのtodoになるので、そのまま残しておいてください)
    // #1on1 漠然読み: コード構造だけ見て、フォーカスを当てられるところを見つけたら...
    // そのフォーカスしたものだけを追っていく。(逆読みする場合は)

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList)
        {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
    }
    /** 拡張forループ(:) 要素をstageという変数に一つずつ取り出しながら、リストの最後に当たるまでforループを回す */

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
    }
    /**
     * StringBuilder：Stringと異なり変更可能。
     * 文字列を追加したり、削除したり、置き換えたりしても、通常は新しいオブジェクトを生成せず、元のオブジェクトの中身を直接変更
     */

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> stageList = prepareStageList();
        List<String> aContainedList = new ArrayList<>();
        // done ayamin sea変数はすでに使われてないので、IDE上でunusedの警告が出ているので削除でお願いします by jflute (2025/07/02)
        // 確認しました！by ayamin (2025/07/08)
        for (String stage : stageList) {
            if (stage.contains("a"))
            {
                aContainedList.add(stage);
            }
        }
        for (String aStage : aContainedList)
        {
            log(aStage);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */

    //TODO ayamin 最終的なseaの値：hanger "br"で始まらず、かつ最初に"ga"を含む要素が見つかったその時の要素
    public void test_iffor_refactor_foreach_to_forEach()
    {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // should be same as before-fix
    }
    public void test_original_forEach()
    {
        // done ayamin [いいね] おおぉ、すごい頑張りましたね！mutableな配列で回避するとは by jflute (2025/07/02)
        // done ayamin 修行++: もう少し悩んでもらうために...breakFlag[]なくても同じ挙動実現できるはずです by jflute (2025/07/02)
        // // done jflute [test_original2_forEach] として、breakFlag[]なしで挙動を再現しました by ayamin (2025/07/08)
        // done jflute 1on1にてふぉろー予定 (Stream APIの実装はそれはそれでGoodです、素晴らしい) (2025/07/08)
        // #1on1: breakFlag使わなくても良い方法のお話をさせて頂きました (2025/07/09)
        // #1on1: breakFlagって名前じゃなくて、どんな状態なのか？(e.g. gaComming)って名前を付けていたら...
        // trueのときにやる処理の名前を付けるんじゃなくて、状態の方を示す名前を付けていたら気付いていたかも。
        // #1on1: booleanの変数名の付け方、個人的には極力、その先の処理よりも、その状態を純粋に示す方がわかりやすいかな
        // ちょっと今回の話と少し違いものですが、booleanの変数名にまつわるブログ:
        // TODO ayamin [読み物課題] なんとかフラグというboolean変数名 by jflute (2025/07/09)
        // https://jflute.hatenadiary.jp/entry/20181013/flgornuance

        List<String> stageList = prepareStageList();
        final String[] resultSea = new String[1];
        final boolean[] breakFlag = new boolean[1];

        stageList.forEach(stage -> {
            if (breakFlag[0]) {
                return;
            }
            if (stage.startsWith("br")) {
                return;
            }
            resultSea[0] = stage;

            if (stage.contains("ga")) {
                breakFlag[0] = true;
            }
        });

        String sea = resultSea[0];
        log(sea);
    }
    // TODO auamin 最終的なseaの値：hanger "br"で始まらず、かつ最初に"ga"を含む要素が見つかったその時の要素
    //  breakFlag を使わずに、Stream API の reduce を用いて同じ結果を得る

    public void test_original2_forEach() {
        List<String> stageList = prepareStageList();

        String sea = stageList.stream()

                .filter(stage -> !stage.startsWith("br"))

                .reduce((lastValidStage, currentStage) -> {

                    if (lastValidStage.contains("ga")) {
                        return lastValidStage;
                    }

                    if (currentStage.contains("ga")) {
                        return currentStage;
                    }

                    return currentStage;
                })
                // ストリームが空の場合やreduceで結果が得られない場合にnullを返す
                .orElse(null);

        log(sea); // "hangar" が出力されるはず
    }


    public void test_original_forEach2()
    {
    }

    /**
     * なぜ「boolean breakFlag = false;」ではなくfinal boolean[] breakFlag = new boolean[1];で配列にしているのか？
     * ラムダ式のルール上、外側のローカル変数を直接変更できないため
     * 配列の中身は上記の制約を受けないのでこのように書く
     */
    // done ayamin [いいね] コードの意図をコメントで表現されてる読み手はとても助かりますね！ by jflute (2025/07/02)

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * oが入るものだけを出力しなさい
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise()
    {
        List<String> stageList = prepareStageList();
        // done ayamin "a" Contained じゃなくて "o" Contained ですかね、ここでは by jflute (2025/07/02)
        List<String> oContainedList = new ArrayList<>();
        // done ayamin sea変数はすでに使われてないので、IDE上でunusedの警告が出ているので削除でお願いします by jflute (2025/07/02)
        for (String stage : stageList) {
            if (stage.contains("o"))
            {
                oContainedList.add(stage);
            }
        }
        for (String aStage : oContainedList)
        {
            log(aStage);
        }

    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
