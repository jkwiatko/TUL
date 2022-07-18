package messagerenderingtoolAPI.Implementations;


import messagerenderingtoolAPI.Services.IJsonMessageService;
import messagerenderingtoolAPI.Services.IMessage;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;




public class JsonMessageService implements IJsonMessageService {
    @Override
    public JsonObject generateJsonMessage(IMessage pushContent) {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder builder = factory.createObjectBuilder()
                .add("data", Json.createObjectBuilder()
                        .add("data", Json.createObjectBuilder()
                                .add("title", pushContent.getTitle())
                                .add("content", pushContent.getBody())
                                .add("small_image_url", pushContent.getSmallImageLink())
                                .add("large_image_url", pushContent.getLargeImageLink())
                                .add("actions", Json.createArrayBuilder()
                                        .add(Json.createObjectBuilder()
                                                .add("name", pushContent.getActionAName())
                                        )
                                        .add(Json.createObjectBuilder()
                                                .add("name", pushContent.getActionBName())
                                        )
                                )
                        )
                        .add("to", pushContent.getTo())
                );
        return builder.build();
    }

    public JsonObject generateSampleJson() {
        IMessage message = new Message().getSampleMessage();
        return generateJsonMessage(message);
    }

}