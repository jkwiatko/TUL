package messagerenderingtoolAPI.Services;

import java.util.Map;

public interface IEmulatorsFactory {
    Map<String, IEmulator> getEmulators();
    void addEmulator(String name);
}
