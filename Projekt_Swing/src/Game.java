import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Game{
    private String poziomTrudnosci;
    private String nazwaLekarstwa;
    private int second = 0;
    private int minute = 0;
    private String dSeconds;
    private String dMinutes;
    private int punkty = 0;
    private String dPunkty;
    private int punktyCure = 0;
    private String dPunktyCure;
    private int tmpPointsCure = 0;
    private int tmpPoints = 0;
    public int peopleCanada = 38504, peopleUSA = 32601, peopleBrazil = 21021, peoplePoland = 38253, peopleEgypt = 10715, peopleAustralia = 25476, peopleGreenland = 26225, peopleRussia = 44765, peopleChina = 44117, peopleIndie = 43770, peopleJapan = 12534;
    public int peopleOverall = peopleCanada + peopleUSA + peopleBrazil + peoplePoland + peopleEgypt + peopleAustralia + peopleGreenland + peopleRussia + peopleChina + peopleIndie + peopleJapan;
    public int sickCanada = 0, sickUSA = 0, sickBrazil = 0, sickPoland = 0, sickEgypt = 0, sickAustralia = 0, sickGreenland = 0, sickRussia = 0, sickChina = 0, sickIndie = 0, sickJapan = 0;
    public int sickOverall = 0;
    public int healthyCanada = 0, healthyUSA = 0, healthyBrazil = 0, healthyPoland = 0, healthyEgypt = 0, healthyAustralia = 0, healthyGreenland = 0, healthyRussia = 0, healthyChina = 0, healthyIndie = 0, healthyJapan = 0;
    public int healthyOverall = 0;
    private boolean planeCanada = true, planeUSA = true, planeBrazil = true, planePoland = true, planeEgypt = true, planeAustralia = true, planeGreenland = true, planeRussia = true, planeChina = true, planeIndie = true, planeJapan = true;
    private boolean busCanada = true, busUSA = true, busBrazil = true, busPoland = true, busEgypt = true, busAustralia = true, busGreenland = true, busRussia = true, busChina = true, busIndie = true, busJapan = true;
    private boolean shipCanada = true, shipUSA = true, shipBrazil = true, shipPoland = true, shipEgypt = true, shipAustralia = true, shipGreenland = true, shipRussia = true, shipChina = true, shipIndie = true, shipJapan = true;
    private int upgrade1 = 0, upgrade2 = 0, upgrade3 = 0, upgrade4 = 0, upgrade5 = 0, upgrade6 = 0, upgrade7 = 0, upgrade8 = 0, upgrade9 = 0;

    File file = new File("./src/HighScore.txt");

    FileOutputStream fos = new FileOutputStream(file);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    FileInputStream fis = new FileInputStream(file);
    ObjectInputStream ois = new ObjectInputStream(fis);

    public Game() throws IOException {
        OknoStartowe();
    }

    public void OknoStartowe() {
        JFrame frame = new JFrame();
        //Okno Startowe
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());
        //Etykieta menu
        JLabel label = new JLabel("Reversed Plague Inc.");
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Courier", Font.ITALIC, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        //Przyciski menu
        JPanel panelPrzyciskow = new JPanel();
        panelPrzyciskow.setLayout(new BoxLayout(panelPrzyciskow, BoxLayout.Y_AXIS));
        panelPrzyciskow.setBackground(Color.BLACK);
        //New Game
        JButton newGame = new JButton("New Game");
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGame.setBackground(Color.BLACK);
        newGame.setFont(new Font("Courier", Font.BOLD, 16));
        newGame.setForeground(Color.WHITE);
        newGame.setBorderPainted(true);
        newGame.setBorder(new LineBorder(Color.WHITE, 1));
        //Wystartownie nowej rozgrywki
        newGame.addActionListener(e -> {
            PoziomTrudnosci();
            frame.dispose();
        });
        //High Scores
        JButton highScores = new JButton("High Scores");
        highScores.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScores.setBackground(Color.BLACK);
        highScores.setFont(new Font("Courier", Font.BOLD, 16));
        highScores.setForeground(Color.WHITE);
        highScores.setBorderPainted(true);
        highScores.setBorder(new LineBorder(Color.WHITE, 1));
        highScores.addActionListener(e -> {
            try {
                HighScores();
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
            frame.dispose();
        });
        //Exit
        JButton exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font("Courier", Font.BOLD, 16));
        exit.setForeground(Color.WHITE);
        exit.setBorderPainted(true);
        exit.setBorder(new LineBorder(Color.WHITE, 1));
        //Zamykanie apliakcji - exit
        exit.addActionListener(e -> frame.dispose());

        panelPrzyciskow.add(newGame);
        panelPrzyciskow.add(highScores);
        panelPrzyciskow.add(exit);

        panelMenu.add(label, BorderLayout.PAGE_START);
        panelMenu.add(panelPrzyciskow, BorderLayout.CENTER);
        frame.add(panelMenu);

        frame.setTitle("Reversed Plague Inc.");
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void HighScores() throws IOException, ClassNotFoundException {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel panelGorny = new JPanel();
        panelGorny.setLayout(new FlowLayout());
        panelGorny.setBackground(Color.BLACK);

        JLabel label = new JLabel("High Scores :");
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Courier", Font.ITALIC, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton returnButton = new BasicArrowButton(SwingConstants.WEST, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        returnButton.setBackground(Color.BLACK);
        returnButton.setBorderPainted(true);
        returnButton.setBorder(new LineBorder(Color.WHITE, 1));
        returnButton.addActionListener(e -> {
            OknoStartowe();
            frame.dispose();
        });

        java.util.List<Save> resultList = new LinkedList<>();
        boolean reading = true;
        while(reading) {
            try{
                resultList.add((Save) ois.readObject());
            } catch(EOFException ex){
                reading=false;
            }
        }

        String[] results = new String[resultList.size()];
        int i = 0;
        for(Save obj : resultList) {
            results[i] = obj.toString();
            i++;
        }

        JList list = new JList(results);
        list.setBackground(Color.BLACK);
        list.setForeground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(list);

        panelGorny.add(returnButton);
        panelGorny.add(label);

        mainPanel.add(panelGorny,BorderLayout.PAGE_START);
        mainPanel.add(scroll,BorderLayout.CENTER);
        frame.add(mainPanel);

        frame.setSize(400,250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void PoziomTrudnosci() {
        JFrame frame = new JFrame();

        JPanel panelPoziomow = new JPanel();
        panelPoziomow.setLayout(new BorderLayout());

        JPanel panelGorny = new JPanel();
        panelGorny.setLayout(new FlowLayout());
        panelGorny.setBackground(Color.BLACK);

        JLabel label = new JLabel("Level of Difficulty:");
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Courier", Font.ITALIC, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton returnButton = new BasicArrowButton(SwingConstants.WEST, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        returnButton.setBackground(Color.BLACK);
        returnButton.setBorderPainted(true);
        returnButton.setBorder(new LineBorder(Color.WHITE, 1));
        returnButton.addActionListener(e -> {
            OknoStartowe();
            frame.dispose();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JButton latwy = new JButton("Easy");
        latwy.setAlignmentX(Component.CENTER_ALIGNMENT);
        latwy.setBackground(Color.BLACK);
        latwy.setFont(new Font("Courier", Font.BOLD, 16));
        latwy.setForeground(Color.WHITE);
        latwy.setBorderPainted(true);
        latwy.setBorder(new LineBorder(Color.WHITE, 1));
        latwy.addActionListener(e -> {
            poziomTrudnosci = "latwy";
            Lekarstwo();
            frame.dispose();
            System.out.println(getPoziomTrudnosci());
        });

        JButton sredni = new JButton("Medium");
        sredni.setAlignmentX(Component.CENTER_ALIGNMENT);
        sredni.setBackground(Color.BLACK);
        sredni.setFont(new Font("Courier", Font.BOLD, 16));
        sredni.setForeground(Color.WHITE);
        sredni.setBorderPainted(true);
        sredni.setBorder(new LineBorder(Color.WHITE, 1));
        sredni.addActionListener(e -> {
            poziomTrudnosci = "sredni";
            Lekarstwo();
            frame.dispose();
            System.out.println(getPoziomTrudnosci());
        });

        JButton trudny = new JButton("Hard");
        trudny.setAlignmentX(Component.CENTER_ALIGNMENT);
        trudny.setBackground(Color.BLACK);
        trudny.setFont(new Font("Courier", Font.BOLD, 16));
        trudny.setForeground(Color.WHITE);
        trudny.setBorderPainted(true);
        trudny.setBorder(new LineBorder(Color.WHITE, 1));
        trudny.addActionListener(e -> {
            poziomTrudnosci = "trudny";
            Lekarstwo();
            frame.dispose();
            System.out.println(getPoziomTrudnosci());
        });

        panelGorny.add(returnButton);
        panelGorny.add(label);

        panel.add(latwy);
        panel.add(sredni);
        panel.add(trudny);

        panelPoziomow.add(panelGorny, BorderLayout.PAGE_START);
        panelPoziomow.add(panel, BorderLayout.CENTER);

        frame.add(panelPoziomow);

        frame.setTitle("Level of Difficulty");
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public String getPoziomTrudnosci() {
        return poziomTrudnosci;
    }

    public void Lekarstwo() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panelGorny = new JPanel();
        panelGorny.setLayout(new FlowLayout());
        panelGorny.setBackground(Color.BLACK);

        JLabel label = new JLabel("Name your cure:");
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setFont(new Font("Courier", Font.ITALIC, 18));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton returnButton = new BasicArrowButton(SwingConstants.WEST, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        returnButton.setBackground(Color.BLACK);
        returnButton.setPreferredSize(new Dimension(40, 40));
        returnButton.setBorderPainted(true);
        returnButton.setBorder(new LineBorder(Color.WHITE, 1));
        returnButton.addActionListener(e -> {
            PoziomTrudnosci();
            frame.dispose();
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);

        JPanel panelCure = new JPanel();
        panelCure.setLayout(new FlowLayout());
        panelCure.setBackground(Color.BLACK);
        panelCure.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etykietaCure = new JLabel("Cure name:");
        etykietaCure.setOpaque(true);
        etykietaCure.setBackground(Color.BLACK);
        etykietaCure.setFont(new Font("Courier", Font.BOLD, 16));
        etykietaCure.setForeground(Color.WHITE);
        etykietaCure.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField cure = new JTextField("");
        cure.setPreferredSize(new Dimension(100, 20));
        cure.setFont(new Font("Courier", Font.BOLD, 16));

        JButton confirmButton = new JButton("Start Game");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setFont(new Font("Courier", Font.BOLD, 16));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBorderPainted(true);
        confirmButton.setBorder(new LineBorder(Color.WHITE, 1));
        confirmButton.addActionListener(e -> {
            if ((!cure.getText().isEmpty())) {
                nazwaLekarstwa = cure.getText();
            }
            if (!(nazwaLekarstwa == null)) {
                NewGame();
                frame.dispose();
                System.out.println(getNazwaLekarstwa());
            } else
                JOptionPane.showMessageDialog(null, "Name your cure !", "CureNameAlert", JOptionPane.WARNING_MESSAGE);
        });

        panelGorny.add(returnButton);
        panelGorny.add(label);

        panelCure.add(etykietaCure);
        panelCure.add(cure);

        centerPanel.add(panelCure);
        centerPanel.add(confirmButton);

        panel.add(panelGorny, BorderLayout.PAGE_START);
        panel.add(centerPanel, BorderLayout.CENTER);

        frame.add(panel);

        frame.setTitle("Cure");
        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public String getNazwaLekarstwa() {
        return nazwaLekarstwa;
    }

    public void NewGame() {
        JFrame frame = new JFrame();

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBackground(Color.BLACK);

        //MapaSwiata
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./src/mapa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel gridLabel = null;
        if (image != null) {
            gridLabel = new JLabel(new ImageIcon(image));
        }
        gridLabel.setLayout(new GridLayout(40, 20));
        gridLabel.setBackground(Color.BLACK);
        rootPanel.add(gridLabel, BorderLayout.CENTER);

        for (int y = 0; y < 800; y++) {
            JButton grid = new JButton("");
            grid.setOpaque(false);
            grid.setContentAreaFilled(false);
            grid.setBorderPainted(false);
            if (y == 14 * 20 + 4) {
                JButton canada = new JButton("Canada");
                canada.setFont(new Font("Courier", Font.BOLD, 12));
                canada.setOpaque(false);
                canada.setBackground(Color.BLACK);
                canada.setForeground(Color.RED);
                canada.setBorderPainted(false);
                canada.addActionListener(e -> Canada());
                gridLabel.add(canada, 0, y);
            } else if (y == 17 * 20 + 4) {
                JButton usa = new JButton("Usa");
                usa.setFont(new Font("Courier", Font.BOLD, 12));
                usa.setOpaque(false);
                usa.setBackground(Color.BLACK);
                usa.setForeground(Color.RED);
                usa.setBorderPainted(false);
                usa.addActionListener(e -> USA());
                gridLabel.add(usa, 0, y);
            } else if (y == 25 * 20 + 7) {
                JButton brazil = new JButton("Brazil");
                brazil.setFont(new Font("Courier", Font.BOLD, 12));
                brazil.setOpaque(false);
                brazil.setBackground(Color.BLACK);
                brazil.setForeground(Color.RED);
                brazil.setBorderPainted(false);
                brazil.addActionListener(e -> Brazil());
                gridLabel.add(brazil, 0, y);
            } else if (y == 16 * 20 + 10) {
                JButton poland = new JButton("Poland");
                poland.setFont(new Font("Courier", Font.BOLD, 12));
                poland.setOpaque(false);
                poland.setBackground(Color.BLACK);
                poland.setForeground(Color.RED);
                poland.setBorderPainted(false);
                poland.addActionListener(e -> Poland());
                gridLabel.add(poland, 0, y);
            } else if (y == 21 * 20 + 10) {
                JButton egypt = new JButton("Egypt");
                egypt.setFont(new Font("Courier", Font.BOLD, 12));
                egypt.setOpaque(false);
                egypt.setBackground(Color.BLACK);
                egypt.setForeground(Color.RED);
                egypt.setBorderPainted(false);
                egypt.addActionListener(e -> Egypt());
                gridLabel.add(egypt, 0, y);
            } else if (y == 28 * 20 + 15) {
                JButton australia = new JButton("Australia");
                australia.setFont(new Font("Courier", Font.BOLD, 12));
                australia.setOpaque(false);
                australia.setBackground(Color.BLACK);
                australia.setForeground(Color.RED);
                australia.setBorderPainted(false);
                australia.addActionListener(e -> Australia());
                gridLabel.add(australia, 0, y);
            } else if (y == 9 * 20 + 7) {
                JButton greenland = new JButton("Greenland");
                greenland.setFont(new Font("Courier", Font.BOLD, 10));
                greenland.setOpaque(false);
                greenland.setBackground(Color.BLACK);
                greenland.setForeground(Color.RED);
                greenland.setBorderPainted(false);
                greenland.addActionListener(e -> Greenland());
                gridLabel.add(greenland, 0, y);
            } else if (y == 15 * 20 + 11) {
                JButton russia = new JButton("Russia");
                russia.setFont(new Font("Courier", Font.BOLD, 12));
                russia.setOpaque(false);
                russia.setBackground(Color.BLACK);
                russia.setForeground(Color.RED);
                russia.setBorderPainted(false);
                russia.addActionListener(e -> Russia());
                gridLabel.add(russia, 0, y);
            } else if (y == 16 * 20 + 13) {
                JButton china = new JButton("China");
                china.setFont(new Font("Courier", Font.BOLD, 12));
                china.setOpaque(false);
                china.setBackground(Color.BLACK);
                china.setForeground(Color.RED);
                china.setBorderPainted(false);
                china.addActionListener(e -> China());
                gridLabel.add(china, 0, y);
            } else if (y == 20 * 20 + 13) {
                JButton indie = new JButton("Indie");
                indie.setFont(new Font("Courier", Font.BOLD, 12));
                indie.setOpaque(false);
                indie.setBackground(Color.BLACK);
                indie.setForeground(Color.RED);
                indie.setBorderPainted(false);
                indie.addActionListener(e -> Indie());
                gridLabel.add(indie, 0, y);
            } else if (y == 18 * 20 + 16) {
                JButton japan = new JButton("Japan");
                japan.setFont(new Font("Courier", Font.BOLD, 12));
                japan.setOpaque(false);
                japan.setBackground(Color.BLACK);
                japan.setForeground(Color.RED);
                japan.setBorderPainted(false);
                japan.addActionListener(e -> Japan());
                gridLabel.add(japan, 0, y);
            } else
                gridLabel.add(grid, 0, y);
        }

        //Górny panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.BLACK);

        //Timer
        JButton timerButton = new JButton("Time [ 00:00 ]");
        timerButton.setSize(10, 20);
        timerButton.setFont(new Font("Courier", Font.BOLD, 16));
        timerButton.setEnabled(false);
        timerButton.setOpaque(false);
        timerButton.setBackground(Color.BLACK);
        timerButton.setForeground(Color.WHITE);
        timerButton.setBorderPainted(false);
        DecimalFormat dFormat1 = new DecimalFormat("00");
        Timer timer = new Timer(1000, e -> {
            second++;
            dSeconds = dFormat1.format(second);
            dMinutes = dFormat1.format(minute);
            timerButton.setText("Time [ " + dMinutes + ":" + dSeconds + " ]");
            if (second == 60) {
                second = 0;
                minute++;
                dSeconds = dFormat1.format(second);
                dMinutes = dFormat1.format(minute);
                timerButton.setText("Time [ " + dMinutes + ":" + dSeconds + " ]");
            }
        });
        timer.start();

        //Panel na Punkty
        JPanel pointsPanel = new JPanel(new FlowLayout());
        pointsPanel.setBackground(Color.BLACK);

        //Punkty
        DecimalFormat dFormat2 = new DecimalFormat("0000");
        JButton points = new JButton("Points: 0000");
        points.setSize(10, 20);
        points.setFont(new Font("Courier", Font.BOLD, 16));
        points.setEnabled(false);
        points.setOpaque(false);
        points.setBackground(Color.BLACK);
        points.setForeground(Color.WHITE);
        points.setBorderPainted(false);
        dPunkty = dFormat2.format(punkty);
        points.setText("Points: " + dPunkty);

        Thread pointsThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                if (poziomTrudnosci.equals("latwy"))
                    punkty = healthyOverall / 10;
                if (poziomTrudnosci.equals("sredni"))
                    punkty = healthyOverall / 25;
                if (poziomTrudnosci.equals("trudny"))
                    punkty = healthyOverall / 50;
                dPunkty = dFormat2.format(punkty);
                points.setText("Points: " + dPunkty);
                try {
                    Thread.sleep(5100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        pointsThread.start();

        //Punkty Cure
        DecimalFormat dFormat3 = new DecimalFormat("0000");
        JButton pointsCure = new JButton("Cure Points: 0000");
        pointsCure.setSize(10, 20);
        pointsCure.setFont(new Font("Courier", Font.BOLD, 16));
        pointsCure.setEnabled(false);
        pointsCure.setOpaque(false);
        pointsCure.setBackground(Color.BLACK);
        pointsCure.setForeground(Color.WHITE);
        pointsCure.setBorderPainted(false);
        dPunktyCure = dFormat3.format(punktyCure);
        pointsCure.setText("Cure Points: " + dPunktyCure);

        Thread updateCurePoints = new Thread(() -> {
            while (!Thread.interrupted()) {
                dPunktyCure = dFormat3.format(punktyCure);
                pointsCure.setText("Cure Points: " + dPunktyCure);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!frame.isShowing())
                Thread.currentThread().interrupt();
        });
        updateCurePoints.start();

        Thread pointsCureThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    if (poziomTrudnosci.equals("latwy")) {
                        tmpPoints = (punkty/25)-(tmpPointsCure);
                        if(tmpPoints>=1) {
                            if (tmpPointsCure < 27) {
                                tmpPointsCure++;
                                punktyCure++;
                            }
                        }
                    }
                    if (poziomTrudnosci.equals("sredni")) {
                        tmpPoints = (punkty/50)-(tmpPointsCure);
                        if(tmpPoints>=1) {
                            if (tmpPointsCure < 27) {
                                tmpPointsCure++;
                                punktyCure++;
                            }
                        }
                    }
                    if (poziomTrudnosci.equals("trudny")) {
                        tmpPoints = (punkty/100)-(tmpPointsCure);
                        if (tmpPoints>=1) {
                            if (tmpPointsCure < 27) {
                                tmpPointsCure++;
                                punktyCure++;
                            }
                        }
                    }
                    dPunktyCure = dFormat3.format(punktyCure);
                    pointsCure.setText("Cure Points: " + dPunktyCure);
                    Thread.sleep(5200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        pointsCureThread.start();

        //Menu
        JButton menuButton = new JButton("Return to Menu");
        menuButton.setSize(10, 20);
        menuButton.setFont(new Font("Courier", Font.BOLD, 16));
        menuButton.setOpaque(false);
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBorderPainted(false);
        menuButton.addActionListener(e -> {
            timer.stop();
            second = 0;
            minute = 0;
            punkty = 0;
            OknoStartowe();
            frame.dispose();
        });
        menuButton.setMnemonic(KeyEvent.VK_C); //Alt+C

        pointsPanel.add(points);
        pointsPanel.add(pointsCure);

        topPanel.add(pointsPanel, BorderLayout.WEST);
        topPanel.add(timerButton, BorderLayout.CENTER);
        topPanel.add(menuButton, BorderLayout.EAST);

        //Dolny Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);

        JButton upgrade = new JButton("UPGRADES");
        upgrade.setSize(10, 20);
        upgrade.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade.setOpaque(false);
        upgrade.setBackground(Color.BLACK);
        upgrade.setForeground(Color.BLUE);
        upgrade.setBorderPainted(false);
        upgrade.addActionListener(e -> Upgrades());

        JPanel overallPanel = new JPanel(new FlowLayout());
        overallPanel.setBackground(Color.BLACK);
        JLabel peopleOverallLabel = new JLabel("People Overall = " + peopleOverall + "   ");
        peopleOverallLabel.setSize(10, 20);
        peopleOverallLabel.setFont(new Font("Courier", Font.BOLD, 16));
        peopleOverallLabel.setOpaque(false);
        peopleOverallLabel.setBackground(Color.BLACK);
        peopleOverallLabel.setForeground(Color.WHITE);

        JLabel sickOverallLabel = new JLabel("Sick Overall = " + sickOverall + "   ");
        sickOverallLabel.setSize(10, 20);
        sickOverallLabel.setFont(new Font("Courier", Font.BOLD, 16));
        sickOverallLabel.setOpaque(false);
        sickOverallLabel.setBackground(Color.BLACK);
        sickOverallLabel.setForeground(Color.RED);

        JLabel healthyOverallLabel = new JLabel("Healthy Overall = " + healthyOverall);
        healthyOverallLabel.setSize(10, 20);
        healthyOverallLabel.setFont(new Font("Courier", Font.BOLD, 16));
        healthyOverallLabel.setOpaque(false);
        healthyOverallLabel.setBackground(Color.BLACK);
        healthyOverallLabel.setForeground(Color.GREEN);
        overallPanel.add(peopleOverallLabel);
        overallPanel.add(sickOverallLabel);
        overallPanel.add(healthyOverallLabel);

        bottomPanel.add(overallPanel, BorderLayout.WEST);
        bottomPanel.add(upgrade, BorderLayout.EAST);

        //Thread
        Thread sickThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                if(!(tmpPointsCure==27)) {
                    if (poziomTrudnosci.equals("latwy")) {
                        if (sickOverall + 10 < peopleOverall) {
                            if (sickCanada + 10 < peopleCanada) {
                                sickCanada += (int) (Math.random() * 50 + 40);
                            }
                            if (sickUSA + 10 < peopleUSA) {
                                sickUSA += (int) (Math.random() * 50 + 40);
                            }
                            if (sickBrazil + 10 < peopleBrazil) {
                                sickBrazil += (int) (Math.random() * 50 + 40);
                            }
                            if (sickPoland + 10 < peoplePoland) {
                                sickPoland += (int) (Math.random() * 50 + 40);
                            }
                            if (sickEgypt + 10 < peopleEgypt) {
                                sickEgypt += (int) (Math.random() * 50 + 40);
                            }
                            if (sickAustralia + 10 < peopleAustralia) {
                                sickAustralia += (int) (Math.random() * 50 + 40);
                            }
                            if (sickGreenland + 10 < peopleGreenland) {
                                sickGreenland += (int) (Math.random() * 50 + 40);
                            }
                            if (sickRussia + 10 < peopleRussia) {
                                sickRussia += (int) (Math.random() * 50 + 40);
                            }
                            if (sickChina + 10 < peopleChina) {
                                sickChina += (int) (Math.random() * 50 + 40);
                            }
                            if (sickIndie + 10 < peopleIndie) {
                                sickIndie += (int) (Math.random() * 50 + 40);
                            }
                            if (sickJapan + 10 < peopleJapan) {
                                sickJapan += (int) (Math.random() * 50 + 40);
                            }
                            sickOverall = sickCanada + sickUSA + sickBrazil + sickPoland + sickEgypt + sickAustralia + sickGreenland + sickRussia + sickChina + sickIndie + sickJapan;
                            sickOverallLabel.setText("Sick Overall = " + sickOverall + "   ");
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (poziomTrudnosci.equals("sredni")) {
                        if (sickOverall + 25 < peopleOverall) {
                            if (sickCanada + 25 < peopleCanada) {
                                sickCanada += (int) (Math.random() * 75 + 65);
                            }
                            if (sickUSA + 25 < peopleUSA) {
                                sickUSA += (int) (Math.random() * 75 + 65);
                            }
                            if (sickBrazil + 25 < peopleBrazil) {
                                sickBrazil += (int) (Math.random() * 75 + 65);
                            }
                            if (sickPoland + 25 < peoplePoland) {
                                sickPoland += (int) (Math.random() * 75 + 65);
                            }
                            if (sickEgypt + 25 < peopleEgypt) {
                                sickEgypt += (int) (Math.random() * 75 + 65);
                            }
                            if (sickAustralia + 25 < peopleAustralia) {
                                sickAustralia += (int) (Math.random() * 75 + 65);
                            }
                            if (sickGreenland + 25 < peopleGreenland) {
                                sickGreenland += (int) (Math.random() * 75 + 65);
                            }
                            if (sickRussia + 25 < peopleRussia) {
                                sickRussia += (int) (Math.random() * 75 + 65);
                            }
                            if (sickChina + 25 < peopleChina) {
                                sickChina += (int) (Math.random() * 75 + 65);
                            }
                            if (sickIndie + 25 < peopleIndie) {
                                sickIndie += (int) (Math.random() * 75 + 65);
                            }
                            if (sickJapan + 25 < peopleJapan) {
                                sickJapan += (int) (Math.random() * 75 + 65);
                            }
                            sickOverall = sickCanada + sickUSA + sickBrazil + sickPoland + sickEgypt + sickAustralia + sickGreenland + sickRussia + sickChina + sickIndie + sickJapan;
                            sickOverallLabel.setText("Sick Overall = " + sickOverall + "   ");
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (poziomTrudnosci.equals("trudny")) {
                        if (sickOverall + 50 < peopleOverall) {
                            if (sickCanada + 50 < peopleCanada) {
                                sickCanada += (int) (Math.random() * 100 + 90);
                            }
                            if (sickUSA + 50 < peopleUSA) {
                                sickUSA += (int) (Math.random() * 100 + 90);
                            }
                            if (sickBrazil + 50 < peopleBrazil) {
                                sickBrazil += (int) (Math.random() * 100 + 90);
                            }
                            if (sickPoland + 50 < peoplePoland) {
                                sickPoland += (int) (Math.random() * 100 + 90);
                            }
                            if (sickEgypt + 50 < peopleEgypt) {
                                sickEgypt += (int) (Math.random() * 100 + 90);
                            }
                            if (sickAustralia + 50 < peopleAustralia) {
                                sickAustralia += (int) (Math.random() * 100 + 90);
                            }
                            if (sickGreenland + 50 < peopleGreenland) {
                                sickGreenland += (int) (Math.random() * 100 + 90);
                            }
                            if (sickRussia + 50 < peopleRussia) {
                                sickRussia += (int) (Math.random() * 100 + 90);
                            }
                            if (sickChina + 50 < peopleChina) {
                                sickChina += (int) (Math.random() * 100 + 90);
                            }
                            if (sickIndie + 50 < peopleIndie) {
                                sickIndie += (int) (Math.random() * 100 + 90);
                            }
                            if (sickJapan + 50 < peopleJapan) {
                                sickJapan += (int) (Math.random() * 100 + 90);
                            }
                            sickOverall = sickCanada + sickUSA + sickBrazil + sickPoland + sickEgypt + sickAustralia + sickGreenland + sickRussia + sickChina + sickIndie + sickJapan;
                            sickOverallLabel.setText("Sick Overall = " + sickOverall + "   ");
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (sickOverall == peopleOverall) {
                    Thread.currentThread().interrupt();
                    frame.dispose();
                    try {
                        Lose();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (sickOverall <= 0) {
                    Thread.currentThread().interrupt();
                    frame.dispose();
                    try {
                        Win();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        sickThread.start();

        Thread healthyThread = new Thread(() -> {
            int counterCanada = 0;
            int counterUSA = 0;
            int counterBrazil = 0;
            int counterPoland = 0;
            int counterEgypt = 0;
            int counterAustralia = 0;
            int counterGreenland = 0;
            int counterRussia = 0;
            int counterChina = 0;
            int counterIndie = 0;
            int counterJapan = 0;
            while (!Thread.interrupted()) {
                if (!planeCanada) counterCanada = 1;
                if (!busCanada) counterCanada = 2;
                if (!shipCanada) counterCanada = 3;
                if (!planeCanada) counterUSA = 1;
                if (!busCanada) counterUSA = 2;
                if (!shipCanada) counterUSA = 3;
                if (!planeCanada) counterBrazil = 1;
                if (!busCanada) counterBrazil = 2;
                if (!shipCanada) counterBrazil = 3;
                if (!planeCanada) counterPoland = 1;
                if (!busCanada) counterPoland = 2;
                if (!shipCanada) counterPoland = 3;
                if (!planeCanada) counterEgypt = 1;
                if (!busCanada) counterEgypt = 2;
                if (!shipCanada) counterEgypt = 3;
                if (!planeCanada) counterAustralia = 1;
                if (!busCanada) counterAustralia = 2;
                if (!shipCanada) counterAustralia = 3;
                if (!planeCanada) counterGreenland = 1;
                if (!busCanada) counterGreenland = 2;
                if (!shipCanada) counterGreenland = 3;
                if (!planeCanada) counterRussia = 1;
                if (!busCanada) counterRussia = 2;
                if (!shipCanada) counterRussia = 3;
                if (!planeCanada) counterChina = 1;
                if (!busCanada) counterChina = 2;
                if (!shipCanada) counterChina = 3;
                if (!planeCanada) counterIndie = 1;
                if (!busCanada) counterIndie = 2;
                if (!shipCanada) counterIndie = 3;
                if (!planeCanada) counterJapan = 1;
                if (!busCanada) counterJapan = 2;
                if (!shipCanada) counterJapan = 3;

                switch (counterCanada) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyCanada + 10 < peopleCanada) healthyCanada += 10;
                                sickCanada -= 10;
                            }
                            case "sredni" -> {
                                if (healthyCanada + 20 < peopleCanada) healthyCanada += 20;
                                sickCanada -= 20;
                            }
                            case "trudny" -> {
                                if (healthyCanada + 45 < peopleCanada) healthyCanada += 45;
                                sickCanada -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyCanada + 20 < peopleCanada) healthyCanada += 20;
                                sickCanada -= 20;
                            }
                            case "sredni" -> {
                                if (healthyCanada + 45 < peopleCanada) healthyCanada += 45;
                                sickCanada -= 45;
                            }
                            case "trudny" -> {
                                if (healthyCanada + 70 < peopleCanada) healthyCanada += 70;
                                sickCanada -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyCanada + 45 < peopleCanada) healthyCanada += 45;
                                sickCanada -= 45;
                            }
                            case "sredni" -> {
                                if (healthyCanada + 70 < peopleCanada) healthyCanada += 70;
                                sickCanada -= 70;
                            }
                            case "trudny" -> {
                                if (healthyCanada + 95 < peopleCanada) healthyCanada += 95;
                                sickCanada -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyCanada < peopleCanada) healthyCanada++;
                    }
                    break;
                }
                switch (counterUSA) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyUSA + 10 < peopleUSA) healthyUSA += 10;
                                sickUSA -= 10;
                            }
                            case "sredni" -> {
                                if (healthyUSA + 20 < peopleUSA) healthyUSA += 20;
                                sickUSA -= 20;
                            }
                            case "trudny" -> {
                                if (healthyUSA + 45 < peopleUSA) healthyUSA += 45;
                                sickUSA -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyUSA + 20 < peopleUSA) healthyUSA += 20;
                                sickUSA -= 20;
                            }
                            case "sredni" -> {
                                if (healthyUSA + 45 < peopleUSA) healthyUSA += 45;
                                sickUSA -= 45;
                            }
                            case "trudny" -> {
                                if (healthyUSA + 70 < peopleUSA) healthyUSA += 70;
                                sickUSA -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyUSA + 45 < peopleUSA) healthyUSA += 45;
                                sickUSA -= 45;
                            }
                            case "sredni" -> {
                                if (healthyUSA + 70 < peopleUSA) healthyUSA += 70;
                                sickUSA -= 70;
                            }
                            case "trudny" -> {
                                if (healthyUSA + 95 < peopleUSA) healthyUSA += 95;
                                sickUSA -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyUSA < peopleUSA) healthyUSA++;
                    }
                    break;
                }
                switch (counterBrazil) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyBrazil + 10 < peopleBrazil) healthyBrazil += 10;
                                sickBrazil -= 10;
                            }
                            case "sredni" -> {
                                if (healthyBrazil + 20 < peopleBrazil) healthyBrazil += 20;
                                sickBrazil -= 20;
                            }
                            case "trudny" -> {
                                if (healthyBrazil + 45 < peopleBrazil) healthyBrazil += 45;
                                sickBrazil -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyBrazil + 20 < peopleBrazil) healthyBrazil += 20;
                                sickBrazil -= 20;
                            }
                            case "sredni" -> {
                                if (healthyBrazil + 45 < peopleBrazil) healthyBrazil += 45;
                                sickBrazil -= 45;
                            }
                            case "trudny" -> {
                                if (healthyBrazil + 70 < peopleBrazil) healthyBrazil += 70;
                                sickBrazil -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyBrazil + 45 < peopleBrazil) healthyBrazil += 45;
                                sickBrazil -= 45;
                            }
                            case "sredni" -> {
                                if (healthyBrazil + 70 < peopleBrazil) healthyBrazil += 70;
                                sickBrazil -= 70;
                            }
                            case "trudny" -> {
                                if (healthyBrazil + 95 < peopleBrazil) healthyBrazil += 95;
                                sickBrazil -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyBrazil < peopleBrazil) healthyBrazil++;
                    }
                    break;
                }
                switch (counterPoland) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyPoland + 10 < peoplePoland) healthyPoland += 10;
                                sickPoland -= 10;
                            }
                            case "sredni" -> {
                                if (healthyPoland + 20 < peoplePoland) healthyPoland += 20;
                                sickPoland -= 20;
                            }
                            case "trudny" -> {
                                if (healthyPoland + 45 < peoplePoland) healthyPoland += 45;
                                sickPoland -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyPoland + 20 < peoplePoland) healthyPoland += 20;
                                sickPoland -= 20;
                            }
                            case "sredni" -> {
                                if (healthyPoland + 45 < peoplePoland) healthyPoland += 45;
                                sickPoland -= 45;
                            }
                            case "trudny" -> {
                                if (healthyPoland + 70 < peoplePoland) healthyPoland += 70;
                                sickPoland -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyPoland + 45 < peoplePoland) healthyPoland += 45;
                                sickPoland -= 45;
                            }
                            case "sredni" -> {
                                if (healthyPoland + 70 < peoplePoland) healthyPoland += 70;
                                sickPoland -= 70;
                            }
                            case "trudny" -> {
                                if (healthyPoland + 95 < peoplePoland) healthyPoland += 95;
                                sickPoland -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyPoland < peoplePoland) healthyPoland++;
                    }
                    break;
                }
                switch (counterEgypt) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyEgypt + 10 < peopleEgypt) healthyEgypt += 10;
                                sickEgypt -= 10;
                            }
                            case "sredni" -> {
                                if (healthyEgypt + 20 < peopleEgypt) healthyEgypt += 20;
                                sickEgypt -= 20;
                            }
                            case "trudny" -> {
                                if (healthyEgypt + 45 < peopleEgypt) healthyEgypt += 45;
                                sickEgypt -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyEgypt + 20 < peopleEgypt) healthyEgypt += 20;
                                sickEgypt -= 20;
                            }
                            case "sredni" -> {
                                if (healthyEgypt + 45 < peopleEgypt) healthyEgypt += 45;
                                sickEgypt -= 45;
                            }
                            case "trudny" -> {
                                if (healthyEgypt + 70 < peopleEgypt) healthyEgypt += 70;
                                sickEgypt -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyEgypt + 45 < peopleEgypt) healthyEgypt += 45;
                                sickEgypt -= 45;
                            }
                            case "sredni" -> {
                                if (healthyEgypt + 70 < peopleEgypt) healthyEgypt += 70;
                                sickEgypt -= 70;
                            }
                            case "trudny" -> {
                                if (healthyEgypt + 95 < peopleEgypt) healthyEgypt += 95;
                                sickEgypt -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyEgypt < peopleEgypt) healthyEgypt++;
                    }
                    break;
                }
                switch (counterAustralia) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyAustralia + 10 < peopleAustralia) healthyAustralia += 10;
                                sickAustralia -= 10;
                            }
                            case "sredni" -> {
                                if (healthyAustralia + 20 < peopleAustralia) healthyAustralia += 20;
                                sickAustralia -= 20;
                            }
                            case "trudny" -> {
                                if (healthyAustralia + 45 < peopleAustralia) healthyAustralia += 45;
                                sickAustralia -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyAustralia + 20 < peopleAustralia) healthyAustralia += 20;
                                sickAustralia -= 20;
                            }
                            case "sredni" -> {
                                if (healthyAustralia + 45 < peopleAustralia) healthyAustralia += 45;
                                sickAustralia -= 45;
                            }
                            case "trudny" -> {
                                if (healthyAustralia + 70 < peopleAustralia) healthyAustralia += 70;
                                sickAustralia -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyAustralia + 45 < peopleAustralia) healthyAustralia += 45;
                                sickAustralia -= 45;
                            }
                            case "sredni" -> {
                                if (healthyAustralia + 70 < peopleAustralia) healthyAustralia += 70;
                                sickAustralia -= 70;
                            }
                            case "trudny" -> {
                                if (healthyAustralia + 95 < peopleAustralia) healthyAustralia += 95;
                                sickAustralia -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyAustralia < peopleAustralia) healthyAustralia++;
                    }
                    break;
                }
                switch (counterGreenland) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyGreenland + 10 < peopleGreenland) healthyGreenland += 10;
                                sickGreenland -= 10;
                            }
                            case "sredni" -> {
                                if (healthyGreenland + 20 < peopleGreenland) healthyGreenland += 20;
                                sickGreenland -= 20;
                            }
                            case "trudny" -> {
                                if (healthyGreenland + 45 < peopleGreenland) healthyGreenland += 45;
                                sickGreenland -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyGreenland + 20 < peopleGreenland) healthyGreenland += 20;
                                sickGreenland -= 20;
                            }
                            case "sredni" -> {
                                if (healthyGreenland + 45 < peopleGreenland) healthyGreenland += 45;
                                sickGreenland -= 45;
                            }
                            case "trudny" -> {
                                if (healthyGreenland + 70 < peopleGreenland) healthyGreenland += 70;
                                sickGreenland -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyGreenland + 45 < peopleGreenland) healthyGreenland += 45;
                                sickGreenland -= 45;
                            }
                            case "sredni" -> {
                                if (healthyGreenland + 70 < peopleGreenland) healthyGreenland += 70;
                                sickGreenland -= 70;
                            }
                            case "trudny" -> {
                                if (healthyGreenland + 95 < peopleGreenland) healthyGreenland += 95;
                                sickGreenland -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyGreenland < peopleGreenland) healthyGreenland++;
                    }
                    break;
                }
                switch (counterRussia) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyRussia + 10 < peopleRussia) healthyRussia += 10;
                                sickRussia -= 10;
                            }
                            case "sredni" -> {
                                if (healthyRussia + 20 < peopleRussia) healthyRussia += 20;
                                sickRussia -= 20;
                            }
                            case "trudny" -> {
                                if (healthyRussia + 45 < peopleRussia) healthyRussia += 45;
                                sickRussia -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyRussia + 20 < peopleRussia) healthyRussia += 20;
                                sickRussia -= 20;
                            }
                            case "sredni" -> {
                                if (healthyRussia + 45 < peopleRussia) healthyRussia += 45;
                                sickRussia -= 45;
                            }
                            case "trudny" -> {
                                if (healthyRussia + 70 < peopleRussia) healthyRussia += 70;
                                sickRussia -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyRussia + 45 < peopleRussia) healthyRussia += 45;
                                sickRussia -= 45;
                            }
                            case "sredni" -> {
                                if (healthyRussia + 70 < peopleRussia) healthyRussia += 70;
                                sickRussia -= 70;
                            }
                            case "trudny" -> {
                                if (healthyRussia + 95 < peopleRussia) healthyRussia += 95;
                                sickRussia -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyRussia < peopleRussia) healthyRussia++;
                    }
                    break;
                }
                switch (counterChina) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyChina + 10 < peopleChina) healthyChina += 10;
                                sickChina -= 10;
                            }
                            case "sredni" -> {
                                if (healthyChina + 20 < peopleChina) healthyChina += 20;
                                sickChina -= 20;
                            }
                            case "trudny" -> {
                                if (healthyChina + 45 < peopleChina) healthyChina += 45;
                                sickChina -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyChina + 20 < peopleChina) healthyChina += 20;
                                sickChina -= 20;
                            }
                            case "sredni" -> {
                                if (healthyChina + 45 < peopleChina) healthyChina += 45;
                                sickChina -= 45;
                            }
                            case "trudny" -> {
                                if (healthyChina + 70 < peopleChina) healthyChina += 70;
                                sickChina -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyChina + 45 < peopleChina) healthyChina += 45;
                                sickChina -= 45;
                            }
                            case "sredni" -> {
                                if (healthyChina + 70 < peopleChina) healthyChina += 70;
                                sickChina -= 70;
                            }
                            case "trudny" -> {
                                if (healthyChina + 95 < peopleChina) healthyChina += 95;
                                sickChina -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyChina < peopleChina) healthyChina++;
                    }
                    break;
                }
                switch (counterIndie) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyIndie + 10 < peopleIndie) healthyIndie += 10;
                                sickIndie -= 10;
                            }
                            case "sredni" -> {
                                if (healthyIndie + 20 < peopleIndie) healthyIndie += 20;
                                sickIndie -= 20;
                            }
                            case "trudny" -> {
                                if (healthyIndie + 45 < peopleIndie) healthyIndie += 45;
                                sickIndie -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyIndie + 20 < peopleIndie) healthyIndie += 20;
                                sickIndie -= 20;
                            }
                            case "sredni" -> {
                                if (healthyIndie + 45 < peopleIndie) healthyIndie += 45;
                                sickIndie -= 45;
                            }
                            case "trudny" -> {
                                if (healthyIndie + 70 < peopleIndie) healthyIndie += 70;
                                sickIndie -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyIndie + 45 < peopleIndie) healthyIndie += 45;
                                sickIndie -= 45;
                            }
                            case "sredni" -> {
                                if (healthyIndie + 70 < peopleIndie) healthyIndie += 70;
                                sickIndie -= 70;
                            }
                            case "trudny" -> {
                                if (healthyIndie + 95 < peopleIndie) healthyIndie += 95;
                                sickIndie -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyIndie < peopleIndie) healthyIndie++;
                    }
                    break;
                }
                switch (counterJapan) {
                    case 1:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyJapan + 10 < peopleJapan) healthyJapan += 10;
                                sickJapan -= 10;
                            }
                            case "sredni" -> {
                                if (healthyJapan + 20 < peopleJapan) healthyJapan += 20;
                                sickJapan -= 20;
                            }
                            case "trudny" -> {
                                if (healthyJapan + 45 < peopleJapan) healthyJapan += 45;
                                sickJapan -= 45;
                            }
                        }
                        break;
                    case 2:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyJapan + 20 < peopleJapan) healthyJapan += 20;
                                sickJapan -= 20;
                            }
                            case "sredni" -> {
                                if (healthyJapan + 45 < peopleJapan) healthyJapan += 45;
                                sickJapan -= 45;
                            }
                            case "trudny" -> {
                                if (healthyJapan + 70 < peopleJapan) healthyJapan += 70;
                                sickJapan -= 70;
                            }
                        }
                        break;
                    case 3:
                        switch (poziomTrudnosci) {
                            case "latwy" -> {
                                if (healthyJapan + 45 < peopleJapan) healthyJapan += 45;
                                sickJapan -= 45;
                            }
                            case "sredni" -> {
                                if (healthyJapan + 70 < peopleJapan) healthyJapan += 70;
                                sickJapan -= 70;
                            }
                            case "trudny" -> {
                                if (healthyJapan + 95 < peopleJapan) healthyJapan += 95;
                                sickJapan -= 95;
                            }
                        }
                        break;
                    default: {
                        if (healthyJapan < peopleJapan) healthyJapan++;
                    }
                    break;
                }

                if (poziomTrudnosci.equals("latwy")) {
                    switch (upgrade1) {
                        case 1: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 150 < peopleOverall) healthyOverall += 150;
                            sickOverall -= 150;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade2) {
                        case 1: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 150 < peopleOverall) healthyOverall += 150;
                            sickOverall -= 150;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade3) {
                        case 1: {
                            if (healthyOverall + 250 < peopleOverall) healthyOverall += 250;
                            sickOverall -= 250;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 300 < peopleOverall) healthyOverall += 300;
                            sickOverall -= 300;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 350 < peopleOverall) healthyOverall += 350;
                            sickOverall -= 350;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade4) {
                        case 1: {
                            if (healthyOverall + 400 < peopleOverall) healthyOverall += 400;
                            sickOverall -= 400;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 450 < peopleOverall) healthyOverall += 450;
                            sickOverall -= 450;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 500 < peopleOverall) healthyOverall += 500;
                            sickOverall -= 500;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade5) {
                        case 1: {
                            if (healthyOverall + 400 < peopleOverall) healthyOverall += 400;
                            sickOverall -= 400;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 450 < peopleOverall) healthyOverall += 450;
                            sickOverall -= 450;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 500 < peopleOverall) healthyOverall += 500;
                            sickOverall -= 500;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade6) {
                        case 1: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 150 < peopleOverall) healthyOverall += 150;
                            sickOverall -= 150;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade7) {
                        case 1: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 150 < peopleOverall) healthyOverall += 150;
                            sickOverall -= 150;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade8) {
                        case 1: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 150 < peopleOverall) healthyOverall += 150;
                            sickOverall -= 150;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade9) {
                        case 1: {
                            if (healthyOverall + 2000 < peopleOverall) healthyOverall += 2000;
                            sickOverall -= 2000;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 4000 < peopleOverall) healthyOverall += 4000;
                            sickOverall -= 4000;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 6000 < peopleOverall) healthyOverall += 6000;
                            sickOverall -= 6000;
                        }
                        break;
                        default:
                            break;
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    switch (upgrade1) {
                        case 1: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 50;
                            sickOverall -= 50;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 75 < peopleOverall) healthyOverall += 75;
                            sickOverall -= 75;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade2) {
                        case 1: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 75 < peopleOverall) healthyOverall += 75;
                            sickOverall -= 75;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade3) {
                        case 1: {
                            if (healthyOverall + 125 < peopleOverall) healthyOverall += 125;
                            sickOverall -= 125;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 150 < peopleOverall) healthyOverall += 150;
                            sickOverall -= 150;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 175 < peopleOverall) healthyOverall += 175;
                            sickOverall -= 175;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade4) {
                        case 1: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 225 < peopleOverall) healthyOverall += 225;
                            sickOverall -= 225;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 250 < peopleOverall) healthyOverall += 250;
                            sickOverall -= 250;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade5) {
                        case 1: {
                            if (healthyOverall + 200 < peopleOverall) healthyOverall += 200;
                            sickOverall -= 200;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 225 < peopleOverall) healthyOverall += 225;
                            sickOverall -= 225;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 250 < peopleOverall) healthyOverall += 250;
                            sickOverall -= 250;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade6) {
                        case 1: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 50;
                            sickOverall -= 50;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 75 < peopleOverall) healthyOverall += 75;
                            sickOverall -= 75;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade7) {
                        case 1: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 50;
                            sickOverall -= 50;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 75 < peopleOverall) healthyOverall += 75;
                            sickOverall -= 75;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade8) {
                        case 1: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 50;
                            sickOverall -= 50;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 75 < peopleOverall) healthyOverall += 75;
                            sickOverall -= 75;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 100 < peopleOverall) healthyOverall += 100;
                            sickOverall -= 100;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade9) {
                        case 1: {
                            if (healthyOverall + 1000 < peopleOverall) healthyOverall += 1000;
                            sickOverall -= 1000;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 2000 < peopleOverall) healthyOverall += 2000;
                            sickOverall -= 2000;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 3000 < peopleOverall) healthyOverall += 3000;
                            sickOverall -= 3000;
                        }
                        break;
                        default:
                            break;
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    switch (upgrade1) {
                        case 1: {
                            if (healthyOverall + 10 < peopleOverall) healthyOverall += 10;
                            sickOverall -= 10;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 15 < peopleOverall) healthyOverall += 15;
                            sickOverall -= 15;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 20 < peopleOverall) healthyOverall += 20;
                            sickOverall -= 20;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade2) {
                        case 1: {
                            if (healthyOverall + 10 < peopleOverall) healthyOverall += 10;
                            sickOverall -= 10;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 15 < peopleOverall) healthyOverall += 15;
                            sickOverall -= 15;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 20 < peopleOverall) healthyOverall += 20;
                            sickOverall -= 20;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade3) {
                        case 1: {
                            if (healthyOverall + 25 < peopleOverall) healthyOverall += 25;
                            sickOverall -= 25;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 30 < peopleOverall) healthyOverall += 30;
                            sickOverall -= 30;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 35 < peopleOverall) healthyOverall += 35;
                            sickOverall -= 35;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade4) {
                        case 1: {
                            if (healthyOverall + 40 < peopleOverall) healthyOverall += 40;
                            sickOverall -= 40;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 45 < peopleOverall) healthyOverall += 45;
                            sickOverall -= 45;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 50;
                            sickOverall -= 50;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade5) {
                        case 1: {
                            if (healthyOverall + 40 < peopleOverall) healthyOverall += 40;
                            sickOverall -= 40;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 45 < peopleOverall) healthyOverall += 45;
                            sickOverall -= 45;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 50 < peopleOverall) healthyOverall += 50;
                            sickOverall -= 50;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade6) {
                        case 1: {
                            if (healthyOverall + 10 < peopleOverall) healthyOverall += 10;
                            sickOverall -= 10;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 15 < peopleOverall) healthyOverall += 15;
                            sickOverall -= 15;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 20 < peopleOverall) healthyOverall += 20;
                            sickOverall -= 20;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade7) {
                        case 1: {
                            if (healthyOverall + 10 < peopleOverall) healthyOverall += 10;
                            sickOverall -= 10;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 15 < peopleOverall) healthyOverall += 15;
                            sickOverall -= 15;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 20 < peopleOverall) healthyOverall += 20;
                            sickOverall -= 20;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade8) {
                        case 1: {
                            if (healthyOverall + 10 < peopleOverall) healthyOverall += 10;
                            sickOverall -= 10;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 15 < peopleOverall) healthyOverall += 15;
                            sickOverall -= 15;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 20 < peopleOverall) healthyOverall += 20;
                            sickOverall -= 20;
                        }
                        break;
                        default:
                            break;
                    }
                    switch (upgrade9) {
                        case 1: {
                            if (healthyOverall + 500 < peopleOverall) healthyOverall += 500;
                            sickOverall -= 500;
                        }
                        break;
                        case 2: {
                            if (healthyOverall + 1000 < peopleOverall) healthyOverall += 1000;
                            sickOverall -= 1000;
                        }
                        break;
                        case 3: {
                            if (healthyOverall + 1500 < peopleOverall) healthyOverall += 1500;
                            sickOverall -= 1500;
                        }
                        break;
                        default:
                            break;
                    }
                }
                healthyOverall = healthyCanada + healthyUSA + healthyBrazil + healthyPoland + healthyEgypt + healthyAustralia + healthyGreenland + healthyRussia + healthyChina + healthyIndie + healthyJapan;
                healthyOverallLabel.setText("Healthy Overall = " + healthyOverall);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (healthyOverall == peopleOverall-1000) {
                    Thread.currentThread().interrupt();
                    try {
                        Win();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    frame.dispose();
                }
            }
        });
        healthyThread.start();

        rootPanel.add(bottomPanel, BorderLayout.PAGE_END);
        rootPanel.add(topPanel, BorderLayout.PAGE_START);
        frame.add(rootPanel);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Win() throws IOException {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);

        JLabel winLabel = new JLabel("!!! YOU WON !!!");
        winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winLabel.setOpaque(true);
        winLabel.setBackground(Color.BLACK);
        winLabel.setFont(new Font("Courier", Font.BOLD, 26));
        winLabel.setForeground(Color.GREEN);

        DecimalFormat dFormat4 = new DecimalFormat("0000");
        JLabel pointsLabel = new JLabel("Points : 0000");
        pointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pointsLabel.setOpaque(true);
        pointsLabel.setBackground(Color.BLACK);
        pointsLabel.setFont(new Font("Courier", Font.BOLD, 26));
        pointsLabel.setForeground(Color.WHITE);
        dPunkty = dFormat4.format(punkty);
        pointsLabel.setText("Points: " + dPunkty);

        JButton winButton = new JButton("Return to menu");
        winButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        winButton.setSize(10, 20);
        winButton.setFont(new Font("Courier", Font.BOLD, 20));
        winButton.setOpaque(false);
        winButton.setBackground(Color.BLACK);
        winButton.setForeground(Color.WHITE);
        winButton.setBorderPainted(true);
        winButton.setBorder(new LineBorder(Color.WHITE, 1));
        winButton.addActionListener(e -> {
            OknoStartowe();
            frame.dispose();
        });

        Save wynik = new Save(punkty, nazwaLekarstwa, "win");
        oos.writeObject(wynik);

        centerPanel.add(winLabel);
        centerPanel.add(pointsLabel);
        centerPanel.add(winButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Lose() throws IOException {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);

        JLabel winLabel = new JLabel("!!! YOU LOST !!!");
        winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winLabel.setOpaque(true);
        winLabel.setBackground(Color.BLACK);
        winLabel.setFont(new Font("Courier", Font.BOLD, 26));
        winLabel.setForeground(Color.RED);

        DecimalFormat dFormat4 = new DecimalFormat("0000");
        JLabel pointsLabel = new JLabel("Points : 0000");
        pointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pointsLabel.setOpaque(true);
        pointsLabel.setBackground(Color.BLACK);
        pointsLabel.setFont(new Font("Courier", Font.BOLD, 26));
        pointsLabel.setForeground(Color.WHITE);
        dPunkty = dFormat4.format(punkty);
        pointsLabel.setText("Points: " + dPunkty);

        JButton winButton = new JButton("Return to menu");
        winButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        winButton.setSize(10, 20);
        winButton.setFont(new Font("Courier", Font.BOLD, 20));
        winButton.setOpaque(false);
        winButton.setBackground(Color.BLACK);
        winButton.setForeground(Color.WHITE);
        winButton.setBorderPainted(true);
        winButton.setBorder(new LineBorder(Color.WHITE, 1));
        winButton.addActionListener(e -> {
            OknoStartowe();
            frame.dispose();
        });

        Save wynik = new Save(punkty, nazwaLekarstwa, "lose");
        oos.writeObject(wynik);

        centerPanel.add(winLabel);
        centerPanel.add(pointsLabel);
        centerPanel.add(winButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Upgrades() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("UPGRADES");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.BOLD, 18));
        nameLabel.setForeground(Color.BLUE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel infoLabel = new JLabel("Costs --> 1 cure point");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoLabel.setOpaque(true);
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(nameLabel);
        topPanel.add(infoLabel);

        JPanel upgradesPanel = new JPanel();
        upgradesPanel.setLayout(new BoxLayout(upgradesPanel, BoxLayout.Y_AXIS));
        upgradesPanel.setBackground(Color.BLACK);

        JPanel upgrade1Panel = new JPanel(new FlowLayout());
        upgrade1Panel.setBackground(Color.BLACK);
        upgrade1Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade1Label = new JLabel("Research : " + upgrade1 + "/3 --> ");
        upgrade1Label.setSize(10, 20);
        upgrade1Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade1Label.setOpaque(false);
        upgrade1Label.setBackground(Color.BLACK);
        upgrade1Label.setForeground(Color.GREEN);
        JButton apply1 = new JButton("+");
        apply1.setSize(10, 20);
        apply1.setFont(new Font("Courier", Font.BOLD, 20));
        apply1.setOpaque(false);
        apply1.setBackground(Color.BLACK);
        apply1.setForeground(Color.WHITE);
        apply1.setBorderPainted(false);
        upgrade1Panel.add(upgrade1Label);
        upgrade1Panel.add(apply1);
        upgradesPanel.add(upgrade1Panel);

        JPanel upgrade2Panel = new JPanel(new FlowLayout());
        upgrade2Panel.setBackground(Color.BLACK);
        upgrade2Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade2Label = new JLabel("DNA tests : " + upgrade2 + "/3 --> ");
        upgrade2Label.setSize(10, 20);
        upgrade2Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade2Label.setOpaque(false);
        upgrade2Label.setBackground(Color.BLACK);
        upgrade2Label.setForeground(Color.DARK_GRAY);
        JButton apply2 = new JButton("+");
        apply2.setSize(10, 20);
        apply2.setFont(new Font("Courier", Font.BOLD, 20));
        apply2.setOpaque(false);
        apply2.setBackground(Color.BLACK);
        apply2.setForeground(Color.WHITE);
        apply2.setBorderPainted(false);
        if (upgrade1 != 3)
            apply2.setEnabled(false);
        else {
            apply2.setEnabled(true);
            upgrade2Label.setForeground(Color.GREEN);
        }
        upgrade2Panel.add(upgrade2Label);
        upgrade2Panel.add(apply2);
        upgradesPanel.add(upgrade2Panel);

        JPanel upgrade3Panel = new JPanel(new FlowLayout());
        upgrade3Panel.setBackground(Color.BLACK);
        upgrade3Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade3Label = new JLabel("Improving the medicine : " + upgrade3 + "/3 --> ");
        upgrade3Label.setSize(10, 20);
        upgrade3Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade3Label.setOpaque(false);
        upgrade3Label.setBackground(Color.BLACK);
        upgrade3Label.setForeground(Color.DARK_GRAY);
        JButton apply3 = new JButton("+");
        apply3.setSize(10, 20);
        apply3.setFont(new Font("Courier", Font.BOLD, 20));
        apply3.setOpaque(false);
        apply3.setBackground(Color.BLACK);
        apply3.setForeground(Color.WHITE);
        apply3.setBorderPainted(false);
        if (upgrade1 != 3)
            apply3.setEnabled(false);
        else {
            apply3.setEnabled(true);
            upgrade3Label.setForeground(Color.GREEN);
        }
        upgrade3Panel.add(upgrade3Label);
        upgrade3Panel.add(apply3);
        upgradesPanel.add(upgrade3Panel);

        JPanel upgrade4Panel = new JPanel(new FlowLayout());
        upgrade4Panel.setBackground(Color.BLACK);
        upgrade4Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade4Label = new JLabel("Producing vaccines : " + upgrade4 + "/3 --> ");
        upgrade4Label.setSize(10, 20);
        upgrade4Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade4Label.setOpaque(false);
        upgrade4Label.setBackground(Color.BLACK);
        upgrade4Label.setForeground(Color.DARK_GRAY);
        JButton apply4 = new JButton("+");
        apply4.setSize(10, 20);
        apply4.setFont(new Font("Courier", Font.BOLD, 20));
        apply4.setOpaque(false);
        apply4.setBackground(Color.BLACK);
        apply4.setForeground(Color.WHITE);
        apply4.setBorderPainted(false);
        if ((upgrade2 != 3) && (upgrade3 != 3))
            apply4.setEnabled(false);
        else {
            apply4.setEnabled(true);
            upgrade4Label.setForeground(Color.GREEN);
        }
        upgrade4Panel.add(upgrade4Label);
        upgrade4Panel.add(apply4);
        upgradesPanel.add(upgrade4Panel);

        JPanel upgrade5Panel = new JPanel(new FlowLayout());
        upgrade5Panel.setBackground(Color.BLACK);
        upgrade5Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade5Label = new JLabel("Producing tablets : " + upgrade5 + "/3 --> ");
        upgrade5Label.setSize(10, 20);
        upgrade5Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade5Label.setOpaque(false);
        upgrade5Label.setBackground(Color.BLACK);
        upgrade5Label.setForeground(Color.DARK_GRAY);
        JButton apply5 = new JButton("+");
        apply5.setSize(10, 20);
        apply5.setFont(new Font("Courier", Font.BOLD, 20));
        apply5.setOpaque(false);
        apply5.setBackground(Color.BLACK);
        apply5.setForeground(Color.WHITE);
        apply5.setBorderPainted(false);
        if ((upgrade2 != 3) && (upgrade3 != 3))
            apply5.setEnabled(false);
        else {
            apply5.setEnabled(true);
            upgrade5Label.setForeground(Color.GREEN);
        }
        upgrade5Panel.add(upgrade5Label);
        upgrade5Panel.add(apply5);
        upgradesPanel.add(upgrade5Panel);

        JPanel upgrade6Panel = new JPanel(new FlowLayout());
        upgrade6Panel.setBackground(Color.BLACK);
        upgrade6Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade6Label = new JLabel("Order to wear masks : " + upgrade6 + "/3 --> ");
        upgrade6Label.setSize(10, 20);
        upgrade6Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade6Label.setOpaque(false);
        upgrade6Label.setBackground(Color.BLACK);
        upgrade6Label.setForeground(Color.GREEN);
        JButton apply6 = new JButton("+");
        apply6.setSize(10, 20);
        apply6.setFont(new Font("Courier", Font.BOLD, 20));
        apply6.setOpaque(false);
        apply6.setBackground(Color.BLACK);
        apply6.setForeground(Color.WHITE);
        apply6.setBorderPainted(false);
        upgrade6Panel.add(upgrade6Label);
        upgrade6Panel.add(apply6);
        upgradesPanel.add(upgrade6Panel);

        JPanel upgrade7Panel = new JPanel(new FlowLayout());
        upgrade7Panel.setBackground(Color.BLACK);
        upgrade7Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade7Label = new JLabel("Quarantine : " + upgrade7 + "/3 --> ");
        upgrade7Label.setSize(10, 20);
        upgrade7Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade7Label.setOpaque(false);
        upgrade7Label.setBackground(Color.BLACK);
        upgrade7Label.setForeground(Color.GREEN);
        JButton apply7 = new JButton("+");
        apply7.setSize(10, 20);
        apply7.setFont(new Font("Courier", Font.BOLD, 20));
        apply7.setOpaque(false);
        apply7.setBackground(Color.BLACK);
        apply7.setForeground(Color.WHITE);
        apply7.setBorderPainted(false);
        upgrade7Panel.add(upgrade7Label);
        upgrade7Panel.add(apply7);
        upgradesPanel.add(upgrade7Panel);

        JPanel upgrade8Panel = new JPanel(new FlowLayout());
        upgrade8Panel.setBackground(Color.BLACK);
        upgrade8Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade8Label = new JLabel("Order to wash your hands : " + upgrade8 + "/3 --> ");
        upgrade8Label.setSize(10, 20);
        upgrade8Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade8Label.setOpaque(false);
        upgrade8Label.setBackground(Color.BLACK);
        upgrade8Label.setForeground(Color.GREEN);
        JButton apply8 = new JButton("+");
        apply8.setSize(10, 20);
        apply8.setFont(new Font("Courier", Font.BOLD, 20));
        apply8.setOpaque(false);
        apply8.setBackground(Color.BLACK);
        apply8.setForeground(Color.WHITE);
        apply8.setBorderPainted(false);
        upgrade8Panel.add(upgrade8Label);
        upgrade8Panel.add(apply8);
        upgradesPanel.add(upgrade8Panel);

        JPanel upgrade9Panel = new JPanel(new FlowLayout());
        upgrade9Panel.setBackground(Color.BLACK);
        upgrade9Panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel upgrade9Label = new JLabel("Order to take the cure : " + upgrade9 + "/3 --> ");
        upgrade9Label.setSize(10, 20);
        upgrade9Label.setFont(new Font("Courier", Font.BOLD, 16));
        upgrade9Label.setOpaque(false);
        upgrade9Label.setBackground(Color.BLACK);
        upgrade9Label.setForeground(Color.DARK_GRAY);
        JButton apply9 = new JButton("+");
        apply9.setSize(10, 20);
        apply9.setFont(new Font("Courier", Font.BOLD, 20));
        apply9.setOpaque(false);
        apply9.setBackground(Color.BLACK);
        apply9.setForeground(Color.WHITE);
        apply9.setBorderPainted(false);
        if ((upgrade4 != 3) && (upgrade5 != 3))
            apply9.setEnabled(false);
        else {
            apply9.setEnabled(true);
            upgrade9Label.setForeground(Color.GREEN);
        }
        upgrade9Panel.add(upgrade9Label);
        upgrade9Panel.add(apply9);
        upgradesPanel.add(upgrade9Panel);

        //Dzialanie upgradow
        apply1.addActionListener(e -> {
            if ((upgrade1 < 3) && (punktyCure >= 1)) {
                upgrade1++;
                punktyCure--;
                upgrade1Label.setText("Research : " + upgrade1 + "/3 --> ");
                if (upgrade1 == 3) {
                    apply2.setEnabled(true);
                    upgrade2Label.setForeground(Color.GREEN);
                    apply3.setEnabled(true);
                    upgrade3Label.setForeground(Color.GREEN);
                }
            }
        });
        apply2.addActionListener(e -> {
            if ((upgrade2 < 3) && (punktyCure >= 1)) {
                upgrade2++;
                punktyCure--;
                upgrade2Label.setText("DNA tests : " + upgrade2 + "/3 --> ");
                if ((upgrade2 == 3) && (upgrade3 == 3)) {
                    apply4.setEnabled(true);
                    upgrade4Label.setForeground(Color.GREEN);
                    apply5.setEnabled(true);
                    upgrade5Label.setForeground(Color.GREEN);
                }
            }
        });
        apply3.addActionListener(e -> {
            if ((upgrade3 < 3) && (punktyCure >= 1)) {
                upgrade3++;
                punktyCure--;
                upgrade3Label.setText("Improving the medicine : " + upgrade3 + "/3 --> ");
                if ((upgrade2 == 3) && (upgrade3 == 3)) {
                    apply4.setEnabled(true);
                    upgrade4Label.setForeground(Color.GREEN);
                    apply5.setEnabled(true);
                    upgrade5Label.setForeground(Color.GREEN);
                }
            }
        });
        apply4.addActionListener(e -> {
            if ((upgrade4 < 3) && (punktyCure >= 1)) {
                upgrade4++;
                punktyCure--;
                upgrade4Label.setText("Producing vaccines : " + upgrade4 + "/3 --> ");
                if ((upgrade4 == 3) && (upgrade5 == 3)) {
                    apply9.setEnabled(true);
                    upgrade9Label.setForeground(Color.GREEN);
                }
            }
        });
        apply5.addActionListener(e -> {
            if ((upgrade5 < 3) && (punktyCure >= 1)) {
                upgrade5++;
                punktyCure--;
                upgrade5Label.setText("Producing tablets : " + upgrade5 + "/3 --> ");
                if ((upgrade4 == 3) && (upgrade5 == 3)) {
                    apply9.setEnabled(true);
                    upgrade9Label.setForeground(Color.GREEN);
                }
            }
        });
        apply6.addActionListener(e -> {
            if ((upgrade6 < 3) && (punktyCure >= 1)) {
                upgrade6++;
                punktyCure--;
                upgrade6Label.setText("Order to wear masks : " + upgrade6 + "/3 --> ");
            }
        });
        apply7.addActionListener(e -> {
            if ((upgrade7 < 3) && (punktyCure >= 1)) {
                upgrade7++;
                punktyCure--;
                upgrade7Label.setText("Quarantine : " + upgrade7 + "/3 --> ");
            }
        });
        apply8.addActionListener(e -> {
            if ((upgrade8 < 3) && (punktyCure >= 1)) {
                upgrade8++;
                punktyCure--;
                upgrade8Label.setText("Order to wash your hands : " + upgrade8 + "/3 --> ");
            }
        });
        apply9.addActionListener(e -> {
            if ((upgrade9 < 3) && (punktyCure >= 1)) {
                upgrade9++;
                punktyCure--;
                upgrade9Label.setText("Order to take the cure : " + upgrade9 + "/3 --> ");
            }
        });

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(upgradesPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Canada() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("CANADA");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);

        JLabel sick = new JLabel("SICK: " + sickCanada);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyCanada);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeCanada)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickCanada >= peopleCanada * 0.25) && (planeCanada));
        applyPlane.addActionListener(e -> {
            if (planeCanada) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickCanada >= peopleCanada * 0.05) {
                        planeCanada = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickCanada >= peopleCanada * 0.075) {
                        planeCanada = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickCanada >= peopleCanada * 0.1) {
                        planeCanada = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busCanada)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickCanada >= peopleCanada * 0.25) && (busCanada));
        applyBus.addActionListener(e -> {
            if (busCanada) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickCanada >= peopleCanada * 0.15) {
                        busCanada = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickCanada >= peopleCanada * 0.2) {
                        busCanada = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickCanada >= peopleCanada * 0.25) {
                        busCanada = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipCanada)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickCanada >= peopleCanada * 0.5) && (shipCanada));
        applyShip.addActionListener(e -> {
            if (shipCanada) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickCanada >= peopleCanada * 0.3) {
                        shipCanada = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickCanada >= peopleCanada * 0.4) {
                        shipCanada = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickCanada >= peopleCanada * 0.5) {
                        shipCanada = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickCanada);
                healthy.setText("HEALTHY: " + healthyCanada);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void USA() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("USA");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickUSA);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyUSA);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeUSA)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickUSA >= peopleUSA * 0.25) && (planeUSA));
        applyPlane.addActionListener(e -> {
            if (planeUSA) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickUSA >= peopleUSA * 0.05) {
                        planeUSA = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickUSA >= peopleUSA * 0.075) {
                        planeUSA = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickUSA >= peopleUSA * 0.1) {
                        planeUSA = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busUSA)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickUSA >= peopleUSA * 0.25) && (busUSA));
        applyBus.addActionListener(e -> {
            if (busUSA) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickUSA >= peopleUSA * 0.15) {
                        busUSA = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickUSA >= peopleUSA * 0.2) {
                        busUSA = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickUSA >= peopleUSA * 0.25) {
                        busUSA = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipUSA)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickUSA >= peopleUSA * 0.5) && (shipUSA));
        applyShip.addActionListener(e -> {
            if (shipUSA) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickUSA >= peopleUSA * 0.3) {
                        shipUSA = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickUSA >= peopleUSA * 0.4) {
                        shipUSA = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickUSA >= peopleUSA * 0.5) {
                        shipUSA = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickUSA);
                healthy.setText("HEALTHY: " + healthyUSA);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Brazil() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("BRAZIL");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickBrazil);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyBrazil);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeBrazil)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickBrazil >= peopleBrazil * 0.25) && (planeBrazil));
        applyPlane.addActionListener(e -> {
            if (planeBrazil) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickBrazil >= peopleBrazil * 0.05) {
                        planeBrazil = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickBrazil >= peopleBrazil * 0.075) {
                        planeBrazil = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickBrazil >= peopleBrazil * 0.1) {
                        planeBrazil = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busBrazil)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickBrazil >= peopleBrazil * 0.25) && (busBrazil));
        applyBus.addActionListener(e -> {
            if (busBrazil) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickBrazil >= peopleBrazil * 0.15) {
                        busBrazil = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickBrazil >= peopleBrazil * 0.2) {
                        busBrazil = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickBrazil >= peopleBrazil * 0.25) {
                        busBrazil = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipBrazil)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickBrazil >= peopleBrazil * 0.5) && (shipBrazil));
        applyShip.addActionListener(e -> {
            if (shipBrazil) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickBrazil >= peopleBrazil * 0.3) {
                        shipBrazil = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickBrazil >= peopleBrazil * 0.4) {
                        shipBrazil = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickBrazil >= peopleBrazil * 0.5) {
                        shipBrazil = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickBrazil);
                healthy.setText("HEALTHY: " + healthyBrazil);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Poland() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("POLAND");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickPoland);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyPoland);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planePoland)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickPoland >= peoplePoland * 0.25) && (planePoland));
        applyPlane.addActionListener(e -> {
            if (planePoland) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickPoland >= peoplePoland * 0.05) {
                        planePoland = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickPoland >= peoplePoland * 0.075) {
                        planePoland = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickPoland >= peoplePoland * 0.1) {
                        planePoland = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busPoland)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickPoland >= peoplePoland * 0.25) && (busPoland));
        applyBus.addActionListener(e -> {
            if (busPoland) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickPoland >= peoplePoland * 0.15) {
                        busPoland = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickPoland >= peoplePoland * 0.2) {
                        busPoland = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickPoland >= peoplePoland * 0.25) {
                        busPoland = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipPoland)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickPoland >= peoplePoland * 0.5) && (shipPoland));
        applyShip.addActionListener(e -> {
            if (shipPoland) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickPoland >= peoplePoland * 0.3) {
                        shipPoland = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickPoland >= peoplePoland * 0.4) {
                        shipPoland = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickPoland >= peoplePoland * 0.5) {
                        shipPoland = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickPoland);
                healthy.setText("HEALTHY: " + healthyPoland);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Egypt() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("EGYPT");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickEgypt);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyEgypt);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeEgypt)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickEgypt >= peopleEgypt * 0.25) && (planeEgypt));
        applyPlane.addActionListener(e -> {
            if (planeEgypt) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickEgypt >= peopleEgypt * 0.05) {
                        planeEgypt = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickEgypt >= peopleEgypt * 0.075) {
                        planeEgypt = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickEgypt >= peopleEgypt * 0.1) {
                        planeEgypt = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busEgypt)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickEgypt >= peopleEgypt * 0.25) && (busEgypt));
        applyBus.addActionListener(e -> {
            if (busEgypt) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickEgypt >= peopleEgypt * 0.15) {
                        busEgypt = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickEgypt >= peopleEgypt * 0.2) {
                        busEgypt = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickEgypt >= peopleEgypt * 0.25) {
                        busEgypt = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipEgypt)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickEgypt >= peopleEgypt * 0.5) && (shipEgypt));
        applyShip.addActionListener(e -> {
            if (shipEgypt) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickEgypt >= peopleEgypt * 0.3) {
                        shipEgypt = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickEgypt >= peopleEgypt * 0.4) {
                        shipEgypt = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickEgypt >= peopleEgypt * 0.5) {
                        shipEgypt = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickEgypt);
                healthy.setText("HEALTHY: " + healthyEgypt);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Australia() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("AUSTRALIA");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickAustralia);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyAustralia);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeAustralia)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickAustralia >= peopleAustralia * 0.25) && (planeAustralia));
        applyPlane.addActionListener(e -> {
            if (planeAustralia) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickAustralia >= peopleAustralia * 0.05) {
                        planeAustralia = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickAustralia >= peopleAustralia * 0.075) {
                        planeAustralia = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickAustralia >= peopleAustralia * 0.1) {
                        planeAustralia = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busAustralia)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickAustralia >= peopleAustralia * 0.25) && (busAustralia));
        applyBus.addActionListener(e -> {
            if (busAustralia) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickAustralia >= peopleAustralia * 0.15) {
                        busAustralia = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickAustralia >= peopleAustralia * 0.2) {
                        busAustralia = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickAustralia >= peopleAustralia * 0.25) {
                        busAustralia = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipAustralia)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickAustralia >= peopleAustralia * 0.5) && (shipAustralia));
        applyShip.addActionListener(e -> {
            if (shipAustralia) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickAustralia >= peopleAustralia * 0.3) {
                        shipAustralia = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickAustralia >= peopleAustralia * 0.4) {
                        shipAustralia = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickAustralia >= peopleAustralia * 0.5) {
                        shipAustralia = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickAustralia);
                healthy.setText("HEALTHY: " + healthyAustralia);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Greenland() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("GREENLAND");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickGreenland);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyGreenland);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeGreenland)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickGreenland >= peopleGreenland * 0.25) && (planeGreenland));
        applyPlane.addActionListener(e -> {
            if (planeGreenland) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickGreenland >= peopleGreenland * 0.05) {
                        planeGreenland = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickGreenland >= peopleGreenland * 0.075) {
                        planeGreenland = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickGreenland >= peopleGreenland * 0.1) {
                        planeGreenland = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busGreenland)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickGreenland >= peopleGreenland * 0.25) && (busGreenland));
        applyBus.addActionListener(e -> {
            if (busGreenland) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickGreenland >= peopleGreenland * 0.15) {
                        busGreenland = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickGreenland >= peopleGreenland * 0.2) {
                        busGreenland = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickGreenland >= peopleGreenland * 0.25) {
                        busGreenland = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipGreenland)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickGreenland >= peopleGreenland * 0.5) && (shipGreenland));
        applyShip.addActionListener(e -> {
            if (shipGreenland) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickGreenland >= peopleGreenland * 0.3) {
                        shipGreenland = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickGreenland >= peopleGreenland * 0.4) {
                        shipGreenland = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickGreenland >= peopleGreenland * 0.5) {
                        shipGreenland = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickGreenland);
                healthy.setText("HEALTHY: " + healthyGreenland);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Russia() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("RUSSIA");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickRussia);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyRussia);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeRussia)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickRussia >= peopleRussia * 0.25) && (planeRussia));
        applyPlane.addActionListener(e -> {
            if (planeRussia) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickRussia >= peopleRussia * 0.05) {
                        planeRussia = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickRussia >= peopleRussia * 0.075) {
                        planeRussia = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickRussia >= peopleRussia * 0.1) {
                        planeRussia = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busRussia)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickRussia >= peopleRussia * 0.25) && (busRussia));
        applyBus.addActionListener(e -> {
            if (busRussia) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickRussia >= peopleRussia * 0.15) {
                        busRussia = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickRussia >= peopleRussia * 0.2) {
                        busRussia = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickRussia >= peopleRussia * 0.25) {
                        busRussia = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipRussia)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickRussia >= peopleRussia * 0.5) && (shipRussia));
        applyShip.addActionListener(e -> {
            if (shipRussia) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickRussia >= peopleRussia * 0.3) {
                        shipRussia = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickRussia >= peopleRussia * 0.4) {
                        shipRussia = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickRussia >= peopleRussia * 0.5) {
                        shipRussia = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickRussia);
                healthy.setText("HEALTHY: " + healthyRussia);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void China() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("CHINA");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickChina);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyChina);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeChina)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickChina >= peopleChina * 0.25) && (planeChina));
        applyPlane.addActionListener(e -> {
            if (planeChina) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickChina >= peopleChina * 0.05) {
                        planeChina = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickChina >= peopleChina * 0.075) {
                        planeChina = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickChina >= peopleChina * 0.1) {
                        planeChina = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busChina)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickChina >= peopleChina * 0.25) && (busChina));
        applyBus.addActionListener(e -> {
            if (busChina) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickChina >= peopleChina * 0.15) {
                        busChina = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickChina >= peopleChina * 0.2) {
                        busChina = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickChina >= peopleChina * 0.25) {
                        busChina = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipChina)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickChina >= peopleChina * 0.5) && (shipChina));
        applyShip.addActionListener(e -> {
            if (shipChina) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickChina >= peopleChina * 0.3) {
                        shipChina = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickChina >= peopleChina * 0.4) {
                        shipChina = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickChina >= peopleChina * 0.5) {
                        shipChina = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickChina);
                healthy.setText("HEALTHY: " + healthyChina);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Indie() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("INDIE");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickIndie);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyIndie);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeIndie)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickIndie >= peopleIndie * 0.1) && (planeIndie));
        applyPlane.addActionListener(e -> {
            if (planeIndie) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickIndie >= peopleIndie * 0.05) {
                        planeIndie = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickIndie >= peopleIndie * 0.075) {
                        planeIndie = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickIndie >= peopleIndie * 0.1) {
                        planeIndie = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busIndie)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickIndie >= peopleIndie * 0.25) && (busIndie));
        applyBus.addActionListener(e -> {
            if (busIndie) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickIndie >= peopleIndie * 0.15) {
                        busIndie = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickIndie >= peopleIndie * 0.2) {
                        busIndie = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickIndie >= peopleIndie * 0.25) {
                        busIndie = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipIndie)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickIndie >= peopleIndie * 0.5) && (shipIndie));
        applyShip.addActionListener(e -> {
            if (shipIndie) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickIndie >= peopleIndie * 0.3) {
                        shipIndie = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickIndie >= peopleIndie * 0.4) {
                        shipIndie = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickIndie >= peopleIndie * 0.5) {
                        shipIndie = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickIndie);
                healthy.setText("HEALTHY: " + healthyIndie);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void Japan() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("JAPAN");
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setFont(new Font("Courier", Font.ITALIC, 18));
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        JLabel sick = new JLabel("SICK: " + sickJapan);
        sick.setSize(10, 20);
        sick.setFont(new Font("Courier", Font.BOLD, 16));
        sick.setOpaque(false);
        sick.setBackground(Color.BLACK);
        sick.setForeground(Color.RED);
        centerPanel.add(sick, BorderLayout.NORTH);
        JLabel healthy = new JLabel("HEALTHY: " + healthyJapan);
        healthy.setSize(10, 20);
        healthy.setFont(new Font("Courier", Font.BOLD, 16));
        healthy.setOpaque(false);
        healthy.setBackground(Color.BLACK);
        healthy.setForeground(Color.GREEN);
        centerPanel.add(healthy, BorderLayout.CENTER);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new BoxLayout(travelPanel, BoxLayout.Y_AXIS));
        travelPanel.setBackground(Color.BLACK);

        JPanel plane = new JPanel(new FlowLayout());
        plane.setBackground(Color.BLACK);
        plane.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel planeText = new JLabel("Plane ");
        planeText.setSize(10, 20);
        planeText.setFont(new Font("Courier", Font.BOLD, 16));
        planeText.setOpaque(false);
        planeText.setBackground(Color.BLACK);
        planeText.setForeground(Color.WHITE);
        JLabel statusTextPlane = new JLabel(" OPEN ");
        statusTextPlane.setSize(10, 20);
        statusTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextPlane.setOpaque(false);
        statusTextPlane.setBackground(Color.BLACK);
        if (planeJapan)
            statusTextPlane.setForeground(Color.GREEN);
        else {
            statusTextPlane.setForeground(Color.RED);
            statusTextPlane.setText(" CLOSED ");
        }
        JLabel arrowTextPlane = new JLabel(" -->");
        arrowTextPlane.setSize(10, 20);
        arrowTextPlane.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextPlane.setOpaque(false);
        arrowTextPlane.setBackground(Color.BLACK);
        arrowTextPlane.setForeground(Color.WHITE);
        JButton applyPlane = new JButton("✔");
        applyPlane.setSize(10, 20);
        applyPlane.setFont(new Font("Courier", Font.BOLD, 20));
        applyPlane.setOpaque(false);
        applyPlane.setBackground(Color.BLACK);
        applyPlane.setForeground(Color.WHITE);
        applyPlane.setBorderPainted(false);
        applyPlane.setEnabled((sickJapan >= peopleJapan * 0.25) && (planeJapan));
        applyPlane.addActionListener(e -> {
            if (planeJapan) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickJapan >= peopleJapan * 0.05) {
                        planeJapan = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickJapan >= peopleJapan * 0.075) {
                        planeJapan = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickJapan >= peopleJapan * 0.1) {
                        planeJapan = false;
                        statusTextPlane.setText(" CLOSED ");
                        statusTextPlane.setForeground(Color.RED);
                        applyPlane.setEnabled(false);
                    }
                }
            }
        });
        plane.add(planeText);
        plane.add(statusTextPlane);
        plane.add(arrowTextPlane);
        plane.add(applyPlane);
        travelPanel.add(plane);

        JPanel bus = new JPanel(new FlowLayout());
        bus.setBackground(Color.BLACK);
        bus.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel busText = new JLabel("Bus ");
        busText.setSize(10, 20);
        busText.setFont(new Font("Courier", Font.BOLD, 16));
        busText.setOpaque(false);
        busText.setBackground(Color.BLACK);
        busText.setForeground(Color.WHITE);
        JLabel statusTextBus = new JLabel(" OPEN ");
        statusTextBus.setSize(10, 20);
        statusTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextBus.setOpaque(false);
        statusTextBus.setBackground(Color.BLACK);
        if (busJapan)
            statusTextBus.setForeground(Color.GREEN);
        else {
            statusTextBus.setForeground(Color.RED);
            statusTextBus.setText(" CLOSED ");
        }
        JLabel arrowTextBus = new JLabel(" -->");
        arrowTextBus.setSize(10, 20);
        arrowTextBus.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextBus.setOpaque(false);
        arrowTextBus.setBackground(Color.BLACK);
        arrowTextBus.setForeground(Color.WHITE);
        JButton applyBus = new JButton("✔");
        applyBus.setSize(10, 20);
        applyBus.setFont(new Font("Courier", Font.BOLD, 20));
        applyBus.setOpaque(false);
        applyBus.setBackground(Color.BLACK);
        applyBus.setForeground(Color.WHITE);
        applyBus.setBorderPainted(false);
        applyBus.setEnabled((sickJapan >= peopleJapan * 0.25) && (busJapan));
        applyBus.addActionListener(e -> {
            if (busJapan) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickJapan >= peopleJapan * 0.15) {
                        busJapan = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickJapan >= peopleJapan * 0.2) {
                        busJapan = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickJapan >= peopleJapan * 0.25) {
                        busJapan = false;
                        statusTextBus.setText(" CLOSED ");
                        statusTextBus.setForeground(Color.RED);
                        applyBus.setEnabled(false);
                    }
                }
            }
        });
        bus.add(busText);
        bus.add(statusTextBus);
        bus.add(arrowTextBus);
        bus.add(applyBus);
        travelPanel.add(bus);

        JPanel ship = new JPanel(new FlowLayout());
        ship.setBackground(Color.BLACK);
        ship.setAlignmentY(Component.CENTER_ALIGNMENT);
        JLabel shipText = new JLabel("Ship ");
        shipText.setSize(10, 20);
        shipText.setFont(new Font("Courier", Font.BOLD, 16));
        shipText.setOpaque(false);
        shipText.setBackground(Color.BLACK);
        shipText.setForeground(Color.WHITE);
        JLabel statusTextShip = new JLabel(" OPEN ");
        statusTextShip.setSize(10, 20);
        statusTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        statusTextShip.setOpaque(false);
        statusTextShip.setBackground(Color.BLACK);
        if (shipJapan)
            statusTextShip.setForeground(Color.GREEN);
        else {
            statusTextShip.setForeground(Color.RED);
            statusTextShip.setText(" CLOSED ");
        }
        JLabel arrowTextShip = new JLabel(" -->");
        arrowTextShip.setSize(10, 20);
        arrowTextShip.setFont(new Font("Courier", Font.BOLD, 16));
        arrowTextShip.setOpaque(false);
        arrowTextShip.setBackground(Color.BLACK);
        arrowTextShip.setForeground(Color.WHITE);
        JButton applyShip = new JButton("✔");
        applyShip.setSize(10, 20);
        applyShip.setFont(new Font("Courier", Font.BOLD, 20));
        applyShip.setOpaque(false);
        applyShip.setBackground(Color.BLACK);
        applyShip.setForeground(Color.WHITE);
        applyShip.setBorderPainted(false);
        applyShip.setEnabled((sickJapan >= peopleJapan * 0.5) && (shipJapan));
        applyShip.addActionListener(e -> {
            if (shipJapan) {
                if (poziomTrudnosci.equals("latwy")) {
                    if (sickJapan >= peopleJapan * 0.3) {
                        shipJapan = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("sredni")) {
                    if (sickJapan >= peopleJapan * 0.4) {
                        shipJapan = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
                if (poziomTrudnosci.equals("trudny")) {
                    if (sickJapan >= peopleJapan * 0.5) {
                        shipJapan = false;
                        statusTextShip.setText(" CLOSED ");
                        statusTextShip.setForeground(Color.RED);
                        applyShip.setEnabled(false);
                    }
                }
            }
        });
        ship.add(shipText);
        ship.add(statusTextShip);
        ship.add(arrowTextShip);
        ship.add(applyShip);
        travelPanel.add(ship);

        centerPanel.add(travelPanel, BorderLayout.SOUTH);

        JButton quit = new JButton("Ok");
        quit.setSize(10, 20);
        quit.setFont(new Font("Courier", Font.BOLD, 16));
        quit.setOpaque(false);
        quit.setBackground(Color.BLACK);
        quit.setForeground(Color.WHITE);
        quit.setBorderPainted(true);
        quit.setBorder(new LineBorder(Color.WHITE, 1));
        quit.addActionListener(e -> frame.dispose());

        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                sick.setText("SICK: " + sickJapan);
                healthy.setText("HEALTHY: " + healthyJapan);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!frame.isShowing())
                    Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        mainPanel.add(nameLabel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(quit, BorderLayout.PAGE_END);
        frame.add(mainPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}