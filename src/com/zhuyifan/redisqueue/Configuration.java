package com.zhuyifan.redisqueue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:24:43 
* @Version 1.0
* <p>Description:Configuration.java:</p>
*/
public class Configuration extends Properties {

    private static final long serialVersionUID = -2296275030489943706L;
    private static Configuration instance = null;

    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }


    public String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return (val == null || val.isEmpty()) ? defaultValue : val;
    }

    public String getString(String name, String defaultValue) {
        return this.getProperty(name, defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Integer.parseInt(val);
    }

    public long getLong(String name, long defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Integer.parseInt(val);
    }

    public float getFloat(String name, float defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Float.parseFloat(val);
    }

    public double getDouble(String name, double defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Double.parseDouble(val);
    }

    public byte getByte(String name, byte defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Byte.parseByte(val);
    }

    public Configuration() {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("config.xml");
        try {
            this.loadFromXML(in);
            in.close();
        } catch (IOException ioe) {

        }
    }
}
