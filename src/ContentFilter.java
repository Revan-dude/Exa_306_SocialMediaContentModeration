public class ContentFilter implements Runnable{
    private final RawContentBuffer rawBuffer;
    private final ReviewBuffer reviewBuffer;
    private int producerCount;
    private int poisonPillCount = 0;
    private int autoApproved = 0;
    private int forwardedToReview = 0;

    public ContentFilter(RawContentBuffer rawBuffer, ReviewBuffer reviewBuffer, int producerCount) {
        this.rawBuffer = rawBuffer;
        this.reviewBuffer = reviewBuffer;
        this.producerCount = producerCount;
    }

    @Override
    public void run() {
        while(poisonPillCount != producerCount) {
            Post post = rawBuffer.take();
            process(post);
        }
    }

    private void process(Post post) {
        System.out.println("Filter checks: " + post + ".");

        if(post.isPoisonPill()) {
            poisonPillCount++;
        }
        else if(post.needsManualReview()) {
            forwardedToReview++;
            reviewBuffer.put(post);
            System.out.println("FILTER -> critical post, forwarding to moderator.");
        } else {
            autoApproved++;
            System.out.println("FILTER -> automatically approved.");
        }
    }

    private void printReport() {
        System.out.printf("""
                === FILTER STATISTICS ===
                Automatically approved: %d
                Forwarded to moderator: %d
                """,
                autoApproved,
                forwardedToReview);
    }

    public int getProducerCount() {
        return producerCount;
    }

    public void setProducerCount(int producerCount) {
        this.producerCount = producerCount;
    }

    public int getPoisonPillCount() {
        return poisonPillCount;
    }

    public void setPoisonPillCount(int poisonPillCount) {
        this.poisonPillCount = poisonPillCount;
    }

    public int getAutoApproved() {
        return autoApproved;
    }

    public void setAutoApproved(int autoApproved) {
        this.autoApproved = autoApproved;
    }

    public int getForwardedToReview() {
        return forwardedToReview;
    }

    public void setForwardedToReview(int forwardedToReview) {
        this.forwardedToReview = forwardedToReview;
    }
}
