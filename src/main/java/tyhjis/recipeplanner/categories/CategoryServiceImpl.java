package tyhjis.recipeplanner.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private Connection connection;

    public CategoryServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Category> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories");
            return createCategoryList(statement.executeQuery());
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)");
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM categories WHERE id=?");
            statement.setInt(1, category.getId());
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE categories SET name = ? WHERE id = ?");
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Category result = new Category();
            result.setId(rs.getInt("id"));
            result.setName(rs.getString("name"));
            return result;
        } catch(SQLException e) {
            throw new RuntimeException(e);
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
