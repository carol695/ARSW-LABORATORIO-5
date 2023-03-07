package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */

@Service("InMemoryBlueprintPersistence")
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
        Point[] pts1 =new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts2 =new Point[]{new Point(141, 141),new Point(116, 116)};
        Point[] pts3 =new Point[]{new Point(110, 240),new Point(130, 115)};
        Blueprint bp1 =new Blueprint("author1", "bp1 ",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        Blueprint bp2 =new Blueprint("author2", "bp2 ",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        Blueprint bp3 =new Blueprint("author3", "bp3 ",pts3);
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
            if(bprintprov.getAuthor()==author){
                ans.add(bprintprov);
            }
        }
        if (ans.isEmpty()){
            throw new BlueprintNotFoundException("The author does not exist: "+author);
        }

        return ans;
    }

}
