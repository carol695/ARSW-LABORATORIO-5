package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("filtroSubmuestreo")
public class filtroSubmuestreo implements filtroBlueprint {

    public filtroSubmuestreo(){
    }

    /**
     *
     */
    @Override
    public Blueprint filtro(Blueprint bp) {
        List<Point> oldPoints=bp.getPoints();
        ArrayList<Point> points=new ArrayList<Point>();
        for(int i=0;i<oldPoints.size();i++){
            if((i%2)==0){
                points.add(oldPoints.get(i));
            }
        }
        Point[] ans = new Point[points.size()];
        return new Blueprint(bp.getAuthor(),bp.getName(),points.toArray(ans));
    }
}
