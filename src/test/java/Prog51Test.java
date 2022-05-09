import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220501)
 * 
 * (注意) Prog51クラス内に printMyName() および makeCall() が
 * 　　　　宣言されるまで、このテストクラスはエラーが表示される
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
    public void testPrintMyName()
    {
        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog51.printMyName();

        // assertion
        try {
            assertEquals("生命太郎です" + System.lineSeparator(), bos.toString(),
                         "「PrintMyName()」におけるprint出力は期待されるものと一致しません!"
            );
        } catch (AssertionError err) {
            after();
            throw err;     
        }

    }

    @Test
    public void testMakeCall()
    {
        // action
        Prog51.makeCall( "生命一郎", "120-3847-1983");

        // assertion
        try {
            assertEquals("生命一郎さんの番号120-3847-1983に電話をかけます" + System.lineSeparator(), bos.toString(),
                         "「testMakeCall()」におけるprint出力は期待されるものと一致しません!"     
            );
        } catch (AssertionError err) {
            after();
            throw err;     
        }

    }

    @Test
    public void testMain()
    {

        // action
        Prog51.main(new String[]{"生命一郎", "120-3847-1983"});

        // assertion
        String[] prints = bos.toString().split(System.lineSeparator());
        try {
            assertEquals("生命太郎です", prints[0],"mainメソッド内のprintMyName()の使い方が不正です!");
            assertEquals("生命一郎さんの番号120-3847-1983に電話をかけます", prints[1],"mainメソッド内のtestMakeCall()の使い方が不正です!");
        } catch (AssertionError err) {
            after();
            throw err;     
        } catch (ArrayIndexOutOfBoundsException aIOBExcpt) {
            after();
            throw new AssertionError("実行結果が２行分ありません!");
        }
        
    }
}
