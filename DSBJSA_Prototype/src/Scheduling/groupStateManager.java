/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduling;

import java.math.RoundingMode;
import java.text.DecimalFormat;



import Modeling.DSBJSA_Prototype_Main;
import org.cloudbus.cloudsim.Log;
/**
 *
 * @author nat
 */


public class groupStateManager {    
DSBJSA_Prototype_Main main=new DSBJSA_Prototype_Main();
 static int group;
 static int division_factor=4;
    
 public static int groupstatmgr(int recieved_score){
 
     group=recieved_score/division_factor;
if(group<=10)
{
group=1;
}
else if(group<=20)
{
group=2;

}
else if(group<=30)
{
group=3;
}     
else if(group<=40)
{
group=4;
}     
else if(group<=50)
{
group=5;
}         
else if(group<=60)
{
group=6;
} 
else if(group<=70)
{
group=7;
}
else if(group<=80)
{
group=8;
}
else if(group<=90)
{
group=9;
}
else
{
 group=10;
    }
 

return group;
 }
 public void calculate_groupCapacity(int group, long storage,Double mips,long ram,long bw )
 {
     int index=group-1;
 main.server_group_information[index][0]=(int) (main.server_group_information[index][0]+storage);
 main.server_group_information[index][1]=(int) (main.server_group_information[index][1]+mips);   
 main.server_group_information[index][2]=(int) (main.server_group_information[index][2]+ram);   
 main.server_group_information[index][3]=(int) (main.server_group_information[index][3]+bw);
 }
 public void checkCloudlet_recivers()
 {
 
 
 }
 
 
 
 
 
 
 
}
