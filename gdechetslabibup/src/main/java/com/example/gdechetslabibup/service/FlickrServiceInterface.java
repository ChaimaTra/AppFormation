package com.example.gdechetslabibup.service;

import java.io.InputStream;

import com.flickr4java.flickr.FlickrException;

public interface FlickrServiceInterface {

    String savePhoto(InputStream photo,String title) throws FlickrException;
}
