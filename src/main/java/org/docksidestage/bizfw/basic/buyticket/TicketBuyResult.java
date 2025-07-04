// TicketBuyResult.java
package org.docksidestage.bizfw.basic.buyticket;

// TODO ayamin authorが YourName のままです by jflute (2025/07/02)
/**
 * Represents the result of a ticket purchase, containing the purchased ticket and any change.
 * @author ayamin (you can use your own name here)
 */
public class TicketBuyResult {

    // TODO ayamin [いいね] Immutableになれるクラスなので、final付けるのgoodです by jflute (2025/07/02)
    private final Ticket ticket;
    private final int change;

    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
