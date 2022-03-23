<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%-- 固定的base标签+头部设置--%>
    <%@include file="/pages/commen/head.jsp" %>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
    <script type="text/javascript">
        $(function () { //页面加载完成后
            //给注册绑定单击事件
            $("#sub_btn").click(function () {
                //验证用户名： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
                //(1)获取输入框中的内容
                var nameText = $("#username").val();
                //(2)验证是否合法
                var usernamePatt = /^\w{5,12}$/;
                if (!usernamePatt.test(nameText)) {
                    $("span.errorMsg").text("用户名不合法");
                    //(3)提示用户
                    return false; //只写用户名且不合法时不进行跳转
                }
                //验证密码： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
                //(1)获取输入框中的内容
                var pwdText = $("#password").val();
                //(2)验证是否合法
                var pwdPatt = /^\w{5,12}$/;
                if (!pwdPatt.test(pwdText)) {
                    //(3)提示用户
                    $("span.errorMsg").text("密码不合法");
                    return false; //只写密码且不合法时不进行跳转
                }
                //验证确认密码：和密码相同
                //(1)获取输入框中的内容
                var repwdText = $("#repwd").val();
                //(2)验证是否合法
                if (repwdText != pwdText) {
                    //(3)提示用户
                    $("span.errorMsg").text("确认密码前后不一致");
                    return false; //只写确认密码且不合法时不进行跳转
                }
                //邮箱验证： xxxxx @xxx.com
                //(1)获取输入框中的内容
                var emailText = $("#email").val();
                //(2)验证是否合法
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)) {
                    //(3)提示用户
                    $("span.errorMsg").text("邮箱不合法");
                    return false; //只写邮箱且不合法时不进行跳转
                }
                //验证码： 现在只需要验证用户已输入。 因为还没讲到服务器。 验证码生成。
                //(1)获取输入框中的内容
                var codeText = $("#code").val();
                //去掉前后空格
                codeText = $.trim(codeText);
                //(2)验证是否合法
                if (codeText == null || codeText == "") { //不填验证码或者验证码为空格
                    //(3)提示用户
                    $("span.errorMsg").text("验证码不能为空!");
                    return false; //只写邮箱且不合法时不进行跳转
                }
                $("span.errorMsg").text("");
            });
        });
    </script>
</head>

<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        ${ requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <!--表单提交到registServlet程序中-->
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                                value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                                value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>
                        <img alt="" src="static/img/code.bmp" style="float: right; margin-right: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--页脚--%>
<%@include file="/pages/commen/footer.jsp" %>
</body>
</html>