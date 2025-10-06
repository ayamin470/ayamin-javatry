package org.docksidestage.bizfw.basic.buyticket;
// TODO ayamin チケットクラスをインターフェース(can enter)にして、同じゲートからキャストさんも入れるようにすることができるかも by ayamin (2025/10/06)
// TODO ayamin 今更だけどお釣りを返すクラスが別で存在しているの違和感あるなあ by ayamin (2025/10/06)
/**
 * チケットが持つ情報を定義(値段、有効日数、入園回数、夜間に使えるかどうか)
 * 入園の処理定義
 * @author ayamin
 */
public interface Interface_Ticket {
    //ここでどのチケットも持つべき機能(動き)を定義する
    //例えば
    int getPrice();       // 価格を取得する
    boolean isValid();    // 有効かどうかを判定する
    void enter();         // 入場処理を行う
}
