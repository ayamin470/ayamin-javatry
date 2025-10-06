/**
 * @author ayamin
 */

// TicketBuyResult.java
package org.docksidestage.bizfw.basic.buyticket;

// done ayamin authorが YourName のままです by jflute (2025/07/02)
/**
 * @author ayamin
 */
public class Inherutance_ReturnTheChange {

    // done ayamin [いいね] Immutableになれるクラスなので、final付けるのgoodです by jflute (2025/07/02)
    private final Inheritance_Ticket ticket;
    private final int change;

    public Inherutance_ReturnTheChange(Inheritance_Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }

    public Inheritance_Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
