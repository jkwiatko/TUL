package messagerenderingtoolAPI;

import messagerenderingtoolAPI.Implementations.Message;
import messagerenderingtoolAPI.Services.IMessage;

public class MessageEditor {
    private IMessage message;

    public MessageEditor() {
        message = new Message().getSampleMessage();
    }

    public IMessage getMessage() {
        return message;
    }

    public void setTitle(String title) {
        message.setTitle(title);
    }

    public void setBody(String body) {
        message.setBody(body);
    }

    public void setActionA(String actionA) {
        message.setActionAName(actionA);
    }

    public void setActionB(String actionB) {
        message.setActionBName(actionB);
    }

    public void setSmallImage(String smallImage) {
        message.setSmallImageLink(smallImage);
    }

    public void setLargeImage(String largeImage) {
        message.setLargeImageLink(largeImage);
    }

    public void setTo (String to) {
        message.setTo(to);
    }
}
