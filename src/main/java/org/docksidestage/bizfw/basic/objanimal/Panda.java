// src/main/java/org/docksidestage/bizfw/basic/objanimal/Panda.java

package org.docksidestage.bizfw.basic.objanimal;

// TODO done ayamin ↑import分のunusedの警告が出ちゃってます。(logger消したときに残っちゃったか) by jflute (2025/07/16)

/**
 * The object for panda (パンダ).
 * (FastRunnerではないAnimalクラスのコンクリートクラスの例)
 * @author ayamin
 */
public class Panda extends Animal { // Animalクラスを継承

    // done ayamin unusedの警告が出ています。最初使おうとして持ってきたけど結局使わなかったのでしょうか？ by jflute (2025/07/07)
//    private static final Logger logger = LoggerFactory.getLogger(Panda.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Panda() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        // done ayamin [ざつだん] そういえばパンダの鳴き声って全然知らない...＞＜ by jflute (2025/07/07)
        // done jflute  [ざつだん] 私も知らなくて調べたのですが、パンダはにゃん！と鳴くらしいです。だから熊猫なのかも...?
        // なんとー、そうだったのかー (^^
        return "gao gao"; // パンダの鳴き声
    }
    // done ayamin ここの空行は削除で by jflute (2025/07/07)
}
