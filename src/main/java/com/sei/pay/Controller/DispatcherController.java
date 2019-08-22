package com.sei.pay.Controller;

import com.sei.util.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sei
 * @Desc
 * @Date 2019/8/12 14:26
 **/
@Controller
public class DispatcherController {

    @RequestMapping("/saveOrder")
    public String saveOrder(Model model){

        model.addAttribute("total_fee",1);
        model.addAttribute("body","测试商品");
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        model.addAttribute("out_trade_no",s);
        return "wxpayPage";
    }



}
