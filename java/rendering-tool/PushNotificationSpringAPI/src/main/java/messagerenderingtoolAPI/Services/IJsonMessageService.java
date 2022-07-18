package messagerenderingtoolAPI.Services;

import javax.json.JsonObject;

public interface IJsonMessageService {
    JsonObject generateJsonMessage(IMessage pushContent);
}
