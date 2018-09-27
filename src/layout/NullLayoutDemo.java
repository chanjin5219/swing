package layout;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class NullLayoutDemo extends JFrame{
    JButton btn1=new JButton("one");
    JButton btn2=new JButton("two");
    JButton btn3=new JButton("three");
    JButton btn4=new JButton("four");
    JButton btn5=new JButton("five");
    NullLayoutDemo(){
        init();
        this.setTitle("�ղ���");
        this.setResizable(true);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void init(){
        this.setLayout(null);
        btn1.setBounds(10, 0, 100, 50); //x����10��y����0�������100����50
        btn2.setBounds(20, 50, 100, 50);
        btn3.setBounds(30, 100, 100, 50);
        btn4.setBounds(40, 150, 100, 50);
        btn5.setBounds(50, 200, 100, 50);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
         
    }
    public static void main(String args[]){
        new NullLayoutDemo();
    }
}
