package demo.consumer;

import demo.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Consumer {

    private static final String URL = "http://94.198.50.185:7081/api/users";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static List<String> set_cookie;
    private static HttpHeaders httpHeaders = new HttpHeaders();
    public static String result = "";


    public String getUsers() {
        HttpEntity<String> request = new HttpEntity<>("body", httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL,HttpMethod.GET, request, String.class);
        set_cookie = response.getHeaders().get("set-cookie");
        return response.getBody();
    }

    public void addUser(User user) {
        httpHeaders.set("cookie", String.join(";", set_cookie));
        httpHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL,HttpMethod.POST, request, String.class);
        result += response.getBody();
    }

    public void updateUser(User user) {
        httpHeaders.set("cookie", String.join(";", set_cookie));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL,HttpMethod.PUT, request, String.class);
        result += response.getBody();
    }

    public void deleteUser(Long id) {
        httpHeaders.set("cookie", String.join(";", set_cookie));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<User> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response =
                restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, request, String.class);
        result += response.getBody();
    }

}
