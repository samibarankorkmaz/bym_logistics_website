
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "fiyatHesapla")
//@Named(value = "fiyatHesapla")
@Dependent
public class fiyatHesapla {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    PreparedStatement preparedStatement2=null;
    PreparedStatement preparedStatement3=null;
    ResultSet resultSet=null,resultSet2=null,resultSet3=null;
    String serviceType,start,company_name,end;
    int company_id,service_id;
    static int fiyat;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
    
    public void fiyatHesaplaMethod()throws ClassNotFoundException, SQLException {
                String url = "jdbc:mysql://localhost:3306/lojistik";
		String username = "root";
		String password = "";
                
                try {
           Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection(url,username,password);
           
           preparedStatement=connection.prepareStatement("select company_id from companies where company_name=?");
           preparedStatement.setString(1,company_name);
           resultSet=preparedStatement.executeQuery();
           
           while (resultSet.next()) { 
               setCompany_id(resultSet.getInt("company_id"));
               
           }
            System.out.println("company name:"+company_name);
           System.out.println("company id:"+company_id);
          preparedStatement2=connection.prepareStatement("select service_id from services where service_name=?");
           preparedStatement2.setString(1, serviceType);
           resultSet2=preparedStatement2.executeQuery();
           
           while (resultSet2.next()) { 
               setService_id(resultSet2.getInt("service_id"));  
           }
           System.out.println("service id:"+service_id);
           String sql="SELECT price from prices,companies,services Where services.service_id=prices.service_id AND companies.company_id=prices.company_id AND prices.company_id=? AND prices.service_id=? AND start_loc=? AND arrive_loc=?";
          preparedStatement3=connection.prepareStatement(sql);
           preparedStatement3.setInt(1,company_id);
           preparedStatement3.setInt(2,service_id);
           preparedStatement3.setString(3,start);
           preparedStatement3.setString(4,end);
           
           
           resultSet3=preparedStatement3.executeQuery();
           
                    while (resultSet3.next()) {                        
                        fiyat=resultSet3.getInt("price");
                    }
                    System.out.println("Fiyat:"+fiyat);
       } catch (Exception e) {
           System.err.println("Hata Meydana Geldi.Hata:"+e);
       }
                
    }
    public void siparisVer() throws ClassNotFoundException, SQLException{
        String url = "jdbc:mysql://localhost:3306/lojistik";
	String username = "root";
	String password = "";
        String sqlSorgu="INSERT INTO orders(email,order_price) VALUES(?,?)";
        
         try {
           Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection(url,username,password);
           
           preparedStatement=connection.prepareStatement(sqlSorgu);
           preparedStatement.setString(1,login.email);
           preparedStatement.setInt(2, fiyat);
           preparedStatement.executeUpdate();
           
           
            
       } catch (Exception e) {
           System.err.println("Hata Meydana Geldi.Hata:"+e);
       }
    }
    
}
