package LogicalDocAPI_Testing;

import java.io.IOException;

/*
curl -u admin:admin -H 'Accept: application/json' http://localhost:8080/services/rest/folder/listChildren?folderId=4

          >>>>>  Port: 8081 <<<<<
 */

public class Create_New_Folder {
    public static void main(String[] args) throws IOException {

        String url = "http://localhost:8080/services/rest/folder/listChildren?folderId=4";
        String command = "curl -u admin:admin -H 'Accept: application/json' "+url;

        Process process = Runtime.getRuntime().exec(command);

    }
}
