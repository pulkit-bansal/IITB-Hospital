/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author
 * Pulkit
 */
@WebServlet(name = "cancelAppointment", urlPatterns = {"/cancelAppointment"})
public class cancelAppointment extends HttpServlet {

    /**
     * Processes
     * requests
     * for
     * both
     * HTTP
     * <code>GET</code>
     * and
     * <code>POST</code>
     * methods.
     *
     * @param
     * request
     * servlet
     * request
     * @param
     * response
     * servlet
     * response
     * @throws
     * ServletException
     * if
     * a
     * servlet-specific
     * error
     * occurs
     * @throws
     * IOException
     * if
     * an
     * I/O
     * error
     * occurs
     */
    private static ConfigFetcher fetcher = new ConfigFetcher();
    private static final String DBNAME = fetcher.fetchDBNAME();
    private static final String DB_USERNAME = fetcher.fetchDBUSER();
    private static final String DB_PASSWORD = fetcher.fetchDBPASS();
    private static final String DBSERVER = fetcher.fetchDBSERVER();
    private static final String CANCEL_QUERY="DELETE from appointments where appointment_id=?";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Connection con=null;
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cancelAppointment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cancelAppointment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            
        } finally {            
            out.close();
        }
    }
    
    
    Connection connect() throws Exception
    {
        Connection con=null;
        try
        {
            String url = "jdbc:mysql://"+DBSERVER+"/"+DBNAME+"?user="+DB_USERNAME+"&password="+DB_PASSWORD;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url);
        } 
        catch (SQLException sqle) 
        {
            System.out.println("SQLException: Unable to open connection to db: "+sqle.getMessage());
            throw sqle;
        }
         catch(Exception e)
        {
            System.out.println("Exception: Unable to open connection to db: "+e.getMessage());
            throw e;
        }
        
        return con;
        
        
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles
     * the
     * HTTP
     * <code>GET</code>
     * method.
     *
     * @param
     * request
     * servlet
     * request
     * @param
     * response
     * servlet
     * response
     * @throws
     * ServletException
     * if
     * a
     * servlet-specific
     * error
     * occurs
     * @throws
     * IOException
     * if
     * an
     * I/O
     * error
     * occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
                String username=(String)session.getAttribute("username");
                String password=(String)session.getAttribute("password");
                checkLoginObj clo = new checkLoginObj();
                int isLoggedIn=clo.isLoggedIn(username, password);
                if(isLoggedIn==0)
                {
                    response.sendRedirect("interfaces/index.html");
                }
                else
                {
                    processRequest(request, response);
                }
    }

    /**
     * Handles
     * the
     * HTTP
     * <code>POST</code>
     * method.
     *
     * @param
     * request
     * servlet
     * request
     * @param
     * response
     * servlet
     * response
     * @throws
     * ServletException
     * if
     * a
     * servlet-specific
     * error
     * occurs
     * @throws
     * IOException
     * if
     * an
     * I/O
     * error
     * occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
                String username=(String)session.getAttribute("username");
                String password=(String)session.getAttribute("password");
                checkLoginObj clo = new checkLoginObj();
                int isLoggedIn=clo.isLoggedIn(username, password);
                if(isLoggedIn==0)
                {
                    response.sendRedirect("interfaces/index.html");
                }
                else
                {
                    //processRequest(request, response);
                
         Connection con=null;
        try {
            //processRequest(request, response);
           
            String app_id = request.getParameter("app_id").toString();
            int app = Integer.parseInt(app_id);
            System.out.println("app id is"+app_id);
            con = connect();
                PreparedStatement prepStmt = con.prepareStatement(CANCEL_QUERY);
                prepStmt.setString(1, app_id);
                    int rs = prepStmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(cancelAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(cancelAppointment.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        
        response.sendRedirect("appointments");
        }}
    }

    /**
     * Returns
     * a
     * short
     * description
     * of
     * the
     * servlet.
     *
     * @return
     * a
     * String
     * containing
     * servlet
     * description
     */
   
