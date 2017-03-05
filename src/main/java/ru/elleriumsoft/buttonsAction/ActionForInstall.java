package ru.elleriumsoft.buttonsAction;

import ru.elleriumsoft.MainFrame;
import ru.elleriumsoft.PathToServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class ActionForInstall implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            copyFiles();
            new ProcessBuilder("cmd", "/c","start", PathToServer.getPathToServer() + "\\bin\\standalone.bat").start();
            JOptionPane.showMessageDialog(MainFrame.get(), "Подождите запуска сервера!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            MainFrame.enableRunButton();
        } catch (IOException e1)
        {
            JOptionPane.showMessageDialog(MainFrame.get(), "Не удалось скопировать файлы!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void copyFiles() throws IOException
    {
        PathToServer.copyFile(".\\add.files\\base.s3db", PathToServer.getPathToServer() + "\\base.s3db");
        PathToServer.copyFile(".\\add.files\\sqlite-jdbc-3.16.1.jar", PathToServer.getPathToServer() + "\\standalone\\deployments\\sqlite-jdbc-3.16.1.jar");
        PathToServer.copyFile(".\\add.files\\laba3.war", PathToServer.getPathToServer() + "\\standalone\\deployments\\laba3.war");
        PathToServer.copyFile(PathToServer.getPathToServer() + "\\standalone\\configuration\\standalone.xml", PathToServer.getPathToServer() + "\\standalone\\configuration\\_standalone.xml");
        copyXml(PathToServer.getPathToServer() + "\\standalone\\configuration\\_standalone.xml", PathToServer.getPathToServer() + "\\standalone\\configuration\\standalone.xml");
    }

    private void copyXml(String sourcerPath, String destPath) throws IOException
    {
        String xmlIn = new String(Files.readAllBytes(Paths.get(sourcerPath)));

        String xmlSqlite = new String(Files.readAllBytes(Paths.get(".\\add.files\\sqlite.txt")));
        int index;
        index = xmlSqlite.indexOf("forreplaceplace");
        xmlSqlite =  xmlSqlite.substring(0, index) + PathToServer.getPathToServer() + xmlSqlite.substring(index + "forreplaceplace".length());

        index = xmlIn.indexOf("<datasources>");
        String  xmlOut =  xmlIn.substring(0, index + "<datasources>".length()) + xmlSqlite + xmlIn.substring(index + "<datasources>".length());

        Files.write(Paths.get(destPath), xmlOut.getBytes());

    }

}
