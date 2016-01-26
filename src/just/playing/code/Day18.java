package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day18 {

	private static int [][] lights = new int[102][102];
	
	
	static {
		init();
	}
	
	public static void main(String[] args) {
//		System.out.println(countLightsOn());
//		System.out.println(countNeighborsOn(3, 2));
//		System.out.println(countNeighborsOn(1, 1));
		System.out.println(countNeighborsOn(1, 3));
		for(int i = 0; i < 100; i++) {
			printLights();
			System.out.println("-----------------");
			newGeneration();
		}
		System.out.println(countLightsOn());
	}
	
	private static int countLightsOn() {
		int rez = 0;
		for(int i = 1; i < lights.length; i++)
			for(int j = 1; j < lights[0].length; j++)
				rez += lights[i][j];
		return rez;
	}
	private static void init() {
		try{
			Path path = Paths.get("", "files/day18");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			int rowNumber = 1;
			while((line = br.readLine()) != null) {
				String[] lineInstruction = line.split("");
				int colNumber = 1;
				for(String s : lineInstruction) {
					if(s.equals("#")) {
						lights[rowNumber][colNumber] = 1;
					}
					colNumber++;
				}
				rowNumber++;
			}
			lights[1][1] = 1;lights[1][100] = 1;lights[100][1] = 1;lights[100][100] = 1;
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	private static void newGeneration() {
		int[][] newLights = new int[lights.length][lights[0].length];
		for(int i = 1; i < lights.length - 1; i++) {
			for(int j = 1; j < lights[0].length - 1; j++) {
				if((i == 1 && j == 1) || (i == 1 && j == 100) || (i == 100 && j == 1) || (i == 100 && j == 100)) newLights[i][j] = 1;
				else if(1 == lights[i][j] && (countNeighborsOn(i, j) == 2 || countNeighborsOn(i, j) == 3)) newLights[i][j] = 1;
				else if(0 == lights[i][j] && countNeighborsOn(i, j) == 3) newLights[i][j] = 1;
				else newLights[i][j] = 0;
			}
		}
		lights = newLights;
	}
	
	private static int countNeighborsOn(int row, int col) {
		int rez = 0;
		rez += (lights[row - 1][col - 1] + lights[row - 1][col] + lights[row - 1][col + 1]);
		rez += (lights[row][col - 1] + lights[row][col + 1]);
		rez += (lights[row + 1][col - 1] + lights[row + 1][col] + lights[row + 1][col + 1]);
		return rez;
	}
	
	private static void printLights() {
		for(int i = 1; i < lights.length - 1; i++) {
			for(int j = 1; j < lights[0].length - 1; j++) {
				System.out.print(lights[i][j]);
			}
			System.out.println();
		}
	}
}
