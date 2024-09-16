package org.db.lamb;

import org.db.lamb.system.SystemMethods;
import org.db.lamb.system.SystemVariables;

import java.util.Scanner;

/**
 * @ Vishwas Karode
 *
 */
public class Lamb
{
    static boolean listen = true;
    static Scanner in = new Scanner(System.in);
    public static void main( String[] args )
    {
        // setting available databases in public db list
        SystemVariables.availableDBs = SystemMethods.setAvailableDBs();

        // printing intro on initial run
        System.out.println(SystemVariables.INTRO);

        // running core application
        do {
            System.out.print("\nlamb> ");
            String input = in.nextLine();
            listen = SystemMethods.handleInput(input);
        } while(listen);

        in.close();
    }

}
