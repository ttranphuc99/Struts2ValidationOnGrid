/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import phuctt.daos.LoginDAO;
import phuctt.dtos.LoginDTO;

/**
 *
 * @author Thien Phuc
 */
public class UpdateAction extends ActionSupport {

    private List<String> username;
    private List<String> isAdmin;
    private String searchValue;

    private List<LoginDTO> list;
    private List<LoginDTO> listUserError;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public UpdateAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("USERNAME");

        if (username != null) {
            LoginDAO dao = new LoginDAO();
            boolean result = dao.updateUsers(list);

            if (result) {
                url = SUCCESS;
            }
        }
        return url;
    }

    @Override
    public void validate() {
        System.out.println("ahihi" + isAdmin);
        ResourceBundle resource = this.getTexts();

        int index = 0;
        HttpServletRequest request = ServletActionContext.getRequest();

        for (String aUsername : username) {
            String password = request.getParameter(aUsername + "_password");
            String lastname = request.getParameter(aUsername + "_lastname");
            boolean error = false;
            boolean role = false;
            
            if (password.isEmpty()) {
                addFieldError(aUsername + "_password", resource.getString("password.required"));
                error = true;
            } else if (password.length() < 6 || password.length() > 20) {
                addFieldError(aUsername + "_password", resource.getString("password.length"));
                error = true;
            } else {
                final String PASSWORD_FORMAT = "[a-zA-Z0-9]+";

                if (!password.matches(PASSWORD_FORMAT)) {
                    addFieldError(aUsername + "_password", resource.getString("password.characters"));
                    error = true;
                }
            }

            if (lastname.isEmpty()) {
                addFieldError(aUsername + "_lastname", resource.getString("lastname.required"));
                error = true;
            } else if (lastname.length() > 20) {
                addFieldError(aUsername + "_lastname", resource.getString("lastname.length"));
                error = true;
            }
            
            if (aUsername.equals(this.isAdmin.get(index))) {
                role = true;
                index++;
            }
            
            if (error) {
                if (listUserError == null) {
                    listUserError = new ArrayList<>();
                }
                
                listUserError.add(new LoginDTO(aUsername, password, lastname, role));
            } else {
                if (list == null) {
                    list = new ArrayList<>();
                }
                
                list.add(new LoginDTO(aUsername, password, lastname, role));
            }
        }

    }

    public List<String> getIsAdmin() {
        System.out.println("in get");
        return isAdmin;
    }

    public void setIsAdmin(List<String> isAdmin) {
        System.out.println("setter" + isAdmin);
        this.isAdmin = isAdmin;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<LoginDTO> getList() {
        return list;
    }

    public void setList(List<LoginDTO> list) {
        this.list = list;
    }

    public List<LoginDTO> getListUserError() {
        return listUserError;
    }

    public void setListUserError(List<LoginDTO> listUserError) {
        this.listUserError = listUserError;
    }

    

}
