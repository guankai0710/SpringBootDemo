<#assign path="${springMacroRequestContext.getContextPath()}">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<ul>
    <!-- 递归  宏定义 -->
    <#macro bpTree children>
      <#if children?? && children?size gt 0>
          <#list children as child>
              <#if child.children?? && child.children?size gt 0>
                    <li class="treeview">
                        <a href="javascript:void(0)">
                            <i class="${(child.mIcon=='')?string('fa fa-share',child.mIcon) }" aria-hidden="true"></i> <span>${child.mCname}</span>
                            <i class="fa fa-angle-left pull-right" aria-hidden="true"></i>
                        </a>
                        <ul class="treeview-menu">
                        <@bpTree children=child.children />
                        </ul>
                    </li>
              <#else>
                    <li><a href="javascript:void(0)" onclick="loadContent('${child.mUrl}')"><i class="${ (child.mIcon=='')?string('fa fa-circle-o',child.mIcon) }" aria-hidden="true"></i> ${child.mCname}</a></li>
              </#if>
          </#list>
      </#if>
    </#macro>
    <!-- 调用宏 生成递归树 -->
    <@bpTree children=treeMenu />
</ul>

</body>
</html>