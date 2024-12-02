package  org.bigdata.bigdatabackend.util;

import org.bigdata.bigdatabackend.enums.RoleEnum;
import org.bigdata.bigdatabackend.exception.BigDataException;
import org.bigdata.bigdatabackend.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class SecurityUtil {

    @Autowired
    HttpServletRequest httpServletRequest;

    public User getCurrentUser(){
        return (User)httpServletRequest.getSession().getAttribute("currentUser");
    }




}
