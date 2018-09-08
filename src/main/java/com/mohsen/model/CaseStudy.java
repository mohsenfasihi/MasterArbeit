/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohsen.model;

import java.util.List;

/**
 *
 * @author mohsen
 */
public class CaseStudy {
    int id;
    String url;
    CaseStudyEntry question;
    List<CaseStudyEntry> answers;
    String review;
    String Category;
}
