import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220501)
 * @version (20230417) suporting both println and print("\n") on Windows
 * @version (20261014) revised
 * 
 * (注意) Prog51クラス内に printMyName() および makeCall() が
 * 　　　　宣言されるまで、このテストクラスはコンパイルエラーとなります
 **/
public class Prog51Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }
    
    @Test
    public void testPrintMyName() {
        Prog51.printMyName();

        String actual = bos.toString().replace("\r\n", "\n");
        assertEquals("生命太郎です\n", actual,
            "「printMyName()」の出力結果が期待されるものと一致しません!（改行 println を含めて完全一致させてください）"
        );
    }

    @Test
    public void testMakeCall() {
        Prog51.makeCall("テスト太郎", "090-1234-5678");

        String actual = bos.toString().replace("\r\n", "\n");
        assertEquals("テスト太郎さんの番号090-1234-5678に電話をかけます\n", actual,
            "「makeCall()」の出力結果が期待されるものと一致しません! 引数（name, telNumber）を使って文字列を出力していますか？"
        );
    }

    @Test
    public void testMain() {
        Prog51.main(new String[]{"生命花子", "000-1111-2222"});

        String[] lines = bos.toString().replace("\r\n", "\n").split("\n");

        String expectedName = "生命太郎です";
        String expectedCall = "生命花子さんの番号000-1111-2222に電話をかけます";

        boolean foundName = false;
        boolean foundCall = false;
        int nameLineIndex = -1;
        int callLineIndex = -1;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.equals(expectedName)) {
                foundName = true;
                nameLineIndex = i;
            } else if (line.equals(expectedCall)) {
                foundCall = true;
                callLineIndex = i;
            }
        }

        assertTrue(foundName, 
            "printMyName() の出力「生命太郎です」が独立した行として確認できません! 改行（println）を入れているか確認してください。"
        );
        assertTrue(foundCall, 
            "makeCall() の出力が確認できません! mainメソッドからの呼び出し、または実行時引数の渡し方を確認してください。"
        );
        assertTrue(nameLineIndex < callLineIndex,
            "printMyName() を呼び出した後に makeCall() を呼び出してください。"
        );
    }

}
