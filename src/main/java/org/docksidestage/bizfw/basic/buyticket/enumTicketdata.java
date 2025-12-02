package org.docksidestage.bizfw.basic.buyticket;

// #1on1: enumとは？enumの仕組み的な本質。
// Java5 (2005) から、Java誕生の1995年から10年経って登場した。
// enumに区分値にぴったり。(区分値のお話を少し)
// 最初の10年enumなかった。
//
// e.g.
// public class TicketType {
//     public static final TicketType ONE_DAY = new TicketType("1日チケット", 6000, 1, false); 
//     public static final TicketType TWO_DAY = new TicketType("2日チケット", 12000, 2, false);
//
//     private String displayName;
//     ...
//     private TicketType(String displayName, int price, int validDays, boolean nightAvailable) { {
//         ...
//     }
// }
//
// 使用感は、ほぼenumと同じになる。インスタンスで種別を表現している。
// Constructorがprivateなのがミソ。ONE_DAYのインスタンスを二個以上作れないようにすることがポイント。
//
// なので、enumは、これを文法として定型化しただけのもの。
// enumって、インスタンスの個数制限をしているクラスとも言える。
// 

// #1on1: もし、enumTicketをTicketBoothのbuyの戻り値にしたら？
//
// public enumTicket buyOneDayPassport() {
//     return ...
// }
//
// enumTicket myTicket = buyTwoDayPassport()
//
// enumTicketLogic logic = new enumTicketLogic(myTicket, ...);
// logic.doInPark(); // 1日目
// (ここは離れてる、別日)
// logic.doInPark(); // 2日目
//
// enumTicketは共有インスタンスなので、究極買わなくても手に入る。
// なので、Logicのインスタンスさえどうにかしてしまえば誰でも入園できちゃう。
//
// 元々Ticketは、買った人の入園権利の状態を管理しているものだった。
// enumにそれができるか？できないので、buyメソッドの戻り値にはならない。
// 一方で、Ticketの役割を enumTicketLogic が肩代わりしていると言える。
// でも、buyTwoDayPassport() で Logic が戻ってきたら気持ち悪い。
// 今のLogicクラスのクラス名を考え直してみよう。
// 今のLogicクラスの役割: 入園回数の状態管理 + 入園処理(doInPark())
// これを分ければまだしっくりくる。例えば、「Ticketクラス」と「入園Logicクラス」。
// その場合、入園Logicクラスは現実の世界で当てはめると自動改札機みたいな感じ。
//
// 一方で一方で、元々のTicketクラスも二つの役割を持ってて、状態管理の方を軸にしてるからTicket。
// あやみんさんのLogicも実質Ticketクラスで、処理の方にフォーカスを当ててLogic。
//

// 
// #1on1: 工業デザインの話。
// jfluteのプログラマーオススメ五冊での建築の本の話
// https://jflute.hatenadiary.jp/entry/20150727/fivebooks
//

/**
 * チケット種別ごとの定数と、それぞれの属性(価格、有効日数、夜間専用かどうか)を書いている(データ)
 * ゲッターメソッドもこのクラスに記載
 * @author ayamin
 */

// done jflute 改めて、クラスやメソッドの名付けが難しい by ayamin (2025/11/20)
// 概念をクラス名にするが基本。つまり、「チケット種別」。
// あえてデータを使うのであれば、「チケットの種類に関するデータ」。つまり、「チケット種別」。

public enum enumTicket {
    ONE_DAY("1日チケット", 6000, 1, false),
    TWO_DAY("2日チケット", 12000, 2, false);

    private final String displayName;
    private final int price;
    private final int validDays;
    // TODO ayamin enumで状態を持つと、あるユーザーがentryしたら他のユーザーに影響してしまう by jflute (2025/12/02)
    private int entryCount;
    private final boolean nightonly;

    enumTicket(String displayName, int price, int validDays, boolean nightAvailable) {
        this.displayName = displayName;
        this.price = price;
        this.validDays = validDays;
        this.entryCount = 0;
        this.nightonly = nightAvailable;
    }

    public String getDisplayName() { return displayName; }
    public int getPrice() { return price; }
    public int getValidDays() { return validDays; }
    public int entryCount() { return entryCount; }
    public boolean isNightOnly() { return nightonly; }
}

