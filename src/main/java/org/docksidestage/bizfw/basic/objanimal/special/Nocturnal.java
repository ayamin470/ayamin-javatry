package org.docksidestage.bizfw.basic.objanimal.special;

/**
 * The interface for nocturnal animal (夜行性の動物).
 * This indicates that the animal can make a special sound at night.
 * @author ayamin
 */
public interface Nocturnal {

    // done ayamin javadoc, @return もお願いします by jflute (2025/07/07)
    /**
     * @return　夜に特別な音を出す
     */
    String makeNightSound();
}
