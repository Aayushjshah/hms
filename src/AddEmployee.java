import javax.swing.*;
import java.awt.Font;   //abstract window toolkit
public class AddEmployee extends JFrame{
    JLabel name, gender , age , phone , aadhar , salary , job , email;
    JTextField t1,t2,t3,t4,t5;
    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    public AddEmployee() {
        setBounds(370,200,900,600);
        setVisible(true);

        //name gender aadhar phone age  salary email
        name = new JLabel("Name:");
        name.setFont(forLabel);
        name.setBounds(60,30,120,27);
        add(name);

        t1 = new JTextField();
        t1.setBounds(200,30,150,27);
        add(t1);

        age = new JLabel("Age:");
        age.setFont(forLabel);
        age.setBounds(60 , 80 , 120,27);
        add(age);

        t2 = new JTextField();
        t2.setBounds(200,80,150,27);;
        add(t2);


        
    }
    
    public static void main(String[] args){
        new AddEmployee();
    }
    
}
