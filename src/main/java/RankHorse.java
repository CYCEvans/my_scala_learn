import java.util.List;

public class RankHorse extends Thread {
    List<RankHorse> rank;
    public RankHorse(List<RankHorse> rank){
        this.rank = rank;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
            System.out.println(getName() + "到達終點");
            //放進rank中
            rank.add(this);
        } catch (InterruptedException e) {
            System.out.println(getName() + "被中斷了");
        }
    }
}