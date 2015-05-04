import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cxj8923 on 4/26/15.
 */
public class UrlChangeTranscript implements Notifier, Serializable {
    private long updatedTime;
    private long updatedSize;

    @Override
    public void update(Monitor aMonitor)
    {
        StringBuffer updateMessage = new StringBuffer();
        updateMessage.append(((UrlMonitor)aMonitor).url()).append(" updated:");
        updatedTime = ((UrlMonitor)aMonitor).getLastUpdate();
        updatedSize = ((UrlMonitor)aMonitor).getUrlSize();
        System.out.println(((UrlMonitor) aMonitor).url() + this);
    }

    @Override
    public String toString()
    {
        StringBuffer output = new StringBuffer();
        Date modified = new Date(updatedTime);
        output.append(" Last updated at time: ").append(modified.toString());
        output.append(" size: ").append(updatedSize);
        return  output.toString();
    }
}
