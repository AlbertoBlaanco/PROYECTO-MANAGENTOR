package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import pojo.Producto;
import pojo.Productos;
import pojo.Fruta;
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/newcss.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <script src=\"js/ServiceFruta.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- FORMULARIO LOGIN-->\n");
      out.write("        <form action=\"Controller\"  method=\"post\" > \n");
      out.write("            <input type=\"hidden\" name=\"ACTION\" value=\"LOGIN\" />\n");
      out.write("            <label for=\"EMAIL\">Email</label>\n");
      out.write("            <input type=\"text\" name=\"EMAIL\" id=\"USER\" />\n");
      out.write("            <br>\n");
      out.write("            <label for=\"NOMBRE\">NOMBRE</label>\n");
      out.write("            <input type=\"text\" name=\"NOMBRE\" id=\"NOMBRE\" />\n");
      out.write("            <br>\n");
      out.write("            <input type=\"submit\" value=\"Enviar\"/>\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("        <a href=\"Controller?ACTION=FIND_ALL_FRUTAS\" class=\"frut\">Lista Frutas</a>\n");
      out.write("        <a href=\"Controller?ACTION=TO_JSON\" class=\"json\">Cambiar Array Frutas a JSON</a>\n");
      out.write("        <a href=\"Controller?ACTION=FIND_ALL_PRODUCTOS\" class=\"prod\">Lista Productos</a>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div style=\"visibility: hidden\" id=\"capa_add\">\n");
      out.write("            <form action=\"Controller\"  method=\"post\" > \n");
      out.write("                <input type=\"hidden\" name=\"ACTION\" value=\"ADD\" />\n");
      out.write("                <label for=\"NOMBRE\">NOMBRE FRUTA</label>\n");
      out.write("                <input type=\"text\" name=\"NOMBRE_FRUTA\" id=\"NOMBRE_FRUTA\" />\n");
      out.write("                <br>\n");
      out.write("                <label for=\"DESC_FRUTA\">DESC_FRUTA</label>\n");
      out.write("                <input type=\"text\" name=\"DESC_FRUTA\" id=\"DESC_FRUTA\" />\n");
      out.write("                <br>\n");
      out.write("\n");
      out.write("                <label for=\"CANTIDAD\">CANTIDAD</label>\n");
      out.write("                <input type=\"text\" name=\"CANTIDAD_FRUTAS\" id=\"CANTIDAD_FRUTAS\" />\n");
      out.write("                <br>\n");
      out.write("                <input type=\"button\" onclick=\"addFruta();\" \n");
      out.write("                       value=\"Enviar Fruta\"/>\n");
      out.write("\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <div style=\"visibility: hidden\" id=\"capa_mod\">\n");
      out.write("            <form action=\"Controller\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"ACTION\" value=\"MODIFICAR_FRUTA\">\n");
      out.write("                <label for=\"NUEVO_N\">NUEVO NOMBRE</label>\n");
      out.write("                <input type=\"text\" name=\"NUEVO_NOMBRE_FRUTA\" id=\"NUEVO_NOMBRE_FRUTA\"> \n");
      out.write("                <br>    \n");
      out.write("                <label for=\"NUEVA_D\">NUEVA DESC FRUTA</label>\n");
      out.write("                <input type=\"text\" name=\"NUEVA_DESC_FRUTA\" id=\"NUEVA_DESC_FRUTA\"> \n");
      out.write("                <br>\n");
      out.write("\n");
      out.write("                <label for=\"POSICION_FRUTA\">FRUTA A CAMBIAR</label>\n");
      out.write("                <input type=\"text\" name=\"POSICION_FRUTA\" id=\"POSICION_FRUTA\">\n");
      out.write("                <br>\n");
      out.write("\n");
      out.write("                <input type =\"button\" onclick=\"modificarCapa()\" value=\"Modificar Fruta\">\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <div style=\"visibility: hidden\" id=\"capa_delete\">\n");
      out.write("            <form action=\"Controller\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"ACTION\" value=\"DELETE_FRUTA\">\n");
      out.write("                <label for=\"N_FRUTA_DELETE\">FRUTA A BORRAR</label>\n");
      out.write("                <input type=\"text\" name=\"DELETE_FRUTA\" id=\"DELETE_FRUTA\"> \n");
      out.write("                <br>    \n");
      out.write("                <input type =\"button\" onclick=\"borrarCapa()\" value=\"Borrar Fruta\">\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            Productos listaProductos = (Productos) request.getAttribute("LISTA_PRODUCTOS");
            if (listaProductos != null && listaProductos.getListaProductos().size() > 0) {
        
      out.write("\n");
      out.write("\n");
      out.write("        <table class =\"tablaProductos\"> \n");
      out.write("            <tr>\n");
      out.write("                <th id =\"n\">NOMBRE</th>\n");
      out.write("                <th id =\"c\">CANTIDAD</th>\n");
      out.write("                <th id =\"d\">DESCRIPCION</th>\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("            ");

                for (Producto producto : listaProductos.getListaProductos()) {
            
      out.write("\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"getters\">");
      out.print(producto.getNombre());
      out.write("</td> \n");
      out.write("                <td class=\"getters\">");
      out.print(producto.getCantidad());
      out.write("</td> \n");
      out.write("                <td class=\"getters\">");
      out.print(producto.getDescripcion());
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("        ");

            //Frutas listaFrutas = (Frutas) request.getAttribute("LISTA_FRUTAS");
            HashMap<Integer, Fruta> mapaFrutas
                    = (HashMap<Integer, Fruta>) request.getAttribute("LISTA");

            //ArrayList<Fruta> lstFrutas
            //      = (ArrayList<Fruta>) request.getAttribute("LISTA");
            Fruta aux;

            // if (lstFrutas != null && lstFrutas.size() > 0) {
            if (mapaFrutas != null && mapaFrutas.size() > 0) {


        
      out.write("\n");
      out.write("\n");
      out.write("        <table class =\"tablaFrutas\">\n");
      out.write("            <tr>\n");
      out.write("                <th class=\"encabezado\" id=\"i\">ID</th>\n");
      out.write("                <th class=\"encabezado\" id=\"n\">NOMBRE</th>\n");
      out.write("                <th class=\"encabezado\" id=\"c\">CANTIDAD</th>\n");
      out.write("                <th class=\"encabezado\" id=\"d\">DESCRIPCION</th>\n");
      out.write("                <th><img src=\"img/add_fruta.png\" alt=\"\" onclick=\"abrirCapa();\"id=\"image\"></th>\n");
      out.write("                <th><img src=\"img/modificar.png\" alt=\"\" onclick=\"abrirCapa2();\" id=\"image\"></th>\n");
      out.write("                <th><img src=\"img/delete_fruta.png\" alt=\"\" onclick=\"abrirCapa3();\" id=\"image\"></th>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            ");
                //for (Fruta fruta : lstFrutas) {
                for (Map.Entry<Integer, Fruta> fruta : mapaFrutas.entrySet()) {
                    int key = fruta.getKey();
                    aux = fruta.getValue();

            
      out.write("\n");
      out.write("            <!--\n");
      out.write("                        <tr>\n");
      out.write("                            <td id =\"getN\">");
//=fruta.getNombre()
      out.write("</td> \n");
      out.write("                            <td id =\"getC\">");
//=fruta.getCantidad()
      out.write("</td> \n");
      out.write("                            <td id =\"getD\">");
//=fruta.getDescripcion()
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("            -->\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"getters\">");
      out.print(key);
      out.write("</td> \n");
      out.write("                <td class=\"getters\">");
      out.print(aux.getNombre());
      out.write("</td> \n");
      out.write("                <td class=\"getters\">");
      out.print(aux.getCantidad());
      out.write("</td> \n");
      out.write("                <td class=\"getters\">");
      out.print(aux.getDescripcion());
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            // ctrl + shift + F5
            ///////////////////////
            // VARIABLES DE SESIÓN O DE ESTADO DE LA WEB
            ///////////////////////
            String login
                    = (String) request.getAttribute("LOGIN");

            if (login
                    != null && !login.equals(
                            "")) {
        
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
      out.write("\n");
      out.write("        ");

            String arrayToJSON
                    = (String) request.getAttribute("LISTA_FRUTAS_TO_JSON");
            if (arrayToJSON
                    != null && !arrayToJSON.equals(
                            "")) {

        
      out.write("\n");
      out.write("\n");
      out.write("        <p>Aqui tienes la lista convertida a JSON</p>\n");
      out.write("        ");
      out.print(arrayToJSON);
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
           }
        
      out.write("\n");
      out.write("        <!---INSERTAR FRUTAS-->\n");
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
