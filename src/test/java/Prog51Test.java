import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog51Test {

    @Test
    public void testPrintMyName()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog51.printMyName();

        // assertion
        assertEquals("生命太郎です\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testMakeCall()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog51.makeCall("生命一郎", "120-3847-1983");

        // assertion
        assertEquals("生命一郎さんの番号120-3847-1983に電話をかけます\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testMain()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog51.main(new String[]{"生命一郎", "120-3847-1983"});

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals("生命太郎です", prints[0]);
        assertEquals("生命一郎さんの番号120-3847-1983に電話をかけます", prints[1]);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
