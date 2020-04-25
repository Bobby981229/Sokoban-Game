package Package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel1 extends JPanel implements KeyListener {
	public int step = 0, max = 6;
	int[][] map, data1;
	int manX, manY, boxnum;
	Image[] image;
	Readmap Levelmap;
	Readmap Levelmaptmp;
	int len = 45;
	public int level = 1;
	Stack returnStack = new Stack();
	

	Panel1() {
		setBounds(15, 85, 600, 600);
		setBackground(Color.white);
		addKeyListener(this);
		image = new Image[10];
		
		for (int i = 0; i < 10; i++) {
			image[i] = Toolkit.getDefaultToolkit().getImage("pic\\" + i + ".jpg");
		}
		setVisible(true);
	}

	void Sokoban(int i) {
		Levelmap = new Readmap(i);
		Levelmaptmp = new Readmap(i);
		map = Levelmap.getmap();
		manX = Levelmap.getmanX();
		manY = Levelmap.getmanY();
		data1 = Levelmaptmp.getmap();
		repaint();
	}

	int maxlevel() {
		return max;
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				g.drawImage(image[map[j][i]], i * len, j * len, this);
			}
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("This is the " + String.valueOf(level) + " level !", 10, 25);
		Main_Interface.lblStep.setText(String.valueOf(step));
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveup();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			movedown();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveleft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveright();
		}
		if (iswin()) {
			if (level == max) {
				JOptionPane.showMessageDialog(this, "Successful clearance, Game over£¡");
			} 
			else {
				String tip = "Successfully pass " + level + " level£¡ Whether to continue?" + "\n The final step is: " + step + "";
				int btn = JOptionPane.YES_NO_OPTION;
				String title = "Clearance";
				int choice = 0;
				choice = JOptionPane.showConfirmDialog(null, tip, title, btn);
				if (choice == 1)
					System.exit(0);
				else if (choice == 0) {
					step = 0;
					level++;
					Sokoban(level);
				}
			}
			returnStack.removeAllElements();
		}
		// Pass the value of steps to Main_Interface pages
		Main_Interface.lblStep.setText(String.valueOf(step));
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	boolean isMystackEmpty() {
		return returnStack.isEmpty();
	}

	Integer back() {
		return (Integer) returnStack.pop();
	}

	void remove() {
		returnStack.removeAllElements();
	}

	void moveup() {
		if (map[manY - 1][manX] == 2 || map[manY - 1][manX] == 4) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY - 1][manX] = 8;
			repaint();
			manY--;
			step++;
			returnStack.push(10);
		}
		else if (map[manY - 1][manX] == 3) {
			if (map[manY - 2][manX] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 9;
				repaint();
				manY--;
				step++;
				returnStack.push(11);
			}
			else if (map[manY - 2][manX] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 3;
				repaint();
				manY--;
				step++;
				returnStack.push(11);
			} else {
				map[manY][manX] = 8;
				repaint();
			}
		} 
		else if (map[manY - 1][manX] == 9) {
			if (map[manY - 2][manX] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 9;
				repaint();
				manY--;
				step++;
				returnStack.push(11);
			} 
			else if (map[manY - 2][manX] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 3;
				repaint();
				manY--;
				step++;
				returnStack.push(11);
			} else {
				map[manY][manX] = 8;
				repaint();
			}
		}
		if (map[manY - 1][manX] == 1) {
			map[manY][manX] = 8;
			repaint();
		}
	}

	void backup(int t) {
		int n = t;
		if (n == 10) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		}
		else if (n == 11) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (data1[manY - 1][manX] == 4 || data1[manY - 1][manX] == 9) {
				map[manY - 1][manX] = 4;
			} else
				map[manY - 1][manX] = 2;
		}
		map[manY + 1][manX] = 8;
		repaint();
		manY++;
		step--;
	}

	void movedown() {
		if (map[manY + 1][manX] == 2 || map[manY + 1][manX] == 4) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY + 1][manX] = 5;
			repaint();
			manY++;
			step++;
			returnStack.push(20);
		}
		else if (map[manY + 1][manX] == 3) {
			if (map[manY + 2][manX] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 9;
				repaint();
				manY++;
				step++;
				returnStack.push(21);
			} 
			else if (map[manY + 2][manX] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 3;
				repaint();
				manY++;
				step++;
				returnStack.push(21);
			} else {
				map[manY][manX] = 5;
				repaint();
			}
		} 
		else if (map[manY + 1][manX] == 9) {
			if (map[manY + 2][manX] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 9;
				repaint();
				manY++;
				step++;
				returnStack.push(21);
			} 
			else if (map[manY + 2][manX] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 3;
				repaint();
				manY++;
				step++;
				returnStack.push(21);
			} else {
				map[manY][manX] = 5;
				step++;
				repaint();
			}
		} 
		else if (map[manY + 1][manX] == 1) {
			map[manY][manX] = 5;
			repaint();
		}
	}

	void backdown(int t) {
		int n = t;
		if (n == 20) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		}
		else if (n == 21) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (data1[manY + 1][manX] == 4 || data1[manY + 1][manX] == 9) {
				map[manY + 1][manX] = 4;
			} else
				map[manY + 1][manX] = 2;
		}
		map[manY - 1][manX] = 5;
		repaint();
		manY--;
		step--;
	}

	void moveleft() {
		if (map[manY][manX - 1] == 2 || map[manY][manX - 1] == 4) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY][manX - 1] = 6;
			repaint();
			manX--;
			step++;
			returnStack.push(30);
		} 
		else if (map[manY][manX - 1] == 3) {
			if (map[manY][manX - 2] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 9;
				repaint();
				manX--;
				step++;
				returnStack.push(31);
			} 
			else if (map[manY][manX - 2] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 3;
				repaint();
				manX--;
				step++;
				returnStack.push(31);
			} else {
				map[manY][manX] = 6;
				repaint();
			}
		} 
		else if (map[manY][manX - 1] == 9) {
			if (map[manY][manX - 2] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 9;
				repaint();
				manX--;
				step++;
				returnStack.push(31);
			} 
			else if (map[manY][manX - 2] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 3;
				repaint();
				manX--;
				step++;
				returnStack.push(31);
			} else {
				map[manY][manX] = 6;
				repaint();
			}
		} 
		else if (map[manY][manX - 1] == 1) {
			map[manY][manX] = 6;
			repaint();
		}
	}

	void backleft(int t) {
		int n = t;
		if (n == 30) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		} 
		else if (n == 31) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (data1[manY][manX - 1] == 4 || data1[manY][manX - 1] == 9) {
				map[manY][manX - 1] = 4;
			} else
				map[manY][manX - 1] = 2;
		}
		map[manY][manX + 1] = 6;
		repaint();
		manX++;
		step--;
	}

	void moveright() {
		if (map[manY][manX + 1] == 2 || map[manY][manX + 1] == 4) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY][manX + 1] = 7;
			repaint();
			manX++;
			step++;
			returnStack.push(40);
		} 
		else if (map[manY][manX + 1] == 3) {
			if (map[manY][manX + 2] == 4) {
				if (data1[manY][manX] == 4)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 9;
				repaint();
				manX++;
				step++;
				returnStack.push(41);
			} 
			else if (map[manY][manX + 2] == 2) {
				if (data1[manY][manX] == 4)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 3;
				repaint();
				manX++;
				step++;
				returnStack.push(41);
			}else {
				map[manY][manX] = 7;
				repaint();
			}
		}
		else if (map[manY][manX + 1] == 9) {
			if (map[manY][manX + 2] == 4) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 9;
				repaint();
				manX++;
				step++;
				returnStack.push(41);
			}
			else if (map[manY][manX + 2] == 2) {
				if (data1[manY][manX] == 4 || data1[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 3;
				repaint();
				manX++;
				step++;
				returnStack.push(41);
			} else {
				map[manY][manX] = 7;
				repaint();
			}
		} 
		else if (map[manY][manX + 1] == 1) {
			map[manY][manX] = 7;
			repaint();
		}
	}

	void backright(int t) {
		int n = t;
		if (n == 40) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 4;
			}else
				map[manY][manX] = 2;
		}
		else if (n == 41) {
			if (data1[manY][manX] == 4 || data1[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (data1[manY][manX + 1] == 4 || data1[manY][manX + 1] == 9) {
				map[manY][manX + 1] = 4;
			} else
				map[manY][manX + 1] = 2;
		}
		map[manY][manX - 1] = 7;
		repaint();
		manX--;
		step--;
	}

	boolean iswin() {
		boolean flag = false;
		out: for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (data1[i][j] == 4 || data1[i][j] == 9)
					if (map[i][j] == 9)
						flag = true;
					else {
						flag = false;
						break out;
					}
			}
		return flag;
	}

}


