package edu.usd.btl.jsf;

import BioPortalOntology.BioPortalConnection;
import BioPortalOntology.BioPortalElement;
import edu.usd.btl.toolbridge.ToolBridgeEntity;
import edu.usd.btl.toolbridge.ToolBridgeEntityFacade;
import edu.usd.btl.utility.util.JsfUtil;
import edu.usd.btl.utility.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named("toolBridgeEntityController")
@SessionScoped
public class ToolBridgeEntityController implements Serializable {

    @EJB
    private edu.usd.btl.toolbridge.ToolBridgeEntityFacade ejbFacade;
    private List<ToolBridgeEntity> items = null;
    private ToolBridgeEntity selected;
    private List<ToolBridgeEntity> selectedBridges;
    private List<BioPortalElement> allElements;
    private List<BioPortalElement> selectedElements;

//    @PostConstruct
//    public void init() {
//        allElements = new ArrayList<BioPortalElement>();
//        
//        List<BioPortalElement> testList = new ArrayList();
//        //get list of nodes from ontology
//        BioPortalConnection bioPortal = new BioPortalConnection();
//        try {
//            testList = bioPortal.executeQuery();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        if (testList != null) {
//            for (int i = 0; i < 5; i++) {
//                if (testList.get(i) != null) {
//                    BioPortalElement testElem = testList.get(i);
//                    System.out.println("TestElem Name = " + testElem.getName() + "URI = " + testElem.getURI());
//                    allElements.add(testElem);
//                }
//            }
//        }
//        System.out.println("*********Size= " + allElements.size());
//    }

    public ToolBridgeEntityController() {
    }

    public ToolBridgeEntity getSelected() {
        return selected;
    }

    public void setSelected(ToolBridgeEntity selected) {
        this.selected = selected;
    }
    
    public List<BioPortalElement> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(List<BioPortalElement> selectedElements) {
        this.selectedElements = selectedElements;
    }

    protected void setEmbeddableKeys() {
    }

    public List<ToolBridgeEntity> getSelectedBridges() {
        return selectedBridges;
    }

    public void setSelectedBridges(List<ToolBridgeEntity> selectedBridges) {
        this.selectedBridges = selectedBridges;
    }

    public List<BioPortalElement> getAllElements() {
        return allElements;
    }

    public void setAllElements(List<BioPortalElement> allElements) {
        this.allElements = allElements;
    }

    protected void initializeEmbeddableKey() {
    }

    private ToolBridgeEntityFacade getFacade() {
        return ejbFacade;
    }

    public ToolBridgeEntity prepareCreate() {
        selected = new ToolBridgeEntity();
        initializeEmbeddableKey();
        return selected;
    }

    public List<ToolBridgeEntity> prepareMassCreate() throws Exception {
        selectedBridges = new ArrayList<ToolBridgeEntity>();
        selectedElements = new ArrayList<BioPortalElement>();
        System.out.println("AllElement Size === " + allElements.size());
        
        return selectedBridges;
    }

    public List<ToolBridgeEntity> massCreate() throws Exception {
        List<ToolBridgeEntity> bridgeList = new ArrayList<ToolBridgeEntity>();
        return bridgeList;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ToolBridgeEntityCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ToolBridgeEntityUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ToolBridgeEntityDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ToolBridgeEntity> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ToolBridgeEntity getToolBridgeEntity(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ToolBridgeEntity> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ToolBridgeEntity> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ToolBridgeEntity.class)
    public static class ToolBridgeEntityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ToolBridgeEntityController controller = (ToolBridgeEntityController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "toolBridgeEntityController");
            return controller.getToolBridgeEntity(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ToolBridgeEntity) {
                ToolBridgeEntity o = (ToolBridgeEntity) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ToolBridgeEntity.class.getName()});
                return null;
            }
        }

    }

}
