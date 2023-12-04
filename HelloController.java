package com.example.newsapp;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextArea newsTextArea;

    private static final String API_KEY = "7860466222a2418c8a1e9d80d9095f98";
    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

    @FXML
    public void initialize() {
        titleLabel.setText("Latest News Headlines");
    }

    @FXML
    public void fetchNewsData() {
        try {
            URL url = new URL(NEWS_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();


                newsTextArea.setText(response.toString());
            } else {
                newsTextArea.setText("Failed to fetch news data. Response code: " + responseCode);
            }
        } catch (IOException e) {
            newsTextArea.setText("Exception occurred: " + e.getMessage());
        }
    }
}
