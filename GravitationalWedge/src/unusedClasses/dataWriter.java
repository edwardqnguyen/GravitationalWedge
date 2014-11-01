/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unusedClasses;

import gravitationalwedge.Particle;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Edward
 */
public class dataWriter {
    PrintWriter pw;
    public dataWriter(String fileName) throws FileNotFoundException{
        pw=new PrintWriter(fileName);
    }
    public void saveState(Particle p){
        pw.println(p.pos_X+" "+p.pos_Y+" "+p.vel_X+" "+p.vel_Y+" "+p.accel+" "+p.time);
    }
    public void stopSaving(){
        pw.flush();
        pw.close();
    }
}
