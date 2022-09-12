
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        login session=(login) req.getSession().getAttribute("login");
        String url=req.getRequestURI();
        
        
        if (session==null || !session.isLogged) {
            if (url.indexOf("fiyathesapla.xhtml")>=0) {
                res.sendRedirect( req.getServletContext().getContextPath()+"/login2.xhtml");
            }else{
                chain.doFilter(request, response);
            }
            
        }else{
            if (url.indexOf("signup.xhtml")>=0 || url.indexOf("login2.xhtml")>=0) {
                res.sendRedirect(req.getServletContext().getContextPath()+"/index.xhtml");
            }else{
                chain.doFilter(request, response);
            }
        }
        
    }  
}
