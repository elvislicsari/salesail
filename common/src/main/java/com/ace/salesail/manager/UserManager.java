package com.ace.salesail.manager;

import com.ace.salesail.domain.User;
import com.ace.salesail.util.EncryptionUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class UserManager extends GenericManager {

    public User getUser(String email, String password) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("email", email));
        dc.add(Restrictions.eq("password", EncryptionUtil.encrypt(password)));
        return getOneByCriteria(dc);
    }

}