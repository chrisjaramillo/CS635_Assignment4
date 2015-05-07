import javax.mail.Session;
import java.io.IOException;

/**
 * Created by cxj8923 on 5/6/15.
 */
public class MockUrlChangeMailer extends UrlChangeMailer
{
    public String url;
    public String subject;
    public String text;

    MockUrlChangeMailer() throws IOException {
        super();
    }

    @Override
    protected void setupSession() throws IOException {

    }

    protected void sendEmail(String aUrl)
    {
        url = aUrl;
        subject = subject(aUrl);
        text = text();
    }
}
