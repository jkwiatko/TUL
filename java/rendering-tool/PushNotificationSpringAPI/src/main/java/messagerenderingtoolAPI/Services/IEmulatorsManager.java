package messagerenderingtoolAPI.Services;

import org.springframework.stereotype.Service;

public interface IEmulatorsManager {
    void addEmulator(String emulatorName);
    void startEmulator(String emulatorName);
    void startEmulators();
    void startAppOnAllEmulators(int uuid, IMessage message);
    void installApp(String emulatorName);
    void startApp(String emulatorName, int uuid, IMessage message);
}
