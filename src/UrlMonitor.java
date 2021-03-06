import java.io.*;
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
    private List<Notifier> notifiers;
    protected transient URLConnection connect;

    public UrlMonitor(String aUrl) throws IOException
    {
        url = aUrl;
        urlSize = 0;
        lastUpdate = 0;
        notifiers = new ArrayList<Notifier>();
        connect = getConnection(url);
    }

    public void run() throws IOException
    {

        System.out.println("Last Update: " + lastUpdate + " size: " + urlSize);
        Timer timer = new Timer();
        timer.schedule(new UrlCheck(), 10000, 100000);
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

    public void checkUrl()
    {
        long lastModified = connect.getLastModified();
        long currentSize = connect.getContentLength();
        if(lastUpdate != lastModified || urlSize != currentSize)
        {
            urlSize = currentSize;
            lastUpdate = lastModified;
            updateClients();
            try {
                String mementoFilename = url.replace("/", "") + ".ser";
                FileOutputStream out = new FileOutputStream(mementoFilename, false);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(this);
                objectOut.close();
                out.close();
            }
            catch (IOException e)
            {
                System.out.println("IO Exception " + e.toString());
            }
        }
    }

    private class UrlCheck extends TimerTask
    {
        @Override
        public void run()
        {
           UrlMonitor.this.checkUrl();
        }
    }

    protected URLConnection getConnection(String url) throws IOException
    {
        URL address = new URL(url);
        return  address.openConnection();
    }
}