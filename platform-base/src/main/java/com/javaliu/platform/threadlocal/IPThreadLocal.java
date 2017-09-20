package com.javaliu.platform.threadlocal;


/**
 *  IP thread local
 */
public class IPThreadLocal {

    private static final ThreadLocal<String> IP_ADDRESS = new ThreadLocal<String>();

    public static void set(String ipAddress){
        IP_ADDRESS.set(ipAddress);
    }

    public static String get(){
        String ipAddress = IP_ADDRESS.get();
        if(null == ipAddress){
            ipAddress = "256.256.256.256";
        }
        return ipAddress;
    }

    public static void remove(){
        IP_ADDRESS.remove();
    }
}
