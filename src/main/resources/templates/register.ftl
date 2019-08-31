<#assign path="${springMacroRequestContext.getContextPath()}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页</title>
    <link href="${path}/css/login/style.css" rel="stylesheet" type="text/css" />
    <link href="${path}/css/login/fontawesome-all.css" rel="stylesheet" />

    <script src="${path}/js/jquery-3.4.1.min.js" ></script>
</head>
<body>
<h1>注册用户</h1>
<div class="register-form">
    <form  onsubmit="return false" id="register-form">
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
        <div class="form-group">
            <label>验证密码:</label>
            <div class="group">
                <i class="fas fa-unlock"></i>
                <input type="password" class="form-control" name="checkPassorwd" placeholder="请再次输入密码" required="required" />
            </div>
        </div>
        <p id="msg-p" style="color: crimson"></p>
        <button id="submitButton">注册</button>
    </form>
</div>
</body>
<script type="text/javascript">
    $("#submitButton").click(function () {
        var password = $("[name='password']").val();
        var checkPassorwd = $("[name='checkPassorwd']").val();
        if (password == checkPassorwd){
            $.ajax({
                type: "post",
                dataType: "json",
                url: '/register',
                data: $('#register-form').serialize(),
                success: function (data) {
                    if (data.success){
                        window.location.href = "/login";
                    } else {
                        $("#msg-p").html(data.msg);
                    }
                }
            });
        } else {
            $("[name='password']").val("");
            $("[name='checkPassorwd']").val("");
            $("#msg-p").html("两次密码不一致，请重新输入！");
        }

    });
</script>
</html>