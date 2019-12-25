package apps.PWMiner.GraphVis;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import apps.PWMiner.common.Define;

import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;

/**
 * @author Qiong Cheng
 *
 * Singleton class for handling pathway's image
 */
public class GraphVisHandler extends prefuse.Display {
    //protected static Logger logger = Logger.getLogger(GraphVisHandler.class);

    private static boolean init = false;
    static GraphVisHandler imageLib = new GraphVisHandler();
    
    private static final String hover = "hover";   
    public static final String GRAPH = "graph";   
    private static final String nodes = "graph.nodes";
    
	private GraphVisHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static void init() {
        if (init) {
            return;
        }
        init = true;
    }
	
	public static PWGraphSearchViz getImage(String basedir, String graphFile, boolean isOrdered, String imageName, int imageL, int imageW){
		PWGraphSearchViz viz=null;
		init();
    	try{
    		viz = new PWGraphSearchViz(basedir  + "/" + Define.DATA_DIR + "/" + graphFile, isOrdered, imageL, imageW, 0);
    	    //ZoomToFitAction fitaction = new ZoomToFitAction(viz.getVisualization());
    	    //fitaction.run(1.0);
    		System.out.println("output " + (viz==null? "T" : "F"));
    		if (viz.isVisible()){
	    	    while ( ! viz.isSaveImageEnable() ){
	    	    	
	    	    }
	    	    	
	    	    System.out.println("output = " + basedir +"/" + Define.GRAPH_VIZ_DIR + "/" + imageName + Define.VIZ_G_SUFFIX);
	    	    FileOutputStream fout = new FileOutputStream(basedir +"/" + Define.GRAPH_VIZ_DIR + "/" + imageName + Define.VIZ_G_SUFFIX);
		    	viz.saveImage(fout, "PNG", 1.0);//1.0/viz.getScale()
		    	fout.close(); 
    		}else
    			viz=null;
	    }catch (Exception ex){
	    	ex.printStackTrace();
	    }     
	    return viz;
    }
	
	public static PWGraphSearchViz getImageByType(String basedir, String graphFile, boolean isOrdered, String imageName, int imageL, int imageW, int type){
		PWGraphSearchViz viz=null;
		init();
    	try{
    		viz = new PWGraphSearchViz(basedir + "/" + Define.DATA_DIR + "/" + graphFile, isOrdered, imageL, imageW, type);
     		if (! viz.isVisible())
    			viz=null;
	    }catch (Exception ex){
	    	ex.printStackTrace();
	    }     
	    return viz;
    }	
	
	public static void saveImageByType(PWGraphSearchViz viz, String basedir, String graphFile1, boolean isOrdered, String imageName, int imageL, int imageW, int type){
		init();
    	try{
    		if (viz.isVisible()){
	    	    while ( ! viz.isSaveImageEnable() ){
	    	    	
	    	    }
	    	    	
	    	    System.out.println("output = " + basedir  +"/" + Define.GRAPH_VIZ_DIR + "/" + imageName + Define.VIZ_G_SUFFIX);
	    	    FileOutputStream fout = new FileOutputStream(basedir +"/" + Define.GRAPH_VIZ_DIR + "/" + imageName + Define.VIZ_G_SUFFIX);
		    	viz.saveImage(fout, "PNG", 1.0);//1.0/viz.getScale()
		    	fout.close(); 
    		}
	    }catch (Exception ex){
	    	ex.printStackTrace();
	    }     
    }	
	
    public static void main(String[] args) {
    	//System.setProperty("java.awt.headless","true");
    	
    	//ConfigFile.init();
    	//String pw = "Escherichia_coli_K12/superpathway_of_threonine_metabolism";
    	final String pw = "Escherichia_coli_K12/arginine_biosynthesis_I";
		final String graphFile = pw + ".grp";

    	final PWGraphSearchViz viz = GraphVisHandler.getImageByType(System.getProperty("user.dir"), graphFile, true, pw, 400, 400, CommonDef.ISMAPPING);     	
        JFrame frame = new JFrame("Graph Visualization");
        
        //main display controls
        viz.setSize(425,425);
        viz.pan(5, 5);
        viz.addControlListener(new DragControl());
        viz.addControlListener(new PanControl());
        viz.addControlListener(new ZoomControl());
        viz.addControlListener(new WheelZoomControl());
        viz.addControlListener(new ZoomToFitControl());
   
        //frame.getContentPane().add(viz);
		  
        // create a new JSplitPane to present the interface
    	JPanel fpanel = new JPanel();
    	JButton saveButton = new JButton(CommonDef.SAVE, CommonDef.createImageIcon("b1.gif", CommonDef.SAVE));
    	saveButton.setPressedIcon(CommonDef.createImageIcon("b1d.gif", CommonDef.SAVE));
    	saveButton.setRolloverIcon(CommonDef.createImageIcon("b1d.gif", CommonDef.SAVE));
    	saveButton.setDisabledIcon(CommonDef.createImageIcon("b1.gif", CommonDef.SAVE));
    	saveButton.setMargin(new Insets(0,0,0,0));
    	saveButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GraphVisHandler.saveImageByType(viz, System.getProperty("user.dir") , graphFile, true, pw, 400, 400, CommonDef.ISMAPPING);
				System.out.println("---------");
				
				//Upload
				
			}
    		
    	});
    	fpanel.add(saveButton);
    	
    	JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, viz, fpanel);
    	split.setOneTouchExpandable(true);
    	split.setContinuousLayout(false);
    	split.setDividerLocation(400);

    	// now we run our action list
    	viz.getVisualization().run("draw");

    	frame.getContentPane().add(split);
        frame.setSize(viz.getWidth()+10,viz.getHeight()+160);    	
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        frame.setVisible(true);
        
    }
    
    
}
