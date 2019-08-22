<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert title here</title>
    <script src="${pageContext.request.contextPath}/plugs/qrious.min.js"></script>
    <script src="${pageContext.request.contextPath}/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>

<span>订单号：${out_trade_no}&nbsp;&nbsp;商品名称：${body}&nbsp;&nbsp;金额：${total_fee}</span><br>
<img id="qrious">

<script>

   $(function () {
       var data ={out_trade_no:${out_trade_no},body:'${body}',total_fee:${total_fee}};
       $.post("${pageContext.request.contextPath}/wxpay",data,function (result) {
           var qr = new QRious({
               element:document.getElementById('qrious'),
               size:250,      level:'H',        value:result.code_url
           });
       })
    });




   /* function orderStatus() {
        $.post("后台查询支付结果url",function (resulet) {
            if (resulet) {
                location.href="支付成功跳转的url";
            }
        });
    }
    setInterval("orderStatus()",3000);//每隔三秒向后台请求一次*/
</script>

</body>
</html>
