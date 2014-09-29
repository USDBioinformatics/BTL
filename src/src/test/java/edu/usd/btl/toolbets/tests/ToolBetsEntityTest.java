//package edu.usd.btl.toolbets.tests;
//
//import java.io.File;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
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
//import org.junit.BeforeClass;
//
///**
// *
// * @author Bill Conn <Bill.Conn@usd.edu>
// */
//@RunWith(Arquillian.class)
//public class ToolBetsEntityTest {
//
//    @BeforeClass
//    public static void setUpClass() {
//        testEntity.setName("Test Name");
//    }
//    /*
//     @AfterClass
//     public static void tearDownClass() {
//     }
//
//     @Before
//     public void setUp() {
//     }
//
//     @After
//     public void tearDown() {
//     }
//     */
//
//    public ToolBetsEntityTest() {
//
//    }
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//        FileAsset persistenceXml = new FileAsset(
//                new File("src/test/resources/META-INF/jpa-test-persistence.xml"));
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(ToolBetsEntityFacade.class)
//                .addClass(ToolBetsEntity.class)
//                .addAsManifestResource(persistenceXml, "persistence.xml")
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @Inject
//    private UserTransaction txn;
//    ToolBetsEntityFacade betsFacade ;
//    static public ToolBetsEntity testEntity = new ToolBetsEntity();
//  
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Test
//    public void testCreate() throws Exception {
//        ToolBetsEntity tBE = new ToolBetsEntity();
//        tBE.setName("Test Name");
//        tBE.setVersion("1.0");
//        tBE.setSummary("Test Summary");
//        tBE.setBets("Test BETS data".getBytes());
//
//        assertNull(tBE.getId());
//
//        try {
//            this.txn.begin();
//            this.em.persist(tBE);
//            this.txn.commit();
//        } catch (Exception e) {
//            this.txn.rollback();
//            throw new Exception(e.fillInStackTrace());
//        }
//
//        assertNotNull(tBE.getId());
//        em.remove(tBE);
//    }
//
//    @Test
//    public void testFindAll() {
//        //create test entity
//        
//        //persist entity
//        
//        
//        //use facade to find
//        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(ToolBetsEntity.class));
//        List<ToolBetsEntity> foundList = em.createQuery(cq).getResultList();
//        assertFalse(foundList.isEmpty());
//    }
//
//    @Test
//    public void testRemove() throws Exception {
//       
//    }
//}
