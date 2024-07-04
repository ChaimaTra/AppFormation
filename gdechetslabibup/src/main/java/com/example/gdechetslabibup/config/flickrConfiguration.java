package com.example.gdechetslabibup.config;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.apis.FlickrApi.FlickrPerm;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;


@Configuration
public class flickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;
    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;

  /* @Bean
    public Flickr getFlickr() throws IOException, InterruptedException, ExecutionException, FlickrException {
        try {
            Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
            OAuth10aService service = new ServiceBuilder(apiKey)
            .apiSecret(apiSecret)
            .build(FlickrApi.instance(FlickrPerm.DELETE));

            final Scanner scanner = new Scanner(System.in);
            final OAuth1RequestToken request = service.getRequestToken();
            final String authUrl = service.getAuthorizationUrl(request);

            System.out.println(authUrl);
            System.out.println("Paste the verifier here:");

            final String authVerifier = scanner.nextLine();
            OAuth1AccessToken accessToken = service.getAccessToken(request, authVerifier);

            System.out.println("Access Token: " + accessToken.getToken());
            System.out.println("Token Secret: " + accessToken.getTokenSecret());

            Auth auth = flickr.getAuthInterface().checkToken(accessToken);

            System.out.println("------------ Authentication Details ------------");
            System.out.println("Token: " + auth.getToken());
            System.out.println("Token Secret: " + auth.getTokenSecret());
            System.out.println("User: " + auth.getUser().getUsername());

            return flickr;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    } */ 
    @Bean
    public Flickr getFlickr() {
         Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
         Auth auth= new Auth();
         auth.setPermission(Permission.DELETE);
         auth.setToken(appKey);
         auth.setTokenSecret(appSecret);
         RequestContext requestContext= RequestContext.getRequestContext();
         requestContext.setAuth(auth);
         flickr.setAuth(auth);
         return flickr;
    }

    
}