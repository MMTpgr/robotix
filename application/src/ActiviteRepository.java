import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class ActiviteRepository {


    private String dataFile = "Activites.xml";


    // -------------------------- GETTER SETTER --------------------------

    
    // -------------------------- UTILS METHODS --------------------------

    public Activite getActivite(String name){

        Activite foundActivite = null;

        return foundActivite;

    }

    public void addActivite(Activite activite){
        return;
    }

    public void removeActivite(String name){
        return;
    }

    public ArrayList<Activite> parseActivites() throws ParserConfigurationException, IOException, SAXException {

        ArrayList<Activite> activites = new ArrayList<>();
        Activite currentActivite;

        File xmlFile = new File(this.dataFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(xmlFile);

        // Normaliser la structure XML
        document.getDocumentElement().normalize();

        // Activites

        NodeList activiteList = document.getElementsByTagName("Activite");

        for (int i = 0; i < activiteList.getLength(); i++) {
            Element activiteElement = (Element) activiteList.item(i);

            String name = activiteElement.getElementsByTagName("name").item(0).getTextContent();
            String etat = activiteElement.getElementsByTagName("etat").item(0).getTextContent();
            String description = activiteElement.getElementsByTagName("description").item(0).getTextContent();
            String date = activiteElement.getElementsByTagName("date").item(0).getTextContent();
            String host = activiteElement.getElementsByTagName("host").item(0).getTextContent();

            LocalDate localDate = LocalDate.parse(date);


            currentActivite = new Activite(name, localDate, description, host, Activite.ETAT.valueOf(etat));

            // Participants
            ArrayList<String> participants = new ArrayList<>();
            NodeList participantList = activiteElement.getElementsByTagName("participant");

            for (int j = 0; j < participantList.getLength(); j++) {
                participants.add(participantList.item(j).getTextContent() + " ");
            }

            currentActivite.setParticipants(participants);


            // Tâches
            ArrayList<Tache> taches = new ArrayList<>();
            NodeList tacheList = activiteElement.getElementsByTagName("tache");
            for (int j = 0; j < tacheList.getLength(); j++) {
                Element tacheElement = (Element) tacheList.item(j);
                String tacheName = tacheElement.getElementsByTagName("name").item(0).getTextContent();
                Tache currentTache = new Tache(tacheName);



                // Actions
                LinkedList<Action> actions = new LinkedList<>();
                NodeList actionList = tacheElement.getElementsByTagName("action");
                for (int k = 0; k < actionList.getLength(); k++) {
                    Element actionElement = (Element) actionList.item(k);
                    String actionName = actionElement.getElementsByTagName("name").item(0).getTextContent();
                    String robot = actionElement.getElementsByTagName("robot").item(0).getTextContent();




                    // Composantes nécessaires
                    ArrayList<String> composanteNecessaires = new ArrayList<>();
                    NodeList composanteList = actionElement.getElementsByTagName("composante");
                    System.out.print("      Composantes nécessaires: ");
                    for (int l = 0; l < composanteList.getLength(); l++) {
                        composanteNecessaires.add(composanteList.item(l).getTextContent());
                    }


                    Action currentAction = new Action(actionName, composanteNecessaires);

                    actions.add(currentAction);

                }

                currentTache.setActions(actions);


                currentActivite.addTache(currentTache, -1);
            }

            activites.add(currentActivite);

        }

            return activites;

    }

    public void writeActivites(){

        // JSON WRITING HERE

    }


    public ArrayList<Activite> filterActivites (ActiviteFilter filter, boolean robotsConform) throws ParserConfigurationException, IOException, SAXException {

        ArrayList<Activite> activites = this.parseActivites();

        // Filtering with Treeset or sort function.
        if (filter.equals(ActiviteFilter.NOM)){
            activites.sort(new NomComparator());
        } else if (filter.equals(ActiviteFilter.DATE)) {
            activites.sort(new DateComparator());
        } else if (filter.equals(ActiviteFilter.POPULARITE)) {
            activites.sort(new PopulariteComparator());
        }

        return activites;
    }
}

// -------------------------- Filtering --------------------------

enum ActiviteFilter{
    NOM,
    DATE,
    POPULARITE
}

class NomComparator implements Comparator<Activite> {
    @Override
    public int compare(Activite a1, Activite a2) {
        return a1.getName().compareTo(a2.getName());
    }
}

class DateComparator implements Comparator<Activite> {
    @Override
    public int compare(Activite a1, Activite a2) {
        return a1.getDate().compareTo(a2.getDate());
    }
}
class PopulariteComparator implements Comparator<Activite> {
    @Override
    public int compare(Activite a1, Activite a2) {
        return Integer.compare(a1.getPopularite(), a2.getPopularite());
    }
}


