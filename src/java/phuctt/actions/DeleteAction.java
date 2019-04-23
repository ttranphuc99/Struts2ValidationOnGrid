/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.actions;

import java.util.List;
import phuctt.daos.LoginDAO;

/**
 *
 * @author Thien Phuc
 */
public class DeleteAction {
    private String searchValue;
    private List<String> delete;
    
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    public DeleteAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        LoginDAO dao = new LoginDAO();
        if (delete != null) {
            boolean result = dao.deleteUsers(delete);
            
            if (result) {
                url = SUCCESS;
            }
        } else {
            url = SUCCESS;
        }
        return url;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<String> getDelete() {
        return delete;
    }

    public void setDelete(List<String> delete) {
        this.delete = delete;
    }
    
}
