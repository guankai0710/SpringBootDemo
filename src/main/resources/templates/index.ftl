<#assign path="${springMacroRequestContext.getContextPath()}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>

    <link type="text/css" href="${path}/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

    <script src="${path}/js/jquery-3.4.1.min.js" ></script>
    <script src="${path}/bootstrap/js/bootstrap.min.js" ></script>
</head>
<style type="text/css">
    body{
        margin: 5px 20px;
    }
    hr{
        margin: 5px auto;
    }
</style>
<body>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="nav nav-pills">
                <li class="active" id="shouye">
                    <a href="#">首页</a>
                </li>
                <li id="jianjie">
                    <a href="#">简介</a>
                </li>
                <li id="xinxi">
                    <a href="#">信息</a>
                </li>
                <li class="dropdown pull-right">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                        <span class="glyphicon glyphicon-user"></span>
                        ${userName}
                        <strong class="caret"></strong>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/person/mydata">我的资料</a>
                        </li>
                        <li>
                            <a href="/logout">退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><hr>

</body>
<script type="text/javascript">
    $("#shouye a").click(function () {
        $("#jianjie").removeClass();
        $("#xinxi").removeClass();
        $("#shouye").addClass("active");
    });

    $("#jianjie a").click(function () {
        $("#shouye").removeClass();
        $("#xinxi").removeClass();
        $("#jianjie").addClass("active");
    });

    $("#xinxi a").click(function () {
        $("#shouye").removeClass();
        $("#jianjie").removeClass();
        $("#xinxi").addClass("active");
    });
</script>
</html>