import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cxj8923 on 4/26/15.
 */
public class UrlMonitor extends Observable implements Serializable{

    private String url;
    private long urlSize;
    private long lastUpdate;
    private transient URLConnection connect;

    public UrlMonitor(String aUrl) throws IOException
    {
        urlSize = 0;
        lastUpdate = 0;
        url = aUrl;
        connect = getConnection(url);
        Timer timer = new Timer();
        timer.schedule(new UrlCheck(), 10000, 10000);
    }

    public long getUrlSize()
    {
        return urlSize;
    }

    public long getLastUpdate()
    {
        return lastUpdate;
    }

    public String url()
    {
        return  connect.getURL().toString();
    }

    private class UrlCheck extends TimerTask
    {

        @Override
        public void run()
        {
            System.out.println("Checking...");
            long lastModified = connect.getLastModified();
            long currentSize = connect.getContentLength();
            if(lastUpdate != lastModified || urlSize != currentSize)
            {
                urlSize = currentSize;
                lastUpdate = lastModified;
                UrlMonitor.this.setChanged();
                UrlMonitor.this.notifyObservers();
            }
        }
    }

    private URLConnection getConnection(String url) throws IOException
    {
        URL address = new URL(url);
        return  address.openConnection();
    }

}