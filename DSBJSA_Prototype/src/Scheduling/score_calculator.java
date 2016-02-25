/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduling;

import com.sun.org.apache.bcel.internal.generic.D2F;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.cloudbus.cloudsim.Log;



public class score_calculator {
groupStateManager statemgr=new groupStateManager();
public double Context_Maximum_MIPS=10000;//trillon instruction persecond
public double Context_Maximim_RAM=2048;
public double Context_Maximum_Storage=104857600;// to represent 100 terbyte of memory space
public double Context_Maximum_BW=5120;//to mean 4 x 10Gbit/s. REf: https://www.profitbricks.com/help/What_is_the_maximum_bandwidth_of_your_data_center
// score for each
public double score_mips,score_ram,score_storage,score_bw;
//average score for all
double serverscore;
public int get_score(String name, long storage, double mips,int ram, int bw)
{  
    
score_storage=(storage/Context_Maximum_Storage)*100;
score_mips=(mips/Context_Maximum_MIPS)*100;     
score_ram=(ram/Context_Maximim_RAM)*100;
score_bw=(bw/Context_Maximum_BW)*100;
Log.printLine("Acquired  Available MIPS of " +name);
Log.printLine("Acquired  Available Secondary Memory of " +name);
Log.printLine("Acquired  Available RAM Size of " +name);
Log.printLine("Acquired  Available Bandwidth of " +name);
Log.printLine("<< All Parameters are Acuired for >> "+name);
Log.printLine("<<Logging Score of >>" + name + " as");
Log.printLine(" Identifier_: "+name+" Secondary Memory: "+score_storage+ " MIPS:" +score_mips+" RAM: "+score_ram+" Bandwidth: "+score_bw); 
Double [] x;
//add this values to array and send to rounder to grup state manager
Double[] scorelist=new Double[4];
scorelist[0]=score_storage;
scorelist[1]=score_mips;
scorelist[2]=score_ram;
scorelist[3]=score_bw;
int []recivescore=new int[4];
recivescore=rounder(name,scorelist); //Here the system calls the rounder
//------------------------------------------------------------------------------
System.out.println("Calculating the score of Elements in :" +name +" ..."  );    
int Per_server_Score=0;
for(int i=0;i<=3;i++)
{ 
    Per_server_Score=Per_server_Score+recivescore[i];
    Log.printLine("Recived Entity scored "+recivescore[i]);
}
Log.printLine("System is performing score aggregation for "+ name+"  ... ");
Log.printLine("<<Logged server score as " + Per_server_Score);
return Per_server_Score;
}

static int truncated_score;

public static int[] rounder(String name, Double [] scorelist) // pass array here
{
int scorereturn[]=new int[4];
DecimalFormat df = new DecimalFormat("#");
df.setRoundingMode(RoundingMode.HALF_DOWN);

for(int i=0;i<=3;i++)
{
Double d = scorelist[i].doubleValue();
truncated_score=Integer.parseInt(df.format(d));    
scorereturn[i]=truncated_score;
}
return scorereturn; //return array here  
}


}
