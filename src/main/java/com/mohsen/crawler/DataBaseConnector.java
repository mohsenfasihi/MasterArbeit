package com.mohsen.crawler;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnector {

    Connection conn = null;

    public void connect() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/thesis_schema?"
                    + "user=root&password=Jaleh1367&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

            System.out.println("Connection ok...");

            //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    public void insertToDb(String url, String code) {

        try {
            String sql = "INSERT INTO code (url, codes) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, url);
            statement.setString(2, code);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("code inserted:?\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertCaseStudy(String url) {
        int id = -1;
        try {
            String sql = "INSERT INTO case_study (url) VALUES (?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, url);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println("case study inserted:? " + id + " \n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public int insertQuestion(int caseStudyId, String title) {
        int rowsInserted = -1;
        try {
            String sql = "INSERT INTO case_study_entry (case_study_id, content, type) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, caseStudyId);
            statement.setString(2, title);
            statement.setString(3, "question");

            statement.executeUpdate();


            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                rowsInserted = rs.getInt(1);
                System.out.println("question inserted:? " + rowsInserted + " \n");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsInserted;
    }

    public int insertAnswer(int caseStudyId, String accepted) {
        int rowsInserted = -1;
        try {
            String sql = "INSERT INTO case_study_entry (case_study_id,type, accepted) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, caseStudyId);
            statement.setString(2, "answer");
            statement.setString(3, accepted);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                rowsInserted = rs.getInt(1);
                System.out.println("question inserted:? " + rowsInserted + " \n");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsInserted;

    }


    public int insertCode(int caseStudyEntryId, String codeSnippets, String author) {
        int rowsInserted = -1;
        try {
            String sql = "INSERT INTO code (case_study_entry_id, codeSnippets, author) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, caseStudyEntryId);
            statement.setString(2, codeSnippets);
            statement.setString(3, author);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                rowsInserted = rs.getInt(1);
                System.out.println("code inserted:? " + rowsInserted + " \n");
            }


        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsInserted;
    }

    public int insertComment(int caseStudyId, String code_comment, String comment_author) {
        int rowsInserted = -1;
        try {
            String sql = "INSERT INTO comment (code_comment, case_study_entry_id ,comment_author) VALUES (?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, code_comment);
            statement.setInt(2, caseStudyId);
            statement.setString(3, comment_author);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                rowsInserted = rs.getInt(1);
                System.out.println("comment inserted:? " + rowsInserted + " \n");
            }


        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsInserted;
    }

    public ResultSet write_to_file() {

        try {
            //String sql = "SELECT url, category, content, review, codeSnippets, type, author FROM case_study Inner Join case_study_entry ON case_study.id = case_study_entry.case_study_id INNER JOIN code ON case_study_entry.id = code.case_study_entry_id;";

            //String sql = "SELECT thesis_schema.case_study.url, thesis_schema.case_study.category, thesis_schema.case_study_entry.content, thesis_schema.code.author, thesis_schema.code.codeSnippets, thesis_schema.case_study.review, thesis_schema.case_study_entry.type FROM thesis_schema.case_study left JOIN thesis_schema.case_study_entry ON thesis_schema.case_study.id = thesis_schema.case_study_entry.case_study_id left JOIN thesis_schema.code ON thesis_schema.case_study_entry.id = thesis_schema.code.case_study_entry_id ;";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//                    //"left JOIN thesis_schema.comment ON thesis_schema.code.id = thesis_schema.comment.case_study_entry_id;";
                    //thesis_schema.comment.content as comment, thesis_schema.comment.author as comment_author,


            String rs1 = "SELECT * from case_study";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(rs1);

            while(rs.next()){
                PreparedStatement st_1 = conn.prepareStatement("SELECT * from case_study_entry WHERE case_study_id = ?");
                st_1.setInt(1, rs.getInt("id"));
                ResultSet rs_1 = st_1.executeQuery();

                while(rs_1.next()){
                    PreparedStatement st_2 = conn.prepareStatement("SELECT * from code where case_study_entry_id = ?");
                    st_2.setInt(1, rs_1.getInt("id"));
                    ResultSet rs_2 = st_2.executeQuery();

                    PreparedStatement st_3 = conn.prepareStatement("SELECT * from comment where case_study_entry_id = ?");
                    st_3.setInt(1, rs_1.getInt("id"));
                    ResultSet rs_3 = st_3.executeQuery();

                    String url = rs.getString("url");
                    String review = rs.getString("review");
                    String category = rs.getString("category");
                    String type = rs_1.getString("type");
                    String content = rs_1.getString("content");
                    boolean accepted = rs_1.getBoolean("accepted");
                    JSONArray codeSnippets = codes(rs_2);
                    JSONArray code_comments = comments(rs_3);

                    JSONObject json2 = new JSONObject();

                    json2.put("url",url);
                    json2.put("content",content);
                    json2.put("category",category);
                    json2.put("review",review);
                    json2.put("type",type);
                    json2.put("accepted",accepted);
                    json2.put("codes",codeSnippets);
                    json2.put("comments",code_comments);

                    //  PRINT json2
                    System.out.println(json2.toString());

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public JSONArray codes(ResultSet rs_2){

        JSONArray code_collection = new JSONArray();
        try {
            while (rs_2.next()) {
                JSONObject object = new JSONObject();
                object.put("code", rs_2.getString("codeSnippets"));
                object.put("author", rs_2.getString("author"));

                code_collection.put(object);
            }
        }catch (SQLException ex){
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code_collection;
    }

    public JSONArray comments(ResultSet rs_3){

        JSONArray comments_collection = new JSONArray();
        try{
            while(rs_3.next()){
                comments_collection.put(rs_3.getString("code_comment"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(DataBaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comments_collection;
    }
}
