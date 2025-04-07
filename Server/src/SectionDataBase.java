import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        String msg ="";
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()){
            Data data = iterator.next();
            if (data.getClassificazione().equals("Categoria "+categoriaN)){
                msg += data.toString();
            }
        }
        return msg;
    }

    public String getDataByMunicipio(String municipioN ,String municipioNEX){
        String msg ="";
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()){
            Data data = iterator.next();
            if (data.getMunicipio().equals("Municipio "+municipioN+" (ex Mun "+municipioNEX+")")){
                //Debugging output
                //System.out.println("Municipio "+municipioN+" (ex Mun "+municipioNEX+")");
                msg += data.toString();
            }
        }
        return msg;
    }

    public String getDataByTipologia(){
        String msg ="";
        Iterator<Data> iterator = lastData.iterator();
        while (iterator.hasNext()){
            Data data = iterator.next();
            msg += data.toString();
        }
        return msg;
    }
}
