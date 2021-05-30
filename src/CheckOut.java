import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Choice;
import java.sql.*;
import java.awt.event.*;

public class CheckOut extends JFrame implements ActionListener{
        
    JButton b1,b2,b3;
    JTextField t1;
    Choice c1;
    
    
        CheckOut(){


            JLabel head = new JLabel("Check Out");
            head.setFont(new Font("Tahoma" , Font.PLAIN , 22));
            head.setForeground(Color.BLUE);
            head.setBounds(100,30,200,30);
            add(head);
            

            Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
            int x=100;
            JLabel l1 = new JLabel("Customer Id");
            l1.setFont(forLabel);
            l1.setBounds(40,x,120,30);
            
            add(l1);
            
            c1 = new Choice();
            
            String sql = "select number from customer";
            conn c = new conn();
            try{
                ResultSet rs = c.s.executeQuery(sql);
                while(rs.next()){
                    c1.add(rs.getString(1));
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            c1.setBounds(170,x,150,30);
            c1.setFont(forLabel);
            add(c1);


            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
            Image i2 = i1.getImage().getScaledInstance(30,20,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JButton b3 = new JButton(i3);
            b3.setBounds(325,x+5,30,20);
            b3.addActionListener(this);
            add(b3);

            x+=50;
            JLabel l2 = new JLabel("Room No");
            l2.setFont(forLabel);
            l2.setBounds(40,x,120,30);
            
            add(l2);

            t1 = new JTextField();
            t1.setBounds(170,x,150,30);
            t1.setForeground(Color.RED);
            t1.setFont(forLabel);
            t1.setEnabled(false);
            add(t1);
            x+=50;
            
            b1 = new JButton("Check Out");
            b1.setBounds(50,x,120,30);
            b1.setFont(forLabel);
            b1.setForeground(Color.WHITE);
            b1.addActionListener(this);
            b1.setEnabled(false);
            b1.setBackground(Color.BLACK);
            add(b1);

            b2 = new JButton("Back");
            b2.setBounds(200,x,120,30);
            b2.setFont(forLabel);
            b2.setForeground(Color.WHITE);
            b2.setBackground(Color.BLACK);
            b2.addActionListener(this);
            add(b2);

            ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
            Image i22 = i11.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
            ImageIcon i33 = new ImageIcon(i22);
            JLabel li2 = new JLabel(i33);
            li2.setBounds(380,40,400,300);
            add(li2);


            //mainPane
            setBounds(280,170,800,350);
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);
            setVisible(true);
        }



        public void actionPerformed(ActionEvent ae){
            JButton temp =(JButton)ae.getSource();
            if(temp == b1){
                //checkout

                //set room as available
                conn c3 = new conn();

                String q = "update room set available='Available' where roomNo='" + t1.getText()+"'";
                    
                try{
                    c3.s.executeUpdate(q);
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                // delete the customer

                String q2 ="delete from customer where number='"+ c1.getSelectedItem() +"'";
                try{
                    c3.s.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null,"Thank You For Choosing Us.Check out sucessful");
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.exit(0);
            }else if(temp == b2){
                //back
                new Reception().setVisible(true);
                this.setVisible(false);

            }else{
                //tick
                b1.setEnabled(true);
                conn c2 = new conn();
                String query = "select roomNo from customer where number='" + c1.getSelectedItem()  +"'";
                try{
                    ResultSet rs2 = c2.s.executeQuery(query);
                    // rs2.first();
                    
                    while(rs2.next()){
                        t1.setText(rs2.getString(1));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    public static void main(String[] args){
        new CheckOut().setVisible(true);
    }
}
