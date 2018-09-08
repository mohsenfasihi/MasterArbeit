package com.mohsen.crawler;

import java.io.IOException;


public class m_test {

    public static void main(String[] args) throws IOException, InterruptedException {

	StackOverflowCrawler sof = new StackOverflowCrawler();
	SecurityStackExchangeCrawler sse  = new SecurityStackExchangeCrawler();
	//Crawler cr = new Crawler();
	//sof.start();
	sse.start();
	
    }

}
