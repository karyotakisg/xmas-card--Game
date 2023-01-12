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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The Christmas Card program main class.
 *
 * @author Giorgos Gousios, Diomidis Spinellis
 * @depend - - - gr.aueb.xmascard.DrawPanel
 * @depend - <instantiate> - gr.aueb.xmascard.MidiPlayer
 * @depend - - - gr.aueb.xmascard.Tree
 * @depend - - - gr.aueb.xmascard.PointSnowFlake
 * @depend - - - gr.aueb.xmascard.SlashSnowFlake
 */
public class XmasCard {

	/** Number of trees */
	private static final int numTrees = 30;
	/** Number of snowflakes */
	private static final int numSnowFlakes = 1500;
	/** Minimum tree width. */
	private static final int treeWidth = 30;
	/** Minimum tree height. */
	private static final int treeHeight = 100;
	/** Additional variation to tree height and width */
	private static final int treeWobble = 100;
	/** Song to play. */
	// private static String musicFile =
	// "xmas-card--Game\\src\\main\\resources\\Jingle_Bells_full_Ab.mid";
	private static String musicFile = "xmas-card--Game\\src\\main\\resources\\Jingle_Bells_full_Ab.mid";

	static Integer number = 0;

	public static JLabel num = new JLabel(number.toString());

	static DrawPanel d;
	static Pacman p;
	static JLabel gameOver;
	static JLabel score;
	
	
	public static void main(String[] args) {

		// Create a window and the canvas to draw onto.
		d = new DrawPanel();
		
		d.setLayout(new GridBagLayout());
		GridBagConstraints scoreConstraints = new GridBagConstraints();
		scoreConstraints.gridx = 0;
		scoreConstraints.gridy = 0;
		scoreConstraints.insets = new Insets(10, 10, 10, 10); // Add some margins
		scoreConstraints.anchor = GridBagConstraints.NORTHWEST; // align to top-left corner

		GridBagConstraints gameOverConstraints = new GridBagConstraints();
		gameOverConstraints.gridx = 0;
		gameOverConstraints.gridy = 1;
		gameOverConstraints.gridwidth = GridBagConstraints.REMAINDER; // span the whole row
		gameOverConstraints.fill = GridBagConstraints.HORIZONTAL; // fill the whole row
		gameOverConstraints.insets = new Insets(10, 10, 10, 10); // Add some margins
		gameOverConstraints.anchor = GridBagConstraints.CENTER; // center alignment


		gameOver = new JLabel(" ");
		gameOver.setFont(new Font("Verdana", Font.BOLD, 150));
		gameOver.setForeground(Color.RED);
		gameOver.setBounds(10, 200, 1000, 1000);
		//d.add(gameOver);
		//d.add(gameOver, constraints);
		d.add(gameOver, gameOverConstraints);

		score = new JLabel("Score: ");
		score.setBounds(90, 10, 30, 50);
		score.setFont(new Font("Arial", Font.BOLD, 15));
		score.setForeground(Color.WHITE);
		d.add(score);
		//d.add(score, scoreConstraints);

		num.setBounds(20, 10, 30, 50);
		num.setFont(new Font("Arial", Font.BOLD, 15));
		num.setForeground(Color.WHITE);
		d.add(num);

		// Create randomly-positioned trees.
		for (int i = 0; i < numTrees; i++) {
			Rectangle treeBox = new Rectangle((int) (Math.random() * DrawPanel.WIDTH),
					(int) (Math.random() * DrawPanel.HEIGHT), treeWidth + (int) (Math.random() * treeWobble),
					treeHeight + (int) (Math.random() * treeWobble));

			Tree t = new Tree(d.getCanvas(), treeBox);
			d.addDrawObject(t);
		}

		p = new Pacman(d.getCanvas());
		d.addDrawObject(p);
		d.addKeyListener(p);

		SnowFlake sf = null;

		int sum = 25;//

		// Start playing music
		MidiPlayer m = new MidiPlayer(musicFile);

		int count = 0;
		// Create the snowflakes.
		for (int i = 0; i < numSnowFlakes; i++) {

			sum += 5;

			sf = new SnowFlake(d.getCanvas(), sum);

			d.addDrawObject(sf);

			if (i <= 25) {
				try {
					// Allow existing snow flakes to fall a bit, before adding more
					Thread.sleep(6000);
				} catch (InterruptedException e) {

				}
			} else if (i <= 30) {
				try {
					// Increase the production of snow flakes to make the game more challenging
					Thread.sleep(1000);
				} catch (InterruptedException e) {

				}
			} else {
				try {
					// Increase the production of snow flakes to make the game more challenging
					Thread.sleep(600);
				} catch (InterruptedException e) {

				}
			}

			Integer increasedSnowFlakeNumber;

		}

	}

	public static void touched() {
		
		d.removeKeyListener(p);
		gameOver.setText("Game Over");
		score.setText(" ");
		num.setText(" ");
	}

}
