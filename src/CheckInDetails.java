import javax.swing.*;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;


public class CheckInDetails extends JFrame implements ActionListener{

    JButton b1,b2,b3;
    JTextField[] tarr = new JTextField[5];
    Choice c1;
    String[] labels ={"Customer ID","Room No." , "Name" ,"Check-In" , "Amount Paid" , "Pending Amount"};
    JLabel[] arr = new JLabel[6];
    int i;

    conn c = new conn();
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    CheckInDetails(){
        int x =80 ;

        JLabel head = new JLabel("Check-In Details");
        head.setFont(new Font("Serif" , Font.PLAIN , 22));
        head.setForeground(Color.BLUE);
        head.setBounds(80,20,200,30);
        add(head);


        for(i=0;i<6;i++){
            arr[i] = new JLabel(labels[i]);
            arr[i].setFont(forLabel);
            add(arr[i]);
            arr[i].setBounds(40,x,130,30);
            
            if(i==0){
                c1 = new Choice();
                String query = "select  number from customer";
                try{
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()){
                        c1.add(rs.getString(1));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                c1.setFont(forLabel);
                c1.setBounds(180,x,150,30);
                add(c1);
                x+=50;
                continue;
            }

            tarr[i-1] = new JTextField();
            tarr[i-1].setFont(forLabel);
            tarr[i-1].setBounds(180,x,150,30);
            add(tarr[i-1]);

            x+=50;
        }

//buttons
        b1 = new JButton("Check");
        b1.setBounds(150,x+=20,150,30);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.addActionListener(this);
        x+=60;
        add(b1);

        b2 = new JButton("Update");
        b2.setBounds(50,x,150,30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setEnabled(false);
        b2.addActionListener(this);
        add(b2);
        b3 = new JButton("Back");
        b3.setBounds(220,x,150,30);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.BLACK);
        
        b3.addActionListener(this);
        add(b3);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(400,50,450,400);
        add(li);

        //main pane
        setBounds(280,170,900,550);
        setLayout(null);
        // getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        JButton temp = (JButton)ae.getSource();
        if(temp == b1){
            //check
            conn c = new conn();
            String query = "select * from customer where number='" + c1.getSelectedItem()+"'";
            try{
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    String fetchedRoomNo=rs.getString(6);
                    tarr[0].setText(fetchedRoomNo);
                    tarr[1].setText(rs.getString(2));
                    tarr[2].setText(rs.getString(7));
                    int deposit = Integer.parseInt(rs.getString(8));
                    tarr[3].setText(Integer.toString(deposit));
                    conn c1 = new conn();
                    String sql = "select price from room where roomNo='" + fetchedRoomNo +"'";
                    // System.out.println(sql);
                    try{
                        ResultSet rs1 = c1.s.executeQuery(sql);
                        while(rs1.next()){
                            Integer totAmt = Integer.parseInt(rs1.getString(1));
                            int ans = totAmt - deposit;
                            tarr[4].setText("" + ans);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                }
            }catch(Exception e){
                e.printStackTrace();
            }


            b2.setEnabled(true);
        }else if( temp == b2){
            //update
            String updateQuery = "update customer set roomNo='"+tarr[0].getText()
            + "', name='"+tarr[1].getText()+"',checkedIn='"+ tarr[2].getText() + "',Deposit ='"+tarr[3].getText()+"' where number='"+c1.getSelectedItem()+"'";
            ;
            // System.out.println(updateQuery);
            conn c3 = new conn();
            try{
                c3.s.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null,"Details updated sucessfully");
            }catch(Exception e){
                e.printStackTrace();
            }

            for(int i=0;i<tarr.length;i++){
                tarr[i].setText("");
                b2.setEnabled(false);
            }
        }else{
            //b3
            new Reception().setVisible(true);
            this.setVisible(false);

        }
    }
    public static void main(String[] args){
        new CheckInDetails().setVisible(true);
    }
}
