<%--
  Created by IntelliJ IDEA.
  User: xyhua
  Date: 2019/8/13
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>loginPage</title>
</head>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<body>
<form action="testLogin">
    ID<input type="text" name="userid"><br>
    用户名<input type="text" name="username"><br>
    性别<input type="radio" name="gender" value="0">男 <input type="radio" name="gender" value="1">女<br>
<%--    密码<input type="password" name="password"><br>--%>
    <input type="submit" value="注册">
    <input type="reset" value="重置">
</form>
<script>
$(function () {
    $("input[type = 'submit']").click(function () {
        console.log("点击注册");
        <%--console.log("<%=request.getContextPath()%>");--%>
        var userid = $("input[name = 'userid']").val();
        var username = $("input[name = 'username']").val();
        var gender = $("input[name='gender']:checked").val();
        debugger
        $.ajax({
            url: "<%=request.getContextPath()%>" + "/testLogin",
            type: "POST",
            data: {
                userid : userid,
                username : username,
                gender : gender
            },
            success :function (data) {
                console.log(data);
            },
            error: function (xhr, textStatus, errorThrown){
                console.log(xhr + "---" + textStatus + "---" + errorThrown);
            }
        });
        return false;
    });
});
</script>
</body>
</html>
