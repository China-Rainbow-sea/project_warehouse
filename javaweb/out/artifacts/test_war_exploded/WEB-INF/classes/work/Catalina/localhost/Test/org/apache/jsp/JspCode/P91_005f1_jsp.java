/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.82
 * Generated at: 2022-11-19 11:54:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.JspCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;

public final class P91_005f1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP 只允许 GET、POST 或 HEAD。Jasper 还允许 OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTM>\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>邮箱格式验证</title>\r\n");
      out.write("  </head>\r\n");
      out.write("  <script language=\"javascript\">\r\n");
      out.write("  	function isEmail() {\r\n");
      out.write("  		 var reg = /^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$/;  // 常规的邮箱正则表达式的格式\r\n");
      out.write("  		 var strEmail = form.email.value;\r\n");
      out.write("  		 // 通过name获取值:  var strEmail = form.email.vaule; 注意表单也需要name值锁定\r\n");
      out.write("  		 // 或者是:if(strEmail.search(/^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$/) != -1)\r\n");
      out.write("  		 if(reg.test(strEmail)) {   // reg.在左边防止 strEmail的空引用报错\r\n");
      out.write("  	  		 alert(\"邮箱格式正确\");\r\n");
      out.write("  	  		 return true;\r\n");
      out.write("  	  	 } else {\r\n");
      out.write("  	  	  	 alert(\"邮箱输入格式错误 !!!\");\r\n");
      out.write("  	  	  	 return false;\r\n");
      out.write("  	  	 }\r\n");
      out.write("  	}\r\n");
      out.write("  	/*\r\n");
      out.write("  	search() 方法用于检索字符串中指定的子字符串，或检索与正则表达式相匹配的子字符串，并返回子串的起始位置\r\n");
      out.write("  	且不区分大小写,如果没有找到任何匹配的子串，则返回 -1.\r\n");
      out.write("  	test() 方法是一个正则表达式方法。\r\n");
      out.write("  	test() 方法用于检测一个字符串是否匹配某个模式，如果字符串中含有匹配的文本，则返回 true，否则返回 false。\r\n");
      out.write("  	*/\r\n");
      out.write("  \r\n");
      out.write("  </script>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("  	<form name=\"form\">\r\n");
      out.write("	  	<table align=\"center\" >\r\n");
      out.write("	  		<tr>\r\n");
      out.write("	  			<th>邮箱:</th>\r\n");
      out.write("	  			<td>\r\n");
      out.write("	  				<input type=\"text\" name=\"email\"/> \r\n");
      out.write("	  				<!--  onblur=isEmail(this.value):onblur 事件会在对象失去焦点时发生。就是移去鼠标\r\n");
      out.write("	  				错误可以提交数据 -->\r\n");
      out.write("	  			</td>\r\n");
      out.write("	  		</tr>\r\n");
      out.write("	  		<tr>\r\n");
      out.write("	  			<td colspan = \"2\" align=\"center\">\r\n");
      out.write("	  				<input type=\"submit\" value=\"提交\" onclick=\"return isEmail()\" />\r\n");
      out.write("	  				<!-- <input type=\"submit\" value=\"提交\" onclick=\"return isEmail()\"/> 或者使用 onclick事件错误将无法提交 -->\r\n");
      out.write("	  			</td>\r\n");
      out.write("	  		</tr>\r\n");
      out.write("	  	</table>\r\n");
      out.write("  	</form>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}