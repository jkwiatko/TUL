package messagerenderingtoolAPI.Implementations;

import messagerenderingtoolAPI.Services.IEmulator;
import messagerenderingtoolAPI.Services.IEmulatorsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmulatorsFromConfigFactory implements IEmulatorsFactory {
    private static int portNumber;
    private static Map<String, IEmulator> emulators;
    private static int emulatorsNumber;
    private static EmulatorsFromConfigFactory factory;

    public static EmulatorsFromConfigFactory getInstance() {
        if (factory == null)
            factory = new EmulatorsFromConfigFactory();
        return factory;
    }

    private EmulatorsFromConfigFactory() {
        portNumber = Integer.parseInt(Configuration.getProperty("MinPortNumber"));
        emulatorsNumber = Integer.parseInt(Configuration.getProperty("EmulatorsNumber"));
        emulators = new HashMap<>();
        initEmulators();
    }

    @Override
    public Map<String, IEmulator> getEmulators() {
        return emulators;
    }

    @Override
    public void addEmulator(String name) {
        IEmulator emulator = new Emulator(name, portNumber);
        portNumber += 2;
        emulators.put(name, emulator);
    }

    private void initEmulators() {
        for (int i = 0; i < emulatorsNumber; ++i) {
            addEmulator(Configuration.getProperty("Emulator" + i));
        }
    }

}