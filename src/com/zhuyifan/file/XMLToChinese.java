package com.zhuyifan.file;

import org.apache.commons.lang3.StringEscapeUtils;


import java.io.*;

/**
 * @author 朱一帆
 * @version V1.0
 * @Package com.iflytek.dep.etl.utils
 * @Description:
 * @date 2019/4/12--18:55
 */
public class XMLToChinese {

   // private static final Logger logger = LoggerFactory.getLogger(XMLToChinese.class);

//    public static void main(String[] args) throws Exception {
//        File[] xmls = findXML("D:\\home\\PKG_APP-G1_TO_APP-J1,APP-J2_1555053947869_76487f7cde9c4efa9c103fa3b4bde667");
//        ToChinese(xmls);
//    }

    public static File[] findXML(String path) throws Exception {

        if (path == null) {
           // logger.error("没有传入文件地址");
            return null;
        }

        File file = new File(path);
        File[] xmls = file.listFiles((dir, name) -> name.endsWith("xml"));
        return xmls;
    }

    public static boolean ToChinese(File[] xmls) throws Exception {

        for (File xml : xmls) {
            try {
                FileInputStream in = new FileInputStream(xml);
                //FileOutputStream中的文件不存在，将自动新建文件
                OutputStream out = new FileOutputStream(xml.getAbsoluteFile() + ".temp");
                //用reader来行级读取
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(isr);

                OutputStreamWriter osw = new OutputStreamWriter(out);
                PrintWriter pw = new PrintWriter(osw);
                //byte[] buff = new byte[1024];
                long beginTime = System.currentTimeMillis();
                String b = null;
                while ((b = br.readLine()) != null) {
                    System.out.println("---------------" + b);
                    String escape = StringEscapeUtils.unescapeHtml4(b);
                    System.out.println("**********************" + escape);
                    //out.write(escape.getBytes());
                    pw.println(escape);
                }
                pw.flush();
                pw.close();
                br.close();
                osw.close();
                isr.close();
                //删除掉原来文件并且将修改后文件重命名为源文件
                deleteAndRename(xml);
                //int b;
//                while ((b = in.read(buff)) != -1) {
//                    System.out.println("---------------" + new String(buff));
//                    String escape = StringEscapeUtils.unescapeHtml4(new String(buff));
//                    System.out.println("**********************" + escape);
//                    out.write(escape.getBytes(), 0, b);
//                }
                long endTime = System.currentTimeMillis();
                System.out.println("运行时长为: " + (endTime - beginTime) + "毫秒");
                in.close();
                out.close();
                System.out.println("正常运行！");
            } catch (IOException e) {
                //logger.error("解析文件出问题！入库不会成功！！");
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }

    public static void deleteAndRename(File xml){
        String newName = xml.getAbsolutePath();
        xml.delete();
        File oldFile = new File(newName+".temp");
        oldFile.renameTo(xml);
    }
}
