<%-- 
    Document   : search
    Created on : Apr 22, 2019, 10:36:18 AM
    Author     : Thien Phuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <s:head/>
    </head>
    <body>
        <font color="red">
        Welcome, <s:property value="%{#session.USERNAME}"/>
        </font>
        <a href="logout">Log out</a>

        <h1>Search page</h1>

        <s:form action="searchLastname">
            <s:textfield name="searchValue"/>
            <s:submit value="Search"/>
        </s:form>

        <s:if test="%{!searchValue.isEmpty()}">
            <s:if test="%{!list.isEmpty()}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last Name</th>
                            <th>Role</th>
                        </tr>
                    </thead>

                    <tbody>
                        <s:form action="updateUser" theme="simple">
                            <s:iterator value="list" status="counter">
                                <tr>
                                    <td>
                                        <s:property value="%{#counter.count}"/>
                                    </td>

                                    <td>
                                        <s:property value="username"/>
                                        <s:hidden name="username" value="%{username}"/>
                                    </td>

                                    <td>
                                        <s:textfield name="%{username}_password" value="%{password}" theme="css_xhtml"/>
                                    </td>

                                    <td>
                                        <s:textfield name="%{username}_lastname" value="%{lastname}" theme="css_xhtml"/>
                                    </td>

                                    <td>
                                        <s:checkbox name="isAdmin" fieldValue="%{username}"/>
                                    </td>
                                    
                                    <td>
                                        <s:checkbox name="delete" fieldValue="%{username}"/>
                                    </td>
                                </tr>

                            </s:iterator>
                            <tr>
                                <s:hidden name="searchValue" value="%{searchValue}"/>
                                <td colspan="5">
                                    <s:submit value="Update"/>
                                </td>
                                <td>
                                    <s:submit value="Delete" action="deleteUser"/>
                                </td>
                            </tr>
                        </s:form>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                <font color="red">Not found</font>
            </s:else>
        </s:if>

    </body>
</html>
