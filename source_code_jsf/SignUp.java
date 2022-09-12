
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "signUp")
@SessionScoped
public class SignUp implements Serializable{
    String name,mail,password;
    int i;
   
    public SignUp() {
    
    }
    public SignUp(String userName, String mail, String password) {
        this.name = userName;
        this.mail = mail;
        this.password = password;
    }
    

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String kayit() throws ClassNotFoundException, SQLException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
      
        String url = "jdbc:mysql://localhost:3306/lojistik";
        String username = "root";
	String passw = "";
        String sqlSorgu="INSERT INTO users(name,mail,password) VALUES(?,?,?)";
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(url,username,passw);
            preparedStatement=connection.prepareStatement(sqlSorgu);

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,mail);
            preparedStatement.setString(3,password);
            i=preparedStatement.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
           
        }finally{
            try {
                connection.close();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (i>0) {
            return "login2?faces-redirect=true";
        }else{
            return "index?faces-redirect=true";
        }               
    }  
}
