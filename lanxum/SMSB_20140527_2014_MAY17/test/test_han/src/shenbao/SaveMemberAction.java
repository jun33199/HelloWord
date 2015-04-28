package shenbao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class SaveMemberAction extends Action {

    public ActionForward perform(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {

        MemberForm memForm = (MemberForm) actionForm;

        int  new_age = 0;
        Integer old_age = memForm.getAge();

        if (old_age != null) {
            new_age =  old_age.intValue();
            memForm.setAge(new Integer(new_age*2));
        }

        return (new ActionForward(actionMapping.getInput()));
    }
}