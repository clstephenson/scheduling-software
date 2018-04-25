package com.clstephenson;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println(new AddressRepository().findSingle(a -> a.getId() == 11));
        System.out.println(new CustomerRepository().findSingle(a -> a.getId() == 11));

    }
}
