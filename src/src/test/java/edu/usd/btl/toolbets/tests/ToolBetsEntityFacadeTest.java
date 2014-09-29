//package edu.usd.btl.toolbets.tests;
//
//import java.io.File;
//import javax.inject.Inject;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.asset.FileAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import static org.junit.Assert.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import edu.usd.btl.toolbets.ToolBetsEntity;
//import edu.usd.btl.toolbets.ToolBetsEntityFacade;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//
///**
// *
// * @author Bill Conn <Bill.Conn@usd.edu>
// */
//@RunWith(Arquillian.class)
//public class ToolBetsEntityFacadeTest {
//
//    @BeforeClass
//    public static void setUpClass() {
//        betsEntity = new ToolBetsEntity();
//        betsEntity.setName("Test Tool Name");
//        betsEntity.setSummary("Test Tool Summary");
//        betsEntity.setVersion("v1.0");
//        betsEntity.setBets("Test Bets File".getBytes());
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        try {
//            tbeFacade.remove(betsEntity);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public ToolBetsEntityFacadeTest() {
//    }
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//        FileAsset persistenceXml = new FileAsset(
//                new File("src/test/resources/META-INF/jpa-test-persistence.xml")); //set to use derby in memory DB
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(ToolBetsEntity.class)
//                .addClass(ToolBetsEntityFacade.class)
//                .addAsManifestResource(persistenceXml, "persistence.xml")
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//    @Inject
//    private ToolBetsEntityFacade tbeFacade;
//    static public ToolBetsEntity betsEntity;
//
//    @Test
//    public void testCreate() throws Exception {
//        tbeFacade.create(betsEntity); //create the entity
//        assertEquals(tbeFacade.count(), 1);
//        tbeFacade.remove(betsEntity);
//    }
//
//    @Test
//    public void testEdit() throws Exception {
//        tbeFacade.create(betsEntity); //CREATE THE TEST ENTITY
//        List<ToolBetsEntity> foundList = tbeFacade.findByName("Test Tool Name");
//        ToolBetsEntity locEntity = foundList.get(0); //get the first tool
//        locEntity.setName("Test Tool Edit"); //edit the tool name
//
//        assertTrue(locEntity.getName().equals("Test Tool Edit"));
//
//    }
//
//    @Test
//    public void testFindAll() throws Exception {
//        tbeFacade.create(betsEntity);
//        List<ToolBetsEntity> allTools = tbeFacade.findAll();
//        assertFalse(allTools.isEmpty());
//        assertEquals(allTools.size(), tbeFacade.count()); //assert the list size and entity count are equal
//    }
//
//    @Test
//    public void testRemove() throws Exception {
//        tbeFacade.create(betsEntity); //CREATE THE TEST ENTITY
//        tbeFacade.remove(betsEntity);
//        assertEquals(tbeFacade.count(), 0);
//    }
//
//    @Test
//    public void testFindByName() throws Exception {
//        tbeFacade.create(betsEntity); //CREATE THE TEST ENTITY
//        List<ToolBetsEntity> foundList = tbeFacade.findByName("Test Tool Name");
//        ToolBetsEntity locEntity = foundList.get(0); //get the first tool
//        assertEquals("Test Tool Name", locEntity.getName());
//    }
//
//    @Test
//    public void testCount() {
//        tbeFacade.create(betsEntity); //CREATE THE TEST ENTITY
//        assertEquals(1, tbeFacade.count());
//    }
//
//    @Test
//    public void testFind() {
//        tbeFacade.create(betsEntity); //CREATE THE TEST ENTITY
//        ToolBetsEntity locEntity = tbeFacade.find(betsEntity.getId());
//        assertTrue(locEntity.getName().equals("Test Tool Name"));
//    }
//    
//    @Test 
//    public void testGetBets(){
//        tbeFacade.getBets();
//    }
//}
