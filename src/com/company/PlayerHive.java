package com.company;

import java.util.Objects;

public class PlayerHive
{
	public String HiveName;
	public Hives PHive;

	public int bugs;
	public int bugProduction;

	public PlayerHive(int HiveChoice)
	{
			PHive = new Hives();
			if (HiveChoice == 1)
			{
				HiveName = "AntHive";
				PHive.AntHive();
			}
			else if(HiveChoice == 2)
			{
				HiveName = "TermiteHive";
				PHive.TermiteHive();
			}
			else if(HiveChoice == 3)
			{
				HiveName = "WaspHive";
				PHive.WaspHive();
			}

			bugs = PHive.StartingBugs;
			bugProduction = PHive.BugProduction;

	}

	public void SendBugs(int NumOfBugs, Hills HillSelected) 
	{
		if(Objects.equals(HiveName, "AntHive"))
		{
			HillSelected.Ants += NumOfBugs;
		}
		else if(Objects.equals(HiveName, "TermiteHive"))
		{
			HillSelected.Termites += NumOfBugs;
		}
		else if(Objects.equals(HiveName, "WaspHive"))
		{
			HillSelected.Wasps += NumOfBugs;
		}

		bugs -= NumOfBugs;
	}

	public void GenerateBugs()
	{
		bugs += bugProduction;
	}

	public void recallBugs(int NumOfBugs, Hills Hill)
	{
		if(Objects.equals(HiveName, "AntHive"))
		{
			if(Hill.Ants >= NumOfBugs)
			{
				Hill.Ants -= NumOfBugs;
			}
			else
			{
				NumOfBugs = 0;
			}
		}
		else if(Objects.equals(HiveName, "TermiteHive"))
		{
			if(Hill.Termites >= NumOfBugs)
			{
				Hill.Termites -= NumOfBugs;
			}
			else
			{
				NumOfBugs = 0;
			}
		}
		else if(Objects.equals(HiveName, "WaspHive"))
		{
			if(Hill.Wasps >= NumOfBugs)
			{
				Hill.Wasps -= NumOfBugs;
			}
			else
			{
				NumOfBugs = 0;
			}
		}

		bugs += NumOfBugs;
		
	}
}