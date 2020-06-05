package Projeto;

import java.util.ArrayList;

public interface MapInerface<T> {
    void addPoint(T point);
    ArrayList<T> getAllPoints();
    ArrayList<T> getAllPointsByFloor(int floor);
}
