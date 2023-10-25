package com.example.atelier1.dao;

import com.example.atelier1.model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoDB {
    static Connection con;
    static List<Article> listeArticles ;
    static{
        try {
            con = DbConnection.getConnection();
            System.out.println("okey ");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public static List<Article> getAll() {
        List<Article> LA = new ArrayList<>();
        String sql = "select * from article;";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setDesignation(rs.getString("designation"));
//                System.out.println(rs.getInt("id")+" "+rs.getString("designation"));
//                System.out.println(article.getId() +" *** "+ article.getDesignation());
                LA.add(article);
            }
            System.out.println("ca marchegetall");
        } catch(SQLException ex) {
            System.out.println("problemaaa frida");
            ex.printStackTrace();
        }
        return LA;
    }

    public static Article getArticleById(int id) throws SQLException {
        String sql = "select * from Article where id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Article art = new Article();
                    art.setId(rs.getInt(1));
                    art.setDesignation(rs.getString(2));
                    return art;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//    public static Article getArticleById(int id) throws SQLException {
//        ListeArticles = getAll();
//        for(Article art : ListeArticles)
//        {
//            if(art.getId() == id)
//                return art;
//        }
//        return null;
//        String sql = "select * from Article where id = ?";
//        try (PreparedStatement pst = con.prepareStatement(sql)) {
//            pst.setInt(1, id);
//            try (ResultSet rs = pst.executeQuery()) {
//                if (rs.next()) {
//                    Article art = new Article();
//                    art.setId(rs.getInt(1));
//                    art.setDesignation(rs.getString(2));
//                    return art;
//                } else {
//                    return null;
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//
//    }
    public static void initialize(){
        listeArticles = new ArrayList<>();
        listeArticles = getAll();
        for(Article art : listeArticles)
        {
            System.out.println(art.getId() +"wassim"+art.getDesignation());
        }
    }

    public static void addArticle(Article article)
    {
        String sql = "insert into Article (id, designation) values (?, ?)";
        try{
            PreparedStatement Pst = con.prepareStatement(sql);
            Pst.setInt(1,article.getId());
            Pst.setString(2,article.getDesignation());
            Pst.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateArticle(Article article)
    {
        String sql ="update Article set designation = ? where id = ?";
        PreparedStatement pst ;
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, article.getDesignation());
            pst.setInt(2,article.getId());
            pst.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static Article getArticle(int idd) throws SQLException {
        String sql = "select * from Article where id = ?";
        ResultSet rs ;
        PreparedStatement pst= con.prepareStatement(sql);
        pst.setInt(1,idd);
        rs=pst.executeQuery();
        Article art = new Article();
        while(rs.next())
        {
            art.setDesignation(rs.getString(2));
            art.setId(rs.getInt(1));
        }
        return art;
    }
    public static void deleteArticle(int id)
    {
        String sql="delete from Article where id = ?";
        PreparedStatement pst;
        try{
            pst= con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}
