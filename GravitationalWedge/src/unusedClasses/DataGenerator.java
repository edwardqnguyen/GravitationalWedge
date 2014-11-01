/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unusedClasses;

import gravitationalwedge.Particle;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Edward
 */
public class DataGenerator {
    dataWriter dw;
    String file;
    public DataGenerator(String s) throws FileNotFoundException{
        dw=new dataWriter(s);
        file = s;
    }
    public void generate(Particle p,double maxTime) throws IOException{
        dw.saveState(p);
        while(p.time<=maxTime){
            p.bounceUpdate(p.b.nextBounce(p));
            dw.saveState(p);
            
        }
        dw.stopSaving();
    }
    public void generateSurfaceSection(double x) throws FileNotFoundException{
        SurfaceSectionDataWriter ssdw=new SurfaceSectionDataWriter(file,0);
    }
}
