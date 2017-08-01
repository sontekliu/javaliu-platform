package com.javaliu.platform.utils;

import com.javaliu.platform.Global;
import com.javaliu.platform.exception.PlatformException;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * 文件压缩的工具类
 */
public class ZipUtils {

    /**
     * 日志记录器
     */
    private static final Logger LOG = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 压缩文件
     * @param files             需要被压缩的文件
     * @param zipFilePath       压缩后的zip文件
     * @param encoding          编码格式，可以为空，若为空，默认为UTF-8
     * @author  javaliu
     */
    public static void compress2Zip(List<File> files, String zipFilePath, String encoding){
        if(null == files || files.size() == 0){
            throw new PlatformException("需要被压缩的文件不能为空");
        }

        if(!_isEndWithZip(zipFilePath)){
            throw new PlatformException("压缩后的文件不是已.zip结尾");
        }
        if(StringUtils.isBlank(encoding) || StringUtils.isEmpty(encoding)) {
            encoding = Global.DEFAULT_ENCODING;
        }
        ZipArchiveOutputStream zaos = null;
        try {
            zaos = new ZipArchiveOutputStream(new File(zipFilePath));
            zaos.setEncoding(encoding);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            for (File sourceFile : files) {
                _zipFile(sourceFile, zaos);
            }
            zaos.finish();
        } catch (IOException e) {
            LOG.error("压缩文件失败", e);
            throw new PlatformException("压缩文件失败", e);
        } catch (PlatformException e){
            throw e;
        } finally {
            IOUtils.closeQuietly(zaos);
        }
    }

    /**
     * 压缩单个文件到输出流
     * @param file  被压缩的文件
     * @param zaos
     * @author  javaliu
     */
    private static void _zipFile(File file, ZipArchiveOutputStream zaos){
        if(null == file || !file.exists()){
            throw new PlatformException("需要被压缩的文件不存在，请检查");
        }
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024 * 100];
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
        try {
            zaos.putArchiveEntry(zipArchiveEntry);
            bis = new BufferedInputStream(new FileInputStream(file));
            int len = -1;
            while((len = bis.read(buffer)) != -1){
                zaos.write(buffer, 0, len);
            }
            zaos.closeArchiveEntry();
            zaos.flush();
        } catch (IOException e) {
            LOG.error("压缩单个文件失败", e);
            throw new PlatformException("压缩单个文件失败" + file.getName(), e);
        }finally {
            IOUtils.closeQuietly(bis);
        }
    }

    /**
     * 判断该路径是否是以.zip结尾
     * @param zipFilePath     zip文件路径
     * @return
     * @author javaliu
     */
    private static boolean _isEndWithZip(String zipFilePath){
        boolean flag = false;
        String zipFileName = StringUtils.blankReturnEmpty(zipFilePath);
        if(zipFileName.endsWith(".zip") || zipFileName.endsWith(".ZIP")){
            flag = true;
        }
        return flag;
    }

    /**
     * 解压文件
     * @param source        待解压的文件
     * @param targetDir     解压后存放的目录
     * @author  javaliu
     */
    public static void decompress(String source, String targetDir){
        if(StringUtils.isBlank(source)){
            throw new PlatformException("要解压缩的文件为空");
        }
        File zipFile = new File(source);
        if(!zipFile.exists()){
            throw new PlatformException("待加压缩的文件不存在");
        }
        decompress(zipFile, targetDir);
    }

    /**
     * 解压文件
     * @param zipFile       待解压的文件
     * @param targetDir     解压后存放的目录
     * @author  javaliu
     */
    public static void decompress(File zipFile, String targetDir){
        if(null == zipFile){
            throw new PlatformException("要解压缩的文件为空");
        }
        int index = zipFile.getName().lastIndexOf(".");
        if(!"zip".equalsIgnoreCase(zipFile.getName().substring(index + 1))){
            throw new PlatformException("要加压的文件不是zip文件，请确认");
        }
        if(StringUtils.isBlank(targetDir)){
            throw new PlatformException("加压后文件存放目录为空");
        }
        File file = new File(targetDir);
        if(!file.exists()){
            file.mkdirs();
        }
        _unZip(zipFile, targetDir);
    }

    /**
     * 解压文件
     * @param zipFile       待解压的文件
     * @param targetDir     解压后存放的目录
     * @author  javaliu
     */
    private static void _unZip(File zipFile, String targetDir){
        ZipArchiveInputStream zais = null;
        OutputStream os = null;
        try {
            zais = new ZipArchiveInputStream(new FileInputStream(zipFile));
            ZipArchiveEntry zipArchiveEntry = null;
            while (null != (zipArchiveEntry = zais.getNextZipEntry())){
                if(zipArchiveEntry.isDirectory()){
                    File desDir = new File(targetDir, zipArchiveEntry.getName());
                    desDir.mkdirs();
                }else{
                    os = new BufferedOutputStream(new FileOutputStream(new File(targetDir, zipArchiveEntry.getName())));
                    IOUtils.copy(zais, os);
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("文件不存在", e);
            throw new PlatformException("文件不存在");
        } catch (IOException e) {
            LOG.error("读取文件异常", e);
            throw new PlatformException("读取文件异常");
        }finally {
            IOUtils.closeQuietly(zais);
            IOUtils.closeQuietly(os);
        }
    }

    private static void unZip(byte[] bytes){
        ZipArchiveInputStream zais = null;
        OutputStream os = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            zais = new ZipArchiveInputStream(bis);
            ZipArchiveEntry zipArchiveEntry = null;
            int index = 0;
            while (null != (zipArchiveEntry = zais.getNextZipEntry())){
                if(zipArchiveEntry.isDirectory()){
                    continue;
                }
                index++;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                IOUtils.copy(zais, bos);
                os = new BufferedOutputStream(new FileOutputStream(new File("d:/datadir/", index + ".jpg")));
                IOUtils.write(bos.toByteArray(), os);
                System.out.println(bos.toByteArray().length);
            }
        } catch (FileNotFoundException e) {
            LOG.error("文件不存在", e);
            throw new PlatformException("文件不存在");
        } catch (IOException e) {
            LOG.error("读取文件异常", e);
            throw new PlatformException("读取文件异常");
        }finally {
            IOUtils.closeQuietly(zais);
            IOUtils.closeQuietly(os);
        }
    }

}
