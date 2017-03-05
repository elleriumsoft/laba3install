package ru.elleriumsoft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by Dmitriy on 27.02.2017.
 */
public class PathToServer
{
    private static String pathToServer = "Путь не выбран";

    public static String getPathToServer()
    {
        return pathToServer;
    }

    public static void setPathToServer(String pathToServer)
    {
        PathToServer.pathToServer = pathToServer;
        MainFrame.getLabel().setText(pathToServer);
    }

    public static boolean isCorrectPath()
    {
        String path = getPathToServer() + "\\bin\\standalone.bat";
        File file = new File(path);
        if (file.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void copyFile(String sourcePath, String destPath) throws IOException
    {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        Files.copy(source.toPath(), dest.toPath(),  REPLACE_EXISTING);
    }
}
