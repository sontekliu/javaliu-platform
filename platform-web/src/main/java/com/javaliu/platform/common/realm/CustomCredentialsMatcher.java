package com.javaliu.platform.common.realm;

import com.javaliu.platform.modules.auth.entity.User;
import com.javaliu.platform.security.Digests;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        char[] password = (char[]) token.getCredentials();
        User user = (User) token.getPrincipal();
        String passwordStr = Digests.password(new String(password), user.getSalt());
        Object obj = getCredentials(info);
        System.out.println(passwordStr);
        System.out.println(obj);
        return equals(passwordStr, obj);
    }
}
