package com.javaliu.platform.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装了一些采用HttpClient发送HTTP请求的方法
 * @see
 */
public class HttpClientUtil {

    private HttpClientUtil(){}
     
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    /**
     * 发送Post请求
     * @param url               请求的Url
     * @param paramEntity       参数
     * @param headers           Http头部信息
     * @param config            Http请求配置
     * @param responseHandler   返回结果处理类
     * @return
     */
    public static <T> T sendPost(String url, HttpEntity paramEntity, Header[] headers, RequestConfig config, ResponseHandler<T> responseHandler){
        if(StringUtils.isBlank(url)){
            throw new RuntimeException("调用地址不能为空");
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost();
        T result = null;
        try {
            httpPost.setURI(new URI(url));
            if(null != headers){
                httpPost.setHeaders(headers);
            }
            if(null != config){
                httpPost.setConfig(config);
            }
            if(null != paramEntity) {
                httpPost.setEntity(paramEntity);
            }
            result = httpClient.execute(httpPost, responseHandler);
        } catch (URISyntaxException e) {
            logger.error("Url 格式不正确", url, e);
            throw new RuntimeException("Url 格式不正确", e);
        } catch (ClientProtocolException e) {
            logger.error("Http 调用异常", e);
            throw new RuntimeException("Http 调用异常", e);
        } catch (IOException e) {
            logger.error("Http 调用异常", e);
            throw new RuntimeException("Http 调用异常", e);
        } finally {
            httpPost.releaseConnection();
            HttpClientUtils.closeQuietly(httpClient);
        }
        return result;
    }

    /**
     * 发送Post请求
     * @param url               请求的Url
     * @param paramEntity       参数
     * @param responseHandler   返回结果处理类
     * @return
     */
    public static <T> T sendPost(String url, HttpEntity paramEntity, ResponseHandler<T> responseHandler){
        return sendPost(url, paramEntity, null, null, responseHandler);
    }

    /**
     * HttpPost请求
     * @param url           请求URL
     * @param paramEntity   参数实体
     * @param headers       请求头
     * @param config        配置信息
     * @return
     */
    public static String sendPost(String url, HttpEntity paramEntity, Header[] headers, RequestConfig config){
        return sendPost(url, paramEntity, headers, config, new BasicResponseHandler());
    }

    /**
     * HttpPost请求
     * @param url           请求URL
     * @param paramEntity   参数实体
     * @return
     */
    public static String sendPost(String url, HttpEntity paramEntity){
        return sendPost(url, paramEntity, null, null, new BasicResponseHandler());
    }

    /**
     *  调用Http请求
     * @param url               请求Url
     * @param textParam         请求的基本参数 key - value 形式
     * @param binaryParam       请求的文件参数 key - byte[], key参数名称， byte[]文件内容
     * @param headers           请求头
     * @param config            Http请求配置
     * @param responseHandler   Http请求结果处理类
     * @return
     */
    public static <T> T sendPost(String url, Map<String, String> textParam, Map<String, byte[]> binaryParam,
                                  Header[] headers, RequestConfig config, ResponseHandler<T> responseHandler){
        HttpEntity paramEntity = null;
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if(null != textParam){
            for (Map.Entry<String, String> item : textParam.entrySet()){
                builder.addTextBody(item.getKey(), item.getValue());
            }
        }
        if(null != binaryParam){
            for (Map.Entry<String, byte[]> item : binaryParam.entrySet()){
                builder.addPart(FormBodyPartBuilder.create(item.getKey(), new ByteArrayBody(item.getValue(), ContentType.APPLICATION_OCTET_STREAM, item.getKey())).build());
            }
        }
        paramEntity = builder.build();
        return sendPost(url, paramEntity, headers, config, responseHandler);
    }

    /**
     *  调用Http请求
     * @param url               请求Url
     * @param textParam         请求的基本参数 key - value 形式
     * @param binaryParam       请求的文件参数 key - byte[], key参数名称， byte[]文件内容
     * @param responseHandler   Http请求结果处理类
     * @return
     */
    public static <T> T sendPost(String url, Map<String, String> textParam, Map<String, byte[]> binaryParam, ResponseHandler<T> responseHandler){
        return sendPost(url, textParam, binaryParam, null, null, responseHandler);
    }

    /**
     *  调用Http请求
     * @param url               请求Url
     * @param textParam         请求的基本参数 key - value 形式
     * @param binaryParam       请求的文件参数 key - byte[], key参数名称， byte[]文件内容
     * @param headers           请求头
     * @param config            Http请求配置
     * @return
     */
    public static String sendPost(String url, Map<String, String> textParam, Map<String, byte[]> binaryParam,
                                 Header[] headers, RequestConfig config){
        return sendPost(url, textParam, binaryParam, headers, config, new BasicResponseHandler());
    }

    /**
     *  调用Http请求
     * @param url               请求Url
     * @param textParam         请求的基本参数 key - value 形式
     * @param binaryParam       请求的文件参数 key - byte[], key参数名称， byte[]文件内容
     * @return
     */
    public static String sendPost(String url, Map<String, String> textParam, Map<String, byte[]> binaryParam){
        return sendPost(url, textParam, binaryParam, null, null, new BasicResponseHandler());
    }
}