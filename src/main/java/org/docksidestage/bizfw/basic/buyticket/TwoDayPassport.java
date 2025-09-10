package org.docksidestage.bizfw.basic.buyticket;

/**
 * 2日パスポートを表すクラス。
 * 2日の有効期間と、夜間利用不可のルールを持つ。
 * @author ayamin
 */
public class TwoDayPassport extends Ticket {

    public TwoDayPassport(ClockProvider clockProvider) {
        super(12000, 2, false, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "2日パスポート";
    }
}
