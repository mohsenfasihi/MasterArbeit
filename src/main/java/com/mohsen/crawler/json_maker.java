package com.mohsen.crawler;
import org.json.JSONObject;
import org.json.JSONArray;
import java.sql.*;

public class json_maker {

    public static void main(String[] args) throws SQLException {

        DataBaseConnector dbc = new DataBaseConnector();
        dbc.connect();
        dbc.write_to_file();
        //JSONArray codes = dbc.codes(ResultSet rs_2);
        //JSONArray comments = dbc.comments(ResultSet rs_3);

        //JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//        //JSONObject item = new JSONObject();
//        int i=0;
//        while (rs.next())
//        {
//            //int id = rs.getInt("id");
//            String url = rs.getString("url");
//            String review = rs.getString("review");
//            String category = rs.getString("category");
//            boolean accepted = rs.getBoolean("accepted");
//            String type = rs.getString("type");
//            String content = rs.getString("content");
//            String codeSnippets = rs.getString("codeSnippets");
//            String author = rs.getString("author");
//
//            JSONObject json2 = new JSONObject();
//
//            json2.put("url",url);
//            json2.put("content",content);
//            json2.put("category",category);
//            json2.put("type",type);
//            json2.put("accepted",accepted);
//            json2.put("codeSnippets",codeSnippets);
//            json2.put("author",author);
//            json2.put("review",review);
//
//            array.put(i,json2);
//            i++;
//
//        }
//
//       System.out.println(array.toString());
    }
}
