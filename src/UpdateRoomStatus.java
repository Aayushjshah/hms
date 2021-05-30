import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Choice;
import java.sql.*;
import java.awt.event.*;

public class UpdateRoomStatus extends JFrame implements ActionListener{
    
    JTextField[] tarr = new JTextField[3];
    Choice c1= new Choice();
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    JButton b1,b2,b3;
    conn c = new conn();


    UpdateRoomStatus(){


        JLabel head = new JLabel("Update Room Status");
        head.setFont(new Font("Tahoma" , Font.BOLD , 20));
        head.setBounds(60,20,230,30);
        add(head);

        String[] labels = {"Guest Id" , "Room Number" , "Availablilty" ,"Clean Status"};
        JLabel[] arr = new JLabel[4];

        int i=0, x=80;
        for(;i<4;i++){
            arr[i] = new JLabel(labels[i]);
            arr[i].setFont(forLabel);
            arr[i].setBounds(40,x,120,30);
            
            add(arr[i]);
            if(i==0){
                //choice

                

                String query = "select number from customer";
                try{
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()){
                        c1.add(rs.getString(1));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }



                c1.setBounds(200,x,150,30);
                c1.setFont(forLabel);
                c1.setForeground(Color.MAGENTA);
                add(c1);
                x+=70;
                continue;
            }
            tarr[i-1] = new JTextField();
            tarr[i-1].setFont(forLabel);
            tarr[i-1].setBounds(200,x,150,30);
            add(tarr[i-1]);


            x+=70;
        }

//buttons
        b1 = new JButton("Check");
        b1.setFont(forLabel);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.addActionListener(this);
        b1.setBounds(150,x+=20,120,30); x+=60;
        add(b1);


        b2 = new JButton("Update");
        b2.setFont(forLabel);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        b2.setEnabled(false);
        b2.setBounds(50,x,120,30);
        add(b2);

        b3 = new JButton("Back");
        b3.setFont(forLabel);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.BLACK);
        b3.addActionListener(this);
        b3.setBounds(240,x,120,30);
        add(b3);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(350,350,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(500,50,350,350);
        add(li);


//mainPane
        setBounds(280,170,1000,550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae){
        JButton temp = (JButton)ae.getSource();
        if(temp ==b1){
            //check
            
            String query = "select * from customer where number='" + c1.getSelectedItem() + "'";
            try{
                ResultSet rs = c.s.executeQuery(query);
                String roomNum = null ;
                // rs.absolute(1);
                while(rs.next()){
                    // System.out.println(rs.getString(1));
                     roomNum = rs.getString(6);
                    tarr[0].setText(roomNum);
                    
                    // rs.close();
                }

                    //pt2
                    conn c2 = new conn();
                    String query2 = "select * from room where roomNo='" + roomNum + "'";
                    rs =c2.s.executeQuery(query2);
                    while (rs.next()){
                        // rs.absolute(1);
                        if(rs.getInt(3)== 0){
                            tarr[2].setText("Dirty");
                        }else{
                            tarr[2].setText("Clean");
                        }
                        tarr[1].setText(rs.getString(2));

                        
                    }


            }catch(Exception e){
                e.printStackTrace();
            }
            b2.setEnabled(true);
        }else if(temp ==b2){
            //update
            
            String[] addtoDb = new String[3];
            String query = "update room set available='" + tarr[1].getText() +"', clean=";
            if(tarr[2].getText().toLowerCase().equals("dirty")){
                query+=0;
            }else{
                query+=1;
            }
            query+=" where roomNo='"+tarr[0].getText()+"'";

            // System.out.println(query);
            
            conn c = new conn();
            try{
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Room Status Updated");
            }catch(Exception e){
                e.printStackTrace();
            }


        }else{
            //b3
            this.setVisible(false);
            new Reception().setVisible(true);
            
        }
    }
    public static void main(String[] args){
        new UpdateRoomStatus();
    }
}
