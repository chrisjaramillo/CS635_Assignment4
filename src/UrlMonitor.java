import java.io.Serializable;
import java.net.URLConnection;
import java.util.Observable;

/**
 * Created by cxj8923 on 4/26/15.
 */
public class UrlMonitor extends Observable implements Serializable{

    private URLConnection connect;

    public UrlMonitor(URLConnection aConnection)
    {
        connect = aConnection;
    }

    public void addMailListener()
    {
        UrlChangeMailer mailer = new UrlChangeMailer();
        this.addObserver(mailer);
    }

    public void addTranscriptListener()
    {
        UrlChangeTranscript transcript = new UrlChangeTranscript();
        this.addObserver(transcript);
    }

}