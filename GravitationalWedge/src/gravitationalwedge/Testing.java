/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitationalwedge;

import Boundaries.QuadraticBoundary;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Edward
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        Scanner sc=new Scanner(System.in);
        Particle p = new Particle(-1,6,1,0,-10,new QuadraticBoundary(2,2,-10));
        

        System.out.println("Particle before update: \n"+p);
        System.out.println("Particle after update of 1 second: ");
        p.timeUpdate(1);
        System.out.println(p);
        
        double t=1;
        int counter=1;
        
        p = new Particle(-1,6,1,0,-10,new QuadraticBoundary(2,2,-10));
        while(counter<10000000){
            counter++;
            t=p.b.nextBounce(p);
            System.out.println(counter+" "+t);
            String s;
            if(counter%10000 == 0)s = sc.next();
            p.bounceUpdate(t);
            
            /*
            System.out.println("Particle before update: \n"+p);
            System.out.println("Time to hit next boundary: "+t);
            System.out.println();
            p.bounceUpdate(p.b.nextBounce(p));
            System.out.println("Particle after bounce: "+p);
            
            System.out.println("Is the particle still in bounds after 1 second: "+p.b.inBounds(p, 1));
            System.out.println("Is the particle still in bounds after 5 second: "+p.b.inBounds(p, 5));
            System.out.println("Is the particle still in bounds after 10 second: "+p.b.inBounds(p, 10));
            System.out.println("Is the particle still in bounds after 100 second: "+p.b.inBounds(p, 100));
            System.out.println();
            
            String doesntmatter = sc.next();*/
        }
        
        
        
        
        
        System.out.println("Is the particle still in bounds after 1 second: "+p.b.inBounds(p, 1));
        System.out.println("Is the particle still in bounds after 5 second: "+p.b.inBounds(p, 5));
        System.out.println("Is the particle still in bounds after 10 second: "+p.b.inBounds(p, 10));
        System.out.println("Is the particle still in bounds after 100 second: "+p.b.inBounds(p, 100));
        
        
        
        
    }
    public static double quadraticSolve(double a,double b,double c){
        double discriminant=b*b-4*a*c;
        if(discriminant<0)return -1;
        
        double sol1,sol2;
        
        sol1=(-b+Math.sqrt(discriminant))/(2*a);
        sol2=(-b-Math.sqrt(discriminant))/(2*a);
        
        //System.out.println(sol1+" "+sol2);
        if(sol2<=0 && sol1<=0 ){//System.out.println(sol1+" "+sol2);
            return -1;}
        if(sol2<=0 && sol1>0  )return sol1;
        if(sol2>0  && sol1<=0 )return sol2;
        if(sol2>0  && sol1>0  )return sol2<sol1?sol2:sol1;
        
        return -1;
        
    }
}
