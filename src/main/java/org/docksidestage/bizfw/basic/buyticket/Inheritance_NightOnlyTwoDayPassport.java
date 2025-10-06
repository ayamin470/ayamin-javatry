package org.docksidestage.bizfw.basic.buyticket;

/**
 * 夜間2日限定パスポートを表すクラス。
 * 2日の有効期間と、夜間利用可のルールを持つ。
 * @author ayamin
 */
public class Inheritance_NightOnlyTwoDayPassport extends Inheritance_Ticket {

    public Inheritance_NightOnlyTwoDayPassport(Util_ClockProvider clockProvider) {
        super(7400, 2, true, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "夜間専用2日パスポート";
    }
}
