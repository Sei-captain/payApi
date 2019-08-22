package com.sei.pay;

/**
 * @Author Sei
 * @Desc
 * @Date 2019/8/10 20:07
 **/
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id="2016100100640376";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDeIL0gtTiR1SAp2i3FQmI6rL/k3oEa5a+SPu0GHWp1pmxIHtuh77WjIW2/LDMI/Zma9LENq60AaL+2+zXZ7Ma20kk7QDo4aUhsEghU1VO5CfGXp3nHaIDnJHuiH/HJkljN8rArgPnMZQgeEB44+rK6wdEG3N2r4cf3ZLojbcg7A3Z6TfXO+vI2cOoEoUjWXTGWCgojZ/3Ka03SePobyevM90s8t63nwm2yjJDGI+5AxdzySLJErOhHn8xOHEyl3gK1o3gakTosbAuXfb6MPSdGOLZLw7U1EGSXAADz6LeNfugzuntB1T1W7KEUL03O106QdXLk+u96XOV0tXH1L5DAgMBAAECggEACZbElmjkdgyV0TxkmtXh+CaGH7X19FSUjhRsp1oRWu3YD986oXktJiDhr9MGYh1NX3MF0GHcpCxqYwRPZfaeTZso3pSauGGc1bQxzpxxYGurqwBDrBOxOWo2CpvSFgho2d+73gnTJw2qrCOZMDgPa/Vwdjs6VLHAvUY/7M5JSRFTnjmqm08A/cOE/fVtHxDOJpuZM3fEI4wC//oKe2UOnK1mBAP345C71jUUU0VZvLRF3vtYFxdR18SjZOpt2LFO7+WCCWOhcgwsk50KIRWGXVhK/z25X2OQzh7mqeJCtkbxptvY03+P+yfqWSavwcYXdLMF5fgI3FUq8TyNLWHXYQKBgQDKGMI5hvrcwy5HBGwM0t/DYg46+HJi1pIniCRDUPbJMCEBLvjMPgUjgvgwXpC13AVSd+CGAXwFBJUQdGYx2bhas6Y5tviuQDvWzXKBRGcExC4mhQR3FC8e1nSBN+o81ymgUEjHG9lf5ZmL2naElxzPaRM3YuTlSmWNSeKn1X6iPQKBgQCmiWKA8Gispr2Maa4EZEuRt75C7aUB0tHelqtjCC1/Lhh/zJk/RfB6gOSCaNUh8oU8j9+qJTyoO8m+xl0h1L1JaBa7elTx1BNg/re87wFzcU2wkkDW47pVakzIO+MCmQ/JwjKa0ZZQLRzm+zPu6Dbo7YqYyTIxtIgCO0F0wHlqfwKBgGgKhPrutXkvdkSTTzju2smQUnkjbGfbxRRa7yhEpzf98g/U6brj9utIQpNMK72kDo1dL5ZtbzZ6HG+DLRVe25y+PcYanz29zdCQpfFMkKiQgp3L/vd6yaNbjlDzw0AdGM3yZvUyLh9DaV/pUPiDfw58Lqtl2yb7vPoW3nu+2lmVAoGAKkleQsFIwvY/0SSQdDsHWUPfwtzW9kSS18NIV4gfDVkmDWVlUESxEFokK9z0qX8MR9l8gJad9VXhH1j9EvcS7U3J3+brJNCl7w4el5HUu3x8rqYkYqxGhyPLxVg3h1RYRvHRqbAoS8l4ukn04OgD8VdHxw3neemOU4q6TywEaH8CgYAOupfsUphlkq2/LpXwUzxua5wNJ7pxs21kq1lS7O4sYOIvcb2DRgeTtu+kyfDBN3hjcKe/UcbFQIpu7qyqDb/WgJLjajDM1EKPAUZ2jILLSQfKweUAe9rzhnYjfBBHTS8xT0KMz8RWPAOV3Fqz9om+jAYNFvfLyC7gAwBjBPaXNA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqHro6pldpynaP6i1h0/L4y35QDw+86HeYxKuX2AAZW6KyYmdxzFe/n23ww1vy63OGrDr1H9C5uYOhLPitinefBuTIEuxQGCN+hSaTBwPXGkRks7aVi173uOa/Itvh4hcsW0zCtL+FXFzqySj9Dvb0WiOqmwvp8l0xJqp3z7QXMb/WgTi2CQUkEnYyE62kJvAoxYGiXO1cLHf3TNnHJRZ1FueiE0LQkG6luVAot6DmxnJqTHPFfC8zt0hHB2wZS//W+Xr+A+nKJn4TuuIynt/8h7J/dOoUcIvhHFHWmDjRlJQ8rHvRPBA5iF9ml2679yRARFpUAqQ45BVxlMyI2vWawIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/pay/alipayNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/pay/alipayReturn";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关 沙箱环境
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志记录路径
    public static String log_path = "D:\\";

}
