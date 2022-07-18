package messagerenderingtoolAPI.Implementations;

import messagerenderingtoolAPI.Services.IEmulator;
import messagerenderingtoolAPI.Services.IMessage;

import java.io.IOException;

public class Emulator implements IEmulator {
    private final String deviceName;
    private final String appName;
    private final String activityName;
    private final int port;
    private final String adbExe;
    private final String emulatorExe;

    public Emulator(String _deviceName, int _port) {
        deviceName = _deviceName;
        port = _port;
        appName = Configuration.getProperty("AppName");
        activityName = Configuration.getProperty("ActivityName");
        adbExe = Configuration.getProperty("PathToAdbExe");
        emulatorExe = Configuration.getProperty("PathToEmulatorExe");
    }

    public void runDevice() {
        String[] arguments = {Configuration.getProperty("PowerOnEmulatorBat"), emulatorExe, deviceName, String.valueOf(port)};
        ProcessBuilder builder = new ProcessBuilder(arguments);
        try {
            builder.start();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void installApp() {
        String[] command = {Configuration.getProperty("InstallAppBat"), adbExe, String.valueOf(port), appName};
        ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runApp(int uuid, IMessage message) {
        String[] command = {Configuration.getProperty("RunAppBat"), adbExe, String.valueOf(port), activityName, String.valueOf(uuid),
                message.getTitle(), message.getBody(), message.getActionAName(), message.getActionBName(),
                message.getLargeImageLink(), message.getSmallImageLink()};
        ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
