package com.zhuyifan.util;

import org.springframework.core.env.Environment;

/** 
* @Author zhuyifan
* @Time 2019年6月4日 上午9:55:47 
* @Version 1.0
* <p>Description:FileConfigUtil.java:</p>
*/
public class FileConfigUtil {

    private static Environment environment = SpringUtil.getApplicationContext().getBean(Environment.class);
    //private static Logger logger = Logger.getLogger(FileConfigUtil.class);
    public static final String PACKDIR=getStrValueTrim("pack.dir");//待打包文件存放的目录
    public static final String PACKEDDIR=getStrValueTrim("packed.dir");//打包完压缩包存放目录
    public static final String PUBLICKEY=getStrValueTrim("public.key");//公钥的存放地址
    public static final String PRIVATEKEY=getStrValueTrim("private.key");//密钥的存放地址
    public static final Integer SHUNTSIZE=getIntValueTrim("shunt.size");//分卷大小单位为gb
    public static final boolean ISCENTER=getBooleanValueTrim("is.center");//所在节点是否为中心节点
    public static final String CREATEKEY=getStrValueTrim("create.key");//生成密钥存放目录
    public static final String CURNODEID=getStrValueTrim("node.id");//此节点当前id
    public static final String SERVER_NODE_TYPE_DM=getStrValueTrim("server.node.type.dm");// 逻辑节点服务类型，01、中心，02、分支，03、中继
    public static final String SERVER_NODE_ID=getStrValueTrim("server.node.id");// 逻辑节点id
    public static final Integer FTP_POLLING_INTERVAL=getIntValueTrim("ftp.polling.interval");// 扫描间隔
    public static final String ACKDIR=getStrValueTrim("ack.dir");// ack包存放处


    static String getStrValueTrim(String key) {

        if (null == ConcurrentCache.getFieldValue(key)) {
//            Properties config = new Properties();
            String value = null;
            try {
//                InputStream in = FileConfigUtil.class.getClassLoader().getResourceAsStream(environment.getProperty("used.file.properties"));
//                config.load(in);
                value = environment.getProperty(key);
                ConcurrentCache.setFieldValue(key, value == null ? "" : value.trim());
//                CloseUtil.closeQuietly(in);
            } catch (Exception e) {
                //logger.error("", e);
            }
        }
        return (String)ConcurrentCache.getFieldValue(key) ;
    }

    static Integer getIntValueTrim(String key) {
        return Integer.parseInt(getStrValueTrim(key)) ;
    }
    static boolean getBooleanValueTrim(String key) {
        if("true".equals(getStrValueTrim(key))){
            return true;
        }
        return false;
    }

    private FileConfigUtil(){}
}
