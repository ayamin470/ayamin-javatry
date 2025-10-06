package org.docksidestage.bizfw.basic.buyticket;

// done ayamin javadocでauthorを。自信持って自分の名前を！ by jflute (2025/09/05)

/**
 * 4日パスポートを表すクラス。
 * 4日の有効期間と、夜間利用不可のルールを持つ。
 * @author ayamin
 */
public class Inheritance_FourDayPassport extends Inheritance_Ticket {

    public Inheritance_FourDayPassport(Util_ClockProvider clockProvider) {
        super(22000, 4, false, clockProvider);
    }
    @Override
    public String getTicketTypeDisplayName() {
        return "4日パスポート";
    }
}
