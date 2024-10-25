package org.example.Ollama;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private RequestFormat requestFormat;
    private final HttpClient CLIENT = HttpClient.newHttpClient();

    public Client() {
        requestFormat = new RequestFormat();
    }

    /**
     * Sends a message to the chat model, without completion request
     * @param message the message to send
     * @return the response from the chat model
     */
    public String sendMessage(String message) throws IOException, InterruptedException {
        //Get current chat request format from the settings
        HttpRequest request = requestFormat.getRequest(message);

        HttpResponse<String> response;

        // Send message to the chat model
        response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the response
        return requestFormat.parseResponse(response.body());
    }
}
