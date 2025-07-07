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

    // TODO ayamin unusedの警告が出ています。最初使おうとして持ってきたけど結局使わなかったのでしょうか？ by jflute (2025/07/07)
    private static final Logger logger = LoggerFactory.getLogger(Panda.class);

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
        // TODO ayamin [ざつだん] そういえばパンダの鳴き声って全然知らない...＞＜ by jflute (2025/07/07)
        return "gao gao"; // パンダの鳴き声
    }
    // TODO ayamin ここの空行は削除で by jflute (2025/07/07)

}
