//import org.junit.Assert;

import static org.junit.Assert.*;

/**
 * Created by Christopher on 5/3/2015.
 */
public class UrlMonitorTest {

    @org.junit.Test
    public void testRun() throws Exception
    {
        UrlMonitor mon = new MockUrlMonitor("http://eli.sdsu.edu/");
        mon.addNotifier(new UrlChangeTranscript());
        mon.checkUrl();
        assertTrue(mon.getLastUpdate() != 0);
        assertTrue(mon.getUrlSize() != 0);
        assertTrue(mon.getLastUpdate() == 2);
        assertTrue(mon.getUrlSize()== 2);
    }

    @org.junit.Test
    public void testAddNotifier() throws Exception
    {

    }

    @org.junit.Test
    public void testUpdateClients() throws Exception
    {

    }
}