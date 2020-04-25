package Package1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Readmap
{
	private int level, mapX, mapY;
	private int[][] map = new int[20][20];
	FileReader reader;
	BufferedReader bufferReader;
	String str = "";
	int[] x;
	int num = 0;
	Readmap(int k)
	{
		level=k;
		String s;
		try
		{
			File file = new File("maps\\" + level + ".map");
			reader = new FileReader(file);
			bufferReader = new BufferedReader(reader);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		try
		{
			while ((s = bufferReader.readLine()) != null)
			{
				str = str + s;
				
			}
		}
		catch (IOException g)
		{
			System.out.println(g);
		}
		
		byte[] d = str.getBytes();
		int len = str.length();
		int[] x = new int[len];
		for(int i = 0; i < str.length(); i++) 
			x[i] = d[i] - 48;
		for(int i = 0; i<20; i++)
		{
			for(int j = 0; j<20; j++)
		    {
				map[i][j] = x[num];
		        if(map[i][j] == 5)
		        {
		        	mapY = i;
					mapX = j;
		        }
		        num++;
		    }
	    }
	}
	int[][] getmap(){return map;}
	int getmanX(){return mapX;}
	int getmanY(){return mapY;}
}
