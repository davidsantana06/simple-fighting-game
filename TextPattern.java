package Projects.P01;

public class TextPattern {
    public final static int INTRO = 0, ATTACK = 1, CUT = 2, SPECIAL = 3, INV_INP = 4, GAME_OVER = 5;

    public static String getMsg(int idx) {
        String msg = "";

        switch (idx) {
            case INTRO:
                msg = "J_ BATTLE GAME ~~~~~~~~~~~~~~~~\n"
                    + "~~~~~~~~~~~~~~ by David Santana";
                break;
            case ATTACK:
                msg = "> [1] Sword   \n"
                    + "> [2] XSpecial\n";
                break;
            case CUT:
                msg = "Nice Cut!";
                break;
            case SPECIAL:
                msg = "Special Attack Inflicted!";
                break;
            case INV_INP:
                msg = "Invalid Input!";
                break;
            case GAME_OVER:
                msg = "You Have Been Defeated! Game Over";
        }

        if (idx == ATTACK) {
            return msg;
        } else{
            return formatText(msg);
        }
    }

    public static String formatText(String text) {
        String[] strArray = text.split("\n");
        int maxLenght = maxLenght(strArray);

        String format = "| %-" + maxLenght + "s |";
        String hyphens = "|-" + "-".repeat(maxLenght) + "-|";
        String formatedText = "";

        for (String str : strArray) {
            formatedText += String.format(format, str) + '\n';
        }

        return (hyphens + "\n" + formatedText + hyphens);
    }

    public static String hyphens(int lenght) {
        return ("-".repeat(lenght));
    }

    private static int maxLenght(String[] strArray) {
        int maxLenght = 0;
        for (String str : strArray) {
            if (str.length() > maxLenght) {
                maxLenght = str.length();
            }
        }

        return maxLenght;
    }

    public static int maxLenght(String text) {
        String[] strArray = text.split("\n");
        int maxLenght = 0;
        for (String str : strArray) {
            if (str.length() > maxLenght) {
                maxLenght = str.length();
            }
        }

        return maxLenght;
    }
}
