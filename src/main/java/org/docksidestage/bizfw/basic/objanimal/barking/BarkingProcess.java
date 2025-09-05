package org.docksidestage.bizfw.basic.objanimal.barking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO ayamin authorおねがいしまーす by jflute (2025/09/05)
/**
 * The process for animal barking.
 * (動物の鳴き声のプロセス)
 * @author jflute (or your name)
 */
public class BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    /**
     * Perform the complete barking process.
     * (鳴き声の完全なプロセスを実行する)
     * @param barkWord The word the animal barks. (動物が鳴く言葉)
     * @param hitPointCallback Callback to decrease hit point. (ヒットポイントを減らすためのコールバック)
     * @return The sound created by barking. (鳴き声によって生成された音)
     */
    public BarkedSound bark(String barkWord, Runnable hitPointCallback) {
        // Animalクラスから移動してきたロジック
        breatheIn(hitPointCallback);
        prepareAbdominalMuscle(hitPointCallback);
        BarkedSound barkedSound = doBark(barkWord, hitPointCallback);
        return barkedSound;
    }

    // Animalクラスから移動してきたメソッド
    protected void breatheIn(Runnable hitPointCallback) {
        logger.debug("...Breathing in for barking");
        hitPointCallback.run(); // ヒットポイント減少処理をコールバックで呼び出す
    }

    // Animalクラスから移動してきたメソッド
    protected void prepareAbdominalMuscle(Runnable hitPointCallback) {
        logger.debug("...Using my abdominal muscle for barking");
        hitPointCallback.run(); // ヒットポイント減少処理をコールバックで呼び出す
    }

    // Animalクラスから移動してきたメソッド
    protected BarkedSound doBark(String barkWord, Runnable hitPointCallback) {
        hitPointCallback.run(); // ヒットポイント減少処理をコールバックで呼び出す
        return new BarkedSound(barkWord);
    }
}
