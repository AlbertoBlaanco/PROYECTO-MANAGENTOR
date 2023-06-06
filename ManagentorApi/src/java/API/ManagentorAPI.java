/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import beans.Alquilados;
import beans.Cita;
import beans.ClienteInteresado;
import beans.FilterModel;
import beans.Imagenes;
import beans.Propiedad;
import beans.Usuario;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.AlquiladosDAO;
import dao.CitaDAO;
import dao.ClienteIntDAO;
import dao.ImagenesDAO;
import dao.PropiedadDAO;
import java.io.StringReader;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.UsuarioDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

/**
 * REST Web Service
 *
 * @author alber
 */
@Path("Controller")
public class ManagentorAPI {

    @Context
    private UriInfo context;

    public ManagentorAPI() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/listadoProp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getListadoProp() {
        //TODO return proper representation object        
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        ArrayList<Propiedad> lstPropiedades = propiedadDAO.findAll(null);
        return Propiedad.fromArrayListToJson(lstPropiedades);
    }

    @GET
    @Path("/getCitas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCitas() {
        //TODO return proper representation object        
        CitaDAO citaDAO = new CitaDAO();
        ArrayList<Cita> lstCitas = citaDAO.findAll(null);
        return Cita.fromArrayListToJson(lstCitas);
    }

    @GET
    @Path("/getImagenes/{idInmueble}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getImagenes(@PathParam("idInmueble") int idInmueble) {
        //TODO return proper representation object        
        ImagenesDAO imagenDAO = new ImagenesDAO();
        Imagenes img = new Imagenes();
        img.setID_INMUEBLE(idInmueble);
        ArrayList<Imagenes> lstImagenes = imagenDAO.findAll(img);
        return Imagenes.fromArrayListToJson(lstImagenes);
    }

    @GET
    @Path("/getProp/{idInmueble}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getProp(@PathParam("idInmueble") int idInmueble) {
        //TODO return proper representation object        
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        Propiedad property = new Propiedad();
        property.setID_PROPIEDAD(idInmueble);
        property = propiedadDAO.findOne(property);
        return Propiedad.fromArrayListToJsonOne(property);
    }

    @GET
    @Path("/getCliInt/{idInmueble}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getClientesInteresados(@PathParam("idInmueble") int idInmueble) {
        //TODO return proper representation object        
        ClienteIntDAO clienteIntDAO = new ClienteIntDAO();
        ClienteInteresado cliInteresado = new ClienteInteresado();
        cliInteresado.setIdInmueble(idInmueble);
        ArrayList<ClienteInteresado> lstClientes = clienteIntDAO.findClientesPropiedad(cliInteresado);
        return ClienteInteresado.fromArrayListToJson(lstClientes);
    }

    @GET
    @Path("/getCliente/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCliente(@PathParam("idCliente") int idCliente) {
        //TODO return proper representation object        
        ClienteIntDAO clienteIntDAO = new ClienteIntDAO();
        ClienteInteresado cliInteresado = new ClienteInteresado();
        cliInteresado.setIdCliente(idCliente);
        return ClienteInteresado.fromArrayListToJsonOne(clienteIntDAO.findOne(cliInteresado));
    }

    @GET
    @Path("/getAlquiladas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAlquiladas() {
        AlquiladosDAO alquiladosDAO = new AlquiladosDAO();

        ArrayList<Alquilados> lstClientes = alquiladosDAO.findAll(null);
        return Alquilados.fromArrayListToJson(lstClientes);

    }

    @GET
    @Path("/getProfile/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getProfile(@PathParam("idUser") int idUser) {
        Usuario user = new Usuario();
        UsuarioDAO userDAO = new UsuarioDAO();
        user.setIdEmple(idUser);
        Usuario userRespuesta = userDAO.findOne(user);
        return Usuario.fromArrayListToJsonOne(userRespuesta);

    }

    @PUT
    @Path("/editPropiedad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editProp(String prop) {
        Gson gson = new Gson();
        Propiedad propiedad = gson.fromJson(prop, Propiedad.class);
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        propiedadDAO.updatePropiedad(propiedad);
        return "finished";
        //AlquiladosDAO alquiladosDAO = new AlquiladosDAO();
        //ArrayList<Alquilados> lstClientes = alquiladosDAO.findAll(null);
        //return Alquilados.fromArrayListToJson(lstClientes);

    }

    @POST
    @Path("/addPropiedad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPropiedad(String prop) {
        Gson gson = new Gson();
        Propiedad propiedad = gson.fromJson(prop, Propiedad.class);
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        propiedadDAO.addPropiedad(propiedad);
        return "finished";
        //AlquiladosDAO alquiladosDAO = new AlquiladosDAO();
        //ArrayList<Alquilados> lstClientes = alquiladosDAO.findAll(null);
        //return Alquilados.fromArrayListToJson(lstClientes);

    }

    @POST
    @Path("/addClientInt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addClientInt(String client) {
        Gson gson = new Gson();
        ClienteInteresado cliente = gson.fromJson(client, ClienteInteresado.class);
        ClienteIntDAO clientIntDAO = new ClienteIntDAO();
        clientIntDAO.addClientInteresado(cliente);
        return "finished";
    }

    @POST
    @Path("/addCita")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCita(String cita) {
        Gson gson = new Gson();
        Cita cit = gson.fromJson(cita, Cita.class);
        CitaDAO citaDAO = new CitaDAO();
        citaDAO.addCita(cit);
        return "finished";
    }

    @POST
    @Path("/addAlquiler")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAlquiler(String alquiler) {
        Gson gson = new Gson();
        Alquilados cit = gson.fromJson(alquiler, Alquilados.class);
        AlquiladosDAO alquiladosDAO = new AlquiladosDAO();
        alquiladosDAO.addAlquiler(cit);
        return "finished";
    }

    @POST
    @Path("/getFilterProp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getFilterProp(String filtro) {
        Gson gson = new Gson();
        FilterModel propFilter = gson.fromJson(filtro, FilterModel.class);
        PropiedadDAO propiedadDAO = new PropiedadDAO();
        ArrayList<Propiedad> lstPropiedades = propiedadDAO.filter(propFilter);
        return Propiedad.fromArrayListToJson(lstPropiedades);
    }

    @POST
    @Path("/uploadImage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadImage(String base64Image) {
        Gson gson = new Gson();
        try {
            // Decodificar la imagen en Base64
            /*for (int i = 0; i < lstbase64Image.size(); i++) {
                
            }*/
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);

            // Crear un directorio para guardar las imágenes
            String imagesDirectory = "E:\\Escritorio\\Insti\\2año\\Insti\\PHP\\www\\PruebaImagenesAndroid";
            //http://192.168.1.36//PruebaImagenesAndroid/image_1684074786042.jpg
            String urlBBDD = "http://192.168.1.42//PruebaImagenesAndroid/";

            File directory = new File(imagesDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Guardar la imagen en el servidor
            String imageName = "image_" + System.currentTimeMillis() + ".jpg";
            File imageFile = new File(Paths.get(imagesDirectory, imageName).toString());
            //String imagePath = Paths.get(imagesDirectory, imageName).toString();
            String imagePath = urlBBDD + imageName;

            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }

            Imagenes img = new Imagenes();
            img.setID_INMUEBLE(1);
            img.setURL(imagePath);
            ImagenesDAO imagenesDAO = new ImagenesDAO();

            imagenesDAO.addImagen(img);

            return imagePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String usuario) {
        Gson gson = new Gson();
        Usuario user = gson.fromJson(usuario, Usuario.class);
        UsuarioDAO usuarioDao = new UsuarioDAO();

        Usuario usuarioRespuesta = new Usuario();
        usuarioRespuesta = usuarioDao.findOne(user);

        if (!usuarioRespuesta.getEmail().equals(user.getEmail()) && !usuarioRespuesta.getPassword().equals(user.getPassword())) {
            return null;
        }

        return Usuario.fromArrayListToJsonOne(usuarioRespuesta);
    }
    
    @POST
    @Path("/insertUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUser(String usuario) {
        Gson gson = new Gson();
        Usuario user = gson.fromJson(usuario, Usuario.class);
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.findOne(user);

        return "Hecho";
    }

    /*
    ////////////COSAS DE LA API DE GLOVO//////////////
    @GET
    @Path("/filtr_usu/{tipo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getFiltroComida(@PathParam("tipo") String tipo) {
        //TODO return proper representation object

        RestauranteDAO restauranteDAO = new RestauranteDAO();
        Restaurante restaurante = new Restaurante();
        restaurante.setNOMBRE("");
        restaurante.setIMAGEN("");
        if (tipo.equals("Tipo")) {
            restaurante.setTIPO("");
        } else {
            restaurante.setTIPO(tipo);
        }

        ArrayList<Restaurante> lstRestaurantes = restauranteDAO.findAllAk(restaurante);
        return Restaurante.fromArrayListToJson(lstRestaurantes);
    }

    @GET
    @Path("/ver_ficha/{idRestaurante}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getFichaRestaurante(@PathParam("idRestaurante") int idRestaurante) {
        //TODO return proper representation object

        RestauranteDAO restauranteDAO = new RestauranteDAO();
        Restaurante restaurante = new Restaurante();
        if (idRestaurante != 0) {
            restaurante.setID_RESTAURANTE(idRestaurante);
        } else {

        }

        Restaurante restauranteRespuesta = restauranteDAO.findOne(restaurante);
        return Restaurante.fromArrayListToJsonOnly(restauranteRespuesta);
    }

    @GET
    @Path("/ver_carta/{idRestaurante}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCartaRestaurante(@PathParam("idRestaurante") int idRestaurante) {
        //TODO return proper representation object

        PlatoDAO platoDAO = new PlatoDAO();
        Plato plato = new Plato();
        if (idRestaurante != 0) {
            plato.setId_restaurante(idRestaurante);
        } else {

        }

        ArrayList<Plato> cartaRespuesta = platoDAO.findRestaurantesVentas(plato);
        return Plato.fromArrayListToJson(cartaRespuesta);
    }

    @GET
    @Path("/comprar/{total}/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String comprarCarro(@PathParam("total") String total, @PathParam("idUser") int id_Usuario) {
        //TODO return proper representation object

        Pedido pedido = new Pedido();
        PedidoDAO pedidoDAO = new PedidoDAO();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        pedido.setId_usuario(id_Usuario);
        pedido.setPrecio_total(total);
        pedido.setFecha(dateFormat.format(date));
        pedidoDAO.add(pedido);
        return "Hecho";
    }

    @GET
    @Path("/register/{nombre}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario register(@PathParam("nombre") String nombre, @PathParam("password") String password) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        ArrayList<Usuario> usuarios = usuarioDao.findAll(null);
        for (Usuario usuarioRespuesta : usuarios) {
            if (usuarioRespuesta.getNombre().equals(nombre) && usuarioRespuesta.getPassword().equals(password)) {
                return null;
            }
        }
        usuarioDao.add(usuario);
        return usuario;
    }

    @GET
    @Path("/login/{nombre}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@PathParam("nombre") String nombre, @PathParam("password") String password) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        ArrayList<Usuario> usuarios = usuarioDao.findOne(usuario);
        for (Usuario usuarioRespuesta : usuarios) {
            if (!usuarioRespuesta.getNombre().equals(nombre) && !usuarioRespuesta.getPassword().equals(password)) {
                return null;
            }
        }

        return Usuario.fromArrayListToJson(usuarios);
    }

    @GET
    @Path("/historialCompras/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getHstCompras(@PathParam("idUser") int idUsu) {
        //TODO return proper representation object

        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = new Pedido();
        pedido.setId_usuario(idUsu);
        pedido.setFecha("");
        pedido.setPrecio_total("");
        ArrayList<Pedido> lstPedidos = pedidoDAO.findAllAk(pedido);
        return Pedido.fromArrayListToJson(lstPedidos);
    }*/
}
