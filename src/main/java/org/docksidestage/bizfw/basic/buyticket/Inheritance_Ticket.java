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
package org.docksidestage.bizfw.basic.buyticket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// done ayamin [いいね] javadoc素晴らしい、特に2行目の補足の抽象度が良い by jflute (2025/08/06)
// #1on1: javadoc, 読み手を想像して書く (2025/08/06)
// #1on1: javadocを見る意義、ググってもAIでも、レアなクラス、業務内のクラスはわからない (2025/08/06)
// #1on1: 本気のJavaDocの例
// https://github.com/lastaflute/lastaflute-example-maihama/blob/master/maihama-orleans/src/main/java/org/docksidestage/bizfw/job/contributed/NxBatchRecorder.java

// #1on1: authorの話。
// 「責任が付く」by あやみんさん => That's right!
// ちょっと行動経済学のお話。名前を書くことで、後ろめたさが消える (責任が心理的に生まれやすい) みたいな話。
// 「テストのときも名前を書くとピリっとする」by あやみんさん => 素晴らしい、それそれ。
// (メソッド、下に追加されがち問題のお話)
// 既存コード修正するときに、そのクラスに対する責任をほんの少しでも持ってほしい。authorがそのチリも積もればのチリ。
/**
 * パークのチケットを表すクラス
 * チケットの情報(有効日数、価格など)と、入園処理を定義
 * @author jflute
 * @author ayamin
 */
public abstract class Inheritance_Ticket {
    
    // done ayamin 一応、インスタンス変数、コンストラクター、メソッドというの定義順序の基本ではあるので... by jflute (2025/09/05)
    // 少なくとも、コンストラクターよりも下のどこかに定義しましょう。
    // done jflute [質問] 調べたところではAttributeとインスタンス変数は厳密には異なるようですが、ここでは同じ意味で使っていますか？ by ayamin (2025/09/30)
    // #1on1: オブジェクト指向用語(概念世界の用語)、プログラミング用語(Java用語)、Attributeは属性。
    
    // done ayamin あごが開いている by jflute (2025/09/05)
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int validDays;
    private final boolean nightOnly;
    private int entryCount;
    private final Util_ClockProvider clockProvider;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // #1on1: コンストラクターが一個しか無いので、重要なの@param, 説明がなくてもいいかも
    // 一方で、TicketBoothみたいに、コンストラクターあって用途の区別があるのであればそれは説明したい。
    // #1on1: (NotNull) の話
    public Inheritance_Ticket(int displayPrice, int validDays, boolean nightOnly, Util_ClockProvider clockProvider) {
        this.displayPrice = displayPrice;
        this.validDays = validDays;
        this.nightOnly = nightOnly;
        this.entryCount = 0;
        this.clockProvider = clockProvider;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======

    public abstract String getTicketTypeDisplayName();

    /**
     * チケットを使って遊園地に入園する
     * @throws IllegalStateException 入園条件を満たさない場合。
     */
    public void doInPark() {
        if (entryCount >= validDays) {
            throw new IllegalStateException("有効日数を超えています。現在の入園カウント: " + entryCount + ", 有効日数: " + validDays);
        }
        
        LocalTime currentTime = clockProvider.getCurrentTime();
        if (nightOnly && Util_DayNightChecker.isDay(currentTime)) {
            // done ayamin 変数は先頭小文字が慣習 by jflute (2025/08/06)
            // done ayamin 同じ概念でも型違いの変数が同じスコープに入るときは名前で区別しましょう by jflute (2025/08/06)
            //  e.g. currentTimeStr, currentTimeDisp, currentTimeExp
            String currentTimeStr = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            String errorMessage = "このチケットは夜間専用です。現在時刻(" + currentTimeStr + ")は昼間のため使用できません。";
            throw new IllegalStateException(errorMessage);
        }
        entryCount++;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // #1on1: getterのjavadoc, 書かれないことが多いけど...特に業務の項目に関しては、javadocあると嬉しい。
    // #1on1: privateのインスタンス変数にあるよりかは、publicのgetterにある方が呼び出し側にとって嬉しい。
    // #1on1: オウム返し問題の話、個人的には@returnのみのjavadocでもいいかなと思う、よくやる。
    /**
     * チケットに表示されている価格を取得
     * @return チケットの表示価格
     */
    public int getDisplayPrice() {
        return displayPrice;
    }
    /**
     * 一度でも入園したことがあるかを判定
     * @return 入園済みであればtrue
     */
    public boolean isAlreadyIn() {
        return entryCount > 0;
    }
    /**
     * まだ入園できるかを判定
     * @return 入園可能であればtrue
     */
    public boolean canEnterPark() {
        return entryCount < validDays;
    }
    /**
     * チケットの有効日数を取得
     * @return 有効日数
     */
    public int getValidDays() {
        return validDays;
    }
    /**
     * 現在の入園カウントを取得
     * @return 入園した回数
     */
    public int getEntryCount() {
        return entryCount;
    }
    /**
     * このチケットが夜間専用であるかを判定
     * @return 夜間専用であればtrue
     */
    public boolean isNightOnly() {
        return nightOnly;
    }
}
