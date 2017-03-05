package ru.elleriumsoft;

import ru.elleriumsoft.buttonsAction.ActionForEditPath;
import ru.elleriumsoft.buttonsAction.ActionForInstall;
import ru.elleriumsoft.buttonsAction.ActionForRun;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class MainFrame
{
    private static final Frame mainFrame = new Frame("Установщик");

    public static void init()
    {
        mainFrame.setBounds(200,200,400,200);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        MainFrame.get().addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        createLabel();

        createButtons();

        mainFrame.setVisible(true);
    }

    private static void createLabel()
    {
        Label labelForPath = new Label(PathToServer.getPathToServer());
        labelForPath.setBounds(0, 25, 400,30);
        labelForPath.setAlignment(Label.CENTER);
        labelForPath.setVisible(true);
        MainFrame.get().add(labelForPath);
    }

    private static void createButtons()
    {
        Button buttonForEditPath = new Button("Выбрать путь до сервера WildFly");
        buttonForEditPath.setBounds(0, 50, 400, 50);
        buttonForEditPath.setVisible(true);
        buttonForEditPath.setEnabled(true);
        buttonForEditPath.addActionListener(new ActionForEditPath());
        MainFrame.get().add(buttonForEditPath);

        Button buttonForInstall = new Button("Установить");
        buttonForInstall.setBounds(0, 100, 400, 50);
        buttonForInstall.setVisible(true);
        buttonForInstall.setEnabled(false);
        buttonForInstall.addActionListener(new ActionForInstall());
        MainFrame.get().add(buttonForInstall);

        Button buttonForRun = new Button("Запустить");
        buttonForRun.setBounds(0, 150, 400, 50);
        buttonForRun.setVisible(true);
        buttonForRun.setEnabled(false);
        buttonForRun.addActionListener(new ActionForRun());
        MainFrame.get().add(buttonForRun);
    }

    public static Frame get()
    {
        return mainFrame;
    }

    public static Label getLabel()
    {
        Component[] components = mainFrame.getComponents();
        for (Component component : components)
        {
            if (component.getClass().getName().equals("java.awt.Label"))
            {
                return (Label) component;
            }
        }
        return null;
    }

    public static void enableInstallButton()
    {
        Component[] components = mainFrame.getComponents();
        for (Component component : components)
        {
            if (component.getClass().getName().equals("java.awt.Button") && component.getName().equals("button1"))
            {
                component.setEnabled(true);
            }
        }
    }

    public static void enableRunButton()
    {
        Component[] components = mainFrame.getComponents();
        for (Component component : components)
        {
            if (component.getClass().getName().equals("java.awt.Button") && component.getName().equals("button2"))
            {
                component.setEnabled(true);
            }
        }
    }
}
