// src/main/java/org/docksidestage/bizfw/basic/objanimal/Panda.java

package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for panda (パンダ).
 * (FastRunnerではないAnimalクラスのコンクリートクラスの例)
 * @author ayamin
 */
public class Panda extends Animal { // Animalクラスを継承

    // TODO done ayamin unusedの警告が出ています。最初使おうとして持ってきたけど結局使わなかったのでしょうか？ by jflute (2025/07/07)
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
        // TODO done ayamin [ざつだん] そういえばパンダの鳴き声って全然知らない...＞＜ by jflute (2025/07/07)
        // TODO jflute  [ざつだん] 私も知らなくて調べたのですが、パンダはにゃん！と鳴くらしいです。だから熊猫なのかも...?
        return "gao gao"; // パンダの鳴き声
    }
    // TODO done ayamin ここの空行は削除で by jflute (2025/07/07)
}
