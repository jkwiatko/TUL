package messagerenderingtoolAPI.Services;

public interface IEmulator {
    void runDevice();
    void installApp();
    void runApp(int uuid, IMessage message);
}
