package com.sei.test;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sei
 * @Desc
 * @Date 2019/8/11 0:25
 **/
public class TestQRCode {
    @Test
    public void generateQRCode() throws WriterException, IOException {
        //用fastjson生成json数据，转成字符串，生成二维码
        // {"国家":"China","省份":"福建"，"景区":{"泉州:"开元寺","厦门":"鼓浪屿"}}
        JSONObject jsonObject01= new JSONObject();
        jsonObject01.put("国家","Chain");
        jsonObject01.put("省份","福建");
        JSONObject jsonObject02 = new JSONObject();
        jsonObject02.put("泉州","开元寺");
        jsonObject02.put("厦门","鼓浪屿");
        jsonObject01.put("景区",jsonObject02);
        String jsonString = jsonObject01.toString();

        Map<EncodeHintType,Object> hintMap = new HashMap<EncodeHintType, Object>();
        hintMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        //创建一个矩阵对象
        BitMatrix bitMatrix = new MultiFormatWriter().encode(jsonString, BarcodeFormat.QR_CODE,200,200);
        String filePath = "F://qrCode.jpg";
        Path path = FileSystems.getDefault().getPath(filePath);
        //将矩阵对象转为图片
        MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
    }
}
