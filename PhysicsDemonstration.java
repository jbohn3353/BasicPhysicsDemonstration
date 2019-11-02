import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private PhysicsPane physicsPane;

        public TestPane() {
            physicsPane = new PhysicsPane();
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = gbc.REMAINDER;
            
            gbc.anchor = GridBagConstraints.PAGE_START;
            
            JButton b1 = new JButton("1");
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    startFirst();
                }
            });
            this.add(b1,gbc);
            
            JButton b2 = new JButton("2");
            b2.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  startSecond();
              }
            });
            this.add(b2,gbc);
      
            JButton b3 = new JButton("3");
            b3.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  startThird();
              }
            });
            this.add(b3,gbc);
      
            JButton b4 = new JButton("4");
            b4.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  startFourth();
              }
            });
            this.add(b4,gbc);
      
            JButton b5 = new JButton("5");
            b5.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  startFifth();
              }
            });
            this.add(b5, gbc);
            
            this.add(physicsPane,gbc);
        }

        protected void startFirst() {
            physicsPane.startFirst();
        }
        
        protected void startSecond() {
            physicsPane.startSecond();
        }
        
        protected void startThird() {
            physicsPane.startThird();
        }
        
        protected void startFourth() {
            physicsPane.startFourth();
        }
        
        protected void startFifth() {
            physicsPane.startFifth();
        }

    }

    public class PhysicsPane extends JPanel {

        private Timer timer;
        private double xPos, yPos;
        private int tick;

        public PhysicsPane() {
            setBackground(Color.CYAN);
        }

        protected void stopTimer() {
            if (timer == null) {
                return;
            }
            timer.stop();
            timer = null;
        }

        public void startFirst() {
            stopTimer();

            xPos = 300;
            yPos = 80;
            tick = 0;

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tick >= 150) {
                        stopTimer();
                        return;
                    }

                    yPos = .1 * tick * tick - 0 * tick + 80;

                    tick++;
                    repaint();
                }
            });
            timer.start();
        }
        
        public void startSecond() {
            stopTimer();

            xPos = 300;
            yPos = 500;
            tick = 0;

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tick >= 150) {
                        stopTimer();
                        return;
                    }

                    yPos = .1 * tick * tick - 15 * tick + 550;

                    tick++;
                    repaint();
                }
            });
            timer.start();
        }
        
        public void startThird() {
            stopTimer();
            
            yPos = 550;
            xPos = 50;
            tick = 0;

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tick >= 150) {
                        stopTimer();
                        return;
                    }

                    yPos = .1*tick*tick - 15*tick + 550; // parametric equation for y
                    xPos = 0*tick*tick + 3*tick + 50;

                    tick++;
                    repaint();
                }
            });
            timer.start();
        }
        
        public void startFourth() {
            stopTimer();
            
            yPos = 80;
            xPos = -4;
            tick = 0;

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tick >= 150) {
                        stopTimer();
                        return;
                    }

                    yPos = .01*tick*tick*tick; // parametric equation for y
                    xPos = 0*tick*tick + tick - 4;

                    tick++;
                    repaint();
                }
            });
            timer.start();
        }
        
        public void startFifth() {
            stopTimer();
            
            yPos = 300;
            xPos = 500;
            tick = 0;

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tick >= 150) {
                        stopTimer();
                        return;
                    }

                    yPos = 200*Math.sin(.05*tick) + 300; // parametric equation for y
                    xPos = 200*Math.cos(.05*tick) + 300;

                    tick++;
                    repaint();
                }
            });
            timer.start();
        }
        
        
        

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600, 600);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.RED);
            g2d.fillOval((int) xPos, (int) yPos, 30, 30);
            g2d.dispose();
        }
    }

}