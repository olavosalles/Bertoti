package org.example;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatResult;

public class Atividade4 {

    public static void main(String[] args) throws Exception {

        Ollama ollama = new Ollama("http://127.0.0.1:11434/");
        String model = "gemma3:270m";
        ollama.pullModel(model);
        ollama.setRequestTimeoutSeconds(200);
        OllamaChatRequest builder = OllamaChatRequest.builder().withModel(model);

        // create first user question
        OllamaChatRequest requestModel =
                builder.withMessage(OllamaChatMessageRole.USER, "What is the capital of France?")
                        .build();

        // start conversation with model
        OllamaChatResult chatResult = ollama.chat(requestModel, null);

        System.out.println(
                "First answer: " + chatResult.getResponseModel().getMessage().getResponse());

        // create next userQuestion
        requestModel =
                builder.withMessages(chatResult.getChatHistory())
                        .withMessage(
                                OllamaChatMessageRole.USER, "And what is the second largest city?")
                        .build();

        // "continue" conversation with model
        chatResult = ollama.chat(requestModel, null);

        System.out.println(
                "Second answer: " + chatResult.getResponseModel().getMessage().getResponse());

        System.out.println("Chat History: " + chatResult.getChatHistory());
    }
}