import javax.mail.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cxj8923 on 4/26/15.
 */
public class UrlChangeMailer implements Notifier, Serializable
{
    private long updatedTime;
    private long updatedSize;
    private String username;
    private String password;
    private String server;
    private String port;
    private transient Session mailSession;

    UrlChangeMailer() throws IOException {
        mailSession = getSession();
    }

    private Session getSession() throws IOException {
        System.out.print("Username:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        username = br.readLine();
        System.out.print("Password:");
        br = new BufferedReader(new InputStreamReader(System.in));
        password = br.readLine();
        System.out.print("Server:");
        br = new BufferedReader(new InputStreamReader(System.in));
        server = br.readLine();
        System.out.print("Port:");
        br = new BufferedReader(new InputStreamReader(System.in));
        port = br.readLine();

        return null;
    }

    @Override
    public void update(Monitor aMonitor)
    {
        sendEmail();
    }

    private void sendEmail()
    {
        System.out.println("If I could I would email you");

    }
}
