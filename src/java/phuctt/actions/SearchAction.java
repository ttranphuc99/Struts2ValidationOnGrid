/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import phuctt.daos.LoginDAO;
import phuctt.dtos.LoginDTO;

/**
 *
 * @author Thien Phuc
 */
public class SearchAction {

    private String searchValue;
    private List<LoginDTO> list;
    private List<LoginDTO> listUserError;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public SearchAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String aUsername = (String) session.get("USERNAME");

        if (aUsername != null) {
            int index = 0;
            
            LoginDAO dao = new LoginDAO();
            dao.searchListAccount(searchValue);
            list = dao.getList();
            
            if (listUserError != null) {
                for (int i = 0 ; i < list.size() ; i++) {
                    String username = list.get(i).getUsername();
                    String usernameError = listUserError.get(index).getUsername();
                    
                    if (username.equals(usernameError)) {
                        list.set(i, listUserError.get(index));
                        index++;
                    }
                    
                    if (index == listUserError.size()) {
                        break;
                    }
                }
            }

            return SUCCESS;
        }
        return FAIL;
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
