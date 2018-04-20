package ftnhps.movieenthusiasts.places;

public class ChartDTO {

	public int  [][] data = {{1, 1, 2, 5, 4, 8},{100,50,5000,2525,1000,7777}};;
	public String [] lables  = {"July", "August","September","October","November","December"};
	
	public ChartDTO(int a)
	{
		this.data = new int [2][31];
		this.lables = new String[31];
	}
	public ChartDTO() {}
}
