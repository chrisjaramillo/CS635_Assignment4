import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

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

    UrlChangeMailer() throws IOException {
        setupSession();
    }

    protected void setupSession() throws IOException {
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
    }

    @Override
    public void update(Monitor aMonitor)
    {
        updatedTime = ((UrlMonitor)aMonitor).getLastUpdate();
        updatedSize = ((UrlMonitor)aMonitor).getUrlSize();
        sendEmail(((UrlMonitor)aMonitor).url());
    }

    public String subject(String url)
    {
        Date time = new Date();
        return url + " updated " + time.toString();
    }

    public String text()
    {
        Date time = new Date(updatedTime);
        StringBuffer updateText = new StringBuffer();
        updateText.append("Most recent update time: " + time.toString());
        updateText.append("\nMost reccent size: " + updatedSize);
        return updateText.toString();
    }

    protected void sendEmail(String url)
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.server);
        props.put("mail.smtp.port", this.port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("cjaramillo@transcendinsights.com"));
            message.setSubject(subject(url));
            message.setText(text());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
