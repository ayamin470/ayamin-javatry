package org.docksidestage.javatry.basic.st6.dbms;

// done ayamin この潔いクラス名が良いですね笑。自身のコメントで「データベースの種類」という言葉が出ていますから... by jflute (2025/07/10)
// MySQLは何？PostgreSQLは何？(なんて名前のスーパークラスを継承すればいいのか？) の答えが出てるんじゃないかと。
// done ayamin とはいえ、もう少しクラス名は練ったほうがいいですよね。反省。何の Superclass なのか見た人はわからないかもしれないので by ayamin (2025/07/11)
// done ayamin ↑ということで、良い名前が思いついたら renameリファクタリングしてみてください by jflute (2025/07/16)
// #1on1: 抽象クラスの名前を考えるとき、まだ見ぬ具象クラスを想像してみると良い。
// #1on1: (質問) スーパークラスはやることを名前にした方がいいかなと思いましたが？
// (回答) step6のオブジェクト指向とインターフェースの違いの話とつながるのでそっちへ
// done jflute #1on1: オブジェクト指向とインターフェースの違いの話をする (2025/07/18)
// → ということで、抽象クラスは極力概念を名前にした方がいい。 (buildPagingQuery()以外のメソッドも柄されるかも)

// done ayamin Javaのクラス名はキャメルケースが慣習なので... DatabeseSet にしましょう by jflute (2025/07/18)
// 一方で、スペルミス？ Databese -> Database ですかね。 
/**
 * @author ayamin
 */

public abstract class DetabaseSet {
    // done ayamin [見比べ課題] Animal の bark() と、ここの buildPagingQuery() を比べてみてください。 by jflute (2025/07/16)
    // 再利用の方法というか構造というか方向性がちょっと違うと思いません？
    // done jflute あまりピンとこなかったので、1on1でお話しさせていただきたいです！
    // done jflute #1on1 でフォロー予定 (2025/07/18)
    // #1on1: bark()は、流れが再利用できている。こっちは、流れが再利用できていない。
    // done ayamin ということで伝統的なテンプレートメソッドパターンになおしてみましょう by jflute (2025/07/25)
    // done ayamin [いいね] おおおぉ、Good。buildPagingQuery()の流れが見事に再利用できていますね！ by jflute (2025/07/31)
    // オブジェクト階層を作るのであれば、できればこういった構造を作っていきたいところです。
    
    // done ayamin 細かいところですが、publicメソッドに対して、呼ばれるprotectedメソッドはpublicメソッドの下に by jflute (2025/07/31)
    // buildPagingQuery() の下に doBuildPagingSqlPart() が定義されているのと同様に、calculateOffset() も。
    public final String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = calculateOffset(pageSize, pageNumber); // 共通手順
        String pagingSqlPart = doBuildPagingSqlPart(pageSize, offset); // ★フックメソッドを呼び出す★
        return pagingSqlPart;
    }
    protected int calculateOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }

    protected abstract String doBuildPagingSqlPart(int pageSize, int offset);
}

//[memo] ayamin
// 中身が空(に見える)抽象クラスを作って何が嬉しいのか？意味があるのか？
// ①もし抽象クラスがない場合、それぞれの場所で「もしMySQLならこう、もしPostgreSQLならこう」とデータベースの種類に応じて異なるクラスをインスタンス化し、異なるメソッドを呼び出すためのif-elseやswitch文を書く必要が出てきて面倒&複雑
// ②新しいデータベースシステムを追加するときも同上、また一から書き直さないといけなくなる
// 抽象クラスがあれば、新しい St6OracleSql クラスを Superclass を継承して作成し、buildPagingQuery を実装するだけで済みます。dbms オブジェクトを呼び出している既存のコードは一切変更する必要がなくなる

//[memo] ayamin
// テストコード作るのが難しくなってきた
// そもそもテストとはコードを設計意図通りに動いているかを確認するもの。だから、コードを作る時点での設計意図を明確にして覚えておく(メモしておく)必要があるね
