/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import phuctt.daos.LoginDAO;

/**
 *
 * @author Thien Phuc
 */
public class LoginAction {
    private String username, password;
    
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    public LoginAction() {
    }
    
    public String login() throws Exception {
        String url = FAIL;
        LoginDAO dao = new LoginDAO();
        
        if (dao.checkLogin(username, password)) {
            url = SUCCESS;
            Map session = ActionContext.getContext().getSession();
            session.put("USERNAME", username);
        }
        return url;
    }
    
    public String logout() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("USERNAME");
        
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
