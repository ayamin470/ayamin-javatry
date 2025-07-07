package org.docksidestage.javatry.basic.st6.os;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO ayamin ayaminさんがjavadocのタグになっています(^^。@author ayamin でお願いします by jflute (2025/07/07)
/**
 * 様々なOSの抽象クラス
 * @ayamin
 */
public abstract class OperationSystem {

    private static final Logger logger = LoggerFactory.getLogger(OperationSystem.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final String loginId; // ログインIDは共通で持つ

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public OperationSystem(String loginId) {
        this.loginId = loginId;
        // TODO ayamin [いいね] loggerうまく使いこなしてますね！ by jflute (2025/07/07)
        logger.debug("OperationSystem created for loginId: {}", loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    // ユーザーリソースパス、共通
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    // TODO ayamin タグコメント、フォーマットがちょっとズレてます!? (タグコメントうまく使く使えています！) by jflute (2025/07/07)
    // こういう、右にばぁーっと伸ばしてタイトルみたいなコメント入れてるのをタグコメントと読んでいます。
    // 参考例:
    // https://github.com/lastaflute/lastaflute-example-harbor/blob/master/src/main/java/org/docksidestage/app/web/signup/SignupAction.java
    // ===================================================================================
    //                                                                      Abstract Methods
    //                                                                      ================
    /**
     * Get file separator specific to the OS. (OS固有のファイル区切り文字を取得する)
     * @return The file separator string. (ファイル区切り文字の文字列)
     */
    public abstract String getFileSeparator(); // 抽象メソッド：子クラスで実装必須

    /**
     * Get user directory path specific to the OS. (OS固有のユーザーディレクトリパスを取得する)
     * @return The user directory path. (ユーザーディレクトリパス)
     */
    public abstract String getUserDirectory(); // 抽象メソッド：子クラスで実装必須

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getLoginId() {
        return loginId;
    }
}
