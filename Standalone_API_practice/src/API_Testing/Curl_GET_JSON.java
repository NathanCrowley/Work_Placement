package API_Testing;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

import org.json.*;

//Curl Commands for LogicalDOC:
/* Curl Commands for LogicalDOC:
    1) point browser to 'http://localhost:8080/services'
        - Create a new folder:
            'curl -u admin:admin -H "Accept: application/json" \
            -X POST -H "Content-Type: text/plain" -d "/Default/Curl/newfolder" \
             http://localhost:8080/services/rest/folder/createSimple'

        - Create a path of folders starting from folder ID 4:
            'curl -u admin:admin -H "Accept: application/json" \
             -X POST -H "Content-Type: application/x-www-form-urlencoded" -d parentId=4 -d path=How/to/POST/JSON/data/with/Curl \
             http://localhost:8080/services/rest/folder/createPath'

        - Create a document, we need to provide the document binary data:
            'curl -u admin:admin -H "Accept: application/json" \
           -X POST -F folderId=4 -F filename=CHANGELOG.txt -F filedata=@CHANGELOG.txt \
           http://localhost:8080/services/rest/document/upload'

        - Search content,title and tags:
            'curl -u admin:admin -H "Content-Type: application/json" -H "Accept: application/json" -X POST \
           -d "{\"maxHits\":50,\"expression\":\"document management system\",\"expressionLanguage\":\"en\",\"language\":\"en\"}"
           http://localhost:8080/services/rest/search/find'
 */


/*
    Class for API 'GET' request using the 'curl' command. - [https://jsonplaceholder.typicode.com/]
        1) first the user is asked to enter two urls:
            - url to test JSONArray objects
            - url to test JSONObject objects

        2) second the class parses the JSON response:
            - loops through JSON
 */

public class Curl_GET_JSON {

    public static void main(String[] args) throws IOException, JSONException {
        // intro
        System.out.println("\n\t******************* Testing [https://jsonplaceholder.typicode.com/] *******************\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Example urlJSONObject: https://jsonplaceholder.typicode.com/posts/1");
        System.out.println("Example urlJSONArray: https://jsonplaceholder.typicode.com/posts\n");

        // endpoint options
        System.out.println("GET url endpoints: \n\t- /posts \n\t- /comments \n\t- /albums \n\t- /photos \n\t- /todos \n\t- /users\n");

        // get user input endpoint for testing
        System.out.print("Enter endpoint for testing: >>>");
        String endpoint = scanner.next();

        // create url from user endpoint
        String urlJSONObject = "https://jsonplaceholder.typicode.com/"+endpoint+"/1";
        String urlJSONArray = "https://jsonplaceholder.typicode.com/"+endpoint;

        // API get request with curl
        String commandJSONArray = "curl "+urlJSONArray;
        String commandJSONObject = "curl "+urlJSONObject;

        // run the curl command
        Process processJSONArray = Runtime.getRuntime().exec(commandJSONArray);
        Process processJSONObject = Runtime.getRuntime().exec(commandJSONObject);

        processJSONArray.getInputStream();
        processJSONObject.getInputStream();

        // create output strings for API responses
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder outJSONArray = new StringBuilder();
        StringBuilder outJSONObject = new StringBuilder();

        // read JSON response
        Reader inJSONArray = new InputStreamReader(processJSONArray.getInputStream(), StandardCharsets.UTF_8);
        Reader inJSONObject = new InputStreamReader(processJSONObject.getInputStream(), StandardCharsets.UTF_8);

        // loop through JSONArray, append response to string built earlier
        for (int numRead; (numRead = inJSONArray.read(buffer,0,buffer.length))>0;){
            outJSONArray.append(buffer,0,numRead);
        }
        String responseJSONArray = outJSONArray.toString();

        // loop through JSONObject, append response to string built earlier
        for (int numRead2; (numRead2 =inJSONObject.read(buffer,0,buffer.length))>0;){
            outJSONObject.append(buffer,0,numRead2);
        }
        String responseJSONObject = outJSONObject.toString();

        // parse the json
        System.out.println("\n\n\n\t******************* Parsing JSON input **********************\n\n");

/* 1) Loop through JSONObject */
        System.out.println(" 1) JSONObject - {.....} :\n");
        JSONObject obj = new JSONObject(responseJSONObject);
        Iterator<String> keys = obj.keys();
        

        while(keys.hasNext()){
            String key = keys.next();
            System.out.println("\t"+key+" - "+obj.get(key)+"\n");
        }
        System.out.println("\t************************************************************");

/* 2) Loop through JSONArray */
        System.out.println(" 2) JSONArray - [.....] :\n");
        JSONArray arr = new JSONArray(responseJSONArray);

        for (int i=0; i<arr.length();i++){
            Object item = arr.getJSONObject(i);
            System.out.println(item);
        }

        System.out.println("*********************************************************");
        scanner.close();
    }
}
