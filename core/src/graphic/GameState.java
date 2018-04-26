package graphic;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameState extends Stage {
	/**
     * Viewport width in meters. Height depends on screen ratio
     */
    public static final int VIEWPORT_WIDTH = 4;

    /**
     * A football is 22cm in diameter and the sprite has a width of 200px
     */
    static final float PIXEL_TO_METER = 0.22f / 200;
}
