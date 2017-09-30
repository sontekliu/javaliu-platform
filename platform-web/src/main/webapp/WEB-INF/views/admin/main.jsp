<%@ page pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@ include file="/WEB-INF/include/base-style.jsp"%>
    <title>JavaLiu 博客后台</title>
</head>
<body class="layui-layout-body">
<div class="layui-out layui-layout-admin">
   <div class="layui-header">
        <div class="layui-logo">JavaLiu 博客后台管理</div>
   </div>

   <div class="layui-side layui-bg-black">
       <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed"><a href="#">博客管理</a></li>
                <li class="layui-nav-item layui-nav-itemed"><a href="#">菜单管理</a></li>
                <li class="layui-nav-item layui-nav-itemed"><a href="#">用户管理</a></li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="#">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="#">列表一</a></dd>
                        <dd><a href="#">列表二</a></dd>
                        <dd><a href="#">列表三</a></dd>
                        <dd><a href="#">列表四</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="#">云管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="#">列表一</a></dd>
                        <dd><a href="#">列表二</a></dd>
                        <dd><a href="#">列表三</a></dd>
                        <dd><a href="#">列表四</a></dd>
                    </dl>
                </li>
            </ul>
       </div>
   </div>

   <div class="layui-body">
            This is content
   </div>

   <div class="layui-footer">
       <!-- 底部固定区域 -->
       &copy; aaa © layui.com - 底部固定区域
   </div>
</div>
<%@ include file="/WEB-INF/include/base-js.jsp"%>
</body>
</html>