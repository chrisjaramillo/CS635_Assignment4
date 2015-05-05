import org.junit.Test;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Christopher on 5/5/2015.
 */
public class ParserTest {

    @Test
    public void testParse() throws Exception
    {
        Path path = Paths.get("testFile");
        BufferedWriter writer = Files.newBufferedWriter(path);
        writer.write("http://www.eli.sdsu.edu/courses/spring15/cs635/notes/index.html transcript");
        writer.newLine();
        writer.write("http://www.eli.sdsu.edu/index.html mail transcript");
        writer.newLine();
        writer.write("http://bismarck.sdsu.edu/CS635/recent mail");
        writer.newLine();
        writer.write("http://bismarck.sdsu.edu/CS635/8 transcript mail");
        writer.newLine();
        writer.close();
        Parser aParser = new Parser("testFile");

        Map<String, List<String>> urlMap = aParser.parse();
        Set<String> keySet = urlMap.keySet();

        assertTrue(keySet.contains("http://www.eli.sdsu.edu/courses/spring15/cs635/notes/index.html"));
        List<String> eliCourseList = urlMap.get("http://www.eli.sdsu.edu/courses/spring15/cs635/notes/index.html");

        assertTrue(keySet.contains("http://www.eli.sdsu.edu/index.html"));
        List<String> eliIndexList = urlMap.get("http://www.eli.sdsu.edu/index.html");

        assertTrue(keySet.contains("http://bismarck.sdsu.edu/CS635/recent"));
        List<String> bismarckRecentList = urlMap.get("http://bismarck.sdsu.edu/CS635/recent");

        assertTrue(keySet.contains("http://bismarck.sdsu.edu/CS635/8"));
        List<String> bismarckEightList = urlMap.get("http://bismarck.sdsu.edu/CS635/8");

        assertFalse(keySet.contains("http://test.com"));

        assertTrue(eliCourseList.contains("transcript"));

        assertTrue(eliIndexList.contains("mail"));
        assertTrue(eliIndexList.contains("transcript"));

        assertTrue(bismarckRecentList.contains("mail"));

        assertTrue(bismarckEightList.contains("mail"));
        assertTrue(bismarckEightList.contains("transcript"));

        assertFalse(bismarckEightList.contains("testHandler"));

        Files.delete(path);
    }
}