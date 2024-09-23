/***
 * Problem-Statement : Write a java code to build a "Cricket Match Scoring program."
 * Owner-Name : Aadhya Goyal
 * Date Of Creation : 17-09-24
 */

import java.util.Scanner;

public class CricketMatchScorer {

    private final Scanner scanner;
    private int TotalRuns;
    public int TotalOvers;
    private int TotalWickets;
    private int BallsInCurrentOver;
    private int team1TotalRuns;
    private final int team1TotalWickets;
    private int team2TotalRuns;
    private final int team2TotalWickets;
    private String TeamName;
    public String TeamSelect;

    public CricketMatchScorer() {
        this.scanner = new Scanner(System.in);
        this.TotalRuns = 0;
        this.TotalOvers = 0;
        this.TotalWickets = 0;
        this.BallsInCurrentOver = 0;
        this.team1TotalRuns = 0;
        this.team1TotalWickets = 0;
        this.team2TotalRuns = 0;
        this.team2TotalWickets = 0;
    }

    //Main method
    public static void main(String[] args) {
        CricketMatchScorer scorer = new CricketMatchScorer();

        while (true) {
            System.out.println(Constant.StartMessage);
            scorer.Team();
            scorer.BatOrBowl();
            scorer.Scoring();
            System.out.print(Constant.YesOrNoMessage);
            String cont = scorer.scanner.nextLine();
            if (!cont.equalsIgnoreCase("yes")) {
                System.out.println(Constant.Exit);
                break;
            }
        }
    }

    //Method to ask batting or bowling
    public void BatOrBowl() {

        System.out.println(Constant.Toss);
        System.out.print("Select a team first: ");
        TeamSelect = scanner.nextLine();
        System.out.print(Constant.BatOrBowl);
        String choice = scanner.nextLine().toLowerCase();
        while (!choice.equals("bat") && !choice.equals("bowl")) {
            System.out.println(Constant.InvalidBat);
            choice = scanner.nextLine().toLowerCase();
        }

        if (choice.equals("bat")) {
            System.out.println(TeamSelect + Constant.Bat);
        }
        else {
            System.out.println(TeamSelect + Constant.Bowl);
        }
    }

    //Method of scoring
    public void Scoring() {
        System.out.print(Constant.OverMessage);
        int Overs;

        while (true) {
            try {
                Overs = Integer.parseInt(scanner.nextLine());
                if (Overs > 0) {
                    break;
                }
                else {
                    System.out.println(Constant.Overs);
                }
            }
            catch (NumberFormatException exception) {
                System.out.println(Constant.InvalidInput);
            }
        }

        team1TotalRuns = Score(Overs);
        System.out.println("Team 1 Score: " + team1TotalRuns + "/" + TotalWickets);
        System.out.println(Constant.Target + (team1TotalRuns + 1));

        TotalWickets = 0;

        team2TotalRuns = Score(Overs);
        System.out.println("Team 2 Score: " + team2TotalRuns + "/" + TotalWickets);

        System.out.println(Constant.MatchFinished + team1TotalRuns + "/" + team1TotalWickets +
                "\n                                    Team 2 - " + team2TotalRuns + "/" + team2TotalWickets);

        if (team1TotalRuns > team2TotalRuns) {
            System.out.println( TeamName + " wins!");
        }
        else if (team2TotalRuns > team1TotalRuns) {
            System.out.println( TeamName + " wins!");
        }
        else {
            System.out.println("It's a tie!");
        }
    }

    private int Score(int totalOvers) {
        int runsScored = 0;
        for (int over = 1; over <= totalOvers; over++) {
            System.out.println("Starting Over: " + over);
            runsScored += Runs();
            System.out.println("Score after Over " + over + ": " + TotalRuns + "/" + TotalWickets);
        }
        TotalRuns = 0;
        return runsScored;
    }


    //Method  to Create Team
    public void Team() {
        System.out.print(Constant.CreateTeam);
        String Team1 = scanner.nextLine().toLowerCase();
        if (Team1.equals("existing")) {
            chooseExistingTeam();
            player11();
        }
        else if (Team1.equals("new")) {
            createNewTeam();
        }
        else {
            System.out.println(Constant.InvalidTeam);
            Team();
        }


        System.out.println(Constant.SecondTeam);
        String Team2 = scanner.nextLine().toLowerCase();
        if (Team2.equals("existing")) {
            chooseExistingTeam();
            player11();
        }
        else if (Team2.equals("new")) {
            createNewTeam();
        }
        else {
            System.out.println(Constant.InvalidTeam);
            Team();
        }
    }

    //Method Player11
    private void player11() {
        String[] playing11;

        while (true) {
            System.out.println(Constant.Playing11);
            String playingInput = scanner.nextLine();
            playing11 = playingInput.split(",");

            for (int i = 0; i < playing11.length; i++) {
                playing11[i] = playing11[i].trim();
            }

            if (playing11.length != 11) {
                System.out.println(Constant.Exact11);
                continue;
            }

            boolean validSelection = true;

            for (String player : playing11) {
                boolean found = false;
                for (String availablePlayer : Constant.A) {
                    if (availablePlayer.trim().equalsIgnoreCase(player)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Player \"" + player + Constant.Found);
                    validSelection = false;
                    break;
                }
            }

            if (validSelection) {
                System.out.println(Constant.ValidSelect);
                for (String player : playing11) {
                    System.out.println(player);
                }
                break;
            }
        }
    }

    //Method To Choose An Existing Team
    private void chooseExistingTeam() {
        System.out.println(Constant.ChooseTeam);

        int teamChoice = -1;
        while (teamChoice < 1 || teamChoice > 2) {
            try {
                teamChoice = Integer.parseInt(scanner.nextLine());
                if (teamChoice < 1 || teamChoice > 2) {
                    System.out.println(Constant.TeamChoice);
                }
            }
            catch (NumberFormatException exception) {
                System.out.println("Please enter a number.");
            }
        }

        String[] players = switch (teamChoice) {
            case 1 -> (Constant.A);
            case 2 -> (Constant.B);
            default -> new String[0];
        };

        System.out.println("You have chosen Team " + (char) ('A' + teamChoice - 1));
        System.out.println("Players in " + (char) ('A' + teamChoice - 1) + ":");
        for (String player : players) {
            System.out.println(player);
        }
    }

    //Method to create a new team
    private void createNewTeam() {
        int playerInput;
        String[] playerNames;

        do {
            System.out.print(Constant.CreateTeamMessage);
            String input = scanner.nextLine();
            System.out.print(Constant.TeamName);
            TeamName = scanner.nextLine();
            try {
                playerInput = Integer.parseInt(input);
                if (playerInput < 11 || playerInput > 15) {
                    System.out.print(Constant.InvalidMessage);
                    playerInput = 0;
                }
            }
            catch (NumberFormatException e) {
                System.out.print(Constant.InvalidInput);
                playerInput = 0;
            }
        } while (playerInput < 11 || playerInput > 15);

        playerNames = new String[playerInput];
        System.out.println(Constant.EnterName);
        for (int i = 0; i < playerInput; i++) {
            System.out.print((i + 1) + ": ");
            playerNames[i] = scanner.nextLine();
        }

        String[] selectedPlayers;
        boolean validSelection = true;
        do {
            System.out.println(Constant.Playing11);
            String playingInput = scanner.nextLine();
            selectedPlayers = playingInput.split(",");

            for (int i = 0; i < selectedPlayers.length; i++) {
                selectedPlayers[i] = selectedPlayers[i].trim();
            }

            if (selectedPlayers.length != 11) {
                System.out.println(Constant.Exact11);
                validSelection = false;
            }
            else {
                for (String player : selectedPlayers) {
                    boolean found = false;
                    for (String playerName : playerNames) {
                        if (player.equalsIgnoreCase(playerName)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Player \"" + player + Constant.Found);
                        validSelection = false;
                        break;
                    }
                }
            }

            if (validSelection) {
                System.out.println(Constant.ValidSelect);
                for (String player : selectedPlayers) {
                    System.out.println(player);
                }
            }
        } while (!validSelection);
        System.out.println(Constant.Substitute + (playerInput - 11));
    }

    //Method to calculate Runs
    public void CountRuns(int runs) {
        if (runs < 0) {
            System.out.println(Constant.InvalidRun);
        }
        TotalRuns += runs;
    }

    public int Runs() {
        int runsInOver = 0;
        System.out.println(Constant.RunInput);
        for (int i = 1; i <= 6; ) {
            System.out.print("Ball " + i + ": ");
            String runInput = scanner.nextLine().trim();

            switch (runInput.toLowerCase()) {
                case "wide" -> {
                    TotalRuns++;
                    runsInOver++;
                    System.out.println(Constant.Wide);
                }
                case "no ball" -> {
                    TotalRuns++;
                    runsInOver++;
                    System.out.println(Constant.NoBall);
                    System.out.print(Constant.RunHit);
                    String run = scanner.nextLine();
                }
                case "wicket" -> {
                    System.out.println(Constant.Wicket);
                    String player = scanner.nextLine();
                    TotalWickets++;
                    i++;
                }
                case "rain" -> {
                    System.out.println(Constant.Rain);
                }
                case "break" -> {
                    System.out.println("Break Time");
                }
                default -> {
                    try {
                        int runs = Integer.parseInt(runInput);

                        if (runs >= 0 && runs <= 6) {
                            runsInOver += runs;
                            CountRuns(runs);
                            BallsInCurrentOver++;
                            i++;
                            if (runs == 1 || runs == 3 || runs == 5) {
                                System.out.println(Constant.StrikeChange);
                            }
                            if (BallsInCurrentOver > 6) {
                                System.out.println(Constant.StrikeChange);
                            }
                        }
                        else {
                            System.out.println(Constant.Invalidrun);
                        }
                    }
                    catch (NumberFormatException exception) {
                        System.out.println(Constant.InvalidInput);
                    }
                }
            }

            if (BallsInCurrentOver >= 6) {
                TotalOvers++;
                BallsInCurrentOver = 0;
                System.out.println(Constant.OverCompleted);
                break;
            }
        }
        return runsInOver;
    }
}