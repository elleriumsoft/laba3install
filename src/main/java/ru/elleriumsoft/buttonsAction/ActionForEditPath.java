package ru.elleriumsoft.buttonsAction;


import ru.elleriumsoft.MainFrame;
import ru.elleriumsoft.PathToServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class ActionForEditPath implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser  fileDialog = new JFileChooser ();
        fileDialog.setDialogTitle("Выбор пути до сервера");

        fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileDialog.showOpenDialog(MainFrame.get());

        if (result == JFileChooser.APPROVE_OPTION )
        {
            PathToServer.setPathToServer(fileDialog.getSelectedFile().getPath());
            if (!PathToServer.isCorrectPath())
            {
                JOptionPane.showMessageDialog(MainFrame.get(), "По указанному пути нет Wildfly!","Ошибка", JOptionPane.ERROR_MESSAGE);
                PathToServer.setPathToServer("Путь ошибочен!");
            }
            else
            {
                MainFrame.enableInstallButton();
            }
        }
    }
}
