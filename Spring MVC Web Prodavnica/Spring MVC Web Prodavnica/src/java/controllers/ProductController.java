/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.validation.Valid;
import model.Product;
import model.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nermin
 */
;
@Controller
public class ProductController {
    
   @Autowired
   private DataSource dataSource;
   
   @Autowired
   ProductDAO productDAO;
   
   @RequestMapping(value = "/", method=RequestMethod.GET)
   public String home(){
       
       return "home";
       
   }
    //otvaramo formu za unos novog proizvoda, prosljeđujemo instancu klase Product, "Praznu"
    @RequestMapping(value = "/new_product", method=RequestMethod.GET)
    public String NewProductForm(Model model){
        model.addAttribute("product", new Product());

        return "new_product";
    }
    //uzimamo podatke sa forme, radimo validacciju unesenih podatak, i ako je sve ok unosimo proizvod u bazu
    @RequestMapping(value = "/new_product", method = RequestMethod.POST) 
    
    public  String addProduct(@ModelAttribute("product")  @Valid Product product,BindingResult result, ModelMap model) throws SQLException, ClassNotFoundException{
    
        if(result.hasErrors()){
            return "/new_product";
        }
     productDAO.insertProduct(product); //unos novog proizvoda u bazu, statiči metod klase ProductDAO
     NewProductForm((Model) model);
        readProducts(model);

        return "new_product"; //vracamo opet pogled new product za unosn sljedeceg proizvoda
    }
    
    //zahtjev za izlistavanje proizvoda
    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public String readProducts(ModelMap model) throws SQLException, ClassNotFoundException {
        ArrayList<String[]> proizvodi = new ArrayList<String[]>();
        proizvodi = ProductDAO.productList();
        model.put("proizvodi", proizvodi);

        return "proizvodi"; //poziv pogleda proizvodi, odnosno proizvodi.jsp
    }
    
    //poziv novo pogleda za prikaz detalja o proizvodu na osnoviu ID proizvoda
    @RequestMapping(value = "/detalji/{Id}",method = RequestMethod.GET)
    public String detalji(@PathVariable (value = "Id") String proizvod, ModelMap model) throws SQLException, ClassNotFoundException {
        
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/web_prodavnica", "root", "");
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select * from products where productID="+proizvod);
         if(rs.next()){
         model.addAttribute("proizvod", rs.getString("productDescription"));
         model.addAttribute("productName",rs.getString("productName"));
         }
      
        return "detalji";
    }
    
//brisanje proizvoda na osnuv ID broja proizvoda
        @RequestMapping(value = "/brisi/{param}")
        public String deleteProduct(@PathVariable (value = "param") String proizvod, ModelMap model) throws SQLException, ClassNotFoundException {
        
        ProductDAO.deleteProduct(Integer.parseInt(proizvod));
        readProducts(model);
        return "proizvodi";
    }
        
        
       @RequestMapping(value = "/izmjeni/{param}")
    public String EditProductForm(@PathVariable(value = "param") Integer productId, ModelMap model) throws SQLException {
        Product pr = new Product();
        pr = ProductDAO.selectProduct(productId);
        model.addAttribute("product", pr);
        return "edit_product";
    }

    @RequestMapping(value = "izmjeni/edit_product", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, ModelMap model) throws SQLException, ClassNotFoundException {
        if (result.hasErrors()) {
            return "edit_product";
        }
        System.out.println(product);
        productDAO.editProduct(product);
        readProducts(model);

        return "proizvodi";
    }      
    
}
