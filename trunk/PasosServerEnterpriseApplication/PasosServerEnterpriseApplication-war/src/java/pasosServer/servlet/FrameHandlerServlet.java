/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.servlet;

import com.frame.JavaBeans.Frame;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pasosServer.ejb.AlarmaFacadeRemote;
import pasosServer.ejb.MaltratadorFacadeRemote;
import pasosServer.ejb.ProtegidoFacadeRemote;
import pasosServer.model.Alarma;
import pasosServer.model.Maltratador;
import pasosServer.model.Protegido;

/**
 *
 * @author Jesus Ruiz Oliva
 */
//@WebServlet(name = "FrameHandlerServlet", urlPatterns = {"/FrameHandlerServlet"})
public class FrameHandlerServlet extends HttpServlet {
    @EJB
    private AlarmaFacadeRemote alarmaFacade;
    @EJB
    private MaltratadorFacadeRemote maltratadorFacade;
    @EJB
    private ProtegidoFacadeRemote protegidoFacade;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Frame frame = new Frame();
        RequestDispatcher requestDispatcher; 
        String trama = request.getParameter("trama");
        if (trama!=null && !trama.isEmpty()){
            String typeFrame = trama.substring(2,4);
            if (typeFrame.equals("ZN") || typeFrame.equals("TE")){
                frame.setType(typeFrame);
                trama = trama.substring(4);
            } else if (typeFrame.equals("AU")){
                frame.setType(trama.substring(2,6));
                trama = trama.substring(6);
            }            
            
            String[] trozos =trama.split("&", 10);
            for (String trozo: trozos){
               if(trozo != null && !trozo.isEmpty()){
                   if (trozo.substring(0, 2).equals("LD")){
                       frame.setLD(trozo.substring(2));
                   }
                   else if (trozo.substring(0, 2).equals("LH")){
                       frame.setLH(trozo.substring(2));
                   }
                   else if (trozo.substring(0, 2).equals("LN")){
                       frame.setLN(trozo.substring(2));
                   }
                   else if (trozo.substring(0, 2).equals("LT")){
                       frame.setLT(trozo.substring(2));
                   }
                   else if (trozo.substring(0, 2).equals("RD")){
                       frame.setRD(trozo.substring(2));
                   }
               }
            }            
            if (frame.getType().equals("TE")){
                //Almacenar en BD                
                Protegido protegido = protegidoFacade.findByimei(frame.getRD());
                protegido.setLatitud(new BigInteger(frame.getLT()));                
                protegido.setLongitud(new BigInteger(frame.getLN()));
                protegidoFacade.updateProtegido(protegido);
            }
            if (frame.getType().equals("ZN") || frame.getType().equals("AU")){
                Maltratador maltratador = maltratadorFacade.findByimei(frame.getRD());
                Calendar calendar = Calendar.getInstance();                
                calendar.setTime(new Date());
                Date date = calendar.getTime();
                Alarma alarma = new Alarma();
                alarma.setFechaHora(date);
                alarma.setIdMaltratador(maltratador);
                alarma.setIdProtegido(maltratador.getIdProtegido());
                if (frame.getType().equals("ZN")){
                    alarma.setTipo(BigInteger.ZERO);
                }else{
                    alarma.setTipo(BigInteger.ONE);
                }
                alarmaFacade.create(alarma);
                BigDecimal idAlarma = alarma.getIdAlarma();
                frame.setID(idAlarma);
                request.setAttribute("trama",frame);
                requestDispatcher = getServletContext().getRequestDispatcher("/chat");
                requestDispatcher.forward(request, response);
            }
        }      
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
