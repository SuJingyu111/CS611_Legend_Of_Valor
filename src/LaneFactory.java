public class LaneFactory {

    public Lane getLane(int x) {
        if (x==0) {
            return new Lane(0);
        }
        else {
            return new Lane(1);
        }
    }
}
