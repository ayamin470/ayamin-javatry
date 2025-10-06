package org.docksidestage.bizfw.basic.buyticket;

public class Implements_OneDayPassport implements Interface_Ticket {
    //一旦適当にメソッド入れた、インターフェースはメソッド入れないと赤い波線が出る
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
    }
}
