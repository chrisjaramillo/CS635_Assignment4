import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christopher on 5/6/2015.
 */
public class UrlChangeTranscriptTest
{
    @Test
    public void testToString() throws Exception
    {
        UrlMonitor mockUrlMonitor = new MockUrlMonitor("http://www.myTestWeb.com");
        UrlChangeTranscript testChangeTranscript = new UrlChangeTranscript();
        mockUrlMonitor.addNotifier(testChangeTranscript);
        mockUrlMonitor.checkUrl();
        String output = testChangeTranscript.toString();
        assertEquals("Not equal:"," Last updated at time: Wed Dec 31 16:00:00 PST 1969 size: 2", output);
    }
}