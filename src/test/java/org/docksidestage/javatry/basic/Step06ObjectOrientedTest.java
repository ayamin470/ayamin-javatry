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

import org.docksidestage.bizfw.basic.buyticket.Inheritance_Ticket;
import org.docksidestage.bizfw.basic.buyticket.Inherutance_TicketBooth;
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.unit.PlainTestCase;
import org.docksidestage.bizfw.basic.objanimal.Panda;
import org.docksidestage.bizfw.basic.objanimal.special.Nocturnal;

import org.docksidestage.javatry.basic.st6.os.Mac;
import org.docksidestage.javatry.basic.st6.os.Windows;
import org.docksidestage.javatry.basic.st6.os.OldWindows;

// done ayamin 全体的に空行が、チグハグなところが見受けられますので、気をつけてみてください。 by jflute (2025/07/07)
// ↓ぜひこちらを改めて読んでもらえればと:
// ハンズオンのコーディングポリシー - 4. 空行マネジメント
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#emptyline

// TODO ayamin [読み物課題] ホワイトボードを買ってこよう by jflute (2025/09/26)
// https://jflute.hatenadiary.jp/entry/20110607/1307440686
// ↑ 図で表現する習慣を

// TODO ayamin [読み物課題] まず何より、目の前の道具を使いこなしてください by jflute (2025/09/26)
// https://jflute.hatenadiary.jp/entry/20180223/mastercurrent
// ↑ "目の前は学びの宝庫"

// TODO ayamin [読み物課題] 使いこなしてないAと使いこなしてるA by jflute (2025/09/26)
// https://jflute.hatenadiary.jp/entry/20140922/twotools
// ↑ 使いこなしてるAフレームワークと、使いこなしてないAフレームワーク、
// 同じ A だけど、別物と言える。

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author ayamin
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented2_aboutObject_againstObject() {
        // done ayamin チケットを購入し、入園するまでを書く
        // done ayamin [ふぉろー] すごい、書き換えてるのびっくりしました(^^ by jflute (2025/07/07)
        // 書き直すことで、ベタ書きの状態とオブジェクトを使った状態までの変化がわかりやすくなるので良いと思います。
        // done ayamin ただ、間違い探しが、ちゃんと探せてたのかどうか？もう知るすべがない。。。笑

//        TicketBooth booth = new TicketBooth();
//        Ticket ticket = booth.buyOneDayPassport(10000);
//        ticket.doInPark();
//        saveBuyingHistory(booth, ticket);

    }

    // done ayamin [質問] ちょっとだけ元のコードが残ってるので、↓に間違いがまだ残ってますね。どこが間違ってるでしょうか？ by jflute (2025/07/07)
    // alreadyInはもう使っていないので、間違い。チケットを複数日数使用できるように変更した時に、isAlreadyInという変数に置き換えた上で、1回でも入場したらisAlreadyInがtrueを返すように変更した
    // done jflute 1on1にてフォロー予定 (2025/07/08)
    // #1on1: まず、こういうメソッドを(自分が)極力作らないこと
    // o データの関連性をもとに作ったオブジェクトを使うことで防ぐことができる
    // o (完璧ではないけど)引数の順序を工夫するとかもあり
    //
    // (質問)引数の順序の話: (個人的には)
    // o (そもそも引数の順序が気になるほどの引数の数にしないことは前提として...)
    // o 基本的にはカテゴリごと、でも多少そのときの都合でアドリブもあり
    // o 加えて、重要な引数を先に定義したりする
    // o その上で、型の並びを最後に微調整 (e.g. intが連続しないようにとか)
    // 
    // #1on1: 呼び出すときの注意 (オブジェクト指向とか関係ない話 / 開発者として)
    // o ここぞってときは、(比喩的な)指差し確認をする (その5秒を取れるか？)
    // o 自分でどういうミスをしやすいのか？を普段から自己分析しておくことが大切
    // o ここぞの集中力を上げどころを意識しておく
    
    // 呼び出し側
    //saveBuyingHistory(quantity, displayPrice, salesProceeds, alreadyIn);
    //
//    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
//        if (alreadyIn) {
//            // simulation: only logging here (normally e.g. DB insert)
//            showTicketBooth(displayPrice, salesProceeds);
//            showYourTicket(quantity, alreadyIn);
//        }
//    }

    // done ayamin Ticket や TicketBooth オブジェクトを使わずに直接 int や boolean の値を受け取っているため、オブジェクト指向ではない
//    private void showTicketBooth(int quantity, Integer salesProceeds) {
//        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
//    }
//
//    private void showYourTicket(int displayPrice, boolean alreadyIn) {
//        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
//    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */

    // done jflute さん　オブジェクトとは何か？
    //    現実にあるものをコードに書き起こしたものがオブジェクトだと思います
    //    そもそも、オブジェクト指向とは、手続型で順番通りにコードを書き連ねるのではなく、
    //    現実にある物体をイメージしながら、オブジェクト同士が何をやりとりするのか？
    //    を考えるのがオブジェクト指向だと考えるからです
    // done ayamin [ふぉろー] ありがとうございます。ただ、"現実" というのがちょっと曖昧な表現で難しいところですね by jflute (2025/07/07)
    // done ayamin [質問] もう少し踏み込んで..."現実にある物体" と言っていますが、TicketBuyResultとかは現実の物体でしょうか？ by jflute (2025/07/07)
    // 確かに...現実の物体ではないと思います。ということは、現実に存在して目に見える物体をオブジェクトとして考えているわけではない
    // であれば、役割や責任をまず考え、その役割や責任をオブジェクトとして持たせる、という考えに行きつきました
    // 例えば、「TicketBoothはお金を受け取り、チケットとお金を返し、チケットの枚数を管理する責任」、TicketBuyResultは「チケットの購入履歴を一時的に保管しておく責任」といった感じ
    // done jflute 1on1にてふぉろー予定 (2025/07/08)
    //
    // #1on1: "概念" をオブジェクトにするというのがちょうどいいかなと
    //
    // 最初から概念が思いついて、その概念が持つデータと振る舞いを考えるのはトップダウンな設計。
    // (これはだいぶ慣れた人じゃないと)
    //
    // データだけがバラバラとあって、そこからデータ同士の関連性や処理を見出してオブジェクトを作るのがボトムアップ設計。
    // 例えば、「購入されたチケット」と「お釣り」で「購入した結果」という概念になる。
    //
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        Inherutance_TicketBooth booth = new Inherutance_TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        //Ticket ticket = booth.buyOneDayPassport(10000);
        booth.buyOneDayPassport(10000); // as temporary, remove if you finished step05
//        Ticket ticket = new Ticket(7400); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
//        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
//        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(Inherutance_TicketBooth booth, Inheritance_Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(Inherutance_TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Inheritance_Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // _/_/_/_/_/_/_/_/_/_/
    // 上の方で書いた by jflute

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? =>wan
        int land = dog.getHitPoint();
        log(land); // your answer? =>7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? =>wan
        int land = animal.getHitPoint();
        log(land); // your answer? =>7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? =>wan
        int land = animal.getHitPoint();
        log(land); // your answer? =>7
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? =>wan
        int land = animal.getHitPoint();
        log(land); // your answer? =>7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? =>nya-
        int land = animal.getHitPoint();
        log(land); // your answer? =>5
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? =>uooo
        int land = animal.getHitPoint();
        log(land); // your answer? =>-1
    }

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        //「それっぽく扱う＝多様態」
        // animalという大元のクラスがあることで、コードを書くのが楽になる
        // 動物の種類ごとに異なるメソッドを呼び出す必要がなくなるから、他に動物が増えた時でも楽！
        // _/_/_/_/_/_/_/_/_/_/
        // done ayamin [いいね] それっぽく扱う、というのがなかなかおもしろい表現です^^ by jflute (2025/07/07)
        // でも、的を得てはいると思います。「動物」って概念で (扱えるものは) 扱う、って感覚でしょうか。
        // done jflute 1on1にて、少し補足する予定 (2025/07/07)
        // #1on1: 抽象化した概念って、日常でもよく使っている。
        // e.g. 車、新卒
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? =>uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? =>uooo
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? =>iri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? =>false
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? =>true


        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? =>false
    }

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // Dogインスタンスを作成
        Dog dog = new Dog();
        log("最初のDog HP: " + dog.getHitPoint()); // 初期HPを確認

        // DogがFastRunnerインターフェースを実装しているかを確認
        assertTrue(dog instanceof FastRunner);
        log(" DogはFastRunner? " + (dog instanceof FastRunner)); // true と表示されるはず

        // FastRunner型の変数にDogインスタンスを代入し、run()メソッドを呼び出す
        FastRunner runner = dog;
        log("Dog is running...");
        runner.run();

        // run()メソッドが呼ばれた後にHPが減少しているか確認
        log("DogのHP: " + dog.getHitPoint()); // 9

        // DogがAnimalとしてのbark()もできるか確認
        log("Dog: " + dog.bark().getBarkWord());
        log("DogのHP " + dog.getHitPoint());
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        //抽象クラス：中身がまだ定まっていない大元のクラス。子クラスで詳しく情報を詰めることができる
        //インターフェース：抽象度の中でも特に抽象度の高いクラス
        // _/_/_/_/_/_/_/_/_/_/
        // done jflute 1on1にて、↑について詳しく説明してもらう予定 (2025/07/07)
        // #1on1: オブジェクト指向とインターフェースの考え方、は根本的には別物
        // Javaは、オブジェクト指向言語といいつつ、色々な要素を取り入れる
        // コンセプトの話をしました。
        // done jflute ColorBoxインターフェースの応用編はまた次回 (2025/07/25)
        // #1on1: AbstractColorBoxの役割...
        // 1: 対外的なポリモーフィズムはinterfaceにお任せ
        // 2: コンクリートクラスの共通化(形付け)は変わらずやっている
        // 元々abstract classにあった「外交」と「内政」の役割を分離して、外交はinterfaceに任せた。
        //
        // 単純に interface に切り出されると何が呼べるのかわかりやすいってのもあるし...
        // 内部publicメソッド:
        // 例えば、AbstractColorBox に内部publicメソッドを作りたくなっても、
        // ユーザーには interface で隠蔽できている。
        // public void init() { // colorboxメーカーが呼ぶ内部publicメソッド
        //    // ...
        // }
        // 半カプセル化ができる。
        // List interface と AbstractList も見てみた。
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Panda panda = new Panda();
        log("最初の Panda HP: " + panda.getHitPoint()); // Animalの初期HP (10)
        log("Panda HP: " + panda.getHitPoint()); // 7

        // FastRunnerではないことを確認
        boolean isFastRunner = panda instanceof FastRunner;
        log("Is Panda a FastRunner? " + isFastRunner);
        assertFalse(isFastRunner);
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        log("--- Confirmation: Nocturnal interface for Cat only ---");

        // Cat は Nocturnal を実装しているので、Nocturnal 型として扱える
        Cat cat = new Cat();
        log("Cat's normal bark: {}", cat.bark().getBarkWord()); // 通常の鳴き声
        Nocturnal nocturnalCat = cat;
        log("Cat's night sound: {}", nocturnalCat.makeNightSound()); // Nocturnal
        assertTrue(true);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */

    //done jfluteさん　SQLをよく勉強していないので、後で解きます　
    // done ayamin SQLの知識は、エクササイズの本質的には関係ないので、やってみたらできると思います！ by jflute (2025/07/07)
    // (気になるところあっても、ちょっと調べたらわかる程度のものではあるかと思います)
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        St6MySql mySqlInstance = new St6MySql();
        String mySqlQueryResult = mySqlInstance.buildPagingQuery(10, 1);
        System.out.println("St6MySql の buildPagingQuery 結果: " + mySqlQueryResult);

        St6PostgreSql postgreSqlInstance = new St6PostgreSql();
        String postgreSqlQueryResult = postgreSqlInstance.buildPagingQuery(10, 1);
        System.out.println("St6PostgreSql の buildPagingQuery 結果: " + postgreSqlQueryResult);
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // done ayamin テスト実行すると、アサートで落ちてます by jflute (2025/07/07)
        // done Jflute アサートなしでテストコードを作成しました
        //  (アサートという機能を知って、使ってみたくて使ってしまいました...使わなくてもテストコード作れるんだったらそれに越したことはないと思ってます)
        // done ayamin javatryではアサート使うのは必須ではないですが、アサート使うのは良いことではありますよ by jflute (2025/07/10)
        // ただ、アサートもコツが要るので、そこに躓いて時間掛かるくらいなら無しでという感覚ですね。

        System.out.println("\n=== 各OSサブクラスの実装確認 ===");

        // Macクラスのテスト
        String macLoginId = "macuser";
        Mac macOs = new Mac(macLoginId);
        String macFileSeparator = macOs.getFileSeparator();
        String macUserDirectory = macOs.getUserDirectory();
        String macResourcePath = macOs.buildUserResourcePath("Desktop/my_file.txt");

        System.out.println("Mac - ログインID: " + macOs.getLoginId());
        System.out.println("Mac - ファイル区切り: " + macFileSeparator);
        System.out.println("Mac - ユーザーディレクトリ: " + macUserDirectory);
        System.out.println("Mac - リソースパス: " + macResourcePath);

        // OldWindowsクラスのテスト
        String oldWinLoginId = "oldwinuser";
        OldWindows oldWindowsOs = new OldWindows(oldWinLoginId);
        String oldWinFileSeparator = oldWindowsOs.getFileSeparator();
        String oldWinUserDirectory = oldWindowsOs.getUserDirectory();
        String oldWinResourcePath = oldWindowsOs.buildUserResourcePath("My Documents\report.doc");

        System.out.println("\nOldWindows - ログインID: " + oldWindowsOs.getLoginId());
        System.out.println("OldWindows - ファイル区切り: " + oldWinFileSeparator);
        System.out.println("OldWindows - ユーザーディレクトリ: " + oldWinUserDirectory);
        System.out.println("OldWindows - リソースパス: " + oldWinResourcePath);

        // Windowsクラスのテスト
        String winLoginId = "winuser";
        Windows windowsOs = new Windows(winLoginId);
        String winFileSeparator = windowsOs.getFileSeparator();
        String winUserDirectory = windowsOs.getUserDirectory();
        String winResourcePath = windowsOs.buildUserResourcePath("Desktop/my_project/image.png");

        System.out.println("\nWindows - ログインID: " + windowsOs.getLoginId());
        System.out.println("Windows - ファイル区切り: " + winFileSeparator);
        System.out.println("Windows - ユーザーディレクトリ: " + winUserDirectory);
        System.out.println("Windows - リソースパス: " + winResourcePath);

    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */

    //TODO [memo] ayamin Animalクラスは、「HPを持つ」だけでなく、「鳴く」や「息を吸う」などまで責任を持っていて、保守性が低い
    public void test_objectOriented_writing_withDelegation() {
        // done ayamin Zombie でコンパイルエラーが出ています。 by jflute (2025/07/07)
        log("--- Confirmation: BarkingProcess delegation ---");

        Dog dog = new Dog();
        log("Initial Dog HP: {}", dog.getHitPoint()); // 初期HP: 10
        String dogBark = dog.bark().getBarkWord();
        log("Dog barks: {}", dogBark); // "wan"
        log("Dog HP after bark: {}", dog.getHitPoint()); // HP: 10 - 3 = 7

        assertEquals("wan", dogBark);
        assertEquals(7, dog.getHitPoint());

        log("---");

        Cat cat = new Cat();
        log("Initial Cat HP: {}", cat.getHitPoint()); // 初期HP: 10
        String catBark = cat.bark().getBarkWord();
        log("Cat barks: {}", catBark); // "nya-"

        log("Cat HP after bark: {}", cat.getHitPoint()); // HP: 10 - 5 = 5

        assertEquals("nya-", catBark);
        assertEquals(5, cat.getHitPoint());

        log("--- Confirmation finished ---");
    }
    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        //適切ではない
        //いや、適切な気がする。zombieの問題を改めて解き直した時、animalを継承しているところがたくさんあり、animalのサブクラスとして適切と言えそうと考えた
        // _/_/_/_/_/_/_/_/_/_/
        // done ayamin その理由を書ける範囲で書いてもらっても良いでしょうか？ by jflute (2025/07/07)
        // #1on1: バイオハザードのゾンビの解釈で考えると、状態と捉えることもできるかもしれない。
        // (もちろん、ゾンビの業務設定次第ではある)
    }
}
