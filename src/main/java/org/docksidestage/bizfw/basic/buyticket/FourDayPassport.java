package org.docksidestage.bizfw.basic.buyticket;

// TODO ayamin javadocでauthorを。自信持って自分の名前を！ by jflute (2025/09/05)
public class FourDayPassport extends Ticket {

    public FourDayPassport(ClockProvider clockProvider) {
        super(22000, 4, false, clockProvider);
    }

    @Override
    public String getTicketTypeDisplayName() {
        return "4日パスポート";
    }
}
