public class HorseRunnable implements Runnable{
    public void run(){
        int h = 0;
        for (int i=0; i<200; i++){
            h++;
            System.out.println(h);
        }
    }
}