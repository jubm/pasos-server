/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.ParseException; //agregado para la fecha
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pasosServer.ejb.ContactoFacadeRemote;
import pasosServer.ejb.MaltratadorFacadeRemote;
import pasosServer.ejb.ProtegidoFacadeRemote;
import pasosServer.model.Contacto;
import pasosServer.model.Maltratador;
import pasosServer.model.Protegido;

/**
 *
 * @author Gonzalo
 */
@WebServlet(name = "CreateUserServlet", urlPatterns = {"/CreateUserServlet"})
public class CreateUserServlet extends HttpServlet {
    @EJB
    private ContactoFacadeRemote contactoFacade;
    @EJB
    private MaltratadorFacadeRemote maltratadorFacade;
    @EJB
    private ProtegidoFacadeRemote protegidoFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Integer tipo = Integer.parseInt(request.getParameter("tipo"));
            if (tipo == 1){
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String fecha = request.getParameter("fechanac");
                Integer movil1 = Integer.parseInt(request.getParameter("telefono"));
                BigInteger movil2 = BigInteger.valueOf(movil1);

                String nombrec = request.getParameter("nombrec");
                Integer movilpr = Integer.parseInt(request.getParameter("movil"));
                BigInteger movilse = BigInteger.valueOf(movilpr);
                String email = request.getParameter("email");
                String nombrecs = request.getParameter("nombrecs");
                Integer movila = Integer.parseInt(request.getParameter("movils"));
                BigInteger movilb = BigInteger.valueOf(movila);
                String emails = request.getParameter("emails");

                SimpleDateFormat fechanac = new SimpleDateFormat("dd-MM-yyyy");
                Date d = fechanac.parse(fecha);

                Protegido protegido = new Protegido();
                protegido.setNombre(nombre);
                protegido.setApellidos(apellidos);
                protegido.setFechaNacimiento(d);
                protegido.setTelefonoMovil(movil2);

                Contacto contacto = new Contacto();
                contacto.setNombre(nombrec);
                contacto.setTelefonoContacto(movilse);
                contacto.setEmail(email);

                contacto.setNombre(nombrecs);
                contacto.setTelefonoContacto(movilb);
                contacto.setEmail(emails);


                this.protegidoFacade.create(protegido);
                this.contactoFacade.create(contacto);
            }else{
                
                String nombrea = request.getParameter("nombrea");
                String apellidosa = request.getParameter("apellidosa");
                //String dispositivo = request.getParameter("dispositivo");
                //String distancia = request.getParameter("distancia");
                Integer dispositivop = Integer.parseInt(request.getParameter("dispositivo"));
                BigInteger dispositivopr = BigInteger.valueOf(dispositivop);
                Integer distanciap = Integer.parseInt(request.getParameter("distancia"));
                BigInteger distanciapr = BigInteger.valueOf(distanciap);

                Maltratador maltratador = new Maltratador();
                maltratador.setNombre(nombrea);
                maltratador.setApellidos(apellidosa);
                maltratador.setDispositivo(dispositivopr);
                maltratador.setDistanciaAlejamiento(distanciapr);

                this.maltratadorFacade.create(maltratador);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostrarRespuesta.jsp");
                dispatcher.forward(request, response);
            }
           
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(CreateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(CreateUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
