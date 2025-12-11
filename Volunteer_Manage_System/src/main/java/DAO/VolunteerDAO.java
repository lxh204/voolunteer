package DAO;

import model.Volunteer;
import util.DBUtil;

import java.sql.*;

public class VolunteerDAO {
    
    // 更新志愿者个人信息
    public boolean updateProfile(Volunteer volunteer) throws SQLException {
        String sql = "UPDATE `volunteer` SET `real_name`=?, `gender`=?, `phone`=?, " +
                     "`address`=?, `experience`=?,`username`=?, `email`=?, `password`=? WHERE `id`=?";
        
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, volunteer.getRealName());
            stmt.setString(2, volunteer.getGender());
            stmt.setString(3, volunteer.getPhone());
            stmt.setString(4, volunteer.getAddress());
            stmt.setString(5, volunteer.getExperience());
            stmt.setString(6, volunteer.getUsername());
            stmt.setString(7, volunteer.getEmail());
            stmt.setString(8, volunteer.getPassword());
            stmt.setInt(9, volunteer.getId());

            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 0) {
                String insertSql = "INSERT INTO volunteer"+
                        "(`id`, `real_name`, `gender`, `phone`, `address`, `experience`,`username`,`email`,`password`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, volunteer.getId());
                    insertStmt.setString(2, volunteer.getRealName());
                    insertStmt.setString(3, volunteer.getGender());
                    insertStmt.setString(4, volunteer.getPhone());
                    insertStmt.setString(5, volunteer.getAddress());
                    stmt.setString(6, volunteer.getUsername());
                    stmt.setString(7, volunteer.getEmail());
                    stmt.setString(8, volunteer.getPassword());
                    insertStmt.setString(9, volunteer.getExperience());

                    return insertStmt.executeUpdate() > 0;
                }
            }
            return updatedRows > 0;
        }
    }
    
    // 根据ID查询志愿者信息
    public Volunteer findById(int id) throws SQLException {
        String sql = "SELECT v.*, u.username, u.email, u.created_at " +
                "FROM volunteer v " +
                "LEFT JOIN users u ON v.id = u.user_id " +
                "WHERE v.id=?";
        
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Volunteer volunteer = new Volunteer();
                volunteer.setId(rs.getInt("id"));
                volunteer.setRealName(rs.getString("real_name"));
                volunteer.setGender(rs.getString("gender"));
                volunteer.setPhone(rs.getString("phone"));
                volunteer.setAddress(rs.getString("address"));
                volunteer.setExperience(rs.getString("experience"));

                volunteer.setUsername(rs.getString("username"));
                volunteer.setEmail(rs.getString("email"));
                volunteer.setCreatedat(rs.getTimestamp("created_at"));
                return volunteer;
            }
        }
        return null;
    }
}
