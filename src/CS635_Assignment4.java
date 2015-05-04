/**
 * Created by cxj8923 on 4/27/15.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class CS635_Assignment4 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties props = new Properties();
        SMTPAuthenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);
        File f = new File("monitor.ser");
        if(!f.exists())
        {
            UrlMonitor mon = new UrlMonitor("http://eli.sdsu.edu/");
            mon.addNotifier(new UrlChangeTranscript());
            mon.addNotifier(new UrlChangeMailer());
            FileOutputStream out = new FileOutputStream("monitor.ser", false);
            ObjectOutputStream oOut = new ObjectOutputStream(out);
            oOut.writeObject(mon);
            oOut.close();
            out.close();
            mon.run();
        }
        else
        {
            FileInputStream in = new FileInputStream("monitor.ser");
            ObjectInputStream oIn = new ObjectInputStream(in);
            UrlMonitor mon2 = (UrlMonitor) oIn.readObject();
            oIn.close();
            in.close();
            mon2.run();
        }
    }


}

class SMTPAuthenticator extends javax.mail.Authenticator {
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "chris";
        String password = "chris";
        return new PasswordAuthentication(username, password);
    }
}