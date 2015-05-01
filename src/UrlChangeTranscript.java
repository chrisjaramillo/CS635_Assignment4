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
        long newTime = ((UrlMonitor)aMonitor).getLastUpdate();
        long newSize = ((UrlMonitor)aMonitor).getUrlSize();
        if(updatedTime != newTime)
        {
            updatedTime = newTime;
            Date modifiedDate = new Date(newTime);
            updateMessage.append(" at time ").append(modifiedDate.toString());
        }
        if(updatedSize != newSize)
        {
            updatedSize = newSize;
            updateMessage.append(" content size is now ").append(newSize);
        }
        System.out.println(updateMessage.toString());
    }
}
