package com.javaliu.platform.threadlocal;

import java.sql.Connection;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ConnectionContextHook {

    private static final ThreadLocal<Connection> CONNECTION_HOOK = new ThreadLocal<Connection>();

    public static void set(Connection connection){
        CONNECTION_HOOK.set(connection);
    }

    public static Connection get(){
        Connection connection = CONNECTION_HOOK.get();
        return connection;
    }

    public static void remove(){
        CONNECTION_HOOK.remove();
    }
}
