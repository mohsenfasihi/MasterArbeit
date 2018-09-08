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
public class SecurityStackExchangeCrawler extends Crawler{
    
    @Override
    public String getUrl(int pageId) {

	String url1 = "https://security.stackexchange.com/questions/tagged/java?page=";
	String url2 = "&sort=votes&pagesize=15";

	String url = url1 + pageId + url2;
	return url;
    }
    
}
