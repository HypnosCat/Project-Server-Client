import java.util.*;

public class SectionDataBase {
    private List<Data> lastData = new LinkedList<>();

    public SectionDataBase(String[] listSectionData) {
        addData(listSectionData);
    }

    public void addData(String[] listD) {
        if (listD.length < 15) {
            throw new IllegalArgumentException("Input array must have at least 15 elements.-->" + listD.length );
        }

        try {
            lastData.add(new Data(
                    listD[0], listD[1], listD[2], listD[3], 
                    listD[4], listD[5], listD[6], listD[7], 
                    listD[8],listD[9], listD[10],listD[11], 
                    listD[12],listD[13], listD[14]));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer from input data: " + e.getMessage());
            System.err.println("Input data: " + Arrays.toString(listD));
            throw e; // rethrow the exception after logging
        }
    }

    public String getDataByCategoria(String categoriaN){
        StringBuilder msg = new StringBuilder();
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()){
            Data data = iterator.next();
            if (data.getClassificazione().equalsIgnoreCase(categoriaN)){
                msg.append(data.toString());
            }
        }
        return msg.toString();
    }

    public String getDataByMunicipio(String municipioN ,String municipioNEX){
        StringBuilder msg = new StringBuilder();
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()){
            Data data = iterator.next();
            if (data.getMunicipio().equalsIgnoreCase("Municipio "+municipioN+" (ex Mun "+municipioNEX+")")){
                //Debugging output
                //System.out.println("Municipio "+municipioN+" (ex Mun "+municipioNEX+")");
                msg.append(data.toString());
            }
        }
        return msg.toString();
    }

    public String getDataByTipologia(){
        StringBuilder msg = new StringBuilder();
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()){
            Data data = iterator.next();
            msg.append(data.toString());
        }
        return msg.toString();
    }

    public String getDataByKeyword(String keyWord){
        StringBuilder msg = new StringBuilder();
        Iterator<Data> iterator = lastData.iterator();
        String lowerKeyWord = keyWord.toLowerCase(); // Для пошуку без урахування регістру
        while (iterator.hasNext()) {
            Data data = iterator.next();
            if (data.getMunicipio().toLowerCase().contains(lowerKeyWord) ||
                    data.getTipologia().toLowerCase().contains(lowerKeyWord) ||
                    data.getClassificazione().toLowerCase().contains(lowerKeyWord) ||
                    data.getDenominazione().toLowerCase().contains(lowerKeyWord) ||
                    data.getIndirizzo().toLowerCase().contains(lowerKeyWord)) {
                msg.append(data.toString());
            }
        }
        return msg.toString();
    }

    public String getAllTipologia() {
        Set<String> uniqueTipologie = new HashSet<>();
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()) {
            Data data = iterator.next();
            uniqueTipologie.add(data.getTipologia()+"|");
        }

        StringBuilder msgBuilder = new StringBuilder();
        for (String tipologia : uniqueTipologie) {
            msgBuilder.append(tipologia);
        }
        return msgBuilder.toString();
    }

    public String getAllCategoria() {
        Set<String> uniqueTipologie = new HashSet<>();
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()) {
            Data data = iterator.next();
            uniqueTipologie.add(data.getClassificazione()+"|");
        }

        StringBuilder msgBuilder = new StringBuilder();
        for (String tipologia : uniqueTipologie) {
            msgBuilder.append(tipologia);
        }
        return msgBuilder.toString();
    }

    public String getAllMunicipio() {
        Set<String> uniqueTipologie = new HashSet<>();
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()) {
            Data data = iterator.next();
            uniqueTipologie.add(data.getMunicipio()+"|");
        }

        StringBuilder msgBuilder = new StringBuilder();
        for (String tipologia : uniqueTipologie) {
            msgBuilder.append(tipologia);
        }
        return msgBuilder.toString();
    }
}
