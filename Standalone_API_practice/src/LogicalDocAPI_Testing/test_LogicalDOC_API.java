package LogicalDocAPI_Testing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.print.PrinterGraphics;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;

public class test_LogicalDOC_API {
    public static void main(String[] args) throws IOException, JSONException {
        System.out.println("*********************************************************");

        // GET
        System.out.println("\n\t>>>>>>>>> Testing GET <<<<<<<<<<");
        /*getSid();
        getBookmarks();
        listFolders();
        listDocumentsInFolder(100);
        listDocumentsInFolder(101);*/

        // POST
        System.out.println("\n\t>>>>>>>>> Testing POST <<<<<<<<<<");
        listFolders();
        createFolder("CurlTest");
        listFolders();

        //createDocument();

        // DELETE
        System.out.println("\n\t>>>>>>>>> Testing DELETE <<<<<<<<<<");

        // PUT
        System.out.println("\n\t>>>>>>>>> Testing PUT <<<<<<<<<<");

        System.out.println("*********************************************************");
    }


    // GET
    public static  void getSid() throws IOException {
        String url = "http://localhost:8081/services/rest/auth/getSid";
        String command = MessageFormat.format("curl -u admin:admin -H \"Accept: application/json\" {0}",url);

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        System.out.println("\n\tsID: "+response);
    }
    public static void getFolderName(int folderId) throws IOException, JSONException {
        String url = "http://localhost:8081/services/rest/folder/getFolder?folderId="+folderId;
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String responseString = commandGETResponse.getCommandResponse(command);

        JSONObject responseJSON = new JSONObject(responseString);

        System.out.print(responseJSON.get("name"));
    }
    public static void listFolders() throws IOException, JSONException {
        String url = "http://localhost:8081/services/rest/folder/listChildren?folderId=4";
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        JSONArray arr = new JSONArray(response);
        System.out.println("\n\tFolders [");
        for (int i=0; i<arr.length();i++) {
            JSONObject item = arr.getJSONObject(i);
            System.out.println("\t\tName: "+item.get("name")+" - ID: "+item.get("id"));
        }
        System.out.println("\n\t]");
    }
    public static void listDocumentsInFolder(int folderID) throws IOException, JSONException {
        String url = "http://localhost:8081/services/rest/document/listDocuments?folderId="+folderID;
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        JSONArray arr = new JSONArray(response);
        System.out.print("\n\t");
        getFolderName(folderID);System.out.println(" - Documents[");
        for (int i=0; i<arr.length();i++) {
            JSONObject item = arr.getJSONObject(i);
            System.out.println("\t\t"+item.get("fileName"));
        }
        System.out.println("\n\t]");

        //System.out.println("FolderID: "+folderID+ "Documents: "+response);
    }
    public static void getBookmarks() throws IOException, JSONException {
        String url = "http://localhost:8081/services/rest/bookmark/getBookmarks\n";
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        JSONArray arr = new JSONArray(response);
        System.out.println("\n\tBookmarks [");
        for (int i=0; i<arr.length();i++) {
            JSONObject item = arr.getJSONObject(i);
            System.out.println("\t\tName: "+item.get("title")+" - ID: "+item.get("id"));
        }
        System.out.println("\n\t]");
    }

    // POST
    public static void createFolder(String folderPATH) throws IOException {
        /*
        Creates folder from main path /Default/...
         */
        String url="http://localhost:8081/services/rest/folder/createSimple";
        //String requestBody = "";
        String command = MessageFormat.format("curl -u admin:admin -X POST {0} -H \"accept: application/json\" -H \"Content-Type: application/json\" -d \"/Default/{1}\"",url,folderPATH);

        Runtime.getRuntime().exec(command);
    }

    //public static void createDocument() throws IOException{    }

    // DELETE

    // PUT
}
