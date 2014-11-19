/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.btl.toolTree;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.usd.btl.ontology.OntologyFileRead;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler
 */
public class OntoToTree {
    

    public String getOntoJson() throws Exception {
        ObjectMapper treeMapper = new ObjectMapper(); //create new Jackson Mapper
        OntologyFileRead ontFileRead = new OntologyFileRead();
        ArrayList<edu.usd.btl.ontology.BioPortalElement> nodeList = ontFileRead.readFile("C:\\Users\\Tyler\\Documents\\GitHub\\BTL\\src\\ontology_files\\EDAM_1.3.owl");
        ObjectWriter treeWriter = treeMapper.writer().withDefaultPrettyPrinter();

        String edamJSON = treeMapper.writeValueAsString(nodeList);
        System.out.println("**** EDAM ONTOLOGY JSON ****" + edamJSON);

        return edamJSON;
    }

    /*
     This section reads from a local JSON file, similar to the one above.'
     Converts JSON to Java Objects
     */
    public void jsonToJava() throws Exception {
        try {
            File input = new File("C:\\Users\\Tyler\\Documents\\GitHub\\BTL\\src\\ontology_files\\testEdam.json");

            /*
             CONVERT JSON TO JAVA OBJECTS
             */
            //map EDAM ontology JSON to List of objects
            ObjectMapper treeMapper = new ObjectMapper(); //create new Jackson Mapper
            ObjectWriter treeWriter = treeMapper.writer().withDefaultPrettyPrinter();
            List<EDAMNode> ontologyNodes = treeMapper.readValue(input, treeMapper.getTypeFactory().constructCollectionType(
                    List.class, EDAMNode.class
            ));

            for (EDAMNode node : ontologyNodes) { //for each ontology node
                System.out.println(node.getName());
                //build tree from ontology nodes

            }
            JsonNode rootNode = treeMapper.readValue(getOntoJson(), JsonNode.class);
            JsonNode tool = rootNode.get("name");
            
            System.out.println("\n\n\n\n****************************" + tool.toString());
            System.out.println(
                    "**** ONTOLOGY SIZE *****" + ontologyNodes.size());

            String jsonOutput = treeWriter.writeValueAsString(ontologyNodes.get(0));

            System.out.println(
                    "\n\n****ONTOLOGY JSON OUPUT*****+\n"
                    + jsonOutput);

//            IplantV1 iplantOutput = BETSConverter.toIplant(betsTool);
//            String iplantOutputJson = iplantWriter.writeValueAsString(iplantOutput); //write Json as String
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
