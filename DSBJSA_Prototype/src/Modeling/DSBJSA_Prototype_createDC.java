/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeling;

import Scheduling.groupStateManager;
import Scheduling.score_calculator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import Scheduling.groupStateManager;
public class DSBJSA_Prototype_createDC {
    static int group;
  public static Datacenter createDatacenter(String name, int serverid) {
    
		// 1. create a list to store machines machine
		List<Host> hostList = new ArrayList<Host>();
		// 2. A Machine contains one or more PEs or CPUs/Cores. In this example, it will have only one core.
		List<Pe> peList = new ArrayList<Pe>();
		double mips = 100;
		// 3. Create PEs and add these into a list.
		peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
		// 4. Create Host with its id and list of PEs and add them to the list of machines
		int hostId = 0;
		int ram = 512; // host memory (MB)512 GB
		long storage = 10886080; // host storage 80 TB
		int bw = 1040; //10 Gb Bandwindth
		//Creating three hosts
                hostList.add(
			new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList,
				new VmSchedulerTimeShared(peList)
			)
		); // This is our machine
		hostId++;
                hostList.add(
			new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList,
				new VmSchedulerTimeShared(peList)
			)
		); // This is our machine
                hostId++;
                hostList.add(
			new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList,
				new VmSchedulerTimeShared(peList)
			)
		); // This is our machine
// 5. Create a DatacenterCharacteristics object 
		String arch = "x86"; // system architecture
		String os = "Linux"; // operating system
		String vmm = "Xen";
		double time_zone = 10.0; // Time zone this resource located
		double cost = 3.0; // The cost of using processing in this resource
		double costPerMem = 0.05; // The cost of using memory in this resource
		double costPerStorage = 0.001; // The cost of using storage in this							// resource
		double costPerBw = 0.0; // The cost of using bw in this resource
		LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding S							// devices by now
		DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
				arch, os, vmm, hostList, time_zone, cost, costPerMem,
				costPerStorage, costPerBw);
                Log.printLine("Datacenter Characterstrics:");
               //this is where to call the score calculator
               Log.printLine("Initializing Score Calculator");
               int Per_server_score=0;
               score_calculator scalc=new score_calculator();
               //Here i got the sum of the score of a given server
               Per_server_score=scalc.get_score(name, storage, mips, ram,bw);
               Log.printLine("System checking the group state ...");
               //System.out.println("This is  ----------------------"+ Per_server_score);
// This is the way we get the score of each server -----------------
               groupStateManager statemgr=new groupStateManager();
               group=statemgr.groupstatmgr(Per_server_score);               
               System.out.println(name + " is registered as a member of Group ... " +group);
               
//The ff code calculates the capacity of a group - this is done by adding the capcity of each server to each respective server     
               statemgr.calculate_groupCapacity(group, storage, mips, ram, bw);
// 6. Finally, we need to create a PowerDatacenter object.
		Datacenter datacenter = null;
		try {
			datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Log.printLine(CloudSim.clock()+" Created datcenter "+ datacenter.getName()+" Hosts "+  hostId +" Characterstics: VMM- " +characteristics.getVmm()+", CostPbw" + characteristics.getCostPerBw());
                return datacenter;
	}      
}
