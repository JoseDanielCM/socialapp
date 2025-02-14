package com.socialmedia.socialapp.DbEntity.User.DTO.Img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/validate-image")
    public boolean validateImage(@RequestParam String url) {
        try {
            System.out.println(url);
            if (url.isEmpty() || url.isEmpty() || url.equals("")){
                return false;
            }
            // Send a HEAD request to the image URL
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.HEAD, null, String.class);

            // Check if the content type of the response is an image
            String contentType = response.getHeaders().getContentType().toString();
            return contentType.startsWith("image/");
        } catch (Exception e) {
            // In case of an error (invalid URL or non-image content)
            return false;
        }
    }
}