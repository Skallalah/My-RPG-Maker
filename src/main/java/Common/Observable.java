package Common;

public interface Observable {
    public void addObserver(Observer obs);
    public void notifyObserver(String str);
    public void removeObserver();
}
