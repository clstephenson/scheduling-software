package com.clstephenson;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    private static LoginSession session;

    public static void main(String[] args) throws IOException, SQLException {

        session = new LoginSession("test", "test");
        if(session.isLoggedIn()) {
            User currentUser = session.getLoggedInUser();

            //testAddingCustomerToDatabase();


        }
    }

    private static void testAddingCustomerToDatabase() throws IOException, SQLException {
        Country country = new Country("My Country");
        City city = new City("My City", country);
        Address address = new Address("123 My Address", "", city, "85255", "112-556-5285");
        Customer customer = new Customer("Chris Test", address, true);
        CustomerRepository customerRepository = new CustomerRepository();
        int custId = customerRepository.add(customer, session);
        System.out.println(customerRepository.findSingle(c -> c.getId() == custId));
    }
}
