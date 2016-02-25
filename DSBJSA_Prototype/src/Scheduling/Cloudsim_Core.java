/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scheduling;

/**
 *
 * @author nat
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.predicates.Predicate;
import org.cloudbus.cloudsim.core.predicates.PredicateAny;
import org.cloudbus.cloudsim.core.predicates.PredicateNone;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.CloudInformationService;
import org.cloudbus.cloudsim.core.CloudSim;
import static org.cloudbus.cloudsim.core.CloudSim.run;
public class Cloudsim_Core extends CloudSim {
    

/** The Constant CLOUDSIM_VERSION_STRING. */
    private static final String CLOUDSIM_VERSION_STRING = "3.0";

    /** The id of CIS entity. */
    private static int cisId = -1;

    /** The id of CloudSimShutdown entity. */
    @SuppressWarnings("unused")
    private static int shutdownId = -1;

    /** The CIS object. */
    private static CloudInformationService cis = null;

    /** The Constant NOT_FOUND. */
    private static final int NOT_FOUND = -1;

    /** The trace flag. */
    @SuppressWarnings("unused")
    private static boolean traceFlag = false;

    /** The calendar. */
    private static Calendar calendar = null;

    /** The termination time. */
    private static double terminateAt = -1;
    
public static double startSimulation() throws NullPointerException {
		Log.printLine("Starting  Distributed Score Based Job Scheduler " );
		try {
			double clock = run();
			// reset all static variables
			cisId = -1;
			shutdownId = -1;
			cis = null;
			calendar = null;
			traceFlag = false;

			return clock;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new NullPointerException("CloudSim.startCloudSimulation() :"
					+ " Error - you haven't initialized CloudSim.");
		}
	}    
    
}
