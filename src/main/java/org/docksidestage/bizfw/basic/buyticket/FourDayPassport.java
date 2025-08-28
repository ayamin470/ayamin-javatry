package org.docksidestage.bizfw.basic.buyticket;

public class FourDayPassport extends Ticket {

    public FourDayPassport(ClockProvider clockProvider) {
        super(22000, 4, false, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "4日パスポート";
    }
}
