package org.docksidestage.bizfw.basic.buyticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author ayamin
 */
public class Interface_OneDayPassport implements Interface_Ticket {
    private static final Logger log = LoggerFactory.getLogger(Interface_OneDayPassport.class);
    private int price;
    private boolean used;

    public void OnedayTicket(int price) {
        this.price = price;
        this.used = false;
    }

    @Override
    public int getPrice() {
        return 0;
    }
    @Override
    public boolean isValid() {
        return false;
    }
    @Override
    public void enter() {
        if (isValid()) {
            System.out.println("1日券で入場しました。");
            this.used = true;
        } else {
            System.out.println("このチケットは使用済みです。");
        }
    }
}
