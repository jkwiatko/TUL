package messagerenderingtoolAPI.Implementations;

import messagerenderingtoolAPI.Services.IEmulator;
import messagerenderingtoolAPI.Services.IEmulatorsFactory;
import messagerenderingtoolAPI.Services.IEmulatorsManager;
import messagerenderingtoolAPI.Services.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmulatorsManager implements IEmulatorsManager{
    private static Map<String, IEmulator> emulators;
    private static IEmulatorsManager emulatorsManager;
    private static IEmulatorsFactory emulatorsFactory;


    private EmulatorsManager(IEmulatorsFactory iEmulatorsFactory){
        emulatorsFactory = iEmulatorsFactory;
        emulators = emulatorsFactory.getEmulators();
    }

    @Autowired
    public static IEmulatorsManager getInstance(IEmulatorsFactory iEmulatorsFactory) {
        if (emulatorsManager == null)
            emulatorsManager = new EmulatorsManager(iEmulatorsFactory);
        return emulatorsManager;
    }


    public void addEmulator(String emulatorName) {
        emulatorsFactory.addEmulator(emulatorName);
    }

    public void startEmulator(String emulatorName) {
        IEmulator emulator = emulators.get(emulatorName);
        if (emulator != null)
            emulator.runDevice();
    }

    @Override
    public void startEmulators() {
        for (IEmulator emulator : emulators.values()) {
            emulator.runDevice();
        }
    }

    @Override
    public void startAppOnAllEmulators(int uuid, IMessage message) {
        for (IEmulator emulator : emulators.values()) {
            emulator.runApp(uuid, message);
        }
    }

    public void installApp(String emulatorName) {
        IEmulator emulator = emulators.get(emulatorName);
        if (emulator != null)
            emulator.installApp();
    }

    public void startApp(String emulatorName, int uuid, IMessage message) {
        IEmulator emulator = emulators.get(emulatorName);
        if (emulator != null)
            emulator.runApp(uuid, message);
    }
}
