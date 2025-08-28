package org.docksidestage.bizfw.basic.buyticket;

public class TwoDayPassport extends Ticket {

    public TwoDayPassport(ClockProvider clockProvider) {
        super(12000, 2, false, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "2日パスポート";
    }
}
