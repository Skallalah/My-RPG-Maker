package Game;

/**
 * Created by skallalah on 06/05/17.
 */
public class GameDialogue {
    public GameDialogue(String content) {
        next_step_ = null;
        content_ = content;
    }

    public String getContent() {
        return content_;
    }

    public void setContent(String content) {
        this.content_ = content;
    }

    public GameDialogue getNext_step() {
        return next_step_;
    }

    public void setNext_step(GameDialogue next_step) {
        this.next_step_ = next_step;
    }

    private String content_;
    private GameDialogue next_step_;

}
