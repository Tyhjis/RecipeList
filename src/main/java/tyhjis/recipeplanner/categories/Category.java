package tyhjis.recipeplanner.categories;

import tyhjis.recipeplanner.common.databaseconnection.ConnectionWrapper;
import tyhjis.recipeplanner.common.DatabaseObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category implements DatabaseObject {

    private int id;

    private String name;

    private static ConnectionWrapper connectionWrapper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return id + " " + name;
    }

    @Override
    public void save() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("INSERT INTO categories (name) VALUES (?)");
            statement.setString(1, this.name);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("DELETE FROM categories WHERE id = ?");
            statement.setInt(1, this.id);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("UPDATE categories SET name = ? WHERE id = ?");
            statement.setString(1, this.name);
            statement.setInt(2, this.id);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Category find(int id) {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("SELECT * FROM categories WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            return category;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Category> selectAll() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("SELECT * FROM categories");
            ResultSet resultSet = statement.executeQuery();
            return buildCategoryList(resultSet);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Category> buildCategoryList(ResultSet rs) throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        while(rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            categoryList.add(c);
        }
        return categoryList;
    }
}
