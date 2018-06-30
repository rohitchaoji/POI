/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poi;
import java.io.FileNotFoundException;
import java.lang.*;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author srbr
 */
public class BattleEngine {

    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        int i = 0;
        double C = 300000000.0;
        int TOTAL_TICKS;
        double R;
        double Preceived;
        double aVel;
        double aircraft_hits = 0;
        double radar_hits = 0;
        int iteration = 0;
        int bandADuration;
        int bandBDuration;
        int bandCDuration;
        int bandDDuration;
        CartesianLocation cl;
        
        int j;
        int k;
        
        // Stationary object.
        aVel = 0;
        
        //
        // TUNABLES.
        // EDIT TO GET DIFFERENT POI
        //
        
        // Set band durations here.
        bandADuration = 10;
        bandBDuration =  0;
        bandCDuration =  0;
        bandDDuration =  0;
        
        // Instantiating the Aircraft and Radar objects here.
        AirCraft A1 = new AirCraft(1, new CartesianLocation(100, 100, 100), new CartesianLocation(0, 0, 0),
                aVel, bandADuration, bandBDuration, bandCDuration, bandDDuration);
        Radar R1 = new Radar(1, new CartesianLocation(0, 0, 0), 25  * Math.pow(10.0, -6.0), Bands.BANDA);
        
        //
        // Total number of ticks the simulation
        // should run.
        //
        TOTAL_TICKS = 10000;
        
        
        
        
        
        //for (k = 0; k < 11; k++) {

            //aircraft_hits = 0;
            //radar_hits = 0;
            //bandADuration = k;
            //nonBandADuration = 10 - k;

            // Main loop.
            
            for (i = 0; i < TOTAL_TICKS; i++){


                cl = A1.getCurLoc();
                

                R = R1.getCurLoc().distanceCart(cl);
                R1.setEchoMatrix(A1, R1, R);
                R1.setEchoMatrixFilledTime((2 * R) / C);
                
                System.out.println(i + "," + A1.getRWRBand() + "," + aircraft_hits + "," + radar_hits + "," + R1.getRadarAntennaAzimuth());
                
                if (R1.checkEchoMatrix() == 1) {

                    radar_hits = radar_hits + 1;
                    if (R1.getSignalBand() == A1.getRWRBand()) {
                        aircraft_hits = aircraft_hits + 1;
                    }
                }  

                // Update the Radar
                R1.update(i);

                // Update the Aircraft.

                A1.update(i);

                
            }

            System.out.println("\n" + bandADuration + "," + bandBDuration + "," + bandCDuration + "," + bandDDuration +
                    "," + aircraft_hits + "," + radar_hits + "," + (aircraft_hits / radar_hits));

        //}
            
        
        
    }
}
