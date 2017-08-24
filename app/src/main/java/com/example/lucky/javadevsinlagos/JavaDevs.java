package com.example.lucky.javadevsinlagos;

/**
 * Created by LUCKY on 8/17/2017.
 */

public class JavaDevs {
    private String imageUrlJavaDevs; //variable holds javadevs image object
    private String userJavaDevs; //variable holds javadevs username object
    private String urlJavaDevs; //variable holds javadevs url object

    /* JavaDev class Constructor which serve as our object model*/
    public JavaDevs(String imageUrlJavaDevs, String userJavaDevs, String urlJavaDevs){
        this.imageUrlJavaDevs = imageUrlJavaDevs;
        this.userJavaDevs = userJavaDevs;
        this.urlJavaDevs = urlJavaDevs;
    }
    /* method to return JavaDevs Image*/
    public String getImageUrlJavaDevs(){
        return imageUrlJavaDevs;
    }
    /* method to return JavaDevs username*/
    public String getUserJavaDevs(){
        return userJavaDevs;
    }
    /* method to return JavaDevs URL*/
    public String getUrlJavaDevs(){
        return urlJavaDevs;
    }
}
