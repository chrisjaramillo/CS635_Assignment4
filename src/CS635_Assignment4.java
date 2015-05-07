/**
 * Created by cxj8923 on 4/27/15.
 */

import org.apache.commons.cli.*;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CS635_Assignment4 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        File dir = new File(".");
        File [] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ser");
            }
        });
        if(files.length == 0)
        {
            Options options = new Options();
            options.addOption("f", false, "input file");
            CommandLineParser parser = new GnuParser();
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption('f'))
            {
                String[] arguments = cmd.getArgs();
                String urlFilename = arguments[0];
                Parser fileParser = new Parser(urlFilename);
                Map<String, List<String>> urlMap = fileParser.parse();
                Set<String> keySet = urlMap.keySet();
                for(String key : keySet)
                {
                    UrlMonitor mon = new UrlMonitor(key);
                    List<String> notifiers = urlMap.get(key);
                    for(String notifier : notifiers)
                    {
                        Notifier aNotifier = null;
                        if(notifier.equals("mail"))
                        {
                            aNotifier = new UrlChangeMailer();
                        }
                        else if(notifier.equals("transcript"))
                        {
                            aNotifier = new UrlChangeTranscript();
                        }
                        mon.addNotifier(aNotifier);
                    }
                    mon.run();
                }
            }
            else
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "assignment4", options );
                return;
            }
        }
        else
        {
            for (File memento : files) {
                FileInputStream in = new FileInputStream(memento);
                ObjectInputStream oIn = new ObjectInputStream(in);
                UrlMonitor monitorMemento = (UrlMonitor) oIn.readObject();
                oIn.close();
                in.close();
                monitorMemento.run();
            }

        }
    }

}