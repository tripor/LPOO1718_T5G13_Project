package graphic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Scanner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
/**
 * 
 * @author figor
 *
 */
public class GroundUpGame extends Game {
	/**
	 * Manages the game assets
	 */
	private AssetManager assetManager;
	/**
	 * The screen of the game
	 */
	private GroundUpScreen screen;

	@Override
	public void create() {
		this.assetManager = new AssetManager();
		screen=new GroundUpScreen(this);
		this.setScreen(screen);
	}
	/**
	 * Loads the game from the server with a name
	 * @param name The name of the game I want
	 */
	public void loadGame(String name)
	{	
		String url="https://web.fe.up.pt/~up201605314/test/receive.php";
	    String charset = "UTF-8";
	    String param1=name;
	    String param2="";
	    try {
			String query = String.format("name=%s&game=%s",URLEncoder.encode(param1, charset),URLEncoder.encode(param2, charset));
            URLConnection connection= new URL(url+"?"+query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            String responseBody;
            try (Scanner scanner = new Scanner(response)) {
                responseBody = scanner.useDelimiter("\\A").next();
            }
            this.screen=(GroundUpScreen) GroundUpGame.fromString(responseBody);
            
            
		}  catch (IOException | ClassNotFoundException e) {
            System.out.println("Error Message");
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
		}
	}

	/** Read the object from Base64 string. */
	private static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}

	/** Write the object to a Base64 string. */
	private static String toString(Serializable o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	/**
	 * Save the game to a server
	 * @param name The name I want to save to
	 */
	public void saveGame(String name)
	{
		String url="https://web.fe.up.pt/~up201605314/test/receive.php";
	    String charset = "UTF-8";
	    String param1=name;
	    try {
			String param2 = toString(this.screen);
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

	/**
	 * Gets the assetManager of GroundUpGame
	 * @return the game assetManager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Sets the game AssetManager
	 * @param assetManager the game assetManager I want to change to
	 */
	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

}
