package com.sei.test;

import com.google.zxing.WriterException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author Sei
 * @Desc
 * @Date 2019/8/11 0:25
 **/
public class TestDemo {
    @Test
    public void test01() throws WriterException, IOException {
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        System.out.println(s);
        int floor = (int) (Math.random() * 100);
        System.out.println(s+floor);
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostAddress);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
        System.out.println(uuid);
    }
}
