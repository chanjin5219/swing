package layout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class FlowLayoutDemo extends JFrame{
  JButton btn1=new JButton("one");
  JButton btn2=new JButton("two");
  JButton btn3=new JButton("three");
  JButton btn4=new JButton("four");
  JButton btn5=new JButton("five");
  FlowLayoutDemo(){
    init();
    this.setTitle("流式布局");
    this.setResizable(true);
    this.setSize(300, 200);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  void init(){
    this.setLayout(new FlowLayout(FlowLayout.LEFT,10,5)); //默认为居中;水平间距10，垂直间距5
    this.add(btn1);
    this.add(btn2);
    this.add(btn3);
    this.add(btn4);
    this.add(btn5);
  }
  public static void main(String args[]){
    new FlowLayoutDemo();
  }
}