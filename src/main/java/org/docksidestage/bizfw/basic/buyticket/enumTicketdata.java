package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケット種別ごとの定数と、それぞれの属性(価格、有効日数、夜間専用かどうか)を書いている(データ)
 * ゲッターメソッドもこのクラスに記載
 * @author ayamin
 */

// TODO jflute 改めて、クラスやメソッドの名付けが難しい by ayamin (2025/11/20)

public enum enumTicket {
    ONE_DAY("1日チケット", 6000, 1, false),
    TWO_DAY("2日チケット", 12000, 2, false);

    private final String displayName;
    private final int price;
    private final int validDays;
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

