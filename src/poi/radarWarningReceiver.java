/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poi;

/**
 *
 * @author srbr
 */
public class radarWarningReceiver {
    
    int checkBand;
    
    public radarWarningReceiver(int cBand) {
        checkBand = cBand;
    }
    
    public void setBand(int cBand) {
        checkBand = cBand;
    }
    
    public int getBand() {
        return checkBand;
    }
    
    
}