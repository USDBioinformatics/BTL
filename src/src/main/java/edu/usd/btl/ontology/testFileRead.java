/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.usd.btl.ontology;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler.Jones
 */
public class testFileRead {
    
    public void readOntologyFile() {
        //read owl ontology file
        OntologyFileRead ontFileRead = new OntologyFileRead();
        ArrayList<BioPortalElement> nodeList = ontFileRead.readFile("C:\\Users\\tyler.jones\\Desktop\\BTL-SVN\\BTL-Project\\TJ - Projects\\BTL-DB-Project\\MavenMySQLTest\\ontology_files\\EDAM_1.3.owl");
        
        //print
        for(BioPortalElement node : nodeList){
            System.out.println(node.getName());
        }
    }
    
}
