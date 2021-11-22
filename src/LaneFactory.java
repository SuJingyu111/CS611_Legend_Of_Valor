//Factory class that produces lanes

public class LaneFactory {

    public Lane getLane(int x) {
        if (x==0) {
            return new InaccessibleLane();
        }
        else {
            return new Lane();
        }
    }
}
