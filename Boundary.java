package gravitationalwedge;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Edward
 */
public interface Boundary {
    public double nextBounce(Particle p);
    public double[] normal(double x);
    public boolean inBounds(Particle p,double time);
    public double function(double x);
    public double getHeight();
    public double getWidth();
    
}
