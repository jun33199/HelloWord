package action;

import service.UserService;
import bean.User;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
    private User user;
    private UserService userService;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
   
    public String login(){
        if(this.userService.check(this.user)){
            return SUCCESS;
        }else{
            return INPUT;
        }
    }
}
