public class GuitarString{
    private static final double  DECAY_FACTOR = 0.994;
    private static final int SR = 44100;
    private int tics = 0;
    private int capacity = 0;
    private RingBuffer rBuffer;

    // create a guitar string of the given frequency, using a sampling rate of 44,100
    public GuitarString(double frequency){
       capacity = (int) (Math.ceil(SR / frequency));
        rBuffer = new RingBuffer(capacity);
        for (int i = 0; i< capacity; i++){
            rBuffer.enqueue(0);
        }
    }

    // create a guitar string whose size and initial values are given by the array
    public GuitarString(double[] init){
        for(int i = 0; i < init.length; i ++){
            rBuffer.enqueue(init[i]);
        }
        capacity = init.length;
    }

    // set the buffer to white noise
    public void pluck(){
        while(!rBuffer.isEmpty()) {
            rBuffer.dequeue();
        }
        while (!rBuffer.isFull()) {
            rBuffer.enqueue(Math.random() - 0.5);
        }
    }

    // advance the simulation one time step
    public void tic(){
        double first = rBuffer.dequeue();
        double second = rBuffer.peek();
        double addon = DECAY_FACTOR * .5 * (first + second);
        rBuffer.enqueue(addon);
        this.tics ++;
    }

    // return the current sample
    public double sample(){
        return rBuffer.peek();
    }

    // return number of tics
    public int time(){
        return this.tics;
    }
}

