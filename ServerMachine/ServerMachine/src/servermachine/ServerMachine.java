/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermachine;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import service.MachineService;
import service.SalleService;
import util.HibernateUtil;

/**
 *
 * @author Ajmaoui
 */
public class ServerMachine {
  
   public static void main(String[] args) throws RemoteException {
   try {
    IDao<Salle> salleDao = new SalleService();
    IDao<Machine> machineDao = new MachineService();
    
    
     List<Machine> machines = machineDao.findAll();
     /*
        for (Machine machine : machines) {
            if (machine != null) {
                System.out.println("ID: " + machine.getId());
                System.out.println("Référence: " + machine.getRef());
                System.out.println("Marque: " + machine.getMarque());
                System.out.println("Prix: " + machine.getPrix());
                if (machine.getSalle() != null) {
                    System.out.println("Salle ID: " + machine.getSalle().getId());
                } else {
                    System.out.println("Salle non définie pour cette machine.");
                }
                System.out.println();
            }
        }*/

     /*   List <Salle > salles = salleDao.findAll();
        for (Salle salle : salles) {
            System.out.println("SALLE --------------" + salle.getCode());
        }*/
        
        List<Machine> machinesInSalle = machineDao.getMachinesBySalle("salle 1");
                       for (Machine machine : machinesInSalle) {
                           System.out.println("ID: " + machine.getId());
                           System.out.println("Référence: " + machine.getRef());
                           System.out.println("Marque: " + machine.getMarque());
                           System.out.println("Prix: " + machine.getPrix());
                             System.out.println("Salle: " + machine.getSalle().getCode());
                           System.out.println();
                       }
           LocateRegistry.createRegistry(2323);
           Naming.bind("rmi://localhost:" + 2323 + "/salleDao", salleDao);
           Naming.bind("rmi://localhost:" + 2323 + "/machineDao", machineDao);

    System.out.println("En attente d'un client ");
} catch (AlreadyBoundException ex) {
    Logger.getLogger(ServerMachine.class.getName()).log(Level.SEVERE, null, ex);
} catch (MalformedURLException ex) {
    Logger.getLogger(ServerMachine.class.getName()).log(Level.SEVERE, null, ex);
}

}

}
