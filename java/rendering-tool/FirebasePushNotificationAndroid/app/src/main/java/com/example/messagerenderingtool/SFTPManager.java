package com.example.messagerenderingtool;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;



import java.nio.charset.Charset;
import java.util.Properties;

public class SFTPManager {
    private String server;
    private int port;
    private String login;
    private String password;

    private JSch jsch = null;
    private Session session = null;
    private Channel channel = null;
    private ChannelSftp c = null;

    public SFTPManager(String server, int port, String login, String password) {
        this.server = server;
        this.port = port;
        this.login = login;
        this.password = password;
    }

    /**
     * Connects to the server and does some commands.
     */
    public void connect() {
        try {
            jsch = new JSch();
            session = jsch.getSession(login, server, port);

            // Java 6 version
            session.setPassword(password.getBytes(Charset.forName("ISO-8859-1")));

            // Java 5 version
            // session.setPassword(password.getBytes("ISO-8859-1"));

            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            // Initializing a channel
            channel = session.openChannel("sftp");
            channel.connect();
            c = (ChannelSftp) channel;
        } catch (JSchException e) {
        }
    }

    /**
     * Uploads a file to the sftp server
     * @param sourceFile String path to sourceFile
     * @param destinationFile String path on the remote server
     * @throws Exception if connection and channel are not available or if an error occurs during upload.
     */
    public void uploadFile(String sourceFile, String destinationFile) throws Exception {
        if (c == null || session == null || !session.isConnected() || !c.isConnected()) {
            throw new Exception("Connection to server is closed. Open it first.");
        }

        try {
            c.put(sourceFile, destinationFile);
        } catch (SftpException e) {
            throw new Exception(e);
        }
    }

    /**
     * Retrieves a file from the sftp server
     * @param destinationFile String path to the remote file on the server
     * @param sourceFile String path on the local fileSystem
     * @throws Exception if connection and channel are not available or if an error occurs during download.
     */
    public void retrieveFile(String sourceFile, String destinationFile) throws Exception {
        if (c == null || session == null || !session.isConnected() || !c.isConnected()) {
            throw new Exception("Connection to server is closed. Open it first.");
        }

        try {
            c.get(sourceFile, destinationFile);
        } catch (SftpException e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public void disconnect() {
        if (c != null) {
            c.disconnect();
        }
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}