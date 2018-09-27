package layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class GridLayoutDemo extends JFrame{
    JButton btn1=new JButton("one");
    JButton btn2=new JButton("two");
    JButton btn3=new JButton("three");
    JButton btn4=new JButton("four");
    JButton btn5=new JButton("five");
    GridLayoutDemo(){
        init();
        this.setTitle("��񲼾�");
        this.setResizable(true);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void init(){
        this.setLayout(new GridLayout(2,3,10,5)); //Ĭ��Ϊ1�У�n�У�2��3�У�ˮƽ���10����ֱ���5
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }
    public static void main(String args[]){
        new GridLayoutDemo();
    }
}