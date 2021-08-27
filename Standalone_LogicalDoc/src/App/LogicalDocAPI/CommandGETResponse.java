package App.LogicalDocAPI;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class CommandGETResponse {

    public String getCommandResponse(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        process.getInputStream();

        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);

        for (int numRead; (numRead = in.read(buffer,0,buffer.length))>0;){
            out.append(buffer,0,numRead);
        }

        return out.toString();
    }
}
