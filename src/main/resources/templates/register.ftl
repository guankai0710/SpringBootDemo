<#assign path="${springMacroRequestContext.getContextPath()}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页</title>
    <link href="${path}/css/login/style.css" rel="stylesheet" type="text/css" />
    <link href="${path}/css/login/fontawesome-all.css" rel="stylesheet" />
</head>
<body>
<h1>注册用户</h1>
<div class="register-form">
    <form action="${path}/login" method="POST">
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
        <button type="submit">注册</button>
    </form>
</div>
</body>
</html>