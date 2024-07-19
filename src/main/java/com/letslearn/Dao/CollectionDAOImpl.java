package com.letslearn.Dao;

import com.letslearn.Interface.CollectionDAO;
import com.letslearn.Modal.Collection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CollectionDAOImpl implements CollectionDAO {
    private Connection connection;

    public CollectionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveCollection(Collection collection) {
        String query = "INSERT INTO collections (date, amount, machine) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, collection.getDate());
            stmt.setDouble(2, collection.getAmount());
            stmt.setString(3, collection.getMachine());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getLastCollectionTotal(String machine) {
        String query = "SELECT amount FROM collections WHERE machine = ? ORDER BY date DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, machine);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getTotalLast30Days(String machine) {
        String query = "SELECT SUM(amount) AS total FROM collections WHERE machine = ? AND date >= ?";
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, machine);
            stmt.setDate(2, Date.valueOf(thirtyDaysAgo));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getTotalLast365Days(String machine) {
        String query = "SELECT SUM(amount) AS total FROM collections WHERE machine = ? AND date >= ?";
        LocalDate threeHundredSixtyFiveDaysAgo = LocalDate.now().minusDays(365);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, machine);
            stmt.setDate(2, Date.valueOf(threeHundredSixtyFiveDaysAgo));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Collection> getCollections(String machine, String startDate, String endDate) {
        List<Collection> collections = new ArrayList<>();
        String query = "SELECT * FROM collections WHERE machine = ? AND date BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, machine);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Collection collection = new Collection();
                collection.setId(rs.getInt("id"));
                collection.setDate(rs.getString("date"));
                collection.setAmount(rs.getDouble("amount"));
                collection.setMachine(rs.getString("machine"));
                collections.add(collection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collections;
    }
}
