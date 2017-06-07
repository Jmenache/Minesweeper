package ex;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class MineButton extends JButton
{
	private MineFrame mineFrame;
	private String icon = "?";
	private int x;
	private int y;
	
	//gives each button a unique x and y and sets its icon
	public MineButton(int xVal, int yVal, MineFrame mF)
	{
		mineFrame = mF;
		x = xVal;
		y = yVal;
		setFont(new Font("Comic Sans MS", Font.BOLD, 42));
		setText(icon);
		addMouseListener(new MouseHandler());
	}
	
	//if certain button is clicked then it's either a reveal or flag
	//if the game is lost display losing window, else display win
	private class MouseHandler extends MouseAdapter
	{	
		public void mousePressed(MouseEvent e)
		{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				mineFrame.getModel().revealSquare(x, y);
			}
			else if (e.getButton() == MouseEvent.BUTTON3)
			{
				mineFrame.getModel().flagSquare(x, y);
			}
			mineFrame.repaint();
			
			if (mineFrame.getModel().getStatus())
			{
				JOptionPane.showMessageDialog(mineFrame, "YOU LOST. GET GOOD.");
				System.exit(0);
			}
			if (mineFrame.getModel().getWin())
			{
				JOptionPane.showMessageDialog(mineFrame, "YOU WON. GET LOST.");
				System.exit(0);
			}
		}
	}
	
	//updates the buttons
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(mineFrame.getModel().getMine(x, y) == mineFrame.getModel().FLAG || 
		   mineFrame.getModel().getMine(x, y) == mineFrame.getModel().MINE_FLAG)
		{
			setText("F");
		}
		else if (mineFrame.getModel().getMine(x, y) == mineFrame.getModel().MINE &&
				 mineFrame.getModel().getStatus())
		{
			setText("!");
		}
		else if (mineFrame.getModel().getMine(x, y) == mineFrame.getModel().SAFE)
		{
			setText("" + mineFrame.getModel().getNeighbor(x, y));
		}
		else if (mineFrame.getModel().getMine(x, y) == mineFrame.getModel().MINE || 
				 mineFrame.getModel().getMine(x, y) == mineFrame.getModel().NO_MINE)
		{
			setText("?");
		}
	}
}
