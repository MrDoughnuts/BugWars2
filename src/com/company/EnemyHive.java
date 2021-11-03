package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;


public class EnemyHive
{
	public String HiveName;
	public Hives EHive;

	int totalBugs;
	public int bugs;
	public int bugProduction;

	public EnemyHive(String hiveChoice)
	{
		EHive = new Hives();

		if(Objects.equals(hiveChoice, "AntHive"))
		{
			HiveName = "AntHive";
			EHive.AntHive();
		}
		else if(Objects.equals(hiveChoice, "TermiteHive"))
		{
			HiveName = "TermiteHive";
			EHive.TermiteHive();
		}
		else if (Objects.equals(hiveChoice, "WaspHive"))
		{
			HiveName = "WaspHive";
			EHive.WaspHive();
		}

		bugs = EHive.StartingBugs;
		totalBugs = bugs;
		bugProduction = EHive.BugProduction;
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
		totalBugs += bugProduction;
	}

	public void DivideAndConquer(Hills WeakestHill, Hills SecondWeakestHill, ArrayList<Hills> ListOfHills)
	{
		RecallAllBugs(ListOfHills);

		int attackForce = bugs/2;

		SendBugs(attackForce, WeakestHill);
		SendBugs(attackForce, SecondWeakestHill);
	}

	public void RecallAllBugs(ArrayList<Hills> ListOfHills)
	{
		for(Hills i: ListOfHills)
		{
			if(Objects.equals(HiveName, "AntHive"))
			{
				bugs += i.Ants;
				i.Ants = 0;
			}
			else if(Objects.equals(HiveName, "TermiteHive"))
			{
				bugs += i.Termites;
				i.Termites = 0;
			}
			else if(Objects.equals(HiveName, "WaspHive"))
			{
				bugs += i.Wasps;
				i.Wasps = 0;
			}

		}
	}

	public void DivideAndConquer2(@NotNull ArrayList<Hills> ListOfEmptyHills, ArrayList<Hills> listOfHills)
	{
		RecallAllBugs(listOfHills);
		int attackForce = totalBugs/ListOfEmptyHills.size();

		for(Hills i: ListOfEmptyHills)
		{
			SendBugs(attackForce, i);
		}

	}
}