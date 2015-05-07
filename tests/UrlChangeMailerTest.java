import junit.framework.TestCase;

/**
 * Created by cxj8923 on 5/6/15.
 */
public class UrlChangeMailerTest extends TestCase {

    public void testSendEmail() throws Exception
    {
        UrlMonitor mockUrlMonitor = new MockUrlMonitor("http://www.myTestWeb.com");
        UrlChangeMailer testMailer = new MockUrlChangeMailer();
        mockUrlMonitor.addNotifier(testMailer);
        mockUrlMonitor.checkUrl();
        assertEquals("url incorrect: ", "http://www.myTestWeb.com", ((MockUrlChangeMailer) testMailer).url);
        assertEquals("text incorrect: ","Most recent update time: Wed Dec 31 16:00:00 PST 1969\n" +
                "Most reccent size: 2",((MockUrlChangeMailer)testMailer).text);
        assertTrue(((MockUrlChangeMailer) testMailer).subject.contains("http://www.myTestWeb.com updated"));
    }
}