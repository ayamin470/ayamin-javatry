package org.docksidestage.bizfw.basic.objanimal.special;

/**
 * The interface for nocturnal animal (夜行性の動物).
 * This indicates that the animal can make a special sound at night.
 * @author ayamin
 */
public interface Nocturnal {

    /**
     * 夜に特別な音を出す
     */
    String makeNightSound();
}
