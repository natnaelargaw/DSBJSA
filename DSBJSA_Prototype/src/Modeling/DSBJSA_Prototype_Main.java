/*  
This Code is developed to proof the efficency of proposed Algorithm , Distributed Score Based Job Scheduling Algorithm for Cloud Computing enviroment.
©Copyright: Addis Ababa University, Department of Computer Science
This Class Mainly performs cloud modeling and serves as an entry point for the voerall system.
*/
package Modeling;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;

import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

//overriden
import Scheduling.Cloudsim_Core;
import Scheduling.score_calculator;
import Scheduling.groupStateManager;

import Scheduling.createCloudlet;
import Scheduling.createVirtualMachine;
import Scheduling.DSBJSA_Broker;

public class DSBJSA_Prototype_Main {
    /** The vmList. */
private static List<Cloudlet> cloudletList;
/** The cloudlet list. */
//private static List<Vm> vmList;
    /**
     * @param args the command line arguments
     */
public static int [][] server_group_information=new int[1000000][4];
private static List<Vm> vmlist;
//int datacenterCount=5;

// --------------------------------
public static void main(String[] args) { 
        int datacenterCount=1;
        Datacenter [] datacenter= new Datacenter[datacenterCount]; 
        int i;
        int num_user = 1;   // number of cloud users
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = false;  // mean trace events
        // Initialize the CloudSim library
        Cloudsim_Core.init(num_user, calendar, trace_flag);       
        //Log.printLine("DSBJSA is modeling and initializing the cloud Enviroment : Time-"+CloudSim.clock());
        //Creating Datacenters
        String datacenter_name;
        DSBJSA_Prototype_createDC dcCreate=new DSBJSA_Prototype_createDC();
        //in this phase we pass arguments to create datacenter ...
        for(i=0;i<datacenterCount;i++)
        {            
            datacenter_name= "DATACENTER_" + i;
            datacenter[i]=dcCreate.createDatacenter(datacenter_name,i);   
        } 
        //log group information ...... ...... ...... .......
  Log.printLine("Gathering the group capacity in the cloud ...");
  Log.printLine("                           ... ...");
  Log.printLine("                                  ... ...");
  Log.printLine("System Acquired the capacity of each Group");
  for(int j=0;j<datacenterCount-1;j++)
 {
    Log.printLine("+---------------- Group "+(j+1)+" ----------------------+");
    Log.printLine("+    Storage:" + server_group_information[j][0] + "    MIPS:" +
        server_group_information[j][1] + "    RAM:" + server_group_information[j][2]
        + "    Bandwidth:" + server_group_information[j][3] +" +");
    Log.printLine(" Finalizing group state calculation  process ... ... ...");
 }
       
       //Beign Working on Cloudlets ... Cloudlets...................................Cloudlets.
        //create Broker
        //DSBJSA_Prototype_broker R_broker=new DSBJSA_Prototype_broker();
        //DatacenterBroker broker=R_broker.createBroker();
        DSBJSA_Broker broker = createBroker();
			int brokerId = broker.getId();
//creating virtual machine ...
        //createVirtualMachine vmCreator=new createVirtualMachine();
        createCloudlet cloudletCreator=new createCloudlet();
        
        
       // vmList = vmCreator.createVM(brokerId, 100, 0); //creating 5 vms
       	


//cloudletList = cloudletCreator.createCloudlet(brokerId, 100); // creating 100 cloudlets
createCloudlet createcloudlet=new createCloudlet();
cloudletList = createcloudlet.createCloudlet(brokerId, 20); // creating 100 cloudlets
//+---------------------------------------------------------------------------------------------     +++
// There is some thing that i have to do here. 
//Categorizing jobs per group and then creating VM for Each Virtual Machine. This one seems cool
Log.printLine("--------Fetched   "+cloudletList.size() + "Number of Jobs ...");


//what is next + get the requirment intermms of mips,ram, storage, and bw
//+---------------------------end of job categorization------------------------------------------    +++
broker.submitCloudletList(cloudletList);
// submitting broker list   
createVirtualMachine vmcreate=new createVirtualMachine();
vmlist =vmcreate.createVM(brokerId,6); //creating 10 vms       
broker.submitVmList(vmlist);
     
       // start simulation
       // Fifth step: Starts the simulation
       Cloudsim_Core.startSimulation();

       List<Cloudlet> newList = broker.getCloudletReceivedList();
       Cloudsim_Core.stopSimulation();
       
        // show results
       DSBJSA_Prototype_Interfacing display=new DSBJSA_Prototype_Interfacing();
       display.printCloudletList(newList);
        //Print the debt of each user to each datacenter
	for(i=0;i<datacenterCount;i++)
        {
        datacenter[i].printDebts();
        
        }
        Log.printLine("Distributed Score Based Scheduling Simulation finished Successfully!");       
    
            
            } 






public static void printCloudletList(List<Cloudlet> list) {
		int size = list.size();
		Cloudlet cloudlet;

		String indent = "    ";
		Log.printLine();
		Log.printLine("========== OUTPUT ==========");
		Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
				"Data center ID" + indent + "VM ID" + indent + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

		DecimalFormat dft = new DecimalFormat("###.##");
		for (int i = 0; i < size; i++) {
			cloudlet = list.get(i);
			Log.print(indent + cloudlet.getCloudletId() + indent + indent);

			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
				Log.print("SUCCESS");

				Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
						indent + indent + indent + dft.format(cloudlet.getActualCPUTime()) +
						indent + indent + dft.format(cloudlet.getExecStartTime())+ indent + indent + indent + dft.format(cloudlet.getFinishTime()));
			}
		}

	}
    





//creating broker


private static DSBJSA_Broker createBroker(){

		DSBJSA_Broker broker = null;
		try {
			broker = new DSBJSA_Broker("Broker");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}
}



    
   
