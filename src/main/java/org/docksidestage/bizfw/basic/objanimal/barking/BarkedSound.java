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
