package com.javaliu.platform.common.realm;

import com.javaliu.platform.modules.auth.entity.User;
import com.javaliu.platform.modules.auth.service.IUserService;
import com.javaliu.platform.security.Digests;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthRealm extends AuthorizingRealm{

    @Autowired
    private IUserService userService;
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        char[] password = (char[])token.getCredentials();
        User user = userService.findUserByCode(username);
        return new SimpleAuthenticationInfo(user.getCode(), user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),getName());
    }
}
