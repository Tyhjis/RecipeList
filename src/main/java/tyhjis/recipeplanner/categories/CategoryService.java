package tyhjis.recipeplanner.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private Connection connection;

    public CategoryService(Connection connection) {
        this.connection = connection;
    }

    public List<Category> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories");
            return createCategoryList(statement.executeQuery());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void insertCategory(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private List<Category> createCategoryList(ResultSet rs) throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        Category category;
        while(rs.next()) {
            category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            categoryList.add(category);
        }
        return categoryList;
    }
}
