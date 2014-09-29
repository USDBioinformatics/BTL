/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.usd.btl.toolbridge.tests;

import edu.usd.btl.toolbets.ToolBetsEntity;
import edu.usd.btl.toolbets.ToolBetsEntityFacade;
import edu.usd.btl.toolbridge.ToolBridgeEntity;
import edu.usd.btl.toolbridge.ToolBridgeEntityFacade;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Bill Conn <Bill.Conn@usd.edu>
 */
@RunWith(Arquillian.class)
public class ToolBridgeEntityFacadeTest {
    
    public ToolBridgeEntityFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         List<ToolBridgeEntity> list = tBridgeFacade.findAll();
         for(ToolBridgeEntity i : list) {
             tBridgeFacade.remove(i);
         }
    }
    
    @After
    public void tearDown() {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        FileAsset persistenceXml = new FileAsset(
                new File("src/test/resources/META-INF/jpa-test-persistence.xml"));
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ToolBetsEntity.class)
                .addClass(ToolBetsEntityFacade.class)
                .addClass(ToolBridgeEntity.class)
                .addClass(ToolBridgeEntityFacade.class)
                .addAsManifestResource(persistenceXml, "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    //@Inject
    //private UserTransaction txn;
    
    @Inject
    private ToolBridgeEntityFacade tBridgeFacade;
    @Inject
    private ToolBetsEntityFacade tBETSFacade;

    @PersistenceContext
    private EntityManager em;

    //@Inject
    //private ToolBetsEntityFacade tBEF;
    
    @Test
    public void testCreate() throws Exception {
        ToolBetsEntity tBETS = new ToolBetsEntity();
        tBETS.setName("Test");
        tBETS.setSummary("HI");
        tBETS.setVersion("1.0");
        tBETS.setBets("JSON".getBytes());
        tBETSFacade.create(tBETS);
        
        ToolBridgeEntity tBE = new ToolBridgeEntity();
        ToolBridgeEntity tBE2 = new ToolBridgeEntity();
        ToolBridgeEntity tBE3 = new ToolBridgeEntity();
        tBE.setOntologyId("Test Name");
        tBE2.setOntologyId("Test Name 2");
        tBE3.setOntologyId("Test Name 3");
        tBE.setToolId(tBETS);
        tBE2.setToolId(tBETS);
        tBE3.setToolId(tBETS);
        
        tBridgeFacade.create(tBE);
        tBridgeFacade.create(tBE2);
        tBridgeFacade.create(tBE3);
        
        assertEquals(tBridgeFacade.count(),3);

    }
    
    @Test
    public void testFindAll() throws Exception {
        List<ToolBridgeEntity> list = tBridgeFacade.findAll();
        assertEquals(list.size(),0);
    }

}