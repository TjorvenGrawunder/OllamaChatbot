package org.example.Ollama.responseobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Ollama.messageobjects.ErrorObject;
import org.example.Ollama.messageobjects.MessageObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject {
    private MessageObject message;
    private ErrorObject error;

    @JsonProperty("message")
    public MessageObject getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(MessageObject message) {
        this.message = message;
    }

    @JsonProperty("error")
    public ErrorObject getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(ErrorObject error) {
        this.error = error;
    }
}
