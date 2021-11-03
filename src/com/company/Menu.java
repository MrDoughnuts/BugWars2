package com.company;

import java.util.*;

public class Menu 
{
	public Hills hill0 = new Hills();
	public Hills hill1 = new Hills();
	public Hills hill2 = new Hills();
	public Hills hill3 = new Hills();
	public Hills hill4 = new Hills();
	public Hills hill5 = new Hills();

	private PlayerHive PHive;
	private EnemyHive EHive;

	public ArrayList<Hills> ListOfHills = new ArrayList<>();

	public Menu() 
	{
		newGame();
		RunGame();
	}

	public void newGame() 
	{
		ListOfHills.add(hill0);
		ListOfHills.add(hill1);
		ListOfHills.add(hill2);
		ListOfHills.add(hill3);
		ListOfHills.add(hill4);
		ListOfHills.add(hill5);

		int HiveChoice = hiveChoice();
		PHive = new PlayerHive(HiveChoice);

		String enemyHiveChoice = hiveChoice2();
		EHive = new EnemyHive(enemyHiveChoice);

		System.out.println("enemyHive Choice : " + enemyHiveChoice);


	}

	public void RunGame()
	{
		boolean PlayerWin;
		boolean EnemyWin;

		while(true)
		{
			MenuSelection();
			PlayerWin = CheckForWin(PHive.HiveName);
			EnemyWin = CheckForWin(EHive.HiveName);

			if(PlayerWin)
			{
				System.out.println("Congrats you won!");
				break;
			}
			else if (EnemyWin)
			{
				System.out.println("Try Again Next Time!");
				break;
			}

		}

	}

	public int hiveChoice() 
	{
		Scanner input = new Scanner(System.in);
		int choice;

		System.out.println("enter 1 for Ants");
		System.out.println("enter 2 for Termites");
		System.out.println("enter 3 for Wasps");
		choice = input.nextInt();

		if(choice != 1 && choice != 2 && choice != 3)
		{
			System.out.println("Invalid input");
			System.exit(0);
		}
		return choice;	
	}

	public String hiveChoice2()
	{
		ArrayList<String> ListOfHives = new ArrayList<>();

		ListOfHives.add("AntHive");
		ListOfHives.add("TermiteHive");
		ListOfHives.add("WaspHive");

		ListOfHives.remove(PHive.HiveName);

		Random randNum = new Random();

		return ListOfHives.get(randNum.nextInt(2));
	}

	public void displayMenu() 
	{
		System.out.println("Your choices are:");
		System.out.println("1: Deploy Bugs");
		System.out.println("2: Recall Bugs");
		System.out.println("3: View all hills");
		System.out.println("4: End turn");
	}


	public void MenuSelection() 
	{
		Scanner input = new Scanner(System.in);

		int playerResponse = 0;

		while(playerResponse != 4)
		{
			displayMenu();
			playerResponse = input.nextInt();
			
			if (playerResponse == 1) 
			{
				DeployBugs();
			} 
			else if (playerResponse == 2) 
			{
				RecallBugs();
			} 
			else if (playerResponse == 3) 
			{
				ViewAllHills();
			} 
			else if (playerResponse == 4) 
			{
				EndTurn();
			}

		}

	}



	public void DeployBugs() 
	{
		Scanner input = new Scanner(System.in);

		System.out.println("which hill? (1-5)");
		int HillSelected = input.nextInt();

		System.out.println(HillSelected);
		System.out.println("How many bugs do you want to deploy?");
		int BugsSelected = input.nextInt();

		if (BugsSelected <= PHive.bugs) 
		{
			PHive.SendBugs(BugsSelected, ListOfHills.get(HillSelected));
		}
		else 
		{
			System.out.println("Please enter a valid number");
		}

	}

	public void RecallBugs()
	{
		Scanner input = new Scanner(System.in);

		System.out.println("which hill? (1-5)");
		int HillSelected = input.nextInt();

		System.out.println("How many bugs do you want to recall?");
		int BugsSelected = input.nextInt();

		PHive.recallBugs(BugsSelected,ListOfHills.get(HillSelected));
	}

	public void ViewAllHills()
	{
			System.out.println("_______________________");
			System.out.println("Player Hive (" + PHive.HiveName + "):");
			System.out.println("Bugs:" + PHive.bugs);
			System.out.println("_______________________");

			System.out.println("_______________________");
			System.out.println("Enemy Hive (" + EHive.HiveName + "):");
			System.out.println("_______________________");

			System.out.println("_______________________");
			System.out.println("Hill 1:" );
			System.out.println("Ants: " + hill1.Ants);
			System.out.println("Termites: " + hill1.Termites);
			System.out.println("Wasps: " + hill1.Wasps);
			System.out.println("_______________________");

			System.out.println("_______________________");
			System.out.println("Hill 2:");
			System.out.println("Ants: " + hill2.Ants);
			System.out.println("Termites: " + hill2.Termites);
			System.out.println("Wasps: " + hill2.Wasps);
			System.out.println("_______________________");

			System.out.println("_______________________");
			System.out.println("Hill 3:" );
			System.out.println("Ants: " + hill3.Ants);
			System.out.println("Termites: " + hill3.Termites);
			System.out.println("Wasps: " + hill3.Wasps);
			System.out.println("_______________________");

			System.out.println("_______________________");
			System.out.println("Hill 4:" );
			System.out.println("Ants: " + hill4.Ants);
			System.out.println("Termites: " + hill4.Termites);
			System.out.println("Wasps: " + hill4.Wasps);
			System.out.println("_______________________");

			System.out.println("_______________________");
			System.out.println("Hill 5:" );
			System.out.println("Ants: " + hill5.Ants);
			System.out.println("Termites: " + hill5.Termites);
			System.out.println("Wasps: " + hill5.Wasps);
			System.out.println("_______________________");
	}

	public void EndTurn() 
	{
		enemyTurn();

		for (Hills i: ListOfHills)
		{
			i.fite();
		}

		PHive.GenerateBugs();
		EHive.GenerateBugs();

	}

	public void enemyTurn()
	{
		
		if(CheckFor3())
		{
			ArrayList<Hills> ListOfWeakHills = CheckForWeakestHill(PHive.HiveName);
			EHive.DivideAndConquer(ListOfWeakHills.get(0), ListOfWeakHills.get(1), ListOfHills);
		}
		else
		{
			ArrayList<Hills> ListOfEmptyHills = CheckForEmptyHills(PHive.HiveName);
			EHive.DivideAndConquer2(ListOfEmptyHills, ListOfHills);
		}
	}

	public boolean CheckFor3()
	{
		int HillsOccupied = 0;

		for (Hills i : ListOfHills)
		{
			HillsOccupied += i.CheckHill(PHive.HiveName);
		}
		return HillsOccupied >= 3;
	}

	public ArrayList<Hills> CheckForWeakestHill(String HiveName)
	{
		ArrayList<Integer> ListOfBugs= new ArrayList<>();

		ArrayList<Hills> ListOfWeakHills = new ArrayList<>();

		if(Objects.equals(HiveName, "AntHive"))
		{
			for(Hills i : ListOfHills)
			{
				if(i != hill0)
				{
					ListOfBugs.add(i.Ants);
				}
			}
		}
		else if(Objects.equals(HiveName, "TermiteHive"))
		{
			for(Hills i : ListOfHills)
			{
				if(i != hill0)
				{
					ListOfBugs.add(i.Termites);
				}
			}
		}
		else if(Objects.equals(HiveName, "WaspHive"))
		{	
			for(Hills i : ListOfHills)
			{
				if(i != hill0)
				{
					ListOfBugs.add(i.Wasps);

				}
			}
		}

		int SmallestHillIndex = 0;
		int SecondSmallestHillIndex = 0;
		int tempBugs = 1000;

		for(int i = 1; i < ListOfBugs.size(); i++)
		{
			if(ListOfBugs.get(i) < tempBugs)
			{
				tempBugs = ListOfBugs.get(i);
				SmallestHillIndex = i;
			}
		}
		ListOfBugs.set(SmallestHillIndex, 1000);

		for(int i = 1; i < ListOfBugs.size(); i++)
		{
			if(ListOfBugs.get(i) < tempBugs)
			{
				tempBugs = ListOfBugs.get(i);
				SecondSmallestHillIndex = i;
			}
		}

		ListOfWeakHills.add(ListOfHills.get(SmallestHillIndex));
		ListOfWeakHills.add(ListOfHills.get(SecondSmallestHillIndex));

		return ListOfWeakHills;
	}

	public ArrayList<Hills> CheckForEmptyHills(String PHiveName)
	{

		ArrayList<Hills> ListOfEmptyHills = new ArrayList<>();

		if(Objects.equals(PHiveName, "AntHive"))
		{
			for(Hills i: ListOfHills)
			{
				if(i.Ants == 0 && i != hill0)
				{
					ListOfEmptyHills.add(i);
				}
			}
		}
		if(Objects.equals(PHiveName, "TermiteHive"))
		{
			for(Hills i: ListOfHills)
			{
				if(i.Termites == 0 && i != hill0)
				{
					ListOfEmptyHills.add(i);
				}
			}
		}
		if(Objects.equals(PHiveName, "WaspHive"))
		{
			for(Hills i: ListOfHills)
			{
				if(i.Wasps == 0 && i != hill0)
				{
					ListOfEmptyHills.add(i);
				}
			}
		}
		return ListOfEmptyHills;
	}

	public boolean CheckForWin(String HiveName)
	{
		int hillsControlled = 0;
		if(Objects.equals(HiveName, "AntHive"))
		{
			for (Hills i : ListOfHills)
			{
				if(i.Ants > 0 && i.Termites == 0 && i.Wasps == 0)
				{
					hillsControlled++;
				}
			}	
		}
		else if(Objects.equals(HiveName, "TermiteHive"))
		{
			for (Hills i : ListOfHills)
			{
				if(i.Ants == 0 && i.Termites > 0 && i.Wasps == 0)
				{
					hillsControlled++;
				}
			}	
		}
		else if(Objects.equals(HiveName, "WaspHive"))
		{
			for (Hills i : ListOfHills)
			{
				if(i.Ants == 0 && i.Termites == 0 && i.Wasps > 0)
				{
					hillsControlled++;
				}
			}	

		}

		return hillsControlled == 5;
	}
}