package logic;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.badlogic.gdx.utils.Json;

public class SaveState {
	
	
    
	public SaveState()
	{
		
	}

	/**
	 * Loads the game from the server with a name
	 * @param name The name of the game I want
	 * @return The map loaded or the null if impossible to load
	 */
	public Map loadGame(String name)
	{	
		String url="https://web.fe.up.pt/~up201605314/test/enviar.php";
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
            if(!responseBody.equals("Fail"))
            {
                Json json=new Json();
                load=json.fromJson(Map.class, responseBody);
            }
            else
            {
            	load=null;
            }
            
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
	public void saveGame(String name, Map map) {
		try {
			Json json = new Json();
			String guardar = json.toJson(map);

			URL url = new URL("https://www.fe.up.pt/~up201605314/test/receber.php");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// connection.setRequestProperty("Content-Length",
			// String.valueOf(postData.length()));
			PrintWriter out = new PrintWriter(connection.getOutputStream());
			String name2 = "name=" + URLEncoder.encode(name, "UTF-8");
			String email = "game=" + URLEncoder.encode(guardar, "UTF-8");
			out.println(name2 + "&" + email);
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				//System.out.println(line);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}