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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList; // ArrayList をインポート
import java.util.Map;
import java.util.Set;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author ayamin
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() { // example, so begin from the next method
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // answer:5
        } else {
            log("*not found");
        }
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    //TODO[memo]ayamin 最初に、リストが空でないかを必ず確認する(エラー防止のため)
    public void test_length_findMax_colorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        // リストが空でないことを確認
        if (colorBoxList.isEmpty()) {
            log("ColorBoxリストが空です。");
            return;
        }

        int maxLength = 0;
        List<String> maxColorNames = new ArrayList<>();

        for (ColorBox colorBox : colorBoxList) {
            BoxColor boxColor = colorBox.getColor();

            if (boxColor != null) {
                String colorName = boxColor.getColorName();
                if (colorName != null) {
                    int currentLength = colorName.length();

                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        maxColorNames.clear();
                        maxColorNames.add(colorName);
                    } else if (currentLength == maxLength) {
                        if (!maxColorNames.contains(colorName)) {
                            maxColorNames.add(colorName);
                        }
                    }
                }
            }
        }

        if (maxColorNames.isEmpty()) {
            log("色の名前が見つかりませんでした。");
        } else if (maxColorNames.size() == 1) {
            log("色の名前が一番長いもの: " + maxColorNames.get(0) + " (長さ: " + maxLength + ")");
        } else {
            log("色の名前が一番長いもの (" + maxColorNames.size() + "つ): " + maxColorNames + " (長さ: " + maxLength + ")");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        // リストが空でないことを確認
        if (colorBoxList.isEmpty()) {
            log("ColorBoxリストが空です。");
            return;
        }

        int maxLength = 0;
        List<String> maxContentStrings = new ArrayList<>(); // 最長文字列を複数保持するためListを使用

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();

                // nullの場合はスキップ
                if (content == null) {
                    continue;
                }

                // 文字列に変換して長さを比較するための変数
                String currentString = null;

                // Contentの型に応じて適切な文字列を取得
                if (content instanceof String) {
                    currentString = (String) content;
                } else if (content instanceof Integer || content instanceof Long || content instanceof Double || content instanceof Float ||
                        content instanceof BigDecimal || content instanceof BigInteger) {
                    currentString = String.valueOf(content);
                } else if (content instanceof Boolean) {
                    currentString = String.valueOf(content);
                } else if (content instanceof File) {
                    currentString = ((File) content).getName(); // ファイル名を取得
                } else if (content instanceof LocalDateTime) {
                    currentString = ((LocalDateTime) content).toString();
                } else if (content instanceof LocalDate) {
                    currentString = ((LocalDate) content).toString();
                } else if (content instanceof LocalTime) {
                    currentString = ((LocalTime) content).toString();
                } else if (content instanceof Map) {
                    StringBuilder sb = new StringBuilder();
                    ((Map<?, ?>) content).forEach((key, value) -> {
                        sb.append(key != null ? key.toString() : "null");
                        sb.append("=");
                        sb.append(value != null ? value.toString() : "null");
                        sb.append(", ");
                    });
                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 2); // 最後の ", " を削除
                    }
                    currentString = "{" + sb.toString() + "}";
                } else if (content instanceof List) {
                    // Listの場合、各要素の文字列を結合して長さを比較
                    StringBuilder sb = new StringBuilder();
                    ((List<?>) content).forEach(element -> {
                        if (element != null) {
                            if (element instanceof Number) { // 数字はそのまま文字列化
                                sb.append(element.toString());
                            } else if (element instanceof YourPrivateRoom.BoxedResort) { // BoxedResortを処理
                                sb.append(((YourPrivateRoom.BoxedResort) element).getRegion());
                                ((YourPrivateRoom.BoxedResort) element).getPark().ifPresent(park -> {
                                    sb.append(park.getTheme());
                                    park.getStage().ifPresent(stage -> {
                                        sb.append(stage.getShowName());
                                        String keyword = stage.getKeyword();
                                        if (keyword != null) {
                                            sb.append(keyword);
                                        }
                                    });
                                });
                            } else {
                                sb.append(element.toString());
                            }
                        }
                    });
                    currentString = sb.toString();
                } else if (content instanceof Set) {
                    // Setの場合、各要素の文字列を結合して長さを比較
                    StringBuilder sb = new StringBuilder();
                    ((Set<?>) content).forEach(element -> {
                        if (element != null) {
                            sb.append(element.toString());
                        }
                    });
                    currentString = sb.toString();
                } else if (content instanceof YourPrivateRoom.SecretBox) {
                    currentString = ((YourPrivateRoom.SecretBox) content).getText();
                } else if (content instanceof YourPrivateRoom.GuardianBox) {
                    YourPrivateRoom.GuardianBox guardianBox = (YourPrivateRoom.GuardianBox) content;
                    try {
                        guardianBox.wakeUp();
                        guardianBox.allowMe();
                        guardianBox.open();
                        currentString = guardianBox.getText();
                    } catch (IllegalStateException | YourPrivateRoom.GuardianBoxTextNotFoundException e) {
                        // GuardianBoxの仕様により、getText()が例外を投げる場合があるためキャッチ
                        // この場合は文字列としてカウントしない、またはエラーメッセージを文字列として扱うか決める
                        // ここではカウントしない方針で続行
                        log("GuardianBox content could not be retrieved: {}", e.getMessage());
                        continue; // 次のコンテンツへ
                    }
                } else if (content instanceof YourPrivateRoom.BittersweetMemorableException) {
                    currentString = ((YourPrivateRoom.BittersweetMemorableException) content).getMessage();
                } else if (content instanceof YourPrivateRoom.FavoriteProvider) {
                    // FavoriteProviderはjustHere()を持つ
                    try {
                        currentString = ((YourPrivateRoom.FavoriteProvider) content).justHere();
                    } catch (YourPrivateRoom.BittersweetMemorableException e) {
                        log("FavoriteProvider justHere() threw exception: {}", e.getMessage());
                        continue;
                    }
                } else {
                    // 上記以外の不明な型の場合、toString()を使用
                    currentString = content.toString();
                }

                // 文字列が取得できた場合のみ長さを比較
                if (currentString != null) {
                    int currentLength = currentString.length();

                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        maxContentStrings.clear(); // より長い文字列が見つかったらクリア
                        maxContentStrings.add(currentString);
                    } else if (currentLength == maxLength) {
                        // 同じ長さの文字列は追加（重複を避けるためにcontainsチェック）
                        if (!maxContentStrings.contains(currentString)) {
                            maxContentStrings.add(currentString);
                        }
                    }
                }
            }
        }
        if (maxContentStrings.isEmpty()) {
            log("カラーボックス内に有効な文字列コンテンツは見つかりませんでした。");
        } else {
            log("最長文字列の長さ: {}", maxLength);
            log("最長文字列のリスト: {}", maxContentStrings);
        }
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (latter if same length) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (同じ長さのものがあれば後の方を))
     */
    public void test_length_findSecondMax_contentToString() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBoxリストが空です。");
            return;
        }

        int maxLength = 0;
        String maxString = null;

        int secondMaxLength = 0;
        String secondMaxString = null;

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();

                if (content == null) {
                    continue;
                }

                String currentString = convertContentToString(content);

                if (currentString == null) {
                    continue;
                }

                int currentLength = currentString.length();

                if (currentLength > maxLength) {
                    secondMaxLength = maxLength;
                    secondMaxString = maxString;

                    maxLength = currentLength;
                    maxString = currentString;
                } else if (currentLength == maxLength) {
                } else if (currentLength > secondMaxLength) {
                    secondMaxLength = currentLength;
                    secondMaxString = currentString;
                } else if (currentLength == secondMaxLength) {
                    secondMaxString = currentString;
                }
            }
        }

        if (maxString == null) {
            log("カラーボックス内に有効な文字列コンテンツは見つかりませんでした。");
        } else if (secondMaxString == null) {
            log("最長文字列は見つかりましたが、二番目に長い文字列は見つかりませんでした。");
            log("最長文字列の長さ: {}", maxLength);
            log("最長文字列: {}", maxString);
        } else {
            log("最長文字列の長さ: {}", maxLength);
            log("最長文字列: {}", maxString);
            log("二番目に長い文字列の長さ: {}", secondMaxLength);
            log("二番目に長い文字列: {}", secondMaxString);
        }
    }

    private String convertContentToString(Object content) {
        if (content == null) {
            return null;
        }

        if (content instanceof String) {
            return (String) content;
        } else if (content instanceof Integer || content instanceof Long || content instanceof Double || content instanceof Float ||
                content instanceof BigDecimal || content instanceof BigInteger) {
            return String.valueOf(content);
        } else if (content instanceof Boolean) {
            return String.valueOf(content);
        } else if (content instanceof File) {
            return ((File) content).getName();
        } else if (content instanceof LocalDateTime) {
            return ((LocalDateTime) content).toString();
        } else if (content instanceof LocalDate) {
            return ((LocalDate) content).toString();
        } else if (content instanceof LocalTime) {
            return ((LocalTime) content).toString();
        } else if (content instanceof Map) {
            StringBuilder sb = new StringBuilder();
            ((Map<?, ?>) content).forEach((key, value) -> {
                sb.append(key != null ? key.toString() : "null");
                sb.append("=");
                sb.append(value != null ? value.toString() : "null");
                sb.append(", ");
            });
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 2);
            }
            return "{" + sb.toString() + "}";
        } else if (content instanceof List) {
            StringBuilder sb = new StringBuilder();
            ((List<?>) content).forEach(element -> {
                if (element != null) {
                    if (element instanceof Number) {
                        sb.append(element.toString());
                    } else if (element instanceof YourPrivateRoom.BoxedResort) {
                        YourPrivateRoom.BoxedResort resort = (YourPrivateRoom.BoxedResort) element;
                        sb.append(resort.getRegion());
                        resort.getPark().ifPresent(park -> {
                            sb.append(park.getTheme());
                            park.getStage().ifPresent(stage -> {
                                sb.append(stage.getShowName());
                                String keyword = stage.getKeyword();
                                if (keyword != null) {
                                    sb.append(keyword);
                                }
                            });
                        });
                    } else {
                        sb.append(element.toString());
                    }
                }
            });
            return sb.toString();
        } else if (content instanceof Set) {
            StringBuilder sb = new StringBuilder();
            ((Set<?>) content).forEach(element -> {
                if (element != null) {
                    sb.append(element.toString());
                }
            });
            return sb.toString();
        } else if (content instanceof YourPrivateRoom.SecretBox) {
            return ((YourPrivateRoom.SecretBox) content).getText();
        } else if (content instanceof YourPrivateRoom.GuardianBox) {
            YourPrivateRoom.GuardianBox guardianBox = (YourPrivateRoom.GuardianBox) content;
            try {
                guardianBox.wakeUp();
                guardianBox.allowMe();
                guardianBox.open();
                return guardianBox.getText();
            } catch (IllegalStateException | YourPrivateRoom.GuardianBoxTextNotFoundException e) {
                log("GuardianBox content could not be retrieved: {}", e.getMessage());
                return null;
            }
        } else if (content instanceof YourPrivateRoom.BittersweetMemorableException) {
            return ((YourPrivateRoom.BittersweetMemorableException) content).getMessage();
        } else if (content instanceof YourPrivateRoom.FavoriteProvider) {
            try {
                return ((YourPrivateRoom.FavoriteProvider) content).justHere();
            } catch (YourPrivateRoom.BittersweetMemorableException e) {
                log("FavoriteProvider justHere() threw exception: {}", e.getMessage());
                return null;
            }
        } else {
            return content.toString();
        }
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
    }

    // ===================================================================================
    //                                                                 Welcome to Guardian
    //                                                                 ===================
    /**
     * What is total length of text of GuardianBox class in color-boxes? <br>
     * (カラーボックスの中に入っているGuardianBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToGuardian() {
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // these are very difficult exercises so you can skip
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
    }
}
