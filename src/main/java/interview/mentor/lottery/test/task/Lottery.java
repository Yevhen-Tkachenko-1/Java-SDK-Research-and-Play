package interview.mentor.lottery.test.task;

import java.util.Random;

public class Lottery extends ColorSupplier {
    public Ball getRandomBall() {

        Ball ball = new Ball();

        ball.setBallColor(getRandomColor());

        ball.setBallNumber(new Random().nextInt(100));

        return ball;
    }
}
