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
package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 * @author ayamin
 */
public class Zombie extends Animal {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }
    @Override
    protected String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    // TODO ayamin 単純に消すだけだと、Zombieの息を吸うごとに日記を付けるという挙動がなくなってしまいます by jflute (2025/07/08)
    // BarkingProcessに切り出したことで、Zombieの機能にデグレが発生してしまうのは良くないです。
    // (リファクタリングは、既存の挙動を変えずにコードの形を変えるというもの)
    //    @Override
    //    protected void breatheIn() {
    //        super.breatheIn();
    //        zombieDiary.countBreatheIn();
    //    }
    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        // do nothing, infinity hit point
    }
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }
}
