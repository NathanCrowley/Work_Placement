package API_Testing;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class SpotifyTest {

    public static String getResponse(String command) throws IOException {
        //String command = "curl -X \"GET\" \"https://api.spotify.com/v1/artists/43ZHCT0cAZBISjO8DG9PnE/top-tracks?market=US\" -H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer BQDa9-farAnJ5efWleECO-3eic7FatPqbDyddFv0e-THsubntaOrhjF3kx3o5OdESZrOEguIpugNS_uDKsge4QxiD50tn7io7Gqc_rW3gyJQ2BdZUZSCKAY4TUwgXY6fw8A4GY6F5wHWXvtqgtjonx1k9K-rc8YWPG8\"";
        Process process = Runtime.getRuntime().exec(command);
        process.getInputStream();

        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);

        for (int numRead; (numRead = in.read(buffer,0,buffer.length))>0;){
            out.append(buffer,0,numRead);
        }

        String response = out.toString();
        return response;
    }

    public static void parseJSONObject(String responseJSONObject) throws JSONException {
        System.out.println("\n\n\n\t******************* Parsing JSON input **********************\n\n");

        /* 1) Loop through JSONObject */
        System.out.println(" 1) JSONObject - {.....} :\n");
        org.json.JSONObject obj = new JSONObject(responseJSONObject);
        Iterator<String> keys = obj.keys();

        //********************************* print array of keys at this level*********************************
        System.out.println("------------ Array of Keys ------------");
        Iterator<String> keys2 = obj.keys();   // casting everything to String !!!!!
        System.out.println("[");
        while(keys2.hasNext()){
            String key2 = keys2.next();
            System.out.println(" "+key2+" - "+key2.getClass()+",");
        }
        System.out.println("]");
        System.out.println("---------------------------------------");
        //******************************************************************

        while(keys.hasNext()){
            String key = keys.next();
            System.out.println("\t"+key+" - "+obj.get(key)+"\n");
        }
        System.out.println("\t************************************************************");

    }

    public static void main(String[] args) throws IOException, JSONException {
        String command;
        //command = "curl -X \"GET\" \"https://api.spotify.com/v1/artists/43ZHCT0cAZBISjO8DG9PnE/top-tracks?market=US\" -H \"Accept: application/json\" -H \"Content-Type: application/json\" -H \"Authorization: Bearer BQDa9-farAnJ5efWleECO-3eic7FatPqbDyddFv0e-THsubntaOrhjF3kx3o5OdESZrOEguIpugNS_uDKsge4QxiD50tn7io7Gqc_rW3gyJQ2BdZUZSCKAY4TUwgXY6fw8A4GY6F5wHWXvtqgtjonx1k9K-rc8YWPG8\"";
        command = "curl https://jsonplaceholder.typicode.com/users/1";

        System.out.println("\n\t******************* Raw JSON **********************\n\n");
        System.out.println(getResponse(command));

        parseJSONObject(getResponse(command));

    }

}


/*

    Check if JSON is object or array with:
        "String data = "{ ... }";
      **Object json = new JSONTokener(data).nextValue();**
        if (json instanceof JSONObject)
          //you have an object
        else if (json instanceof JSONArray)
          //you have an array"
 */