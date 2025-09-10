package org.docksidestage.bizfw.basic.buyticket;

/**
 * 1日限定パスポートを表すクラス。
 * 1日の有効期間と、夜間利用不可のルールを持つ。
 * @author ayamin
 */
public class OneDayPassport extends Ticket {

    public OneDayPassport(ClockProvider clockProvider) {
        super(7400, 1, false, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "1日パスポート";
    }
}
