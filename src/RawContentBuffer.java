import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RawContentBuffer {
    private Queue<Post> queue;
    private int capacity;

    public RawContentBuffer(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public synchronized void put(Post post) {
        while (queue.size() >= capacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " raw content buffer full!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.add(post);
        if(!post.isPoisonPill()) {
            System.out.println(Thread.currentThread().getName() + " -> RAW in: " + post);
        }
        notifyAll();
    }

    public synchronized Post take() {
        while (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " raw content buffer empty!");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Post post = queue.remove();
        notifyAll();
        return post;
    }

}
