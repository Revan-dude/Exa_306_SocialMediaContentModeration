public class Main {
    static void main() throws InterruptedException {
        RawContentBuffer rawContentBuffer = new RawContentBuffer(5);
        ReviewBuffer reviewBuffer = new ReviewBuffer(5);

        Thread producer1 = new Thread(new PostProducer("Feed-A", 6, rawContentBuffer));
        Thread producer2 = new Thread(new PostProducer("Feed-B", 6, rawContentBuffer));
        Thread producer3 = new Thread(new PostProducer("Feed-C", 6, rawContentBuffer));
        Thread filter = new Thread(new ContentFilter(rawContentBuffer, reviewBuffer, 3));
        Thread moderator = new Thread(new Moderator(reviewBuffer));

        producer1.start();
        producer2.start();
        producer3.start();
        filter.start();
        moderator.start();

        producer1.join();
        producer2.join();
        producer3.join();
        filter.join();
        moderator.join();
    }
}
