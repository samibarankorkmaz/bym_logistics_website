
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "kayit")
@Dependent
public class KayitCek {
    
    String email,name;
    int price;
    
   List<contact> sorguSonucu;
   List<Orders> sorguSonucu2;

    public List<contact> getSorguSonucu(){
        return sorguSonucu;
    }

    public List<Orders> getSorguSonucu2() {
        return sorguSonucu2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public void setSorguSonucu(List<contact> sorguSonucu) {
        this.sorguSonucu = sorguSonucu;
    }
   
   
    
public List<Orders> getTablodakiKayitlar2()  throws ClassNotFoundException, SQLException {
       Connection connection=null;
       PreparedStatement preparedStatement=null;
       ResultSet resultSet=null;
       sorguSonucu2=new ArrayList<>();
       String url = "jdbc:mysql://localhost:3306/lojistik";
		String username = "root";
		String password = "";
       
       try {
           Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection(url,username,password);
           
       
           preparedStatement=connection.prepareStatement("SELECT AVG(order_price),email FROM orders group by(email);");
           
          
           resultSet=preparedStatement.executeQuery();
           while (resultSet.next()) {               
               Orders order=new Orders();
              
               
               
              
               order.setEmail(resultSet.getString("email"));
               order.setPrice(resultSet.getInt("AVG(order_price)"));
               sorguSonucu2.add(order);
        
           }
           
       } catch (Exception e) {
           System.err.println("Hata Meydana Geldi.Hata:"+e);
       }
          
       return sorguSonucu2;
   }

    public List<contact> getTablodakiKayitlar()  throws ClassNotFoundException, SQLException {
       Connection connection=null;
       PreparedStatement preparedStatement=null;
       ResultSet resultSet=null;
       sorguSonucu=new ArrayList<>();
       String url = "jdbc:mysql://localhost:3306/lojistik";
		String username = "root";
		String password = "";
       
       try {
           Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection(url,username,password);
           
         
           preparedStatement=connection.prepareStatement("SELECT * FROM contactmessages order by name;");
        
           resultSet=preparedStatement.executeQuery();
           while (resultSet.next()) {               
             contact  contact =new contact();
               contact.setName(resultSet.getString("name"));
               contact.setEmail(resultSet.getString("email"));
               contact.setKonu(resultSet.getString("konu"));
               contact.setMesaj(resultSet.getString("mesaj"));
               sorguSonucu.add(contact);  
           }
           
       } catch (Exception e) {
           System.err.println("Hata Meydana Geldi.Hata:"+e);
       }
        
       return sorguSonucu;
   }   
}
