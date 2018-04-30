/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.bd;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author geoge
 */
public class ConexionBD implements Serializable{
    
    public String bd = "pokemon";
    public String url = "jdbc:mysql://localhost:3306/" + bd;
    public String user = "root";
    public String pass = "1234";

    public Connection Conectar() {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");

            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Problema " + e);
        }
        return link;

    }
    
}
