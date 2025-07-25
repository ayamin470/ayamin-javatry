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
package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jflute
 * @author ayamin
 */
public class BarkedSound {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String barkWord;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkedSound(String barkWord) {
        this.barkWord = barkWord;
    }

    public String getBarkWord() {
        return barkWord;
    }
    
    // done ayamin [質問] BarkingProcess を BarkedSound のインナークラスとして作った理由(設計思想)は何でしょうか by jflute (2025/07/07)
    // done jflute 以下のいくつかの理由があります by ayamin
    //目的の明確化・プロセスと結果という繋がりの明確化：BarkingProcessの結果としてBarkedSoundが鳴るという関係性を表現したかったから
    //とはいえ、例えばパンダが鳴く、のような似た構造のコードを作ろうとした時に、再利用性がなくてよくなかったかもしれないです
    // done ayamin [いいね] ありがとうございます。是非は置いておいて、しっかりと説明ができるってことは良いことです by jflute (2025/07/16)
    // done jflute #1on1 にてフォロー予定 (2025/07/16)
    // かるそうなクラスの中に、業務ロジックがっつりのクラスが入ってるのは、隠れてる感じがあるので、個人的には切り出したい。
    // まだ逆だったらなんとなく納得もしやすいかなと。

    /**
     * 動物の鳴き声のプロセス
     * @ayamin
     */
    public static class BarkingProcess {

        private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

        // ===================================================================================
        //                                                                         Constructor
        //                                                                         ===========
        public BarkingProcess() {
        }

        // ===================================================================================
        //                                                                               Bark
        //                                                                              ======
        // done jflute 知らなかったので調べてから作りました！ by aymin
        // done jflute #1on1 にてコールバックの補足する予定 (2025/07/16)
        // #1on1 javaのスコープの話、振り返り、packageスコープのジレンマ話
        // 何にせよ、Animalを直接参照してdownHitPoint()を呼び出してたときから、
        // barkingパッケージに移動したらpackage違いで呼べなくなってしまう。
        // それで、コールバックになったんじゃないかな？と...
        // (or 先にコールバックが来たのかもしれない)
        // #1on1: オブジェクト指向とコールバックの関係性、コールバックの実現方法はJavaだとオブジェクト指向!?
        // #1on1: もっと色々と機能が増えてきたら、downHitPoint()とかもクラス化して部品化するかも!?したらコールバックじゃなくなることも
        // (つまり、コールバックでちゃちゃっとやっていたものが、オブジェクトに昇格していくような感覚)
        // #1on1: (質問) どうやって、そういった業務を想像していくか？ 
        // 業務概念の既存の業務機能から、他の架空の業務概念を想像する習慣があると... (2025/07/18)
        // 色々と将来の想定みたいなのを考えながら構造をデザインすることができるかも!?
        public BarkedSound bark(String barkWord, Runnable hitPointCallback) {
            breatheIn(hitPointCallback);
            prepareAbdominalMuscle(hitPointCallback);
            BarkedSound barkedSound = doBark(barkWord, hitPointCallback);
            return barkedSound;
        }


        protected void breatheIn(Runnable hitPointCallback) {
            logger.debug("...Breathing in for barking");
            hitPointCallback.run();
        }


        protected void prepareAbdominalMuscle(Runnable hitPointCallback) {
            logger.debug("...Using my abdominal muscle for barking");
            hitPointCallback.run();
        }


        protected BarkedSound doBark(String barkWord, Runnable hitPointCallback) {
            hitPointCallback.run();
            return new BarkedSound(barkWord);
        }
    }
}
