package messagerenderingtoolAPI.Implementations;

import messagerenderingtoolAPI.Services.IMessage;

public class Message implements IMessage {
    private String to;
    private String title;
    private String body;
    private String smallImageLink;
    private String largeImageLink;
    private String actionAName;
    private String actionBName;


    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getSmallImageLink() {
        return smallImageLink;
    }

    @Override
    public String getLargeImageLink() {
        return largeImageLink;
    }

    @Override
    public String getActionAName() {
        return actionAName;
    }

    @Override
    public String getActionBName() {
        return actionBName;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void setSmallImageLink(String sImageLink) {
        this.smallImageLink = sImageLink;
    }

    @Override
    public void setLargeImageLink(String lImageLink) {
        this.largeImageLink = lImageLink;
    }

    @Override
    public void setActionAName(String actionAName) {
        this.actionAName = actionAName;
    }

    @Override
    public void setActionBName(String actionBName) {
        this.actionBName = actionBName;
    }

    public IMessage getSampleMessage() {
        to = "topics/all";
        title = "Hello Anna! ;)";
        body = "How are you?";
        smallImageLink = "";
        largeImageLink = "";
        actionAName = "Go to offers";
        actionBName = "Go to rewards";

        return this;
    }
}
