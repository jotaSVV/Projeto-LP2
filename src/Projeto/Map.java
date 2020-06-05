package Projeto;

import java.util.ArrayList;

public class Map<T extends Point> implements MapInerface<T> {
    private ArrayList<T> points = new ArrayList<>();

    @Override
    public void addPoint(T point) {
        this.points.add(point);
    }

    @Override
    public ArrayList<T> getAllPoints() {
        return this.points;
    }

    @Override
    public ArrayList<T> getAllPointsByFloor(int floor) {
        ArrayList<T> pointsByFloor = new ArrayList<>();
        for (T point:this.points) {
            if(point.getZ() == floor){
                pointsByFloor.add(point);
            }
        }
        return pointsByFloor;
    }
}
