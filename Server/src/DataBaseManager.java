import java.io.IOException;
import java.util.HashMap;

public class DataBaseManager {
        private final String FILENAME = "Comune-di-Roma-Capitale---Elenco-delle-strutture-ricettive.csv";
        private ReaderDataBaseManager readerDataBaseManager;
        private HashMap <String , SectionDataBase> listDB;
    
        public void initDataBase() throws IOException{
            readerDataBaseManager = new ReaderDataBaseManager();
            readerDataBaseManager.dataBaseReader(FILENAME);
            listDB = readerDataBaseManager.getDataBase();
        }

        public String findDataByTipologia(String tipologia){
            String msg = " & ";
            for(java.util.Map.Entry<String, SectionDataBase> entry: listDB.entrySet()){
                String key = entry.getKey();
                SectionDataBase value = entry.getValue();
                if (key.equals(tipologia)) {
                    msg += value.getDataByTipologia();
                }
            }
            return msg;
        }

        public String findDataByCategoria(String categoriaN){
            String msg = " & ";
            for(java.util.Map.Entry<String, SectionDataBase> entry: listDB.entrySet()){
                SectionDataBase value = entry.getValue();
                msg += value.getDataByCategoria(categoriaN);
            }
            return msg;
        }

        public String findDataByMunicipio(String municipioN , String municipioNEX){
            String msg = " & ";
            for(java.util.Map.Entry<String, SectionDataBase> entry: listDB.entrySet()){
                SectionDataBase value = entry.getValue();
                msg += value.getDataByMunicipio(municipioN, municipioNEX);
            }
            return msg;
        }
}
