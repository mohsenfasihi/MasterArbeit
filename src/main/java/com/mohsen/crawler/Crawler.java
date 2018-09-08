package com.mohsen.crawler;

import com.mohsen.model.CaseStudy;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class Crawler {

    abstract public String getUrl(int pageId);

    public void start() throws InterruptedException, IOException {

        DataBaseConnector db = new DataBaseConnector();
        db.connect();

        for (int i = 1; i < 39; i++) {

            Thread.sleep(20000);

            System.out.println(getUrl(i));
            Document doc = Jsoup.connect(getUrl(i)).get();
            Elements links = doc.select("div.summary");
            Elements imports = links.select("a[href]");


            for (Element src : imports) {

                if ((src.attr("abs:href").contains("questions")) && (src.attr("class").contains("question-hyperlink"))) {

                    String url = src.attr("abs:href");
                    Document loadedPage = Jsoup.connect(url).get();

                    Elements codes = this.acceptableCodes(loadedPage.select("code"));

                    if (codes.toArray().length == 0) {
                        System.out.println("has no code");
                        continue;
                    }

                    Element divQuestion = loadedPage.getElementById("question");
                    Elements codesInQuestion = this.acceptableCodes(divQuestion.select("code"));
                    String questioniarName = divQuestion.select("div.user-details").last().select("a[href]").text();


                    // insert case study
                    int caseId = db.insertCaseStudy(url);

                    // insert question
                    Element questionHeader = loadedPage.getElementById("question-header");
                    String questionTitle = questionHeader.select("a.question-hyperlink").text();

                    int questionId = db.insertQuestion(caseId, questionTitle);

                    for (Element code : codesInQuestion) {
                        db.insertCode(questionId, code.text(), questioniarName);
                    }

                    if (codesInQuestion.toArray().length > 0 ) {
                        System.out.println("Lets find comments");
                        Elements comments = divQuestion.select("div.comment-body");
                        for (Element cm : comments) {
                            String author = cm.select("a[href]").text();
                            db.insertComment(questionId,cm.text(),author);
                        }
                    }

                    Elements answers = loadedPage.select("div.answer");

                    for (Element answer : answers) {
                        Elements codesInAnswer = this.acceptableCodes(answer.select("code"));

                        if (codesInAnswer.toArray().length > 0) {

//                            int answerId = db.insertAnswer(caseId);

                            Element userDetails = answer.select("div.user-details").first();
                            String author = userDetails.select("a[href]").text();

                            Element accepted_answer = answer.select("div.vote").first();
                            String spans = accepted_answer.select("span.vote-accepted-on.load-accepted-answer-date").text();

                            String accepted = "non";
                            if(spans.isEmpty()){
                                System.out.println("Not accepted.");
                                accepted = "false";
                            }else {
                                System.out.println("accepted Or Not: " + spans);
                                accepted = "true";
                            }

                            int answerId = db.insertAnswer(caseId,accepted);



                            for (Element c : codesInAnswer) {
                                db.insertCode(answerId,c.text(),author);
                                System.out.println("code in answer:? " + c.text());
                            }

                            Elements comments = answer.select("div.comment-body");
                            for (Element cm : comments) {
                                String cmauthor = cm.select("a[href]").text();
                                db.insertComment(answerId, cm.text(), cmauthor);
                            }
                        }
                    }

                    System.out.println("-------------------");

                    System.out.println(url);
                    System.out.println(questionTitle);
                    System.out.println(questioniarName);

                }
            }
        }
    }

    Elements acceptableCodes(Elements codes) {
        Elements acCodes = new Elements();

        for (Element c : codes) {
            if (c.text().length() > 150 ) {
                acCodes.add(c);
            }
        }
        return  acCodes;
    }
}
