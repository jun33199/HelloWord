package shenbao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MemberForm extends ActionForm {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        name = null;
        age = null;
    }
}