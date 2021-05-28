import javax.swing.*;
// import java.sql.*;
import java.awt.Color;
import java.awt.Font;   //abstract window toolkit
import java.awt.Image;
import java.awt.event.*;
public class AddEmployee extends JFrame implements ActionListener{
    JLabel name, gender , age , phone , aadhar , salary , job , email;
    JLabel[] arr = {aadhar ,phone ,email};
    JTextField t1,t2,t3,t4,t5,t6;
    JRadioButton r1,r2;
    JComboBox<String> c1;
    JButton b1;

    Font forLabel = new Font("Tahoma" , Font.PLAIN , 17);
    public AddEmployee() {
        
        //name gender aadhar phone age  salary email
        name = new JLabel("Name:");
        name.setFont(forLabel);
        name.setBounds(60,30,120,27);
        add(name);

        t1 = new JTextField();
        t1.setFont(forLabel);
        t1.setBounds(200,30,150,27);
        add(t1);

        age = new JLabel("Age:");
        age.setFont(forLabel);
        age.setBounds(60 , 80 , 120,27);
        add(age);

        t2 = new JTextField();
        t2.setFont(forLabel);
        t2.setBounds(200,80,150,27);;
        add(t2);

        gender = new JLabel("Gender:");
        gender.setFont(forLabel);
        gender.setBounds(60,130,120,27);
        add(gender);
        job = new JLabel("Job");
        job.setFont(forLabel);
        job.setBounds(60 ,230,120,27);
        add(job);

        salary = new JLabel("Salary:");
        salary.setBounds(60,180,120,27);
        salary.setFont(forLabel);
        add(salary);

        String[] jobs = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff",
        "Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
        c1 = new JComboBox<String>(jobs);
        c1.setBounds(200,230,150,27);
        c1.setFont(forLabel);
        c1.setForeground(Color.MAGENTA);
        c1.setBackground(Color.WHITE);
        add(c1);

        t3 = new JTextField();
        t3.setBounds(200, 180, 150 , 27);
        add(t3);
        int x =230;
        
        for(int i=0 ; i<arr.length;i++){
            arr[i] = new JLabel("aa");
            arr[i].setFont(forLabel);
            arr[i].setBounds(60,x=x+50,120,27);
            add(arr[i]);
        }
        arr[0].setText("Aadhar NO. :");
        arr[1].setText("phone no. :");
        arr[2].setText("email:");
        // aadhar.setForeground(Color.WHITE); nt working

        t4 = new JTextField();
        t4.setFont(forLabel);
        t4.setBounds(200,280,150,27);
        add(t4);
        t5 = new JTextField();
        t5.setFont(forLabel);
        t5.setBounds(200,330,150,27);
        add(t5);
        t6 = new JTextField();
        t6.setFont(forLabel);
        t6.setBounds(200,380,150,27);
        add(t6);

        //Radio button 
        r1 = new JRadioButton("MALE");
        r1.setBackground(Color.WHITE);
        r1.setBounds(200,130,75 ,27);
        add(r1);
        r2 = new JRadioButton("FEMALE");
        r2.setBackground(Color.WHITE);
        r2.setBounds(275,130,75,27);
        add(r2);

//image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel li = new JLabel(i3);
        li.setBounds(400,70,500,600);
        add(li);

        JLabel head = new JLabel("ADD EMPLOYEE DETAILS");
        head.setForeground(Color.MAGENTA);
        head.setFont(new Font("Tahoma",Font.PLAIN , 26));
        head.setBounds(500, 20,400,30);
        add(head);

        //Submit button

        b1 = new JButton("Submit");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.setFont(new Font("Serif" , Font.BOLD , 22));
        b1.addActionListener(this);
        b1.setBounds(200,430,150,30);
        add(b1);

        setBounds(370,200,900,520);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String namef = t1.getText();
            String agef = t2.getText();
            String aadharf = t4.getText();
            String phonef = t5.getText();
            String emailf = t6.getText();
            String salary = t3.getText();
            int sal = Integer.parseInt(salary);
            String genderf = null;
            if(r1.isSelected()){
                genderf = "Male";
            }else if( r2.isSelected()){
                genderf = "Female";
            }

            String jobf = c1.getSelectedItem().toString();
            
            conn n1 = new conn();
            String query = "insert into employee values('" + namef + "','" + agef + "','" + genderf + "'," + sal + ",'"+
            jobf + "','" +aadharf + "','" +phonef + "','" +emailf + "')";
            try{
            //  n1.s.executeQuery(query);  //execute query to fetch data from db.
            n1.s.executeUpdate(query); //updateQuery to update to db.
                // System.out.println(namef + agef + aadharf + phonef + emailf + genderf + jobf);
                JOptionPane.showMessageDialog(null , "Employee added sucessfully");
                this.setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        new AddEmployee();
    }
    
}
