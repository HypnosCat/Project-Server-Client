import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ReaderDataBaseManager {
    private HashMap <String , SectionDataBase> listDB = new HashMap<>();

    public void dataBaseReader(String fileName) throws IOException {
     try {
         File file = new File (fileName);
         Scanner readerFile = new Scanner (file);
         while (readerFile.hasNextLine()){
            String riga = readerFile.nextLine();
            String [] data = riga.split(";");
            // Debugging output
            //System.out.println("Data read: " + Arrays.toString(data));

            // Check for the expected number of fields
            if (data.length != 15) {
                //System.err.println("Unexpected number of fields: " + Arrays.toString(data));
                continue; // Skip this line or handle the error as needed
            }
            initFlashDataBase(data);
         }
         readerFile.close();
         }
         catch (IOException e){
             e.printStackTrace();
         }
     }

    public void initFlashDataBase(String [] dataBase){
        if (dataBase != null) {
            if (!listDB.containsKey(dataBase[1])) {
                listDB.put(dataBase[1], new SectionDataBase(dataBase));
            }else{
                listDB.get(dataBase[1]).addData(dataBase);
            }
        }
    }

    public HashMap getDataBase(){
        return listDB;
    }
}
