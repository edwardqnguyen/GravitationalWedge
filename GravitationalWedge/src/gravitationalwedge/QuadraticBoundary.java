/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitationalwedge;



/**
 *
 * @author Edward
 */
public class QuadraticBoundary implements Boundary{
    public double amplitude,shift,accel;
    
    
    public QuadraticBoundary(double height,double length,double a){
        amplitude=height;shift=length;accel=a;
    }
    
    
    
    @Override
    public double nextBounce(Particle p){
        double a,b,c;
        
        a=.5*accel + amplitude*p.vel_X*p.vel_X;
        b=p.vel_Y+amplitude*2*p.vel_X*shift+amplitude*2*p.pos_X*p.vel_X;
        c=amplitude*p.pos_X*p.pos_X+2*amplitude*p.pos_X*shift+p.pos_Y;
        
        double t=quadraticSolve(a,b,c);
        //System.out.println(a+" "+b+" "+c);
        if(t>0&&inBounds(p,t))return t;
        
            a=.5*accel + amplitude*p.vel_X*p.vel_X;
            b=p.vel_Y-amplitude*2*p.vel_X*shift+amplitude*2*p.pos_X*p.vel_X;
            c=amplitude*p.pos_X*p.pos_X-2*amplitude*p.pos_X*shift+p.pos_Y;
        
        t=quadraticSolve(a,b,c);
        //System.out.println(a+" "+b+" "+c);
        if(t>0&&inBounds(p,t))return t;
        
        return -1;
        
        
    }
    /*public double nextBounce(Particle p){
        double a=.5*accel+amplitude*p.vel_X*p.vel_X;
        double b,c,time;
        
        if(p.pos_X<0){
            b=p.vel_Y+amplitude*p.vel_X*shift+2*amplitude*p.pos_X*p.vel_X;
            c=amplitude*p.pos_X*p.pos_X+2*amplitude*p.pos_X*shift+p.pos_X;
            if(quadraticSolve(a,b,c)!=-1||!inBounds(p,quadraticSolve(a,b,c))){
                
            }
        }
    }*/
    /*public double nextBounce(Particle p){
        
        double a=.5*accel+amplitude*p.vel_X*p.vel_X;
        double b,c,time;
        
        if(p.pos_X<0){
            b=p.vel_Y+2*amplitude*p.vel_X*p.pos_X-2*amplitude*p.vel_X*shift;
            c=-4*amplitude*p.pos_X*shift;
            
            if(quadraticSolve(a,b,c)==-1||!inBounds(p,quadraticSolve(a,b,c))){
                b=p.vel_Y+2*amplitude*p.vel_X*p.pos_X+2*amplitude*p.vel_X*shift;
                c=0;
            }
            if(quadraticSolve(a,b,c)==-1||!inBounds(p,quadraticSolve(a,b,c)))return 0;
            
            time=quadraticSolve(a,b,c);
        }
        else
        {
            b=p.vel_Y+2*amplitude*p.vel_X*p.pos_X+2*amplitude*p.vel_X*shift;
            c=4.0*amplitude*p.pos_X*shift;
            
            if(quadraticSolve(a,b,c)==-1||!inBounds(p,quadraticSolve(a,b,c))){
                b=p.vel_Y+2*amplitude*p.vel_X*p.pos_X-2*amplitude*p.vel_X*shift;
                c=0;
            }
            if(quadraticSolve(a,b,c)==-1||!inBounds(p,quadraticSolve(a,b,c)))return 0;
            
            time=quadraticSolve(a,b,c);
        }
        
        //System.out.println(time);
        return time;
    }*/
    
    //simplified code (incomplete)
    /*public double nextBounce(Particle p){
        double a,b,c;
        
        a=.5*accel + amplitude*p.vel_X*p.vel_X;
        b=p.vel_Y+amplitude*2*p.vel_X*shift+amplitude*2*p.pos_X*p.vel_X;
        c=amplitude*p.pos_X*p.pos_X+2*amplitude*p.pos_X*shift+p.pos_Y;
        
        double t=quadraticSolve(a,b,c);
        if(t>0&&inBounds(p,t))return t;
        
            a=.5*accel + amplitude*p.vel_X*p.vel_X;
            b=p.vel_Y-amplitude*2*p.vel_X*shift+amplitude*2*p.pos_X*p.vel_X;
            c=amplitude*p.pos_X*p.pos_X-2*amplitude*p.pos_X*shift+p.pos_Y;
        
        t=quadraticSolve(a,b,c);
        if(t>0&&inBounds(p,t))return t;
        return -1;
    
        
        
        
    }*.
    
    /* Done.
     * Given a particular time to check, the methods checks to see whether or
     * not the particle is within the bounds of curve.
     */
    
    public boolean inBounds(Particle p,double time){
        
        double pos_X2=p.pos_X+p.vel_X*time;
        double pos_Y2=p.pos_Y+p.vel_Y*time+.5*accel*time*time;
        
        if(pos_X2>shift+.00001||pos_X2<-(shift+.00001)||pos_Y2<-0.00001||pos_Y2-function(pos_X2)<-.00001){
            //System.out.println(pos_Y2-function(pos_X2)+" "+pos_X2+" "+pos_Y2+" "+function(pos_X2));
            return false;}
        return true;
        
    }
    
    /* Done
     * Finds the two roots of a quadratic equation with a,b,c as the
     * coefficients of the quadratic. Returns the smallest positive root.
     * Returns -1 if there is no positive real root.
     */
    
    /*public double quadraticSolve(double a,double b,double c){
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
        
    }*/
    
    public double quadraticSolve(double a,double b,double c){
        double discriminant=b*b-4*a*c;
        if(discriminant<0)return -1;
        if(a==0)return -b/c<0?-1:-b/c;
        double sol1,sol2;
        
        sol1=(-b+Math.sqrt(discriminant))/(2*a);
        sol2=(-b-Math.sqrt(discriminant))/(2*a);
        
        double acc = 0.000000001;
        
        
        if(sol2<=acc && sol1<=acc)return -1;
        if(sol2<=acc && sol1>acc)return sol1;
        if(sol2>acc  && sol1<=acc )return sol2;
        if(sol2>acc  && sol1>acc )return sol2<sol1?sol2:sol1;
        return -1;
        
    }
    
    @Override
    //Done.
    //Returns the slope of the boundary.
    public double[] normal(double x){
        double slope = 0;
        slope = -2*amplitude*(x<0&&x!=0?(x+shift):(x-shift));
        
        double[] ret = new double[2];
        
        ret[0]=slope/Math.sqrt((1+slope*slope));
        ret[1]=-1/Math.sqrt(slope*slope+1);
        
        return ret;
    }
    
    @Override
    //Done.
    //Returns the Y value of the boundary given an x value.
    public double function(double x){
        double ret;
	ret=x<0?(x+shift):(x-shift);
	ret*=ret;
	ret*=-1*amplitude;
	ret+=amplitude*shift*shift;
	return ret;
    }
    
    //returns height of boundary
    public double getHeight(){
        return amplitude;
    }
    
    public double getWidth(){
        return shift;
    }
    
    
}
