package com.company;

public class Hives
{
	public int StartingBugs;
	public int BugProduction;

	public void AntHive()
	{
		StartingBugs = (int)Math.floor(Math.random()*(10-5+1)+5);
		BugProduction = 5;
	}

	public void TermiteHive()
	{
		StartingBugs = (int)Math.floor(Math.random() * (6 - 3 + 1) + 3);
		BugProduction = 3;
	}

	public void WaspHive()
	{
		StartingBugs = 2;
		BugProduction = 2;
	}
}