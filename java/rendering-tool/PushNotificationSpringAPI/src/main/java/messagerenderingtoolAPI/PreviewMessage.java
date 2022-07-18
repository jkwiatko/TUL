package messagerenderingtoolAPI;

import messagerenderingtoolAPI.Implementations.*;
import messagerenderingtoolAPI.Services.IEmulatorsManager;
import messagerenderingtoolAPI.Services.IFtpManager;
import messagerenderingtoolAPI.Services.IJsonMessageService;
import messagerenderingtoolAPI.Services.IMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PreviewMessage {
    private IFtpManager ftpManager;
    private IEmulatorsManager emulatorsManager;
    private IJsonMessageService messageService;
    private List<Integer> uuIds;

    public PreviewMessage(IEmulatorsManager iEmulatorsManager) {
        uuIds = new ArrayList<>();
        ftpManager = new FtpManagerImpl();
        emulatorsManager = iEmulatorsManager;
        messageService = new JsonMessageService();
    }



    public int startEmulators(IMessage message) {
        emulatorsManager.startEmulators();
        int uuid = generateUuid();
        new Thread ( () -> startApp(uuid, message)).start();

        return uuid;
    }

    private void startApp(int uuid, IMessage message) {
        try {
            Thread.sleep(15000);
            emulatorsManager.startAppOnAllEmulators(uuid, message);
            Thread.sleep(5000);
            ftpManager.setUuid(uuid);
            ftpManager.getScreens();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int startEmulator(String name) {
        emulatorsManager.startEmulator(name);
        return generateUuid();
    }

    private int generateUuid() {
        int uuid = new Random().nextInt();
        while (uuIds.contains(uuid))
            uuid = new Random().nextInt();
        uuIds.add(uuid);
        return uuid;
    }
}
