/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unusedClasses;

import gravitationalwedge.Particle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Edward
 */
public class SurfaceSectionDataWriter extends dataWriter{
    PrintWriter pw;
    double xPos;
    String name;
    public SurfaceSectionDataWriter(String fileName,double x) throws FileNotFoundException{
        super(fileName);
        pw=new PrintWriter(fileName);
        name=fileName;
        xPos=x;
    }
    public void saveState(Particle p){
        double y=p.pos_Y-(p.vel_Y*p.pos_X/p.vel_X)+.5*p.accel*p.pos_X*p.pos_X/p.vel_X/p.vel_X;
        double vely=p.vel_Y-p.accel*p.pos_X/p.vel_X;
        if(p.vel_X<0^p.pos_X<0){
            pw.println(y+" "+vely);
        }
    }
    public void translate(String sFile) throws FileNotFoundException, IOException{
        Scanner sc=new Scanner(new File(sFile));
        Particle p=new Particle(sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),null);
        p.time=sc.nextDouble();
        while(sc.hasNext()){
            Particle pNext=new Particle(sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),null);
            pNext.time=sc.nextDouble();
            if(p.pos_X<0^pNext.pos_X<0)saveState(p);
            p.clone(pNext);
        }
    }
    public void stopSaving(){
        pw.flush();
        pw.close();
    }
}
