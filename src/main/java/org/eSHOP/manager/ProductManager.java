package org.eSHOP.manager;

import org.eSHOP.db.DBConnectionProvider;
import org.eSHOP.model.Category;
import org.eSHOP.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();

    public void addProduct(Product product) {
        String sql = "INSERT INTO product (name,description,price,quantity,category) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            System.out.println("Product successfully added");
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        List<Product> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category");
                Category categoryById = categoryManager.getCategoryById(categoryId);
                Product product = new Product(id, name, description, price, quantity, categoryById);
                result.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int qty = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category");
                CategoryManager categoryManager = new CategoryManager();
                Category categoryById = categoryManager.getCategoryById(categoryId);
                Product product = new Product(id, name, description, price, qty, categoryById);
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteProductById(int id) {
        Product productById = getProductById(id);
        if (productById == null) {
            System.out.println("wrong ID please try again");
            return;
        }
        String sql = "DELETE FROM product WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Product was removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void editProductById(Product product) {
        Product productById = getProductById(product.getId());
        if (productById == null) {
            System.out.println("wrong ID please try again");
            return;
        }
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, quantity = ?, category = ? WHERE id=" + product.getId();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int printSumOfProducts() {
        String sql = "SELECT COUNT(id) AS sumProduct FROM (product)";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("sumProduct");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double printMaxOfPriceProduct() {
        String sql = "SELECT MAX(price) AS maxProductPrice FROM (product)";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getDouble("maxProductPrice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public double printMinOfPriceProduct() {
        String sql = "SELECT MIN(price) AS minProductPrice FROM (product)";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getDouble("minProductPrice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double printAvgOfPriceProduct() {
        String sql = "SELECT AVG(price) AS avgProductPrice FROM (product)";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getDouble("avgProductPrice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
