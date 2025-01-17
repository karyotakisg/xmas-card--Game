/*-
 * Copyright 2005-2018 Diomidis Spinellis
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package gr.aueb.xmascard;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A self-drawable 'snowflake' represented by a character. The move pattern and
 * character to be displayed is determined by subclasses.
 *
 * @author Giorgos Gousios, Diomidis Spinellis, Vaggelis Talos
 * @opt nodefillcolor white
 */
public class SnowFlake extends Drawable {

	/** The snowflake's background color. */
	private static final Color white = new Color(255, 255, 255);

	/**
	 * The 'x' current coordinate of the snowflake.
	 */
	protected int coordX;

	/**
	 * The 'y' current coordinate of the snowflake.
	 */
	protected int coordY;

	/**
	 * The character to be displayed as a snowflake
	 */

	/** The wieght of the snowflake. */
	int weight;

	int sum;//

	/**
	 * Create a snowflake represented by a filled oval.
	 *
	 * @param panel The panel to draw the object onto
	 * @param w     The snowflake's weight
	 */
	public SnowFlake(JPanel panel, int w) {
		super(panel);
		coordX = (int) (bounds.width * Math.random()) + bounds.x; // Snowflake's position before starting to fall
		coordY = 0;

		weight = w; // Increase the speed of each snow flake, to make the game more challenging
	}

	/**
	 * Draw the snowflake and wrap around.
	 *
	 * @param g The Graphics object on which we will paint
	 */
	Integer increasedSnowFlakeNumber; // Counts the number of snow flakes that fall

	static int x, y; // Pacman's coordinates

	/**
	 * The method pacmanCoords(int a, int b) sets main character's coordinates in
	 * order to compare them with the SnowFlake's coordinates to see if our
	 * character has touched any snowflakes.
	 * 
	 * @param a The pacman's x coordinate
	 * @param b The pacman's y coordinate
	 */
	public static void pacmanCoords(int a, int b) {
		x = a;
		y = b;
	}

	static boolean touched = false; // Turns true if our character touches at least one snowflake

	@Override
	public void draw(Graphics g) {

		// Go back to the top when hitting the bottom
		// and also increase the number of snow flakes player managed to dodge
		if (coordY >= bounds.width + bounds.y) {
			coordY = 0;
		}

		if (Math.abs(coordX - x) <= 30 && Math.abs(coordY - y) <= 30) { // When a snowflake touches the main character
			touched = true;
			XmasCard.touched();
		}

		if (coordY == 0 && !touched) { // When a new snowflake falls
			increasedSnowFlakeNumber = ++XmasCard.number;
			XmasCard.num.setText(increasedSnowFlakeNumber.toString());
		} else if (coordY == 0 && touched) { //waits 2 seconds and the closes the game
			Thread t = new Thread();
			try {
				t.sleep(2000);
				System.exit(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Move the snow flake left and right
		switch (coordY % 3) {
		case 1:
			coordX = coordX - 4;
			break;
		case 2:
			coordX = coordX + 4;
			break;
		default:
			break;
		}

		// Move down, based on the weight
		coordY += (int) (Math.random() * weight);

		// Draw the oval in white
		g.setColor(white);
		g.drawOval(coordX, coordY, 40, 40);
		g.fillOval(coordX, coordY, 40, 40);

	}
}
