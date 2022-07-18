package messagerenderingtoolAPI;

import messagerenderingtoolAPI.Implementations.Message;
import messagerenderingtoolAPI.Services.IEmulatorsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PreviewMessageController {
    private PreviewMessage previewMessage;
    private MessageEditor messageEditor;

    @Autowired
    public PreviewMessageController(IEmulatorsManager iEmulatorsManager) {
        previewMessage = new PreviewMessage(iEmulatorsManager);
        messageEditor = new MessageEditor();
    }

    @RequestMapping(path = "/preview-message/push", method = RequestMethod.POST)
    public int generatePush() {
        return previewMessage.startEmulators(messageEditor.getMessage());
    }

    @RequestMapping(path = "/preview-message/push/{name}", method = RequestMethod.POST)
    public int generatePushOnConcreteDevice(@RequestParam String name) {
        return previewMessage.startEmulator(name);
    }

    @RequestMapping(path = "/preview-message/push", method = RequestMethod.GET)
    public void getPushResults(@PathVariable int id) {
    }

    @RequestMapping(path = "/message/title", method = RequestMethod.POST)
    public void setMessageTitle(@RequestBody String title) {
        messageEditor.setTitle(title);
    }

    @RequestMapping(path = "/message/body", method = RequestMethod.POST)
    public void setMessageBody(@RequestBody String body) {
        messageEditor.setTitle(body);
    }

    @RequestMapping(path = "/message/to", method = RequestMethod.POST)
    public void setMessageTo(@RequestBody String to) {
        messageEditor.setTo(to);
    }

    @RequestMapping(path = "/message/actionA", method = RequestMethod.POST)
    public void setMessageActionA(@RequestBody String actionA) {
        messageEditor.setActionA(actionA);
    }

    @RequestMapping(path = "/message/actionB", method = RequestMethod.POST)
    public void setMessageActionB(@RequestBody String actionB) {
        messageEditor.setActionB(actionB);
    }

    @RequestMapping(path = "/message/largeImage", method = RequestMethod.POST)
    public void setMessageLargeImage(@RequestBody String image) {
        messageEditor.setLargeImage(image);
    }

    @RequestMapping(path = "/message/smallImage", method = RequestMethod.POST)
    public void setMessageSmallImage(@RequestBody String image) {
        messageEditor.setSmallImage(image);
    }

}
