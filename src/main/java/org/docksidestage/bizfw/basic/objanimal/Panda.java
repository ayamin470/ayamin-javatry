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
        return "gao gao"; // パンダの鳴き声
    }

}
