import java.util.Random;

public class Moderator implements Runnable{

    private int approvedPosts = 0;
    private int rejectedPosts = 0;
    private final Random random = new Random();
    private final ReviewBuffer reviewBuffer;

    public Moderator(ReviewBuffer reviewBuffer) {
        this.reviewBuffer = reviewBuffer;
    }

    @Override
    public void run() {

    }
}
