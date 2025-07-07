package org.docksidestage.javatry.basic.st6.os;

/**
 * @ayamin
 */
public class Mac extends OperationSystem {

    public Mac(String loginId) {
        super(loginId);
    }

    @Override // 抽象メソッドの実装
    public String getFileSeparator() {
        return "/";
    }

    @Override // 抽象メソッドの実装
    public String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
