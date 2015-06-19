package dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bean.User;
import dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    public boolean check(User user) {
        if(user!=null){
            String hql="from User where username=? and password=?";
            List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword());
            if(!list.isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
