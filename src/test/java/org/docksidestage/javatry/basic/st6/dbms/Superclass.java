package org.docksidestage.javatry.basic.st6.dbms;

// TODO ayamin この潔いクラス名が良いですね笑。自身のコメントで「データベースの種類」という言葉が出ていますから... by jflute (2025/07/10)
// MySQLは何？PostgreSQLは何？(なんて名前のスーパークラスを継承すればいいのか？) の答えが出てるんじゃないかと。
/**
 * @author ayamin
 */

public abstract class Superclass {
    public abstract String buildPagingQuery(int pageSize, int pageNumber);
}

//TODO ayamin
// 中身が空(に見える)抽象クラスを作って何が嬉しいのか？意味があるのか？
// ①もし抽象クラスがない場合、それぞれの場所で「もしMySQLならこう、もしPostgreSQLならこう」とデータベースの種類に応じて異なるクラスをインスタンス化し、異なるメソッドを呼び出すためのif-elseやswitch文を書く必要が出てきて面倒&複雑
// ②新しいデータベースシステムを追加するときも同上、また一から書き直さないといけなくなる
// 抽象クラスがあれば、新しい St6OracleSql クラスを Superclass を継承して作成し、buildPagingQuery を実装するだけで済みます。dbms オブジェクトを呼び出している既存のコードは一切変更する必要がなくなる

//TODO ayamin 気づきメモ
// テストコード作るのが難しくなってきた^^;
// そもそもテストとはコードを設計意図通りに動いているかを確認するもの。だから、コードを作る時点での設計意図を明確にして覚えておく(メモしておく)必要があるね
