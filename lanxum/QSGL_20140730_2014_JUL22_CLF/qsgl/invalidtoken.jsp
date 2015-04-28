<%@ page import="com.ttsoft.common.util.ResourceLocator"%>
<%


String url= com.ttsoft.common.util.ResourceLocator.getServerURL(request) + "/invalidToken.jsp";

response.sendRedirect(url);

 %>

