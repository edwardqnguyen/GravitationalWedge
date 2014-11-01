/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unusedClasses;

import Boundaries.Boundary;
import gravitationalwedge.Particle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Edward
 */
public class DataReader {
    Scanner sc;
    double inc;
    Particle curState,nextState;
    Boundary b;
    public DataReader(String fileName,double increment,Boundary boun) throws FileNotFoundException, IOException{
        sc=new Scanner(new File(fileName));inc=increment;
        curState=new Particle(sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),boun);
        curState.time=sc.nextDouble();
        
    }
    public boolean hasNextState(){
        return sc.hasNext();
    }
    public Particle next() throws IOException{
        if(nextState==null){
            if(!hasNextState())return null;
            nextState=new Particle(sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),sc.nextDouble(),curState.b);
            nextState.time=sc.nextDouble();   
        }
        if(curState.time+inc<nextState.time){
            curState.timeUpdate(inc);
            return curState;
        }
        else curState.clone(nextState);
        
        nextState=null;
        
        return curState;
    }
}
