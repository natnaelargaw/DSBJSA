/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduling;

import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;


//local imports
import java.util.Random;
import org.cloudbus.cloudsim.core.CloudSim;
/**
 *
 * @author nat
 */

public class createCloudlet {

    public static List<Cloudlet> createCloudlet(int userId, int cloudlets){
        Log.printLine("Looking for incoming jobs ...");
        Log.printLine("System Accepted a Cloudlet with the following requirement" );
        
		LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
                Random rgen=new Random();
                int Mfactor;
		//Creating object type clodlet
		Cloudlet[] cloudlet = new Cloudlet[cloudlets];
		for(int i=0;i<cloudlets;i++){
                Mfactor=rgen.nextInt(10);//randomization limit/range
                long length = 100*Mfactor;
		long fileSize = 300*Mfactor;
		long outputSize = 300*Mfactor;
		int pesNumber = 1;
		UtilizationModel utilizationModel = new UtilizationModelFull();
                cloudlet[i] = new Cloudlet(i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
	        // setting up the broker id for the specific job
	        cloudlet[i].setUserId(userId);
	        list.add(cloudlet[i]);
	        Log.printLine(" Job with id "+  i+ " have the following features ");
                Log.printLine("Length: " + length + " Core: " + pesNumber + " filesize: " +fileSize+ " outputsize: "+outputSize);
                Log.printLine();
                
                
                }
                
               
		return list;
	
    
    
    }





}
