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
import java.time.LocalTime;

// TODO done ayamin [質問] コードたちの「たち」ってのは、変数やメソッドが複数で「変数やメソッドたち」みたいなニュアンスですかね？(^^ by jflute (2025/07/07)
// TODO jflute [へんじ]　by ayamin (2025/07/08)
// 「たち」はいらないというか、誤解を招きかねないですね。気をつけます。私的には、「コード」はたとえ一文でも「コード」であるという認識をしていて、コードが何行にも渡っているから「たち」を無意識につけてしまいました
/**
 * 昼か夜かを制御するコードたち
 * @author ayamin
 */
//public class ParkContext {
//
//    // TODO done ayamin [質問] デフォルトを夜間にした理由はなんでしょうか？ by jflute (2025/07/07)
//    // test_class_moreFix_wonder_night() を見ると、実際明示的に setNight(true) してたので、
//    // あまりデフォルトが夜間であることに現状は意味があるように見えなかったので。
//    // とはいえ、本来は時間で決まるものなので、昼がデフォルトも変っちゃ変ですけど...
//    // 「デフォルトは夜間とする」と意図的なコメントまで書いてますから、どう考えてそうしたのかな？と思いまして。
//    // TODO jflute [へんじ] by ayamin (2025/07/08)
//    // たしかに、 ParkContext.setNight(true);を書くなら、ここでわざわざtrueにする必要はないですね
//    // おそらく手グセで書いてしまったと思います
//
//    private static boolean currentIsNight = true; // デフォルトは夜間とする
//    public static boolean isNight() {
//        return currentIsNight;
//    }
//    public static boolean isDay() {
//        return !currentIsNight;
//    }
//    public static void setNight(boolean isNight) {
//        currentIsNight = isNight;
//    }
//}

// TODO jflute [へんじ] by ayamin (2025/07/08)
//  「現在日時で夜かどうか？を判定」するようにコードを変更しました
public class ParkContext {

    private ParkContext() {

    }

    public static boolean isNight() {
        return isNight(LocalTime.now());
    }

    public static boolean isNight(LocalTime checkTime) {
        LocalTime dayStartTime = LocalTime.of(6, 0, 0);
        LocalTime nightStartTime = LocalTime.of(18, 0, 0);
        return checkTime.isBefore(dayStartTime) || checkTime.isAfter(nightStartTime);
    }

    public static boolean isDay() {
        return isDay(LocalTime.now());
    }

    public static boolean isDay(LocalTime checkTime) {
        return !isNight(checkTime);
    }
}


