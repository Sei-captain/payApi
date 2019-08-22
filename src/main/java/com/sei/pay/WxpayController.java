package com.sei.pay;

import com.github.wxpay.sdk.WXPayUtil;
import com.sei.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author Sei
 * @Desc
 * @Date 2019/8/11 0:05
 **/
@Controller
public class WxpayController {
    @Value("${appid}")
    String appid;

    @Value("${mch_id}")
    String mch_id;

    @Value("${trade_type}")
    String trade_type;

    @Value("${partnerKey}")
    String partnerKey;

    @Value("${wxpayQuery_url}")
    String wxpayQuery_url;

    @Value("${wxpay_url}")
    String wxpay_url;


    /**
     * 微信支付接口
     */
    @RequestMapping("/wxpay")
    @ResponseBody
    public Object wxpay(HttpServletRequest request,
                        @RequestParam(value = "out_trade_no", required = true) String out_trade_no,
                        @RequestParam(value = "body", required = true) String body,
                        @RequestParam(value = "total_fee", required = true) String total_fee) throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("appid", appid);//公众账号ID
        data.put("mch_id", mch_id);//微信支付分配的商户号
        data.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串，长度要求在32位以内。推荐随机数生成算法
        data.put("body", body);//商品描述
        data.put("out_trade_no", out_trade_no);//商户系统内部订单号，要求32个字符内且在同一个商户号下唯一
        //将元转为分
        BigDecimal bigDecimal = new BigDecimal(total_fee);
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100));
        data.put("total_fee", multiply.toString());//订单总金额，单位为分

        //data.put("spbill_create_ip", request.getRemoteAddr());//终端IP 在开发环境中可以用此获取ip
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        data.put("spbill_create_ip", hostAddress);//终端IP
        data.put("notify_url", "http://localhost:8080/wxpayNotify");//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
        data.put("trade_type", trade_type);//交易类型
        data.put("product_id", out_trade_no);//trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义

        String sign = WXPayUtil.generateSignature(data, partnerKey);
        data.put("sign", sign);//签名

        //将map集合的参数转成xml的请求参数
        String dataXml = WXPayUtil.mapToXml(data);
        //调用微信统一下单API接口
        String responseDataXml = HttpClientUtil.doPostByXml(wxpay_url, dataXml);

        //将相应的xml转成map集合
        Map<String, String> dataMap = WXPayUtil.xmlToMap(responseDataXml);
        //将前端需要参数封装到map中，不要将dataMap的信息全部返回
        Map<String, String> map = new HashMap<>();
        map.put("code_url", dataMap.get("code_url"));//二维码url
        //为避免反复调用支付的接口，此处可以将结果存到redis

        return dataMap;
    }

    /**
     * 微信支付返回参数校验
     */
    @RequestMapping("/wxpayNotify")
    public void wxpayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String len;
        while ((len = bufferedReader.readLine()) != null) {
            sb.append(len);
        }
        bufferedReader.close();
        inputStream.close();

        Map<String, String> callbackMap = WXPayUtil.xmlToMap(sb.toString());
        //判断签名是否正确
        if (WXPayUtil.isSignatureValid(callbackMap, partnerKey)){
            if ("SUCCESS".equals(callbackMap.get("result_code"))){
                //从callbackMap里取出订单号，根据订单号查询到订单，更新订单的支付状态，支付时间，支付人等结合业务
                //TODO
                //业务处理完后响应给微信结果
                response.setContentType("text/xml");
                response.getWriter().println("SUCCESS");
                response.getWriter().flush();
                response.getWriter().close();
                return;
            }
        }
        response.setContentType("text/xml");
        response.getWriter().println("FAIL");
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 微信支付结果查询
     */
    @RequestMapping("/wxpayQuery")
    @ResponseBody
    public Object wxpayQuery(HttpServletRequest request,
                             @RequestParam(value = "out_trade_no", required = true) String out_trade_no) throws Exception {
        Map data = new HashMap();
        data.put("appid", appid);
        data.put("mch_id", mch_id);
        data.put("out_trade_no", out_trade_no);
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        String sign = WXPayUtil.generateSignature(data, partnerKey);
        data.put("sign", sign);

        String dataXml = WXPayUtil.mapToXml(data);
        String responseDataXml = HttpClientUtil.doPostByXml(wxpayQuery_url, dataXml);
        Map<String, String> dataMap = WXPayUtil.xmlToMap(responseDataXml);

        return dataMap;
    }
}
