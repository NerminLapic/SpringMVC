/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.validation.Valid;
import model.Customer;
import model.CustomerDAO;
import model.Product;
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
@Controller
public class CustomerController {

    //otvranje prazne forme za unos novog kupca, prosljedjumeo instanuc klase Customer, metod GET
    @RequestMapping(value = "/new_customer", method = RequestMethod.GET)
    public String NewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "new_customer";
    }

    //snimanje unesenih podatak sa forme, ukoliko su pravilno uneseni
    @RequestMapping(value = "/new_customer", method = RequestMethod.POST)

    public String addCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, ModelMap model) throws SQLException, ClassNotFoundException {

        if (result.hasErrors()) {
            return "/new_customer";
        }
        CustomerDAO.insertCustomer(customer);

        return "new_customer";
    }

    //poziv prikaza liste kupaca
    @RequestMapping(value = "/lista_kupaca", method = RequestMethod.GET)
    public String readCustomers(ModelMap model) throws SQLException, ClassNotFoundException {

        ArrayList<String[]> kupci = new ArrayList<String[]>();
        kupci = CustomerDAO.customerList();
        model.addAttribute("kupci", kupci);

        return "kupci";
    }

    //brisanje kupca pi ID broju
    @RequestMapping(value = "/brisiKupca/{param}")
    public String deleteCustomer(@PathVariable(value = "param") String proizvod, ModelMap model) throws SQLException, ClassNotFoundException {

        CustomerDAO.deleteCustomer(Integer.parseInt(proizvod));
        readCustomers(model);
        return "/kupci";
    }

    //forma za izmjenu postojeceg kupca, odabit po ID broju. Otvara se forma sa popunjenim podacima odabranog kupca
    @RequestMapping(value = "/izmjeniKupca/{param}")
    public String EditCustomerForm(@PathVariable(value = "param") Integer customerId, ModelMap model) throws SQLException {
        Customer cust = new Customer();
       cust=CustomerDAO.selectCusomer(customerId);
        model.addAttribute("customer", cust);

        return "edit_customer";
    }

    @RequestMapping(value = "izmjeniKupca/edit_customer", method = RequestMethod.POST)

    public String editCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, ModelMap model) throws SQLException, ClassNotFoundException {

        if (result.hasErrors()) {
            return "edit_customer";
        }
        CustomerDAO.editCustomer(customer);
        readCustomers(model);

        return "kupci";
    }
}
