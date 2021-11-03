package com.company;

import java.util.Objects;

public class Hills
{
	public int Ants = 0;
	public int Termites = 0;
	public int Wasps = 0;

	private final int AntAtk = 1;
	private final int AntHp = 1;
	private final int TermiteAtk = 2;
	private final int TermiteHp = 1;
	private final int WaspAtk = 3;
	private final int WaspHp = 3;


	public void fite()
	{
		int totalAntAtk = Ants * AntAtk;
		int totalAntHp = Ants * AntHp;

		int totalTermiteAtk = Termites * TermiteAtk;
		int totalTermiteHp = Termites * TermiteHp;

		int totalWaspAtk = Wasps * WaspAtk;
		int totalWaspHp = Wasps * WaspHp;

		totalAntHp -= totalTermiteAtk + totalWaspAtk;
		totalTermiteHp -= totalAntAtk + totalWaspAtk;
		totalWaspHp -= totalAntAtk + totalTermiteAtk;

		if(totalAntHp < 0 )
		{
			Ants = 0;
		}
		else
		{
		Ants = (int)Math.ceil(totalAntHp/AntHp);
		}

		if(totalTermiteHp < 0 )
		{
			Termites = 0;
		}
		else
		{
		Termites = (int)Math.ceil(totalTermiteHp/TermiteHp);
		}

		if(totalWaspHp < 0)
		{
			Wasps = 0;
		}
		else
		{
		Wasps = (int)Math.ceil(totalWaspHp/WaspHp);
		}
	}

	public int CheckHill(String HiveName)
	{
		if(Objects.equals(HiveName, "AntHive"))
		{
			if(Ants > 0 )
			{
				return 1;
			}
			else
			{
				return 0;

			}
		}
		else if (Objects.equals(HiveName, "TermiteHive"))
		{
			if(Termites > 0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else if(Objects.equals(HiveName, "WaspHive"))
		{
			if(Wasps > 0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}



}