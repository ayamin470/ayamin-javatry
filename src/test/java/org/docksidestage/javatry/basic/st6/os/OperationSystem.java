package org.docksidestage.javatry.basic.st6.os;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO done ayamin ayaminさんがjavadocのタグになっています(^^。@author ayamin でお願いします by jflute (2025/07/07)
/**
 * 様々なOSの抽象クラス
 * @author ayamin
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
        // TODO done ayamin [いいね] loggerうまく使いこなしてますね！ by jflute (2025/07/07)
        // TODO jflute とはいえ、ここではlog();で良かったなと思っています
        //  log();がJavaの標準ライブラリではないことを知り、さらにlogger.debug()を知り、後学のために使ってみたくなってしまったのをそのままにしていました...
        //  統一性という観点と読み手に余計な違和感を与えないという意味でlog();にするべきだったと考えています
        logger.debug("OperationSystem created for loginId: {}", loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    // TODO done ayamin タグコメント、フォーマットがちょっとズレてます!? (タグコメントうまく使く使えています！) by jflute (2025/07/07)
    // こういう、右にばぁーっと伸ばしてタイトルみたいなコメント入れてるのをタグコメントと読んでいます。
    // 参考例:
    // https://github.com/lastaflute/lastaflute-example-harbor/blob/master/src/main/java/org/docksidestage/app/web/signup/SignupAction.java

    // ===================================================================================
    //                                                                    Abstract Methods
    //                                                                    ================

    public abstract String getFileSeparator();
    public abstract String getUserDirectory();

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getLoginId() {
        return loginId;
    }
}
