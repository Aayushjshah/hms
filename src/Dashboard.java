import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
public class Dashboard extends JFrame implements ActionListener{
    JMenuBar mb1;
    JMenu m1,m2;
    JMenuItem i1,i2,i3,i4;
        Dashboard(){
            mb1 = new JMenuBar();
            mb1.setBackground(Color.WHITE);
            mb1.setBounds(0,0,1700,30);
            add(mb1);

            Font menuItem = new Font("Serif" , Font.PLAIN , 16);
            m1 = new JMenu("Hotel MANAGEMENT");
            m1.setForeground(Color.RED);
            m1.setFont(new Font("Serif" , Font.PLAIN , 18));
            mb1.add(m1);

            m2 = new JMenu("ADMIN");
            m2.setForeground(Color.BLUE);
            m2.setFont(new Font("Serif" , Font.PLAIN , 18));
            mb1.add(m2);

            i1 =new JMenuItem("Reception");
            i1.setFont(menuItem);
            m1.add(i1);

            i2 = new JMenuItem("Add Employee");
            i2.setFont(menuItem);
            i2.addActionListener(this);
            m2.add(i2);

            i3 = new JMenuItem("Add Drivers");
            i3.setFont(menuItem);
            m2.add(i3);

            i4 = new JMenuItem("Add Rooms");
            i4.setFont(menuItem);
            m2.add(i4);

            ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
            JLabel l1 = new JLabel(ii1);
            l1.setBounds(0,0,1700,830);
            add(l1);
            // l1.setBounds(50,20,100,30);
    

            //welcome
            JLabel l2 = new JLabel("THE TAJ GROUP WLECOMES YOU");
            l2.setBounds(600,100,500,40);
            l2.setForeground(Color.WHITE);
            l2.setFont(new Font("Tahoma" , Font.PLAIN , 38));
            l1.add(l2);

           //keep to the end 
            setBounds(0,0,1700,830);
            setVisible(true);
            setLayout(null);
        }
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource()== i2){
                new AddEmployee().setVisible(true);
            }
        }


    public static void main(String[] args){
        new Dashboard();
    }
}
