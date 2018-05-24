package logic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import com.badlogic.gdx.utils.Json;

public class SaveState {
	
	
    
	SaveState()
	{
		
	}

	/**
	 * Loads the game from the server with a name
	 * @param name The name of the game I want
	 * @return The map loaded or the null if impossible to load
	 */
	public Map loadGame(String name)
	{	
		String url="https://web.fe.up.pt/~up201605314/test/receive.php";
	    String charset = "UTF-8";
	    String param1=name;
	    String param2="";
	    Map load=null;
	    try {
			String query = String.format("name=%s&game=%s",URLEncoder.encode(param1, charset),URLEncoder.encode(param2, charset));
            URLConnection connection= new URL(url+"?"+query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            String responseBody;
            try (Scanner scanner = new Scanner(response)) {
                responseBody = scanner.useDelimiter("\\A").next();
            }
            Json json=new Json();
            load=json.fromJson(Map.class, responseBody);
            
		}  catch (IOException e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
		}
        return load;
	}

	/**
	 * Save the game to a server
	 * @param name The name I want to save to
	 */
	public void saveGame(String name,Map map)
	{
		String url="https://web.fe.up.pt/~up201605314/test/receive.php";
	    String charset = "UTF-8";
	    String param1=name;
	    
	    
	    Json json= new Json();
	    try {
	    	String param2 =json.toJson(map);
			String query = String.format("name=%s&game=%s",URLEncoder.encode(param1, charset),URLEncoder.encode(param2, charset));
            URLConnection connection= new URL(url+"?"+query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
            }
            
            
		}  catch (IOException e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
		}
	}
}
