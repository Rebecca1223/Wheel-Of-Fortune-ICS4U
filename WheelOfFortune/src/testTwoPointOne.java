
import javax.swing.JOptionPane;
import java.util.Random; //importing RNG method
import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(null, "Message here ",null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (n==0) {
            System.out.println ("this works");
        }

 */
/**
 *
 * @author acceb
 */
public class testTwoPointOne extends javax.swing.JFrame {

    public static Object[] options = {"OK"};
    public static char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static String[] phrases = {"PHILLY CHEESE STEAK", "SAUSAGE AND EGGDS", "BOWL OF RAMEN", "BAKED POTATO", "CHICKEN WINGS", "BUCKET OF CHICKEN", "ONION RINGS", "BIRTHDAY CAKE", "CARROT CAKE", "SPONGE CAKE", "BURGER AND FRIES", "FISH AND CHIPS", "BARBEQUE CHIPS", "TOMATO CHIPS", "TURKEY DINNER", "PIZZA AND DIP", "FRIED RICE", "BUBBLE TEA", "HOT CHOCOLATE", "MEXICAN BURRITO", "HARD SHELL TACO", "CHICKEN NUGGETS", "CHOCOLATE FUDGE", "STRAWBERRY WAFFLE", "EGG TARTS"};
    public static int[] spinner = {500, 520, 540, 560, 580, 600, 620, 640, 660, 680, 700, 720, 740, 760, 780, 800, 820, 840, 860, 880, 900, 0, 0, 10, 3000};
    public static boolean enter[] = new boolean[26]; //boolean array used when checking if letter has been guessed already
    public static Random randGen = new Random(); //random number generator declaration
    public static char[] phraseC;
    public static char[] phraseS;

    public static int rngPhrase, userTotal = 0, computerTotal = 0, rngSpin, type, gain, numberC;
    public static String phrase, guess, display = "", board, reveal;
    public static char guessL;
    public static boolean tof;

    /**
     * Creates new form testTwo
     */
    public testTwoPointOne() {
        initComponents();
        GuessConsonant.setEnabled(false);
        GuessVowel.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Submit.setEnabled(false);

        Wheel.setEditable(false);
        Guess.setEditable(false);
        UserMoney.setEditable(false);
        CompMoney.setEditable(false);
        Phrase.setEditable(false);
        Instructions.setEditable(false);

        hideAll();
        pnlStart.setVisible(true);
    }

    // Shows winner in end screen
    public void showWinner() {
        String strWinnerOutput = "<html><center>";
        if (userTotal > computerTotal) {
            strWinnerOutput += "Winner: User<br/>";
        } else if (computerTotal < userTotal) {
            strWinnerOutput += "Winner: Computer<br/>";
        } else {
            strWinnerOutput += "Tie!<br/>";
        }
        strWinnerOutput += "Thank you for playing Wheel of Fortune!</center></html>";
        lblEndGameText.setText(strWinnerOutput);
    }

    // Hides all screens
    public void hideAll() {
        pnlStart.setVisible(false);
        pnlInstructions.setVisible(false);
        pnlGame.setVisible(false);
        pnlEnd.setVisible(false);
        pnlGameResult.setVisible(false);
    }

    // Generates text file as game report
    public void generateReport() {
        // src: https://tdsb.elearningontario.ca/d2l/le/lessons/16360232/topics/125463885
        String strEndResult = "";
        try {
            OutputStream fout = new FileOutputStream("gameReport.xml");
            OutputStream bout = new BufferedOutputStream(fout);
            OutputStreamWriter out
                    = new OutputStreamWriter(bout, "8859_1");

            out.write("<?xml version=\"1.0\" ");
            out.write("encoding=\"ISO-8859-1\"?>\r\n");
            out.write("<game>\r\n");

            out.write("<result>\r\n");
            if (userTotal > computerTotal) {
                out.write("<winner>User</winner>\r\n");
            } else if (computerTotal < userTotal) {
                out.write("<winner>Computer</winner>\r\n");
            } else {
                out.write("<winner>Tie</winner>\r\n");
            }
            out.write("</result>\r\n");

            out.write("<player>\r\n");
            out.write("<name>User</name>\r\n");
            out.write("<balance>" + Integer.toString(userTotal) + "</balance>\r\n");
            out.write("</player> \r\n");

            out.write("<player>\r\n");
            out.write("<name>Computer</name>\r\n");
            out.write("<balance>" + Integer.toString(computerTotal) + "</balance>\r\n");
            out.write("</player> \r\n");
            out.write("</game>\r\n");

            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(
                    "This VM does not support the Latin-1 character set."
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        // src: https://mkyong.com/java/how-to-read-xml-file-in-java-jdom-example/
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("gameReport.xml");
        
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list1 = rootNode.getChildren("result");
            List list2 = rootNode.getChildren("player");

            for (int i = 0; i < list1.size(); i++) {
               Element node = (Element) list1.get(i);

               strEndResult += "Winner: " + node.getChildText("winner") + "\n";
            }
            
            for (int j = 0; j < list2.size(); j++) {
               Element node = (Element) list2.get(j);

               strEndResult += node.getChildText("name") + " balance: " + (node.getChildText("balance")) + "\n";
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        
        /*if (userTotal > computerTotal) {
            strEndResult += "Winner: User\n";
        } else if (computerTotal < userTotal) {
            strEndResult += "Winner: Computer\n";
        } else {
            strEndResult += "Tie!\n";
        }
        strEndResult += "Player balance: " + Integer.toString(userTotal) + "\nComputer balance: " + Integer.toString(computerTotal);
        */
        txtEndResult.setText(strEndResult);
    }

    public void userPlay() {
        UserMoney.setText(Integer.toString(userTotal));
        CompMoney.setText(Integer.toString(computerTotal));

        rngSpin = randGen.nextInt(25);
        if (0 <= rngSpin && rngSpin <= 20) { //condition, if met, proceed
            Wheel.setText("You landed on $" + spinner[rngSpin] + " amount!");
            System.out.println("You landed on $" + spinner[rngSpin] + " amount!"); //Print this
        } else if (rngSpin == 21 || rngSpin == 22) { //condition, if met, proceed
            Wheel.setText("You went bankrupt");
            userTotal = 0;
            UserMoney.setText(Integer.toString(userTotal));
            computer();
            System.out.println("You went bankrupt"); //Print this
//      break;
        } else if (rngSpin == 23) { //condition, if met, proceed
            Wheel.setText("You landed on skip a turn");
            computer();
            System.out.println("You landed on skip a turn"); //Print this
//      break;
        } else if (rngSpin == 24) { //condition, if met, proceed
            Wheel.setText("You landed on a top dollar value of $" + spinner[24] + " amount!");
            System.out.println("You landed on a top dollar value of $" + spinner[24] + " amount!"); //Print this
        }

        type = 0;
        Instructions.setText("Would you like to buy a vowel, guess a consonant, or guess the phrase?");
        if (userTotal > 249) {
            GuessVowel.setEnabled(true);
            GuessConsonant.setEnabled(true);
            GuessPhrase.setEnabled(true);
        } else {
            GuessConsonant.setEnabled(true);
            GuessPhrase.setEnabled(true);
        }
    }

    public void computer() {
        rngSpin = randGen.nextInt(25);
        if (rngSpin == 21 || rngSpin == 22) {
            computerTotal = 0;
            int n = JOptionPane.showOptionDialog(null, "The computer went bankrupted", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (n == 0) {
                CompMoney.setText(Integer.toString(computerTotal));
                userPlay();
            }
        } else if (rngSpin == 23) {
            int n = JOptionPane.showOptionDialog(null, "The computer skipped its turn", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (n == 0) {
                userPlay();
            }
        } else if ((0 <= rngSpin && rngSpin <= 20) || rngSpin == 24) {
            int compChoice = randGen.nextInt(4) + 1;
            if (1 <= compChoice && compChoice < 4) {
                int cLetter = randGen.nextInt(5) + 1; //assigns RNG number as value for variable
                if (cLetter == 5 && computerTotal > 249) {
                    computerTotal = computerTotal - 250;
                    CompMoney.setText(Integer.toString(computerTotal));
                    int rngVowel = randGen.nextInt(5);
                    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
                    char vowel = vowels[rngVowel];
                    display = "The computer chose to buy a vowel. \n It wishes to buy: " + vowel + "\n";
                    tof = Methods.validateC(phrase, vowel, alphabet, enter, phraseS);
                    if (tof) { //condition, if tof is true, proceed
                        display = display + "That letter has already been guessed. \n The Computer lost its turn \n\n Your turn!";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            userPlay();
                        }
                    } else {
                        Methods.record(vowel, enter, alphabet); //uses record method
                        Methods.gameBoard(phrase, phraseC, phraseS, vowel, spinner, rngSpin);

                        if (numberC > 0) { //condition, if met, proceed
                            display = display + "There are " + numberC + " of those letters!";
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                if (Methods.boardFinish(phrase, phraseS)) {
                                    int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                    if (x == 0) {
                                        //go to end game screen
                                        System.out.println("Game over");
                                        hideAll();
                                        pnlEnd.setVisible(true);
                                        showWinner();
                                    }
                                } else {
                                    reveal = String.valueOf(phraseS);
                                    Phrase.setText(reveal);
                                    computer();
                                }
                            }
                        } else if (numberC == 0) {  //condition, if met, proceed
                            System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                            display = display + "That letter does not appear in this phrase. \n\n Your turn!";
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                userPlay();
                            }
                        }
                    }
                } else {
                    display = "The Wheel landed on: $" + spinner[rngSpin] + "\n The Computer chooses to guess a consonant \n";
                    int rngAlpha = randGen.nextInt(21); //assigns RNG number as value for variable
                    char consonant[] = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'}; //char array declaration and stores all consonant in it
                    char guessC = consonant[rngAlpha];
                    display = display + "The Computer guesses: " + guessC + "\n";

                    tof = Methods.validateC(phrase, guessC, alphabet, enter, phraseS); //stores return value of validateC method as value for variable
                    if (tof) { //condition, if tof is true, proceed
                        display = display + "That letter has already been guessed. \n The Computer lost its turn \n\n Your turn!";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            userPlay();
                        }
                    } else {
                        Methods.record(guessC, enter, alphabet); //uses record method
                        Methods.gameBoard(phrase, phraseC, phraseS, guessC, spinner, rngSpin);

                        if (numberC > 0) { //condition, if met, proceed
                            gain = numberC * spinner[rngSpin];
                            display = display + "There are " + numberC + " of those letters! \n The Computer gained " + gain + " dollars!";;
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                computerTotal = computerTotal + gain;
                                CompMoney.setText(Integer.toString(computerTotal));
                                if (Methods.boardFinish(phrase, phraseS)) {
                                    int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                    if (x == 0) {
                                        //go to end game screen
                                        System.out.println("Game over");
                                        hideAll();
                                        pnlEnd.setVisible(true);
                                        showWinner();
                                    }
                                } else {
                                    reveal = String.valueOf(phraseS);
                                    Phrase.setText(reveal);
                                    computer();
                                }
                            }
                        } else if (numberC == 0) {  //condition, if met, proceed
                            System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                            display = display + "That letter does not appear in this phrase. \n\n Your turn!";
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                userPlay();
                            }
                        }
                    }
                }
            } else if (compChoice == 4) {
                int rngPhraseC = randGen.nextInt(25); //stores RNG number as value for variable
                String compPhrase = phrases[rngPhraseC]; //variable declaration and stores index rngPhraseC of phrases array as value for variable
                display = "The Computer chose to guess the phrase \n It guesses: " + compPhrase + "\n";
                if (compPhrase.equalsIgnoreCase(phrase)) {
                    display = display + "It was the correct phrase! \n The computer gained $1000 \n\n Game Over";
                    computerTotal = computerTotal + 1000;
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        //go to end game screen
                        System.out.println("Game Over");
                        hideAll();
                        pnlEnd.setVisible(true);
                        showWinner();
                    }
                } else {
                    display = display + "It was the incorrect phrase. \n Your turn!";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        userPlay();
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new javax.swing.JPanel();
        pnlStart = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        btnInstructions = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlInstructions = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlGame = new javax.swing.JPanel();
        Play = new javax.swing.JButton();
        GuessPhrase = new javax.swing.JButton();
        GuessVowel = new javax.swing.JButton();
        UserMoney = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Guess = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        GuessConsonant = new javax.swing.JButton();
        Instructions = new javax.swing.JTextField();
        Wheel = new javax.swing.JTextField();
        Phrase = new javax.swing.JTextField();
        CompMoney = new javax.swing.JTextField();
        Submit = new javax.swing.JButton();
        pnlEnd = new javax.swing.JPanel();
        lblEndGameText = new javax.swing.JLabel();
        btnGenerateReport = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlGameResult = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEndResult = new javax.swing.JTextArea();
        btnExitGameResult = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlStart.setBackground(new java.awt.Color(234, 234, 255));
        pnlStart.setMaximumSize(new java.awt.Dimension(601, 273));
        pnlStart.setPreferredSize(new java.awt.Dimension(601, 273));

        lblTitle.setFont(new java.awt.Font("Stencil", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 0));
        lblTitle.setText("WHEEL OF FORTUNE");

        btnPlay.setBackground(new java.awt.Color(204, 153, 0));
        btnPlay.setForeground(new java.awt.Color(153, 51, 0));
        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnInstructions.setBackground(new java.awt.Color(204, 153, 0));
        btnInstructions.setForeground(new java.awt.Color(153, 51, 0));
        btnInstructions.setText("Instructions");
        btnInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstructionsActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel8.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 51, 0));
        jLabel8.setText("Welcome To...");

        jLabel9.setText("ICS4U");

        javax.swing.GroupLayout pnlStartLayout = new javax.swing.GroupLayout(pnlStart);
        pnlStart.setLayout(pnlStartLayout);
        pnlStartLayout.setHorizontalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8))
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitle)
                    .addGroup(pnlStartLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlStartLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnPlay)
                                .addGap(29, 29, 29)
                                .addComponent(btnInstructions)))))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        pnlStartLayout.setVerticalGroup(
            pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStartLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInstructions)
                    .addComponent(btnPlay))
                .addGap(146, 146, 146))
        );

        pnlInstructions.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setBackground(new java.awt.Color(204, 102, 0));
        jButton1.setForeground(new java.awt.Color(153, 51, 0));
        jButton1.setText("Return to main menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setForeground(new java.awt.Color(153, 51, 0));
        jButton2.setText("Start game");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("<html><center><b>Tutorial</b><br/><br/>Wheel of Fortune begins with a start screen with two buttons: “Start Game” and “Instructions”. “Start Game” will start the game and take you to the main game screen. “Instructions” will take you to a screen displaying instructions on how to play the game.<br/><br/>The main game screen will have five buttons: “Play”, “Buy a Vowel”, “Guess a Consonant”, “Guess the Phrase”, and “Submit Guess”.<br/><br/>Clicking “Play” will start the game with your turn, which will display the result of the spinning wheel. If you have received a monetary value, it will be displayed below and you will be allowed to click either “Buy a Vowel” *, “Guess a Consonant” or “Guess the Phrase”. Once you have selected your choice the game will progress.<br/><br/>If you choose to buy a vowel for a set amount of money, the set amount will be deducted from your overall balance. You will then be prompted to enter any vowel that you choose. If the selected vowel is in the phrase, the vowel will be shown. If not, all players will be notified that the vowel is not in the phrase.<br/><br/>If you have chosen to guess a consonant, you will be prompted to enter your letter or answer into the answer field and click “Submit Guess”. If your letter is in the phrase, it will be revealed, otherwise it won’t. If you guess the phrase correctly you will receive a $1,000 bonus, otherwise it will be the computer’s turn.<br/><br/>If you have chosen to guess the phrase, you will be prompted to enter your guess. If the guess matches the hidden phrase, the game will display a message stating that the guess is correct. If the guess is incorrect, the game will continue.<br><br/>This will repeat until the phrase has been guessed correctly or until all the letters have been revealed. Once that happens, the player with the most money wins. </center></html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
        );

        javax.swing.GroupLayout pnlInstructionsLayout = new javax.swing.GroupLayout(pnlInstructions);
        pnlInstructions.setLayout(pnlInstructionsLayout);
        pnlInstructionsLayout.setHorizontalGroup(
            pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstructionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlInstructionsLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jButton1)
                .addGap(79, 79, 79)
                .addComponent(jButton2)
                .addContainerGap(177, Short.MAX_VALUE))
        );
        pnlInstructionsLayout.setVerticalGroup(
            pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInstructionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });

        GuessPhrase.setText("Guess the Phrase");
        GuessPhrase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessPhraseActionPerformed(evt);
            }
        });

        GuessVowel.setText("Buy a Vowel");
        GuessVowel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessVowelActionPerformed(evt);
            }
        });

        jLabel1.setText("Your phrase is:");

        jLabel6.setText("Wheel:");

        jLabel4.setText("Enter your guess here:");

        jLabel2.setText("You currently have ($):");

        jLabel5.setText("The Computer currently have ($):");

        GuessConsonant.setText("Guess a Consonant");
        GuessConsonant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessConsonantActionPerformed(evt);
            }
        });

        Submit.setText("Submit Guess");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Wheel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Phrase)))
                    .addGroup(pnlGameLayout.createSequentialGroup()
                        .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlGameLayout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Play)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlGameLayout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(36, 36, 36))
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(UserMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CompMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Instructions)
                .addContainerGap())
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GuessVowel)
                .addGap(119, 119, 119)
                .addComponent(GuessConsonant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GuessPhrase)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(Guess, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(Submit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Play)
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wheel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Phrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CompMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Instructions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GuessConsonant)
                    .addComponent(GuessVowel)
                    .addComponent(GuessPhrase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(Submit)
                .addContainerGap())
        );

        lblEndGameText.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEndGameText.setText("<html><center>Thank you for playing Wheel of Fortune!</center></html>");

        btnGenerateReport.setText("Generate game report");
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEndLayout = new javax.swing.GroupLayout(pnlEnd);
        pnlEnd.setLayout(pnlEndLayout);
        pnlEndLayout.setHorizontalGroup(
            pnlEndLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEndLayout.createSequentialGroup()
                .addGroup(pnlEndLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEndLayout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(pnlEndLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenerateReport)
                            .addGroup(pnlEndLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jButton4))))
                    .addGroup(pnlEndLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblEndGameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnlEndLayout.setVerticalGroup(
            pnlEndLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEndLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEndGameText, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnGenerateReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(147, 147, 147))
        );

        txtEndResult.setEditable(false);
        txtEndResult.setColumns(20);
        txtEndResult.setRows(5);
        jScrollPane1.setViewportView(txtEndResult);

        btnExitGameResult.setText("Exit");
        btnExitGameResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitGameResultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGameResultLayout = new javax.swing.GroupLayout(pnlGameResult);
        pnlGameResult.setLayout(pnlGameResultLayout);
        pnlGameResultLayout.setHorizontalGroup(
            pnlGameResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameResultLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(pnlGameResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGameResultLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGameResultLayout.createSequentialGroup()
                        .addComponent(btnExitGameResult)
                        .addGap(253, 253, 253))))
        );
        pnlGameResultLayout.setVerticalGroup(
            pnlGameResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameResultLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnExitGameResult)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContainerLayout = new javax.swing.GroupLayout(pnlContainer);
        pnlContainer.setLayout(pnlContainerLayout);
        pnlContainerLayout.setHorizontalGroup(
            pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlContainerLayout.createSequentialGroup()
                    .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlInstructions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlGameResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlContainerLayout.setVerticalGroup(
            pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlContainerLayout.createSequentialGroup()
                    .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlInstructions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlGameResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        for (int i = 0; i < 25; i++) { //for loop
            enter[i] = false; //sets all indexes of the array to false
        }
        rngPhrase = randGen.nextInt(24); //stores the RNG number as value for variable
        phrase = phrases[rngPhrase];
        phraseC = new char[phrase.length()]; //stores the board
        phraseS = new char[phrase.length()]; //stores the original phrase

        for (int i = 0; i < phrase.length(); i++) { //for loop
            phraseC[i] = phrase.charAt(i); //splits the phrase and stores each character to the indexes of array
            phraseS[i] = phrase.charAt(i); //splits the phrase and stores each character to the indexes of array
        }

        for (int i = 0; i < phrase.length(); i++) { //for loop
            if (phraseS[i] != ' ') { //condition, if met proceed
                phraseS[i] = '#'; //changes the phrase (in char array) to a series of hashtags
            }
        }

        String displayPhrase = String.copyValueOf(phraseS);
        Phrase.setText(displayPhrase);

        UserMoney.setText(Integer.toString(userTotal));
        CompMoney.setText(Integer.toString(computerTotal));

        userPlay();

        System.out.println(phrase);
    }//GEN-LAST:event_PlayActionPerformed

    private void GuessVowelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessVowelActionPerformed
        Instructions.setText("Enter your guess below and press Submit");
        GuessVowel.setEnabled(false);
        GuessConsonant.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Guess.setEditable(true);
        Submit.setEnabled(true);

        type = 1;
    }//GEN-LAST:event_GuessVowelActionPerformed

    private void GuessConsonantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessConsonantActionPerformed
        Instructions.setText("Enter your guess below and press Submit");
        GuessVowel.setEnabled(false);
        GuessConsonant.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Guess.setEditable(true);
        Submit.setEnabled(true);

        type = 2;
    }//GEN-LAST:event_GuessConsonantActionPerformed

    private void GuessPhraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessPhraseActionPerformed
        Instructions.setText("Enter your guess below and press Submit");
        GuessVowel.setEnabled(false);
        GuessConsonant.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Guess.setEditable(true);
        Submit.setEnabled(true);

        type = 3;
    }//GEN-LAST:event_GuessPhraseActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        if (Guess.getText().equals("")) {
            Instructions.setText("Please enter your guess then press Submit");
        } else {
            guess = Guess.getText();

            Guess.setText("");
            Guess.setEditable(false);
            Submit.setEnabled(false);

            Methods.iChange(guess);

            if (type == 1) {
                tof = Methods.validateC(phrase, guessL, alphabet, enter, phraseS); //assigns return value of validateC method as value for variable
                userTotal = userTotal - 250;
                UserMoney.setText(Integer.toString(userTotal));

                if (Methods.checkVowel(guessL) == false) {
                    Instructions.setText("That is not a vowel! You lost your turn..");
                    computer();
                } else if (tof) { //condition, if met, proceed
                    System.out.println("That letter has already been guessed"); //Print this
                    Instructions.setText("That letter has already been guessed");
                    computer();
                } else {
                    Methods.record(guessL, enter, alphabet); //uses record method
                    Methods.gameBoard(phrase, phraseC, phraseS, guessL, spinner, rngSpin); //uses gameboard method

                    if (numberC > 0) { //condition, if met, proceed
                        System.out.println("Congrats, there are " + numberC + " of those letters!"); //Print this
                        display = "Congrats, there are " + numberC + " of those letters!";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            if (Methods.boardFinish(phrase, phraseS)) {
                                int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if (x == 0) {
                                    //go to end game screen
                                    System.out.println("Game over");
                                    hideAll();
                                    pnlEnd.setVisible(true);
                                    showWinner();
                                }
                            } else {
                                reveal = String.valueOf(phraseS);
                                Phrase.setText(reveal);
                                userPlay();
                            }
                        }
                    } else if (numberC == 0) {  //condition, if met, proceed
                        System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                        display = "Sorry, that letter does not appear in this phrase";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            computer();
                        }
                    }
                }
            } else if (type == 2) {
                tof = Methods.validateC(phrase, guessL, alphabet, enter, phraseS); //assigns return value of validateC method as value for variable

                if (Methods.checkVowel(guessL)) {
                    Instructions.setText("That is not a consonant! You lost your turn..");
                    computer();
                } else if (tof) { //condition, if met, proceed
                    System.out.println("That letter has already been guessed"); //Print this
                    Instructions.setText("That letter has already been guessed");
                    computer();
                } else {
                    Methods.record(guessL, enter, alphabet); //uses record method
                    Methods.gameBoard(phrase, phraseC, phraseS, guessL, spinner, rngSpin); //uses gameboard method
                    if (numberC > 0) { //condition, if met, proceed
                        System.out.println("Congrats, there are " + numberC + " of those letters!"); //Print this
                        gain = numberC * spinner[rngSpin]; //value assignment to variable
                        display = "Congrats, there are " + numberC + " of those letters! \n You gained " + gain + " dollars!";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            userTotal = userTotal + gain;
                            UserMoney.setText(Integer.toString(userTotal));
                            if (Methods.boardFinish(phrase, phraseS)) {
                                int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if (x == 0) {
                                    //go to end game screen
                                    System.out.println("Game over");
                                    hideAll();
                                    pnlEnd.setVisible(true);
                                    showWinner();
                                }
                            } else {
                                reveal = String.valueOf(phraseS);
                                Phrase.setText(reveal);
                                userPlay();
                            }
                        }
                    } else if (numberC == 0) {  //condition, if met, proceed
                        System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                        display = "Sorry, that letter does not appear in this phrase";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            computer();
                        }
                    }
                }
            } else if (type == 3) {
                if (guess.equalsIgnoreCase(phrase)) {
                    display = "Congrats, you have guessed the correct phrase!";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        //go to end game screen
                        System.out.println("Game over");
                        userTotal = userTotal + 1000;
                        hideAll();
                        pnlEnd.setVisible(true);
                        showWinner();
                    }
                } else {
                    display = "That is not the correct phrase!";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        computer();
                    }
                }
            }
        }
    }//GEN-LAST:event_SubmitActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        hideAll();
        pnlGame.setVisible(true);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstructionsActionPerformed
        // TODO add your handling code here:
        hideAll();
        pnlInstructions.setVisible(true);
    }//GEN-LAST:event_btnInstructionsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        hideAll();
        pnlStart.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        hideAll();
        pnlGame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnExitGameResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitGameResultActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitGameResultActionPerformed

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        // TODO add your handling code here:
        hideAll();
        pnlGameResult.setVisible(true);
        generateReport();
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(testTwoPointOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testTwoPointOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testTwoPointOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testTwoPointOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testTwoPointOne().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextField CompMoney;
    private static javax.swing.JTextField Guess;
    private static javax.swing.JButton GuessConsonant;
    private static javax.swing.JButton GuessPhrase;
    private static javax.swing.JButton GuessVowel;
    private static javax.swing.JTextField Instructions;
    private static javax.swing.JTextField Phrase;
    private static javax.swing.JButton Play;
    private static javax.swing.JButton Submit;
    private static javax.swing.JTextField UserMoney;
    private static javax.swing.JTextField Wheel;
    private javax.swing.JButton btnExitGameResult;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnInstructions;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEndGameText;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlEnd;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JPanel pnlGameResult;
    private javax.swing.JPanel pnlInstructions;
    private javax.swing.JPanel pnlStart;
    private javax.swing.JTextArea txtEndResult;
    // End of variables declaration//GEN-END:variables
}
