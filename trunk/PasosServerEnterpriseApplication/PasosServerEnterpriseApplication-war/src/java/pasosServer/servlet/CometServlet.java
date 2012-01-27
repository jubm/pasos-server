/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.servlet;

import com.frame.JavaBeans.Frame;
import com.sun.grizzly.comet.CometContext;
import com.sun.grizzly.comet.CometEngine;
import com.sun.grizzly.comet.CometEvent;
import com.sun.grizzly.comet.CometHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pasosServer.ejb.AlarmaFacadeRemote;
import pasosServer.model.Alarma;

/**
 *
 * @author Jesus Ruiz Oliva
 */
public class CometServlet extends HttpServlet {
    @EJB
    private AlarmaFacadeRemote alarmaFacade;
    private final static String JUNK = "<!-- Comet is a programming technique that enables web " +
            "servers to send data to the client without having any need " +
            "for the client to request it. -->\n";
    private String contextPath;
    private ArrayList<ClientInfo> clientInfos = new ArrayList<ClientInfo>();
    static int firstServlet = -1;
    private boolean isPendingMessage = false;
    private ArrayList<Frame> listAlarmFrame = new ArrayList<Frame>();

    public CometServlet() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        contextPath = config.getServletContext().getContextPath() + "/chat";
        CometEngine cometEngine = CometEngine.getEngine();
        CometContext context = cometEngine.register(contextPath);
        context.setExpirationDelay(-1);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                 
             
        String action = request.getParameter("action");
        Frame trama = (Frame)request.getAttribute("trama");
        CometEngine cometEngine = CometEngine.getEngine();
        CometContext cometContext = cometEngine.getCometContext(contextPath);

        if (action != null) {
            if ("login".equals(action)) {
                String username = request.getParameter("username");
                request.getSession(true).setAttribute("username", username);                
                response.sendRedirect("chat.html");
                return;
            } 
            else if ("openchat".equals(action)) {
                // For IE, Safari and Chrome, we must output some junk to enable
                // streaming
                for (int i = 0; i < 10; i++) {
                    response.getWriter().write(JUNK);
                }
                response.getWriter().flush();
                response.setContentType("text/html");
                String username = (String) request.getSession(true).getAttribute("username");
                response.getWriter().println("<h2>Welcome " + username + " </h2>");

                CometRequestHandler handler = new CometRequestHandler();
                handler.clientIP = request.getRemoteAddr();
                handler.attach(response.getWriter());
                //Guardamos el manejador
                ClientInfo clientInfo= new ClientInfo(Boolean.FALSE, username,(CometHandler) handler);
                clientInfos.add(clientInfo);             
                cometContext.addCometHandler(handler);

                if (isPendingMessage){
                    String codigo = "<script languaje='Javascript'>alarma();</script>";
                    /*ClientInfo firstClientNotBusy = null;
                    for(ClientInfo clientInfo : clientInfos){
                        if (firstClientNotBusy==null && clientInfo.getHandlerState().equals(false)){
                            firstClientNotBusy = clientInfo;
                        }
                    }
                    if (firstClientNotBusy!=null){
                        
                        firstClientNotBusy.setHandlerState(Boolean.TRUE);
                        cometContext.notify(codigo,CometEvent.NOTIFY,firstClientNotBusy.getCometHandler());
                    }*/
                    clientInfo.setHandlerState(Boolean.TRUE);
                    cometContext.notify(codigo,CometEvent.NOTIFY,clientInfo.getCometHandler());
                    listAlarmFrame.remove(0);
                    isPendingMessage = !listAlarmFrame.isEmpty();
                }

                return;
            } else if ("atendido".equals(action)){
                String username = (String) request.getSession(true).getAttribute("username");
                
                for (ClientInfo clientInfo : clientInfos){
                    if (clientInfo.getUsuario().equals(username)){
                        if(isPendingMessage){
                            String codigo = "<script languaje='Javascript'>alarma();</script>";
                            clientInfo.setHandlerState(Boolean.TRUE);
                            cometContext.notify(codigo,CometEvent.NOTIFY,clientInfo.getCometHandler());
                            listAlarmFrame.remove(0);
                            isPendingMessage = !listAlarmFrame.isEmpty();
                        }else{                    
                            clientInfo.setHandlerState(Boolean.FALSE);
                        }
                     }
                }
                /*if (isPendingMessage){
                    String codigo = "<script languaje='Javascript'>alarma();</script>";
                    ClientInfo firstClientNotBusy = null;
                    for(ClientInfo clientInfo : clientInfos){
                        if (firstClientNotBusy==null && clientInfo.getHandlerState().equals(false)){
                            firstClientNotBusy = clientInfo;
                        }
                    }
                    if (firstClientNotBusy!=null){
                        firstClientNotBusy.setHandlerState(Boolean.TRUE);
                        cometContext.notify(codigo,CometEvent.NOTIFY,firstClientNotBusy.getCometHandler());
                    }
                    listAlarmFrame.remove(0);
                    isPendingMessage = !listAlarmFrame.isEmpty();
                }*/
                return;
            } else if ("logout".equals(action)){
                String username = (String) request.getSession(true).getAttribute("username");
                for (ClientInfo clientInfo : clientInfos){                    
                    if (clientInfo.getUsuario().equals(username)){
                        clientInfos.remove(clientInfo);
                    }                
                }
            }

            }
            if (trama!=null){
                String codigo = "<script languaje='Javascript'>alarma();</script>";
                ClientInfo firstClientNotBusy = null;
                for(ClientInfo clientInfo : clientInfos){
                    if (firstClientNotBusy==null && clientInfo.getHandlerState().equals(false)){
                        firstClientNotBusy = clientInfo;
                    }
                }
                if (firstClientNotBusy!=null){
                    firstClientNotBusy.setHandlerState(Boolean.TRUE);
                    cometContext.notify(codigo,CometEvent.NOTIFY,firstClientNotBusy.getCometHandler()); 
                }
                else {
                    isPendingMessage = true;
                    listAlarmFrame.add(trama);
                }
                return; 
            }           
    }

    // --------------------------------------------------------- Async Hook ---/
    public class CometRequestHandler implements CometHandler<PrintWriter> {

        private PrintWriter printWriter;
        public String clientIP;

        @Override
        public void attach(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        @Override
        public void onEvent(CometEvent event) throws IOException {
            try {

                /*if (firstServlet != -1 && this.hashCode() != firstServlet) {
                    event.getCometContext().notify("User " + clientIP + " is getting a new message.<br/>", CometEvent.NOTIFY,
                            firstServlet);
                }*/
                if (event.getType() != CometEvent.READ) {
                    printWriter.println(event.attachment());
                    printWriter.flush();
                }
            } catch (Throwable t) {
            }
        }

        @Override
        public void onInitialize(CometEvent event) throws IOException {
            printWriter.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
            printWriter.println("<html><head><title>Chat</title><script type='text/javascript' src='js/funciones.js'></script></head><body bgcolor=\"#FFFFFF\"><div id='alarma'></div>");
            printWriter.flush();
        }

        @Override
        public void onTerminate(CometEvent event) throws IOException {
            onInterrupt(event);
        }

        @Override
        public void onInterrupt(CometEvent event) throws IOException {

            /*if (this.hashCode() == firstServlet) {
                firstServlet = -1;
            }*/

            printWriter.println("Chat closed<br/>");
            printWriter.println("</body></html>");
            printWriter.flush();
            printWriter.close();
        }
    }
    public class ClientInfo{        
        private Boolean handlerState;
        private String usuario;
        private CometHandler cometHandler;
       
        public ClientInfo(Boolean handlerState,String usuario,CometHandler cometHandler){
            this.handlerState = handlerState;
            this.usuario = usuario;            
            this.cometHandler = cometHandler;
        }
        public Boolean getHandlerState() {
            return handlerState;
        }

        public void setHandlerState(Boolean handlerState) {
            this.handlerState = handlerState;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public CometHandler getCometHandler() {
            return cometHandler;
        }

        public void setCometHandler(CometHandler cometHandler) {
            this.cometHandler = cometHandler;
        }
        
        
    }
}
