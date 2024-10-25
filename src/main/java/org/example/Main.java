package org.example;

import org.example.Ollama.Client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Client client;

    public Main() {
        client = new Client();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                String response = client.sendMessage(userInput);
                System.out.println("Bot: " + response);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Main chatbot = new Main();
        chatbot.startChat();
    }
}