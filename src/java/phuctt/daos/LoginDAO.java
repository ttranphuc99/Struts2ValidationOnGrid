/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import phuctt.db.DBConnection;
import phuctt.dtos.LoginDTO;

/**
 *
 * @author Thien Phuc
 */
public class LoginDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private List<LoginDTO> list;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<LoginDTO> getList() {
        return list;
    }

    public void setList(List<LoginDTO> list) {
        this.list = list;
    }

    public void searchListAccount(String searchValue) throws Exception {
        String sql = "SELECT username, password, lastname, isAdmin FROM Login WHERE lastname LIKE ?";
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            rs = ps.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String lastname = rs.getString("lastname");
                boolean isAdmin = rs.getBoolean("isAdmin");

                LoginDTO dto = new LoginDTO(username, password, lastname, isAdmin);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
    }
    
    public boolean updatePassRole(String username, String password, boolean isAdmin) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Login SET password = ?, isAdmin = ? WHERE username = ?";
            
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setBoolean(2, isAdmin);
            ps.setString(3, username);
            
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean checkLogin(String username, String password) throws Exception {
        boolean check = false;
        try {
             String sql = "SELECT * FROM Login WHERE username = ? AND password = ?";
             conn = DBConnection.getConnection();
             ps = conn.prepareStatement(sql);
             ps.setString(1, username);
             ps.setString(2, password);
             
             rs = ps.executeQuery();
             
             if (rs.next()) {
                 check = true;
             }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteUsers(List<String> listUsername) throws Exception {
        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM Login WHERE username = ?";
            ps = conn.prepareStatement(sql);

            for (String username : listUsername) {
                ps.setString(1, username);
                ps.executeUpdate();
            }
            return true;
        } finally {
            closeConnection();
        }
    }
    
    public boolean updateUsers(List<LoginDTO> list) throws Exception{
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE Login SET password = ?, isAdmin = ?, lastname = ? WHERE username = ?";
            ps = conn.prepareStatement(sql);
            
            for (LoginDTO dto : list) {
                ps.setString(1, dto.getPassword());
                ps.setBoolean(2, dto.isIsAdmin());
                ps.setString(3, dto.getLastname());
                ps.setString(4, dto.getUsername());
                ps.setString(1, dto.getPassword());
                
                ps.executeUpdate();
            }
            return true;
        } finally {
            closeConnection();
        }
    }
}
