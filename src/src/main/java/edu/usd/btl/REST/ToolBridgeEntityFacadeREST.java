/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.usd.btl.REST;

import edu.usd.btl.toolbridge.ToolBridgeEntity;
import edu.usd.btl.utility.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Tyler
 */
@Stateless
@Path("bridges")
public class ToolBridgeEntityFacadeREST extends AbstractFacade<ToolBridgeEntity> {
    @PersistenceContext(unitName = "MavenMySQLTest")
    private EntityManager em;

    public ToolBridgeEntityFacadeREST() {
        super(ToolBridgeEntity.class);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<ToolBridgeEntity> findAll() {
        return super.findAll();
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
