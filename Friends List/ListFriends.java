import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ListFriends {
    Map<String, String> friendsMap;
    List <String> friendsKey;

    ListFriends() {
        friendsMap = new HashMap<>();
        friendsKey = new ArrayList<>();
    }

    void readFiles(String file) throws IOException
    {
        FileReader fileReader = null;
        BufferedReader reader = null;

        try
        {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            String read = "";

            while((read = reader.readLine()) != null)
            {
                read = read.replaceAll(" ", "");
                String arraySplit[] = read.split(":");
                friendsMap.put(arraySplit[0], arraySplit[1]);
                friendsKey.add(arraySplit[0]);
            }
        }
        catch(Exception e)
        {
            System.out.println("file not found");
        }
        finally
        {
            if(fileReader != null)
            {
                fileReader.close();
            }
            
            if(reader != null)
            {
                reader.close();
            }
        }
    }

    void showFriends()
    {
        for(int i = 0; i < friendsMap.size(); i++)
        {
            String arr[] = friendsMap.get(friendsKey.get(i)).split(";");
            for(int j = 0; j < arr.length; j++)
            {
                String nameYear[] = arr[j].split(",");
                System.out.println(friendsKey.get(i) + " berteman dengan " + nameYear[0] 
                                   + " sejak " + nameYear[1]);
            }
        }
    }
    
}