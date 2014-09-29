package edu.usd.btl.jsf;
 
import edu.usd.btl.toolbets.ToolBetsEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.DragDropEvent;
 
@ManagedBean(name = "DNDController")
@ApplicationScoped
public class DNDController implements Serializable {
  
    @ManagedProperty("#{testRunController}")
    private TestRunController service;
 
    private List<ToolBetsEntity> tools;
    private List<ToolBetsEntity> droppedTools;
    private ToolBetsEntity selectedTool;
     
    @PostConstruct
    public void init() {
        tools = service.getBetsList();
        droppedTools = new ArrayList<ToolBetsEntity>();
    }
     
    public void onCarDrop(DragDropEvent ddEvent) {
        ToolBetsEntity tool = ((ToolBetsEntity) ddEvent.getData());
  
        droppedTools.add(tool);
        tools.remove(tool);
    }

    public TestRunController getService() {
        return service;
    }

    public void setService(TestRunController service) {
        this.service = service;
    }

    public List<ToolBetsEntity> getTools() {
        return tools;
    }

    public void setTools(List<ToolBetsEntity> tools) {
        this.tools = tools;
    }

    public List<ToolBetsEntity> getDroppedTools() {
        return droppedTools;
    }

    public void setDroppedTools(List<ToolBetsEntity> droppedTools) {
        this.droppedTools = droppedTools;
    }

    public ToolBetsEntity getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(ToolBetsEntity selectedTool) {
        this.selectedTool = selectedTool;
    }
     
    
}