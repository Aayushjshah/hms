import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.awt.*;
public class Reception extends JFrame implements ActionListener{
    
    // JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;    
    // JButton[] arr={b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12};
    JButton[] arr = new JButton[12];
    String[] labels={"New Customer Info", "Room" ,  "Department", "All Employee INfo",
                    "Customer Info" , "Manager Info" , "Checkout" , "Update Check Status ",
                    "Update Room status","Pickup Service" , "Search Room","Logout"};
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 20);
    Reception(){
    int x=30,i=0;
    for(;i<12;i++){
        arr[i]=new JButton(labels[i]);
        arr[i].setBounds(60,x,250,30);
        arr[i].setForeground(Color.WHITE);
        arr[i].setBackground(Color.BLACK);
        arr[i].setFont(forLabel);
        arr[i].addActionListener(this);
        add(arr[i]);
        x+=40;
    }
//Image

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
    Image i2 = i1.getImage().getScaledInstance(440, 480 , Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel li = new JLabel(i3);
    li.setBounds(350,30,450,480);
    add(li);
        
    
    
    
        //main pane    
        setBounds(530,200,850,570);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae){
        JButton ref = (JButton)ae.getSource();
        if(ref == arr[0]){
            //customer
            this.setVisible(false);
            new AddCustomer();
        }else if( ref == arr[1]){
            //room display
            this.setVisible(false);
            new RoomDisplay();

        }else if( ref == arr[2]){
            
        }else if( ref == arr[3]){
            //all employee info
            this.setVisible(false);
            new EmployeeInfo().setVisible(true);

        }else if( ref == arr[4]){
            
        }else if( ref == arr[5]){
            //manager infor
            this.setVisible(false);
            new ManagerInfo().setVisible(true);
        }else if( ref == arr[6]){
            
        }else if( ref == arr[7]){
            
        }else if( ref == arr[8]){
            
        }else if( ref == arr[9]){
            
        }else if( ref == arr[10]){
            
        }else if( ref == arr[11]){
            System.exit(0);
        }
        
    }
    public static void main(String[] args){
        new Reception();
    }
}
