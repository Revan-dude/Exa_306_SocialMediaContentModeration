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
        boolean pill = false;

        while(!pill){
            Post post = reviewBuffer.take();

            if(post.isPoisonPill()){
                pill = true;
            }

            try {
                Thread.sleep(random.nextInt(400, 901));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            moderate(post);
        }

        System.out.println("Moderator shutting down...");
        printReport();
    }

    private void moderate(Post post) {
        if(!post.isPoisonPill()) {
            if(post.getToxicScore() < 85) {
                approvedPosts++;
            } else {
                rejectedPosts++;
            }
        }
    }

    private void printReport() {
        System.out.printf("""
                === Moderation Statistics ===
                Approved: %d
                Rejected: %d%n
                """, approvedPosts, rejectedPosts);
    }
}
