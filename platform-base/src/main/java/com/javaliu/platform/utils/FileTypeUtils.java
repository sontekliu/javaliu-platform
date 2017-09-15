package com.javaliu.platform.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型判断，非文件后缀方式
 */
public class FileTypeUtils {

    private static final Map<String, String> FILE_TYPE = new HashMap<>();

    // 默认取用文件头部字节数组的长度
    private static final int DEFAULT_FILE_HEAD_BYTES_LEN = 30;

    static {
        // images
        FILE_TYPE.put("FFD8FF", "jpg");
        FILE_TYPE.put("89504E47", "png");
        FILE_TYPE.put("47494638", "gif");
        FILE_TYPE.put("49492A00", "tif");
        FILE_TYPE.put("424D", "bmp");

        FILE_TYPE.put("41433130", "dwg"); // CAD
        FILE_TYPE.put("38425053", "psd"); //
        FILE_TYPE.put("7B5C727466", "rtf"); // 日记本(Rich Text Format)
        FILE_TYPE.put("3C3F786D6C", "xml");
        FILE_TYPE.put("68746D6C3E", "html");
        FILE_TYPE.put("44656C69766572792D646174653A", "eml"); // 邮件
        FILE_TYPE.put("CFAD12FEC5FD746F", "dbx"); // Outlook Express
        FILE_TYPE.put("2142444E", "pst"); // Outlook
        FILE_TYPE.put("D0CF11E0", "doc");
        FILE_TYPE.put("5374616E64617264204A", "mdb"); // MS Access
        FILE_TYPE.put("FF575043", "wpd"); // WordPerfect
        FILE_TYPE.put("252150532D41646F6265", "ps"); //Postscript
        FILE_TYPE.put("255044462D312E", "pdf");
        FILE_TYPE.put("AC9EBD8F", "qdf"); // Quicken
        FILE_TYPE.put("E3828596", "pwl"); // Windows Password
        FILE_TYPE.put("504B0304", "docx");
        FILE_TYPE.put("504B0304", "zip");
        FILE_TYPE.put("52617221", "rar");
        FILE_TYPE.put("57415645", "wav");
        FILE_TYPE.put("41564920", "avi");
        FILE_TYPE.put("2E524D46", "rm");  // Real Media
        FILE_TYPE.put("2E7261FD", "ram"); // Real Audio
        FILE_TYPE.put("000001BA", "mpg"); // MPEG
        FILE_TYPE.put("000001B3", "mpg"); // MPEG
        FILE_TYPE.put("6D6F6F76", "mov"); // Quicktime
        FILE_TYPE.put("3026B2758E66CF11", "asf"); // Windows Media
        FILE_TYPE.put("4D546864", "mid");  // MIDI
        FILE_TYPE.put("1F8B08", "gz");
    }

    /**
     * 根据文件头查询文件类型
     * @param header
     * @return
     */
    private static String _findFileType(String header){
        String fileType = null;
        for (Map.Entry<String, String> map : FILE_TYPE.entrySet()){
            String key = map.getKey();
            int headerLen = header.length();
            int keyLen = key.length();
            if(headerLen >= keyLen){
                if(header.startsWith(key)){
                    fileType = map.getValue();
                    break;
                }
            }else{
                if(key.startsWith(header)){
                    fileType = map.getValue();
                    break;
                }
            }
        }
        return fileType;
    }

    /**
     * 将字节数组转换成 16 进制字符串
     * @param src
     * @return
     */
    private static final String byteToHexString(byte[] src){
        //return Hex.toHexString(src).toUpperCase();
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取文件类型
     * @param file
     * @return
     */
    public static String getFileType(File file){
        return getFileType(file, DEFAULT_FILE_HEAD_BYTES_LEN);
    }

    /**
     * 获取文件类型
     * @param file          文件
     * @param fileHeadLen   取用文件头部字节数组的长度
     * @return
     */
    public static String getFileType(File file, int fileHeadLen){
        String fileType = null;
        boolean b = file.exists();
        if(!b){
            throw new RuntimeException("文件不存在");
        }
        BufferedInputStream bis = null;
        try {
            byte[] bytes = new byte[fileHeadLen];
            bis = new BufferedInputStream(new FileInputStream(file));
            bis.read(bytes, 0, bytes.length);
            String fileHeader = byteToHexString(bytes);
            fileType = _findFileType(fileHeader);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileType;
    }

    /**
     * 获取该字节数组的文件类型
     * @param bytes         文件字节数组
     * @return
     */
    public static String getFileType(byte[] bytes){
        return getFileType(bytes, DEFAULT_FILE_HEAD_BYTES_LEN);
    }

    /**
     *  根据文件字节数组获取文件类型
     * @param bytes     文件字节数组
     * @param len       取用字节数组的长度
     * @return
     */
    public static String getFileType(byte[] bytes, int len){
        String fileType = null;
        if(bytes.length >= len){
            byte[] fileHeaderByte = new byte[len];
            for (int i = 0;i < len; i++){
                fileHeaderByte[i] = bytes[i];
            }
            String fileHeader = byteToHexString(fileHeaderByte);
            fileType = _findFileType(fileHeader);
        }else {
            String fileHeader = byteToHexString(bytes);
            fileType = _findFileType(fileHeader);
        }
        return fileType;
    }
}
