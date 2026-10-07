import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220501)
 * @version (20230417) suporting both println and print("\n") on Windows
 * @version (20261007) revised
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
            "「printMyName()」における出力結果が期待されるものと一致しません!"
        );
    }

    @Test
    public void testMakeCall() {
        Prog51.makeCall("生命一郎", "120-3847-1983");

        String actual = bos.toString().replace("\r\n", "\n");
        assertEquals("生命一郎さんの番号120-3847-1983に電話をかけます\n", actual,
            "「makeCall()」における出力結果が期待されるものと一致しません!"
        );
    }

    @Test
     public void testMain() {
        Prog51.main(new String[]{"生命一郎", "120-3847-1983"});

        String[] prints = bos.toString().replace("\r\n", "\n").split("\n");

        assertTrue(prints.length >= 2, "実行結果が2行分ありません! メソッドの呼び出し忘れや改行漏れがないか確認してください。");
        assertEquals("生命太郎です", prints[0], "mainメソッド内の printMyName() の呼び出し結果が不正です!");
        assertEquals("生命一郎さんの番号120-3847-1983に電話をかけます", prints[1], "mainメソッド内の makeCall() の呼び出し結果が不正です!");
    }

}
