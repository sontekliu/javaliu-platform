<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>--%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
%>

<c:set var="ctx" value="<%=basePath %>"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="adminPath" value="${ctx}a"/>


