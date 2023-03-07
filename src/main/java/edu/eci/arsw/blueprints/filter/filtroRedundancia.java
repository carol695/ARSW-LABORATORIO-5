package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;



@Service("filtroRedundancia")
public class filtroRedundancia implements filtroBlueprint {

    public filtroRedundancia(){
    }

    @Override
    public Blueprint filtro(Blueprint bp) {
        boolean redundancia;
        ArrayList<Point> points=new ArrayList<Point>();
        for (Point i :bp.getPoints()){
            redundancia=false;
            for(Point j : points){
                if(i.equals(j)){
                    redundancia=true;
                    break;
                }
            }
            if(redundancia==false){
                points.add(i);
            }
        }
        Point[] ans = new Point[points.size()];
        return new Blueprint(bp.getAuthor(),bp.getName(),points.toArray(ans));
    }
}

