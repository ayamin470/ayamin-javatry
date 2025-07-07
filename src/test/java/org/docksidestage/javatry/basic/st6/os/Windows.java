package org.docksidestage.javatry.basic.st6.os;

/**
 * @ayamin
 */
public class Windows extends OperationSystem {

    public Windows(String loginId) {
        super(loginId);
    }

    @Override
    public String getFileSeparator() {
        return "\\";
    }

    @Override
    public String getUserDirectory() {
        return "/Users/" + loginId; // Modern Windows uses /Users/
    }
}
