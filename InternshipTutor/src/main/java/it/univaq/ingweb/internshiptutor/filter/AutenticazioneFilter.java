package it.univaq.ingweb.internshiptutor.filter;

import it.univaq.ingweb.framework.result.TemplateManagerException;
import it.univaq.ingweb.framework.result.TemplateResult;
import it.univaq.ingweb.framework.security.SecurityLayer;
import it.univaq.ingweb.internshiptutor.controller.InternshipTutorBaseController;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/richiesta_tirocinio") //per richiesta tirocinio
public class AutenticazioneFilter implements Filter {


    public AutenticazioneFilter() {
        // TODO Auto-generated constructor stub
    }


    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        String ref = null;
        HttpServletRequest requestHTTP = (HttpServletRequest)request;
        System.err.println(requestHTTP.getRequestURI());

        HttpServletResponse responseHTTP = (HttpServletResponse)response;

        // pass the request along the filter chain

        HttpSession sessione = requestHTTP.getSession(); //sessione
        if (sessione.getAttribute("id_utente") != null /*||
                requestHTTP.getRequestURI().contains("/logGestore") || requestHTTP.getRequestURI().contains("/regGestore") ||
                requestHTTP.getRequestURI().contains("/LoginServlet") || requestHTTP.getRequestURI().contains("/RegistrazioneServlet") ||
                requestHTTP.getRequestURI().contains("/index")  || requestHTTP.getRequestURI().contains("/css") || requestHTTP.getRequestURI().contains("/img") || requestHTTP.getRequestURI().contains("/lib")*/) {
            chain.doFilter(request, response); //vai tranquillo
        } else {
            System.err.println("ACCESSO NEGATO");
            if (SecurityLayer.checkNumericBool(request.getParameter("n"))) {
                System.out.println("richiesta_tirocinio?n=" + request.getParameter("n"));
                System.out.println("login?referrer=richiesta_tirocinio&referrer_res=" +  request.getParameter("n"));
                responseHTTP.sendRedirect("login?referrer=richiesta_tirocinio&referrer_res=" + "n=" + request.getParameter("n"));
            } else {
                responseHTTP.sendRedirect("login");
            }



        }

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}

