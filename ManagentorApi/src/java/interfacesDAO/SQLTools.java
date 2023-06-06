/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesDAO;

import java.sql.ResultSet;

/**
 *
 * @author S2-PC00
 */
public interface SQLTools {
     void connect();
    void disconnect();
    int execute(String SQL);
    ResultSet executeQuery(String SQL);
}
