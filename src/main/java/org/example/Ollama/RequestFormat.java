package org.example.Ollama;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Ollama.messageobjects.ChatRequestObject;
import org.example.Ollama.messageobjects.MessageObject;
import org.example.Ollama.responseobject.ResponseObject;

public class RequestFormat {

    private final String API_URL = "http://131.173.38.68:11434/api/chat";

    /**
     * Get a request from the message
     * @param message the chat message to send to the server
     * @return HttpRequest to send to the server
     * @throws JsonProcessingException
     */
    public HttpRequest getRequest(String message) throws JsonProcessingException {
        //Create a json object from the message
        ObjectMapper mapper = new ObjectMapper();
        // Ignore null values
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // Create the message object
        MessageObject messageObject = new MessageObject("user", message);
        // Create the request object
        ChatRequestObject requestObject = new ChatRequestObject("llama3", getSystemPromptsWithMessage(messageObject), false);

        // Create the request
        return HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestObject)))
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .build();
    }

    /**
     * Parse the response from the chat model
     * @param response the response from the chat model
     * @return the content of the response
     * @throws JsonProcessingException
     */
    public String parseResponse(String response) throws JsonProcessingException {
        // Parse the response
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject;
        responseObject = mapper.readValue(response, ResponseObject.class);

        // Get the content of the response
        response = responseObject.getMessage().getContent();

        return response;
    }

    /**
     * Get the system prompts with the message
     * @param messageObject the message object to add to the system prompts
     * @return the system prompts with the message
     */
    private List<MessageObject> getSystemPromptsWithMessage(MessageObject messageObject) {
        List<MessageObject> systemPrompts = new ArrayList<>();
        systemPrompts.add(new MessageObject("system", "Welcome to Ollama!"));
        systemPrompts.add(messageObject);
        return systemPrompts;
    }
}
