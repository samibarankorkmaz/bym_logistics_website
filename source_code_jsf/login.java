
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "login")
@SessionScoped
public class login implements Serializable{
    String a="";
    String passw;
    static String email;
    boolean control=false;
    public boolean isLogged=false;
   
    public login() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
    
    
    public String login(){
        Connection connection=null;
       PreparedStatement preparedStatement=null;
       ResultSet resultSet=null;
       
       String url = "jdbc:mysql://localhost:3306/lojistik";
		String username = "root";
		String password = "";
        try {
           Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection(url,username,password);
           preparedStatement=connection.prepareStatement("SELECT mail,password from users where mail=? and password=?");
           preparedStatement.setString(1, email);
           preparedStatement.setString(2, passw);
           resultSet=preparedStatement.executeQuery();
           
           
			
           
            if (resultSet.next()) {
                control=true;
            }
           
       } catch (Exception e) {
           System.err.println("Hata Meydana Geldi.Hata:"+e);
       }
      
        
        if (control) {
            isLogged=true;
            a= "fiyathesapla.xhtml?faces-redirect-true";
        }
       return a; 
    }
    
}
