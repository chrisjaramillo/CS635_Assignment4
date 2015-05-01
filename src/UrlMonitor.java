import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by cxj8923 on 4/26/15.
 */
public class UrlMonitor implements Monitor, Serializable{

    private String url;
    private long urlSize;
    private long lastUpdate;
    private transient URLConnection connect;
    private transient List<Notifier> notifiers;

    public UrlMonitor(String aUrl) throws IOException
    {
        urlSize = 0;
        lastUpdate = 0;
        url = aUrl;
        connect = getConnection(url);
        notifiers = new ArrayList<Notifier>();
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

    @Override
    public void addNotifier(Notifier aNotifier)
    {
        notifiers.add(aNotifier);
    }

    public void updateClients()
    {
        for(Notifier aNotifier : notifiers)
        {
            aNotifier.update(this);
        }
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
                UrlMonitor.this.updateClients();
            }
        }
    }

    private URLConnection getConnection(String url) throws IOException
    {
        URL address = new URL(url);
        return  address.openConnection();
    }
}