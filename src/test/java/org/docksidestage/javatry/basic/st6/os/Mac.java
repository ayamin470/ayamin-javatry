package org.docksidestage.javatry.basic.st6.os;

/**
 * @ayamin
 */
public class Mac extends OperationSystem {

    public Mac(String loginId) {
        super(loginId);
    }

    @Override
    public String getFileSeparator() {
        return "/";
    }

    @Override
    public String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
