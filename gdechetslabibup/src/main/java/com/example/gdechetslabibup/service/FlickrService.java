package com.example.gdechetslabibup.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;

@Service
public class FlickrService implements FlickrServiceInterface {
   
    
    public Flickr flickr;

    @Autowired
    public FlickrService(Flickr flickr){
        this.flickr=flickr;
    }

    @Override
    public String savePhoto(InputStream photo,String title) throws FlickrException{
        UploadMetaData uploadMetaData=new UploadMetaData();
        uploadMetaData.setTitle(title);
        String photoId= flickr.getUploader().upload(photo,uploadMetaData);
     return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }

}
