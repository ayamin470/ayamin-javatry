/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

// TODO ayamin [質問] コードたちの「たち」ってのは、変数やメソッドが複数で「変数やメソッドたち」みたいなニュアンスですかね？(^^ by jflute (2025/07/07)
/**
 * 昼か夜かを制御するコードたち
 *
 * @author ayamin
 */
public class ParkContext {

    // TODO ayamin [質問] デフォルトを夜間にした理由はなんでしょうか？ by jflute (2025/07/07)
    // test_class_moreFix_wonder_night() を見ると、実際明示的に setNight(true) してたので、
    // あまりデフォルトが夜間であることに現状は意味があるように見えなかったので。
    // とはいえ、本来は時間で決まるものなので、昼がデフォルトも変っちゃ変ですけど...
    // 「デフォルトは夜間とする」と意図的なコメントまで書いてますから、どう考えてそうしたのかな？と思いまして。
    //
    // テストのために時間帯を制御できるようにする (通常はシステム時刻から判断)
    private static boolean currentIsNight = true; // デフォルトは夜間とする

    public static boolean isNight() {
        return currentIsNight;
    }

    public static boolean isDay() {
        return !currentIsNight;
    }
    public static void setNight(boolean isNight) {
        currentIsNight = isNight;
    }
}

