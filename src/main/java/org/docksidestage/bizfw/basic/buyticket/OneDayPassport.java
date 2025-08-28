package org.docksidestage.bizfw.basic.buyticket;

public class OneDayPassport extends Ticket {

    public OneDayPassport(ClockProvider clockProvider) {
        super(7400, 1, false, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "1日パスポート";
    }
}
