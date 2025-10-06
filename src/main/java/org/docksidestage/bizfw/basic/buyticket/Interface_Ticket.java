package org.docksidestage.bizfw.basic.buyticket;

// TODO ayamin チケットクラスをインターフェース(can enter)にして、同じゲートからキャストさんも入れるようにすることができるかも by ayamin (2025/10/06)
public interface Interface_Ticket {
    //ここでどのチケットも持つべき機能(動き)を定義する
    //例えば
    int getPrice();       // 価格を取得する
    boolean isValid();    // 有効かどうかを判定する
    void enter();         // 入場処理を行う
}
