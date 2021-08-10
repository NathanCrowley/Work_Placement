import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    private static final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws IOException, InterruptedException {


        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(POSTS_API_URL))
                .build();

        HttpResponse<String> response =  httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());
    }
}
