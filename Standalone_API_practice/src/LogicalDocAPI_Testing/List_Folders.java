package LogicalDocAPI_Testing;

import org.json.JSONException;

import java.io.IOException;

/*
    Class that uses LogicalDoc API to list folders and their details.
      >>>>>  Port: 8081 <<<<<
 */

public class List_Folders {
    public static void main(String[] args) throws IOException, JSONException {

        String url = "http://localhost:8081/services/rest/folder/listChildren?folderId=4";
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        System.out.println(response);

        // Loop through response and print
        /*System.out.println(" 2) JSONArray - [.....] :\n");
        JSONArray arr = new JSONArray(response);

        for (int i=0; i<arr.length();i++){
            JSONObject item = arr.getJSONObject(i);
            System.out.println("*****");
            System.out.println(item);

            Iterator<String> keys = item.keys();
            System.out.println("NAME: "+item.get("name"));

            //Loop through all keys in JSONObject
            while(keys.hasNext()){
                String key = keys.next();
                System.out.println("\t"+key+" - "+item.get(key)+"\n");
            }
        }*/

    }
}
