package Game;

/**
 * Created by skallalah on 06/05/17.
 */
public interface GameScript {
    public boolean execute(GameWorld world);
    public Integer getId();
    public void setId(Integer id);
}