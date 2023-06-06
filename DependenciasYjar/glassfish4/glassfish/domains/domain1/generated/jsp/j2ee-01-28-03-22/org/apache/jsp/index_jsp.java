package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import pojo.Fruta;
import java.util.ArrayList;
import pojo.Frutas;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style link =\"\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- FORMULARIO-->\n");
      out.write("        <form action=\"Controller\" \n");
      out.write("              method=\"post\" > \n");
      out.write("            <input type=\"hidden\" name=\"ACTION\" value=\"LOGIN\" />\n");
      out.write("            <label for=\"EMAIL\">Email</label>\n");
      out.write("            <input type=\"text\" name=\"EMAIL\" id=\"USER\" />\n");
      out.write("            <br>\n");
      out.write("            <label for=\"NOMBRE\">NOMBRE</label>\n");
      out.write("            <input type=\"text\" name=\"NOMBRE\" id=\"NOMBRE\" />\n");
      out.write("            <br>\n");
      out.write("            <input type=\"submit\" value=\"Enviar\"/>\n");
      out.write("        </form>\n");
      out.write("        <a href=\"Controller?ACTION=FIND_ALL_FRUTAS\" >Lista frutas</a>\n");
      out.write("        ");

            // ctrl + shift + F5
            ///////////////////////
            // VARIABLES DE SESIÓN O DE ESTADO DE LA WEB
            ///////////////////////
            String login
                    = (String) request.getAttribute("LOGIN");
            if (login != null && !login.equals("")) {
        
      out.write("\n");
      out.write("        <div>\n");
      out.write("            <p>Login Correcto, bienvenido</p>\n");
      out.write("            <!-- BIENVENIDO SEÑOR ¿?¿?-->\n");
      out.write("            ");

                out.print(login);
                // document.write("");
                // System.out.println("");
            
      out.write("\n");
      out.write("            ");
      out.print(login);
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("        <h1>Hello World!</h1>\n");
      out.write("\n");
      out.write("        ");

            /**
             * ****TABLA DE FRUTAS
             */
            Frutas lstFrutas = (Frutas) request.getAttribute("LISTA");
            if (lstFrutas != null && lstFrutas.getFrutas().size()>0) {
        
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>Nombre</th>\n");
      out.write("                <th>Descripción</th>\n");
      out.write("                <th>Cantidad</th>  \n");
      out.write("            </tr>\n");
      out.write("            ");

                for (Fruta fruta : lstFrutas.getFrutas()) {
            
      out.write("   \n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(fruta.getNombre());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(fruta.getDescripcion());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(fruta.getCantidad());
      out.write("</td>  \n");
      out.write("            </tr>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("        ");

            }
            /**
             * ****FIN TABLA DE FRUTAS
             */


        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
