public class Post {
    private int id;
    private String username;
    private String text;
    private int toxicScore;
    private boolean poisonPill;

    public Post(int toxicScore, String text, String username, int id) {
        this.toxicScore = toxicScore;
        this.text = text;
        this.username = username;
        this.id = id;
        this.poisonPill = false;
    }

    public boolean needsManualReview() {
        return toxicScore > 60;
    }
    
    public String toString() {
        return String.format("Post {id = %d, username = %s, text = %s, toxicScore = %d}",
                id, username, text, toxicScore);
    }

    public static Post poisonPill() {
        Post post = new Post(0, "The pill...", "Pillmaster", 0);
        post.poisonPill = true;
        return post;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getToxicScore() {
        return toxicScore;
    }

    public void setToxicScore(int toxicScore) {
        this.toxicScore = toxicScore;
    }

    public boolean isPoisonPill() {
        return poisonPill;
    }

    public void setPoisonPill(boolean poisonPill) {
        this.poisonPill = poisonPill;
    }
}
