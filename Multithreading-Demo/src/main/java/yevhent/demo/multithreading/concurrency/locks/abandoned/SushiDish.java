package yevhent.demo.multithreading.concurrency.locks.abandoned;

public class SushiDish {

    private int sushiCount;

    public SushiDish(int sushiCount) {
        this.sushiCount = sushiCount;
    }

    public int eatSushi() {
        sushiCount--;
        return sushiCount;
    }

    public boolean hasSushi() {
        return sushiCount > 0;
    }
}