/**
 * Created by cxj8923 on 4/27/15.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class CS635_Assignment4 {

    public static void main(String[] args) throws IOException
    {
        Properties props = new Properties();
        SMTPAuthenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);
        UrlMonitor mon = new UrlMonitor("http://eli.sdsu.edu/");
        mon.addNotifier(new UrlChangeTranscript());
    }


}

class SMTPAuthenticator extends javax.mail.Authenticator {
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "chris";
        String password = "chris";
        return new PasswordAuthentication(username, password);
    }
}