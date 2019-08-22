<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert title here</title>
    <script src="${pageContext.request.contextPath}/jquery/jquery-3.2.1.min.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<form>
    <h2>请选择支付方式</h2>
    <br>
    商家订单号：<input type="text" name="out_trade_no" id="out_trade_no" value="ali"><br>
    订单金额：<input type="text" name="total_amount" id="total_amount" value="ali"><br>
    商品描述：<input type="text" name="subject" id="subject" value="ali"><br>
    <input type="radio" name="pay" id="blue" value="ali">支付宝<br>
    <input type="radio" name="pay" id="green" value="wx">微信<br>
    <button type="button" onclick="toPay()">结算</button>
</form>
<span id="spa"></span>

<script>

    function toPay() {
        var rad = $("input:radio:checked").val();
        var out_trade_no = $("#out_trade_no").val();
        var total_amount = $("#total_amount").val();
        var subject = $("#subject").val();

        if (!rad){
            alert("请选择支付方式")
        }


    }
    function aliAjax() {
        var data={memberId:"1234566",projectId:"1"};
        $.post("${pageContext.request.contextPath}/saveOrder",data,function (result) {
            var out_trade_no=result.out_trade_no;
            var total_amount=result.total_amount;
            var subject=result.subject;
            var body=result.body;
            location.href="${pageContext.request.contextPath}/alipay?out_trade_no="+out_trade_no+"&total_amount="+total_amount+"&subject="+subject+"&body="+body;
        });
    }
    function wxAjax() {
       location.href="${pageContext.request.contextPath}/saveOrder";
    }

</script>
</body>
</html>

