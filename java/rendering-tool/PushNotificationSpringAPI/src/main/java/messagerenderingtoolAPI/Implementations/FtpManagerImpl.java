package messagerenderingtoolAPI.Implementations;

import messagerenderingtoolAPI.Services.IFtpManager;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;

public class FtpManagerImpl implements IFtpManager {

    private String login;
    private String password;
    private String server;
    private String parentDirectory;
    private String currentDirectory;
    private int uuid;
    private String saveDirectory;
    private FTPClient ftpClient;


    public FtpManagerImpl() {
        login = Configuration.getProperty("Login");
        password = Configuration.getProperty("Password");
        server = Configuration.getProperty("Server");
        parentDirectory = Configuration.getProperty("Directory");
        ftpClient = new FTPClient();
    }

    public FtpManagerImpl(int _uuid) {
        login = Configuration.getProperty("Login");
        password = Configuration.getProperty("Password");
        server = Configuration.getProperty("Server");
        parentDirectory = Configuration.getProperty("Directory");
        ftpClient = new FTPClient();
        setUuid(_uuid);
    }

    private void connectToFtpServer() throws IOException {
        ftpClient.connect(server);
        ftpClient.enterLocalPassiveMode();
    }

    private boolean loginToFtpServer() throws IOException {
        return ftpClient.login(login + "@" + server, password);
    }

    private void createLocalDirectory(String path) {
        new File(path).mkdirs();
    }

    private void changeFtpDirectory(String path) throws IOException {
        ftpClient.changeWorkingDirectory(path);
    }

    private void downloadFiles(FTPFile[] files) throws IOException{
        if (files != null && files.length > 0) {
            for (FTPFile aFile : files) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    continue;
                }
                File downloadFile = new File(saveDirectory + File.separator + aFile.getName());
                OutputStream output = new FileOutputStream(downloadFile);
                ftpClient.retrieveFile(parentDirectory + currentDirectory + "/" + aFile.getName(), output);
                output.close();
            }
        }
    }

    public void getScreens() {
        try {
            connectToFtpServer();
            if (loginToFtpServer()) {
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                createLocalDirectory(saveDirectory);
                changeFtpDirectory(parentDirectory);
                FTPFile[] subFiles = ftpClient.listFiles(currentDirectory);
                downloadFiles(subFiles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadScreens() {

    }

    public void setUuid(int _uuid) {
        uuid = _uuid;
        currentDirectory = Integer.toString(uuid);
        saveDirectory = System.getProperty("user.dir") + File.separator + uuid + File.separator;
    }
}
