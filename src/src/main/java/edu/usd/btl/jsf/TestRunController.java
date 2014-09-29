package edu.usd.btl.jsf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.usd.btl.ontology.BioPortalConnection;
import edu.usd.btl.ontology.BioPortalElement;
import edu.usd.btl.ontology.OntologyFileRead;
import edu.usd.btl.toolbets.ToolBetsEntity;
import edu.usd.btl.toolbets.ToolBetsEntityFacade;
import edu.usd.btl.toolbridge.ToolBridgeEntity;
import edu.usd.btl.toolbridge.ToolBridgeEntityFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.usd.edu.btl.betsconverter.BETSV1.BETSV1;
import org.usd.edu.btl.betsconverter.iPlantV1.IplantV1;
import org.usd.edu.btl.converters.IplantConverter;

/**
 *
 * @author Tyler.Jones
 */
@ManagedBean(name = "testRunController")
@ApplicationScoped
public class TestRunController implements Serializable {

    @EJB
    private ToolBetsEntityFacade betsFacade;
    @EJB
    private ToolBridgeEntityFacade bridgeFacade;

    private List<ToolBetsEntity> betsList;
    private List<ToolBridgeEntity> bridgeList;
    private String betsJSON;

    private List<BioPortalElement> allNodes; //list of bioportal elements
    private Integer randInt;
    private List<String> nodesList = new ArrayList<String>();

    private ToolBetsEntity betsEntity;
    private ToolBridgeEntity bridgeEntity;

    @PostConstruct
    public void init() {
        getAllBets();
        getAllBridges();
    }

    public TestRunController() {

    }

    /**
     * ====================================== GETTERS AND SETTERS
     * ======================================
     *
     * @return
     */
    public ToolBetsEntityFacade getBetsFacade() {
        return betsFacade;
    }

    public void setBetsFacade(ToolBetsEntityFacade betsFacade) {
        this.betsFacade = betsFacade;
    }

    public ToolBridgeEntityFacade getBridgeFacade() {
        return bridgeFacade;
    }

    public void setBridgeFacade(ToolBridgeEntityFacade bridgeFacade) {
        this.bridgeFacade = bridgeFacade;
    }

    public List<ToolBetsEntity> getBetsList() {
        return betsList;
    }

    public void setBetsList(List<ToolBetsEntity> betsList) {
        this.betsList = betsList;
    }

    public List<ToolBridgeEntity> getBridgeList() {
        return bridgeList;
    }

    public void setBridgeList(List<ToolBridgeEntity> bridgeList) {
        this.bridgeList = bridgeList;
    }

    public String getBetsJSON() {
        return betsJSON;
    }

    public void setBetsJSON(String betsJSON) {
        this.betsJSON = betsJSON;
    }

    public List<BioPortalElement> getAllNodes() {
        return allNodes;
    }

    public void setAllNodes(List<BioPortalElement> allNodes) {
        this.allNodes = allNodes;
    }

    public List<String> getNodesList() {
        return nodesList;
    }

    public void setNodesList(List<String> nodesList) {
        this.nodesList = nodesList;
    }

    public ToolBetsEntity getBetsEntity() {
        return betsEntity;
    }

    public void setBetsEntity(ToolBetsEntity betsEntity) {
        this.betsEntity = betsEntity;
    }

    public ToolBridgeEntity getBridgEntity() {
        return bridgeEntity;
    }

    public void setBridgEntity(ToolBridgeEntity bridgeEntity) {
        this.bridgeEntity = bridgeEntity;
    }

    /**
     * ==================================== METHODS
     * ====================================
     *
     * @return
     */
    public byte[] convertToBETS() {
        System.out.println("******** IN testRunController convertToBETS() *************");
        File ipInput = new File("C:\\Users\\Tyler_000\\Desktop\\BTL-SVN\\BTL-PROJECT\\TJ - Projects\\BTL-DB-Project\\MavenMySQLTest\\test_inputs\\test_iplant_FULL.json"); //iPlantInput
        ObjectMapper mapper = new ObjectMapper(); //create new Jackson Mapper

        IplantV1 ipTool;
        //BETSV1 betsTool;

        try {
            //map input json files to iplant class
            ipTool = mapper.readValue(ipInput, IplantV1.class);

            //map input json file to Bets class
            //betsTool = mapper.readValue(betsInput, BETSV1.class);
            //call iplantToBets()
            BETSV1 bets = IplantConverter.toBETS(ipTool); //pass the iplant tool spec, convert to bets
            /*===============PRINT JSON TO CONSOLE AND FILES =================== */
            System.out.println("************************************************\n"
                    + "*********PRINTING OUT FIRST CONVERSION************\n"
                    + "************************************************\n");
            //print objects as Json using jackson
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            betsJSON = ow.writeValueAsString(bets); //write Json as String

            System.out.println("=== IPLANT TO BETS JSON === \n"
                    + betsJSON);

            //write to files
            //ow.writeValue(new File("bets_Converted_toIplant.json"), betsJson);
            //ow.writeValue(new File("iPlant_OUTPUT.json"), iPlantJson);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return betsJSON.getBytes(); //return BETS JSON as byte[]
    }

    public void makeTestToolBets() throws Exception {
        randInt = getRandInt(1, 1000); //get a random integer
        ToolBetsEntity locBETS = new ToolBetsEntity(); //create BetsEntity object
        locBETS.setName("Test Name" + randInt);
        locBETS.setSummary("Test Summary");
        locBETS.setVersion("1.0");
        locBETS.setBets(convertToBETS());  //convert input file to BETS...setBets to entity
        betsFacade.create(locBETS); //create the entity.
        betsEntity = locBETS; //set betsEntity = to  this betsEntity
    }

    public void makeTestBridge() throws Exception { //exception for query
        makeTestToolBets();  //make test bets entity
        queryOntology();
        for (int i = 0; i < 5; i++) {
            ToolBridgeEntity locBridge = new ToolBridgeEntity();
            locBridge.setOntologyId(nodesList.get(i));
            locBridge.setToolId(getBetsEntity());
            bridgeFacade.create(locBridge);
        }
    }

    public void queryOntology() {
        allNodes = new ArrayList<BioPortalElement>();
        //get list of nodes from ontology
        BioPortalConnection bioPortal = new BioPortalConnection();
        try {
            allNodes = bioPortal.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //read owl ontology file
        OntologyFileRead ontFileRead = new OntologyFileRead();
        allNodes = ontFileRead.readFile("C:\\Users\\Tyler_000\\Desktop\\BTL-SVN\\BTL-PROJECT\\TJ - Projects\\BTL-DB-Project\\MavenMySQLTest\\ontology_files\\EDAM_1.3.owl");

        Integer randNode;
        //get 5 random nodes, add to allNodes list
        for (int i = 0; i < 5; i++) {
            randNode = getRandInt(1, allNodes.size());
            if (allNodes.get(randNode) != null) {
                BioPortalElement testElem = allNodes.get(randNode);
                allNodes.add(testElem);
            }
        }

        //add nodes to a string list
        for (int i = 0; i < allNodes.size(); i++) {
            BioPortalElement loopEle = allNodes.get(i);
            this.nodesList.add(loopEle.getURI());
        }
    }

    public void testPipeline() throws Exception {
        makeTestBridge();
    }

    public void getAllBets() {
        if (betsList == null) {
            betsList = getBetsFacade().findAll();
        }
        this.betsList = betsList;
    }

    public void getAllBridges() {
        if (bridgeList == null) {
            bridgeList = getBridgeFacade().findAll();
        }
        this.bridgeList = bridgeList;
    }

    public Integer getRandInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
