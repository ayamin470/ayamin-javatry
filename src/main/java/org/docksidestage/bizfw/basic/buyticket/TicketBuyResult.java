// TicketBuyResult.java
package org.docksidestage.bizfw.basic.buyticket;

/**
 * Represents the result of a ticket purchase, containing the purchased ticket and any change.
 * @author YourName (you can use your own name here)
 */
public class TicketBuyResult {

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
