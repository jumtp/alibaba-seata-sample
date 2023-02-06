package com.example.filter;


import com.example.vo.LoginUserInfo;

public class AccessContext {
    private static final ThreadLocal<LoginUserInfo> LOGIN_USER_INFO = new ThreadLocal<>();

    public static LoginUserInfo getLoginUserInfo(){
        return LOGIN_USER_INFO.get();
    }

    public static void setLoginUserInfo(LoginUserInfo loginUserInfo){
        LOGIN_USER_INFO.set(loginUserInfo);
    }

    public static void clearLoginUserInfo(){
        LOGIN_USER_INFO.remove();
    }
}
