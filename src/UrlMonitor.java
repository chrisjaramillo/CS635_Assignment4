import java.io.Serializable;
import java.net.URLConnection;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cxj8923 on 4/26/15.
 */
public class UrlMonitor extends Observable implements Serializable{

    private URLConnection connect;
    private long urlSize;
    private long lastUpdate;

    public UrlMonitor(URLConnection aConnection)
    {
        connect = aConnection;
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
        return  connect.toString();
    }
    private class UrlCheck extends TimerTask
    {

        @Override
        public void run()
        {
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

}