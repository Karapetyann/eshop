package org.eSHOP.manager;

import org.eSHOP.db.DBConnectionProvider;
import org.eSHOP.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addCategory(Category category) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            System.out.println("Category successfully added");
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        List<Category> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                result.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int categoryId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(categoryId, name);
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCategoryById(int id) {
        Category categoryById = getCategoryById(id);
        if (categoryById == null) {
            System.out.println("wrong ID please try again");
            return;
        }
        String sql = "DELETE FROM category WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Category was removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void editCategoryById(Category category) {
        Category categoryById = getCategoryById(category.getId());
        if (categoryById == null) {
            System.out.println("wrong ID please try again");
            return;
        }
        String sql = "UPDATE category SET name = ? WHERE id=" + category.getId();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
