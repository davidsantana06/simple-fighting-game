package Projects.P01;

import java.util.Scanner;
import java.util.Random;

public class FightingGame {
	public static final int MAX_LIFE = 100, MAX_SPECIAL = 5;
	public static final int SWD_DMG = 8, SP_DMG = 20;

	public static Scanner input;
	public static Random random;

	public static String userAttack(String attributes) {
		String panel = attributes + "\n" + TextPattern.hyphens(TextPattern.maxLenght(attributes)) 
					 + "\n" + TextPattern.getMsg(TextPattern.ATTACK);
		String attack = "";
		boolean validAttack = false;
		while (!validAttack) {
			print(1, TextPattern.formatText(panel) + "\n_Attack: ");
			attack = input.nextLine();
			if (attack.equals("1") || attack.equals("2")) {
				validAttack = true;
			} else {
				print(2, TextPattern.getMsg(TextPattern.INV_INP));
			}
		}
		
		return attack;
	}

	public static String attributes(int userLife, int specialCounter, int enemyLife) {
		String attributes = "Your HP_: " + userLife + "/" + MAX_LIFE + "\n"
						  + "Special_: " + specialCounter + "/" + MAX_SPECIAL + "\n"
						  + "Enemy HP_: " + enemyLife;
		return attributes;
	}

	public static void battles() {
		int userLife = MAX_LIFE, specialCounter = MAX_SPECIAL;
		int enemyLevel = 1, enemyLife;

		boolean endBattles = false;
		while (!endBattles) {
			enemyLife = 10 + enemyLevel;
			print(2, TextPattern.formatText("Enemy #" + enemyLevel));

			while (userLife > 0) {
				int attack = Integer.parseInt(userAttack(attributes(userLife, specialCounter, enemyLife)));
				switch (attack) {
					case 1:
						enemyLife -= SWD_DMG;
						print(2, TextPattern.getMsg(TextPattern.CUT));
						break;
					case 2:
						enemyLife -= SP_DMG;
						specialCounter--;
						print(2, TextPattern.getMsg(TextPattern.SPECIAL));
				}

				if (enemyLife > 0) {
					int enemyAttack = random.nextInt(3) + 1;
					int damage = enemyAttack + (int)(enemyLevel / 10);
					if (enemyAttack == 4) {
						damage += (damage * 0.1);
					}
					userLife -= damage;

					print(2, TextPattern.formatText("Enemy done " + enemyAttack + " DMG!"));
				} else {
					print(2, TextPattern.formatText("Enemy Defeated!"));
					break;
				}
			}

			if (userLife > 0) {
				userLife += 5;
				if (userLife > MAX_LIFE) {
					userLife = MAX_LIFE;
				}

				if (enemyLevel % 10 == 0 && specialCounter < MAX_SPECIAL) {
					specialCounter++;
				}
			} else {
				endBattles = true;
			}

			clearTerminal();
			enemyLevel++;
		}
	}

	private static void print(int blankSpace, String text) {
		if (blankSpace == 0) {
			System.out.print(text);
		} else if (blankSpace == 1) {
			System.out.print("\n" + text);
		} else {
			System.out.print("\n" + text + "\n");
		}
	}

	private static void clearTerminal() {
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}

	public static void main(String args[]) {
		input = new Scanner(System.in);
		random = new Random();

		print(0, TextPattern.getMsg(TextPattern.INTRO) + "\n");
		battles();
		print(1, TextPattern.getMsg(TextPattern.GAME_OVER));
	}
}
