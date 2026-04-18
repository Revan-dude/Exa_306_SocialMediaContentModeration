import java.util.LinkedList;
import java.util.Queue;

public class ReviewBuffer {
    private Queue<Post> queue;
    private int capacity;

    public ReviewBuffer(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public synchronized void put(Post post) {
        while (queue.size() >= capacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " review buffer full!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.add(post);
        System.out.println(Thread.currentThread().getName() + " -> REVIEW in: " + post);
        notifyAll();
    }

    public synchronized Post take() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " review buffer empty!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Post post = queue.remove();
        System.out.println(Thread.currentThread().getName() + " -> REVIEW in: " + post);
        notifyAll();
        return post;
    }
}
