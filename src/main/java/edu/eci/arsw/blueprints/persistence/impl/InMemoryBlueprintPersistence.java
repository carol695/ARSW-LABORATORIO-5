package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */

@Repository
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{



    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();
    public Set<Blueprint> getBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> ans = new HashSet<Blueprint>();
        for (Map.Entry<Tuple<String,String>,Blueprint> entry: blueprints.entrySet()) {
            ans.add(entry.getValue());
        }
        if(ans.isEmpty()){
            throw new BlueprintNotFoundException("No Blueprints avaliable ");
        }
        else {
            return ans;
        }
    }

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp = new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);

        Point[] pts1=new Point[]{new Point(80, 45),new Point(39, 64),new Point(1254,546),new Point(7,8),new Point(4,2),new Point(11,22)};
        Blueprint bp1=new Blueprint("Carol", "Planos1",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);

        Point[] pts2=new Point[]{new Point(0, 1),new Point(1, 0)};
        Blueprint bp2=new Blueprint("Gabriela", "Planos2",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);

        Point[] pts3=new Point[]{new Point(50, 32),new Point(1, 42)};
        Blueprint bp3=new Blueprint("Gabriela", "Planos3",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    public Set<Blueprint> getBlueprintByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> ans = new HashSet<>();

        Blueprint bprintprov;
        for(Map.Entry<Tuple<String,String>,Blueprint>  entry :  blueprints.entrySet()){
            bprintprov=entry.getValue();
            if(bprintprov.getAuthor().equals(author)){
                ans.add(bprintprov);
            }
        }
        if (ans.isEmpty()){
            throw new BlueprintNotFoundException("The author does not exist: "+author);
        }
        else{
            return ans;
        }

    }

    @Override
    public void updateBlueprint(String author, String name, Blueprint bp) {

    }

}
