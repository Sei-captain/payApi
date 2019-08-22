package com.sei.pay;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sei.util.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sei
 * @Desc
 * @Date 2019/8/11 0:23
 * 后台生成二维码，以流响应出去
 **/
@Controller
public class QRCode {
    @RequestMapping("/generateQRCode")
    public void generateQRCode(HttpServletResponse response, @RequestParam(value = "out_trade_no", required = true) String out_trade_no,
                               @RequestParam(value = "body", required = true) String body,
                               @RequestParam(value = "total_fee", required = true) String total_fee) throws WriterException, IOException {
        Map map = new HashMap();
        map.put("out_trade_no", out_trade_no);
        map.put("body", body);
        map.put("total_fee", total_fee);
        //String result = HttpClientUtil.doPost("http://localhost:8080/pay/wxpay", map);
        String result ="{return_code:'SUCCESS',result_code:'SUCCESS',code_url:'code_url'}";
        JSONObject jsonObject = JSONObject.parseObject(result);
        String return_code = jsonObject.getString("return_code");
        if ("SUCCESS".equals(return_code)) {
            String result_code = jsonObject.getString("result_code");
            if ("SUCCESS".equals(result_code)){
                String code_url = jsonObject.getString("code_url");

                Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
                hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);//设置纠错级别

                //创建一个矩阵对象
                //BitMatrix bitMatrix = new QRCodeWriter().encode(code_url, BarcodeFormat.QR_CODE, 200, 200 , hintMap);
                BitMatrix bitMatrix = new MultiFormatWriter().encode(code_url, BarcodeFormat.QR_CODE, 200, 200,hintMap);
                MatrixToImageWriter.writeToStream(bitMatrix,"png",response.getOutputStream());
            }
        }
    }
}
