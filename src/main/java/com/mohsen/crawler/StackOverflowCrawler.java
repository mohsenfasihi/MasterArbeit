/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohsen.crawler;

/**
 *
 * @author mohsen
 */
public class StackOverflowCrawler extends Crawler{
    
    @Override
    public String getUrl(int pageId) {

	String url1 = "https://stackoverflow.com/questions/tagged/java+security?page=";
	String url2 = "&sort=newest&pagesize=15";

	String url = url1 + pageId + url2;
	return url;
    }
}
