public class Tortoise implements Runnable{
    private int totalstep;
    private int step;
    public Tortoise(int totalstep){
        this.totalstep = totalstep;
    }

    @Override
    public void run(){
        while (step < totalstep){
            step ++;
            System.out.printf("烏龜跑了 %d步....%n", step);
        }
    }
}
