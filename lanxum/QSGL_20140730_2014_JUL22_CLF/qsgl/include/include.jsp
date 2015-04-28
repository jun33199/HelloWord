<%@page import="com.ttsoft.common.util.ResourceLocator"%>

<%
  String static_file = ResourceLocator.getStaticFilePath(request);
%>
<%
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Expires", "0");
%>