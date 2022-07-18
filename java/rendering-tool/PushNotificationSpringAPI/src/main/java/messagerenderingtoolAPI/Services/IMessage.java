package messagerenderingtoolAPI.Services;

public interface IMessage {
    String getTo();
    String getTitle();
    String getBody();
    String getSmallImageLink();
    String getLargeImageLink();
    String getActionAName();
    String getActionBName();
    void setTo(String to);
    void setTitle(String title);
    void setBody(String body);
    void setSmallImageLink(String sImageLink);
    void setLargeImageLink(String lImageLink);
    void setActionAName(String actionAName);
    void setActionBName(String actionBName);
}
