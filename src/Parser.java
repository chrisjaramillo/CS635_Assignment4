import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Christopher on 5/1/2015.
 */
public class Parser
{
    private Path aFilePath;

    Parser(String aFilename)
    {
        aFilePath = Paths.get(aFilename);
    }

    Map<String, List<String>> parse() throws IOException {
        Map<String, List<String>> urlMap = new HashMap<String, List<String>>();
        Scanner fileScannner = new Scanner(aFilePath);
        while(fileScannner.hasNextLine())
        {
            processLine(fileScannner.nextLine(), urlMap);
        }
        return urlMap;
    }

    private void processLine(String aLine, Map aMap)
    {
        String key = "";
        Scanner lineScanner = new Scanner(aLine);
        lineScanner.useDelimiter(" ");
        if(lineScanner.hasNext())
        {
            key = lineScanner.next();
        }
        while(lineScanner.hasNext())
        {
            aMap.put(key, lineScanner.next());
        }
    }
}
