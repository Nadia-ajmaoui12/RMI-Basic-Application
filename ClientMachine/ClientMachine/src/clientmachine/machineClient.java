/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmachine;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ajmaoui
 */
public class machineClient {
    private static final int PORT = 2323;

    public static void main(String[] args) {
   try {
     IDao<Machine>   dao = (IDao<Machine>) Naming.lookup("rmi://localhost:" + PORT + "/machineDao");

      List<Machine> machines = dao.getMachinesBySalle("test");
        for (Machine machine : machines) {
            System.out.println("ID: " + machine.getId());
            System.out.println("Référence: " + machine.getRef());
            System.out.println("Marque: " + machine.getMarque());
            System.out.println("Prix: " + machine.getPrix());
            System.out.println("Salle ID: " + machine.getSalle().getId()); 
            System.out.println();
        }

} catch (NotBoundException ex) {
    Logger.getLogger(machineClient.class.getName()).log(Level.SEVERE, null, ex);
} catch (MalformedURLException ex) {
    Logger.getLogger(machineClient.class.getName()).log(Level.SEVERE, null, ex);
} catch (RemoteException ex) {
    Logger.getLogger(machineClient.class.getName()).log(Level.SEVERE, null, ex);
}

    }
}
