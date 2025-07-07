package org.docksidestage.javatry.basic.st6.os;

/**
 * @ayamin
 */
public class OldWindows extends OperationSystem {

    public OldWindows(String loginId) {
        super(loginId);
    }

    @Override
    public String getFileSeparator() {
        return "\\";
    }

    @Override
    public String getUserDirectory() {
        return "/Documents and Settings/" + loginId; // Old Windows path
    }
}
