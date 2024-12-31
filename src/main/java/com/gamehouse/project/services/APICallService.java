package com.gamehouse.project.services;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APICallService {


    public void searchGames(String searchItem) {
        HttpResponse<String> response = null;
        try {

            String searchTerm = "fields name; search \"" + searchItem + "\";";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/games/"))
                    .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                    .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                    .setHeader("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                    .build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    };

}


/*public HttpResponse http(String url, String body) {

    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(body);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse result = httpClient.execute(request);

        String json = EntityUtils.toString(result.getEntity(), "UTF-8");
        try {
            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(json);

            if (resultObject instanceof JSONArray) {
                JSONArray array=(JSONArray)resultObject;
                for (Object object : array) {
                    JSONObject obj =(JSONObject)object;
                    System.out.println(obj.get("example"));
                    System.out.println(obj.get("fr"));
                }

            }else if (resultObject instanceof JSONObject) {
                JSONObject obj =(JSONObject)resultObject;
                System.out.println(obj.get("example"));
                System.out.println(obj.get("fr"));
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    } catch (IOException ex) {
    }
    return null;
}*/