package org.docksidestage.bizfw.basic.buyticket;

public class NightOnlyTwoDayPassport extends Ticket {

    public NightOnlyTwoDayPassport(ClockProvider clockProvider) {
        super(7400, 2, true, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "夜間専用2日パスポート";
    }
}
