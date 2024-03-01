package db;

import model.Comment;
import model.News;
import model.NewsCategories;
import model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    static {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:2003/postgres", "postgres", "kusi2015");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Users getUser(String email){
        Users user = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? LIMIT 1");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new Users();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getLong("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleID(resultSet.getString("role_id"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static Users getUserID(Long id) {
        Users user = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? LIMIT 1");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new Users();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getLong("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleID(resultSet.getString("role_id"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean addUser(Users user){
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name) " +
                    "VALUES (?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean editUser(Users users){
        int rows =0;

        try{

            PreparedStatement statement = connection.prepareStatement(""+
                    "UPDATE users SET full_name = ?, password = ?"
                    + " WHERE id = ? ");

            statement.setString(1, users.getFullName());
            statement.setString(2, users.getPassword());
            statement.setLong(3, users.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean addNews(News news){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO news (category_id, title, content, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, news.getCategoryID());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean editNews(News news){
        int rows =0;

        try{

            PreparedStatement statement = connection.prepareStatement(""+
                    "UPDATE news SET category_id = ?, title = ?, content = ?"
                    + " WHERE id = ? ");

            statement.setLong(1, news.getCategoryID());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setLong(4, news.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static ArrayList<News> getAllNews(){

        ArrayList<News> news = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM news n "+
                    "INNER JOIN news_categories c on n.category_id = c.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                news.add(new News(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("post_date"),
                        new NewsCategories(
                                resultSet.getLong("category_id"),
                                resultSet.getString("name")
                        ).getId(),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                        )
                );
            }
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(Long id){

        News news = null;

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news n " +
                    "INNER JOIN news_categories c on n.category_id = c.id where n.id = ? LIMIT 1");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                news = new News(resultSet.getLong("id"),
                        resultSet.getTimestamp("post_date"),
                        new NewsCategories(
                                resultSet.getLong("category_id"),
                                resultSet.getString("name")
                        ).getId(),
                        resultSet.getString("title"),
                        resultSet.getString("content"));
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return news;
    }

    public  static ArrayList<NewsCategories> getNewsCategories(){
        ArrayList<NewsCategories> newsCategories = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM news_categories");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                newsCategories.add(new NewsCategories(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }

            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return newsCategories;
    }

    public static NewsCategories getCategoryById(Long id){

        NewsCategories newsCategories = null;

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news_categories WHERE id = ? LIMIT 1");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                newsCategories = new NewsCategories(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return newsCategories;
    }

    public static boolean addComment(Comment comment){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (user_id, news_id, comment, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, comment.getUserID().getId());
            statement.setLong(2, comment.getNewsID().getId());
            statement.setString(3, comment.getComment());

            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows>0;
    }

    public static ArrayList<Comment> getAllComments(Long blogId){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.comment, c.blog_id, u.full_name, u.email, c.post_date " +
                    "FROM comments c " +
                    "INNER JOIN users u ON c.user_id = u.id " +
                    "WHERE c.blog_id = ? " +
                    "ORDER BY c.post_date DESC");

            statement.setLong(1, blogId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                Users user = new Users();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                comment.setUserID(user);
                News news = new News();
                news.setId(resultSet.getLong("news_id"));
                comment.setNewsID(news);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
