package com.demo.springbootdemo.test;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议socket传输服务端
 *
 * @author: guan.kai
 * @date: 2019/11/22 15:34
 **/
public class TestTCPIPServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTCPIPServer.class);

    //报文头长度
    private static final int LEN_LENGHT=8;
    //默认编码
    private static final String DEFULT_ENCODING="GBK";


    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6969);

            while (true){
                Socket socket = server.accept();
                byte[] req = readPackage(socket.getInputStream());
                Document doc = DocumentHelper.parseText(new String(new String(req, DEFULT_ENCODING).getBytes(System.getProperty("file.encoding"))));
                String tranCode = doc.getRootElement().selectSingleNode("TranCode").getText();
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), DEFULT_ENCODING));
                    if (StringUtils.isBlank(tranCode)){
                        String resp = "<body_resp><TranCode>9999</TranCode><Msg>无此交易码！</Msg></body_resp>";
                        writer.write(new String(wrapMessagePackage(resp.getBytes()), DEFULT_ENCODING));
                        writer.flush();
                    } else if ("000001".equals(tranCode)){

                    }
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                } finally {
                    if (writer != null){
                        writer.close();
                        socket.close();
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (DocumentException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 从输出流获取报文内容，并去除报文中长度字段，编码默认gbk
     * @param in
     * @return
     */
    private static byte[] readPackage(InputStream in) throws IOException {
        //报文体长度
        byte[] bodyLen = null;
        try {
            bodyLen = getPackageLen(in, 0, LEN_LENGHT);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            throw e;
        }

        String lenStr = "-1";
        try {
            lenStr = new String(bodyLen).trim();
        } catch (NullPointerException e) {
            LOGGER.error(e.getMessage(),e);
            throw new IOException(e.getMessage());
        }

        int  len = -1;
        try {
            len = Integer.parseInt(lenStr);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(),e);
            throw new IOException(e.getMessage());
        }

        //获取报文头和报文体
        byte[] body = null;
        try {
            body = getPackageLen(in, 0, len);
            body = new String(body, DEFULT_ENCODING).getBytes(System.getProperty("file.encoding"));
            LOGGER.info("=======服务端接收报文=======\n{}", new String(body));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

        return body;
    }

    /**
     * 获取报文长度
     * @param in
     * @param off
     * @param byteLen 报文体长度
     * @return
     */
    private static byte[] getPackageLen(InputStream in, int off, int byteLen) throws IOException {
        byte[] buf = new byte[byteLen];
        int readLen = 0;
        while (readLen < byteLen){
            int len = in.read(buf, off + readLen, byteLen - readLen);
            if (len == -1){
                return null;
            }
            readLen += len;
        }
        return buf;
    }

    /**
     * 添加报文长度
     * @param msg
     * @return
     */
    private static byte[] wrapMessagePackage(byte[] msg){
        byte[] retMsg = null;
        try {
            //将使用系统默认字符集编码的数据转换成gbk编码
            msg = new String(msg,System.getProperty("file.encoding")).getBytes(DEFULT_ENCODING);

            retMsg = new byte[msg.length + LEN_LENGHT];
            String lenStr = String.valueOf(msg.length);
            lenStr = "0000000000" + lenStr;
            lenStr = lenStr.substring(lenStr.length() - LEN_LENGHT);
            System.arraycopy(lenStr.getBytes(), 0, retMsg, 0, LEN_LENGHT);
            System.arraycopy(msg, 0, retMsg, LEN_LENGHT, msg.length);
            LOGGER.info("=======服务端返回报文=======\n{}", new String(retMsg, DEFULT_ENCODING));
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return retMsg;
    }

}
