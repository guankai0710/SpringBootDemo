<#assign path="${springMacroRequestContext.getContextPath()}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页</title>

    <link type="text/css" href="${path}/css/login/style.css" rel="stylesheet" />
    <link type="text/css" href="${path}/css/login/fontawesome-all.css" rel="stylesheet" />

    <script src="${path}/js/jquery-3.4.1.min.js" ></script>
</head>
<body>
<h1>欢迎登录系统</h1>
<div class="login-form">
    <form onsubmit="return false" id="login-form">
        <div class="form-group">
            <label>账号:</label>
            <div class="group">
                <i class="fas fa-user"></i>
                <input type="text" class="form-control" name="account" placeholder="请输入账号" required="required" />
            </div>
        </div>
        <div class="form-group">
            <label>密码:</label>
            <div class="group">
                <i class="fas fa-unlock"></i>
                <input type="password" class="form-control" name="password" placeholder="请输入密码" required="required" />
            </div>
        </div>
        <p id="msg-p" style="color: crimson"></p>
        <button id="submitButton">登录</button>
    </form>
    <p class="register-p">还没有账号？<a href="/register" class="register"> 注册一下</a></p>
</div>
</body>
<script type="text/javascript">
    $("#submitButton").click(function () {
        $.ajax({
            type: "post",
            dataType: "json",
            url: '/login',
            data: $('#login-form').serialize(),
            success: function (data) {
                if (data.success){
                    window.location.href = "/index";
                } else {
                    $("#msg-p").html(data.msg);
                }
            }
        });
    });
</script>
</html>