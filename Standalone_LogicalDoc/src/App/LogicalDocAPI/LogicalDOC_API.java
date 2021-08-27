package App.LogicalDocAPI;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.MessageFormat;

public class LogicalDOC_API {
    public static void main(String[] args) throws IOException, JSONException {
        System.out.println("*********************************************************");

        // GET
        System.out.println("\n\t>>>>>>>>> Testing GET <<<<<<<<<<");
        System.out.println("sID: "+getSid());
        System.out.println("Bookmarks: "+getBookmarks());
        /*listFolders();
        listDocumentsInFolder(100);
        listDocumentsInFolder(101);*/

        System.out.println("Documents Array: "+getDocumentsArray(101));

        // POST
        System.out.println("\n\t>>>>>>>>> Testing POST <<<<<<<<<<");
        getFoldersArray();
        createFolder("CurlTest");
        getFoldersArray();

        //createDocument();

        // DELETE
        System.out.println("\n\t>>>>>>>>> Testing DELETE <<<<<<<<<<");

        // PUT
        System.out.println("\n\t>>>>>>>>> Testing PUT <<<<<<<<<<");

        System.out.println("*********************************************************");
    }

// GET
    public static String getSid() throws IOException {
        String url = "http://localhost:8081/services/rest/auth/getSid";
        String command = MessageFormat.format("curl -u admin:admin -H \"Accept: application/json\" {0}",url);

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        return response;
    }
    public static JSONArray getBookmarks() throws IOException, JSONException {
        String url = "http://localhost:8081/services/rest/bookmark/getBookmarks\n";
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        JSONArray arr = new JSONArray(response);
        return arr;
    }
    public static JSONArray getFoldersArray() throws IOException, JSONException {
        String url = "http://localhost:8081/services/rest/folder/listChildren?folderId=4";
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        JSONArray arr = new JSONArray(response);
        return arr;
    }
    public static JSONArray getDocumentsArray(int folderID) throws IOException, JSONException {
        String url = MessageFormat.format("http://localhost:8081/services/rest/document/listDocuments?folderId={0}",folderID);
        String command = "curl -u admin:admin -H \"Accept: application/json\" "+url;

        CommandGETResponse commandGETResponse = new CommandGETResponse();
        String response = commandGETResponse.getCommandResponse(command);

        JSONArray arr = new JSONArray(response);
        return arr;
    }

// POST
    public static void createFolder(String folderPATH) throws IOException {
        /*
        Creates folder from main path /Default/...
         */
        String url="http://localhost:8081/services/rest/folder/createSimple";
        String command = MessageFormat.format("curl -u admin:admin -X POST {0} -H \"accept: application/json\" -H \"Content-Type: application/json\" -d \"/Default/{1}\"",url,folderPATH);

        Runtime.getRuntime().exec(command);
    }
    /*public static void addBookmark() throws IOException {
        String url = "http://localhost:8081/services/rest/bookmark/saveBookmark";
        String requestBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<WSBookmark>\n" +
                                    "\t<id>105</id>\n" +
                                    "\t<userId>0</userId>\n" +
                                    "\t<targetId>0</targetId>\n" +
                                    "\t<title>string</title>\n" +
                                    "\t<description>string</description>\n" +
                                    "\t<position>0</position>\n" +
                                    "\t<fileType>string</fileType>\n" +
                                    "\t<type>0</type>\n" +
                                "</WSBookmark>";
        String command = MessageFormat.format("curl -u admin:admin -X POST {0} -H \"accept: application/json\" -H \"Content-Type: application/json\" -d {1}",url,requestBody);

        Runtime.getRuntime().exec(command);
    }*/
    //public static void createDocument(String fileName, File file){    }
    //public static void createDocument() throws IOException{    }

// DELETE
    /*public static void folderDelete(int folderID) throws IOException{
        String url= "https://localhost:8081/services/rest/folder/deleteSimple";
        String command = MessageFormat.format("curl -u admin:admin -X DELETE {0} -H \"accept: application/json\" -d folderId={1}",url,folderID);

        Runtime.getRuntime().exec(command);
    }*/

// PUT
}
