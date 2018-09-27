package layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class BorderLayoutDemo extends JFrame{
  JButton btn1=new JButton("��");
  JButton btn2=new JButton("��");
  JButton btn3=new JButton("��");
  JButton btn4=new JButton("��");
  JButton btn5=new JButton("��");
  BorderLayoutDemo(){
    init();
    this.setTitle("�߽粼��");
    this.setResizable(true);
    this.setSize(300, 200);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  void init(){
    this.setLayout(new BorderLayout(10,5)); //Ĭ��Ϊ0��0��ˮƽ���10����ֱ���5
    this.add(btn1,BorderLayout.EAST);
    this.add(btn2,BorderLayout.SOUTH);
    this.add(btn3,BorderLayout.WEST);
    this.add(btn4,BorderLayout.NORTH);
    this.add(btn5,BorderLayout.CENTER);
  }
  public static void main(String args[]){
    new BorderLayoutDemo();
  }
}
