package ru.elleriumsoft.buttonsAction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class ActionForRun implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        launchBrowser("127.0.0.1:8080/laba3");
    }

    private void launchBrowser(String uriStr) {
        Desktop desktop;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri;
                try {
                    uri = new URI("http://" + uriStr);
                    desktop.browse(uri);
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                catch (URISyntaxException use) {
                    use.printStackTrace();
                }
            }
        }
    }
}
