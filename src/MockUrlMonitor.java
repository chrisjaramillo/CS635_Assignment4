import static org.mockito.Mockito.*;

import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;



/**
 * Created by Christopher on 5/3/2015.
 */
public class MockUrlMonitor extends UrlMonitor {

    public MockUrlMonitor(String aUrl) throws IOException
    {
        super(aUrl);
        connect = getConnection(aUrl);
    }

    private URLConnection getConnection(String url) throws IOException
    {
        URLConnection connection = mock(URLConnection.class);
        when(connection.getLastModified()).thenReturn((long)2);
        when(connection.getContentLength()).thenReturn(2);
        when(connection.getURL()).thenReturn(new URL(url));
        return connection;
    }
}
