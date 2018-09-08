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
public class CaseStudyEntry {
    int id;
    String type;
    String content;
    List<Comment> comments;
    boolean accepted;
}
