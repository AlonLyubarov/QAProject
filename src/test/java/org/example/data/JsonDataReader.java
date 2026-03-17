package org.example.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

// Class to read JSON file and return list of LoginData
public class JsonDataReader {

    public static List<LoginData> getLoginData() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // Load JSON from resources folder
        InputStream is = JsonDataReader.class
                .getClassLoader()
                .getResourceAsStream("loginData.json");

        // Convert JSON array to LoginData[]
        LoginData[] data = mapper.readValue(is, LoginData[].class);

        // Convert to List and return
        return Arrays.asList(data);
    }
}