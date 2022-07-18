package messagerenderingtoolAPI.Implementations;

import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class SFtpManagerImpl {
    private String login;
    private String password;
    private String server;
    private String parentDirectory;
    private String currentDirectory;
    private int uuid;
    private String saveDirectory;
    private FTPClient ftpClient;
    private Session session = null;

    SFtpManagerImpl(int _uuid) {
        login = Configuration.getProperty("Login");
        password = Configuration.getProperty("Password");
        server = Configuration.getProperty("Server");
        parentDirectory = Configuration.getProperty("Directory");
        uuid = _uuid;
        currentDirectory = String.valueOf(uuid);
        saveDirectory = System.getProperty("user.dir");
    }
    public void downloadScreens() { // testowa metoda TODO
        Session session = null;
        try {
            createSession();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftpChannel = (ChannelSftp) channel;

            try {
                download(sftpChannel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
    private void createSession() throws JSchException, SftpException {
        JSch jsch = new JSch();
        session = jsch.getSession(login + '@' + server, server, 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();
    }

    private void download(ChannelSftp channel) throws SftpException, FileNotFoundException {
        File currentDir = new File(currentDirectory);
        channel.cd("cd " + parentDirectory);
        if(currentDir.isDirectory() && currentDir.listFiles() != null){
            for(File file: Objects.requireNonNull(currentDir.listFiles())){
                channel.get(file.getName(), saveDirectory + '/' + file.getName());
            }
        }
    }

}
