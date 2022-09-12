
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "contact")
@RequestScoped
public class contactsInsert implements Serializable{
    String name,email,konu,mesaj;
    int i;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
   
   

   
    
    public String kayit(){
        
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        
      
        String url = "jdbc:mysql://localhost:3306/lojistik";
        String username = "root";
	String passw = "";
        String sqlSorgu="INSERT INTO contactmessages(name,email,konu,mesaj) VALUES(?,?,?,?)";
        
                
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(url,username,passw);
            preparedStatement=connection.prepareStatement(sqlSorgu);
      
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,konu);
            preparedStatement.setString(4,mesaj);
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
            return "index?faces-redirect=true";
        }else{
            return "contact?faces-redirect=true";
        }
        
        
    }
    
    
}
