package org.example.Game;

import org.example.DeckOfCards.CardValuesInt;
import org.example.DeckOfCards.DeckOfCards;
import org.example.DeckOfCards.DeckOfCards.*;
import org.example.DeckOfCards.SortByCardNumber;
import org.example.Utility.InputScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack extends Game{
    private ArrayList<String> playersHand = new ArrayList<>();
    private ArrayList<String> dealersHand = new ArrayList<>();
    private int playerScore;
    private int dealerScore;
    private int playerIndex = 0;
    private int dealerIndex = 0;

    DeckOfCards cardCommands = new DeckOfCards();
    SortByCardNumber cardValue = new SortByCardNumber();

    public Blackjack() {
        super("Blackjack", blackjackRules());
    }

    public static String blackjackRules() {
        return "\nRules of Blackjack \n\n" +
                "Objective:\n    Get cards totaling closer to 21 than the dealer, without going over 21.\n" +
                "Card Values:\n    Cards 2-10 are worth their face value.\n" +
                "    Face cards (Jack, Queen, King) are each worth 10 points.\n" +
                "    Aces can count as 1 or 11 points.\n" +
                "Dealing:\n    You get two cards as does the dealer, with one of the dealers cards exposed.\n" +
                "    Hit: Take an additional card to get closer to 21.\n" +
                "    Stand: Stay with your current total.\n" +
                "Busting: \n" +
                "    If your hand goes above 21, its a bust and you lose.\n" +
                "Blackjack: \n" +
                "    If your hand hits 21 you win.\n" +
                "Dealer: \n" +
                "    The dealer will go after your turn.\n" +
                "    They will hit while below 17, or stand if on 17 and above.";
    }

    public void dealPlayersHand() {
        cardCommands.shuffleDeck();
        playersHand.add(cardCommands.dealCard());
    }

    public void turnPlayerHandIntoCards() {
        for (String card : playersHand) {
            if (card.startsWith("10")) {
                System.out.println(" ---------- \n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|    " + card + "   |\n" +
                        "|          |\n" +
                        "|          |\n" +

                        " ---------- ");
            } else {
                System.out.println(" ---------- \n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|    " + card + "    |\n" +
                        "|          |\n" +
                        "|          |\n" +

                        " ---------- ");
            }
        }
    }

    public void showPlayersHand() {
        System.out.println("\nYour hand: \n");
        turnPlayerHandIntoCards();
    }

    public void dealDealersHand() {
        dealersHand.add(cardCommands.dealCard());
    }

    public void turnDealerHandIntoCards() {
        for (int i = 0; i < dealersHand.size(); i++) {
            if (i == dealersHand.size() - 1) {
                System.out.println(" ---------- \n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|    "+ "? " + "    |\n" +
                        "|          |\n" +
                        "|          |\n" +

                        " ---------- ");
            } else {
                if (dealersHand.get(i).startsWith("10")) {
                    System.out.println(" ---------- \n" +
                            "|          |\n" +
                            "|          |\n" +
                            "|    "+ dealersHand.get(i) + "   |\n" +
                            "|          |\n" +
                            "|          |\n" +

                            " ---------- ");
                } else {
                    System.out.println(" ---------- \n" +
                            "|          |\n" +
                            "|          |\n" +
                            "|    "+ dealersHand.get(i) + "    |\n" +
                            "|          |\n" +
                            "|          |\n" +

                            " ---------- ");
                }
            }

        }
    }

    public void showDealerHand() {
        System.out.println("\nDealers hand: \n");
        turnDealerHandIntoCards();
    }

    public void showRoundStats() {
        for (int i = playerIndex; i < playersHand.size(); i++) {
            playerScore += CardValuesInt.matchValue(playersHand.get(i));
            playerIndex++;
        }
        for (int i = dealerIndex; i < dealersHand.size() - 1; i++) {
            dealerScore += CardValuesInt.matchValue(dealersHand.get(i));
            dealerIndex++;
        }
        System.out.println("\nPlayer score: " + playerScore);
        System.out.println("\nDealer score: " + dealerScore);

    }

    public void chooseHitOrStand() {
        System.out.println("Hit or Stand: ");
        Scanner scanner = InputScanner.useScanner();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("hit")) {
            chooseHit();
            if (playerScore > 21) {
                winOrLose();
            } else {
                chooseHitOrStand();
            }

        } else if (input.equalsIgnoreCase("stand")) {
            while (dealerScore < 17) {
                chooseStand();
            }
        }
        winOrLose();
    }

    public void chooseHit() {
        dealPlayersHand();
        showPlayersHand();
        showRoundStats();
        showDealerHand();
    }

    public void chooseStand() {
        showPlayersHand();
        dealDealersHand();
        showRoundStats();
        showDealerHand();
    }

    public void winOrLose() {
        if (playerScore > 21) {
            System.out.println("You lose!");
        } else if (21 - playerScore < 21 - dealerScore) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }
    }

    @Override
    public void printRules() {
        super.printRules();
    }

    @Override
    public void printTitle() {
        super.printTitle();
    }

    @Override
    public void play() {

    }

    @Override
    public boolean playAgain() {
        return false;
    }
}
