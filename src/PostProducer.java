import java.util.Random;

public class PostProducer implements Runnable{

    private static int nextId = 1;
    private static final String[] USERS = {
            "kampfpenner17",
            "Christschn01",
            "geovexx",
            "Sc0vile",
            "EliAlien",
            "MrSkulllo",
            "Jonas136",
            "Gamerflo8785"
    };
    private static final String[] TEXTS = {
            "some of you have never been told no and it shows",
            "honestly your takes are embarrassing",
            "wishing everyone a genuinely good day, you deserve it",
            "unfollow me if you think that was funny",
            "proud of you for real, keep going",
            "you're not a victim, you're just annoying",
            "sending love to anyone struggling right now",
            "blocked and moving on, bye",
            "you actually make this app better, thank you",
            "imagine being this loud and this wrong",
            "hope something good happens to you today",
            "not everyone needs an opinion, especially you",
            "genuinely happy to see you winning",
            "some of you need therapy, not a platform",
            "you add nothing to the conversation, ever",
            "just want to say i see you and i appreciate you"
    };
    private String producerName;
    private final Random random = new Random();
    private int postCount;
    private final RawContentBuffer rawBuffer;

    public PostProducer(String producerName, int postCount, RawContentBuffer rawBuffer) {
        this.producerName = producerName;
        this.postCount = postCount;
        this.rawBuffer = rawBuffer;
    }

    private static int nextId() {
        return nextId++;
    }


    @Override
    public void run() {
        for(int i = 0; i < postCount; i++) {
            Post post = new Post(
                    random.nextInt(101),
                    "[" + producerName + "] " + TEXTS[random.nextInt(TEXTS.length)],
                    USERS[random.nextInt(USERS.length)],
                    nextId
            );

            nextId();
            rawBuffer.put(post);

            try {
                Thread.sleep(random.nextInt(200, 701));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        rawBuffer.put(Post.poisonPill());

        System.out.println(Thread.currentThread().getName() + "finished!");
    }
}
